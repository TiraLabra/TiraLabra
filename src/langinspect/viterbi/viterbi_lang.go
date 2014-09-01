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
func GetEmitProb(dict *naivetrie.Node, stats *builder.Stats) func([]byte, int) float64 {

	emit_prob := func(obs []byte, lang int) float64 {
		langTab := dict.TryAndGet(obs).(LangTable)
		freq := langTab[lang+1]
		stats.Get
	}

	return emit_prob
}
