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
	{"aakkos", "AAKKOS"},
	{"aakkosia", "AAKKOSIA"},
	{"haju", "HAJU"},
	{"soppaako", "SOPPAAKO"},
}

func TestBasics(t *testing.T) {
	trie := NewNode()
	for _, test := range flagtests {
		trie.Add([]byte(test.in), test.out)
	}
	for _, test := range flagtests {
		output := trie.TryAndGet([]byte(test.in))
		if output != test.out {
			fmt.Println("Should have returned " + test.out + " but returned " + output.(string))
			t.Fail()
		}
	}
}
