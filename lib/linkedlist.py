class LinkedList:
	"""Simple linked list implementation for our MD5. 
	To conform to the 2.5 MD5 module specification, this list doesn't need
	anything else than addition and a traversed catenated representation
	of itself."""

	def __init__(self, root):
		self.root = root
		self.tail = root

	def toString(self):
		s = ""
		root = self.root

		if root == None:
			return s

		while (root != None):
			s += root.value
			root = root.next
		return s


	def add(self, node):
		print("Adding", node.value, node.next)
		if self.root == None:
			self.root = node
			self.tail = node
		else:
			self.tail.next = node
			self.tail = node


class Node:
	"""A simple node for the list"""
	def __init__(self, value, next):
		self.value = value
		self.next = next