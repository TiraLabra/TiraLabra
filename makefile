all:
	g++ main.cpp nfa.cpp parser.cpp parsenode.cpp state.cpp

testi:
	g++ -c main.cpp nfa.cpp parser.cpp parsenode.cpp state.cpp

clean:
	rm *.o
