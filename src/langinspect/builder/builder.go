//Package builder builds a database of n-grams (byte sequences of size n) that show up in the learning data.
package builder

import (
	"bufio"
	"fmt"
	"github.com/golddranks/TiraLabra/src/trie"
	"math"
	"os"
	"path/filepath"
	"time"
	//	"unsafe"
)

// Prints annoingly lot of debugging data
const DebugAll = true

// Prints some debugging data
const DebugSome = true

// Keeps stats of the n-gram frequencies
const KeepStats = true

// Does some additional runtime checks
const Assert = true

// Maximum length of n-grams in the database. The size is in bytes.
//The value 9 is recommended, as it is enough to record three consecutive Japanese/Chinese/Korean characters in UTF-8.
const MaxDepth = 9

// Amount of frequency classes in the statistics (NGramStats). The value 47 represents
// frequency values around the upper bound of 32-bit integer.
// So it's not gonna overflow too easily.
const FreqClasses = FreqClass(47)

// Represents a (usually) two-byte marker that specifies a language.
// The language tags should be in UTF-8 and two-character ISO-639 language codes.
type LangTag []byte

/*
Index type for the LangTable type.
*/
type LangIndex int

/*
A container for the n-gram frequency data of a single n-gram (represented by
a single trie.Node). The index is of type LangIndex. Index AllLangs (= 0)
represents the general frequency of the n-gram disregarding the language.
*/
type LangTable []int

/*
In the statistics data, the frequencies of the n-grams are divided into
frequency classes represented by FreqClass type. The raw frequency value can
be converted to a frequency class using IntToFreqClass() function.
*/
type FreqClass int

/*
A named index value for type LangTable. Represents the frequency of the containing
n-gram disregarding the language.
*/
const AllLangs = LangIndex(0)

/*
A named index value for variable NGramStats first index. Represents the statistics of
all the n-grams regardless of their length.
*/
const AllGrams = int(0)

/*
Amount of languages in the data. Builder can increment the amount automatically,
but it's better to set up on compile-time.
*/
var AmountOfLangs = 0

/*
Contains general statistics of the n-grams read.

The first index corresponds to the length of the n-gram. Index AllGrams (= 0)
contains accumulated stats of all n-grams disregarding the length.

The second index corresponds to the frequency class of n-grams.
class 0 = Mentioned at least once in the data. (freq = 1)
class 1 = Mentioned at least twice in the data. (freq = 2)
class 2 = Mentioned at least thrice in the data. (freq = 3)
class 3 = Mentioned at least 4-5 in the data. (freq = 4-5)
class 4 = Mentioned at least 6-8 in the data. (freq = 8-6)
... and so on, according to the logaritmic function IntToFreqClass()

The value, LangTable contains the frecuency data separated by language. Check
the LangTable for details.
*/
var NGramStats [MaxDepth + 1][FreqClasses]LangTable

//
var langTagToIndex *trie.Node
var langIndexToTag [][]byte = [][]byte{nil}

/*
A trie container that contains all the n-gram frequency data. The frequency values
are saved in type LangTable slices found from Node.Value.(LangTable).
See the general usage of the Node object in the trie package.
*/
var Dict *trie.Node
var startTime time.Time

/*
Converts a raw appearing frequency to a logarithmically scaled frequency class value.
In other words: Calculates 1.59-based logarithm of the input frequency and rounds it to integer.
*/
func FreqToFreqClass(x int) FreqClass {
	return FreqClass((math.Log(float64(x)) / math.Log(1.59)) + 0.5)
}

/*
Converts a logarithmically scaled frequency class value to the smallest possible raw appearing frequency in that class.
Reverse function of FreqToFreqClass() (Not a true bijection, but FreqToFreqClass(FreqClassToFreq(class))) == class works.
*/
func FreqClassToMinFreq(x FreqClass) int {
	return int(math.Pow(1.59, (float64(x)-0.5)) + 1)
}

