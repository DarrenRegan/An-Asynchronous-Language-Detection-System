package ie.gmit.sw;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;

public class LanguageDB implements StorageDB<LanguageEntry>{
	//Variables
	private Map<Language, KmerDB> languageData;
	private static LanguageDB instance = new LanguageDB();

	private LanguageDB() {
		this.languageData = new ConcurrentHashMap<>();
	}
	
	public static LanguageDB getInstance() {
		return instance;
	}
	
	/**
	 * Prediction for language used in the text query
	 * @param query Kmer map that contains all kmers and kmer freqs
	 * @return returns first language with lowest out of place distance in the treeset
	 */
	public Language getLanguage(KmerDB query) {
		TreeSet<OutOfPlaceMetric> oopm = new TreeSet<>();
		
		Set<Language> langs = languageData.keySet();
		for (Language lang : langs) {
			oopm.add(new OutOfPlaceMetric(lang, languageData.get(lang).getOutOfPlaceDistance(query)));
		}
		return oopm.first().getLanguage();
	}
	
	/**
	 * Adds kmer to kmerDB
	 * @param langEntry
	 */
	@Override
	public void add(LanguageEntry langEntry) {
		Kmer kmer = new Kmer(langEntry.getKmer());
		
		if (languageData.containsKey(langEntry.getLanguage())) {
			KmerDB kmerDatabase = languageData.get(langEntry.getLanguage());
			kmerDatabase.add(kmer);
		} else {
			languageData.put(langEntry.getLanguage(), new KmerDB(kmer));
		}
	}//add

}//end
