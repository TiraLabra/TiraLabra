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

func printPath(input []byte, max_path []int) {
	for i, _ := range max_path {
		fmt.Printf("%c  ", input[i])
	}
	fmt.Println()
	for _, v := range max_path {
		fmt.Print(builder.LangIndexToTag(builder.LangIndex(v+1)), " ")
	}
	fmt.Println()
}

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
	/*	trans_prob := [][]float64{
		[]float64{0.98, 0.01, 0.01},
		[]float64{0.01, 0.98, 0.01},
		[]float64{0.01, 0.01, 0.98},
	}*/
	trans_prob := [][]float64{
		[]float64{0.96, 0.02, 0.02},
		[]float64{0.02, 0.96, 0.02},
		[]float64{0.02, 0.02, 0.96},
	}
	emit_prob := viterbi.GetEmitProbFunction(db)
	obs := slice(text)
	path := viterbi.Run(obs, states, trans_prob, start_prob, emit_prob)

	return path
}

func main() {
	input := make([]byte, 0)
	db := builder.Build("data")
	fmt.Println("Ready.")
	rd := bufio.NewReader(os.Stdin)
	for {
		input, _ = rd.ReadBytes('\n')
		input := input[:len(input)-1]
		if len(input) == 0 {
			continue
		}
		max_path := inspect(db, input)

		printPath(input, max_path)

	}
}
