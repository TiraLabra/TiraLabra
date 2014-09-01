/*
LangInspect is a software that uses a learning algorithm to recognise and
categorise natural languages. TODO: Details about the command line
interface.
*/
package main

import "github.com/golddranks/TiraLabra/src/langinspect/builder"
import "github.com/golddranks/TiraLabra/src/langinspect/viterbi"
import "fmt"

func slice(text string) [][]byte {
	r := make([][]byte, 0, len(text))
	for i := 0; i < len(text); i++ {
		r = append(r, []byte{text[i]})
	}
	return r
}

func main() {
	db := builder.Build("data")
	states := 3
	start_prob := []float64{0.33333, 0.33333, 0.33333}
	trans_prob := [][]float64{
		[]float64{0.33333, 0.33333, 0.33333},
		[]float64{0.33333, 0.33333, 0.33333},
		[]float64{0.33333, 0.33333, 0.33333},
	}
	emit_prob := viterbi.GetEmitProbFunction(db)
	obs := slice("jooopa joo")
	probs, paths := viterbi.Run(obs, states, trans_prob, start_prob, emit_prob)
	fmt.Println(probs, paths)
}
