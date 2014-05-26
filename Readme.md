StringAlgorithmsStaticLib
=========================

A C++ library containing commercial and non-commercial string algorithms for processing, searching, etc

#Commerical algorithms:
- Z algorithm

Description of the algorithm in ODT format: [link](https://github.com/martinradev/TiraLabra/blob/master/Docs/z%20algorithm.odt)
- Knuth-morris-pratt algorithm
- Rabin-karp algorithm.
This algorithm is relatively good in practise. It is similar to the naive approach. Given strings A and B where B is the string which we are searching for in A, it checks for all i (i>=0 i and i <len(A) - len(B)) whether the substring A[i,i+len(B)] equals B. The algorithm is faster in comparison to the naive approach since it can be implemented to rely on hashing the string A[i,i+len(B)] and B. If the h(A[i,i+len(B)]) != h(B), then the strings are surely different. If both hashes are equal, then we have to check whether the string are actually equal and return the index. Computed the hash is a costly sequence of operations. However knowing h1, we could compute h2 in O(1) time.
For example, let h() be the Bernstein hashing function defined as
h2(A) = base*h1(A) + A[2] where base is usually 33
Now, let H be the hash for the string from 1 to 5. 
We could find the hash from 2 to 5 in the following manner. H(2,5) = (H(1,5) - A[1]*base^3).
If we have all of the powers of base, then we could do relatively fast searches on the string since it takes O(1) time to compute the next hash.
Generally, the algorithm runs in O(nm) time in the worst case where n is the length of A and m is the length of B. The algorithm is prefered for multiple search in a string. For example, check for many strings in a given string.
- ...

#Commercial data-structures:
- Suffix tree
A suffix tree can be built in O(n) time where n is the length of string we are building the tree for. Accordingly, if we are searching for a string with length m, then the search can be done in O(m) time.
- Aho–Corasick string matching (Trie)
- ...

#My idea
- I have an idea to implement a fast data structure for string matching using hashing and a binary indexed tree. Generally, 
there will be preprocessing of the string in O(n * logn) time and answering queries whether a substring from start_index to
end_index matches another string.
The basic idea in my head right now is that I will be using a binary index tree which can answer queries in the type q(a,b) and return the sum of all elements of the array S from a to b in logN time where N is the size of the string (array). Since the array could get quite big then it makes sense to use a hashing function so that there aren't any overflows. Then check whether the hash of [a,b] equals the hash of the string we are searching for. If we are fine with errors and the hashing function is good enough, supports rolling hashing and there aren't too many collisions, then we can answer queries in just logN time with not too many errors.
- Search for consistent data structures for string searching/pattern matching or for something similar to B+ trees, but for strings.


#IDE
The project will be developed using CodeBlocks with the GNU GCC compiler.

#Automated testing
Boost will be added as a helpful library in this case.

#Documentation
For now, Doxys will be used.
