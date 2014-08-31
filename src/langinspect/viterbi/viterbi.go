package viterbi

import (
	"github.com/golddranks/TiraLabra/src/orderedmap/naivetrie"
)

type LangTable []int

func viterbi(obs [][]byte, states []int, trans_prob [][]float64, start_prob []float64, emit_prob *naivetrie.Node) ([][]float64, [][]int) {

	numStates := len(states)
	probs := [][]float64{make([]float64, numStates)}
	paths := [][]int{}

	for s := range states {
		var emit_p float64 = float64(emit_prob.TryAndGet(obs[0]).(LangTable)[s+1]) / float64(emit_prob.TryAndGet(obs[0]).(LangTable)[0])
		probs[0][s] = start_prob[s] * emit_p
	}

	for o := range obs[1:] {
		probs = append(probs, make([]float64, numStates))
		newpaths := make([][]int, len(states))
		for nowState := range states {
			var emit_p float64 = float64(emit_prob.TryAndGet(obs[o]).(LangTable)[nowState+1]) / float64(emit_prob.TryAndGet(obs[o]).(LangTable)[0])
			var max_prob float64
			var max_lastState int
			for lastState := range states {
				thisProb := probs[o-1][lastState] * trans_prob[lastState][nowState] * emit_p
				if max_prob < thisProb {
					max_prob = thisProb
					max_lastState = lastState
				}
			}
			probs[o][nowState] = max_prob
			newpaths[nowState] = append(paths[max_lastState], nowState)
		}
		paths = newpaths
	}

	return probs, paths

}
