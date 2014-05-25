
Tiralabra
========
A reimplementation of the MD5 module for Python 3.x

Since 2.5, MD5 has been deprecated to hashlib - this project is a reimplementation of that module.
The goal is to implement the MD5 digest calculation module for strings in verbatim from the 2.5 version.

This includes the following methods:

* md5.digest_size
* md5.new([arg])
* md5.md5([arg])
* md5.update(arg)
* md5.digest()
* md5.hexdigest()
* md5.copy()

The module will be deployed as a custom importable class or if possible as a Python egg.

The implementation will likely contain at least a linked list implementation, but mostly - following the RFC - will feature more bitwise operations on strings.

https://docs.python.org/2/library/md5.html includes the detailed specification.


