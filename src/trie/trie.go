/*
Package trie implements a trie, that is, a prefix tree.

Trie provides fast (O(1)) setting and retrieval relative to the amount of keys
in the dataset. (O(L) where L is the length of the key.)
*/
package trie

import "fmt"

// Node represents a trie key-value pair. It may contain a value, which is represented
// by the Value field. If it doesn't, Value is nil. The value is contained by an empty
// interface, so you may need to type-assert it to get the original value.
// You can get the key of the node by calling the method Node.Prefix()
type Node struct {
	parent      *Node
	keyFragment byte
	children    [256]*Node
	Value       interface{}
}

var nodeCount int

const debug = false
const stats = true

// Creates a new node that isn't part of any existing trie.
// Created node can be used as a root node.
func NewNode() *Node {
	if debug {
		fmt.Println("Create node")
	}
	n := &Node{}
	n.children = [256]*Node{}
	return n
}

// Adds a key-value pair to the trie.
// Key is a sequence of bytes and value may be any kind of a value. If the
// key-value pair already exists, it is overwritten.
func (n *Node) Add(key []byte, object interface{}) {
	if debug {
		fmt.Println("Add with key:", key)
	}
	leaf := n.GetOrCreate(key)
	leaf.Value = object
	if debug {
		fmt.Println("Added object:", object)
	}
}

// Gets a node (representing a key-value pair) by given key.
// If the key-value pair doesn't exist, it creates and returns a node representing
// a key-value pair with a nil value. You can set the value.
func (n *Node) GetOrCreate(key []byte) *Node {
	if debug {
		fmt.Println("GetOrCreate with path:", string(n.Prefix()), "+", string(key))
	}
	child := &n.children[key[0]]
	if *child == nil {
		*child = NewNode()
		(*child).parent = n
		(*child).keyFragment = key[0]
	}
	if stats {
		nodeCount++
	}
	if len(key) > 1 {
		return (*child).GetOrCreate(key[1:])
	} else {
		return *child
	}
}

// Returns the value by key. Returns nil if key doesn't exist or if the value
// isn't set.
func (n *Node) TryAndGet(key []byte) interface{} {
	if len(key) > 0 {
		child := n.children[key[0]]
		if child == nil {
			return nil
		} else {
			return child.TryAndGet(key[1:])
		}
	} else {
		return n.Value
	}
}

func (n *Node) prefix(depth int) []byte {
	if n.parent != nil {
		prefix := n.parent.prefix(depth + 1)
		prefix[len(prefix)-depth-1] = n.keyFragment
		return prefix
	} else {
		return make([]byte, depth, depth)
	}
}

// Get the key of given node.
func (n *Node) Prefix() []byte {
	return n.prefix(0)
}

func (n *Node) walkKeys(nodeChan chan *Node) {
	nodeChan <- n
	for _, child := range n.children {
		if child != nil {
			child.walkKeys(nodeChan)
		}
	}
}

// Return a channel that iterates through all the keys and prefixes of keys
// in the trie.
// Note: it walks through also keys whose values aren't set.
func (n *Node) WalkKeys() chan *Node {
	nodeChan := make(chan *Node)
	go func() {
		n.walkKeys(nodeChan)
		close(nodeChan)
	}()
	return nodeChan
}

func (n *Node) walkValues(valueChan chan interface{}) {
	if n.Value != nil {
		valueChan <- n.Value
	}
	for _, child := range n.children {
		if child != nil {
			child.walkValues(valueChan)
		}
	}
}

// Return a channel that iterates through all the values in the trie.
func (n *Node) WalkValues() chan interface{} {
	valueChan := make(chan interface{})
	go func() {
		n.walkValues(valueChan)
		close(valueChan)
	}()
	return valueChan
}
