import java.util.*;
public class City
{
	HashSet<String> citiesSet = new HashSet<String>();

	ArrayList<String> combine; //<All cities no duplicate>
	ArrayList<ArrayList<Integer>> adj, adjlist;

	private String location, attraction;

	public City(Places p, String c)
	{
		// this.location = c;
		// this.attraction = p.getAttraction(p.locations().indexOf(c));

		this.attraction = c;
		this.location = p.getLocation(p.attractions().indexOf(c));
	}
	public City()
	{
		generateAdjList();
	}
	public void generateCities()
	{	
		Roads r = new Roads();
		int size = r.getSize();

		combine = new ArrayList<String>();

		for(int i=0;i<size;i++)
		{
			if(!citiesSet.contains(r.getEndCity(i)))
			{
				citiesSet.add(r.getEndCity(i));
	
				combine.add(r.getEndCity(i));			
			}
		}

		for(int i = 0; i<size;i++)
		{
			if(!citiesSet.contains(r.getInitCity(i)))
			{
				citiesSet.add(r.getInitCity(i));

				combine.add(r.getInitCity(i));
			}
		}
	}
	public ArrayList<String> nextCity(String city)
	{
		Roads r = new Roads();
		ArrayList<String> result = new ArrayList<String>();
		for(int i = 0;i<r.getSize();i++)
		{
			if(city.equals(r.getEndCity(i)))
			{
				result.add(r.getInitCity(i));
			}
			if(city.equals(r.getInitCity(i)))
			{
				result.add(r.getEndCity(i));
			}
		}
		return result;
	}

	public ArrayList<String> getCities()
	{
		return combine;
	}
	public int getSize()
	{
		return combine.size();
	}
	public void generateAdjList()
	{
		generateCities(); //generate cities no duplicates
		int size = getCities().size();
		adj = new ArrayList<ArrayList<Integer>>(size);
		for(int i = 0; i<size;i++)
		{
			adj.add(new ArrayList<Integer>());
		}
		for(int i =0;i<size;i++)
		{
			ArrayList<String> l2 = nextCity(getCities().get(i));
			for(int j = 0; j<l2.size();j++)
			{
				if(getCities().contains(l2.get(j)))
				{
					addEdge(adj,getCities().indexOf(getCities().get(i)),getCities().indexOf(l2.get(j)));
				}
			}
		}
		adjlist = new ArrayList<ArrayList<Integer>>();
		for(int i =0; i<size;i++)
		{
			adjlist.add(new ArrayList<Integer>());
		}
		
		for(int i=0;i<size;i++)
		{
			for(int j = 0;j<adj.get(i).size();j++)
			{
				if(!adjlist.get(i).contains(adj.get(i).get(j)))
				{
					adjlist.get(i).add(adj.get(i).get(j));
				}
			}
		}
		
	}
	// return the adjacency list of all vertices
	public ArrayList<ArrayList<Integer>> getAdjList()
	{
		return adjlist;
	}
	// create a addjacency list
	public void addEdge(ArrayList<ArrayList<Integer> > adj, 
                        int u, int v) 
    { 
        adj.get(u).add(v); 
        adj.get(v).add(u); 
    }

}