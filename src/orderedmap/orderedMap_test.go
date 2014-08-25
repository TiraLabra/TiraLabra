package orderedmap

import (
	"fmt"
	"github.com/golddranks/TiraLabra/src/orderedmap/darraytrie"
	"github.com/golddranks/TiraLabra/src/orderedmap/naivetrie"
	"math/rand"
	"testing"
)

var darray darraytrie.Trie
var naive naivetrie.Node

type perftestType struct {
	in  []byte
	out int
}

var perftest = []perftestType{}

func randoms() perftestType {
	b := make([]byte, 9)
	for i := 0; i < 9; i++ {
		b[i] = byte(rand.Intn(256))
	}
	c := rand.Int()
	return perftestType{b, c}
}

func prepareInput(n int) {
	for i := 0; i < n; i++ {
		perftest = append(perftest, randoms())
	}
}

func BenchmarkBasicsDarray(t *testing.B) {
	darray := darraytrie.NewTrie(10000, 256)
	prepareInput(1000)
	t.ResetTimer()
	for i := 0; i < t.N; i++ {
		for _, test := range perftest {
			darray.Add(test.in, test.out)
		}
		for _, test := range perftest {
			iter := darray.TryAndGet(test.in)
			if iter.Value() == 0 {
				fmt.Println("Output is 0 and it definitely shouldn't be!")
				t.Fail()
			} else if iter.Value() != test.out {
				fmt.Println("Should have returned " + string(test.out) + " but returned " + string(iter.Value()))
				t.Fail()
			}
		}
	}
	fmt.Println("TEST A for DARRAY TRIE PASSES.\n\n\n")
}

func BenchmarkBasicsNaive(t *testing.B) {
	naive := naivetrie.NewNode()
	t.ResetTimer()
	for i := 0; i < t.N; i++ {
		for _, test := range perftest {
			naive.Add(test.in, test.out)
		}
		for _, test := range perftest {
			val := naive.TryAndGet(test.in).(int)
			if val == 0 {
				fmt.Println("Output is 0 and it definitely shouldn't be!")
				t.Fail()
			} else if val != test.out {
				fmt.Println("Should have returned " + string(test.out) + " but returned " + string(val))
				t.Fail()
			}
		}
	}
	fmt.Println("TEST A for NAIVE TRIE PASSES.\n\n\n")
}
