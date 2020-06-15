package ie.gmit.sw;

/**
 * Class split from LanguageEntry.java
 * Doing this promotes loose coupling as less components in the same class prevent potential errors from occuring if a feature were to be changed.
 * 
 * @author Darren
 *
 */
/*


Kmer Class has a Kmer, kmerStr & freq
KmerStr is converted to an int in hashcode() function
*/
public class Kmer {
	
	//Variables
	private int freq;
	private int kmer;
	private String kmerStr;

	//Constructors
	public Kmer() {
	}

	/**
	 * Constructor with freq, kmer and kmerStr
	 * @param freq
	 * @param kmer
	 * @param kmerStr
	 */
	public Kmer(int freq, int kmer, String kmerStr) {
		super();
		this.freq = freq;
		this.kmer = kmer;
		this.kmerStr = kmerStr;
	}
	
	/**
	 * Constructor with kmerStr
	 * @param kmerStr
	 */
	public Kmer (String kmerStr) {
		this.kmerStr = kmerStr;
		this.kmer = kmerStr.hashCode();
		this.freq = 1;
	}
	
	public int getFreq() {
		return freq;
	}

	public void setFreq(int freq) {
		this.freq = freq;
	}
	
	public void incFreq() {
		freq++;
	}

	@Override
	public int hashCode() {
		return kmer;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Kmer))
			return false;
		Kmer kmer123 = (Kmer) obj;
		return kmer == kmer123.kmer;
	}
	
	@Override
	public String toString() {
		return "[" + "Frequency: " + freq + "/" + "Kmer: " + kmer + "/" + "kmer String: " + kmerStr + "]";
		//return "[" + kmer + "/" + frequency + "/" + rank + "]";
	}
	
	
	
	
	
	


	
	
	
}
