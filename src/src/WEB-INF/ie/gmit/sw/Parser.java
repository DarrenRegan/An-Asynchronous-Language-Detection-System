package ie.gmit.sw;

import java.io.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * Parser
 * @author Darren
 *
 */
public class Parser implements Runnable {
	//Variables
	//private String file;
	private File file;
	//private int k;
	private int kmerSize;
	//private Database db = null;
	private static LanguageDB db;

	//Constructor
	public Parser(File file, int k ) {
		this.file = file;
		this.kmerSize = k;
	}
	
	//Set Database
	public void setDB(LanguageDB db) {
		Parser.db = db;
	}

	/**
	 * (1). Reads the file, (2). splits line where @ symbol is reached, (3). remove punctuation and convert to lowercase, (4). parse
	 */
	@Override
	public void run() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			String line = null;
			
			while ((line = br.readLine()) != null) {
				String[] record = line.trim().split("@");
				if (record.length != 2) continue;
				parse(record[0], record[1]);
			}
			br.close();
		}catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	/**
	 * Parse used in run() method
	 * @param text
	 * @param lang
	 */
	public void parse(String text, String lang) {
		Language language = Language.valueOf(lang);
		
		for(int i = 0; i <= text.length() - kmerSize ; i ++) {	
			//CharSequence kmer = text.substring(i, i + k);
			String kmer = text.substring(i, i + kmerSize);
			LanguageEntry langEntry = new LanguageEntry(kmer, language);
			db.add(langEntry);
		}
	}
	
	/*public static void main(String[] args) throws Throwable{
		Parser p = new Parser("data/wili-2018-Small-11750-Edited.txt", 1);
		
		Database db = new Database();
		p.setDb(db);
		Thread t = new Thread(p);
		t.start();
		t.join();
		db.resize(300);
		
		//Irish
		String s = "testing";
		//English
		//String s = 
		//Greek
		//String s =
		//French
		//String s =
		
		p.analyseQuery(s);
		
		//System.out.println(db);
		
	}*/

}

