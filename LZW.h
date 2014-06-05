#ifndef lzw
#define lzw

#include <map>
#include <string>
#include <iostream>
#include <fstream>
#include <vector>

class LZW {
  private:
    
    std::map<std::string, unsigned int> dict;
    std::map<unsigned int, std::string> revdict;
    std::fstream toEncode;
    std::fstream toWriteEnc;
    std::fstream toReadEnc;
    std::fstream toWriteDec;
    char* filename;
    char* data; 
    int filelength;
    unsigned int currentCode;
    int writeIndex;
    unsigned int currentEnCode;
  public:
    LZW( char* file);
    void makeInitialDict();
    void printDict();
    void readFile(); 
    int encode();
    int decode();
    void writeCode( unsigned int code );
    unsigned int getCurrentCode();
    std::vector<unsigned int>* codeToBits( unsigned int code );
    void printAsBinary( std::vector<unsigned int>* bin );

    unsigned int bitsToCode(unsigned int first, unsigned int second );
    unsigned int readCode();
};

#endif
