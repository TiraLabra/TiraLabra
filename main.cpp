#include <iostream>
#include "LZW.h"


int main() {
 
  LZW testi("helppotesti.txt");
  if( testi.encode() == -1 ){
    std::cout << "erhe\n";
    return 0;
  }

  if( testi.decode() == -1 ){
    std::cout << "shit\n";
    return 0;
  }
  //testi.printDict();  
  //testi.printDict();
/*  if( testi.encode() == -1 ) {
    std::cout << "dict full, aborting\n";
    return 0;
  }
  testi.printDict();
  std::cout << testi.getCurrentCode() << "\n";
*/
/*
  testi.writeCode( 0   );
  testi.writeCode( 112 );
  testi.writeCode( 413 );
  testi.decode();

  std::cout << testi.readCode() << std::endl;  
  std::cout << testi.readCode() << std::endl;  
  std::cout << testi.readCode() << std::endl;  
*/

  return 0;
}
