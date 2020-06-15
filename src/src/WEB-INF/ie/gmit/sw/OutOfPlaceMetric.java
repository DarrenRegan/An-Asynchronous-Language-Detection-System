package ie.gmit.sw;

//
/**
 * OutOfPlaceMetric split from Database.java
 * Doing this promotes loose coupling as less components in the same class prevent 
 * potential errors from occuring if a feature were to be changed.
 * @author Darren
 *
 */
public class OutOfPlaceMetric implements Comparable<OutOfPlaceMetric>{
	private Language lang;
	private int distance;
	
	public OutOfPlaceMetric(Language lang, int distance) {
		super();
		this.lang = lang;
		this.distance = distance;
	}

	public Language getLanguage() {
		return lang;
	}
	/**
	 * get distance and return
	 * @return distance
	 */
	public int getAbsoluteDistance() {
		return Math.abs(distance);
	}

	/**
	 * compare distances
	 */
	@Override
	public int compareTo(OutOfPlaceMetric o) {
		return Integer.compare(this.getAbsoluteDistance(), o.getAbsoluteDistance());
	}

	@Override
	public String toString() {
		return "[lang=" + lang + ", distance=" + getAbsoluteDistance() + "]";
	}
}