#include <map>
#include <string>
#include <iostream>
#include <fstream>
#include <limits>
#include <math.h>
#include <vector>

#include "LZW.h"

LZW::LZW( char * file_par ) {
 
  filename = file_par;
  writeIndex = 0;
  currentCode = 0;
  readFile();
  makeInitialDict();
  
}
void LZW::makeInitialDict() {
  for( int i = 0; i < 256; i++ ) {
    std::string c(1, i );
    dict.insert( std::pair<std::string,unsigned int>(c,i));
    revdict.insert( std::pair<unsigned int, std::string>(i,c));
    currentCode++;
  }
  currentEnCode = 256;
}
void LZW::printDict() {
  for(std::map<unsigned int, std::string>::iterator it=revdict.begin(); it!=revdict.end(); ++it ) {
    std::cout << it->first << " -> " << it->second << "\n";
  }
}
void LZW::readFile() {
  toEncode.open( filename );  
  
  toEncode.seekg(0, toEncode.end);
  filelength = toEncode.tellg();
  toEncode.seekg(0, toEncode.beg);

  data = new char[filelength];
  toEncode.read( data, filelength);
  toEncode.close();
}

int LZW::encode() {
  toWriteEnc.open( "enco.ded", std::fstream::trunc | std::fstream::in | std::fstream::out ); 
  std::string w(""); 
  for( int i = 0; i < filelength; i++ ) {
    char c = data[i];
    std::string k (1,c);  
    std::string wk = w + k; 

    if( dict.count( wk ) != 0 ) {
      w = wk;
    }
    else {
      dict[wk] = currentCode;
      writeCode( dict[w] );
      currentCode++;
      w = std::string(1,c);
    }
    if( currentCode == 65535 ) {
      return -1;
    }
  }
  toWriteEnc.close();
  return 0;
}

void LZW::writeCode( unsigned int code ) {
  char word1 = 0;
  char word2 = 0;
  std::vector<unsigned int>* bittiesitys = codeToBits( code );
  
  for( int i = 0; i < 16; i++ ) {

    if( i < 8 ) {
      word1 <<=1;
      if( (*bittiesitys)[i] == 1 ) {
        word1 += 1;
      }
    } else {
      word2 <<= 1;
      if( (*bittiesitys)[i] == 1 ) {
        word2 += 1;
      }
    }
  }
  toWriteEnc.put(word1);
  toWriteEnc.put(word2);
  //printAsBinary( bittiesitys );
  delete bittiesitys; 
}

unsigned int LZW::getCurrentCode() {
  return currentCode;
}

std::vector<unsigned int>* LZW::codeToBits( unsigned int code ) {

  std::vector<unsigned int>* asBinary = new std::vector<unsigned int>();
  int remainder = code; 
  if( code == 0 ) {
    asBinary->insert( asBinary->begin(), 0 );
    while( asBinary->size() < 16 ) {
      asBinary->insert( asBinary->begin(), 0);
    }
    return asBinary;
  }
  while( remainder != 0  ) { 
    asBinary->insert( asBinary->begin(), remainder % 2); 
    remainder = remainder / 2;
  }
  if( asBinary->size() < 16 ) {
    while( asBinary->size() < 16 ) {
      asBinary->insert( asBinary->begin(), 0);
    }
  }
//  for( std::vector<int>::iterator it = asBinary->begin(); it != asBinary->end(); ++it ) {
//    std::cout << (*it) ;
//  }
  return asBinary;
}
void LZW::printAsBinary( std::vector<unsigned int>* bin ) {
  for( int i = 0; i < bin->size(); i++ ) {
    std::cout << (*bin)[i];
  }
  std::cout << "\n";
}
int LZW::decode() {
 
  currentCode = 256;
  toReadEnc.open( "enco.ded" );
  toWriteDec.open( "deco.ded", std::fstream::in | std::fstream::out | std::fstream::trunc ); 

  if( !toReadEnc.good() || !toWriteDec.good() ) {
    std::cout << "shit\n";
    return -1;
  }

/*  for( int i = 0; i < 4; i++ ) {
    std::cout << readCode() << " ";
  }
  std::cout << std::endl;
*/
 
  unsigned int k = readCode(); 
  std::string entry("");
  std::string w( revdict[k] ); 
  toWriteDec << w;
  while( toReadEnc.good() ) {
    k = readCode();
    if( !toReadEnc.good() ) {
      return 0;
    }
    if( revdict.count( k ) ) {
      entry = revdict[k];
      toWriteDec << entry;
      std::string toInsert;
      toInsert = w + (std::string(1,entry[0]));
      revdict.insert( std::pair<unsigned int, std::string>( currentCode, toInsert ));
      currentCode++;
      w = entry;
    } else {
      entry = w + w[0];
      toWriteDec << entry;
      revdict.insert( std::pair<unsigned int,std::string>( currentCode, entry ));
      currentCode++;
      w = entry;
    }
  }
  
  //toWriteDec. << "\0";
  toReadEnc.close(); 
  toWriteDec.close();
  
  /*
  unsigned int a = readCode();
  unsigned int b = readCode();
  unsigned int c = readCode();

  std::cout << revdict[a] << std::endl;
  std::cout << revdict[b] << std::endl;
  std::cout << revdict[c] << std::endl;
  */

}

unsigned int LZW::bitsToCode( unsigned int first, unsigned int second ) {

  first <<= 8;
  unsigned int code = first + second;

  return code;


}
unsigned int LZW::readCode() {
  unsigned int first = toReadEnc.get();
  unsigned int second = toReadEnc.get();

  unsigned int code = bitsToCode( first, second ); 

  return code;

}
