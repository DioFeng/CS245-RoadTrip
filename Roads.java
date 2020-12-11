import java.io.File; 
import java.io.FileNotFoundException; 
import java.util.*;

public class Roads
{
	private List<Integer> distance;
	private List<Integer> time;
	private List<String> initCity, endCity;

	public Roads()
	{
		distance = new ArrayList<Integer>();
		time = new ArrayList<Integer>();
		initCity = new ArrayList<String>();
		endCity = new ArrayList<String>();
		importData(); // initiate lists
	}
	// store data
	private void importData()
	{
		try
		{	
			File file = new File("roads.csv");
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
	public String getEndCity(int i)//get city
	{
		return endCity.get(i);
	}

	public String getInitCity(int i)//get city
	{
		return initCity.get(i);
	}

	public int getDistance(int i)//cost
	{
		return distance.get(i);
	}

	public int getMinute(int i)//cost
	{
		return time.get(i);
	}
	public int getSize()
	{
		return endCity.size();
	}
	public List<String> endCity()
	{
		return endCity;
	}
	public List<String> initCity()
	{
		return initCity;
	}
}