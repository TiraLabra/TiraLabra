A reimplementation of the MD5 module for Python 3.x

Message Digest 5 (MD5) is a hash function used to generate a hash from a given input. It's most popular uses
are using it as a checksum or to obfuscate passwords. Since MD5 for strings has a hash collision weakness, it is considered unsafe for use with passwords. It is however still quite popular for computing and verifying checksums due to low chance of collisions.

Since 2.5, MD5 has been deprecated to hashlib - this project is a reimplementation of that module.
The goal is to implement the MD5 digest calculation module for strings in verbatim from the 2.5 version. The module was most likely deprecated for encapsulation within the *hashlib* module for clarity, and also because it isn't safe enough anymore to warrant a specific named module for itself.

This includes the following methods:

* md5.digest_size
* md5.new([arg])
* md5.md5([arg])
* md5.update(arg)
* md5.digest()
* md5.hexdigest()
* md5.copy()

# MD5 Overview
MD5 is a hash function that generates a hash from a given input (message). After taking an input, it performs the following steps:
<ol>
	<li>Pad message with bits so it is congruent to 448 mod 512</li>
	<li>Append a 64-bit representation of the message length (little-endian)</li>
	<li>Initialize constants A,B,C,D</li>
	<li>Slice message into 512-bit blocks</li>
	<li>For each block, slice it into 16 "words"</li>
	<li>Perform four rounds with functions F,G,H,I on words</li>
	<li>Save result and give a digest</li>
</ol>

# Implementation goals

The goal of this product was to reimplement the MD5 module deprecated in Python 2.5. I didn't plan anything else beforehand except that it would follow the Python object style which is documented.

This implementation will focus on code clarity and the internal workings on MD5 - speed is not a primary concern. Because Python is a high level language and as such is clearly a lot slower than many other languages.

With this implementation, data is mostly represented as a string of binary numbers. This helps with the abstraction of the algorithm, but will probably be slower compared to using Python's byte literals for example. 

Mostly the MD5 algorithm is a linear time algorithm, because with increasing message length the running time increases (albeit slowly). A custom linked list structure is used to store all added strings of the NMD5 object.

Like the original `md5` module, this class is meant to be imported into a project as a library, and has a corresponding API to what the original has.
