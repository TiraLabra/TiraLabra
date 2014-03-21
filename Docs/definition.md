Project Definition
==================
The purpose of this laboratory project is to implement the Data Encryption Standard into a working encryption/decryption software using Java as the language. The DES algorithm is the first major standard for an algorithm meant to encrypt and decrypt sensitive messages, developed in the 1970's, now old and insecure. Among the different modes defined for the algorithm are electronic codebook, cipher block chaining, cipher feedback, and output feedback -modes, each intended to circumvent it's different vulnerabilities.

Implementation
==============
The application I am developing should be a working command-line application, taking a message, either encrypted or not, and running necessary algorithms for it and producing the wanted result: either a decrypted or encrypted message. The main algorithm itself is a block cipher, of which there have been defined several different modes, some of which are being implemented here.

Input and output
----------------
The application accepts a number of parameters and flags from the command line, including flags for encryption or decryption (specified in [manual file] (https://github.com/Julppu/Tira-DESu/blob/master/Docs/man.md).

Algorithms
----------
DES uses a number of algorithms for operations, main one being the Feistel f-function. DES chops the data under encryption/decryption into blocks of standard size, 64 bits, which is divided into two halves of 32 bits (left and right). The right half is passed to Feistel f-function and the left half if XOR-ed with the result. This is repeated 15 times, and on the 16th time the result is permuted for the last time.  
Other required algorithms are the Feistel s-function, which permutes the data through eight arrays of bits. Other algorithms are either implementations of different modes or methods for handling the data.

Data Structures
---------------
The algorithm for DES does not require data structures besides normal arrays, since it mainly uses bit-shifting on byte-arrays. Optionally it is possible to implement the passing of blocks to the algorithm via a queue or a stack in order to encrypt/decrypt longer messages.

Complexity
----------
As this implementation requires only looping through arrays using a non variable amount of variables, space complexity is constant, O(1). All algorithms use only singular for-loops and a singular block is of standard size, so time complexity remains O(1), unless data is being handled in a series, in which case the complexity is O(n), where n equals the number of blocks.

Sources
=======
* [Data Encryption Standard] (http://csrc.nist.gov/publications/fips/fips46-3/fips46-3.pdf): FIPS Publication 46-3, Oct. 1995.
* [Data Encryption Standard] (http://en.wikipedia.org/wiki/Data_Encryption_Standard): Wikipedia, fetched 14.3.2014
