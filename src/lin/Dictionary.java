package lin;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;


public class Dictionary {

    HashMap<String, Integer> dict;
    String filelocation;
    String[] arr;

    public Dictionary(){
        String workingDirectory = System.getProperty("user.dir");
        dict = new HashMap<String, Integer>();
        filelocation = workingDirectory+"\\src\\lin\\words_alpha.txt";
        load();
    }

    /**
     * inserts words.toUpper into hashmap
     * Set might be less memory consumption than Hashmap
     */
    public void load() {
        try(BufferedReader br = new BufferedReader(new FileReader(filelocation))){
            for(String line; (line = br.readLine()) != null;){
                dict.put(line.toUpperCase(), 1);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    /**
     * @return false if is not in dictionary
     */
    public boolean check(String word){
        if(dict.containsKey(word)){
            return true;
        }
        else{
            return false;
        }
    }

    public String[] toArray(){
        arr = (String[]) dict.keySet().toArray();
        return arr;
    }

    /**
     * needs some TLC
     * should index my alphabet for faster word lookup
     * A,B,C..locations in arr
     */
    public void index(){
        char [] letters = {};

        for (char alphabet = 'A'; alphabet <= 'Z'; alphabet++) {
            System.out.println(alphabet);
        }
    }

    public void ngrams() throws IOException {

    }

    /**
     *
     * @return size of dictionary
     */
    public int size(){
        return dict.size();
    }

    /**
     * @return dictionary, a HashMap<String, Integer>
     */
    public HashMap<String, Integer> Get(){
        return dict;
    }
}
