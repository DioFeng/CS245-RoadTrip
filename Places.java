import java.io.File; 
import java.io.FileNotFoundException; 
import java.util.*;


public class Places
{
	private List<String> attractions;
	private List<String> locations;

	public Places()
	{
		attractions = new ArrayList<String>();
		locations = new ArrayList<String>();
		getData(); //store data
	}
	//return location
	public String getLocation(int i)
	{
		return locations.get(i);
	}
	//return attraction
	public String getAttraction(int i)
	{
		return attractions.get(i);
	}
	//return a list of attraction
	public List<String> attractions()
	{
		return attractions;
	}
	//return a list of location
	public List<String> locations()
	{
		return locations;
	}
	//get data from file and store them
	private void getData()
	{
		try
		{
			File file = new File("attractions.csv");
			Scanner sc = new Scanner(file);
			sc.nextLine();
			while(sc.hasNextLine())
			{
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
	
	// public String printLocation()
	// {
	// 	String result = "";
	// 	for(int i = 0;i<locations.size();i++)
	// 	{
	// 		result+= (i+1)+". "+locations.get(i);
	// 		if(i<locations.size()-1)
	// 		{
	// 			result+="\n";
	// 		}
	// 	}
	// 	return result;
	// }
	// public String printAttraction()
	// {
	// 	String result = "";
	// 	for(int i = 0;i<attractions.size();i++)
	// 	{
	// 		result+= (i+1)+". "+attractions.get(i);
	// 		if(i<attractions.size()-1)
	// 		{
	// 			result+="\n";
	// 		}
	// 	}
	// 	return result;
	// }
}