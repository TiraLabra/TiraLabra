package orderedmap

import (
	"fmt"
	"github.com/golddranks/TiraLabra/src/orderedmap/darraytrie"
	"github.com/golddranks/TiraLabra/src/orderedmap/naivetrie"
	"testing"
)

var darray darraytrie.Trie
var naive naivetrie.Node

var flagtestsA = []struct {
	in  string
	out int
}{
	{"hajoo", 1},
	{"haju", 2},
	{"aakkos", 3},
	{"aakkosia", 4},
	{"soppaako", 5},
}

func TestBasicsDarray(t *testing.T) {
	darray := darraytrie.NewTrie(10000, 256)
	for _, test := range flagtestsA {
		darray.Add([]byte(test.in), test.out)
	}
	for _, test := range flagtestsA {
		iter := darray.TryAndGet([]byte(test.in))
		if iter.Value() == 0 {
			fmt.Println("Output is 0 and it definitely shouldn't be!")
			t.Fail()
		} else if iter.Value() != test.out {
			fmt.Println("Should have returned " + string(test.out) + " but returned " + string(iter.Value()))
			t.Fail()
		}
	}
	fmt.Println("TEST A for DARRAY TRIE PASSES.\n\n\n")
}

func TestBasicsNaive(t *testing.T) {
	naive := naivetrie.NewNode()
	for _, test := range flagtestsA {
		naive.Add([]byte(test.in), test.out)
	}
	for _, test := range flagtestsA {
		val := naive.TryAndGet([]byte(test.in)).(int)
		if val == 0 {
			fmt.Println("Output is 0 and it definitely shouldn't be!")
			t.Fail()
		} else if val != test.out {
			fmt.Println("Should have returned " + string(test.out) + " but returned " + string(val))
			t.Fail()
		}
	}
	fmt.Println("TEST A for NAIVE TRIE PASSES.\n\n\n")
}
