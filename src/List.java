/*
* Chandler Hawkins
* chmhawki
* pa1
* */

import java.io.*;
import java.util.Scanner;

public class List
{
    Object mine;
    //List will be made of objects of Node class
    private class Node
    {
        //fields
        public Node next;
        public Node previous;

        public int data;

        //constructor for Node class: takes String from array
        public Node(int data)
        {
            this.data = data;
        }

        public String toString()
        {
            return String.valueOf(data);
        }
    }

/*///////////////////////////////////////////////////////////
Below is the code for operating on the Node objects
 *///////////////////////////////////////////////////////////

    // Fields for List class
    private Node firstLink;
    private Node lastLink;
    private Node cursor;
    private int length;
    private int index;

    // Constructor
    List()
    {
        firstLink = lastLink = cursor = null;
        length = 0;
        index = -1;
    }// Creates a new empty list.

    // Access functions
    int length()
    {
        return this.length;
    }// Returns the number of elements in this List.

    int index()
    {
        if (cursor == null)
        {
            return -1;
        }

        return index;
    }
    // If cursor is defined, returns the index of the cursor element,
    // otherwise returns -1.

    int front()
    {
        if (length > 0)
        {
            return firstLink.data;
        }
        else
            return -1;
    }// Returns front element. Pre: length()>0

    int back()
    {
        if (length() > 0)
            return lastLink.data;
        return -1;
    }// Returns back element. Pre: length()>0

    int get()
    {
        if (length > 0 && index >= 0)
        {
            return cursor.data;
        }
        else
            return -1;
    }// Returns cursor element. Pre: length()>0, index()>=0

    boolean equals(List L)
    {
        if (this.length != L.length) //if lengths not equal, return false
            return false;
        if (this.length == 0 && L.length == 0) //if both lists empty, equal by default
            return true;

        //create temporary iterators so user's cursor not altered
        Node it1 = this.firstLink;
        Node it2 = L.firstLink;
        index = 0;
        while (index < length)
        {
            if (it1.data != it2.data)
                return false;
            it1 = it1.next;
            it2 = it2.next;
            index++;
        }
        return true;
    }
    // Returns true if and only if this List and L are the same
    // integer sequence. The states of the cursors in the two Lists
    // are not used in determining equality.
// Manipulation procedures

    void clear()
    {
        firstLink = null;
        lastLink = null;
        cursor = null;
        length = 0;
        index = -1;
    }// Resets this List to its original empty state.

    void moveFront()
    {
        if (length > 0)
        {
            cursor = firstLink;
            index = 0;
        }
        return;
    }// If List is non-empty, places the cursor under the front element,
    // otherwise does nothing.

    void moveBack()
    {
        if (length > 0)
        {
            cursor = lastLink;
            index = length - 1;
        }
        return;
    }// If List is non-empty, places the cursor under the back element,

    // otherwise does nothing.
    void movePrev()
    {
        if (cursor == null)
        {
            return;
        }
        else if (index == 0)
        {
            cursor = null;
            index = -1;
        }

        else
        {
            cursor = cursor.previous;
            --index;
        }

    }// If cursor is defined and not at front, moves cursor one step toward
    // front of this List, if cursor is defined and at front, cursor becomes
    // undefined, if cursor is undefined does nothing.

    void moveNext()
    {
        if (cursor == null)
        {
            return;
        }
        else if (cursor == lastLink)
        {
            cursor = null;
            index = -1;
        }
        else
        {
            cursor = cursor.next;
            index++;
        }

    }// If cursor is defined and not at back, moves cursor one step toward
    // back of this List, if cursor is defined and at back, cursor becomes
    // undefined, if cursor is undefined does nothing.

    void prepend(int data)
    {
        Node newNode = new Node(data);
        if (length == 0)
        {
            lastLink = firstLink = newNode;
        }
        else
        {
            firstLink.previous = newNode;
            newNode.next = firstLink;
            newNode.previous = null;

            firstLink = newNode;
        }

        if (index != -1)
        {
            index++;
        }
        length++;
    }// Insert new element into this List. If List is non-empty,
    // insertion takes place before front element.

    void append(int data)
    {
        Node newNode = new Node(data);
        if (length == 0)
        {
            firstLink = lastLink = newNode;
        }
        else
        {
            lastLink.next = newNode;
            newNode.previous = lastLink;
            newNode.next = null;
            lastLink = newNode;
        }
        length++;

    }// Insert new element into this List. If List is non-empty,
    // insertion takes place after back element.

    void insertBefore(int data)
    {
        if (cursor == firstLink)
        {
            prepend(data);
        }
        else
        {
            Node newNode = new Node(data);
            newNode.previous = cursor.previous;
            cursor.previous = newNode;
            newNode.next = cursor;
            if (newNode.previous != null)
            {
                newNode.previous.next = newNode;
            }
            index++;
            length++;
        }


    }    // Insert new element before cursor.
    // Pre: length()>0, index()>=0

    void insertAfter(int data)
    {
        if (length > 0 && index >= 0)
        {
            Node newNode = new Node(data);
            newNode.next = cursor.next;
            cursor.next = newNode;
            newNode.previous = cursor;
            if (newNode.next != null)
            {
                newNode.next.previous = newNode;
            }
            length++;
        }
        else
            return;
    }// Inserts new element after cursor.
    // Pre: length()>0, index()>=0

    void deleteFront()
    {
        if (cursor == firstLink)
        {
            index = -1;
            cursor = null;
        }
        //Node temp = firstLink.next;
        //firstLink.next.previous = null;
        //firstLink.next = null;
        //firstLink = temp;
        firstLink = firstLink.next;
        firstLink.previous = null;
        length--;
        index--;
    }// Deletes the front element. Pre: length()>0

    void deleteBack()
    {
        if (cursor == lastLink)
        {
            index = -1;
            cursor = null;
        }

        lastLink = lastLink.previous;
        lastLink.next = null;
        length--;
    }// Deletes the back element. Pre: length()>0

    void delete()
    {
        if (length < 1 || index < 0)
        {
            return;
        }

        if (cursor == firstLink)
        {
            deleteFront();
        }
        else if (cursor == lastLink)
        {
            deleteBack();
        }
        else
        {
            cursor.previous.next = cursor.next;
            cursor.next.previous = cursor.previous;
            cursor = null;
            length--;
            index = -1;
        }

    }// Deletes cursor element, making cursor undefined.
    // Pre: length()>0, index()>=0

    public String toString()
    {
        if(length() == 0)
        {
            return new String("");
        }
        Node temp = firstLink;
        String output = "";
        while (temp != null)
        {
            output = output + temp.data + " ";
            temp = temp.next;
        }
        return output;

    }// Overrides Object's toString method. Returns a String
    // representation of this List consisting of a space
// separated sequence of integers, with front on left.

    List copy()
    {
        List copy = new List();
        Node it = this.firstLink;
        while (it != null)
        {
            copy.append(it.data);
            it = it.next;
        }
        return copy;
    }// Returns a new List representing the same integer sequence as this
    // List. The cursor in the new list is undefined, regardless of the
    // state of the cursor in this List. This List is unchanged.

}