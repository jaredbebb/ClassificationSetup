package lin;

import java.util.HashMap;



public class StringFormat {
	String currentLine;
	StopWords stopWords;
	HashMap<String, Integer> words;
	
	
	public StringFormat(String currentLine){
		this.currentLine = currentLine;
		stopWords = new StopWords();
		words = new HashMap<String, Integer>();
	}

	
	public void RemovePunctuationLower(){
		currentLine = currentLine.toLowerCase();
		currentLine = currentLine.replaceAll("[^a-z\\s]", "");
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
	
	public String Get(){
		return currentLine;
	}
	
	public static void main(String[] args){
		StringFormat sf = new StringFormat("The  big Brown co.w ./()^%  ");
		sf.RemovePunctuationLower();
		System.out.println(sf.currentLine.equals("the big brown cow"));
		sf.RemoveStopWords();
		System.out.println(sf.currentLine);
	}
}



