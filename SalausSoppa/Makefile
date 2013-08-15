
SOURCES = $(wildcard src/*.c)
TARGET = ssoppa
TEST_EXECUTABLE = alltests
CFLAGS = -std=c99 -Wall -Isrc/

ALLTESTS = test/AllTests.c
TEST_SOURCES = $(filter-out $(ALLTESTS), $(wildcard test/*.c)) $(ALLTESTS)

OBJS = $(patsubst %.c,%.o,$(SOURCES))
TEST_OBJS += $(filter-out src/main.o, $(OBJS))
TEST_OBJS += $(patsubst %.c,%.o,$(TEST_SOURCES))

$(TARGET): $(OBJS)
	$(CC) $(CFLAGS) $(CPPFLAGS) $(OBJS) -o $@

$(ALLTESTS): $(filter-out $(ALLTESTS), $(TEST_SOURCES))
	@cd test && bash make-tests.sh > AllTests.c

$(TEST_EXECUTABLE): $(ALLTESTS) $(TEST_OBJS)
	$(CC) $(CFLAGS) $(CPPFLAGS) $(TEST_OBJS) -o $@

.PHONY: clean
.PHONY: test

test: $(TEST_EXECUTABLE)
	./$(TEST_EXECUTABLE)

clean: 
	rm -f $(OBJS) $(TEST_OBJS) $(ALLTESTS) $(TEST_EXECUTABLE) $(TARGET)
