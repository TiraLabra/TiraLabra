#ifndef parsenode
#define parsenode

#include <string>
#include <vector>

class ParseNode {

  private:
    std::vector<ParseNode*> children;
    char type;
    std::string data;

  public:
    
    void addChild( ParseNode* addptr );
    void setData( std::string data_par );
    void setType( char type_par );
    bool hasChildren();
    char getType();
    ParseNode* printTree( ParseNode* par );
};

#endif
