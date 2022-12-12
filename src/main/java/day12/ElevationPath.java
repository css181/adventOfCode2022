package day12;

import java.util.LinkedList;

public class ElevationPath {

	private LinkedList<Elevation> priorElevations;
	private Elevation elevation;
	
	public ElevationPath() {
	}
	
	public ElevationPath(Elevation elevation, LinkedList<Elevation> priorElevations) {
		this.elevation = elevation;
		this.priorElevations = priorElevations;
	}

	protected LinkedList<Elevation> getPriorElevations() {
		return priorElevations;
	}

	protected Elevation getElevation() {
		return elevation;
	}
	
	@Override
    public String toString() {
    	String print = elevation.getCoordinate() + "<==" + priorElevations;
		return print;
    } 

}
