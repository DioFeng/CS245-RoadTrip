import java.util.*;
public class ShortestPath
{

	public int SIZE;
	ArrayList<Integer> vertex,path,cost;
	ArrayList<Boolean> known;
	Stack<Integer> result;
	Queue<String> travelCities = new LinkedList<String>();
	private int timecost;
	public ShortestPath(Roads r, City c)
	{
		// initiate data
		SIZE = c.getSize();
		vertex = new ArrayList<Integer>(SIZE);
		path = new ArrayList<Integer>(SIZE);
		cost = new ArrayList<Integer>(SIZE);
		known = new ArrayList<Boolean>(SIZE);
		for(int i = 0; i<SIZE;i++)
		{
			vertex.add(i);
			path.add(-1);
			cost.add(Integer.MAX_VALUE);
			known.add(false);
		}
	}
	// return the index of the vertex that has the least cost and unvisited
	public int least_cost_unknown_vertex()
	{
		City c = new City();
		int minCost = Integer.MAX_VALUE;
		int minDex = 0;
		// return the least cost city index
		for(int i = 0; i<c.getSize();i++)
		{
			if(known.get(i)==false&&cost.get(i)<minCost)
			{
				minCost = cost.get(i);
				minDex = i;
			}
		}
		return minDex;
	}
	public ArrayList<Integer> adjacent(int vertex)
	{
		City c = new City();
		ArrayList<ArrayList<Integer>> list = c.getAdjList();
		return list.get(vertex);
	}

	//set vertex visited
	public void known(int vertex)
	{
		known.set(vertex,true);
	}

	//return cost
	public int cost(int vertex)
	{
		return cost.get(vertex);
	}

	//return the distance between two nearby cities
	public int edge_weight(int v,int n)
	{
		Roads r = new Roads();
		City c = new City();
		String first = c.getCities().get(v);
		String second = c.getCities().get(n);
		int dist = 0;
		// get the distance
		for(int i = 0; i<r.endCity().size();i++)
		{
			if((r.getInitCity(i).equals(second)&&r.getEndCity(i).equals(first)) ||
					(r.getEndCity(i).equals(second)&&r.getInitCity(i).equals(first)))
				dist = r.getDistance(i);
		}

		return dist;
	}
	//update distance
	public void update_distance(int v,int n)
	{
		cost.set(n,edge_weight(v,n)+cost(v));

	}
	// update the path
	public void update_path(int v,int n)
	{
		path.set(n,v);
	}
	public void doGraph(int sc, int visit)
	{
		result = new Stack<Integer>();
		//initiate table
		for(int i = 0; i<SIZE;i++)
		{
			vertex.set(i,i);
			path.set(i,-1);
			cost.set(i,Integer.MAX_VALUE);
			known.set(i,false);
		}
		

		cost.set(sc,0);

		for(int i = 0;i<vertex.size();i++)
		{
			System.out.println("Please wait========"+i);

			int v = least_cost_unknown_vertex();

			known(v); //mark visited

			for(int n:adjacent(v))
			{

				if(cost(n)>cost(v)+edge_weight(v,n))//greedy algorithm, check if least cost
				{
					update_distance(v,n);//O(1)
					update_path(v,n);//O(1)

				}
			}
			if(known.get(visit)==true)
			{
				//printGraph();
				break;
			}
		}
		timecost+=cost.get(visit);
		int ve = visit;
		while(ve!=sc)
		{
			result.push(ve);
			ve = path.get(ve);
			
		}
		City c = new City();

		while(!result.isEmpty())
		{
			travelCities.add(c.getCities().get(result.pop()));
		}

	}
	// print the table
	public void printGraph()
	{
		System.out.println("Vertex      Known      Path      Cost");
		for(int i = 0; i<vertex.size();i++)
		{
			System.out.println(vertex.get(i)+"           "+
				known.get(i)+"      "+
				path.get(i)+"       "+
				cost.get(i));
		}
	}
	public Queue<String> route(String starting_city, String ending_city, List<String> attractions)
	{
		
		List<Integer> list = new ArrayList<Integer>();
		City c = new City();
		Places p = new Places();

		int sc = c.getCities().indexOf(starting_city); // index of starting city
		// add attractions to list of integer(index)
		for(String n:attractions)
		{
			list.add(c.getCities().indexOf(p.getLocation(p.attractions().indexOf(n))));
		}

		list.add(c.getCities().indexOf(ending_city));
		//calculate the time cost from one city to its nearest attraction in the list
		for(int visit : list)
		{
			doGraph(sc, visit);
			sc = visit;
		}

		return travelCities;
	}
	//return the total cost of the trip(in minute)
	public int getCost()
	{
		return timecost;
	}

	public static void main(String[] args) 
	{
		Places p = new Places();
		Roads r = new Roads();
		City c = new City();
		ShortestPath sp = new ShortestPath(r,c);
		//Example Abilene - Chicago
		//3 attractions
		String sc = "Abilene TX";
		String ec = "San Francisco CA";
		List<String> places = new ArrayList<String>();
		places.add("Cloud Gate");
		places.add("Musical Instrument Museum");
		places.add("USS Midway Museum");
		
		for(String city:sp.route(sc,ec,places))
			System.out.println(city);
		System.out.println("Starting: "+sc+" Ending: "+ec);
		System.out.println("attractions: "+places);
		
		System.out.println("Total minute cost: "+sp.getCost());


	}
}
