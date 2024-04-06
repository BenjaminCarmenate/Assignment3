import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Servant extends Thread{
    public void run(AtomicInteger numInBag, ArrayList<Integer> unOrderedBag, ConcurrentLinkedList LL, int range){
        Random rand = new Random();
        int choice = rand.nextInt(0,3);
        switch (choice)
        {
            case 0:
                if(unOrderedBag.size() <= 0)
                    return;
                int bagChoice = rand.nextInt(0, numInBag.get());
                LL.addInOrder(unOrderedBag.get(bagChoice));
                unOrderedBag.remove(bagChoice);
                numInBag.getAndDecrement();
                break;
            case 1:
                int data = rand.nextInt(0, range);
                boolean check = LL.search(data);
                if(check)
                    System.out.println("" + data + ": in List");
                break;
            case 2:
                LL.deleteNode();
                break;
        }
    }
    public Servant(){

    }
}
