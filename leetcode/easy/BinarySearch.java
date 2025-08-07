// https://leetcode.com/problems/binary-search/
// Problem: Binary Search
// Difficulty: Easy
// Tags: Array, Binary Search

class BinarySearch {
    public int search(int[] nums, int target) {
        int l = 0, r = nums.length - 1;

        while (l <= r) {
            int mid = l + (r - l) / 2;

            if (target == nums[mid]) {
                return mid; // target found
            } else if (target < nums[mid]) {
                r = mid - 1; // search in left half
            } else {
                l = mid + 1; // search in right half
            }
        }

        return -1; // target not found
    }

    // Sample test
    public static void main(String[] args) {
        BinarySearch sol = new BinarySearch();
        int[] nums = {-1, 0, 3, 5, 9, 12};
        int target = 9;
        int target2 = 2;
        System.out.println("Index of target: " + sol.search(nums, target)); // Output: 4
        System.out.println("Index of target 2: " + sol.search(nums, target2)); // Output: -1 (not found)
    }
}
