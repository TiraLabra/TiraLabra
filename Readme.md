# Use
```
import nmd5
m = nmd5.new()
m.update("Hello World!")
m.hexdigest() #'ed076287532e86365e841e92bfc50d8c'
m.digest() #b'\xed\x07b\x87S.\x866^\x84\x1e\x92\xbf\xc5\r\x8c'
```

A reimplementation of the MD5 module for Python 3.x

Message Digest 5 (MD5) is a hash function used to generate a hash from a given input. It's most popular uses
are using it as a checksum or to obfuscate passwords. Since MD5 for strings has a hash collision weakness, it is considered unsafe for use with passwords. It is however still quite popular for computing and verifying checksums due to low chance of collisions.

Since 2.5, MD5 has been deprecated to hashlib - this project is a reimplementation of that module.
The goal is to implement the MD5 digest calculation module for strings in verbatim from the 2.5 version. The module was most likely deprecated for encapsulation within the **hashlib** module for clarity, and also because it isn't safe enough anymore to warrant a specific named module for itself.

This includes the following methods:

- [x] md5.digest_size
- [x] md5.new([arg])
- [x] md5.md5([arg])
- [x] md5.update(arg)
- [x] md5.digest()
- [x] md5.hexdigest()
- [x] md5.copy()

The module will be deployed as a custom importable class.

The implementation uses a linked list structure for storing the input string.

https://docs.python.org/2/library/md5.html includes the more detailed module specification.
