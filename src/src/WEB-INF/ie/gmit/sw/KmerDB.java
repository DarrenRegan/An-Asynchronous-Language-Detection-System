package ie.gmit.sw;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.concurrent.*;

/**
 * Split Kmer & Language parts of Database.java into separate databases to promote Loose Coupling
 * @author Darren
 *
 */
public class KmerDB implements StorageDB<Kmer>{
	//Variables
	private Map<Kmer, Kmer> database;
	
	//Constructors
	public KmerDB(Map<Kmer, Kmer> database) {
		super();
		this.database = database;
	}
	
	public KmerDB(String text, int kmerSize){
		this.database = new ConcurrentHashMap<>();
		createDatabase(text, kmerSize);
	}
	
	public KmerDB(Kmer kmer) {
		this.database = new ConcurrentHashMap<>();
		add(kmer);
	}

	/**
	 * Creaes kmer database
	 * Each kmer object has a kmer text and kmerSize
	 * @param text query text for language dectecting
	 * @param kmerSize size of the kmer
	 */
	private void createDatabase(String text, int kmerSize) {
		
		for(int i = 0; i <= text.length() - kmerSize; i++) {
			String kmerStr = (text.substring(i, i + kmerSize));
			Kmer kmer = new Kmer(kmerStr);
			
			if(database.containsKey(kmer)) {
				database.get(kmer).incFreq();
				database.put(kmer, kmer);
			} else {
				database.put(kmer, kmer);
			}
		}
	}//createDatabase

	/**
	 * Method add() adds kmers to the database
	 * @param kmer object is kmer + the freq
	 */
	@Override
	public void add(Kmer kmer) {
		if (database.containsKey(kmer)) {
			database.get(kmer).incFreq();
		}else {
			database.put(kmer, kmer);
		}
	}//add
	
	/**
	 * Calc the outOfPlaceDistance
	 * @param query is the kmer and its freq
	 * @return returns the distance
	 */
	int getOutOfPlaceDistance(KmerDB query){
		int distance = 0;
		
		List<Kmer> q = query.values();
		List<Kmer> db = query.values();
		
		for (int i = 0; i < q.size(); i++) {
			Kmer kmer = q.get(i);
			if (database.containsKey(kmer)) {
				distance += db.indexOf(kmer) - i;
			} else {
				distance += db.size() + 1;
			}
		}
		return distance;
	}//getOutOfPlaceDistance

	//Adapted from https://stackoverflow.com/questions/30425836/java-8-stream-map-to-list-of-keys-sorted-by-values
	/**
	 * values for list of kmers
	 * @return returns a list of sorted kmers is reversed order
	 */
	private List<Kmer> values() {
		return database.keySet().stream()
				.sorted(Comparator.comparingInt(kmer -> kmer.getFreq() * -1))
				.collect(Collectors.toList());
	}//values

}//end
