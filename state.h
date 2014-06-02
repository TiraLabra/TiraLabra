#ifndef state
#define state

#include <map>

class State {

  private:
    bool endstate;
    bool startstate;

    std::map<char,State*> arrows;

  public:

    bool getEndstate();
    bool getStartsate();
    void setEndstate( bool ends_par );
    void setStartsate( bool start_par );

    State* getNextState( char input );
    void addArrow( char input, State* state );

    State();
    State( bool end, bool start, std::map<char, State*> connections );
    ~State();

};


#endif
