Project Definition
==================
The purpose of this laboratory project is to implement the Data Encryption Standard into a working encryption/decryption software using Java as the language. The DES algorithm is the first major standard for an algorithm meant to encrypt and decrypt sensitive messages, developed in the 1970's, now old and insecure. Among the different modes defined for the algorithm are electronic codebook, cipher block chaining, cipher feedback, and output feedback -modes, each intended to circumvent it's different vulnerabilities.

Implementation
==============
The application I am developing should be a working command-line application, taking a message, either encrypted or not, and running necessary algorithms for it and producing the wanted result: either a decrypted or encrypted message. The main algorithm itself is a symmetric block cipher (both encryption and decryption keys are the same), of which there have been defined several different modes. This project implements the Cymmetric Block Cipher mode, which is more secure than the simpler Electronic Code Book.

Input and output
----------------
The application accepts a number of parameters and flags from the command line, including flags for encryption or decryption (specified in [manual file] (https://github.com/Julppu/Tira-DESu/blob/master/Docs/man.md)).

Algorithms
----------
DES chops the data under encryption/decryption into blocks of standard size, 64 bits. This block is permuted thourgh an Initial Permutation (IP) table, after which it is divided into two halves of 32 bits (left and right). The right half is passed to Feistel f-function and the left half if XOR-ed with the result. This is repeated 15 times, swapping left and right after every round, and on the 16th round both halves are permuted through an inverse IP table.

The Feistel F-function operates on 32 bits of data, which it expands to 48 bits with an expansion table. This data is mixed with a key by XOR-ing it with data from the previous round. The resulting 8x6 bits are run through eight substitution boxes (S-Box) and finally permuted once with a fixed table.

Other required algorithms are the Feistel s-function, which permutes the data through eight arrays of bits, and the padding algorithm PCKS#5 (also known as PBKDF2), which adds bytes to the end of a block equal in value to the number of empty bytes until the block is full 64 bits. The rest of the algorithms are either implementations of different modes or methods for handling the data.

Data Structures
---------------
The algorithm for DES does not require data structures besides normal arrays, since it mainly uses bit-shifting on byte-arrays. Optionally it is possible to implement the passing of blocks to the algorithm via a queue or a stack in order to encrypt/decrypt longer messages.

Complexity
----------
As this implementation requires only looping through arrays using a constant amount of variables, space complexity is constant, O(1). All algorithms use only singular for-loops and a singular block is of standard size, so time complexity remains O(1), unless data is being handled in a series, in which case the complexity is O(n), where n equals the number of blocks.

Sources
=======
* [Data Encryption Standard] (http://csrc.nist.gov/publications/fips/fips46-3/fips46-3.pdf): FIPS Publication 46-3, Oct. 1995 (fetched 14.3.2014).
* [Data Encryption Standard] (http://en.wikipedia.org/wiki/Data_Encryption_Standard): Wikipedia (fetched 14.3.2014).
* [RFC 2898] (http://tools.ietf.org/html/rfc2898): Internet Engineering Task Force, Sept. 2000 (fetched 16.4.2014).
