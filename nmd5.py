class nmd5:
    """MD5 implementation for strings"""

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

	amount = 448 - (len(bstring) % 512) - 1
	padding = "1"
	padding += "0" * amount

	padded += bstring + padding

	return padded