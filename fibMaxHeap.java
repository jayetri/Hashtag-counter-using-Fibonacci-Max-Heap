import java.util.List;
import java.util.ArrayList;



class fibMaxHeap<T>
{
    public fibMaxHeap() //Constructor
    {
    }

    private int SizeOfHeap; //This refers to the number of nodes in the fibonacci heap.
    private fibMaxNode<T> Biggest_node; //This refers to the node containing the maximum value.


//Clears everything
    public void FibMax_Clear()
    {   SizeOfHeap = 0;
        Biggest_node = null;
    }


    public fibMaxNode<T> FibMax_maximum()  //This is the maximum or highest element in the heap. THis has the highest key element.
    {
        return Biggest_node;
    }

    public void removeNodeFromList( fibMaxNode<T> node) //This function removes the node from the doubly linked list in the Fibonacci Heap
    {
        node.sibling_left_node.sibling_right_node = node.sibling_right_node;
        node.sibling_right_node.sibling_left_node = node.sibling_left_node;
    }


    public void add_to_root_list ( fibMaxNode<T> x, fibMaxNode<T> Biggest_node) //This function inserts this node x to the top-most doubly linked list containing the root.
    {

        x.sibling_right_node = Biggest_node.sibling_right_node;
        x.sibling_left_node = Biggest_node;
        x.sibling_right_node.sibling_left_node = x;
        Biggest_node.sibling_right_node = x;

    }

    public void FibMax_Insert(fibMaxNode<T> node, double Heap_Key)
    {
        node.Heap_Key = Heap_Key;

        // concatenate node into max list
        if (Biggest_node != null) { //This would append the node to be inserted to the left of the biggest node and it is present in the same doubly linked list where the biggest element is present.
            node.sibling_right_node = Biggest_node.sibling_right_node;
            node.sibling_left_node = Biggest_node;
            Biggest_node.sibling_right_node = node;
            node.sibling_right_node.sibling_left_node = node;

            if (Heap_Key > Biggest_node.Heap_Key) {
                Biggest_node = node;
            }
        } else {
            Biggest_node = node;
        }
        SizeOfHeap = SizeOfHeap+1 ; //No. of nodes is incremented.
    }


//This function removes the biggest element or the node with the highest key and appends the children to the top level list. It uses pairwise-combine method to merge nodes with the same degree.

