#pragma once

#include <cstring>
#include <string>
#include <iostream>
#include "../constants.hpp"
#include "hashing/hashing.hpp"
using namespace std;

int rabin_karp_search(const string & haystack, const string & needle, int start = 0);

int rabin_karp_search(const char * haystack, const char * needle,
                      int start = 0, int haystack_length = -1, int needle_length = -1);
