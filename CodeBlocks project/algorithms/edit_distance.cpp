#include "edit_distance.hpp"
#include<iostream>
using namespace std;
edit_distance::edit_distance(int add_cost, int remove_cost, int replace_cost) {
    this->add_cost = add_cost;
    this->remove_cost = remove_cost;
    this->replace_cost = replace_cost;
}

edit_distance::~edit_distance() {
    if (dynamic_programming_array!=NULL) {
        free(*this->dynamic_programming_array);
        free(this->dynamic_programming_array);
    }
}

int edit_distance::get_levenstein_distance(const string & str_a, const string & str_b) {
    return this->get_levenstein_distance(str_a.c_str(), str_b.c_str(), str_a.length(), str_b.length());
}

int edit_distance::get_levenstein_distance(const char * str_a, const char * str_b, int str_a_len, int str_b_len) {
    // chech whether the length of the strings are set since they are optional arguments
    if (str_a_len==-1) {
        str_a_len = strlen(str_a);
    }
    if (str_b_len==-1) {
        str_b_len = strlen(str_b);
    }

    if (this->dynamic_programming_array!=NULL) {
        free(*this->dynamic_programming_array);
        free(this->dynamic_programming_array);
    }
    // initiliaze dynamic programming matrix
    this->dynamic_programming_array = (int **)calloc(str_a_len+1, sizeof(int*));
    for (int i = 0; i <= str_a_len; ++i) {
        this->dynamic_programming_array[i] = (int*)calloc(str_b_len+1, sizeof(int));
    }
    // compute array
    // compute simple case
    for (int i = 0; i<=str_a_len; ++i) {
        this->dynamic_programming_array[i][0] = i*this->remove_cost;
    }
    for (int i = 0; i<=str_b_len; ++i) {
        this->dynamic_programming_array[0][i] = i*this->add_cost;
    }
    for (int i = 1; i<= str_a_len; ++i) {
        for (int j = 1; j<= str_b_len; ++j) {
            if (str_a[i-1] == str_b[j-1]) {
                this->dynamic_programming_array[i][j] = this->dynamic_programming_array[i-1][j-1];
            } else {
                int to_insert = this->dynamic_programming_array[i-1][j] + this->add_cost;
                int to_remove = this->dynamic_programming_array[i][j-1] + this->remove_cost;
                int to_replace = this->dynamic_programming_array[i-1][j-1] + this->replace_cost;
                if (to_insert < to_remove) {
                    if (to_insert < to_replace) {
                        this->dynamic_programming_array[i][j] = to_insert;
                    } else {
                        this->dynamic_programming_array[i][j] = to_replace;
                    }
                } else if (to_remove <= to_insert) {
                    if (to_remove < to_replace) {
                        this->dynamic_programming_array[i][j] = to_remove;
                    } else {
                        this->dynamic_programming_array[i][j] = to_replace;
                    }
                }
            }
        }
    }
    return this->dynamic_programming_array[str_a_len][str_b_len];
}

