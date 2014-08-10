package builder

/* Builds a trie-based database of n-gram frequencies in different languages */

import (
	"bufio"
	"fmt"
	"github.com/golddranks/TiraLabra/src/trie"
	"math"
	"os"
	"path/filepath"
	"unsafe"
)

// Compile-time settings
const (
	debugAll    = false
	debugSome   = true
	stats       = true
	maxDepth    = 9 // 9-gram as a maximum has space for three 24-bit Japanese/Chinese/Korean characters.
	freqClasses = FreqClass(30)
)

type LangTag []byte
type LangIndex int
type LangTable []int
type FreqClass int

const AllLangs = LangIndex(0)
const AllGrams = int(0)

var amountOfLangs = 0
var ngramStats [maxDepth][freqClasses]LangTable // [n-gram length][frequency class][language index]number of n-grams of that frequency
var langTagToIndex *trie.Node
var langIndexToTag [][]byte = [][]byte{nil}
var dict *trie.Node
var bytesRead int

// Calculates 1.59-based logarithm and rounds.
func FreqToFreqClass(x int) FreqClass {
	return FreqClass((math.Log(float64(x)) / math.Log(1.59)) + 0.5)
}

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
	for len(*table) < amountOfLangs+1 {
		*table = append(*table, 0)
		if debugAll {
			fmt.Println("Table grew! To", len(*table))
		}
	}
	(*table)[index]++
}

func TouchLangData(node *trie.Node, lang LangIndex) {
	if node.Value == nil {
		if debugAll {
			fmt.Println("Initialising LangTable with", amountOfLangs+1, "slots in node: '", string((*node).Prefix()), "'")
		}
		node.Value = make(LangTable, amountOfLangs+1)
	}
	table := node.Value.(LangTable)
	IncrementLang(&table, AllLangs)
	IncrementLang(&table, lang)
	node.Value = table
	if debugAll {
		fmt.Println("Incremented node '", string((*node).Prefix()), "' language", string(langIndexToTag[lang]), "to", table[lang], ". The langtable is now:", table)
	}
}

func PrintStats() {
	fmt.Println("Max n-gram length:\t", maxDepth)
	//	fmt.Println("N-grams read:\t\t", ngramStats[AllGrams][0][AllLangs])
	fmt.Println("Unique n-grams:\t\t", trie.NodeCount())
	fmt.Println("N-grams mentioned at least once:\t\t", ngramStats[AllGrams][0][AllLangs])
	fmt.Printf("Size:\t\t\t%d MiB, %d bytes per node.\n", trie.NodeCount()*int(unsafe.Sizeof(trie.Node{}))/(1024*1024), unsafe.Sizeof(trie.Node{}))
	fmt.Println("Learning data:")

	for node := range langTagToIndex.Walk() {
		langTag := node.Prefix()
		langindex := LangIndex(0)
		if node.Value != nil {
			langindex = node.Value.(LangIndex)
			fmt.Printf("\t%d %s %d n-grams\n", langindex, langTag, ngramStats[AllGrams][0][langindex]/1000)
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
			node.Value = LangIndex(amountOfLangs + 1)
			langIndexToTag = append(langIndexToTag, langtag)
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
	for n := range ngramStats {
		for f := range ngramStats[n] {
			ngramStats[n][f] = make(LangTable, amountOfLangs+1)
		}
	}
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
			bytesRead++
			if bytesRead%1000 == 0 {
				fmt.Println("Nodes:", ngramStats[AllGrams][0][AllLangs])
			}
		}

		if len(nodes) < maxDepth { // if ringbuffer isn't full yet, append to it
			nodes = append(nodes, nil)
		}
		nodes[i] = dict

		for k := i + len(nodes); k > i; k-- {
			j := k % len(nodes)
			parent := nodes[j]
			child := parent.GetOrCreate([]byte{b})
			TouchLangData(child, langindex)
			if stats {
				// STATS ABOUT ALL N-GRAMS
				// Increment the amount of n-grams of length for all languages
				freq := child.Value.(LangTable)[AllLangs]
				IncrementLang(&(ngramStats[AllGrams][FreqToFreqClass(freq)]), AllLangs)
				// Increment the amount of n-grams of length for current language
				freq = child.Value.(LangTable)[langindex]
				IncrementLang(&ngramStats[AllGrams][FreqToFreqClass(freq)], langindex)

				// STATS ABOUT N-GRAMS OF LENGTH J
				// Increment the amount of n-grams of length for all languages
				freq = child.Value.(LangTable)[AllLangs]
				IncrementLang(&ngramStats[j][FreqToFreqClass(freq)], AllLangs)
				// Increment the amount of n-grams of length for current language
				freq = child.Value.(LangTable)[langindex]
				IncrementLang(&ngramStats[j][FreqToFreqClass(freq)], langindex)
			}
			nodes[j] = child
		}
		i++
		i = i % maxDepth

	}
	PrintStats()
}
