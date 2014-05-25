#include "z_algorithm_tests.hpp"
#include <iostream>

random_string_generator * gen = new random_string_generator();

TEST(Z_ALGORITHM_TEST,NORMAL_TEST) {

    cout << gen->generate_random_string(250);
}
