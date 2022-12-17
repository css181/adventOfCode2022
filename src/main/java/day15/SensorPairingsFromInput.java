package day15;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;

public class SensorPairingsFromInput {

	private static File file;
	private ArrayList<SensorPairing> sensorPairings;
	
	public SensorPairingsFromInput() {
		URL fileName = getClass().getResource("Input.txt");
		file = new File(fileName.getPath());
		sensorPairings = new ArrayList<SensorPairing>();
	}
	
	protected void setFileToUse(File file) {
		SensorPairingsFromInput.file = file;
	}

	protected ArrayList<SensorPairing> getSensorPairings() {
		return sensorPairings;
	}
	protected void setSensorPairings(ArrayList<SensorPairing> sensorPairings) {
		this.sensorPairings = sensorPairings;
	}

}
