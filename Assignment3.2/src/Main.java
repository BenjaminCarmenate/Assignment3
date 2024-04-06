import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        int numSensors = 8;
        int currMinute = 1;
        int maxSkewInterval = 0;
        int skew = -500;
        int minTemp = -200;
        int maxTemp = 200;
        int numSimulatedMinutes = 60;
        List<Integer> sharedList =  Collections.synchronizedList(new ArrayList<Integer>(numSimulatedMinutes * numSensors));
        ArrayList<TemperatureSensor> temperatureSensors = new ArrayList<TemperatureSensor>(numSensors);

        for (int i = 0; i < numSimulatedMinutes * numSensors; i++)
        {
            sharedList.add(-200);
        }

        for(int i = 0; i < numSensors; i++){
            temperatureSensors.add(new TemperatureSensor(i));
        }

        for(int i = 1; i < numSimulatedMinutes+1; i++)
        {
            for(int j = 0; j < numSensors; j++)
            {
                temperatureSensors.get(j).run(sharedList, i);
            }
            if(i % 60 == 0){
                Stream<Integer> sortedTempStream = sharedList.stream().sorted();
                ArrayList<Integer> sortedTempList = new ArrayList<Integer>(sortedTempStream.toList());
                System.out.print("Lowest Temps: ");
                for(int k = 0; k < 5; k++)
                {
                    System.out.print(sortedTempList.get(k) + " ");
                }
                System.out.print("\nHighest Temps: ");
                for(int k = sortedTempList.size()-5; k <sortedTempList.size() ; k++)
                {
                    System.out.print(sortedTempList.get(k) + " ");
                }
            }
            for(int k = 0; k < numSimulatedMinutes; k+=10)
            {
                for (int l = 0; l < (10 * numSensors) - 1; l++)
                {
                    minTemp = Math.min(sharedList.get(k + l), sharedList.get(k + l + 1));
                    maxTemp = Math.max(sharedList.get(k + l), sharedList.get(k + l + 1));
                }
                int tempSkew = maxTemp - minTemp;
                skew = (Math.max(tempSkew, skew) == skew) ? skew : tempSkew;
                maxSkewInterval = (tempSkew == skew) ? k/10 : maxSkewInterval;
            }

        }
        System.out.println("\nMax skew Interval: " + maxSkewInterval + "\n");
    }
}