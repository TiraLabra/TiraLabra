package viterbi

import (
	"fmt"
	"math"
	"testing"
)

func emit_probA(obs []byte, state int) float64 {
	switch obs[0] {
	case byte('a'):
		return []float64{0.33333, 0.16666}[state]
	case byte('b'):
		return []float64{0.16666, 0.33333}[state]
	}
	return 0
}

func TestA(t *testing.T) {
	fmt.Println("Test A")
	a := []byte("a")
	observations := [][]byte{a}
	states := 2
	start_prob := []float64{0.5, 0.5}
	trans_prob := [][]float64{
		[]float64{0.5, 0.5},
		[]float64{0.5, 0.5},
	}
	probs, paths := viterbi(observations, states, trans_prob, start_prob, emit_probA)

	expected := [][]float64{[]float64{0.16666, 0.08333}}

	for i := range expected {
		for s := range expected[i] {
			if math.Abs(probs[i][s]-expected[i][s]) > 0.00001 {
				t.Fail()
				fmt.Println("Probs[", i, "][", s, "] should be around", expected[i][s], "but was", probs[i][s])
			}
		}
	}
	fmt.Println(paths)

}

func emit_probB(obs []byte, state int) float64 {
	switch obs[0] {
	case byte('n'):
		return []float64{0.5, 0.1}[state]
	case byte('c'):
		return []float64{0.4, 0.3}[state]
	case byte('d'):
		return []float64{0.1, 0.6}[state]
	}
	return 0
}

func TestB(t *testing.T) {
	fmt.Println("Test B")
	normal := []byte("n")
	cold := []byte("c")
	dizzy := []byte("d")
	observations := [][]byte{normal, cold, dizzy}
	states := 2
	start_prob := []float64{0.6, 0.4}
	trans_prob := [][]float64{
		[]float64{0.7, 0.3},
		[]float64{0.4, 0.6},
	}
	probs, paths := viterbi(observations, states, trans_prob, start_prob, emit_probB)

	expected := [][]float64{
		[]float64{0.3, 0.04},
		[]float64{0.084, 0.027},
		[]float64{0.00588, 0.01512},
	}

	for i := range expected {
		for s := range expected[i] {
			if math.Abs(probs[i][s]-expected[i][s]) > 0.00001 {
				t.Fail()
				fmt.Println("Probs[", i, "][", s, "] should be around", expected[i][s], "but was", probs[i][s])
			}
		}
	}
	fmt.Println(observations)
	fmt.Println(probs)
	fmt.Println(paths)

}
