#include <string>
#include "nfa.h"
#include "state.h"
#include "parsenode.h"
#include "parser.h"

int main( void ) {

  std::string str = "(* (@ afaf) )";

  Parser parssi(str); 
  parssi.makeTree();
    


  return 0;

}
