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

const debug = false
const stats = true
const maxDepth = 9 // 9-gram as a maximum has space for three 24-bit Japanese/Chinese/Korean characters.

var ngramCount int
var langStat = make(map[Lang]int)

func GetFileReaders(dir string) chan *bufio.Reader {
	readerChannel := make(chan *bufio.Reader)
	go func() {
		filepath.Walk(dir, func(path string, f os.FileInfo, err error) error {
			if f.IsDir() {
				fmt.Println("Reading from directory", f.Name())
				return nil
			}
			file, err := os.Open(path)
			if err != nil {
				fmt.Println("Couldn't open " + f.Name() + "!")
				return err
			}
			if debug {
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
					if debug {
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

type Lang [2]byte
type LangData map[Lang]int

func TouchLangData(node *trie.Node, lang Lang) {
	if node.Value == nil {
		if debug {
			fmt.Println("Initialising LangData object.")
		}
		node.Value = make(LangData)
	}
	data := node.Value.(LangData)
	data[lang]++
	if stats {
		ngramCount++
	}
	if debug {
		fmt.Println("Incremented", string(lang[:]), "to", data[lang])
	}
}

func Build(dir string) {
	byteStream := StreamBytes(dir)
	dict := trie.CreateNode()
	lang := Lang{}
	i := 0
	nodes := make([]*trie.Node, 0, maxDepth)
	for b := range byteStream {
		if b == '@' {
			b2 := <-byteStream
			if b2 != '@' {
				lang[0] = b2
				lang[1] = <-byteStream
				if debug {
					fmt.Println("Changed language to:", string(lang[:]))
				}
				b = <-byteStream
			}
		}

		langStat[lang]++

		if len(nodes) < maxDepth {
			nodes = append(nodes, nil)
		}
		nodes[i] = dict

		for k := i + len(nodes); k > i; k-- {
			parent := nodes[k%len(nodes)]
			child := parent.GetOrCreate([]byte{b})
			TouchLangData(child, lang)
			nodes[k%len(nodes)] = child
		}
		i++
		i = i % maxDepth
	}

	fmt.Println("Max n-gram length:\t", maxDepth)
	fmt.Println("N-grams read:\t\t", ngramCount)
	fmt.Println("Unique n-grams:\t\t", trie.NodeCount())
	fmt.Printf("Non-unique ratio:\t %.2f%%\n", 100.0-float32(trie.NodeCount())/float32(ngramCount)*100.0)
	fmt.Println("Size:\t\t\t", float32(trie.NodeCount()*int(unsafe.Sizeof(trie.Node{})))/1024/1024, "MiB,", unsafe.Sizeof(trie.Node{}), " bytes per node.")
	fmt.Println("Learning data:")
	for lang, bytes := range langStat {
		fmt.Println("\t", string(lang[:]), bytes/1000, "Kb")
	}
}
