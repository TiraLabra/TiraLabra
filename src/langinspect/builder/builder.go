//Package builder builds a database of n-grams (byte sequences of size n) that show up in the learning data.
package builder

import (
	"fmt"
	"github.com/golddranks/TiraLabra/src/orderedmap/naivetrie"
	"math"
)

// Prints annoingly lot of debugging data
const ShowDebug = false

// Prints some debugging data
const ShowMessages = true

// Keeps stats of the n-gram frequencies
const KeepStats = true

// Does some additional runtime checks
const Assert = true

// Maximum length of n-grams in the database. The size is in bytes.
//The value 9 is recommended, as it is enough to record three consecutive Japanese/Chinese/Korean characters in UTF-8.
const MaxDepth = 9

/// LANGUAGES ///

// Represents a (usually) two-byte marker that specifies a language.
// The language tags should be in UTF-8 and two-character ISO-639 language codes.
type LangTag []byte

/*
Index type for the LangTable type.
*/
type LangIndex int

/*
A named index value for type LangTable. Represents the frequency of the containing
n-gram disregarding the language.
*/
const AllLangs = LangIndex(0)

/*
Amount of languages in the data. Builder can increment the amount automatically,
but it's better to set up on compile-time.
*/
var AmountOfLangs = 0

var langTagToIndex *naivetrie.Node
var langIndexToTag [][]byte = [][]byte{nil}

func LangTagToIndex(tag string) LangIndex {
	if tag == "" {
		return AllLangs
	} else {
		val := langTagToIndex.TryAndGet([]byte(tag))
		if val != nil {
			return val.(LangIndex)
		} else {
			return 0
		}
	}
}

func LangIndexToTag(lang LangIndex) string {
	return string(langIndexToTag[lang])
}

/*
A container for the n-gram frequency data of a single n-gram (represented by
a single trie.Node). The index is of type LangIndex. Index AllLangs (= 0)
represents the general frequency of the n-gram disregarding the language.
*/
type LangTable []int

/*
Increments the language data in a LangTable, and grows the LangTable if
neccessary.
*/
func (table *LangTable) IncrementLang(index LangIndex) {
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
		if ShowDebug {
			fmt.Println("Table grew! To", len(*table))
		}
	}
	(*table)[index]++
}

/// FREQ CLASSES ///

/*
In the statistics data, the frequencies of the n-grams are divided into
frequency classes represented by FreqClass type. The raw frequency value can
be converted to a frequency class using IntToFreqClass() function.
*/
type FreqClass int

// Amount of frequency classes in the statistics (NGramStats). The value 47 represents
// frequency values around the upper bound of 32-bit integer.
// So it's not gonna overflow too easily.
const MaxFreqClasses = FreqClass(47)

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
A named index value for variable NGramStats first index. Represents the statistics of
all the n-grams regardless of their length.
*/
const AllGrams = int(0)

/*
A trie container that contains all the n-gram frequency data. The frequency values
are saved in type LangTable slices found from Node.Value.(LangTable).
See the general usage of the Node object in the trie package.
*/
var Dict *naivetrie.Node

/*
Updates the frequency data of a single n-gram (represented by naivetrie.Node).
*/
func touchLangData(node *naivetrie.Node, lang LangIndex) {
	if node.Value == nil {
		if ShowDebug {
			fmt.Println("Initialising8 LangTable with", AmountOfLangs+1, "slots in node: '", string((*node).Prefix()), "'")
		}
		node.Value = make(LangTable, AmountOfLangs+1)
	}
	table := node.Value.(LangTable)
	table.IncrementLang(AllLangs)
	table.IncrementLang(lang)
	node.Value = table
	if ShowDebug {
		fmt.Println("Incremented node '", string((*node).Prefix()), "' language", string(langIndexToTag[lang]), "to", table[lang], ". The langtable is now:", table)
	}
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
			stats.AddLang()
			if ShowMessages {
				fmt.Println("New language! ", string(langtag[:]), node.Value)
			}
		}
		langindex := node.Value.(LangIndex)
		if ShowMessages {
			fmt.Println("Changed language to:", string(langtag[:]))
		}
		return true, langindex
	}
}

var stats *Stats

func builder(byteStream chan byte) {

	// current language
	currentLang := LangIndex(1)

	//nodes is a ring buffer for trie nodes that represent n-grams
	nodes := NewRingBuffer(MaxDepth)

	// The main loop, read byte by byte
	for b := range byteStream {
		if ShowDebug {
			fmt.Println("Reading new byte. It is '", string([]byte{b}))
		}
		// Handle the control sequences for changing the language
		if b == '@' {
			changed, newLang := setLang(byteStream)
			if changed {
				currentLang = newLang
				nodes.Clear()
				continue
			}
		}

		// Newlines break words "universally" so we don't have to include them in n-grams
		if b == '\n' {
			nodes.Clear()
			continue
		}

		stats.saveByteStats(currentLang)

		// Adds a fresh node (0-gram, i.e. the rootnode) to the ringbuffer.
		// Oldest one (the 9-gram) will be thrown away if the buffer is full.
		nodes.Add(Dict)

		iter := nodes.IterFromNewest()
		n := 1
		for iter.Next() {
			node := iter.GetValue()
			child := node.GetOrCreate([]byte{b})
			touchLangData(child, currentLang)
			stats.saveNodeStats(child, currentLang, n)
			n++
			iter.SetValue(child)
		}
	}
}

/*
Builds the database of n-grams. Takes a directory name dir as a parameter.
Scans the dir and its subdirectories and reads all the files there.
*/
func Build(directory string) {
	stats = initStats()
	byteStream := streamBytes(directory)
	Dict = naivetrie.NewNode()           // "dict" is the trie containing all the n-grams
	langTagToIndex = naivetrie.NewNode() // "langTagToIndex" converts to langTags to langIndexes
	builder(byteStream)
	stats.print()

}
