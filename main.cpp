#include <iostream>
#include "LZW.h"
#include "linkedlist.h"

int main() {
/* 
  LZW testi("helppotesti.txt");
  if( testi.encode() == -1 ){
    std::cout << "erhe\n";
    return 0;
  }

  if( testi.decode() == -1 ){
    std::cout << "shit\n";
    return 0;
  }
 */ 
  LinkedList<int>* a = new LinkedList<int>();

  a->push(3);
  std::cout << a->getPos(0) << std::endl;
  std::cout << a->getPos(4) << std::endl;




  return 0;
}
