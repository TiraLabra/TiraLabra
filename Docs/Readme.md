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
