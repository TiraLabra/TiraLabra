package builder

import (
	"github.com/golddranks/TiraLabra/src/trie"
)

type ringBuffer struct {
	buff  []*trie.Node
	index int
}

type ringIterator ringBuffer

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

func (r *ringBuffer) Add(node *trie.Node) {

	r.index++
	r.index = r.index % len(r.buff)

	if len(r.buff) < cap(r.buff) { // if ringbuffer isn't full yet, append to it
		r.buff = append(r.buff, nil)
	}

	// r.index is the ringbuffer index that points to the 1-gram. (Here it's yet but a 0-gram / root.)
	r.buff[r.index] = node
}

func (r *ringBuffer) GetIter() ringIterator {
	i := ringIterator{}
	i.buff = r.buff
	i.index = r.index + len(r.buff)
	return i
}

func (r *ringIterator) Next() bool {
	r.index--
	if r.index > len(r.buff) {
		return true
	}
	return false
}

func (r *ringIterator) GetValue() *trie.Node {
	return r.buff[r.index%len(r.buff)]
}

func (r *ringIterator) SetValue(node *trie.Node) {
	r.buff[r.index%len(r.buff)] = node
}
