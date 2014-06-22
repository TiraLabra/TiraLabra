The goal of this product was to reimplement the MD5 module deprecated in Python 2.5. I didn't plan anything else beforehand except that it would follow the Python object style which is documented.

The structure of the program is as follows:

* The NMD5 class
* The NMD5 module (which contains the class)
* The public module methods **new()** and **md5()**
* The private class methods **__hash()**, **functions FGHI**, **toBinaryString()**, **createWordArray()**, **pad()**, **pad64B()**, **splitToBlocks()**
* The public class methods **update()**, **digest()**, **copy()**, **hexdigest()**
* The public field **digest_size**


### Complexity analysis

#### NMD5 class

##### digest_size

This is a field with the value `16` and as such is O(1) space and time.

##### update

```
	def update(self, arg):

		self.__A, self.__B, self.__C, self.__D = 0x67452301, 0xEFCDAB89, 0x98BADCFE, 0x10325476
		self.__list.add(Node(arg, None))
		self.__hash(self.__list.toString())

```
Only three O(1) operations are performed which makes this algorithm O(1) time and space.

##### new, md5, copy

These three do not perfrom any looping, and always only return a new NMD5 object. Thus it has O(1) time and space complexity.

##### digest

```
	def digest(self):

		res = b''
		buffers = [self.__A, self.__B, self.__C, self.__D]

		for buffer in buffers:
			bufferbytes = []
			b = bin(buffer).replace('b', '0')
			b = "0"*(34-len(b)) + b # pad leading zero if missing

			bufferbytes.append(int(b[ 2:10],2))
			bufferbytes.append(int(b[10:18],2))
			bufferbytes.append(int(b[18:26],2))
			bufferbytes.append(int(b[26:34],2))

			res += bytes([bufferbytes[3]])
			res += bytes([bufferbytes[2]])
			res += bytes([bufferbytes[1]])
			res += bytes([bufferbytes[0]])

		return res
```

O(1) time and space. While it has a for-loop which iterates over four items (O(n)), it is not possible for this algorithm to ever go over more than four items, which clearly makes it O(1).

##### hexdigest

```
	def hexdigest(self):

		return ''.join(["{:02x}".format(byte) for byte in bytearray(self.digest())])
```

O(1) time and space - the same explanation as above. It only ever takes a bytearray of digest which is fixed to 16 bytes and never goes beyond iterating over 16 bytes.

##### hash

```
	def __hash(self, message):

		messageLength = len(message.encode('utf-8'))
		chunks = self.__splitToBlocks(self.__pad(self.__toBinaryString(message)), 512)

		R11, R12, R13, R14 = 7, 12, 17, 22
		R21, R22, R23, R24 = 5, 9, 14, 20
		R31, R32, R33, R34 = 4, 11, 16, 23
		R41, R42, R43, R44 = 6, 10, 15, 21

		F, G, H, I, R, = self.__F, self.__G, self.__H, self.__I, self.__R

		for chunk in chunks:
			words = self.__createWordArray(chunk, messageLength, chunks.index(chunk)==len(chunks)-1)
			a, b, c, d = A, B, C, D = self.__A, self.__B, self.__C, self.__D

			# perform 64 operations
```




##### F, G, H, I, rotateLeft, R

```

```

##### createWordArray


```

```

##### pad

```

```

##### pad64B

```

```

##### splitToBlocks

```

```

##### toBinaryString

```
	def __toBinaryString(self, string):

		return ''.join("{:08b}".format(byte) for byte in bytearray(string.encode('utf-8')))
```

Creating a binary string is O(n) time and space. We can see that message is encoded into a UTF-8 bytearray and formatted into a binary representation of the byte. Even though it needs to encode the string into UTF-8, empirically testing we can see that it is actually O(n):

```
In [11]: print(min(timeit.Timer('tos("a"*100)', setup="import nmd5; m = nmd5.new(); tos=m._NMD5__toBinaryString").repeat(7, 10000)))
0.6207964479981456

In [12]: print(min(timeit.Timer('tos("a"*1000)', setup="import nmd5; m = nmd5.new(); tos=m._NMD5__toBinaryString").repeat(7, 10000)))
6.000566751026781

```
Here the running time increase is tenfold, as well as the message length.



### LinkedList class

##### add

```	
def add(self, node):
		if self.root == None:
			self.root = node
			self.tail = node
		else:
			self.tail.next = node
			self.tail = node			
```

Only variable assignment is in this method, making it O(1) in time and space complexity.

##### toString

```
	def toString(self):
		s = ""
		root = self.root

		if root == None:
			return s

		while (root != None):
			s += root.value
			root = root.next
		return s
```

If there are any elements in the list, `toString` is in O(n) time iterating all elements. Since a concatenated string of all elements is returned O(n) is also the space complexity.



### Sources

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