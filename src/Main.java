import file.Reading;

public class Main {

    public static void main(String[] args){
        Reading re = new Reading(args[0]);
        re.buildDictionary(true);
        re.writeLines(args[1],true);
    }
}
