package day15;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class PartOneTest {

	private SensorPairingsFromInput sensorPairingsFromInput;
	
	@BeforeEach
	public void setup() {
		sensorPairingsFromInput = new SensorPairingsFromInput();
	}
	
	@Test
	void manually_enter_all_sample_pairings() throws Exception {
		ArrayList<SensorPairing> expectedElfPairs = new ArrayList<SensorPairing>();
		expectedElfPairs.add(new SensorPairing(new Sensor(2,18), new Beacon(-2, 15)));
		expectedElfPairs.add(new SensorPairing(new Sensor(9,16), new Beacon(10,16)));
		expectedElfPairs.add(new SensorPairing(new Sensor(13,2), new Beacon(15,3)));
		expectedElfPairs.add(new SensorPairing(new Sensor(12,14), new Beacon(10,16)));
		expectedElfPairs.add(new SensorPairing(new Sensor(10,20), new Beacon(10,16)));
		expectedElfPairs.add(new SensorPairing(new Sensor(14,17), new Beacon(10,16)));
		expectedElfPairs.add(new SensorPairing(new Sensor(8,7), new Beacon(2,10)));
		expectedElfPairs.add(new SensorPairing(new Sensor(2,0), new Beacon(2,10)));
		expectedElfPairs.add(new SensorPairing(new Sensor(0,11), new Beacon(2,10)));
		expectedElfPairs.add(new SensorPairing(new Sensor(20,14), new Beacon(25,17)));
		expectedElfPairs.add(new SensorPairing(new Sensor(17,20), new Beacon(21,22)));
		expectedElfPairs.add(new SensorPairing(new Sensor(16,7), new Beacon(15,3)));
		expectedElfPairs.add(new SensorPairing(new Sensor(14,3), new Beacon(15,3)));
		expectedElfPairs.add(new SensorPairing(new Sensor(20,1), new Beacon(15,3)));
		
		URL fileName = getClass().getResource("SampleInput.txt");
		sensorPairingsFromInput.setFileToUse(new File(fileName.getPath()));
		sensorPairingsFromInput.setSensorPairings(expectedElfPairs);
		assertEquals(expectedElfPairs, sensorPairingsFromInput.getSensorPairings());
	}

	
	@Test
	void partOne_Answer() throws Exception {
//		sensorPairingsFromInput.populateElfPairs();
//		System.out.println(elfPairsFromInput.getTotalContainedPairs());
//		assertEquals(500, sensorPairingsFromInput.getTotalContainedPairs());
	}
}
