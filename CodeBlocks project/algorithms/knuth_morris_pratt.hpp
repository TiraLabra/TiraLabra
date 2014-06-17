#ifndef KNUTH_MORRIS_PRATT
#define KNUTH_MORRIS_PRATT

#include <cstdlib>
#include <cstring>
#include <string>
using namespace std;

int * precompute_transition(const char * needle, int needle_length);

#endif // KNUTH_MORRIS_PRATT
