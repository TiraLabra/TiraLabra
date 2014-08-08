package trie

import "fmt"

type Node struct {
	parent      *Node
	keyFragment byte
	Children    [256]*Node
	Value       interface{}
}

const debug = false
const stats = true

var nodeCount int

func NodeCount() int {
	return nodeCount
}

func CreateNode() *Node {
	if debug {
		fmt.Println("Create node")
	}
	if stats {
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
		fmt.Println("GetOrCreate with path:", string(n.Prefix()), "+", string(key))
	}
	child := &n.Children[key[0]]
	if *child == nil {
		*child = CreateNode()
		(*child).parent = n
		(*child).keyFragment = key[0]
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

func (n *Node) prefix(depth int) []byte {
	if n.parent != nil {
		prefix := n.parent.prefix(depth + 1)
		prefix[len(prefix)-depth-1] = n.keyFragment
		return prefix
	} else {
		return make([]byte, depth, depth)
	}
}

func (n *Node) Prefix() []byte {
	return n.prefix(0)
}
