package builder

import (
	"fmt"
	"github.com/golddranks/TiraLabra/src/trie"
	"reflect"
	"testing"
	"time"
)

var freqClassValues = []struct {
	in  int
	out int
}{
	{1, 0},
	{2, 1},
	{3, 2},
	{4, 3},
	{5, 3},
	{6, 4},
	{7, 4},
	{8, 4},
	{9, 5},
	{10, 5},
	{11, 5},
	{12, 5},
	{13, 6},
	{14, 6},
	{15, 6},
	{16, 6},
	{17, 6},
	{18, 6},
	{19, 6},
	{20, 6},
	{21, 7},
}

// Sadly, the functions are bijective only until 5. (Because of the float -> int conversion.)
func TestFreqClassToFreq(t *testing.T) {
	for class := FreqClass(0); class < FreqClass(48); class++ {
		if FreqToFreqClass(FreqClassToMinFreq(class)) != class {
			fmt.Println("n=", class, "\t", class, "!=", FreqToFreqClass(FreqClassToMinFreq(class)), "(FreqClassToFreq(", class, ") == ", FreqClassToMinFreq(class), ")")
			t.FailNow()
		} else {
			//	fmt.Println("n=", class, "\t", class, "==", FreqToFreqClass(FreqClassToMinFreq(class)), "(FreqClassToFreq(", class, ") == ", FreqClassToMinFreq(class), ")")
		}
	}
}

func TestFreqToFreqClass(t *testing.T) {
	for _, freq := range freqClassValues {
		if FreqToFreqClass(freq.in) != FreqClass(freq.out) {
			fmt.Println("freq.in", freq.in, "freq.out", freq.out, "(FreqToFreqClass(", freq.in, ") == ", FreqToFreqClass(freq.in), ")")
			t.FailNow()
		}
	}
}

func getByteStream(data string) chan byte {
	byteStream := make(chan byte)
	go func() {
		for i := 0; i < len(data); i++ {
			byteStream <- data[i]
		}
		close(byteStream)
	}()
	return byteStream
}

// First of all, there is only 4 different n-grams here. aaaa, aaa, aa and a.
var NGramStatsShouldBe1 = [MaxDepth + 1][FreqClasses]LangTable{
	[FreqClasses]LangTable{ // AllGrams
		LangTable{4, 4}, // freq 1 = 1x{aaaa}
		LangTable{3, 3}, // freq 2 = 1x{aaa, aaa}
		LangTable{2, 2}, // freq 3 = 1x{aa, aa, aa}
		LangTable{1, 1}, // freq 4 = 1x{a, a, a, a}
	},
	[FreqClasses]LangTable{ // 1-grams
		LangTable{1, 1},
		LangTable{1, 1},
		LangTable{1, 1},
		LangTable{1, 1}, // freq 4 = 1x{a, a, a, a}
	},
	[FreqClasses]LangTable{ // 2-grams
		LangTable{1, 1},
		LangTable{1, 1},
		LangTable{1, 1}, // freq 3 =1x{aa, aa, aa}
		LangTable{0},
	},
	[FreqClasses]LangTable{ // 3-grams
		LangTable{1, 1},
		LangTable{1, 1}, // freq 2 = 1x{aaa, aaa}
		LangTable{0},
		LangTable{0},
	},
	[FreqClasses]LangTable{ // 4-grams
		LangTable{1, 1}, // freq 1 = 1x{aaaa}
		LangTable{0},
		LangTable{0},
		LangTable{0},
	},
}

func TestBuilderStatsA(t *testing.T) {
	for i := 0; i < len(NGramStatsShouldBe1); i++ {
		for j := 0; j < len(NGramStatsShouldBe1[0]); j++ {
			if NGramStatsShouldBe1[i][j] == nil {
				NGramStatsShouldBe1[i][j] = LangTable{0}
			}
		}
	}
	startTime = time.Now()
	byteStream := getByteStream("@enaaaa")
	Dict = trie.NewNode()           // "dict" is the trie containing all the n-grams
	langTagToIndex = trie.NewNode() // "langTagToIndex" is a trie that converts to langTags to langIndexes
	builder(byteStream)
	if !reflect.DeepEqual(NGramStatsShouldBe1, NGramStats) {
		fmt.Println(NGramStatsShouldBe1)
		fmt.Println("Should be ↑ vs. actually is ↓")
		fmt.Println(NGramStats)
		t.Fail()
	}
}
