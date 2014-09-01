package viterbi

func viterbi(obs [][]byte, states int, trans_prob [][]float64, start_prob []float64, emit_prob func([]byte, int) float64) ([][]float64, [][]int) {

	probs := [][]float64{make([]float64, states)}
	paths := make([][]int, states)

	for s := 0; s < states; s++ {
		var emit_p float64 = emit_prob(obs[0], s)
		probs[0][s] = start_prob[s] * emit_p
		paths[s] = []int{s}
	}

	for o := 1; o < len(obs); o++ {
		probs = append(probs, make([]float64, states))
		newpaths := make([][]int, states)
		for nowState := 0; nowState < states; nowState++ {
			var nowStateEmitProb float64 = emit_prob(obs[o], nowState)
			var max_prob float64
			var max_lastState int
			for lastState := 0; lastState < states; lastState++ {
				lastStateProb := probs[o-1][lastState]
				transitionProb := trans_prob[lastState][nowState]
				thisProb := lastStateProb * transitionProb * nowStateEmitProb
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
