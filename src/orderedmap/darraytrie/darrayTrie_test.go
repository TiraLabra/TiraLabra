package darraytrie

import (
	"fmt"
	"testing"
)

var flagtestsQ = []struct {
	in  []byte
	out int
}{
	{[]byte{1}, 666},
}

// Testing with a minimal trie and 2-value alphabet
func TestBasicsQ(t *testing.T) {
	trie := NewTrie(100, 2)
	for _, test := range flagtestsQ {
		trie.Add(test.in, test.out)
	}
	for _, test := range flagtestsQ {
		iter := trie.TryAndGet(test.in)
		if iter.Value() == 0 {
			fmt.Println("Output is 0 and it definitely shouldn't be!")
			t.Fail()
		} else if iter.Value() != test.out {
			fmt.Println("Should have returned " + string(test.out) + " but returned " + string(iter.Value()))
			t.Fail()
		}
	}
	fmt.Println("TEST BASIC Q PASSES.\n\n\n")
}

var flagtestsA = []struct {
	in  []byte
	out int
}{
	{[]byte{3, 3, 3}, 1},
}

// Testing with a minimal trie and 4-value alphabet
func TestBasicsA(t *testing.T) {
	trie := NewTrie(100, 4)
	for _, test := range flagtestsA {
		trie.Add(test.in, test.out)
	}
	for _, test := range flagtestsA {
		iter := trie.TryAndGet(test.in)
		if iter.Value() == 0 {
			fmt.Println("Output is 0 and it definitely shouldn't be!")
			t.Fail()
		} else if iter.Value() != test.out {
			fmt.Println("Should have returned " + string(test.out) + " but returned " + string(iter.Value()))
			t.Fail()
		}
	}
	fmt.Println("TEST BASIC A PASSES.\n\n\n")
}

var flagtestsB = []struct {
	in  []byte
	out int
}{
	{[]byte{3, 3, 3}, 11},
	{[]byte{2, 2, 2}, 99},
}

// Testing with a two-branch trie and 4-value alphabet
func TestBasicsB(t *testing.T) {
	trie := NewTrie(100, 4)
	for _, test := range flagtestsB {
		trie.Add(test.in, test.out)
	}
	for _, test := range flagtestsB {
		iter := trie.TryAndGet(test.in)
		if iter.Value() == 0 {
			fmt.Println("Output is 0 and it definitely shouldn't be!")
			t.Fail()
		} else if iter.Value() != test.out {
			fmt.Println("Should have returned " + string(test.out) + " but returned " + string(iter.Value()))
			t.Fail()
		}
	}
	fmt.Println("TEST BASIC B PASSES.\n\n\n")
}

var flagtestsC = []struct {
	in  []byte
	out int
}{
	{[]byte{0, 0}, 111},
	{[]byte{1, 1}, 222},
	{[]byte{2, 2}, 333},
	{[]byte{0, 1}, 444},
	{[]byte{1, 2}, 555},
	{[]byte{2, 0}, 666},
	{[]byte{2, 1}, 777},
	{[]byte{1, 0}, 888},
	{[]byte{0, 2}, 999},
	{[]byte{2}, 999999},
	{[]byte{1}, 111111},
	{[]byte{0}, 555555},
}

// Testing with a small but wide trie and 3-value alphabet
func TestBasicsC(t *testing.T) {
	trie := NewTrie(100, 3)
	for _, test := range flagtestsC {
		trie.Add(test.in, test.out)
	}
	for _, test := range flagtestsC {
		iter := trie.TryAndGet(test.in)
		if iter.Value() == 0 {
			fmt.Println("Output is 0 and it definitely shouldn't be!")
			t.Fail()
		} else if iter.Value() != test.out {
			fmt.Println("Should have returned " + string(test.out) + " but returned " + string(iter.Value()))
			t.Fail()
		}
	}
	fmt.Println("TEST BASIC C PASSES.\n\n\n")
}

var flagtestsD = []struct {
	in  string
	out int
}{
	{"hajoo", 1},
	{"haju", 2},
	{"aakkos", 3},
	{"aakkosia", 4},
	{"soppaako", 5},
}

func TestBasicsD(t *testing.T) {
	trie := NewTrie(10000, 256)
	for _, test := range flagtestsD {
		trie.Add([]byte(test.in), test.out)
	}
	for _, test := range flagtestsD {
		iter := trie.TryAndGet([]byte(test.in))
		if iter.Value() == 0 {
			fmt.Println("Output is 0 and it definitely shouldn't be!")
			t.Fail()
		} else if iter.Value() != test.out {
			fmt.Println("Should have returned " + string(test.out) + " but returned " + string(iter.Value()))
			t.Fail()
		}
	}
	fmt.Println("TEST BASIC D PASSES.\n\n\n")
}

/*
func TestOneAdd(t *testing.T) {
	trie := NewTrie(10000)
	a := trie.
	trie.Add([]byte("a"), 33)
	b := *trie
	if a == b {
		fmt.Println("Node should have changed!")
		t.Fail()
	}
}
*/
func TestRetrieveZeroLength(t *testing.T) {
	trie := NewTrie(10000, 256)
	iter := trie.GetOrCreate([]byte{})
	// Hm, doesn't crash or anything? Cool.
	iter.SetValue(6969)
	if trie.TryAndGet([]byte{}).Value() != 6969 {
		t.Fail()
	}
	trie.Add([]byte{}, 777)
	if trie.TryAndGet([]byte{}).Value() != 777 {
		t.Fail()
	}
}
