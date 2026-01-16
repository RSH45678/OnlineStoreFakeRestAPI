package pojo;

public class Geolocation {

	private String lat;
	private String longi;
	
	//Constructor 
		public Geolocation(String lat, String longi)
		{
			this.lat=lat;
			this.longi=longi;
		}
		
	//Getters and Setters
	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLongi() {
		return longi;
	}

	public void setLongi(String longi) {
		this.longi = longi;
	}
	
}
