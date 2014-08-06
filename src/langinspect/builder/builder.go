package builder

/* Builds a trie-based database of n-gram frequencies in different languages */

import (
	"bufio"
	"fmt"
	"github.com/golddranks/TiraLabra/src/trie"
	"io/ioutil"
	"os"
	"path"
	"unsafe"
)

const debug = true
const maxDepth = 5

var ngramCount int

func GetFileReaders(dir string) chan *bufio.Reader {
	readerChannel := make(chan *bufio.Reader)
	files, _ := ioutil.ReadDir(dir)
	go func() {
		for _, f := range files {
			if f.IsDir() {
				if debug {
					fmt.Println(f.Name() + " is a directory.")
				}
				continue
			}
			file, err := os.Open(path.Join(dir, f.Name()))
			if err != nil {
				fmt.Println("Couldn't open " + f.Name() + "!")
				continue
			}
			if debug {
				fmt.Println("Opened " + f.Name())
			}
			reader := bufio.NewReader(file)
			readerChannel <- reader
		}
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
	if debug {
		ngramCount++
		fmt.Println("Incremented", string(lang[:]), "to", data[lang])
	}
}

func Build(dir string) {
	byteStream := StreamBytes(dir)
	dict := trie.CreateNode()
	lang := [2]byte{}
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

	fmt.Println(dict)
	fmt.Println("Unique nodes: ", trie.NodeCount())
	fmt.Println("MiB: ", float32(trie.NodeCount()*int(unsafe.Sizeof(trie.Node{})))/1024/1024)
	fmt.Println("N-grams: ", ngramCount)
}
