from lib.linkedlist import *

def F(x, y, z):
	"""XY v not(X) Z"""
	return (x & y) | ((~x) & z)

def G(x, y, z):
	"""XZ v Y not(Z)"""
	return (x & z) | (y & (~z))

def H(x, y, z):
	"""X xor Y xor Z"""
	return x ^ y ^ z

def I(x, y, z):
	"""Y xor (X v not(Z))"""
	return y ^ (x | (~z))

def rotateLeft(x, n):
	return (x << n) | (x >> (32-n))

def FF(a,b,c,d,x,s,ac):
	"""Function for round 1"""

	r = a + F(b,c,d)
	r = r + x
	r = r + ac
	r = r & 0xffffffff
	r = rotateLeft(r, s)
	r = r & 0xffffffff
	r = r + b

	return r & 0xffffffff

def GG(a,b,c,d,x,s,ac):
	"""Function for round 2"""
	r = a + G(b,c,d)
	r = r + x
	r = r + ac
	r = r & 0xffffffff
	r = rotateLeft(r, s)
	r = r & 0xffffffff
	r = r + b

	return r & 0xffffffff

def HH(a,b,c,d,x,s,ac):
	"""Function for round 3"""
	r = a + H(b,c,d)
	r = r + x
	r = r + ac
	r = r & 0xffffffff
	r = rotateLeft(r, s)
	r = r & 0xffffffff
	r = r + b

	return r & 0xffffffff

def II(a,b,c,d,x,s,ac):
	"""Function for round 4"""
	r = a + I(b,c,d)
	r = r + x
	r = r + ac
	r = r & 0xffffffff
	r = rotateLeft(r, s)
	r = r & 0xffffffff
	r = r + b

	return r & 0xffffffff


