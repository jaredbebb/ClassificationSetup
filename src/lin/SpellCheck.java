package lin;

public class SpellCheck {

    Levenshtein leven;
    Dictionary dict;

    public SpellCheck(){
        leven = new Levenshtein();
        dict = new Dictionary();
    }

    public String change(String word){
        String improved=null;
        if(!dict.check(word)){
            improved = "new word";
            System.out.println("");

        }

        return improved;
    }
}
