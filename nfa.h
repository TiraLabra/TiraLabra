#ifndef nfa
#define nfa

#include "state.h"

class NFA {
  
  private:
    State* startstate;
    State* endstate;

  public:
    State* getStartstate();
    State* getEndstate();
    void setStartstate( State* start_par);
    void setEndstate( State* end_par );

    NFA( State* start_par, State* end_par );
    NFA();
    ~NFA();

};

#endif
