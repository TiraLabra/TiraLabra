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
  
  return 0;
}
