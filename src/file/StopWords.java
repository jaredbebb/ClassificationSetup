package file;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class StopWords {
	
	HashMap<String, Integer> stopWords;
	String filelocation;
	
	public StopWords(){
		String workingDirectory = System.getProperty("user.dir");
		stopWords = new HashMap<String, Integer>();
		filelocation = workingDirectory+"\\src\\file\\stopwords.txt";
		Load();
	}
	
	public void Load(){
		try(BufferedReader br = new BufferedReader(new FileReader(filelocation))){
			for(String line; (line = br.readLine()) != null;){
				stopWords.put(line, 1);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * @return false if is not stopword
	 */
	public boolean Check(String word){
		if(stopWords.containsKey(word)){
			return true;
		}
		else{
			return false;
		}	
	}
	
	/**
	 * @return stopwords
	 */
	public HashMap<String, Integer> Get(){
		return stopWords;
	}
	

}
