package builder

import (
	"github.com/golddranks/TiraLabra/src/trie"
)

type ringBuffer struct {
	buff  []*trie.Node
	index int
}

func NewRingBuffer() ringBuffer {
	r := ringBuffer{}
	r.buff = make([]*trie.Node, 0, MaxDepth)
	r.index = 0
	return r
}

func (r *ringBuffer) Clear() {
	r.buff = r.buff[:0]
	r.index = 0
}

func (r *ringBuffer) Add(node *trie.Node) {

	r.index++
	r.index = r.index % MaxDepth

	if len(r.buff) < cap(r.buff) { // if ringbuffer isn't full yet, append to it
		r.buff = append(r.buff, nil)
	}

	// r.index is the ringbuffer index that points to the 1-gram. (Here it's yet but a 0-gram / root.)
	r.buff[r.index] = node
}
