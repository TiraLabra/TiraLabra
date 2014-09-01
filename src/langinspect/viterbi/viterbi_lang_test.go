package viterbi

import (
	"fmt"
	"github.com/golddranks/TiraLabra/src/langinspect/builder"
	"github.com/golddranks/TiraLabra/src/orderedmap/naivetrie"
	"math"
	"testing"
)

func TestEmitterOneLangOneWord(t *testing.T) {
	fmt.Println("Testing n-gram emit probability function")
	a := []byte("a")
	states := 1
	dict := naivetrie.NewNode()
	dict.Add(a, builder.LangTable{10, 10})

	builder.AmountOfLangs = states // TODO This is a REALLY ugly global value. Need to do something about this.
	dict.Value = builder.LangTable{10, 10}
	emit_prob := GetEmitProbFunction(dict)

	if math.Abs(emit_prob(a, 0)-1) > 0.00001 {
		t.Fail()
	}
}

func TestEmitterOneLangTwoWords(t *testing.T) {
	fmt.Println("Testing n-gram emit probability function")
	a := []byte("a")
	b := []byte("b")
	states := 1
	dict := naivetrie.NewNode()
	dict.Add(a, builder.LangTable{10, 10})
	dict.Add(b, builder.LangTable{20, 20})

	builder.AmountOfLangs = states // TODO This is a REALLY ugly global value. Need to do something about this.
	dict.Value = builder.LangTable{30, 30}
	emit_prob := GetEmitProbFunction(dict)

	if math.Abs(emit_prob(a, 0)-0.333333) > 0.00001 {
		t.Fail()
	}
	if math.Abs(emit_prob(b, 0)-0.666666) > 0.00001 {
		t.Fail()
	}
}

func TestEmitterTwoLangs(t *testing.T) {
	fmt.Println("Testing n-gram emit probability function")
	a := []byte("a")
	b := []byte("b")
	c := []byte("c")
	states := 2
	dict := naivetrie.NewNode()
	dict.Add(a, builder.LangTable{30, 10, 20})
	dict.Add(b, builder.LangTable{30, 20, 10})
	dict.Add(c, builder.LangTable{30, 15, 15})

	builder.AmountOfLangs = states // TODO This is a REALLY ugly global value. Need to do something about this.
	dict.Value = builder.LangTable{90, 45, 45}
	emit_prob := GetEmitProbFunction(dict)

	if math.Abs(emit_prob(a, 0)-0.222222) > 0.00001 {
		t.Fail()
	}
	if math.Abs(emit_prob(b, 0)-0.444444) > 0.00001 {
		t.Fail()
	}
	if math.Abs(emit_prob(c, 0)-0.333333) > 0.00001 {
		t.Fail()
	}
	if math.Abs(emit_prob(a, 1)-0.444444) > 0.00001 {
		t.Fail()
	}
	if math.Abs(emit_prob(b, 1)-0.222222) > 0.00001 {
		t.Fail()
	}
	if math.Abs(emit_prob(c, 1)-0.333333) > 0.00001 {
		t.Fail()
	}
}

func TestEmitterForReal(t *testing.T) {
	fmt.Println("Testing n-gram emit probability function")
	a := []byte("a")
	b := []byte("b")
	c := []byte("c")
	states := 2
	dict := naivetrie.NewNode()
	dict.Add(a, builder.LangTable{30, 10, 20})
	dict.Add(b, builder.LangTable{30, 20, 10})
	dict.Add(c, builder.LangTable{30, 15, 15})

	builder.AmountOfLangs = states // TODO This is a REALLY ugly global value. Need to do something about this.
	dict.Value = builder.LangTable{90, 45, 45}
	emit_prob := GetEmitProbFunction(dict)

	observations := [][]byte{a}
	start_prob := []float64{0.5, 0.5}
	trans_prob := [][]float64{
		[]float64{0.5, 0.5},
		[]float64{0.5, 0.5},
	}

	probs, _ := Run(observations, states, trans_prob, start_prob, emit_prob)

	expected := [][]float64{[]float64{0.111111, 0.222222}}

	for i := range expected {
		for s := range expected[i] {
			if math.Abs(probs[i][s]-expected[i][s]) > 0.00001 {
				t.Fail()
				fmt.Println("Probs[", i, "][", s, "] should be around", expected[i][s], "but was", probs[i][s])
			}
		}
	}

}
