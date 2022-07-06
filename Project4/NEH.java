import java.util.Arrays;

//finds and returns the best(minimum) job sequence of a dataset including machines and job times

public class NEH {
    
    Population pop = new Population();
    FlowShops flow = new FlowShops();

    //m(machines/rows), n(jobs/columns)
    public int[] runNEH(int[][] processTimes, int m, int n, int flowMethod){
        int[][] timeTotals = new int[n][2];

        //for loop that creates 2d array of total proc times and order sequence
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                timeTotals[j][0] += 0 + processTimes[i][j];
                timeTotals[j][1] = j;
            }
        }

        //step 1 & 2: sequence jobs in decreasing time
        Arrays.sort(timeTotals, (a, b) -> Double.compare(b[0], a[0]));
        
        //create jobSequence array after it is ordered
        //jonSequence holds the order of decreasing time of the dataset
        int[] jobSequence = new int[n];
        for(int i=0; i<n; i++){
            jobSequence[i] = timeTotals[i][1];
        }

        //step 3: Find best partial sequence of first two jobs
        int[][] currentSequence = new int[m][2];
        for(int i=0; i<m; i++){
            for(int j=0; j<2; j++){
                currentSequence[i][j] = processTimes[i][jobSequence[j]];
            }
        }
        int[][] compareSequence = new int[m][2];
        for(int i=0; i<m; i++){
            for(int j=0; j<2; j++){
                compareSequence[i][j] = processTimes[i][jobSequence[2-j-1]];
            }
        }
        if(flow.FSS(compareSequence, m, 2) < flow.FSS(currentSequence, m, 2)){
            int temp = jobSequence[0];
            jobSequence[0] = jobSequence[1];
            jobSequence[1] = temp;
        }

        //step 4: select next in jobSequence and find best makespan order
        bestJobSequence(processTimes, jobSequence, n, m, flowMethod);

        return jobSequence;
    }

    //returns best job sequence out of all permutations
    public int[] bestJobSequence(int[][] processTimes, int[] jobSequence, int n, int m, int flowMethod){
        //always start with permutations of 3rd job
        int currentJob = 3;

        while(currentJob <= n){
            //create initial bestSequence of first 3 jobs {3, 1, 2}
            int[] bestSequence = new int[currentJob];
            for(int i=0; i<currentJob; i++){
                bestSequence[i] = jobSequence[i];
            }
            //create permutable sequence {3, 1}
            int[] permuteSequence = new int[currentJob-1];
            for(int i=0; i<currentJob-1; i++){
                permuteSequence[i] = jobSequence[i];
            }

            //find best makespan out of all job permutations
            for(int i=0; i<currentJob; i++){
                //bestTimes holds time matrix in best order
                int[][] bestTimes = sequenceTimes(processTimes, m, currentJob, bestSequence);
                int[] compareSequence = insertElement(permuteSequence.length, permuteSequence, jobSequence[currentJob-1], i);
                //compareTimes holds time matrix for new permutation to compare
                int[][] compareTimes = sequenceTimes(processTimes, m, currentJob, compareSequence);

                //if permutation makespan is less than best makespan, new best is permutation
                if(flowMethod == 0){
                    if(flow.FSS(compareTimes, m, compareTimes[0].length) < flow.FSS(bestTimes, m, bestTimes[0].length)){
                        bestSequence = compareSequence;
                    }
                }else{
                    if(flow.FSSB(compareTimes, m, compareTimes[0].length) < flow.FSSB(bestTimes, m, bestTimes[0].length)){
                        bestSequence = compareSequence;
                    }
                }
            }
            //update job sequence to match best sequence
            for(int i=0; i<currentJob; i++){
                jobSequence[i] = bestSequence[i];
            }
            currentJob++;
        }
        return jobSequence;
    }

    //create time matrix with according sequence
    public int[][] sequenceTimes(int[][] processTimes, int m, int n, int jobSequence[]){
        int[][] nehTimes = new int[m][n];
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                nehTimes[i][j] = processTimes[i][jobSequence[j]];
            }
        }
        return nehTimes;
    }

    //insert element at certain index
    public static int[] insertElement(int n, int arr[], int element, int index)
    {
        int newarr[] = new int[n + 1];

        if(index == 0){
            newarr[0] = element;
            for(int i=1; i<n; i++){
                newarr[i] = arr[i-1];
            }
        }else{

        for (int i = 0; i < n + 1; i++) {
            if (i < index - 1)
                newarr[i] = arr[i];
            else if (i == index - 1)
                newarr[i] = element;
            else
                newarr[i] = arr[i - 1];
        }
        }
        return newarr;
    }
}
