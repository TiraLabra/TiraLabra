The goal of this product was to reimplement the MD5 module deprecated in Python 2.5. I didn't plan anything else beforehand except that it would follow the Python object style which is documented.

The structure of the program is as follows:

* The NMD5 class
* The NMD5 module (which contains the class)
* The public module methods **new()** and **md5()**
* The private class methods **__hash()**, **functions FGHI**, **toBinaryString()**, **createWordArray()**, **pad()**, **pad64B()**, **splitToBlocks()**
* The public class methods **update()**, **digest()**, **copy()**, **hexdigest()**, **digest_size**"""Function for round 1"""

	r = a + F(b,c,d)
	r = r + x
	r = r + ac
	r = r & 0xffffffff # keep r as an unsigned integer
	r = rotateLeft(r, s)
	r = r & 0xffffffff
	r = r + b

	return r & 0xffffffff


Sources used:
Wikipedia
[http://en.wikipedia.org/wiki/MD5](http://en.wikipedia.org/wiki/MD5)

RFC 1321
[http://www.ietf.org/rfc/rfc1321.txt](http://www.ietf.org/rfc/rfc1321.txt)

Python 2.5 documentation for the MD5 module
[https://docs.python.org/2/library/md5.html](https://docs.python.org/2/library/md5.html)

Step-by-step breakdowns of the algorithm
[http://www.scribd.com/doc/35954574/MD5-With-Example](http://www.scribd.com/doc/35954574/MD5-With-Example)
[http://rosettacode.org/wiki/MD5/Implementation_Debug](http://rosettacode.org/wiki/MD5/Implementation_Debug)

Stack Exchange
[http://security.stackexchange.com/questions/39420/rounds-of-md5-and-sha512-hashing-algorithms](http://security.stackexchange.com/questions/39420/rounds-of-md5-and-sha512-hashing-algorithms)