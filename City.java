import java.util.*;
public class City {
    final static Roads r = new Roads();
    private final HashSet<String> citiesSet = new HashSet<>();//using set: uniqueness, no duplicates
    private ArrayList<String> combine; //<All cities no duplicate>
    private ArrayList<ArrayList<Integer>> adj_list;

    public City() {
        generateAdjList(); //get all the cities that are adjacent(close together)
    }

    public void generateAdjList()
    {
        generateCities(); //generate cities no duplicates (vertices)
        int size = getCities().size();
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>(size);
        adj_list = new ArrayList<>();

        for(int i = 0; i<size;i++) {
            adj.add(new ArrayList<>());
            adj_list.add(new ArrayList<>());
        }
        for(int i =0;i<size;i++) {
            ArrayList<String> l2 = nextCity(getCities().get(i));
            for(int j = 0; j<l2.size();j++) {
                if(getCities().contains(l2.get(j))) {
                    addEdge(adj, getCities().indexOf(getCities().get(i)), getCities().indexOf(l2.get(j)));
                }
            }
        }
        for(int i=0;i<size;i++) {
            for(int j = 0; j< adj.get(i).size(); j++) {
                if(!adj_list.get(i).contains(adj.get(i).get(j))) {
                    adj_list.get(i).add(adj.get(i).get(j));
                }
            }
        }
    }
    
    public void generateCities() {
        int size = r.getSize();
        combine = new ArrayList<>();

        for(int i=0;i<size;i++) {
            if(!citiesSet.contains(r.getEndCity(i))) {
                citiesSet.add(r.getEndCity(i));
                combine.add(r.getEndCity(i));
            }
        }

        for(int i = 0; i<size;i++) {
            if(!citiesSet.contains(r.getInitCity(i))) {
                citiesSet.add(r.getInitCity(i));
                combine.add(r.getInitCity(i));
            }
        }
    }
    //get neighbor cities
    public ArrayList<String> nextCity(String city) {
        ArrayList<String> result = new ArrayList<>();
        for(int i = 0;i<r.getSize();i++) {
            if(city.equals(r.getEndCity(i))) {
                result.add(r.getInitCity(i));
            }
            if(city.equals(r.getInitCity(i))) {
                result.add(r.getEndCity(i));
            }
        }
        return result;
    }

    public ArrayList<String> getCities() {
        return combine;
    }
    //getter function for size of the city list
    public int getSize() {
        return combine.size();
    }
    // return the adjacency list of all vertices
    public ArrayList<ArrayList<Integer>> getAdjList() {
        return adj_list;
    }
    // create an adjacency list
    public void addEdge(ArrayList<ArrayList<Integer>> adj, int u, int v) {
        adj.get(u).add(v);
        adj.get(v).add(u);
    }
}

