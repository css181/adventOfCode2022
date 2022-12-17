package day15;

public class SensorPairing {

	private Sensor sensor;
	private Beacon beacon;
	
	protected Sensor getSensor() {
		return sensor;
	}

	protected void setSensor(Sensor sensor) {
		this.sensor = sensor;
	}

	protected Beacon getBeacon() {
		return beacon;
	}

	protected void setBeacon(Beacon beacon) {
		this.beacon = beacon;
	}

	public SensorPairing(Sensor sensor, Beacon beacon) {
		super();
		this.sensor = sensor;
		this.beacon = beacon;
	}

	@Override
    public String toString() {
    	String print = "Sensor: " + sensor + ", Beacon: " + beacon;
		return print;
    } 
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        if(!(obj instanceof SensorPairing)) { return false; }
        SensorPairing other = (SensorPairing) obj;

        if(!this.sensor.equals(other.sensor)) { return false; }
        if(!this.beacon.equals(other.beacon)) { return false; }
        
        return true;
    }
}
