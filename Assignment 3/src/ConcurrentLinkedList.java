import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;

public class ConcurrentLinkedList {
    ReentrantLock lock;
    Node head;
    int size;
    int counter;
    public ConcurrentLinkedList(){
        this.head = null;
        this.size = 0;
        this.counter = 0;
        this.lock = new ReentrantLock();
    }

    public void addInOrder(int data)
    {
        lock.lock();
        try{
            Node temp = head;
            if(this.head == null)
            {
                this.head = new Node(data, null);
                this.size++;
                return;
            }

            if(this.head.getData() > data)
            {
                this.head = new Node(data, temp);
                return;
            }

            while(temp.getNext() != null)
            {
                if(temp.getNext().getData() <= data)
                {
                    temp = temp.getNext();
                }
                else{
                    Node tempNext = temp.getNext();
                    temp.setNext(new Node(data, tempNext));
                    return;
                }
            }

            if(temp.getNext() == null)
            {
                temp.setNext(new Node(data, null));
            }
        }
        finally {
            lock.unlock();
        }

    }

    public void deleteNode(){
        lock.lock();
        try {
            if (this.head != null) {
                this.head = this.head.getNext();
                this.size--;
                this.counter++;
            }
        }
        finally {
            lock.unlock();
        }
    }

    public boolean search(int data){
        lock.lock();
        try{
            Node temp = this.head;
            while(temp != null)
            {
                if(temp.getData() == data){
                    return true;
                }
                temp = temp.getNext();
            }
            return false;
        }
        finally {
            lock.unlock();
        }

    }
    public void printList(){
        Node temp = this.head;
        while(temp != null)
        {
            System.out.println(temp.getData());
            temp = temp.getNext();
        }
    }

    public int getCounter(){
        return  this.counter;
    }

    public int getSize(){
        return this.size;
    }
}
