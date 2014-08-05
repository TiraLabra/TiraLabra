package main

import (
	"fmt"
	"github.com/golddranks/TiraLabra/src/trie"
)

func main() {
	t := trie.CreateNode()
	o := "JEA"
	fmt.Println(t)
	t.Add("hajoo", o)
	fmt.Println(t)
}
