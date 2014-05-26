#include <iostream>
#include <cstdio>
#include <string>
#include <cstdlib>
#include <ctime>
#include <vector>
using namespace std;

class random_string_generator {
    private:
    int chars_size;
    char * chars;
    public:
    random_string_generator();
    string generate_random_string(int len);
    string get_random_substring(const string & haystack, int len=-1);
    string generate_random_multi_occur_string(const string & occur, int occur_cnt);
};

string random_string_generator::generate_random_multi_occur_string
    (const string & occur, int occur_cnt) {
    string s;
    for (int i = 0; i < occur_cnt; ++i) {
        int junk_len = rand()%20 + 1;
        string junk = this->generate_random_string(junk_len);
        int pos = rand()%2;
        if (pos == 1) {
            s.append(junk);
            s.append(occur);
        } else {
            s.append(occur);
            s.append(junk);
        }
    }
    return s;
}

random_string_generator::random_string_generator() {
    srand(time(NULL));
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

string random_string_generator::get_random_substring(const string & haystack, int len) {
    int haystack_len = haystack.length();
    if (len <= 0) {
        len = rand()%(haystack_len-2) + 1;
    }
    int s_idx = rand()%(haystack_len-len);
    return haystack.substr(s_idx, len);
}

string random_string_generator::generate_random_string(int length) {

    string s;

    for (int i = 0; i < length; ++i) {
        int random_index = rand()%chars_size;
        s.insert(i, 1, chars[random_index]);
    }
    return s;
}

#define RANDOM_TESTS_COUNT_1Q 10
#define LONG_TESTS_COUNT_1Q 2
#define SPECIAL_TESTS_COUNT_1Q 2

FILE * tests, *answers;

void write_test_1q(const string & haystack, const string & needle) {
    fprintf(tests,"%s\n%s\n",haystack.c_str(),needle.c_str());
    size_t index = haystack.find(needle);
    if (index == string::npos) index = -1;
    fprintf(answers,"%d\n", (int)index);
}

void generate_one_query_tests() {
    tests = fopen("tests1q.in", "w+");
    answers = fopen("tests1q.out", "w+");
    random_string_generator * gen = new random_string_generator();
    fprintf(tests, "%d\n", (RANDOM_TESTS_COUNT_1Q+LONG_TESTS_COUNT_1Q+SPECIAL_TESTS_COUNT_1Q));
    for (int i = 0; i < RANDOM_TESTS_COUNT_1Q; ++i) {
        int hay_len = rand()%1000 + 5;
        int needle_len = rand()%(hay_len-2) + 1;
        string haystack = gen->generate_random_string(hay_len);
        string needle = gen->get_random_substring(haystack, needle_len);
        write_test_1q(haystack, needle);
    }
    for (int i = 0; i < LONG_TESTS_COUNT_1Q; ++i) {
        int hay_len = rand()%100000 + 5;
        int needle_len = rand()%10 + 2;
        string haystack = gen->generate_random_string(hay_len);
        string needle = gen->get_random_substring(haystack, needle_len);
        write_test_1q(haystack, needle);
    }
    string haystack = "varna_helsinki_varbhele";
    string needle = "varb";
    write_test_1q(haystack, needle);
    haystack = "bbaaaabaaaabaaaaaaaaab";
    needle = "aaaaaaaaab";
    write_test_1q(haystack, needle);
    fclose(tests);
    fclose(answers);
}

void write_test_mq(const string & haystack, const string & needle) {
    fprintf(tests,"%s\n%s\n",haystack.c_str(),needle.c_str());
    size_t pos_idx=0;
    vector<int> pos;
    while(true) {
        pos_idx = haystack.find(needle, pos_idx);
        if (pos_idx==string::npos) break;
        pos.push_back((int)pos_idx);
        ++pos_idx;
    }
    fprintf(answers, "%d ", (int)pos.size());
    for (int i = 0; i < (int)pos.size(); ++i) {
        fprintf(answers, "%d ", pos[i]);
    }
    fprintf(answers,"\n");
}

void generate_multi_query_tests() {
    tests = fopen("testsmq.in", "w+");
    answers = fopen("testsmq.out", "w+");
    random_string_generator * gen = new random_string_generator();
    fprintf(tests, "%d\n", (RANDOM_TESTS_COUNT_1Q+LONG_TESTS_COUNT_1Q+SPECIAL_TESTS_COUNT_1Q));
    for (int i = 0; i < RANDOM_TESTS_COUNT_1Q; ++i) {
        string occur = gen->generate_random_string(rand()%20 + 1);
        string total = gen->generate_random_multi_occur_string(occur, rand()%15 + 1);
        write_test_mq(total, occur);
    }
    for (int i = 0; i < LONG_TESTS_COUNT_1Q; ++i) {
        string occur = gen->generate_random_string(rand()%30 + 1);
        string total = gen->generate_random_multi_occur_string(occur, 30);
        write_test_mq(total, occur);
    }
    string total = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
    string occur = "a";
    write_test_mq(total, occur);
    total = "a";
    occur = "aaaaaaa";
    write_test_mq(total, occur);
    fclose(tests);
    fclose(answers);
}

int main()
{
    generate_one_query_tests();
    generate_multi_query_tests();
    return 0;
}