class NMD5:
	"""MD5 implementation for strings"""

	digest_size = 16
	list = LinkedList(None)

	A = 0x67452301
	B = 0xEFCDAB89
	C = 0x98BADCFE
	D = 0x10325476
	buffers = [0, 0, 0, 0]

	def update(self, arg):
		"""Adds a string to our list and calculates the hash.
		Note that subsequent updates need to reset the registers."""
		self.A, self.B, self.C, self.D = 0x67452301, 0xEFCDAB89, 0x98BADCFE, 0x10325476
		self.list.add(Node(arg, None))
		self.__hash(self.list.toString())

	# Main hashing function
	def __hash(self, message):
		messageLength = len(message)
		chunks = self.splitToBlocks(self.pad(self.toBinaryString(message)), 512)

		for chunk in chunks:
			words = self.createWordArray(chunk, messageLength, chunks.index(chunk)==len(chunks)-1)
			# Rotation constants
			R11, R12, R13, R14 = 7, 12, 17, 22
			R21, R22, R23, R24 = 5, 9, 14, 20
			R31, R32, R33, R34 = 4, 11, 16, 23
			R41, R42, R43, R44 = 6, 10, 15, 21

			a, b, c, d = A, B, C, D = self.A, self.B, self.C, self.D

			# Round 1
			a = FF(a, b, c, d, words[ 0], R11, 0xD76AA478)
			d = FF(d, a, b, c, words[ 1], R12, 0xE8C7B756)
			c = FF(c, d, a, b, words[ 2], R13, 0x242070DB)
			b = FF(b, c, d, a, words[ 3], R14, 0xC1BDCEEE)
			a = FF(a, b, c, d, words[ 4], R11, 0xF57C0FAF)
			d = FF(d, a, b, c, words[ 5], R12, 0x4787C62A)
			c = FF(c, d, a, b, words[ 6], R13, 0xA8304613)
			b = FF(b, c, d, a, words[ 7], R14, 0xFD469501)
			a = FF(a, b, c, d, words[ 8], R11, 0x698098D8)
			d = FF(d, a, b, c, words[ 9], R12, 0x8B44F7AF)
			c = FF(c, d, a, b, words[10], R13, 0xFFFF5BB1)
			b = FF(b, c, d, a, words[11], R14, 0x895CD7BE)
			a = FF(a, b, c, d, words[12], R11, 0x6B901122)
			d = FF(d, a, b, c, words[13], R12, 0xFD987193)
			c = FF(c, d, a, b, words[14], R13, 0xA679438E)
			b = FF(b, c, d, a, words[15], R14, 0x49B40821)

			# Round 2
			a = GG(a, b, c, d, words[ 1], R21, 0xF61E2562)
			d = GG(d, a, b, c, words[ 6], R22, 0xC040B340)
			c = GG(c, d, a, b, words[11], R23, 0x265E5A51)
			b = GG(b, c, d, a, words[ 0], R24, 0xE9B6C7AA)
			a = GG(a, b, c, d, words[ 5], R21, 0xD62F105D)
			d = GG(d, a, b, c, words[10], R22, 0x02441453)
			c = GG(c, d, a, b, words[15], R23, 0xD8A1E681)
			b = GG(b, c, d, a, words[ 4], R24, 0xE7D3FBC8)
			a = GG(a, b, c, d, words[ 9], R21, 0x21E1CDE6)
			d = GG(d, a, b, c, words[14], R22, 0xC33707D6)
			c = GG(c, d, a, b, words[ 3], R23, 0xF4D50D87)
			b = GG(b, c, d, a, words[ 8], R24, 0x455A14ED)
			a = GG(a, b, c, d, words[13], R21, 0xA9E3E905)
			d = GG(d, a, b, c, words[ 2], R22, 0xFCEFA3F8)
			c = GG(c, d, a, b, words[ 7], R23, 0x676F02D9)
			b = GG(b, c, d, a, words[12], R24, 0x8D2A4C8A)

			# Round 3
			a = HH(a, b, c, d, words[ 5], R31, 0xFFFA3942)
			d = HH(d, a, b, c, words[ 8], R32, 0x8771F681)
			c = HH(c, d, a, b, words[11], R33, 0x6D9D6122)
			b = HH(b, c, d, a, words[14], R34, 0xFDE5380C)
			a = HH(a, b, c, d, words[ 1], R31, 0xA4BEEA44)
			d = HH(d, a, b, c, words[ 4], R32, 0x4BDECFA9)
			c = HH(c, d, a, b, words[ 7], R33, 0xF6BB4B60)
			b = HH(b, c, d, a, words[10], R34, 0xBEBFBC70) 
			a = HH(a, b, c, d, words[13], R31, 0x289B7EC6) 
			d = HH(d, a, b, c, words[ 0], R32, 0xEAA127FA) 
			c = HH(c, d, a, b, words[ 3], R33, 0xD4EF3085) 
			b = HH(b, c, d, a, words[ 6], R34, 0x04881D05) 
			a = HH(a, b, c, d, words[ 9], R31, 0xD9D4D039) 
			d = HH(d, a, b, c, words[12], R32, 0xE6DB99E5) 
			c = HH(c, d, a, b, words[15], R33, 0x1FA27CF8) 
			b = HH(b, c, d, a, words[ 2], R34, 0xC4AC5665) 

			# Round 4
			a = II(a, b, c, d, words[ 0], R41, 0xF4292244) 
			d = II(d, a, b, c, words[ 7], R42, 0x432AFF97) 
			c = II(c, d, a, b, words[14], R43, 0xAB9423A7) 
			b = II(b, c, d, a, words[ 5], R44, 0xFC93A039) 
			a = II(a, b, c, d, words[12], R41, 0x655B59C3) 
			d = II(d, a, b, c, words[ 3], R42, 0x8F0CCC92) 
			c = II(c, d, a, b, words[10], R43, 0xFFEFF47D) 
			b = II(b, c, d, a, words[ 1], R44, 0x85845DD1) 
			a = II(a, b, c, d, words[ 8], R41, 0x6FA87E4F) 
			d = II(d, a, b, c, words[15], R42, 0xFE2CE6E0) 
			c = II(c, d, a, b, words[ 6], R43, 0xA3014314) 
			b = II(b, c, d, a, words[13], R44, 0x4E0811A1) 
			a = II(a, b, c, d, words[ 4], R41, 0xF7537E82) 
			d = II(d, a, b, c, words[11], R42, 0xBD3AF235) 
			c = II(c, d, a, b, words[ 2], R43, 0x2AD7D2BB) 
			b = II(b, c, d, a, words[ 9], R44, 0xEB86D391)
			
			A = (a + A) % pow(2,32)
			B = (b + B) % pow(2,32)
			C = (c + C) % pow(2,32)
			D = (d + D) % pow(2,32)

			self.A = A
			self.B = B
			self.C = C
			self.D = D

	def hexdigest(self):
		"""Returns hex string of result"""
		res = ""
		buffers = [self.A, self.B, self.C, self.D]


		for buffer in buffers:
			for i in range(0,4):
				res = res + "{:02x}".format((buffer >> i*8) & 255)

		return res

	def digest(self):
		pass
	
	def toBinaryString(self, string):
		"""Converts a given string into a binary representation of itself"""
		bstring = ' '.join(bin(ord(x)) for x in string).split(' ')

		# Cleanup, remove 'b's, add leading zeroes
		for i in range(0, len(bstring)):
			b = bstring[i]
			if len(b) == 9:
				bstring[i] = b.replace('b', '')
			else:
				bstring[i] = b.replace('b', '0')
		return ''.join(bstring)

	def pad(self, bstring):
		"""Adds padding to binary string be congruent to 448 mod 512"""
		padded = ''
		messageLength = len(bstring)

		amount = 448 - (messageLength % 512)
		if amount <= 0: # edge cases between 448-512
			amount = (512 - messageLength) + 448 

		padding = "1"
		padding += "0" * (amount - 1)

		padded += bstring + padding
		padded += self.pad64B(messageLength)

		return padded

	def pad64B(self, length):
		"""Adds a 64-bit representation of the message length to the message"""
		s = bin(length)
		if len(s) == 9:
				s = s.replace('b', '')
		else:
				s = s.replace('b', '0')

		padded = ''

		padded = "0" * (64 - len(s))
		padded += s[::-1] # reverse length byte first to preserve correct order
		return padded[::-1]


	def splitToBlocks(self, message, n):
		"""Helper method to split a message into equal sized parts.
		Use for 512-bit sized 'blocks' or 16-bit sized 'words'."""
		if n<1:
			n=1
		return [message[i:i+n] for i in range(0, len(message), n)]

	def createWordArray(self, message, messageLength, finalBlock):
		"""Primes the 16-bit words for the main function"""

		message = self.splitToBlocks(message, 32)
		totalBytes = 0
		totalWords = 0
		wordArray = [0] * 16

		wordIndex = 0
		for word in message:
			bytes = self.splitToBlocks(word, 8)
			tempByte = 0
			powers = 0

			for byte in bytes:
				tempByte = wordArray[wordIndex]
				tempByte = tempByte | int(byte, 2) << powers
				powers += 8
				wordArray[wordIndex] = tempByte
				totalBytes += 1
			
			wordIndex += 1
			powers = 0

		## correct last two bytes
		if finalBlock:
			wordArray[-2] = messageLength << 3
			wordArray[-1] = messageLength >> 29

		return wordArray

## Public methods ##

def new():
	return NMD5()
