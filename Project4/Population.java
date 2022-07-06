import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;

public class Population {

    //print a given matrix (for testing purposes)
    public void printMatrix(int[][] matr){
        for(int i=0; i<matr.length; i++){
            System.out.print("Machine " + (i+1) + ": ");
            for(int j=0; j<matr[i].length; j++){
                System.out.print(matr[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void writeSpanTimes(String instance, int m, int n, int FSSspan, int FSSBspan, long FSStime, long FSSBtime, String file) throws Exception{
        String sep = "&";
        String end = "\\\\";

        String str = instance + "," + sep + "," + m + "," + sep + "," + n + "," + sep + "," + FSSspan + "," + sep + "," + FSStime + "," + sep + "," +FSSBspan+ "," + sep + "," +FSSBtime + "," + end + " \n";

        BufferedWriter writer = new BufferedWriter(new FileWriter(new File(file), true));
            writer.write(str);
            
            writer.close();
    }

    public void writeJobSequence(String instance, int[] FSSsequence, int[] FSSBsequence, String file) throws Exception{
        String str = "Instance " + instance + "\n";

        str += "FSS, "; 
        for(int i=0; i<FSSsequence.length; i++){
            str += (FSSsequence[i]+1) + ",";
        }
        str += "\n";
        str += "FSSB, ";
        for(int i=0; i<FSSBsequence.length; i++){
            str += (FSSBsequence[i]+1) + ",";
        }
        str += "\n"; 

        BufferedWriter writer = new BufferedWriter(new FileWriter(new File(file), true));
            writer.write(str);
            
            writer.close();

    }

}
