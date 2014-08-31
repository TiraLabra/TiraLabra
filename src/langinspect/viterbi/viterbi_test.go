package viterbi

import (
	"fmt"
	"github.com/golddranks/TiraLabra/src/orderedmap/naivetrie"
	"math"
	"testing"
)

func TestA(t *testing.T) {
	fmt.Println("Test A")
	a := []byte("a")
	observations := [][]byte{a}
	states := []int{0, 1}
	start_prob := []float64{0.5, 0.5}
	trans_prob := [][]float64{[]float64{0.5, 0.5}, []float64{0.5, 0.5}}
	dict := naivetrie.NewNode()
	dict.Add([]byte("a"), LangTable{30, 10, 20})
	dict.Add([]byte("b"), LangTable{30, 20, 10})
	result := viterbi(observations, states, trans_prob, start_prob, dict)

	expected := [][]float64{[]float64{0.16666, 0.33333}}

	for i := range expected {
		for s := range expected[i] {
			if math.Abs(result[i][s]-expected[i][s]) > 0.00001 {
				t.Fail()
				fmt.Println("Result[", i, "][", s, "] should be around", expected[i][s], "but was", result[i][s])
			}
		}
	}

}

func TestB(t *testing.T) {
	fmt.Println("Test B")
	a := []byte("a")
	b := []byte("b")
	observations := [][]byte{a, b, b, a, a, b, a, b, b, b}
	states := []int{0, 1}
	start_prob := []float64{0.5, 0.5}
	trans_prob := [][]float64{[]float64{0.5, 0.5}, []float64{0.5, 0.5}}
	dict := naivetrie.NewNode()
	dict.Add([]byte("a"), LangTable{30, 10, 20})
	dict.Add([]byte("b"), LangTable{30, 20, 10})
	result := viterbi(observations, states, trans_prob, start_prob, dict)

	expected := [][]float64{[]float64{0.16666, 0.33333}}

	for i := range expected {
		for s := range expected[i] {
			if math.Abs(result[i][s]-expected[i][s]) > 0.00001 {
				t.Fail()
				fmt.Println("Result[", i, "][", s, "] should be around", expected[i][s], "but was", result[i][s])
			}
		}
	}

}
