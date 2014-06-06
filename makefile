all:
	g++ LZW.cpp main.cpp linkednode.cpp linkedlist.cpp -o main
testi:
	g++ -c LZW.cpp linkedlist.cpp linkednode.cpp

link:
	g++ -c linkedlist.cpp linkednode.cpp

boost:
	g++ test.cpp linkedlist.cpp linkednode.cpp -o test 
	./test
