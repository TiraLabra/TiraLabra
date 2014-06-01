import linkedlist

class nmd5:
    """MD5 implementation for strings"""

A = "67452301"
B = "EFCDAB89"
C = "98BADCFE"
D = "10325476b"

## Private methods ##
## Keeping them public for the time being to enable testing ##

def toBinaryString(string):
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

def pad(bstring):
	"""Adds padding to binary string be congruent to 448 mod 512"""
	padded = ''
	messageLength = len(bstring)

	amount = 448 - (messageLength % 512)
	if amount <= 0:
		amount = (512 - messageLength) + 448 

	padding = "1"
	padding += "0" * (amount - 1)

	padded += bstring + padding

	return padded

## Public methods ##

def digest_size():
	return 16

def new():
	return

def update(arg):
	return

def digest():
	return

def hexdigest():
	return

def copy():
	return
