
#pragma once

/**
 * Structure representing an x and y coordinate on the chessboard
 */
struct coordinate {
    int x, y;
    coordinate() : x(-1), y(-1) {}
    coordinate(int _x, int _y) : x(_x), y(_y) {}
    coordinate(const coordinate &other) : x(other.x), y(other.y) {}

    bool valid() {
        return (x >= 0 && x < 8 && y >= 0 && y < 8);
    }
};

/**
 * Structure representing a move from one coordinate to another
 */
struct cmove {
    coordinate from, to;

    cmove() {}
    cmove(int x1, int y1, int x2, int y2) :
        from(x1,y1), to(x2,y2) {}
};

/**
 * Structure representing a single item in the list
 */
template <class T> struct listItem {
    T item;
    listItem<T> *next;
    listItem<T> *prev;

    listItem(T item_) : item(item_), next(0), prev(0) {
    }
};

/**
 * Implement a linked list of the given type
 */
template <class T> struct linkedList {
    listItem<T> *root, *last;
    int size;

    linkedList() : root(0), size(0) {
    }

    ~linkedList() {
        freeList();
    }
    
    void freeList() {
        if (!root || !size) return;
        
        listItem<T> *next = root;
        while (next != NULL) {
            listItem<T> *item = next;
            next = next->next;
            root = next;
            delete item;
        }
        size = 0;
    }


    /**
     * Return item at the given index from the list
     * 
     * @param idx index of item
     * 
     * @return T 
     */
    T operator [](int idx) {
        listItem<T> *ptr = root; 
        while (idx-- > 0 && ptr != NULL)
            ptr = ptr->next;
        if (idx <= 0 && ptr != NULL)
            return ptr->item;
        return T();
    }

    /**
     * Find pointer to the last item on the list.
     * 
     * @return listItem<T>* 
     */
    listItem<T> *findLast() {
        listItem<T> *ptr = root; 
        if (ptr == NULL) 
            return ptr;

        while (ptr->next != NULL)
            ptr = ptr->next;

        return ptr;
    }

    /**
     * Add new item to list
     *  
     * @param item 
     */
    void add(T item) {
        listItem<T> *newItem = new listItem<T>(item);
        add(newItem);
    }

    /**
     * Add new listItem to the end of the list
     * 
     * @param newItem 
     */
    void add(listItem<T> *newItem) {
        if (root == NULL) {
            // if we don't have a root, initialize list
            root = newItem;
        }
        else {
            last->next = newItem;
            newItem->prev = last;
        }
        last = newItem;
        size++;
    }

    /**
     * Delete item from list
     */
    void del(listItem<T> *item) {
        if (item == NULL) 
            return;

        listItem<T> *next = item->next, *prev = item->prev;
        if (prev) prev->next = next;
        if (next) next->prev = prev;

        size--;
        delete item;
    }

};

/**
 * Tree structure that stores child nodes in a linked list
 */ 
template <class T> struct tree {
    T item;
    tree<T> *parent;
    linkedList< tree<T>* > *children;

    tree() {
        parent = this;
        children = new linkedList< tree<T>* >();
    }

    tree(T _item) {
        item = _item;
        parent = this;
        
        children = new linkedList< tree<T>* >();
    }

    ~tree() {
        freeTree();
    }

    /**
     * Free up the tree and all its nodes
     */
    void freeTree() {
        if (!children) return;
        for (int i = 0; i < children->size; i++)
            (*children)[i]->freeTree();
        for (int i = 0; i < children->size; i++)
            delete (*children)[i];
        delete children;
        children = 0;
    }

    /**
     * insert new item into tree
     * 
     * @param newItem 
     */
    void insert(T newItem) {
        tree<T>* newNode = new tree<T>(newItem);
        insert(newNode);
    }

    /**
     * insert new node item into tree
     * 
     * @param newNode 
     */
    void insert(tree<T> *newNode) {
        newNode->parent = this;
        children->add(newNode);
    }

    /**
     * remove node from tree
     * 
     * @param node 
     */
    void removeNode(tree<T> *node) {
        children->del(node);
    }
};

