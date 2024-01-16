import java.util.Comparator;

public class CostComparator implements Comparator< Services> {

	public int compare(Services o1, Services o2) {
		if(o1.calculateService() > o2.calculateService()) return 1; 
		else if(o1.calculateService() < o2.calculateService()) return -1;
		else return 0;
	}

}
