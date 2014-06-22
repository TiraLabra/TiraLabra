#ifndef linkednode
#define linkednode

template <class T> class LinkedNode {

  private:
    T data;
    LinkedNode<T>* next;

  public:
    LinkedNode();
    LinkedNode( T data_par, LinkedNode<T>* next_par );
    ~LinkedNode();
    T getData();
    LinkedNode<T>* getNext();
  


};

#endif
