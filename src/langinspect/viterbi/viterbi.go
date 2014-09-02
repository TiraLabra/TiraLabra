package viterbi

import (
	"fmt"
	"math"
)

func Run(obs [][]byte, states int, trans_prob [][]float64, start_prob []float64, emit_prob func([]byte, int) float64) []int {
	for i := 0; i < len(trans_prob); i++ {
		for j := 0; j < len(trans_prob[i]); j++ {
			trans_prob[i][j] = math.Log2(trans_prob[i][j])
		}
	}
	for i := 0; i < len(start_prob); i++ {
		start_prob[i] = math.Log2(start_prob[i])
	}
	emit_logProb := func(obs []byte, state int) float64 {
		return math.Log2(emit_prob(obs, state))
	}
	probs, paths := logarithmic(obs, states, trans_prob, start_prob, emit_logProb)
	max_value := math.Inf(-1)
	max_index := -1
	for i, v := range probs[len(probs)-1] {
		if max_value < v {
			max_value = v
			max_index = i
		}
	}
	return paths[max_index]
}

func logarithmic(obs [][]byte, states int, trans_prob [][]float64, start_prob []float64, emit_prob func([]byte, int) float64) ([][]float64, [][]int) {

	probs := [][]float64{make([]float64, states)}
	paths := make([][]int, states)

	// Initializing with the start probabilities
	for s := 0; s < states; s++ {
		var emit_p float64 = emit_prob(obs[0], s)
		var start_p = start_prob[s]
		probs[0][s] = start_p + emit_p
		paths[s] = []int{s}
	}

	for o := 1; o < len(obs); o++ {
		probs = append(probs, make([]float64, states))
		newpaths := make([][]int, states)
		for nowState := 0; nowState < states; nowState++ {
			var nowStateEmitProb float64 = emit_prob(obs[o], nowState)
			var max_prob float64 = math.Inf(-1)
			var max_lastState int
			for lastState := 0; lastState < states; lastState++ { // Searching for the most probable state from where the process entered the current state
				lastStateProb := probs[o-1][lastState]
				transitionProb := trans_prob[lastState][nowState]
				thisProb := lastStateProb + transitionProb + nowStateEmitProb
				if max_prob < thisProb {
					max_prob = thisProb
					max_lastState = lastState
				}
			}
			probs[o][nowState] = max_prob
			newpaths[nowState] = make([]int, len(paths[max_lastState]))
			copy(newpaths[nowState], paths[max_lastState])
			newpaths[nowState] = append(newpaths[nowState], nowState)
		}
		paths = newpaths // Overwriting older paths that converge to the current ones
	}

	return probs, paths

}
