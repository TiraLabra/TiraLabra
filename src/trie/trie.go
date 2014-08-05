package trie

import "fmt"

type Node struct {
	Children [256]*Node
	Value    interface{}
}

func CreateNode() *Node {
	n := &Node{}
	n.Children = [256]*Node{}
	return n
}

func (n *Node) Add(key string, object interface{}) {
	fmt.Println("Add start " + key)
	if len(key) > 0 {
		child := &n.Children[key[0]]
		if *child == nil {
			*child = CreateNode()
		}
		(*child).Add(key[1:], object)
	} else {
		n.Value = object
	}
}

func (n *Node) Get(key string) interface{} {
	if len(key) > 0 {
		child := n.Children[key[0]]
		return child.Get(key[1:])
	} else {
		return n.Value
	}
}
