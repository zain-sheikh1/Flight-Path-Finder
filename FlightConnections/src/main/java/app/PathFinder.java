package app;
import java.io.IOException;
import java.util.* ;
import app.Airport;

public class PathFinder {
	
	public PathFinder() {
		
	}
	
	public static Airport[] listOfConnections() throws IOException{
		Airport[] airports = new Airport[2000000];
		ExcelUtils connectionsData;
		try {
			connectionsData = new ExcelUtils("./data/connections.xlsx", "sheet1");
			for(int i=1; i<connectionsData.getRowCount(); i++) {
				String id = connectionsData.getCellData(i,0);
				int currentAirportIndex = id.hashCode();
				
				if(airports[currentAirportIndex]==null) {
					airports[currentAirportIndex] =  new Airport(id);
				}
				Airport currentAirport = airports[currentAirportIndex];
				String destinationAirportID = connectionsData.getCellData(i, 1);
				
				if(airports[destinationAirportID.hashCode()]==null) {
					airports[destinationAirportID.hashCode()] = new Airport(destinationAirportID);
				}
				
				currentAirport.connectionsTo.add(destinationAirportID);
				airports[currentAirportIndex] = currentAirport;
				
		}
		}catch(IOException e) {
			System.out.println("error");
		}
		
		return airports;
	}
	
	public  String initializePathFinder(String start, String end) throws IOException {
		Airport[] airports = listOfConnections();
		
		for(int i=0; i<airports.length; i++) {
			if(airports[i]!=null) {
				System.out.println(airports[i].id + " ---> " + airports[i].connectionsTo);
			}
		}
		
			return findPath(airports, start, end);
		
		
		
		
		}
	
	public  static String findPath(Airport[] airports, String start, String end) {
	
		
		Airport currentAirport;
		int startHASH = start.hashCode();
		
		
		//if the start and end targets are the same, return 0;
		if(start==end) {
			return "zero";
		}
		
		//initialize a list of already visited Airports
		ArrayList<String> visitedAirports = new ArrayList<String>();
		
		//create a new list that represents the predecessor and minimum distance of each Airport
		//initialize the predecessor to 0 and the minimum distance to an arbitrarily large number
		for(int i=0; i<airports.length; i++) {
			if(airports[i]!=null) {
				currentAirport = airports[i];
				currentAirport.setMinDist(2000000);
				currentAirport.setPred("");
				airports[i] = currentAirport;
			}
		}
		
		
		//the starting Airport is set as it's own predecessor, with a minimum distance of 0
		currentAirport = airports[startHASH];
		currentAirport.setPred(start);
		currentAirport.setMinDist(0);
		airports[startHASH] = currentAirport;
		
		//DJIKSTRA's ALGORITHM
		String currentAirportID = "";
		int currentAirportIndex = 0;
		//for loop
		for(int i=0; i<airports.length; i++) {
			
			
		//set the current Airport to the prioritized Airport 
			currentAirportID = priority(airports, visitedAirports);
			if(currentAirportID == "endRoute") {
				return "The Destination Cannot Be Reached From Your Airport.";
			}
			currentAirportIndex = currentAirportID.hashCode();
			currentAirport = airports[currentAirportIndex];
		//add the current Airport  to the list of visited Airports
			visitedAirports.add(currentAirportID);
			//SECOND for loop - loop through each connection of the current Airport
			
			
			for(int j=0; j<currentAirport.connectionsTo.size(); j++) {
				Airport destinationAirport = airports[currentAirport.connectionsTo.get(j).hashCode()]; 
				System.out.println("current id: " + currentAirport.id);
				System.out.println("destination id: " + destinationAirport.id);
				System.out.println("................................");
			//if the minimum distance of the j Airport  is greater then 1 plus the minimum distance of the current Airport, then  
				if(destinationAirport.minDist>1+currentAirport.minDist) {
				// Airport minimum distance = 1 + current Airport distance 
					destinationAirport.setMinDist(1 + currentAirport.minDist);
				//j Airport predecessor = current Airport
					destinationAirport.setPred(currentAirport.id);
					
					airports[destinationAirport.id.hashCode()] = destinationAirport;
				}
				airports[currentAirport.id.hashCode()] = currentAirport;
			}
			airports[currentAirport.id.hashCode()] = currentAirport;
			
			
			
		//if the current Airport has the minimum distance value of the arbitrarily large number, then this Airport cannot be reached 
		if(currentAirport.minDist == 2000000) {
			return "The Destination Cannot Be Reached From Your Airport.";
		}
		//if the current Airport is the target Airport, return the path
		if(currentAirport.id.equals(end)) {
			return path(airports, currentAirport.id, "");
		}
		}
		return "error";
	}
	
	
	public static int getPred(int[] airport) {
		return airport[0];
	}
	
	public static int getDist(int[] airport) {
		return airport[1];
	}
	
	public static String priority(Airport[] airports, ArrayList<String> visitedAirports){
		int lowestValue = 2000000;
		String priorityAirportID = "endRoute";
		
		for(int i=0; i<airports.length; i++) {
			if(airports[i]!=null) {
				if(airports[i].minDist<lowestValue && !visitedAirports.contains(airports[i].id)) {
					if(visitedAirports.contains(airports[i].id)==false) {
						lowestValue = airports[i].minDist;
						priorityAirportID = airports[i].id;
					}
				}
			}
		}
		return priorityAirportID;
	}
	
	public static String path(Airport[] airports, String destination, String path) {
		Airport destinationAirport = airports[destination.hashCode()];
		if(destinationAirport.pred.equals(destinationAirport.id) || destinationAirport.pred.equals("")) {
			return destinationAirport.id;
		}
		return path(airports, destinationAirport.pred, "") + " --> " + destinationAirport.id;
		
	}
	
}
