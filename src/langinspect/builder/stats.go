package builder

func initStats() {
	// Init the stats
	for n := range NGramStats {
		for f := range NGramStats[n] {
			NGramStats[n][f] = make(LangTable, AmountOfLangs+1)
		}
	}
}

func printNGramStats(stats *NGramStatsType, l LangIndex, n int) {
	for f := FreqClass(0); f < 47; f++ {
		if stats[n][f][0] == 0 {
			continue
		}
		/*
			if n == AllGrams {
				fmt.Print("n-grams of all lengths ")
			} else {
				fmt.Print(n, "-grams ")
			}
			fmt.Println("mentioned at least", FreqClassToMinFreq(f), "times")
		*/
		fmt.Print(stats[n][f][l], "\t")
		/*if l == AllLangs {
			fmt.Println("All languages:", stats[n][f][l])
		} else {
			fmt.Println("Language ", string(langIndexToTag[l]), ":", stats[n][f][l])
		}*/

	}
	fmt.Println()

}

/*
Prints statistics of the data.
*/
func printStats() {
	fmt.Printf("Took %.2f seconds.\n", float32(time.Since(startTime).Seconds()))
	fmt.Println("Max n-gram length:\t", MaxDepth)
	fmt.Printf("Size in memory:\t\t%d MiB, %d bytes per node, %d nodes.\n", trie.NodeCount()*int(unsafe.Sizeof(trie.Node{}))/(1024*1024), unsafe.Sizeof(trie.Node{}), trie.NodeCount())
	fmt.Println()
	lang := LangTagToIndex("en")
	for n := 0; n < MaxDepth+1; n++ {
		bytes := 1000
		fmt.Print("Stats for ", n, "-grams, ", LangIndexToTag(lang), ".\n")
		highestFreqClass := FreqClass(0)
		for f := FreqClass(0); f < 47; f++ {
			if NGramStats[n][f][AllLangs] == 0 {
				highestFreqClass = f
				break
			}
		}
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
		for i := range NGramStatsOverTime[lang] {
			fmt.Print(bytes, " bytes\t")
			bytes += 1000
			printNGramStats(&NGramStatsOverTime[lang][i], lang, n)
		}

		fmt.Println("\nFinal stats.\n")
		fmt.Print(bytesRead[0], " bytes\t")
		printNGramStats(&NGramStats, lang, n)
	}

}

func saveStats() {
	if KeepStats {
		incrementLang(&bytesRead, AllLangs)
		incrementLang(&bytesRead, langindex)
		if bytesRead[langindex]%1000 == 0 {
			NGramStatsOverTime[langindex] = append(NGramStatsOverTime[langindex], NGramStatsType{})
			for n := range NGramStats {
				for f := range NGramStats[n] {
					NGramStatsOverTime[langindex][len(NGramStatsOverTime[langindex])-1][n][f] = make(LangTable, len(NGramStats[n][f]))
					copy(NGramStatsOverTime[langindex][len(NGramStatsOverTime[langindex])-1][n][f], NGramStats[n][f])
				}
			}
		}
	}
}
