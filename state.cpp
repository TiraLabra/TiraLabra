#include "state.h"

#include <map>
#include <utility>

bool State::getEndstate() {
  return endstate;
}
bool State::getStartsate() {
  return startstate;
}
void State::setEndstate( bool ends_par ) {
  endstate = ends_par;
}
void State::setStartsate( bool start_par ) {
  startstate = start_par;
}

State* State::getNextState( char input ) {
  return arrows[input];
}
void State::addArrow( char input, State* state_par ) {
  std::pair<char, State*> arrow( input, state_par );
  arrows.insert(arrow);
}

State::State() {}
State::State( bool end, bool start, std::map<char, State*> connections ) {
  endstate = end;
  startstate = start;
  arrows = connections;
}
