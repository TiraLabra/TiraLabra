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

// Compile-time settings
const (
	debugAll  = false
	debugSome = true
	stats     = true
	maxDepth  = 9 // 9-gram as a maximum has space for three 24-bit Japanese/Chinese/Korean characters.
)

type LangTag []byte
type LangIndex int
type LangTable []int

const AllLangs = LangIndex(0)

var amountOfLangs = 1
var langStat = make(LangTable, amountOfLangs)
var langTagToIndex *trie.Node
var dict *trie.Node

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

func IncrementLang(table *LangTable, index LangIndex) {
	if debugAll {
		fmt.Println("Increment!")
	}
	for len(*table) < amountOfLangs+1 {
		*table = append(*table, 0)
	}
	(*table)[index]++
}

func TouchLangData(node *trie.Node, lang LangIndex) {
	if node.Value == nil {
		if debugAll {
			fmt.Println("Initialising LangData object.")
		}
		node.Value = make(LangTable, amountOfLangs)
	}
	table := node.Value.(LangTable)
	IncrementLang(&table, AllLangs)
	IncrementLang(&table, lang)
	if stats {
		IncrementLang(&langStat, AllLangs)
		IncrementLang(&langStat, lang)
	}
	if debugAll {
		fmt.Println("Incremented", string(lang), "to", table[lang])
	}
}

func PrintStats() {
	fmt.Println("Max n-gram length:\t", maxDepth)
	fmt.Println("N-grams read:\t\t", langStat[AllLangs])
	fmt.Println("Unique n-grams:\t\t", trie.NodeCount())
	fmt.Printf("Non-unique ratio:\t %.2f%%\n", 100.0-float32(trie.NodeCount())/float32(langStat[AllLangs])*100.0)
	fmt.Printf("Size:\t\t\t%d MiB, %d bytes per node.\n", trie.NodeCount()*int(unsafe.Sizeof(trie.Node{}))/(1024*1024), unsafe.Sizeof(trie.Node{}))
	fmt.Println("Learning data:")

	for node := range langTagToIndex.Walk() {
		langTag := node.Prefix()
		langindex := LangIndex(0)
		if node.Value != nil {
			langindex = node.Value.(LangIndex)
			fmt.Printf("\t%d %s %d Kb\n", langindex, langTag, langStat[langindex]/1000)
		}
	}
}

func SetLang(byteStream chan byte) (bool, LangIndex) {
	bNext := <-byteStream
	if bNext == '@' {
		return false, LangIndex(0)
	} else {
		langtag := LangTag{bNext, <-byteStream}
		node := langTagToIndex.GetOrCreate(langtag)
		if node.Value == nil {
			node.Value = LangIndex(amountOfLangs)
			amountOfLangs++
			if debugSome {
				fmt.Println("New language! ", string(langtag[:]), node.Value)
			}
		}
		langindex := node.Value.(LangIndex)
		if debugSome {
			fmt.Println("Changed language to:", string(langtag[:]))
		}
		return true, langindex
	}
}

func Build(dir string) {
	byteStream := StreamBytes(dir)
	dict = trie.CreateNode() // "dict" is the trie containing all the n-grams
	langindex := LangIndex(1)
	langTagToIndex = trie.CreateNode() // "langTagToIndex" is a trie that converts to langTags to langIndexes
	i := 0
	nodes := make([]*trie.Node, 0, maxDepth) // "nodes" is the ring buffer for n-gram-holding trie nodes
	for b := range byteStream {
		if b == '@' {
			changed, newLangIndex := SetLang(byteStream)
			if changed {
				langindex = newLangIndex
				nodes = nodes[:0] // Clear the nodes buffer
				i = 0
				continue
			}
		}

		if debugSome && stats {
			if langStat[0]%1000 == 0 {
				fmt.Println(langStat[AllLangs], "\t", trie.NodeCount(), "   \t")
			}
		}

		if len(nodes) < maxDepth { // if ringbuffer isn't full yet, append to it
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
