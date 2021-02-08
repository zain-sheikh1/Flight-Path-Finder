package app;
import java.util.*;

public class Airport {
	public String id;
	public ArrayList<String> connectionsTo;
	public  ArrayList<String> connectionsFrom;
	public String pred;
	public int minDist;
	
	public Airport(String id) {
		this.id = id;
		this.connectionsTo = new ArrayList<String>();
		this.connectionsFrom = new ArrayList<String>();
		this.minDist = 0;
	}
	
	public void setPred(String pred) {
		this.pred = pred;
	}
	
	public void setMinDist(int md) {
		this.minDist = md;
	}
	
	
}
