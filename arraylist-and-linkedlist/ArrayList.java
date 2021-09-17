public class ArrayList {
    private Object[] data;
    private int length, maxSize;

    //default constructor
    public ArrayList(){
        maxSize = 0;
        data = null;
        length = 0;
    }
    
    //normal constructor
    public ArrayList(int k){
        maxSize = k;
        data = new Object[maxSize];
        length = 0;
    }
    
    //return the size of the array list
    public int getMaxSize(){return maxSize;}
    
    //return the number of elements in the array list
    public int getNum(){return length;}
   
    //insert an element at the back of the array list
    public void insertEnd(Object k) throws ArrayListException{
        if(!isFull()){
            data[length] = k;
            length++;
        }
        else
            throw new ArrayListException("The Array List is full");
    }

    //insert an element at the front of the array list
    public void insertFront(Object k) throws ArrayListException{add(0,k);}

    //insert an element at a specified index of the array list
    public void add(int i, Object k) throws  ArrayListException{
        if(isFull())
            throw new ArrayListException("The Array List is full");
        else if((i < 0) || (i > length))
            throw new ArrayListException("Index is out of bounds");   
        else{
            for(int j=length-1; j>=i; --j)
                data[j+1]=data[j];
                
            data[i] = k;
            length++;
        }
    }

    //return data at the specified location
    public Object getData(int i) throws ArrayListException{
        if(isEmpty())
            throw new ArrayListException("The Array List is empty");
        else if((i<0) || (i>length-1))
            throw new ArrayListException("Index is out of bounds");

        return data[i];
    }

    //set data at the specified location
    public void setData(int i, Object k) throws ArrayListException{
        if(isEmpty())
            throw new ArrayListException("The Array List is empty");
        else if((i<0) || (i>length-1))
            throw new ArrayListException("Index is out of bounds");

        data[i] = k;
    }

    //remove data at the specified location
    public Object removeAt(int i) throws ArrayListException{
        if(isEmpty())
            throw new ArrayListException("The Array List is empty");
        else if((i < 0) || (i > length-1))
            throw new ArrayListException("Index is out of bounds");
        
        if(i == (length-1)){
            length--;
            return data[i];
        }
        
        Object obj = data[i];
        for(int j=i; j<length-1; ++j)
            data[j] = data[j+1];
            
        length--;
        return obj;
    }

    //remove the first element
    public Object removeFirst() throws ArrayListException{return removeAt(0);}

    //remove the last element
    public Object removeLast() throws ArrayListException{return removeAt(length-1);}

    //clear all the elements in the array list
    public void clearList(){length = 0;}

    //return true if the ArrayList is full otherwise return false
    public boolean isFull(){return length == maxSize;}

    //return true if the Array List is empty otherwise return false
    public boolean isEmpty(){return length == 0;}

    //for displaying the element in the Array List
    public String print(){
        String str = "(";

        if(length == 0)
            return "( )";
        else{
            for(int i=0; i<length; ++i)
                str += data[i] + ",";
            
            return str + ")";
        }
    }
}
