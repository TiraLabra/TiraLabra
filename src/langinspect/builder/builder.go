package builder

/* Builds a trie-based database of n-gram frequencies in different languages */

import (
	"bufio"
	"fmt"
	"github.com/golddranks/TiraLabra/src/trie"
	"os"
	"path/filepath"
	"unsafe"
)

const debugAll = false
const debugSome = true
const stats = true
const maxDepth = 9 // 9-gram as a maximum has space for three 24-bit Japanese/Chinese/Korean characters.

type LangTag []byte
type LangIndex int
type LangTable []int

var amountOfLangs = 1
var ngramCount int
var langStat = make(LangTable, amountOfLangs)
var bytesRead int

func GetFileReaders(dir string) chan *bufio.Reader {
	readerChannel := make(chan *bufio.Reader)
	go func() {
		filepath.Walk(dir, func(path string, f os.FileInfo, err error) error {
			if err != nil {
				fmt.Println("Error!", err)
				return err
			}
			if f.IsDir() {
				fmt.Println("Reading from directory", f.Name())
				return nil
			}
			file, err := os.Open(path)
			if err != nil {
				fmt.Println("Couldn't open " + f.Name() + "!")
				return err
			}
			if debugSome {
				fmt.Println("Opened " + f.Name())
			}
			reader := bufio.NewReader(file)
			readerChannel <- reader
			return nil
		})
		close(readerChannel)
	}()
	return readerChannel
}

func StreamBytes(dir string) chan byte {
	byteStream := make(chan byte)
	readerChannel := GetFileReaders(dir)
	go func() {
		for reader := range readerChannel {
			for {
				b, err := reader.ReadByte()
				if err != nil {
					if debugSome {
						fmt.Println("EOF!")
					}
					break
				}
				byteStream <- b
			}
		}
		close(byteStream)
	}()
	return byteStream
}

func IncrementLang(table LangTable, index LangIndex) {
	if debugAll {
		fmt.Println("Increment!")
	}
	for len(table) < amountOfLangs+1 {
		table = append(table, 0)
	}
	table[index]++
}

func TouchLangData(node *trie.Node, lang LangIndex) {
	if node.Value == nil {
		if debugAll {
			fmt.Println("Initialising LangData object.")
		}
		node.Value = make(LangTable, amountOfLangs)
	}
	table := node.Value.(LangTable)
	IncrementLang(table, lang)
	if stats {
		ngramCount++
	}
	if debugAll {
		fmt.Println("Incremented", string(lang), "to", table[lang])
	}
}

func PrintStats() {
	fmt.Println("Max n-gram length:\t", maxDepth)
	fmt.Println("N-grams read:\t\t", ngramCount)
	fmt.Println("Unique n-grams:\t\t", trie.NodeCount())
	fmt.Printf("Non-unique ratio:\t %.2f%%\n", 100.0-float32(trie.NodeCount())/float32(ngramCount)*100.0)
	fmt.Printf("Size:\t\t\t%d MiB, %d bytes per node.\n", trie.NodeCount()*int(unsafe.Sizeof(trie.Node{}))/(1024*1024), unsafe.Sizeof(trie.Node{}))
	fmt.Println("Learning data:")
	for lang, bytes := range langStat {
		fmt.Printf("\t%s %d Kb\n", lang, bytes/1000)
	}
}

func Build(dir string) {
	byteStream := StreamBytes(dir)
	dict := trie.CreateNode()
	langindex := LangIndex(0)
	langdata := trie.CreateNode()
	highestLangIndex := langindex
	i := 0
	nodes := make([]*trie.Node, 0, maxDepth)
	for b := range byteStream {
		if b == '@' {
			bNext := <-byteStream
			if bNext != '@' {
				langtag := LangTag{bNext, <-byteStream}
				node := langdata.GetOrCreate(langtag)
				if node.Value == nil {
					node.Value = LangIndex(highestLangIndex)
					highestLangIndex++
					if debugSome {
						fmt.Println("New language! ", string(langtag[:]), langindex)
					}
				}
				langindex = node.Value.(LangIndex)
				if debugSome {
					fmt.Println("Changed language to:", string(langtag[:]))
				}
				nodes = nodes[:0] // Clear the nodes buffer
				i = 0
				b = <-byteStream
			}
		}

		if stats {
			IncrementLang(langStat, langindex)
			bytesRead++
			if bytesRead%1000 == 0 {
				fmt.Println(ngramCount, "\t", trie.NodeCount(), "   \t")
			}
		}

		if len(nodes) < maxDepth {
			nodes = append(nodes, nil)
		}
		nodes[i] = dict

		for k := i + len(nodes); k > i; k-- {
			parent := nodes[k%len(nodes)]
			child := parent.GetOrCreate([]byte{b})
			TouchLangData(child, langindex)
			nodes[k%len(nodes)] = child
		}
		i++
		i = i % maxDepth

	}
	PrintStats()
}
