class LinkedList:
	"""Simple linked list implementation for our MD5"""
	def __init__(self, root):
		self.root = root
		self.tail = root

	def toString(self):
		s = ""
		root = self.root
		while (root != None):
			s += root.value
			root = root.next
		return s


	def add(self, node):
		self.tail.next = node
		self.tail = node


class Node:
	"""A simple node"""
	def __init__(self, value, next):
		self.value = value
		self.next = next