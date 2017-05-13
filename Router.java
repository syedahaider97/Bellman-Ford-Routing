import java.util.HashMap;
import java.util.Map;

public class Router {
	
	String routerName;
	int distance1 = Integer.MAX_VALUE;
	int distance2 = Integer.MAX_VALUE;
	int distance3 = Integer.MAX_VALUE;
	
	Map<String,Integer> distances = new HashMap<String,Integer>();
	
	public Router(String name) {
		routerName = name;
		distances.put(routerName, 0);
	}
	
	public void connect(Router target, int distance) {
		distances.put(target.getName(), distance);
	}

	public String getName() {
		return routerName;
	}
	
	public static void display(Map<String,Integer> distances) {
		System.out.println("----------------------------------------------------------------------");
		System.out.format("%-12s","Routers:");
		for(int i = 0; i < distances.size(); i++) {
			System.out.format("%-12s","Router" + i);	
		}
		System.out.println();
		System.out.format("%-12s","Distances:");
		for(int i = 0; i < distances.size(); i++) {
			
			System.out.format("%-12s",distances.get("r"+(i)));	
		}
		System.out.println();
	}

	public Map<String, Integer> getTable() {
		return distances;
	}
}
