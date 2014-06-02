#include "parsenode.h"
#include <string>
#include <iostream>

void ParseNode::addChild( ParseNode* addptr ) {
  children.push_back( addptr );
}
void ParseNode::setData( std::string data_par ) {
  data = data_par;
}
void ParseNode::setType( char type_par ) {
  type = type_par;
}
bool ParseNode::hasChildren() {
  return children.empty();
}
char ParseNode::getType() {
  return type;
}
ParseNode* ParseNode::printTree( ParseNode* par ) {
  std::cout << par->getType() << std::endl; 
  if( par->hasChildren() ) {
    for( int i=0; i < par->children.size(); i++ ) {
      children[i]->printTree(par);
    }
  }
}
 
