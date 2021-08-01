import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Places {
    private final List<String> attractions;
    private final List<String> locations;

    public Places() {
        attractions = new ArrayList<>();
        locations = new ArrayList<>();
        getData(); //store data
    }
    private void getData() {
        try {
            File file = new File("attractions.csv");
            Scanner sc = new Scanner(file);
            sc.nextLine();
            while(sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] result = line.split(",");
                attractions.add(result[0]);
                locations.add(result[1]);
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Not found");
        }
    }
    //return location
    public String getLocation(int i) { return locations.get(i); }
    //return attraction
    public String getAttraction(int i) { return attractions.get(i); }
    //return a list of attraction
    public List<String> attractions() { return attractions; }
    //return a list of location
    public List<String> locations() { return locations; }
    //get data from file and store them

}
