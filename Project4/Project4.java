import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

/*
Mathew Dela Cruz
CS471 - Optimization
Project4
*/

//assignment: program the NEH Heuristic on the Flow Shop and the Flow Shop with Blocking problems
//record: makespan and runtime for all 120 problem instances as well as optimal job sequence

//FSS & FSSB to to find makespan (run both on each problem instnace); record in latex file
//NEH is used to find the job sequence of the problem instance; record in csv file

class Project4{

    public static void main(String[] args) throws Exception {
    
        //makespan length of time that elapses from start to end of project
        
        //file format: m, n (machine, numJobs)
        //each index is the processing time of the job on that machine

        //read file to get m,n then populate matrix
        String inputFile = "1";
        //outputFiles
        String sequenceFile = "jobSchedule.csv";
        String spanTimeFile = "makespanTime.csv";

        Scanner scanner = new Scanner(new FileReader(new File(System.getProperty("user.dir") + File.separator + "Taillard_TestData" + File.separator + "Taillard_TestData",inputFile+".txt")));
        //Scanner scanner = new Scanner(new File("test.txt"));
        
        //m(machines/rows), n(jobs/columns)
        int m = scanner.nextInt();
        int n = scanner.nextInt();
        int flowMethod = 0;

        //population object
        Population population = new Population();
        //flow shop object
        FlowShops flowShop = new FlowShops();
        //NEH Heuristic object
        NEH neh = new NEH();

        int[][] processTimes = new int[m][n];

        //populate process times from file
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                processTimes[i][j] = scanner.nextInt();
            }
        }

        

        
        //sequence n jobs with NEH (record in jobSchedule.csv)
        long start = System.currentTimeMillis();
        int[] jobSequenceFSS = neh.runNEH(processTimes, m, n, flowMethod);
        long end = System.currentTimeMillis();
        long runtimeFSS = end - start;
        
        start = System.currentTimeMillis();
        int[] jobSequenceFSSB = neh.runNEH(processTimes, m, n, flowMethod+1);
        end = System.currentTimeMillis();
        long runtimeFSSB = end - start;
        
        //FSS
        int[][] nehTimesFSS = neh.sequenceTimes(processTimes, m, n, jobSequenceFSS);

        //FSSB
        int[][] nehTimesFSSB = neh.sequenceTimes(processTimes, m, n, jobSequenceFSSB);

        //find makespan with both problems on correctly ordered job sequence (record in makespanTime.csv in the LATEX format);
        int makeFSS = flowShop.FSS(nehTimesFSS, m, n);
        int makeFSSB = flowShop.FSSB(nehTimesFSSB, m, n);

        System.out.println("writing to file...");
        //jobSchedule.csv [jobSequenceFSS, jobSequenceFSSB]
        population.writeJobSequence(inputFile, jobSequenceFSS, jobSequenceFSSB, sequenceFile);

        //makespanTime.csv [makeFSS, makeFSSB, runtimeFSS, runtimeFSSB]
        population.writeSpanTimes(inputFile, m, n, makeFSS, makeFSSB, runtimeFSS, runtimeFSSB, spanTimeFile);

        /*
        //print statements for verification
        //Machines and Jobs
        System.out.println("Machines: " + m);
        System.out.println("NumJobs: " + n);
        population.printMatrix(processTimes);
        
        //FSS
        System.out.print("\n" + "Best Sequence(FSS): " );
        for(int i=0;i<jobSequenceFSS.length; i++){
            System.out.print(jobSequenceFSS[i]+1 + ", ");
        }
        System.out.println();
        population.printMatrix(nehTimesFSS);
        
        //FSSB
        System.out.print("\n" + "Best Sequence(FSSB): " );
        for(int i=0;i<jobSequenceFSSB.length; i++){
            System.out.print(jobSequenceFSSB[i]+1 + ", ");
        }
        System.out.println();
        population.printMatrix(nehTimesFSSB);

        System.out.println("\n" + "FSS Makespan: " + makeFSS);
        System.out.println("FSSB Makespan: " + makeFSSB);
        
        System.out.println("\n" + "FSS runtime(ms): " + runtimeFSS);
        System.out.println("FSSB runtime(ms): " + runtimeFSSB);
        */

    }

}