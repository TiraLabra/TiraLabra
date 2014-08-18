package builder

import (
	"fmt"

	"testing"
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

func getMockByteStream(data string) chan byte {
	byteStream := make(chan byte)
	go func() {
		for i := 0; i < len(data); i++ {
			byteStream <- data[i]
		}
		close(byteStream)
	}()
	return byteStream
}
