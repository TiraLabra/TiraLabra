#pragma once

#include <cstring>
#include <string>
#include <vector>
#include <cstdlib>
using namespace std;


bool z_algo_compare(const char * haystack, const char * needle, int index_a,
int index_b, int haystack_length, int needle_length);

vector<int> z_algo_get_positions(const string & haystack, const string & needle, int start=0,
int upper_bound_cnt=-1);

vector<int> z_algo_get_positions(const char * haystack, const char * needle,
 int start=0, int haystack_length=-1, int needle_length=-1, int upper_bound_cnt=-1);

void extend_right(int &right, int left, int total_length, const char * haystack, const char * needle,
int haystack_length, int needle_length, int start);
