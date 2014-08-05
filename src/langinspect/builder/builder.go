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

func StreamBytes(dir string) chan byte {
	byteStream := make(chan byte)
	go func() {
		files, _ := ioutil.ReadDir(dir)
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
		fmt.Printf("%c\n", int(b))
	}
	dict.Add("jee", "juu")
	fmt.Println("output")
}
