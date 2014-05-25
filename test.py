import nmd5
import unittest
import random
from random import randint


class TestHelper():
	def generate_random_binary(self):
		b = ''
		for i in range(0, randint(0,500)):
			x = str(randint(0,1))
			b += x
		return b

	def bstring_formatter(self, string):
		string = bin(ord(string))

		if len(string) == 9:
			return string.replace('b', '')
		else:
			return string.replace('b', '0')


class TestBinaryStringConversion(unittest.TestCase):
	def setUp(self):
		self.TH = TestHelper()

	def test_bstring_a(self):
		s = 'a'
		self.assertEqual(nmd5.toBinaryString(s), self.TH.bstring_formatter('a'))

	def test_bstring_b(self):
		s = 'b'
		self.assertEqual(nmd5.toBinaryString(s), self.TH.bstring_formatter('b'))


class TestPadding(unittest.TestCase):

	def setUp(self):
		self.TH = TestHelper()

	def test_random(self):
		s = self.TH.generate_random_binary()
		self.assertTrue(len(nmd5.pad(s)) % 512 == 448)

	def test_fixed(self):
		s = '011000010111001101100100' #asd
		self.assertEqual(len(nmd5.pad(s)), 448)

if __name__ == '__main__':
    unittest.main()
