#include "nfa.h"
#include "state.h"

State* NFA::getStartstate() {
  return startstate;
}
State* NFA::getEndstate() {
  return endstate;
}
void NFA::setStartstate( State* start_par) {
  startstate = start_par;
}
void NFA::setEndstate( State* end_par ) {
  endstate = end_par;
}
NFA::NFA()  {}
NFA::~NFA() {}
NFA::NFA( State* start_par, State* end_par ) {
  startstate = start_par;
  endstate = end_par;
}
