// https://leetcode.com/problems/maximum-profit-in-job-scheduling/
// Problem: Maximum Profit in Job Scheduling
// Difficulty: Hard
// Tags: Dynamic Programming, Binary Search, Sorting, Array

import java.util.*;

public class MaximumProfitInJobScheduling {

    // helper class to store job details
    static class Job {
        int start, end, profit;

        public Job(int start, int end, int profit) {
            this.start = start;
            this.end = end;
            this.profit = profit;
        }
    }

    // binary search for the last non-conflicting job
    private int binarySearchHelper(Job[] jobs, int currIndex) {
        int l = 0, r = currIndex - 1; // subset to search, doesn't include the current job
        int result = -1; // if all prev jobs overlap

        while (l <= r) {
            int mid = l + ((r - l) / 2); 

            if (jobs[mid].end <= jobs[currIndex].start) {
                result = mid; // valid non-conflicting job
                l = mid + 1; // if valid, let's ssee if we can find a later non-conflicting one

            } else {
                // if prev job ends are greater than currjob start (i,e. overlaps)...
                // ...search jobs with earlier end times (note: jobs is sorted by end time)
                r = mid - 1;
            }
        }

        return result;
    }

    // Bottom-Up DP + Binary Search
    // Time: O(n log n), Space: O(n)
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int n = startTime.length;

        // 1. Combine start, end, profit into job objects
        Job[] jobs = new Job[n];
        for (int i = 0; i < n; i++) {
            jobs[i] = new Job(startTime[i], endTime[i], profit[i]);
        }

        // 2. Sort jobs by end time
        Arrays.sort(jobs, (j1, j2) -> Integer.compare(j1.end, j2.end));

        // 3. DP array to store max profit until each job
        int[] dp = new int[n];
        dp[0] = jobs[0].profit;

        for (int i = 1; i < n; i++) {
            int currProfit = jobs[i].profit; // initial profit of current job

            // find the index of the LAST non-conflicting job
            int noConflictIndex = binarySearchHelper(jobs, i);

            // if a non-conflicting job is found, add nonconflicting job profit
            if (noConflictIndex != -1) {
                currProfit += dp[noConflictIndex];
            }

            // store the max profit
            dp[i] = Math.max(dp[i - 1], currProfit);
        }

        // return the last
        return dp[n - 1];
    }

    // Sample Test
    public static void main(String[] args) {
        MaximumProfitInJobScheduling solution = new MaximumProfitInJobScheduling();
        int[] startTime = {1, 2, 3, 3};
        int[] endTime = {3, 4, 5, 6};
        int[] profit = {50, 10, 40, 70};

        System.out.println("Maximum Profit: " + solution.jobScheduling(startTime, endTime, profit));
        // Expected Output: 120
    }
}
