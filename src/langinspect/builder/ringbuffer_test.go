package builder

import (
	"fmt"
	"testing"
)

func TestRingbufferBasics(t *testing.T) {
	rb := NewRingBuffer(20)
	node := trie.NewNode()
	rb.Add(node)
	if len(rb.buff) != 1 {
		fmt.Println("Wrong size?")
		t.Fail()
	}
	for i := 0; i < 25; i++ {
		rb.Add(node)
	}
	if len(rb.buff) != 20 {
		fmt.Println("Wrong size?")
		t.Fail()
	}
}
