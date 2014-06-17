#pragma once
#include <string>
#include <cstring>
#include "../../constants.hpp"
using namespace std;

/*!
    returns the summed hash of the string \a str with a given \a base, meaning that the values of
    \a str will be summed and will be multiplied by mod \a base.
*/
ULL sum_hash(const string & str, ULL base);
/*!
    returns the summed hash of the string \a str with a given \a base, meaning that the values of
    \a str will be summed and will be multiplied by mod \a base. If \a length of the string is not given,
    then it is computed.
*/
ULL sum_hash(const char * str, ULL base, int length = -1);
