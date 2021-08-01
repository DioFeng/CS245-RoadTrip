import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Roads {
    private final List<Integer> distance;
    private final List<Integer> time;
    private final List<String> initCity, endCity;

    public Roads()
    {
        distance = new ArrayList<>();
        time = new ArrayList<>();
        initCity = new ArrayList<>();
        endCity = new ArrayList<>();
        importData(); // initiate lists
    }
    // store data
    private void importData() {
        try {
            File file = new File("src/roads.csv");
            Scanner sc = new Scanner(file);
            //sc.nextLine();
            while(sc.hasNextLine())
            {
                String line = sc.nextLine();
                String[] result = line.split(",");

                endCity.add(result[0]);
                initCity.add(result[1]);
                int d = Integer.parseInt(result[2]);
                distance.add(d);
                int t = Integer.parseInt(result[3]);
                time.add(t);
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Not such file");
        }
    }
    //getters
    
    public String getEndCity(int i) { return endCity.get(i); }
    public String getInitCity(int i) { return initCity.get(i); }
    //cost in miles
    public int getDistance(int i) { return distance.get(i); }
    //cost in minutes
    public int getMinute(int i) { return time.get(i); }
    public int getSize() { return endCity.size(); }
    public List<String> endCity() { return endCity; }
    public List<String> initCity() { return initCity; }

}
