package trie

import (
	"fmt"
	"testing"
)

var flagtests = []struct {
	in  string
	out string
}{
	{"hajoo", "HAJOO"},
	{"haju", "HAJU"},
	{"aakkos", "AAKKOS"},
	{"aakkosia", "AAKKOSIA"},
	{"soppaako", "SOPPAAKO"},
}

func TestBasics(t *testing.T) {
	trie := NewNode()
	for _, test := range flagtests {
		trie.Add([]byte(test.in), test.out)
	}
	for _, test := range flagtests {
		output := trie.TryAndGet([]byte(test.in))
		if output == nil {
			fmt.Println("Output is nil and it definitely shouldn't be!")
			t.Fail()
		} else if output != test.out {
			fmt.Println("Should have returned " + test.out + " but returned " + output.(string))
			t.Fail()
		}
	}
}

func TestOneAdd(t *testing.T) {
	trie := NewNode()
	a := *trie
	trie.Add([]byte("a"), "TESTI")
	b := *trie
	if a == b {
		fmt.Println("Node should have changed!")
		t.Fail()
	}
}

func TestRetrieveZeroLength(t *testing.T) {
	trie := NewNode()
	trie.GetOrCreate([]byte{})
}
