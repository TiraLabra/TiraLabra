import nmd5
import unittest
import random
import linkedlist
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
		for x in range(0, 1000): # make sure edge cases are tested
			s = self.TH.generate_random_binary()
			self.assertTrue(len(nmd5.pad(s)) % 512 == 0)

	def test_fixed(self):
		s = '011000010111001101100100' #asd
		self.assertEqual(len(nmd5.pad(s)), 512)


class TestLinkedList(unittest.TestCase):

	def setUp(self):
		self.emptyList = linkedlist.LinkedList(None)
		self.initList = linkedlist.LinkedList(linkedlist.Node("Test", None))

	def test_empty_list(self):
		self.assertEqual("", self.emptyList.toString())

	def test_addition(self):
		self.emptyList.add(linkedlist.Node("Abc", None))
		self.assertEqual("Abc", self.emptyList.toString())

	def test_add_many(self):
		self.emptyList.add(linkedlist.Node("Abc", linkedlist.Node("def", linkedlist.Node("ghi", linkedlist.Node("klm", None)))))
		self.assertEqual("Abcdefghiklm", self.emptyList.toString())

	def test_init_list(self):
		self.assertEqual("Test", self.initList.toString())

class TestBlockSplit(unittest.TestCase):

	def setUp(self):
		self.t1 = nmd5.pad(nmd5.toBinaryString("test1"))
		self.t2 = nmd5.pad(nmd5.toBinaryString("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"))

	def test_one_block(self):
		self.assertEqual(len(self.t1), 512)

	def test_another_block(self):
		self.assertEqual(len(self.t2), 1024)

	def test_split_32(self):
		self.assertEqual(len(nmd5.splitToBlocks(self.t1, 512)), 1)

	def test_split_32_2(self):
		self.assertEqual(len(nmd5.splitToBlocks(self.t2, 512)), 2)

	def test_split_32_3(self):
		split = nmd5.splitToBlocks(self.t1, 16)

		self.assertEqual(len(split), 32)



if __name__ == '__main__':
	unittest.main()

