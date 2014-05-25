#pragma once

#include <string>
#include <ctime>
#include <cstdlib>
using namespace std;

class random_string_generator {
    private:
    int chars_size;
    char * chars;
    public:
    random_string_generator();
    string generate_random_string(int length);
};
