package main

import "github.com/golddranks/TiraLabra/src/langinspect/builder"
import "github.com/golddranks/TiraLabra/src/langinspect/viterbi"
import "github.com/golddranks/TiraLabra/src/orderedmap/naivetrie"
import "testing"
import "fmt"

func printLangTable(db *naivetrie.Node, ngram string) {
	lTable := db.TryAndGet([]byte(ngram)).(builder.LangTable)
	lTable2 := db.Value.(builder.LangTable)
	for i, l := range lTable {
		fmt.Println(ngram, "langtable: ", i, builder.LangIndexToTag(builder.LangIndex(i)), l, float32(int(10000*float32(l)/float32(lTable2[i])))/100)
	}
	fmt.Println()
}

func TestInspect(t *testing.T) {
	text := []byte("joopa joo")
	db := builder.Build("testdata")
	states := builder.AmountOfLangs // TODO Ugly, ugly global value. Need to refactor.
	start_prob := []float64{0.33333, 0.33333, 0.33333}
	trans_prob := [][]float64{
		[]float64{0.8, 0.1, 0.1},
		[]float64{0.1, 0.8, 0.1},
		[]float64{0.1, 0.1, 0.8},
	}
	emit_prob := viterbi.GetEmitProbFunction(db)
	obs := slice(text)
	path := viterbi.Run(obs, states, trans_prob, start_prob, emit_prob)
	fmt.Println(path)

	printLangTable(db, "j")
	printLangTable(db, "o")
	printLangTable(db, "p")
	printLangTable(db, "a")
	printLangTable(db, " ")
}
