package trie

import "fmt"

type Node struct {
	Children [256]*Node
	Value    interface{}
}

const debug = false

func CreateNode() *Node {
	if debug {
		fmt.Println("Create node")
	}
	n := &Node{}
	n.Children = [256]*Node{}
	return n
}

func (n *Node) Add(key string, object interface{}) {
	if debug {
		fmt.Println("Add with key: " + key)
	}
	if len(key) > 0 {
		child := &n.Children[key[0]]
		if *child == nil {
			*child = CreateNode()
		}
		(*child).Add(key[1:], object)
	} else {
		n.Value = object
		if debug {
			fmt.Println("Added object: ", object)
		}
	}
}

func (n *Node) Get(key string) interface{} {
	if len(key) > 0 {
		child := n.Children[key[0]]
		if child == nil {
			return nil
		} else {
			return child.Get(key[1:])
		}
	} else {
		return n.Value
	}
}
