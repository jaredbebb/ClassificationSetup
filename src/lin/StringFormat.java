package lin;


import java.util.HashMap;

import org.tartarus.snowball.ext.PorterStemmer;

public class StringFormat {
	private String currentLine;
	StopWords stopWords;
	HashMap<String, Integer> words;

	/**
	 *
	 * @param currentLine
	 */
	public StringFormat(String currentLine){
		this.currentLine = currentLine;
		stopWords = new StopWords();
		words = new HashMap<String, Integer>();
	}

	/**
	 *
	 */
	public StringFormat() {

	}


	public void RemovePunctuation(){
		currentLine = currentLine.replaceAll("[^a-zA-Z\\s]", "");
		currentLine = currentLine.replaceAll(" +", " ");
		currentLine = currentLine.trim();
	}

	public void RemoveStopWords(){
		String removed = "";
		for(String word: currentLine.split(" ") ){
			if(!stopWords.Check(word)){
				removed = removed+" "+word;
			}
		}
		currentLine = removed.trim();
	}

	/**
	 *
	 * @return current line, String
	 */
	public String Get(){
		return currentLine;
	}

	public void Set(String currentLine){
		this.currentLine = currentLine;
	}

	/**
	 * @param word String
	 * @return stemmed String
	 */
	public String stem(String word){
		PorterStemmer stem = new PorterStemmer();
		stem.setCurrent(word.toLowerCase());
		stem.stem();
		return stem.getCurrent().toUpperCase();
	}
}



