/*
This is the general interface for ordered maps. They take slices of bytes as keys,
and any kind of objects as values (interface{}). They are guaranteed to support
insertion, lookup and retrieval, and iterating in order. The performance
characteristics are implementation-specific. At the moment there is two
implementations: a naive trie (implemented with struct nodes and pointers) and
a double array trie, which is more memory efficient and creates less garbage to
collect. They both offer O(1 + K) lookup where K is the length of the key - similar
performance charasteristics with hash tables but with the ordered property.

There might be some non-prefix tree implementations in the future complying this
interface for performance comparison.
*/
package orderedmap

type OrderedMap interface {
	Add([]byte, interface{})
	GetOrCreate([]byte) *OrderedMapIter
	TryAndGet([]byte) *OrderedMapIter
}

type OrderedMapIter interface {
	Add([]byte, interface{})
	GetOrCreate([]byte) *OrderedMapIter
	TryAndGet([]byte) *OrderedMapIter
	Prefix() []byte
}
