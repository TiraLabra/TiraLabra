#include "linkednode.h"


template <class T>LinkedNode<T>::LinkedNode() {}

template <class T>LinkedNode<T>::LinkedNode( T data_par, LinkedNode<T>*  next_par ) {

  data = data_par;
  next = next_par;
}
template <class T>LinkedNode<T>::~LinkedNode() {
  if( next != this ) {
    delete next;
  }
}
template <class T> T LinkedNode<T>::getData() {
  return data;
}

template <class T> LinkedNode<T>* LinkedNode<T>::getNext() {
  if( next == this ) {
    return 0;
  }
  return next;
}

template class LinkedNode<int>;
