package builder

import (
	"fmt"
	"github.com/golddranks/TiraLabra/src/orderedmap/naivetrie"
	"reflect"
	"testing"
)

// First of all, there is only 4 different n-grams here. aaaa, aaa, aa and a.
var NGramStatsShouldBe1 = [][]NGramStatsType{
	// AllLanguages
	[]NGramStatsType{
		NGramStatsType{
			[MaxFreqClasses]int{ // AllGrams
				4, // freq 1 = 1x{aaaa}
				3, // freq 2 = 1x{aaa, aaa}
				2, // freq 3 = 1x{aa, aa, aa}
				1, // freq 4 = 1x{a, a, a, a}
			},
			[MaxFreqClasses]int{ // 1-grams
				1,
				1,
				1,
				1, // freq 4 = 1x{a, a, a, a}
			},
			[MaxFreqClasses]int{ // 2-grams
				1,
				1,
				1, // freq 3 =1x{aa, aa, aa}
				0,
			},
			[MaxFreqClasses]int{ // 3-grams
				1,
				1, // freq 2 = 1x{aaa, aaa}
				0,
				0,
			},
			[MaxFreqClasses]int{ // 4-grams
				1, // freq 1 = 1x{aaaa}
				0,
				0,
				0,
			}},
	},
	// the first language
	[]NGramStatsType{
		NGramStatsType{
			[MaxFreqClasses]int{ // AllGrams
				4, // freq 1 = 1x{aaaa}
				3, // freq 2 = 1x{aaa, aaa}
				2, // freq 3 = 1x{aa, aa, aa}
				1, // freq 4 = 1x{a, a, a, a}
			},
			[MaxFreqClasses]int{ // 1-grams
				1,
				1,
				1,
				1, // freq 4 = 1x{a, a, a, a}
			},
			[MaxFreqClasses]int{ // 2-grams
				1,
				1,
				1, // freq 3 =1x{aa, aa, aa}
				0,
			},
			[MaxFreqClasses]int{ // 3-grams
				1,
				1, // freq 2 = 1x{aaa, aaa}
				0,
				0,
			},
			[MaxFreqClasses]int{ // 4-grams
				1, // freq 1 = 1x{aaaa}
				0,
				0,
				0,
			}},
	},
}

func TestBuilderStatsA(t *testing.T) {
	stats = initStats()
	byteStream := getMockByteStream("@enaaaa")
	Dict = trie.NewNode()           // "dict" is the trie containing all the n-grams
	langTagToIndex = trie.NewNode() // "langTagToIndex" is a trie that converts to langTags to langIndexes
	builder(byteStream)
	if !reflect.DeepEqual(NGramStatsShouldBe1, stats.nGramStats) {
		fmt.Println(NGramStatsShouldBe1)
		fmt.Println("Should be ↑ vs. actually is ↓ (if they look like the same, double check the types!)")
		fmt.Println(stats.nGramStats)
		t.Fail()
	}
}
