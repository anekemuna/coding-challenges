// https://leetcode.com/problems/insert-interval/
// Problem: Insert Interval
// Difficulty: Medium
// Tag: Array, Sorting

import java.util.ArrayList;
import java.util.List;

public class InsertInterval {

    // Insert and merge overlapping intervals
    // Time Complexity: O(n)
    // Space Complexity: O(n)
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> merged = new ArrayList<>();
        int i = 0, n = intervals.length;
        int newStart = newInterval[0], newEnd = newInterval[1];

        // Add all intervals before the new one (no overlap)
        while (i < n && intervals[i][1] < newStart) {
            merged.add(intervals[i]);
            i++;
        }

        // Merge overlapping intervals
        while (i < n && intervals[i][0] <= newEnd) {
            newStart = Math.min(intervals[i][0], newStart);
            newEnd = Math.max(intervals[i][1], newEnd);
            i++;
        }
        merged.add(new int[] { newStart, newEnd });

        // Add the rest
        while (i < n) {
            merged.add(intervals[i]);
            i++;
        }

        return merged.toArray(new int[merged.size()][]);
    }

    public static void main(String[] args) {
        InsertInterval solver = new InsertInterval();
        int[][] intervals = {{1, 3}, {6, 9}};
        int[] newInterval = {2, 5};

        int[][] result = solver.insert(intervals, newInterval);
        System.out.println("Merged Intervals:");
        for (int[] interval : result) {
            System.out.println("[" + interval[0] + ", " + interval[1] + "]");
        }
    }
}

