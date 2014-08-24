/*
Package darraytrie implements a trie, that is, a prefix tree.

Trie provides fast O(1 + K) lookup where K is the length of the key - similar
performance charasteristics with hash tables but with the ordered property.

This is a memory-efficient, double array algorithm -based implementation of a trie.
It doesn't use pointers but array indices - this helps to keep the garbage collection
down and avoids the 64-bit vs 32-bit overhead. (TO DO: It doesn't reserve the full space of
256 indices per node but relocates the node if it needs to grow.) It also has iterative
walking algorithms instead of recursive avoiding the function calling overhead.

This implementation doesn't contain arbitrary objects as values, but stores a single
32-bit integer. You can use that to store any value that fits into 32 bits, or if you
need more, wrap this trie and use the value as an index to some storage array.

The size of the alphabet can be specified when creating a new trie. The maximum size
is 256, using an octet per character.
*/
package darraytrie

import (
	"fmt"
)

const debug = false

/*
A container struct for the trie arrays.
*/
type Trie struct {
	base        []int
	check       []int
	value       []int
	transVector []int
}

/*
Iterator object wraps a reference to a node inside the trie. It can be thought as a
reference to a single key-value pair - but it can be also used as a parent node
to the nodes with the same prefix.
*/
type Iterator struct {
	trie    *Trie
	indices []int
}

/*
Returns a new Trie object
*/
func NewTrie(capacity int, alphabetSize int) Trie {
	t := Trie{}
	t.base = make([]int, 1, capacity)
	t.check = make([]int, 1, capacity)
	t.value = make([]int, 1, capacity)
	t.transVector = make([]int, alphabetSize) // This is just a template that gets appended to base when initing a new transition vector
	return t
}

/*
Adds a key-value pair to trie.
*/
func (t *Trie) Add(key []byte, value int) {
	iter := t.GetOrCreate(key)
	t.value[iter.index()] = value

	if debug {
		fmt.Println("Value set.")
		fmt.Println(t.value)
		fmt.Println("Add complete.\n")
	}
}

/*
Tries to retrieve a key-value pair (as an iterator) and returns null if such a pair
doesn't exist.
*/
func (t *Trie) TryAndGet(key []byte) *Iterator {
	now := 0
	next := 0
	iter := Iterator{trie: t, indices: make([]int, 1, len(key)+1)}
	for i := 0; i < len(key); i++ {
		next = t.base[now] + int(key[i])
		if t.check[next] == now {
			now = next
			iter.indices = append(iter.indices, now)
		} else {
			if debug {
				fmt.Println("Returning from TryAndGet - NO MATCH FOUND. Traversed indices:", iter.indices)
			}
			return nil
		}
	}
	if debug {
		fmt.Println("Returning from TryAndGet - MATCH FOUND. Traversed indices:", iter.indices, "Value:", t.value[iter.index()])
	}
	return &iter
}

/*
Returns a key-value pair as an iterator. If the pair doesn't exist, creates it.
*/
func (t *Trie) GetOrCreate(key []byte) *Iterator {
	now := 0
	next := 0
	iter := Iterator{trie: t, indices: make([]int, 1, len(key)+1)}
	if debug {
		fmt.Println("GetOrCreate - START")
	}
	for i := 0; i < len(key); i++ {
		if debug {
			fmt.Println("current key fragment", int(key[i]))
		}
		if t.base[now] == 0 {
			if debug {
				fmt.Println("extending. now:", now, "\n", t.base, "\n", t.check)
			}
			t.base[now] = len(t.base)
			t.base = append(t.base, t.transVector...)
			t.check = append(t.check, t.transVector...)
			t.value = append(t.value, t.transVector...)
			if debug {
				fmt.Println("extended:\n", t.base, "\n", t.check)
			}
		}
		next = t.base[now] + int(key[i])
		t.check[next] = now
		now = next

		iter.indices = append(iter.indices, now)
		if debug {
			fmt.Println("Round", i, "ends. Printing the state:")
			fmt.Println(t.base)
			fmt.Println(t.check)
			fmt.Println(t.value)
			fmt.Println(iter.indices)
		}

	}
	if debug {
		fmt.Println("GetOrCreate ends.\n")
	}
	return &iter
}

/*
Gets the value of a key-value pair.
*/
func (i *Iterator) Value() int {
	return i.trie.value[i.index()]
}

/*
Sets the value of a key-value pair.
*/
func (i *Iterator) SetValue(value int) {
	i.trie.value[i.index()] = value
}

/*
Internal shorthand for the index of the node the iterator references.
*/
func (i *Iterator) index() int {
	return i.indices[len(i.indices)-1]
}

// Get the key of given node.
func (i *Iterator) Prefix() []byte {
	return n.prefix(0)
}
