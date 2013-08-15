
SOURCES = $(wildcard src/*.c)
TEST_SOURCES = $(wildcard test/*.c)
TARGET = ssoppa
TEST_EXECUTABLE = alltests
CFLAGS = -std=c99 -Wall -Isrc/

OBJS = $(patsubst %.c,%.o,$(SOURCES))
TEST_OBJS = $(filter-out src/main.o, $(OBJS)) $(patsubst %.c,%.o,$(TEST_SOURCES))

$(TARGET): $(OBJS)
	$(CC) $(CFLAGS) $(CPPFLAGS) $(OBJS) -o $@

$(TEST_EXECUTABLE): $(TEST_OBJS)
	$(CC) $(CFLAGS) $(CPPFLAGS) $(TEST_OBJS) -o $@

.PHONY: clean
.PHONY: test

test: $(TEST_EXECUTABLE)
	./$(TEST_EXECUTABLE)

clean: 
	rm -f $(OBJS) $(TEST_OBJS) $(TARGET)
