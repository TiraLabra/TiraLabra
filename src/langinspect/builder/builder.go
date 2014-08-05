package builder
"Builds a trie-based database of n-gram frequencies in different languages"

import (
	"fmt"
	"github.com/golddranks/TiraLabra/src/trie"
	"io"
)

func Build(input io.Reader) {
	dict := trie.CreateNode()
	dict.Add("jee", "juu")
	fmt.Println("output")
}
