package builder

import (
	"fmt"
	"github.com/golddranks/TiraLabra/src/trie"
	"time"
	"unsafe"
)

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
type NGramStatsType [MaxDepth + 1][MaxFreqClasses]int

/*
Stats object keeps statistics about the data builder reads.
*/
type Stats struct {
	startTime  time.Time
	bytesRead  LangTable
	nGramStats [][]NGramStatsType
}

func initStats() *Stats {
	if ShowMessages {
		fmt.Println("Init stats!")
	}
	stats := &Stats{}
	stats.startTime = time.Now()
	stats.nGramStats = make([][]NGramStatsType, 0, AmountOfLangs+1)
	for lang := AllLangs; lang < LangIndex(AmountOfLangs+1); lang++ {
		stats.AddLang()
	}
	return stats
}

func (s *Stats) AddLang() {
	if Assert {
		if s == nil {
			panic("stats object is nil!")
		}
	}
	if ShowMessages {
		fmt.Println("Adding language to stats!")
	}
	s.nGramStats = append(s.nGramStats, make([]NGramStatsType, 1, 10))
}

func (s NGramStatsType) printNGram(n int) {
	for f := FreqClass(0); f < 47; f++ {
		if s[n][f] == 0 {
			break
		}

		fmt.Print(s[n][f], "\t")

	}
	fmt.Println()

}

/*
Prints statistics of the data.
*/
func (s *Stats) print() {
	fmt.Printf("Took %.2f seconds.\n", float32(time.Since(s.startTime).Seconds()))
	fmt.Println("Max n-gram length:\t", MaxDepth)
	fmt.Printf("Size in memory:\t\t%d MiB, %d bytes per node, %d nodes.\n", trie.NodeCount()*int(unsafe.Sizeof(trie.Node{}))/(1024*1024), unsafe.Sizeof(trie.Node{}), trie.NodeCount())
	fmt.Println()
	lang := LangTagToIndex("en")

	// n is the n of n-gram; print stats for each n-gram length
	for n := 0; n <= MaxDepth; n++ {
		fmt.Print("Stats for ", n, "-grams, ", LangIndexToTag(lang), ".\n")

		// Search for highest freq class to print
		highestFreqClass := FreqClass(0)
		for f := FreqClass(0); f < 47; f++ {
			if s.nGramStats[len(s.nGramStats)-1][AllLangs][n][f] == 0 {
				highestFreqClass = f
				break
			}
		}

		// Print the header
		fmt.Print("Freq at least")
		for f := FreqClass(0); f < highestFreqClass; f++ {
			freq := FreqClassToMinFreq(f)
			if freq < 100000 {
				fmt.Print("\t", freq)
			} else {
				fmt.Printf("\t%fk", float64(freq)/1000)
			}
		}
		fmt.Println()

		// Print the the amount of n-grams
		bytes := 1000
		for _, ngram := range s.nGramStats[lang] {
			fmt.Print(bytes, " bytes\t")
			bytes += 1000
			ngram.printNGram(n)
		}
	}

}

func (s *Stats) saveByteStats(currentLang LangIndex) {
	if ShowDebug {
		fmt.Println("saving byte stats")
	}
	// Increment bytes read
	s.bytesRead.IncrementLang(AllLangs)
	s.bytesRead.IncrementLang(currentLang)
	if s.bytesRead[currentLang]%1000 == 0 {

		// Make a new snapshot of the stats every 1000th byte
		s.nGramStats[currentLang] = append(s.nGramStats[currentLang], s.nGramStats[currentLang][len(s.nGramStats[currentLang])-1])
	}

}

func (s *Stats) saveNodeStats(node *trie.Node, currentLang LangIndex, n int) {
	if ShowDebug {
		fmt.Println("incrementing NGramStats")
	}
	freq := node.Value.(LangTable)[AllLangs]
	if freq == FreqClassToMinFreq(FreqToFreqClass(freq)) { // Has reached a new frequency class!
		s.nGramStats[AllLangs][len(s.nGramStats[AllLangs])-1][AllGrams][FreqToFreqClass(freq)]++
		s.nGramStats[AllLangs][len(s.nGramStats[AllLangs])-1][n][FreqToFreqClass(freq)]++
	}

	freq = node.Value.(LangTable)[currentLang]
	if freq == FreqClassToMinFreq(FreqToFreqClass(freq)) { // Has reached a new frequency class!
		s.nGramStats[currentLang][len(s.nGramStats[currentLang])-1][AllGrams][FreqToFreqClass(freq)]++
		s.nGramStats[currentLang][len(s.nGramStats[currentLang])-1][n][FreqToFreqClass(freq)]++
	}
	n++
}
