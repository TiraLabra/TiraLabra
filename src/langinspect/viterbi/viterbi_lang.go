package viterbi

import (
	"github.com/golddranks/TiraLabra/src/langinspect/builder"
	"github.com/golddranks/TiraLabra/src/orderedmap/naivetrie"
)

type LangTable []int

/*
GetEmitProb returns a function that reads database and returns the emit probability
of an observed string, given language. It is supposed to be passed as a callback
to the viterbi function.
*/
func GetEmitProb(dict *naivetrie.Node, stats *builder.Stats) func([]byte, builder.LangIndex) float64 {

	emit_prob := func(obs []byte, lang builder.LangIndex) float64 {
		langTab := dict.TryAndGet(obs).(LangTable)
		freq := langTab[lang+1]
		total := stats.GetTotalNgrams(lang)
		return float64(float64(freq) / float64(total))
	}

	return emit_prob
}
