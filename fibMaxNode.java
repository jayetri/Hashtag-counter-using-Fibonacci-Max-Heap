
/**
This class - fibMaxNode represents each of the nodes in the Max Fibonacci Heap.
 */
class fibMaxNode<T>
{   double Heap_Key; // This is the key of every node.
    int NodeDegree; //This is the degree. Degree is basically the no. of children of every node.
    boolean flag_for_cascading; //This shows if the childcut value is true or false. It is of type boolean. It is false initially. For every node, when one of its children is removed, it is marked true.
    fibMaxNode<T> Parent_Node; //It points to the parent field.
    fibMaxNode<T> Child_Node; //It points to the child node
    fibMaxNode<T> sibling_right_node; //It points to the left sibling of this node since fibonacci heap has a doubly linked list.
    fibMaxNode<T> sibling_left_node; //It points to the right sibling of this node since fibonacci heap has a doubly linked list.
    T value; //It is used to represent the frequency of the hashtag and the value of node in the Fibonacci  Heap.

    public fibMaxNode(T value, double Heap_Key) //Constructor-THis initializes all the node attributes.
    {
        sibling_right_node = this;
        sibling_left_node = this;
        this.value = value;
        this.Heap_Key = Heap_Key;
    }



}

