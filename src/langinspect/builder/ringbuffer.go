package builder

import (
	"github.com/golddranks/TiraLabra/src/orderedmap/naivetrie"
)

type ringBuffer struct {
	buff  []*trie.Node
	index int
}

type ringIterator struct {
	buff  []*trie.Node
	index int
	orig  int
}

func NewRingBuffer(length int) ringBuffer {
	r := ringBuffer{}
	r.buff = make([]*trie.Node, 0, length)
	r.index = 0
	return r
}

func (r *ringBuffer) Clear() {
	r.buff = r.buff[:0]
	r.index = 0
}

// Add a new node to the buffer. If the buffer is full, replace the oldest node with the new. Return replaced one, or nil if nothing is replaced.
func (r *ringBuffer) Add(node *trie.Node) *trie.Node {

	if len(r.buff) < cap(r.buff) { // if ringbuffer isn't full yet, append to it
		r.buff = append(r.buff, nil)
	}

	r.index++
	r.index = r.index % len(r.buff)

	oldest := r.buff[r.index]

	// r.index is the ringbuffer index that points to the 1-gram. (Here it's yet but a 0-gram / root.)
	r.buff[r.index] = node
	return oldest
}

func (r *ringBuffer) IterFromNewest() ringIterator {
	i := ringIterator{}
	i.buff = r.buff
	i.orig = r.index
	i.index = r.index + len(r.buff) + 1
	return i
}

func (i *ringIterator) Next() bool {
	i.index--
	if i.index > i.orig {
		return true
	}
	return false
}

func (r *ringIterator) GetValue() *trie.Node {
	if r.index >= len(r.buff) {
		return r.buff[r.index-len(r.buff)]
	} else {
		return r.buff[r.index]
	}
}

func (r *ringIterator) SetValue(node *trie.Node) {
	if r.index >= len(r.buff) {
		r.buff[r.index-len(r.buff)] = node
	} else {
		r.buff[r.index] = node
	}
}
