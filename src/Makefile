
EXE = chess.exe
SRCS = main.cpp
OBJS = $(subst .cpp,.o,$(SRCS))
CXXFLAGS = -O3 -std=gnu++11 -I. -fpermissive -Wno-deprecated-declarations
CPPFLAGS = $(CXXFLAGS)
LIBS = -pthread

all: chess

chess: $(OBJS)
	g++ $(LDFLAGS) -o $(EXE) $(OBJS) $(LIBS)

clean:
	rm -f $(OBJS) $(EXE)

test:
	g++ $(CXXFLAGS) -DRUN_TESTS main.cpp -o test.exe $(LIBS)
	./test.exe

# automatic dependency generation

df = $(*F)

MAKEDEPEND = gcc -M -I. $(CPPFLAGS) -o $*.d $<
%.o : %.cpp
	@$(MAKEDEPEND); \
		cp $(df).d $(df).P; \
		sed -e 's/#.*//' -e 's/^[^:]*: *//' -e 's/ *\\$$//' \
		-e '/^$$/ d' -e 's/$$/ :/' < $(df).d >> $(df).P; \
		rm -f $(df).d
	$(COMPILE.cpp) -o $@ $<

-include $(SRCS:%.cpp=%.P)
