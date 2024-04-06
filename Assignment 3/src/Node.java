public class Node {
    private Node next;
    private int data;
    public Node(int data, Node next){
        this.next = next;
        this.data = data;
    }

    public void setNext(Node next){
        this.next = next;
    }

    public Node getNext(){
        return this.next;
    }

    public int getData(){
        return this.data;
    }
}
