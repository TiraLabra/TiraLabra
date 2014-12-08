
EXE = chess.exe
SRCS = main.cpp
OBJS = $(subst .cpp,.o,$(SRCS))
CXXFLAGS = -g -std=gnu++11 -I. -fpermissive
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

DEPDIR = .depends
df = $(DEPDIR)/$(*F)

MAKEDEPEND = gcc -M -I. $(CPPFLAGS) -o $(DEPDIR)/$*.d $<
%.o : %.cpp
	@$(MAKEDEPEND); \
		cp $(df).d $(df).P; \
		sed -e 's/#.*//' -e 's/^[^:]*: *//' -e 's/ *\\$$//' \
		-e '/^$$/ d' -e 's/$$/ :/' < $(df).d >> $(df).P; \
		rm -f $(df).d
	$(COMPILE.cpp) -o $@ $<

-include $(SRCS:%.cpp=$(DEPDIR)/%.P)
