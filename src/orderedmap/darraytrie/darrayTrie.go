/*
Package darraytrie implements a trie, that is, a prefix tree.

Trie provides fast O(1 + K) lookup where K is the length of the key - similar
performance charasteristics with hash tables but with the ordered property.

This is a memory-efficient, double array algorithm -based implementation of a trie.
It doesn't use pointers but array indices - this helps to keep the garbage collection
down and avoids the 64-bit overhead. It doesn't reserve the full space of 256
indices per node but relocates the node if it needs to grow. It also has iterative
walking algorithms.
*/
package darraytrie

type Trie struct {
	root  int
	base  []int
	check []int
	value []int
}

type Iterator struct {
	trie    *Trie
	indices []int
}

func NewTrie(capacity int) Trie {
	t := Trie{}
	t.base = make([]int, 0, capacity)
	t.check = make([]int, 0, capacity)
	t.value = make([]int, 0, capacity)
	return t
}

func (t *Trie) Add(key []byte, value int) {

}

func (t *Trie) TryAndGet(key []byte) *Iterator {
	now := t.root
	iter := Iterator{trie: t, indices: make([]int, 0, len(key))}
	for i := 0; i < len(key); i++ {
		next := t.base[now] + int(key[i])
		if t.check[next] == now {
			now := next
			iter.indices = append(iter.indices, now)
		} else {
			return nil
		}
	}
	return &iter
}

func (t *Trie) GetOrCreate(key []byte) *Iterator {
	now := t.root
	iter := Iterator{trie: t, indices: make([]int, 0, len(key))}
	for i := 0; i < len(key); i++ {
		next := t.base[now] + int(key[i])
		if t.check[next] == now {
			now := next
			iter.indices = append(iter.indices, now)
		} else {
			return nil
		}
	}
	return &iter
}