    public fibMaxNode<T> Fib_RemoveMax()
    {
        fibMaxNode<T> big = Biggest_node;

        if (big != null) {
            int NumberOfChildren = big.NodeDegree;
            fibMaxNode<T> tempsibling_right_node;
            fibMaxNode<T> y1 = big.Child_Node;

            for ( int a = 0 ; a < NumberOfChildren ;  a++ ) { //The child nodes are being cut and added to the top level list
                tempsibling_right_node = y1.sibling_right_node;

                removeNodeFromList( y1 ) ; //node is removed from the list.

                add_to_root_list (y1, Biggest_node); //added to the top list.

                y1.Parent_Node = null; //the parent field is set to null.
                y1 = tempsibling_right_node;

            }

            removeNodeFromList( big ) ;
            if ( big.sibling_right_node == big) {
                Biggest_node = null;
            } else {
                Biggest_node = big.sibling_right_node;
                Fib_Pairwise_Merging(); //function called for pairwise combine.
            }

            SizeOfHeap = SizeOfHeap -1 ; //the number of nodes is decreased.
        }

        return big;
    }



//This function merges nodes havg same degree by making the node with smaller element as child of the other node.
    protected void Fib_Pairwise_Merging()
    {
        int NumberOfRoots = 0;
        fibMaxNode<T> x = Biggest_node;

        List<fibMaxNode<T>> array =
                new ArrayList<fibMaxNode<T>>();
        int arraySize = SizeOfHeap ;

        // Initialize degree array
        for (int i = 0; i < arraySize; i++) {
            array.add(null);
        }


        if (x != null) {
            NumberOfRoots = NumberOfRoots + 1 ;
            x = x.sibling_right_node;

            while (x != Biggest_node) {
                NumberOfRoots = NumberOfRoots + 1 ;
                x = x.sibling_right_node;
            }
        }

        for ( int i = 0 ; i < NumberOfRoots ; i++ ) { //this is for each of the nodes in the root list.
            int d = x.NodeDegree;
            fibMaxNode<T> next = x.sibling_right_node;

            while (true) {
                fibMaxNode<T> y = array.get(d);
                if (y == null) {
                    break;
                }

                if (x.Heap_Key < y.Heap_Key) { //makes one nodes child of the other. Calculated based on heap_key of hashtable.
                    fibMaxNode<T> temp = y;
                    y = x;
                    x = temp;
                }

                removeNodeFromList( y ) ;
                y.Parent_Node = x; //x is y's parent.


                if (x.Child_Node != null) {
                    y.sibling_left_node = x.Child_Node;
                    y.sibling_right_node = x.Child_Node.sibling_right_node;
                    x.Child_Node.sibling_right_node = y;
                    y.sibling_right_node.sibling_left_node = y;
                } else {
                    x.Child_Node = y;
                    y.sibling_right_node = y;
                    y.sibling_left_node = y;

                }



                y.flag_for_cascading = false; //childcut is made false

                x.NodeDegree++; //degree is increased.

                array.set(d, null);
                d= d+1;
            }

            array.set(d, x);
           //go to the next element in the list.
            x = next;
        }


        Biggest_node = null;

        for (int a = 0; a < arraySize; a++) {
            fibMaxNode<T> y = array.get(a);
            if (y == null) {
                continue;
            }

            if (Biggest_node != null) {

                removeNodeFromList( y ) ; //add to root list again.

                y.sibling_right_node = Biggest_node.sibling_right_node;
                y.sibling_left_node = Biggest_node;
                Biggest_node.sibling_right_node = y;
                y.sibling_right_node.sibling_left_node = y;

                if (  Biggest_node.Heap_Key< y.Heap_Key) { //checking for new max value
                    Biggest_node = y;
                }
            } else {
                Biggest_node = y;
            }
        }
    }
//This function is called when child cut is true and the node has lost more than one child.

    protected void FibMax_CascadeCut(fibMaxNode<T> y)
    {
        fibMaxNode<T> z = y.Parent_Node;

        if (z != null) { //checks if parent node exists.
            // if y is unmarked, set it marked
            if (y.flag_for_cascading) {
                  //marked, and one more child is lst, so cut this node from the parent.
                removeNodeFromList( y ) ;
                z.NodeDegree = z.NodeDegree -1 ;

                if (z.NodeDegree == 0) {
                    z.Child_Node = null;
                }

                if (z.Child_Node == y) {
                    z.Child_Node = y.sibling_right_node;
                }

                y.flag_for_cascading = false; //child cut value is marked false.

                add_to_root_list (y, Biggest_node);
                y.Parent_Node = null;

                FibMax_CascadeCut(z);
            } else {
                y.flag_for_cascading = true;

            }
        }
    }

//This function increases the key of the node. If key is bigger than parent node, it is cut off from its parent.
    public void FibMax_Key_Increase(fibMaxNode<T> x, double k)
    {
        x.Heap_Key = x.Heap_Key + k;

        fibMaxNode<T> y = x.Parent_Node;

        if (y != null) {
            if (y.Heap_Key < x.Heap_Key ) {

                removeNodeFromList( x ) ; //remove the node from the list
                y.NodeDegree = y.NodeDegree -1 ;

                if (y.NodeDegree == 0) {
                    y.Child_Node = null;
                }

                if (y.Child_Node == x) {
                    y.Child_Node = x.sibling_right_node;
                }


                x.flag_for_cascading = false; //child cut value is marked false.

                add_to_root_list (x, Biggest_node); //the node x is added to root list
                x.Parent_Node = null;

                FibMax_CascadeCut(y); //child is removed from parent
            }
        }


        if (Biggest_node.Heap_Key < x.Heap_Key ) {
            Biggest_node = x;
        }
    }







}

