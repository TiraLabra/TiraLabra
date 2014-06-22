#ifndef linkedlist
#define linkedlist
#include "linkednode.h"


template <class T> class LinkedList { 
  
  private:
    LinkedNode<T>* first;
    LinkedNode<T>* last;
    int size;
    
  public:
    LinkedList();
    ~LinkedList();
    void push( T toFront ) ;
    T getPos( int pos ); 
    bool isEmpty() ;
    int getSize();
};



#endif
