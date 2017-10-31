package lin;


import java.util.Arrays;

/**
 * Levenshtein Distance
 */
public class Levenshtein {
    private int[][] matrix;
    private static int substitition =1;
    private static int insertion =1;
    private static int deletion =1;
    private static int same =0;


    /**
     * Levenshtein Distance
     */
    public Levenshtein(){
    }

    /**
     *
     * @param current string 1
     * @param compareTo string 2
     * @return matrix[n][m]
     */
    public int compute(String current, String compareTo){
        int left,top,diagonal;
        int cost;
        int n = current.length()+1;
        int m = compareTo.length()+1;
        matrix = new int[n][m];
        for(int s=0; s < n; s++){
            matrix[s][0]=s;
        }
        for(int t=0;t<m;t++){
            matrix[0][t]=t;
        }
        for(int i=1; i < n; i++ ){
            for(int j=1; j<m;j++){
                String curr = current.substring(i-1,i);
                String comp = compareTo.substring(j-1,j);
                if(curr.equals(comp)){
                    cost = same;
                }
                else{
                    cost=1;
                }
                top=matrix[i-1][j];
                left=matrix[i][j-1];
                diagonal=matrix[i-1][j-1];
                matrix[i][j]=min(top, left, diagonal)+cost;
            }
        }
        return matrix[n-1][m-1];
    }
    public void printMatrix(){
        for(int i =0; i < matrix.length;i++ ){
            System.out.println();
            for(int j=0; j < matrix[1].length;j++){
                System.out.print(matrix[i][j]+" ");
            }
        }
        System.out.println();
    }
    public int min(int ... args){
        Arrays.sort(args);
        return args[0];
    }
}
