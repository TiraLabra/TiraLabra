The goal of this product was to reimplement the MD5 module deprecated in Python 2.5. I didn't plan anything else beforehand except that it would follow the Python object style which is documented.

The structure of the program is as follows:

* The NMD5 class
* The NMD5 module (which contains the class)
* The public module methods **new()** and **md5()**
* The private class methods **__hash()**, **functions FGHI**, **toBinaryString()**, **createWordArray()**, **pad()**, **pad64B()**, **splitToBlocks()**
* The public class methods **update()**, **digest()**, **copy()**, **hexdigest()**, **digest_size**


Sources used:
[http://en.wikipedia.org/wiki/MD5](http://en.wikipedia.org/wiki/MD5)
[http://www.ietf.org/rfc/rfc1321.txt](http://www.ietf.org/rfc/rfc1321.txt)
