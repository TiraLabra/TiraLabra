/*
This is the general interface for ordered maps. They take slices of bytes as keys,
and any kind of objects as values (interface{}). They are guaranteed to support
insertion, lookup and retrieval, and iterating in order. The performance
characteristics are implementation-specific. At the moment there is two
implementations: a naive trie (implemented with struct nodes and pointers) and
a double array trie, which is more memory efficient and creates less garbage to
collect. They both offer O(1 + K) lookup where K is the length of the key - similar
performance characteristics with hash tables but with the ordered property.

There might be some non-prefix tree implementations in the future complying this
interface for performance comparison.

TO DO: Apparently golang doesn't support co- and contravariance, which means that
this interface might not work... ever. Screw that.
*/
package orderedmap

type OrderedMap interface {
	Add([]byte, int)
	GetOrCreate([]byte) *Iter
	TryAndGet([]byte) *Iter
}

type Iter interface {
	Add([]byte, int)
	GetOrCreate([]byte) *Iter
	TryAndGet([]byte) *Iter
	Prefix() []byte
	Value() int
}
