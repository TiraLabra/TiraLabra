from lib.linkedlist import *

class NMD5:
	"""MD5 implementation for strings"""

	digest_size = 16
	list = LinkedList(None)
	A = 0x67452301
	B = 0xEFCDAB89
	C = 0x98BADCFE
	D = 0x10325476

	def F(self, x, y, z):
		"""XY v not(X) Z"""
		return (x & y) | ((~x) & z)

	def G(x, y, z):
		"""XZ v Y not(Z)"""
		return (x & z) | (y & (~z))

	def H(x, y, z):
		"""X xor Y xor Z"""
		return x ^ y ^ zeroes

	def I(x, y, z):
		"""Y xor (X v not(Z))"""
		return y ^ (x | (~z))

	def R1(self, a, b, c, d, X, s, sine):
		a = b + ((a + self.F(b,c,d) + X + sine << s))

	def update(arg):
		list.add(Node(arg, None))

    # Main hashing function
	def hash(self, message):
		a = self.A
		b = self.B
		c = self.C
		d = self.D


		chunks = self.splitToBlocks(self.pad(self.toBinaryString(message)), 512)

		for chunk in chunks:
			AA = self.A
			BB = self.B
			CC = self.C
			DD = self.D

			words = self.createWordArray(message)

			for word in words:

		return "wew"

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

	def createWordArray(self, message):
		"""Primes the 16-bit words for the main function"""

		message = self.splitToBlocks(self.pad(self.toBinaryString(message)), 32)
		wordArray = [0] * 16 # Our 16-word array

		wordIndex = 0

		for word in message:
			bytes = self.splitToBlocks(word, 8)
			tempByte = 0
			powers = 0

			for byte in bytes:
				#print("Wordindex:", wordIndex)
				tempByte = wordArray[wordIndex]
				#print(tempByte, " | ", int(byte, 2), " << ", powers)
				tempByte = tempByte | int(byte, 2) << powers
				powers += 8
				wordArray[wordIndex] = tempByte
				#print(wordArray[wordIndex])
			
			wordIndex += 1
			powers = 0

		return wordArray

## Public methods TBI ##

def new():
	return NMD5()

def update(arg):
	return

def digest():
	return

def hexdigest():
	return

def copy():
	return
