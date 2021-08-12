import java.util.*;
public class ShortestPath
{
    final static Places p = new Places();//import attraction data from file (attraction=edge)
    final static Roads r = new Roads(); //import city data from file (city=vertex)
    final static City c = new City(); //setting up adjacency list for vertices

    public int SIZE;
    ArrayList<Integer> vertex,path,cost;
    ArrayList<Boolean> known;
    Stack<Integer> result;
    Queue<String> travelCities = new LinkedList<>();
    ArrayList<Integer> most_near_city_cost;
    private int trip_cost;

    public ShortestPath()
    {
        // initiate data
        SIZE = c.getSize();
        vertex = new ArrayList<>(SIZE);
        path = new ArrayList<>(SIZE);
        cost = new ArrayList<>(SIZE);
        known = new ArrayList<>(SIZE);
        //setting default values for the dijkstras algorithm's graph table
        for(int i = 0; i<SIZE; i++) {
            vertex.add(i);
            path.add(-1);
            cost.add(Integer.MAX_VALUE);
            known.add(false);
        }
    }

    /**
     * find index of the unvisited vertex that has the least cost
     * @return index of the found vertex
     */
    public int least_cost_unknown_vertex() {
        int minCost = Integer.MAX_VALUE;
        int minDex = 0;
        // return the least cost city index
        for(int i = 0; i<c.getSize(); i++) {
            if(!known.get(i) && cost.get(i)<minCost) {
                minCost = cost.get(i);
                minDex = i;
            }
        }
        return minDex;
    }

    /**
     * function that generate list of adjacent cities
     * @param vertex index of the city
     * @return list of all neighbor cities
     */
    public ArrayList<Integer> adjacent(int vertex) {
        ArrayList<ArrayList<Integer>> list = c.getAdjList();
        return list.get(vertex);
    }

    //helper function -> set vertex as visited
    public void known(int vertex) {
        known.set(vertex,true);
    }
    
    //return the cost of the vertex
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
    
    //update distance on the graph table
    public void update_distance(int v,int n) {
        cost.set(n,edge_weight(v,n)+cost(v));

    }
    
    // update the path on the graph table
    public void update_path(int v,int n) {
        path.set(n,v);
    }
    
    //Modified Dijkstras' Algorithm
    public void doGraph(int sc, int visit) {
        result = new Stack<>();
        //initiate table
        for(int i = 0; i<SIZE;i++) {
            vertex.set(i, i);
            path.set(i, -1);
            cost.set(i, Integer.MAX_VALUE);
            known.set(i, false);
        }
        cost.set(sc,0);

        for(int i = 0;i < vertex.size();i++) {
            int v = least_cost_unknown_vertex();
            known(v); //mark visited
            for (int n : adjacent(v)) {
                if (cost(n) > cost(v) + edge_weight(v, n))//greedy algorithm, check if least cost
                {
                    update_distance(v, n);//O(1)
                    update_path(v, n);//O(1)
                }
            }
            if (known.get(visit)) {
                //printGraph();
                break;
            }
        }
    }
    // print the table
    public void printGraph() {
        System.out.println("Vertex      Known      Path      Cost");
        for(int i = 0; i<vertex.size(); i++) {
            System.out.println(vertex.get(i)+"           "+
                    known.get(i)+"      "+
                    path.get(i)+"       "+
                    cost.get(i));
        }
    }

    public void route(String starting_city, String ending_city, List<String> attractions) {
        List<Integer> list = new ArrayList<>();
        int sc = c.getCities().indexOf(starting_city); // index of starting city
        list.add(sc); // add source city in the list(starting city)
        if(!attractions.isEmpty()) {
            //add attractions to list of integer(index)
            for(String attraction : attractions) {
                list.add(c.getCities().indexOf(p.getLocation(p.attractions().indexOf(attraction))));
            }
            List<Integer> temp = new ArrayList<>(list);//list used to keep track if visited city(remove it)
            //calculate the time cost from one city to its nearest attraction in the list
            for (int i = 0; i < list.size()-1; i++) {
                ArrayList<Integer> near = find_Nearest_attraction(sc, temp);
                int visit = temp.get(near.indexOf(Collections.min(near)));
                doGraph(sc, visit);
                calculate_cost(visit,sc);
                sc = visit;
            }
        }
        doGraph(sc, c.getCities().indexOf(ending_city));
        calculate_cost(c.getCities().indexOf(ending_city),sc);
    }

