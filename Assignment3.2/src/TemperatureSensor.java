import java.util.List;
import java.util.Random;

public class TemperatureSensor extends Thread {
    private int sensorNumber;
    TemperatureSensor(int sensorNumber){
        this.sensorNumber = sensorNumber;
    }
    public void run(List<Integer> list, int currMinute)
    {
        Random rand = new Random();
        int tempReading = rand.nextInt(-100, 70);
        int index = (currMinute-1) * 8 + sensorNumber;
        list.set(index, tempReading);
    }
}
