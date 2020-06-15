package ie.gmit.sw;

/**
 * 
 * @author Darren
 *
 */
public class LanguageEntry{
	private String kmer;
	private Language language;
	//private int frequency;
	//private int rank;
	

	public LanguageEntry(String kmer, Language language) {
		this.kmer = kmer;
		this.language = language;
	}

	public String getKmer() {
		return kmer;
	}

	public Language getLanguage() {
		return language;
	}
}