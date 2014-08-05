package builder

/* Builds a trie-based database of n-gram frequencies in different languages */

import (
	"bufio"
	"fmt"
	"github.com/golddranks/TiraLabra/src/trie"
	"io/ioutil"
	"os"
	"path"
)

const debug = true

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

func Build(dir string) {
	byteStream := StreamBytes(dir)
	dict := trie.CreateNode()
	for b := range byteStream {
		dict.Add([]byte{b}, "juu")
	}

	fmt.Println(dict)
}
