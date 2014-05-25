#include "test_util.hpp"

random_string_generator::random_string_generator() {
    this->chars_size = ('Z'-'A') + ('z' - 'a') + 2;
    this->chars = new char[chars_size];
    int index=0;
    for (char i = 'a'; i <= 'z'; ++i) {
        chars[index++] = i;
    }
    for (char i = 'A'; i <= 'Z'; ++i) {
        chars[index++] = i;
    }
}

string random_string_generator::generate_random_string(int length) {
    srand(time(NULL));
    string s;

    for (int i = 0; i < length; ++i) {
        int random_index = rand()%chars_size;
        s.insert(i, 1, chars[random_index]);
    }
    return s;
}
