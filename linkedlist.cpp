#include "linkedlist.h"
#include "linkednode.h"

template <class T> LinkedList<T>::LinkedList() {

  first = 0;
  last = 0;
  size = 0;
}

template <class T> LinkedList<T>::~LinkedList() {
  if( !isEmpty() ) {
    delete first;   
  }
}

template <class T> void LinkedList<T>::push( T toFront ) {
  if( isEmpty() ) {
    LinkedNode<T>* toPush = new LinkedNode<T>( toFront, toPush );
    first = toPush;
    last = toPush;
    size++;
  }
  else {
    LinkedNode<T>* toPush = new LinkedNode<T>( toFront, first );
    first = toPush;
    size++;
  }
}

template <class T> T LinkedList<T>::getPos( int pos ) {

  if( pos > size ) {
    return 0;
  }
  LinkedNode<T>* counter = first;
  int i = 0;
  while( i < pos ) {
    counter = counter->getNext();   
  }
  return counter->getData();   
}
template <class T> bool LinkedList<T>::isEmpty() {
  if( first == 0 && last == 0 ) {
    return true;
  }
  else {
    return false;
  }
}
template <class T> int LinkedList<T>::getSize() {
  return size;
}
template class LinkedList<int>;

