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
		return x ^ y ^ z

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

		words = self.splitToBlocks(self.pad(self.toBinaryString(message)), 16)

		for word in words:

			self.R1(a,b,c,d,int(words[0], 2),7,0xD76AA478);
			#print(word)
			# Round 1
			# Round 2
			# Round 3
			# Round 4
		#return AA + BB + CC + DD
		return a

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
		padded += s
		return padded


	def splitToBlocks(self, message, n):
		"""Helper method to split a message into equal sized parts.
		Use for 512-bit sized 'blocks' or 16-bit sized 'words'."""
		if n<1:
			n=1
		return [message[i:i+n] for i in range(0, len(message), n)]

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
