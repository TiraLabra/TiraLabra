#pragma once

#include "../constants.hpp"
#include <cstring>
#include <string>
#include <iostream>

using namespace std;

int naive_search(const string & haystack, const string & needle, int start = 0);

int naive_search(const char * haystack, const char * needle, int start = 0, int haystack_length = -1, int needle_length = -1);
