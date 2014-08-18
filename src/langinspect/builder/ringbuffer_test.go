package builder

import (
	"fmt"
	"github.com/golddranks/TiraLabra/src/trie"
	"testing"
)

func TestRingbufferBasics(t *testing.T) {
	rb := NewRingBuffer(20)
	firstNode := trie.NewNode()
	secondNode := trie.NewNode()
	node := trie.NewNode()
	replaced := rb.Add(firstNode)
	if len(rb.buff) != 1 {
		fmt.Println("Wrong size?")
		t.Fail()
	}
	if replaced != nil {
		fmt.Println("Shouldn't be full yet?")
		t.Fail()
	}
	replaced = rb.Add(secondNode)
	if len(rb.buff) != 2 {
		fmt.Println("Wrong size?")
		t.Fail()
	}
	if replaced != nil {
		fmt.Println("Shouldn't be full yet?")
		t.Fail()
	}
	for i := 0; i < 18; i++ {
		replaced = rb.Add(node)
		if replaced != nil {
			fmt.Println("Shouldn't be full yet?")
			t.Fail()
		}
	}
	replaced = rb.Add(node)
	if len(rb.buff) != 20 {
		fmt.Println("Wrong size?")
		t.Fail()
	}
	if replaced != firstNode {
		fmt.Println("The oldest node should've been replaced!")
		t.Fail()
	}
	replaced = rb.Add(node)
	if len(rb.buff) != 20 {
		fmt.Println("Wrong size?")
		t.Fail()
	}
	if replaced != secondNode {
		fmt.Println("The oldest node should've been replaced!")
		t.Fail()
	}
}

func TestRingbufferClearing(t *testing.T) {
	rb := NewRingBuffer(15)
	node := trie.NewNode()
	for i := 0; i < 18; i++ {
		rb.Add(node)
	}
	rb.Clear()
	node2 := trie.NewNode()
	replaced := rb.Add(node2)
	if replaced != nil {
		fmt.Println("Should have been cleared!")
		t.Fail()
	}
	if len(rb.buff) != 1 {
		fmt.Println("Should have been cleared!")
		t.Fail()
	}

}

func TestRingbufferIter(t *testing.T) {
	rb := NewRingBuffer(5)
	iter := rb.IterFromNewest()
	if iter.Next() {
		fmt.Println("Ringbuffer is empty and shouldn't be able to be iterated!")
		t.Fail()
	}
	node := trie.NewNode()
	rb.Add(node)
	iter = rb.IterFromNewest()
	if iter.Next() == false {
		fmt.Println("There should be one value!")
		t.Fail()
	}
	if iter.GetValue() != node {
		fmt.Println("GetValue() != node")
		t.Fail()
	}
	if iter.Next() == true {
		fmt.Println("There should be only one value!")
		t.Fail()
	}
	node2 := trie.NewNode()
	rb.Add(node2)
	node3 := trie.NewNode()
	rb.Add(node3)
	iter = rb.IterFromNewest()
	iter.Next()
	if iter.GetValue() != node3 {
		fmt.Println("Iterator should start from the newest node!")
		t.Fail()
	}
	if iter.Next() == false {
		fmt.Println("There should be more!")
		t.Fail()
	}
	if iter.GetValue() != node2 {
		fmt.Println("The second newest should become next!")
		t.Fail()
	}
	if iter.Next() == false {
		fmt.Println("There should be more!")
		t.Fail()
	}
	if iter.GetValue() != node {
		fmt.Println("The oldest should become last!")
		t.Fail()
	}
	if iter.Next() == true {
		fmt.Println("That should have been all!")
		t.Fail()
	}
	node4 := trie.NewNode()
	rb.Add(node4)
	node5 := trie.NewNode()
	rb.Add(node5)
	node6 := trie.NewNode()
	rb.Add(node6)
	iter = rb.IterFromNewest()
	iter.Next()
	if iter.GetValue() != node6 {
		fmt.Println("Iterator should start from the newest node!")
		t.Fail()
	}
	i := 1
	for iter.Next() {
		i++
	}
	if i != 5 {
		fmt.Println("Next should have been true 4 times, it was", i)
		t.Fail()
	}
}

func TestRingbufferIterSetValue(t *testing.T) {
	rb := NewRingBuffer(6)
	node := trie.NewNode()
	node2 := trie.NewNode()
	rb.Add(node)
	rb.Add(node)
	iter := rb.IterFromNewest()
	iter.Next()
	iter.SetValue(node2)
	iter.Next()
	iter2 := rb.IterFromNewest()
	iter2.Next()
	if iter2.GetValue() != node2 {
		fmt.Println("The value should be set again!")
		t.Fail()
	}
}
