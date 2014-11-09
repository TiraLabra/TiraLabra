
EXE = chess.exe
SRCS = main.cpp
OBJS = $(subst .cpp,.o,$(SRCS))
CXXFLAGS = -g -std=gnu++11
LIBS =

all: chess

chess: $(OBJS)
	g++ $(LDFLAGS) -o $(EXE) $(OBJS) $(LIBS)

clean:
	rm -f $(OBJS) $(EXE)

test:
	exit
