package builder

import (
	"bufio"
	"fmt"
	"os"
	"path/filepath"
)

/*
Reads recursively the contents of a directory and returns a channel of
buffered readers of files in the directories.
*/
func getFileReaders(directory string) chan *bufio.Reader {
	readerChannel := make(chan *bufio.Reader)
	go func() {
		defer close(readerChannel)
		filepath.Walk(directory, func(path string, f os.FileInfo, err error) error {
			if err != nil {
				fmt.Println("Error!", err)
				return err
			}
			if f.IsDir() {
				if ShowMessages {
					fmt.Println("Reading from directory", f.Name())
				}
				return nil
			}
			file, err := os.Open(path)
			if err != nil {
				fmt.Println("Couldn't open " + f.Name() + "!")
				return err
			}
			if ShowMessages {
				fmt.Println("Opened " + f.Name())
			}
			reader := bufio.NewReader(file)
			readerChannel <- reader
			return nil
		})
	}()
	return readerChannel
}

/*
Reads recursively a directory and returns a channel of bytes of all the
files contained in the directories.
*/
func streamBytes(directory string) chan byte {
	byteStream := make(chan byte)
	readerChannel := getFileReaders(directory)
	go func() {
		defer close(byteStream)
		for reader := range readerChannel {
			for {
				b, err := reader.ReadByte()
				if err != nil {
					if ShowDebug {
						fmt.Println("EOF!")
					}
					break
				}
				byteStream <- b
			}
		}
	}()
	return byteStream
}
