package trie

import "fmt"

type Node struct {
	parent      *Node
	keyFragment byte
	Children    [256]*Node
	Value       interface{}
}

const debug = true

var nodeCount int

func NodeCount() int {
	return nodeCount
}

func CreateNode() *Node {
	if debug {
		fmt.Println("Create node")
		nodeCount++
	}
	n := &Node{}
	n.Children = [256]*Node{}
	return n
}

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

func (n *Node) GetOrCreate(key []byte) *Node {
	if debug {
		fmt.Print("GetOrCreate with key: ", string(key))
		m := n
		for m != nil {
			fmt.Print(string([]byte{m.keyFragment}))
			m = m.parent
		}
		fmt.Println("/")
	}
	child := &n.Children[key[0]]
	if *child == nil {
		*child = CreateNode()
		if debug {
			(*child).parent = n
			(*child).keyFragment = key[0]
		}
	}
	if len(key) > 1 {
		return (*child).GetOrCreate(key[1:])
	} else {
		return *child
	}
}

func (n *Node) TryAndGet(key []byte) interface{} {
	if len(key) > 0 {
		child := n.Children[key[0]]
		if child == nil {
			return nil
		} else {
			return child.TryAndGet(key[1:])
		}
	} else {
		return n.Value
	}
}