    /**
     * function that add the cost between two cities
     * add the cities to the stack for the route printing
     * @param visit_city visiting city
     * @param source_city starting city
     */
    private void calculate_cost(int visit_city, int source_city) {
        trip_cost+=cost.get(visit_city);
        //add cities to the stack
        int ve = visit_city;
        while(ve!=source_city) {
            result.push(ve);
            ve = path.get(ve);
        }
        while(!result.isEmpty()) {
            travelCities.add(c.getCities().get(result.pop()));
        }
    }

    /**
     * 
     * @param start_city city to compare to other cities
     * @param list list of cities
     * @return list of costs from fixate starting city to other cities
     */
    private ArrayList<Integer> find_Nearest_attraction(int start_city, List<Integer> list) {
        most_near_city_cost = new ArrayList<>();
        list.remove(list.indexOf(start_city));
        //System.out.println("Removed==="+list);
        for(int visit : list)
        {
            doGraph(start_city, visit);
            most_near_city_cost.add(cost.get(visit));
        }
        return most_near_city_cost;
    }

    /**
     * getter function
     * @return total cost of the road trip
     */
    public int getCost() {
        return trip_cost;
    }
    //helper function to print the shortest route and the cost
    public void printRoute(String start_city) {
        String next_city = start_city;
        if(!travelCities.isEmpty()) {
            for(String city:travelCities) {
                System.out.println("* "+next_city+" -> "+city);
                next_city = city;
            }
            System.out.println("Total cost: "+getCost()+" miles");
        }
    }

    public static void main(String[] args) {
        ShortestPath sp = new ShortestPath();
        List<String> places = new ArrayList<>();//list to store user's attraction input
        Scanner scan = new Scanner(System.in);
        //user inputs
        String start_city = "";
        String destination = "";
        String attractions_choice = "";

        while(!start_city.equalsIgnoreCase("EXIT")&&
                !attractions_choice.equalsIgnoreCase("ENOUGH")) {
            //Get the initial city from user
            System.out.print("Name of starting city (or EXIT to quit): ");
            start_city = scan.nextLine();
            //Input validation
            while(!r.endCity().contains(start_city) && !r.initCity().contains(start_city) &&
            !start_city.equalsIgnoreCase("EXIT")) {
                System.out.println(start_city + " not found, try again");
                System.out.print("Name of starting city (or EXIT to quit): ");
                start_city = scan.nextLine();
            }
            if(start_city.equalsIgnoreCase("EXIT")) {
                break;
            }
            //Get the destination city from user
            System.out.print("Name of ending city: ");
            destination = scan.nextLine();
            //Input validation
            while(!r.endCity().contains(destination) && !r.initCity().contains(destination)) {
                System.out.println(destination + " not found, try again");
                System.out.print("Name of ending city: ");
                destination = scan.nextLine();
            }
            //Get attractions from user
            while(!attractions_choice.equalsIgnoreCase("ENOUGH")) {
                System.out.print("List an attraction along the way (or ENOUGH to stop listing): ");
                attractions_choice = scan.nextLine();
                if(attractions_choice.equalsIgnoreCase("ENOUGH")) break;
                //Input validation
                if(!p.attractions().contains(attractions_choice)) {
                    System.out.println("Attraction \""+attractions_choice+"\" unknown");
                }
                else {
                    places.add(attractions_choice);//add attractions to the list
                }
            }
            sp.route(start_city, destination, places);//generate a route queue with minimal cost in miles
            System.out.println("=====ROUTE=====");
            sp.printRoute(start_city); // print the route and cost
        }
    }
}
