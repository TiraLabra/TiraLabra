
#pragma once

/**
 * Structure representing an x and y coordinate on the chessboard
 */
struct coordinate {
    int x, y;
    coordinate(int _x, int _y) : x(_x), y(_y) {}

    bool valid() {
        return (x >= 0 && x < 8 && y >= 0 && y < 8);
    }
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
    listItem<T> *root;
    int size;

    linkedList() : root(0), size(0) {
    }

    T operator [](int idx) {
        listItem<T> *ptr = root; 
        while (idx-- > 0 && ptr != NULL)
            ptr = ptr->next;
        if (idx <= 0 && ptr != NULL)
            return ptr->item;
        return 0;
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
     * Add item to list
     */
    void add(T item) {
        listItem<T> *newItem = new listItem<T>(item);
        add(newItem);
    }

    void add(listItem<T> *newItem) {
        if (root == NULL) {
            // if we don't have a root, initialize list
            root = newItem;
        }
        else {
            // find last item on list and append new item there
            listItem<T> *last = findLast();
            last->next = newItem;
            newItem->prev = last;
        }
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

template <class T> struct treeNode {
    treeNode<T> *parent;
    linkedList< treeNode<T> > *children;


};

template <class T> struct tree : treeNode<T> {
    treeNode<T> *root;

    void insert(T item) {
    }
};


