#pragma once
#include <string>
#include <cstring>
#include "../../constants.hpp"
using namespace std;
ULL sum_hash(const string & str, ULL base);
ULL sum_hash(const char * str, ULL base, int length = -1);
