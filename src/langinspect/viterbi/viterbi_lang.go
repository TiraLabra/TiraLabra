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
func GetEmitProbFunction(dict *naivetrie.Node) func([]byte, int) float64 {

	emit_prob := func(obs []byte, stateNumber int) float64 {
		value := dict.TryAndGet(obs)
		if value == nil {
			return 0
		}
		langTab := value.(builder.LangTable)
		if len(langTab) <= stateNumber+1 {
			return 0
		}
		freq := langTab[builder.LangIndex(stateNumber+1)]
		total := dict.Value.(builder.LangTable)[builder.LangIndex(stateNumber+1)]
		return float64(float64(freq) / float64(total))
	}

	return emit_prob
}
