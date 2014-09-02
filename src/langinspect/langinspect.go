/*
LangInspect is a software that uses a learning algorithm to recognise and
categorise natural languages. TODO: Details about the command line
interface.
*/
package main

import "github.com/golddranks/TiraLabra/src/langinspect/builder"
import "github.com/golddranks/TiraLabra/src/langinspect/viterbi"
import "github.com/golddranks/TiraLabra/src/orderedmap/naivetrie"
import "fmt"
import "bufio"
import "os"

func slice(text []byte) [][]byte {
	r := make([][]byte, 0, len(text))
	for i := 0; i < len(text); i++ {
		r = append(r, []byte{text[i]})
	}
	return r
}

func inspect(db *naivetrie.Node, text []byte) []int {
	states := builder.AmountOfLangs // TODO Ugly, ugly global value. Need to refactor.
	start_prob := []float64{0.33333, 0.33333, 0.33333}
	trans_prob := [][]float64{
		[]float64{0.8, 0.1, 0.1},
		[]float64{0.1, 0.8, 0.1},
		[]float64{0.1, 0.1, 0.8},
	}
	emit_prob := viterbi.GetEmitProbFunction(db)
	obs := slice(text)
	probs, paths := viterbi.Run(obs, states, trans_prob, start_prob, emit_prob)
	max_value := 0.0
	max_index := 0
	for i, v := range probs[len(probs)-1] {
		if v < max_value {
			max_value = v
			max_index = i
		}
	}
	return paths[max_index]
}

func main() {
	input := make([]byte, 0)
	db := builder.Build("data")
	rd := bufio.NewReader(os.Stdin)
	for {
		input, _ = rd.ReadBytes('\n')
		fmt.Println(input)
		max_path := inspect(db, input)
		for _, v := range max_path {
			fmt.Print(builder.LangIndexToTag(builder.LangIndex(v+1)), " ")
		}
		fmt.Println()

	}
}
