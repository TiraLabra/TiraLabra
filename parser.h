#ifndef parser
#define parser

#include <string>
#include "state.h"
#include "parsenode.h"

class Parser {

  private:
    std::string regex;
    ParseNode* start;
    bool noMoreChildren( std::string str, int cur );
  public:
    Parser( std::string input );
    ParseNode* parse( ParseNode* cur, std::string regex_par );
    void makeTree();    


};

#endif

