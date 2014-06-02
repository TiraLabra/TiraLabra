#include <iostream>
#include <string>
#include <queue>
#include "parser.h"
#include "parsenode.h"

Parser::Parser( std::string regex_par) {
  regex = regex_par;
  start = new ParseNode();
}
ParseNode* Parser::parse(ParseNode* cur, std::string regex_par ) {
  
  int next = regex_par.find('('); //seuraava operaatio
  
  if( regex_par[next + 1] == '*' ) {
    ParseNode* dap = new ParseNode();
    dap->setType('*');
    cur->addChild( dap );
    dap->addChild( parse( dap, regex_par.substr(next + 1, std::string::npos) ));    
  }
  if( regex_par[next+1] == '|' ) {
    std::cout << "vähän unionia\n";
    ParseNode* dep = new ParseNode();
    dep->setType('|');
    cur->addChild( dep );
    dep->addChild( parse( dep, regex_par.substr(next + 1, std::string::npos) ));
    int next2 = regex_par.find('(', next+1);
    dep->addChild( parse( dep, regex_par.substr( next2, std::string::npos)));



    // find the second child and add it
  }
  if( regex_par[next+1] == '@') {
    ParseNode* dip = new ParseNode(); 
    dip->setType('@');
    int end = regex_par.find(')');
    dip->setData( regex_par.substr(next + 3, end - next - 3 ));
    cur->addChild( dip );
    return dip;
  }
  return cur;
}
void Parser::makeTree() { 
  parse(start, regex);
  start->printTree(start);
}



















   






