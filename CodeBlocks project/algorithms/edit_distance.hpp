#ifndef EDIT_DISTANCE_HEADER
#define EDIT_DISTANCE_HEADER

#include <cstring>
#include <string>
#include <cstdlib>
using namespace std;


class edit_distance {
    private:
    int add_cost;
    int remove_cost;
    int replace_cost;
    //int dynamic_programming_array[100][100];
    int ** dynamic_programming_array=NULL;
    public:
    edit_distance(int add_cost, int remove_cost, int replace_cost);
    ~edit_distance();
    int get_levenstein_distance(const string & str_a, const string & str_b);
    int get_levenstein_distance(const char * str_a, const char * str_b, int str_a_len=-1, int str_b_len=-1);
};

#endif // EDIT_DISTANCE_HEADER
