import java.util.*;
public class ShortestPath
{
    final static Places p = new Places();
    final static Roads r = new Roads();
    final static City c = new City();
    public int SIZE;
    ArrayList<Integer> vertex,path,cost;
    ArrayList<Boolean> known;
    Stack<Integer> result;
    Queue<String> travelCities = new LinkedList<>();
    private int time_cost;
	
    public ShortestPath()
    {
        // initiate data
        SIZE = c.getSize();
        vertex = new ArrayList<>(SIZE);
        path = new ArrayList<>(SIZE);
        cost = new ArrayList<>(SIZE);
        known = new ArrayList<>(SIZE);
        for(int i = 0; i<SIZE;i++) {
            vertex.add(i);
            path.add(-1);
            cost.add(Integer.MAX_VALUE);
            known.add(false);
        }
    }
    // return the index of the vertex that has the least cost and unvisited
    public int least_cost_unknown_vertex() {
        int minCost = Integer.MAX_VALUE;
        int minDex = 0;
        // return the least cost city index
        for(int i = 0; i<c.getSize();i++) {
            if(!known.get(i) &&cost.get(i)<minCost) {
                minCost = cost.get(i);
                minDex = i;
            }
        }
        return minDex;
    }

    public ArrayList<Integer> adjacent(int vertex) {
        ArrayList<ArrayList<Integer>> list = c.getAdjList();
        return list.get(vertex);
    }

    //set vertex visited
    public void known(int vertex) {
        known.set(vertex,true);
    }

    //return cost
    public int cost(int vertex) {
        return cost.get(vertex);
    }

    //return the distance between two nearby cities
    public int edge_weight(int v,int n) {

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
    public void update_distance(int v,int n) {
        cost.set(n,edge_weight(v,n)+cost(v));

    }
    // update the path
    public void update_path(int v,int n) {
        path.set(n,v);
    }
    public void doGraph(int sc, int visit) {
        result = new Stack<Integer>();
        //initiate table
        for(int i = 0; i<SIZE;i++) {
            vertex.set(i, i);
            path.set(i, -1);
            cost.set(i, Integer.MAX_VALUE);
            known.set(i, false);
        }
        cost.set(sc,0);

        for(int i = 0;i<vertex.size();i++) {
            int v = least_cost_unknown_vertex();
            known(v); //mark visited
            for(int n:adjacent(v)) {
                if(cost(n) > cost(v)+edge_weight(v,n))//greedy algorithm, check if least cost
                {
                    update_distance(v,n);//O(1)
                    update_path(v,n);//O(1)
                }
            }
            if(known.get(visit))
            {
                //printGraph();
                break;
            }
        }
        time_cost+=cost.get(visit);
        int ve = visit;
        while(ve!=sc) {
            result.push(ve);
            ve = path.get(ve);
        }
        while(!result.isEmpty()) {
            travelCities.add(c.getCities().get(result.pop()));
        }
    }
    // print the table
    public void printGraph() {
        System.out.println("Vertex      Known      Path      Cost");
        for(int i = 0; i<vertex.size();i++) {
            System.out.println(vertex.get(i)+"           "+
                    known.get(i)+"      "+
                    path.get(i)+"       "+
                    cost.get(i));
        }
    }
    public Queue<String> route(String starting_city, String ending_city, List<String> attractions) {
        System.out.println("Starting: "+starting_city+" Ending: "+ending_city);
        List<Integer> list = new ArrayList<Integer>();

        int sc = c.getCities().indexOf(starting_city); // index of starting city
        // add attractions to list of integer(index)
        if(!attractions.isEmpty()) {
            for(String n:attractions) {
                list.add(c.getCities().indexOf(p.getLocation(p.attractions().indexOf(n))));
            }
        }
        list.add(c.getCities().indexOf(ending_city));
        //calculate the time cost from one city to its nearest attraction in the list
        for(int visit : list) {
            doGraph(sc, visit);
            sc = visit;
        }
        return travelCities;
    }
    //return the total cost of the trip(in minute)
    public int getCost() {
        return time_cost;
    }

    public static void main(String[] args) {
        ShortestPath sp = new ShortestPath();
        //Example Abilene - Chicago
        //3 attractions
        String start_city = "Abilene TX";
        String destination = "Chicago IL";
        List<String> places = new ArrayList<String>();
        places.add("Cloud Gate");
        places.add("Musical Instrument Museum");
        places.add("USS Midway Museum");
        System.out.println(places);
        System.out.println("Start from: "+start_city);
        for(String city:sp.route(start_city, destination, places))
            System.out.println(city);
        System.out.println("Total minute cost: "+sp.getCost());
    }
}
