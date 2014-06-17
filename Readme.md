StringAlgorithmsStaticLib
=========================

A C++ library containing commercial and non-commercial string algorithms for processing, searching, etc

#Pattern searching
- Z algorithm

Description of the algorithm in ODT format: [link](https://github.com/martinradev/TiraLabra/blob/master/Docs/z%20algorithm.odt)
- Knuth-morris-pratt algorithm
- Rabin-karp algorithm.
 
Description of the algorithm in ODT format: [link](https://github.com/martinradev/TiraLabra/blob/master/Docs/rabin%20karp.odt?raw=true)

- Suffix trie
Description of the data structure in ODT format: [link](https://github.com/martinradev/TiraLabra/blob/master/Docs/suffix%20trie.odt)
- Suffix tree
A suffix tree can be built in O(n) time where n is the length of string we are building the tree for. Accordingly, if we are searching for a string with length m, then the search can be done in O(m) time.
- Aho–Corasick string matching (Trie)
- ...

#Hashing
- different hashing algorithms

#Other
- Levenhstein distance using dynamic programming.

Description of the algorithm in ODT format: [link](https://github.com/martinradev/TiraLabra/blob/master/Docs/edit%20distance.odt)

#My idea
- I have an idea to implement a fast data structure for string matching using hashing and a binary indexed tree. Generally, 
there will be preprocessing of the string in O(n * logn) time and answering queries whether a substring from start_index to
end_index matches another string.
The basic idea in my head right now is that I will be using a binary index tree which can answer queries in the type q(a,b) and return the sum of all elements of the array S from a to b in logN time where N is the size of the string (array). Since the array could get quite big then it makes sense to use a hashing function so that there aren't any overflows. Then check whether the hash of [a,b] equals the hash of the string we are searching for. If we are fine with errors and the hashing function is good enough, supports rolling hashing and there aren't too many collisions, then we can answer queries in just logN time with not too many errors.
- Search for consistent data structures for string searching/pattern matching or for something similar to B+ trees, but for strings.


#IDE
The project will be developed using CodeBlocks with the GNU GCC compiler.

#Automated testing
GoogleTest c++ library is used.

#Documentation
For now, Doxys will be used.

#Installation

