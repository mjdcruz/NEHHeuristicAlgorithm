//finds makespan value of a set of data that has machines and job times

public class FlowShops {

    public int FSS(int[][] processTimes, int m, int n){
        int[][] completeTimes = new int[m][n];

        //calculate complete times for all machines with first job
        int sum = 0;
        for(int i=0; i<m; i++){
            sum += processTimes[i][0];
            completeTimes[i][0] = sum;
        }
        //calculate complete times for all jobs with first machine
        sum = 0;
        for(int i=0; i<n; i++){
            sum += processTimes[0][i];
            completeTimes[0][i] = sum;
        }

        //find complete times for the rest of jobs and machines
        for(int i=1; i<m; i++){
            for(int j=1; j<n; j++){
                completeTimes[i][j] = (Math.max(completeTimes[i-1][j], completeTimes[i][j-1])) + processTimes[i][j];
            }
        }
        return completeTimes[m-1][n-1];
    }

    public int FSSB(int[][] processTimes, int m, int n){
        int[][] completeTimes = new int[m][n];

        //calculate complete times for all machines with first job
        int sum = 0;
        for(int i=0; i<m; i++){
            sum += processTimes[i][0];
            completeTimes[i][0] = sum;
        }

        //find complete times for the rest of jobs and machines
        for(int j=1; j<n; j++){
            for(int i=0; i<m; i++){
                if(i == 0){
                    completeTimes[i][j] = Math.max((completeTimes[i][j-1] + processTimes[i][j]),(completeTimes[i+1][j-1]));
                }
                if(i == m-1){
                    completeTimes[i][j] = completeTimes[i-1][j] + processTimes[i][j];
                }
                if(i > 0 && i < m-1){
                    completeTimes[i][j] = Math.max((completeTimes[i-1][j] + processTimes[i][j]),(completeTimes[i+1][j-1]));
                }
            }
        }
        return completeTimes[m-1][n-1];
    }
    
}