/*
Reads recursively the contents of a directory and returns a channel of
buffered readers of files in the directories.
*/
func getFileReaders(directory string) chan *bufio.Reader {
	readerChannel := make(chan *bufio.Reader)
	go func() {
		filepath.Walk(directory, func(path string, f os.FileInfo, err error) error {
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
			if DebugSome {
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

/*
Reads recursively a directory and returns a channel of bytes of all the
files contained in the directories.
*/
func streamBytes(directory string) chan byte {
	byteStream := make(chan byte)
	readerChannel := getFileReaders(directory)
	go func() {
		for reader := range readerChannel {
			for {
				b, err := reader.ReadByte()
				if err != nil {
					if DebugSome {
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

/*
Increments the language data in a LangTable, and grows the LangTable if
neccessary.
*/
func incrementLang(table *LangTable, index LangIndex) {
	if Assert {
		if table == nil {
			panic("ASSERT: table is nil!")
		}
		if int(index) > AmountOfLangs {
			panic("ASSERT: Index can't be greater than current amount of languages!")
		}
	}
	for len(*table) < AmountOfLangs+1 {
		*table = append(*table, 0)
		if DebugAll {
			fmt.Println("Table grew! To", len(*table))
		}
	}
	(*table)[index]++
}

/*
Updates the frequency data of a single n-gram (represented by trie.Node).
*/
func touchLangData(node *trie.Node, lang LangIndex) {
	if node.Value == nil {
		if DebugAll {
			fmt.Println("Initialising LangTable with", AmountOfLangs+1, "slots in node: '", string((*node).Prefix()), "'")
		}
		node.Value = make(LangTable, AmountOfLangs+1)
	}
	table := node.Value.(LangTable)
	incrementLang(&table, AllLangs)
	incrementLang(&table, lang)
	node.Value = table
	if DebugAll {
		fmt.Println("Incremented node '", string((*node).Prefix()), "' language", string(langIndexToTag[lang]), "to", table[lang], ". The langtable is now:", table)
	}
}

func printNGramStats() {

	for n := 0; n <= MaxDepth; n++ {
		for f := FreqClass(0); f < 47; f++ {
			if NGramStats[n][f][0] == 0 {
				continue
			}
			if n == AllGrams {
				fmt.Print("n-grams of all lengths ")
			} else {
				fmt.Print(n, "-grams ")
			}
			fmt.Println("mentioned at least", FreqClassToMinFreq(f), "times")
			for l := LangIndex(0); l < LangIndex(len(NGramStats[0][f])); l++ {
				if l == AllLangs {
					fmt.Println("All languages:", NGramStats[n][f][l])
				} else {
					fmt.Println("Language ", string(langIndexToTag[l]), ":", NGramStats[n][f][l])
				}
			}
		}
	}
}

/*
Prints statistics of the data.
*/
func printStats() {
	fmt.Printf("Took %.2f seconds.\n", float32(time.Since(startTime).Seconds()))
	fmt.Println("Max n-gram length:\t", MaxDepth)
	//	fmt.Printf("Size in memory:\t\t\t%d MiB, %d bytes per node.\n", trie.NodeCount()*int(unsafe.Sizeof(trie.Node{}))/(1024*1024), unsafe.Sizeof(trie.Node{}))
	fmt.Println("Learning data:")

	printNGramStats()
	/*
		for node := range Dict.WalkKeys() {
			if node.Value != nil {
				fmt.Printf("‘%s’ %v\n", string(node.Prefix()), node.Value)
			}
		}
	*/
}

/*
Reads the control sequence in the data, and sets a new language, if needed.
*/
func setLang(byteStream chan byte) (bool, LangIndex) {
	bNext := <-byteStream
	if bNext == '@' {
		return false, LangIndex(0)
	} else {
		langtag := LangTag{bNext, <-byteStream}
		node := langTagToIndex.GetOrCreate(langtag)
		if node.Value == nil {
			node.Value = LangIndex(AmountOfLangs + 1)
			langIndexToTag = append(langIndexToTag, langtag)
			AmountOfLangs++
			if DebugSome {
				fmt.Println("New language! ", string(langtag[:]), node.Value)
			}
		}
		langindex := node.Value.(LangIndex)
		if DebugSome {
			fmt.Println("Changed language to:", string(langtag[:]))
		}
		return true, langindex
	}
}

func builder(byteStream chan byte) {

	var bytesRead int

	// current language
	langindex := LangIndex(1)

	//"nodes is the ring buffer for trie nodes that represent n-grams
	nodes := make([]*trie.Node, 0, MaxDepth)

	// i is a ring buffer index that is set to point to the new 1-gram on each loop
	i := 0

	// Init the stats
	for n := range NGramStats {
		for f := range NGramStats[n] {
			NGramStats[n][f] = make(LangTable, AmountOfLangs+1)
		}
	}

	// The main loop
	for b := range byteStream {
		if DebugAll {
			fmt.Println("Reading new byte. It is '", string([]byte{b}), "' (i is", i, ")")
		}
		// Handle the control sequences for changing the language
		if b == '@' {
			changed, newLangIndex := setLang(byteStream)
			if changed {
				langindex = newLangIndex
				nodes = nodes[:0] // Clear the nodes buffer
				i = 0
				continue
			}
		}

		// NOTE. The exclusion of linefeeds is UTF-8 specific and a bit unneccessary!?
		if b == '\n' {
			nodes = nodes[:0] // Clear the nodes buffer
			i = 0
			continue
		}

		if DebugSome && KeepStats {
			bytesRead++
			if bytesRead%1000 == 0 {
				fmt.Println("Nodes:", NGramStats[AllGrams][0][AllLangs])
			}
		}

		if len(nodes) < MaxDepth { // if ringbuffer isn't full yet, append to it
			nodes = append(nodes, nil)
		}

		// i is the ringbuffer index that points to the 1-gram. (Here it's yet but a 0-gram / root.)
		nodes[i] = Dict

		// n is the "n" of n-grams
		n := 1

		for k := i + len(nodes); k > i; k-- { // k is used only to calculate j and to loop the right amount of times.
			// j is the ringbuffer index in this loop
			// j ranges from i to 0 and then from len(nodes)-1 to i+1.
			// IN OTHER WORDS: j loops downwards from i and wraps over.
			j := k % len(nodes)

			parent := nodes[j]
			child := parent.GetOrCreate([]byte{b})
			touchLangData(child, langindex)
			if KeepStats {
				if DebugAll {
					fmt.Println("incrementing NGramStats")
				}
				freq := child.Value.(LangTable)[AllLangs]
				if freq == FreqClassToMinFreq(FreqToFreqClass(freq)) { // Has reached a new frequency class!
					incrementLang(&(NGramStats[AllGrams][FreqToFreqClass(freq)]), AllLangs)
					incrementLang(&NGramStats[n][FreqToFreqClass(freq)], AllLangs)
				}

				freq = child.Value.(LangTable)[langindex]
				if freq == FreqClassToMinFreq(FreqToFreqClass(freq)) { // Has reached a new frequency class!
					incrementLang(&NGramStats[AllGrams][FreqToFreqClass(freq)], langindex)
					incrementLang(&NGramStats[n][FreqToFreqClass(freq)], langindex)
				}
				n++
			}
			nodes[j] = child
		}
		i++
		i = i % MaxDepth

	}
}

/*
Builds the database of n-grams. Takes a directory name dir as a parameter.
Scans the dir and its subdirectories and reads all the files there.
*/
func Build(directory string) {
	startTime = time.Now()
	byteStream := streamBytes(directory)
	Dict = trie.NewNode()           // "dict" is the trie containing all the n-grams
	langTagToIndex = trie.NewNode() // "langTagToIndex" is a trie that converts to langTags to langIndexes
	builder(byteStream)
	printStats()

}
