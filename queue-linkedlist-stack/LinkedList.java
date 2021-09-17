public class LinkedList{
    private Node head, tail, current;

    //default LinkedList class
    public LinkedList(){
        head = tail = current = null;
    }

    //to check whether the linked list is empty
    public boolean isEmpty(){
        return head == null;
    }
    
    //to print data
    public String print(){
        if(isEmpty())
            return "()";
        else
            return "(" + head + ")";
    }

    //to add new Object data at the front of the LinkedList
    public void insertAtFront(Object data){
        if(isEmpty())
            head = tail = new Node(data);
        else
            head = new Node(data, head);
    }

    //return the number of elements in the list
    public int size(){
        int count = 0;
        
        if(isEmpty())
            return count;
        
            current = head;
            while(current != null){
                ++count;
                current = current.next;
            }
            return count;
    }

    //to add new Object data at the back of linked list
    public void insertAtBack(Object data){
        if(isEmpty())
            head = tail = new Node(data);
        else
            tail = tail.next = new Node(data);
    }

    //return the first element in the linked list
    public Object getFirst() throws EmptyListException
    {
        if(isEmpty())
            throw new EmptyListException();

            current = head;
            return current.data;
    }

    //return the next element in the linked list
    public Object getNext(){
        if(current != tail){
            current = current.next;
            return current.data;
        }
        else
            return null;
    }

    //return the last element in the linked list
    public Object getLast() throws EmptyListException{
        if(isEmpty())
            throw new EmptyListException();

        return tail.data;
    }

    //remove the first element in the linked list
    public Object removeFromFront() throws EmptyListException{
        if(isEmpty())
            throw new EmptyListException();

        Object d = head.data;

        if(head == tail)
            head = tail = null;
        else{
            Node curr = head;
            head = head.next;
            curr.next = null;
        }

        return d;
    }
    
    //remove the last element in the linked list
    public void removeAtPosition(int position){
        // If linked list is empty
        if (head == null)
            return;
 
        // Store head node
        Node temp = head;
 
        // If head needs to be removed
        if (position == 0)
        {
            head = temp.next;   // Change head
            return;
        }
 
        // Find previous node of the node to be deleted
        for (int i=0; temp!=null && i<position-1; i++)
            temp = temp.next;
 
        // If position is more than number of nodes
        if (temp == null || temp.next == null)
            return;
 
        // Node temp->next is the node to be deleted
        // Store pointer to the next of node to be deleted
        Node next = temp.next.next;
 
        temp.next = next;  // Unlink the deleted node from list
    }
    
    //remove the last element in the linked list
    public Object removeFromBack() throws EmptyListException{
        if(isEmpty())
            throw new EmptyListException();

        Object d = tail.data;

        if(head == tail)
            head = tail = null;
        else{
            Node curr = head;
            while(curr.next != tail)
                curr = curr.next;

            tail = curr;
            curr.next = null;
        }
        
        return d;
    }

}