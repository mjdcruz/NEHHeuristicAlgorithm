# NEHHeuristicAlgorithm
An implementation of the Nawaz, Enscore, Ham Heursitc Algorithm on two flowshop variants (with and without blocking)

Mathew Dela Cruz
Project 4
CS471

HOW TO RUN

PROBLEM CALCULATION
1. Unzip Contents of "Mathew_DelaCruz.zip"
2. Navigate and open "Project4.java" in your preferred Java IDE
3. In "Project4.java", specify which instance dataset file to use with the "inputFile" variable on line 27 (numbers 1-120)
4. Run the program (FSS and FSSB results will be calculated for each run)
5. Output for best job schedules will be in "jobSchedule.csv" file
6. Output for best makespan and runtime will be in "makespanTime.csv" file
*** Note, all results have already been recorded and running the program again will simply add to these files ***
*** "maksespanTime.csv" if formatted to be used in a LATEX file ***

FILE STRUCTURE
Project4.java - this file serves as the main driver file for the code execution
    This file calls methods from "Population.java", "FlowShops.java", and "NEH.java" in order to calculate results
    *** The bottom of the file includes print statements to keep track of results during experimentation that were commented out for submission
    

Population.java
Methods:
    printMatrix: Prints a given matrix in the format of all jobs on each machine (for the sake of experimentation only, not calculation)
    writeSpanTimes: Takes in result for makespan and total runtime of both FSS and FSSB then writes them to "makespanTime.csv"
    writeJobSequence: Takes in the resulting optimal job sequence for FSS and FSSB then writes them to "jobSchedule.csv"

FlowShops.java - This file contains functions for FSS and FSSB
    Both functions take in a dataset of process times and returns the caclulated makespan using one of the specified methods

NEH.java - This file contains the implementation of the NEH Heuristic algorithm
Methods:
    runNEH: Begin execution and proper setup of NEH
    bestJobSequence: Takes in the setup calculated  with runNEH and recursively finds the optimal job order sequence
    sequenceTimes: Takes in process times data and a sequence then generated a new dataset of process times in the order of the given sequence
    insertElement: Inserts an element into an array (used to create permutations)
    
TaliardTestData - this file includes all of the execution times for each machine per each job and is used as the input data for experimentation
