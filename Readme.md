StringAlgorithmsStaticLib
=========================

A C++ library containing commercial and non-commercial string algorithms for processing, searching, etc

#Commerical algorithms:
- Z algorithm
- Knuth-morris-pratt algorithm
- Rabin-karp algorithm. Possibily I will implement a few basic hashing functions for use.
- ...

#Commercial data-structures:
- Suffix tree
- Aho–Corasick string matching (Trie)
- ...

#My idea
- I have an idea to implement a fast data structure for string matching using hashing and a binary indexed tree. Generally, 
there will be preprocessing of the string in O(n * logn) time and answering queries whether a substring from start_index to
end_index matches another string.
- Search for consistent data structures for string searching/pattern matching or for something similar to B+ trees, but for strings.


#IDE
The project will be developed using CodeBlocks with the GNU GCC compiler.

#Automated testing
Boost will be added as a helpful library in this case.

#Documentation
For now, Doxys will be used.