import linkedlist

class NMD5:
    """MD5 implementation for strings"""
    list = linkedlist.LinkedList(None)
    A = 0x67452301
    B = 0xEFCDAB89
    C = 0x98BADCFE
    D = 0x10325476

    def F(x, y, z):
    	return (x & y) | ((~x) & z)

    def G(x, y, z):
    	return (x & z) | (y & (~z))

    def H(x, y, z):
    	return x ^ y ^ z

    def I(x, y, z):
    	return y ^ (x | (~z))


    def update(arg):
    	list.add(linkedlist.Node(arg, None))


    # Main hashing function
    def calc(self):
    	AA = self.A
    	BB = self.B
    	CC = self.C
    	DD = self.D		

    
    

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
	if amount <= 0: # edge cases between 448-512
		amount = (512 - messageLength) + 448 

	padding = "1"
	padding += "0" * (amount - 1)

	padded += bstring + padding
	padded += pad64B(messageLength)

	return padded

def pad64B(length):
	s = bin(length)
	if len(s) == 9:
			s = s.replace('b', '')
	else:
			s = s.replace('b', '0')

	padded = ''
	#asd += (64 - length) * "0"
	#wew = asd + s


	padded = "0" * (64 - len(s))
	padded += s
	return padded

## Public methods TBI ##

def digest_size():
	return 16

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
