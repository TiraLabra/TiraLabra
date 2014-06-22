g++ -Wall -g  -c "CodeBlocks project/algorithms/edit_distance.cpp" -o "CodeBlocks project/obj/Debug/algorithms/edit_distance.o"
g++ -Wall -g  -c "CodeBlocks project/algorithms/hashing/bernstein.cpp" -o "CodeBlocks project/obj/Debug/algorithms/hashing/bernstein.o"
g++ -Wall -g  -c "CodeBlocks project/algorithms/hashing/hashing.cpp" -o "CodeBlocks project/obj/Debug/algorithms/hashing/hashing.o"
g++ -Wall -g  -c "CodeBlocks project/algorithms/hashing/sum_hashing.cpp" -o "CodeBlocks project/obj/Debug/algorithms/hashing/sum_hashing.o"
g++ -Wall -g  -c "CodeBlocks project/algorithms/knuth_morris_pratt.cpp" -o "CodeBlocks project/obj/Debug/algorithms/knuth_morris_pratt.o"
g++ -Wall -g  -c "CodeBlocks project/algorithms/naive.cpp" -o "CodeBlocks project/obj/Debug/algorithms/naive.o"
g++ -Wall -g  -c "CodeBlocks project/algorithms/rabin_karp.cpp" -o "CodeBlocks project/obj/Debug/algorithms/rabin_karp.o"
g++ -Wall -g  -c "CodeBlocks project/algorithms/z_algorithm.cpp" -o "CodeBlocks project/obj/Debug/algorithms/z_algorithm.o"
g++ -Wall -g  -c "CodeBlocks project/structures/naive_suffix_tree_builder.cpp" -o "CodeBlocks project/obj/Debug/structures/naive_suffix_tree_builder.o"
g++ -Wall -g  -c "CodeBlocks project/structures/suffix_tree.cpp" -o "CodeBlocks project/obj/Debug/structures/suffix_tree.o"
g++ -Wall -g  -c "CodeBlocks project/structures/suffix_trie.cpp" -o "CodeBlocks project/obj/Debug/structures/suffix_trie.o"
g++ -Wall -g  -c "CodeBlocks project/structures/suffix_array.cpp" -o "CodeBlocks project/obj/Debug/structures/suffix_array.o"
g++ -Wall -g  -c "CodeBlocks project/structures/suffix_array_builder.cpp" -o "CodeBlocks project/obj/Debug/structures/suffix_array_builder.o"
g++ -Wall -g  -c "CodeBlocks project/structures/suffix_trie_builder.cpp" -o "CodeBlocks project/obj/Debug/structures/suffix_trie_builder.o"
g++ -Wall -g  -c "CodeBlocks project/structures/ukkonen_suffix_tree_builder.cpp" -o "CodeBlocks project/obj/Debug/structures/ukkonen_suffix_tree_builder.o"
rm -f "CodeBlocks project/bin/Debug/libStringAlgorithmsStaticLib.a"
ar -r -s "CodeBlocks project/bin/Debug/libStringAlgorithmsStaticLib.a" "CodeBlocks project/obj/Debug/algorithms/edit_distance.o" "CodeBlocks project/obj/Debug/algorithms/hashing/bernstein.o" "CodeBlocks project/obj/Debug/algorithms/hashing/hashing.o" "CodeBlocks project/obj/Debug/algorithms/hashing/sum_hashing.o" "CodeBlocks project/obj/Debug/algorithms/knuth_morris_pratt.o" "CodeBlocks project/obj/Debug/algorithms/naive.o" "CodeBlocks project/obj/Debug/algorithms/rabin_karp.o" "CodeBlocks project/obj/Debug/algorithms/z_algorithm.o" "CodeBlocks project/obj/Debug/structures/naive_suffix_tree_builder.o" "CodeBlocks project/obj/Debug/structures/suffix_tree.o" "CodeBlocks project/obj/Debug/structures/suffix_array.o" "CodeBlocks project/obj/Debug/structures/suffix_array_builder.o" "CodeBlocks project/obj/Debug/structures/suffix_trie.o" "CodeBlocks project/obj/Debug/structures/suffix_trie_builder.o" "CodeBlocks project/obj/Debug/structures/ukkonen_suffix_tree_builder.o"

rm -r "testing/obj"
rm -r "testing/bin"
mkdir "testing/obj"
mkdir "testing/obj/Debug"
mkdir "testing/bin"
mkdir "testing/bin/Debug"
g++ -Wall -fexceptions -g  -c "testing/edit_distance_tests.cpp" -o testing/obj/Debug/edit_distance_tests.o
g++ -Wall -fexceptions -g  -c "testing/knuth_morris_pratt_tests.cpp" -o testing/obj/Debug/knuth_morris_pratt_tests.o
g++ -Wall -fexceptions -g  -c "testing/naive.cpp" -o testing/obj/Debug/naive.o
g++ -Wall -fexceptions -g  -c "testing/rabin_karp_test.cpp" -o testing/obj/Debug/rabin_karp_test.o
g++ -Wall -fexceptions -g  -c "testing/suffix_trie_tests.cpp" -o testing/obj/Debug/suffix_trie_tests.o
g++ -Wall -fexceptions -g  -c "testing/suffix_array_test.cpp" -o testing/obj/Debug/suffix_array_test.o
g++ -Wall -fexceptions -g  -c "testing/test_suite.cpp" -o testing/obj/Debug/test_suite.o
g++ -Wall -fexceptions -g  -c "testing/z_algorithm_tests.cpp" -o testing/obj/Debug/z_algorithm_tests.o
g++  -o testing/bin/Debug/testing testing/obj/Debug/edit_distance_tests.o testing/obj/Debug/knuth_morris_pratt_tests.o testing/obj/Debug/naive.o testing/obj/Debug/suffix_array_test.o testing/obj/Debug/rabin_karp_test.o testing/obj/Debug/suffix_trie_tests.o testing/obj/Debug/test_suite.o testing/obj/Debug/z_algorithm_tests.o  -lgtest -lpthread  "CodeBlocks project/bin/Debug/libStringAlgorithmsStaticLib.a" "CodeBlocks project/bin/Debug/libStringAlgorithmsStaticLib.a"
cd testing/bin/Debug/
./testing

