import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args)
    {
        int numThreads = 4;
        AtomicInteger numInBag =  new AtomicInteger(500000);
        int range = 500000;
        int origNumInBag = numInBag.get();
        ArrayList<Servant> threads = new ArrayList<Servant>(4);
        ArrayList<Integer> unOrderedBag = new ArrayList<Integer>();
        Random rand = new Random();
        ConcurrentLinkedList LL = new ConcurrentLinkedList();

        for(int i = 0; i < numThreads; i++)
            threads.add(new Servant());

        for(int i = 0; i < numInBag.get(); i++)
        {
            unOrderedBag.add(i);
        }

        while(LL.getCounter() != origNumInBag ){
            int choice = rand.nextInt(0, 4);
            threads.get(choice).run(numInBag,unOrderedBag, LL, range);
        }
        if(LL.getSize() <= 0)
        {
            System.out.println("Thank you cards sent: " + LL.getCounter());
        }
        else
        {
            System.out.println("Error " + LL.getSize() + "Elements still in list");
        }

        LL.printList();

    }
}