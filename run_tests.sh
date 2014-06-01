g++ -Wall -g  -c "CodeBlocks project/algorithms/hashing/bernstein.cpp" -o "CodeBlocks project/obj/Debug/algorithms/hashing/bernstein.o"
g++ -Wall -g  -c "CodeBlocks project/algorithms/hashing/hashing.cpp" -o "CodeBlocks project/obj/Debug/algorithms/hashing/hashing.o"
g++ -Wall -g  -c "CodeBlocks project/algorithms/hashing/sum_hashing.cpp" -o "CodeBlocks project/obj/Debug/algorithms/hashing/sum_hashing.o"
g++ -Wall -g  -c "CodeBlocks project/algorithms/naive.cpp" -o "CodeBlocks project/obj/Debug/algorithms/naive.o"
g++ -Wall -g  -c "CodeBlocks project/algorithms/rabin_karp.cpp" -o "CodeBlocks project/obj/Debug/algorithms/rabin_karp.o"
g++ -Wall -g  -c "CodeBlocks project/algorithms/z_algorithm.cpp" -o "CodeBlocks project/obj/Debug/algorithms/z_algorithm.o"
rm -f "CodeBlocks project/bin/Debug/libStringAlgorithmsStaticLib.a"
ar -r -s "CodeBlocks project/bin/Debug/libStringAlgorithmsStaticLib.a" "CodeBlocks project/obj/Debug/algorithms/hashing/bernstein.o" "CodeBlocks project/obj/Debug/algorithms/hashing/hashing.o" "CodeBlocks project/obj/Debug/algorithms/hashing/sum_hashing.o" "CodeBlocks project/obj/Debug/algorithms/naive.o" "CodeBlocks project/obj/Debug/algorithms/rabin_karp.o" "CodeBlocks project/obj/Debug/algorithms/z_algorithm.o"

rm -r "testing/obj"
mkdir "testing/obj"
g++ -Wall -fexceptions -g  -c "testing/naive.cpp" -o "testing/obj/naive.o"
g++ -Wall -fexceptions -g  -c "testing/test_suite.cpp" -o "testing/obj/test_suite.o"
g++ -Wall -fexceptions -g  -c "testing/z_algorithm_tests.cpp" -o "testing/obj/z_algorithm_tests.o"
g++ -Wall -fexceptions -g  -c "testing/rabin_karp_test.cpp" -o "testing/obj/rabin_karp_test.o"
g++  -o "testing/testing" "testing/obj/naive.o" "testing/obj/test_suite.o" "testing/obj/z_algorithm_tests.o" "testing/obj/rabin_karp_test.o"  -lgtest -lpthread  "CodeBlocks project/bin/Debug/libStringAlgorithmsStaticLib.a"
cd testing
./testing

