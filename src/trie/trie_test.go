package trie

import (
	"fmt"
	"testing"
)

func TestBasics(*testing.T) {
	t := NewNode()
	t.Add([]byte("hajoo"), "HAJOO")
	t.Add([]byte("aakkos"), "AAKKOS")
	t.Add([]byte("aakkosia"), "AAKKOSIA")
	t.Add([]byte("haju"), "HAJU")
	t.Add([]byte("soppaako"), "SOPPAAKO")
	fmt.Println(t.TryAndGet([]byte("hajoo")))
	fmt.Println(t.TryAndGet([]byte("aakkos")))
	fmt.Println(t.TryAndGet([]byte("aakkosia")))
	fmt.Println(t.TryAndGet([]byte("haju")))
	fmt.Println(t.TryAndGet([]byte("soppaako")))
}
