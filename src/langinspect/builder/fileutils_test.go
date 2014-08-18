package builder

import (
	"fmt"
	"testing"
)

func TestGetFileReaders(t *testing.T) {
	frChan := getFileReaders("testdir")
	if frChan == nil {
		fmt.Println("Returned channel is nil!")
		t.Fail()
	}
	nested := <-frChan
	fr := <-frChan
	if fr == nil {
		fmt.Println("Received pointer is nil!")
		t.Fail()
	}
	if nested == nil {
		fmt.Println("Received pointer is nil!")
		t.Fail()
	}

	c, err := fr.ReadByte()
	if err != nil {
		fmt.Println("Error while reading!")
		t.Fail()
	}

	if c != 'a' {
		fmt.Println("Wrong content, should be 'a'!")
		t.Fail()
	}

	c, err = fr.ReadByte()
	if err == nil {
		fmt.Println("Should have got error!")
		t.Fail()
	}
	c, err = nested.ReadByte()
	if err != nil {
		fmt.Println("Error while reading!")
		t.Fail()
	}

	if c != 'b' {
		fmt.Println("Wrong content, should be 'b'!")
		t.Fail()
	}

	c, err = nested.ReadByte()
	if err == nil {
		fmt.Println("Should have got error!")
		t.Fail()
	}

	fr, ok := <-frChan
	if ok {
		fmt.Println("Channel should be closed!")
		t.Fail()
	}
}

func TestStreamBytes(t *testing.T) {
	byteChan := streamBytes("testdir")
	if byteChan == nil {
		fmt.Println("Returned channel is nil!")
		t.Fail()
	}
	c := <-byteChan
	if c != 'b' {
		fmt.Println("Wrong content, should be 'b', was", c)
		t.Fail()
	}
	c = <-byteChan
	if c != 'a' {
		fmt.Println("Wrong content, should be 'a', was", c)
		t.Fail()
	}
	c, ok := <-byteChan
	if ok {
		fmt.Println("Channel should be closed!")
		t.Fail()
	}
}
