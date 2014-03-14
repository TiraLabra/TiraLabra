Project Definition
==================
The purpose of this laboratory project is to implement the Data Encryption Standard into a working encryption/decryption software using Java as the language. The DES algorithm is the first major standard for an algorithm meant to encrypt and decrypt sensitive messages, developed in the 1970's, now old and insecure. Among the different modes defined for the algorithm are electronic codebook, cipher block chaining, cipher feedback, and output feedback -modes, each intended to circumvent it's different vulnerabilities.

Implementation
==============
The application I am developing should be a working command-line application, taking a message, either encrypted or not, and running necessary algorithms for it and producing the wanted result: either a decrypted or encrypted message. The main algorithm itself is a block cipher, of which there have been defined several different modes, some of which are being implemented here.

Input and output
----------------
The application accepts a number of parameters and flags from the command line, including flags for encryption or decryption, 

Algorithms
----------
DES uses a number of algorithms for operations, main one being the Feistel f-function. 

Data Structures
---------------
The algorithm for DES does not require data structures besides normal arrays, since it mainly uses bit-shifting on byte-arrays. Optionally it is possible to implement the passing of blocks to the algorithm via a queue or a stack.

Sources
=======
* [Data Encryption Standard] (http://csrc.nist.gov/publications/fips/fips46-3/fips46-3.pdf): FIPS Publication 46-3, Oct. 1995.
* [Data Encryption Standard] (http://en.wikipedia.org/wiki/Data_Encryption_Standard): Wikipedia, fetched 14.3.2014
