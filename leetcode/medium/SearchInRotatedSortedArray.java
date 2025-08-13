class SearchInRotatedSortedArray {
    public int search(int[] nums, int target) {
        int l = 0, r = nums.length - 1;

        while (l <= r) {
            int mid = l + (r - l) / 2; // find the middle index

            // target == nums[mid] i.e. middle element is target
            if (nums[mid] == target) return mid;

            // check if left half is sorted
            if (nums[l] <= nums[mid]) {
                if (target < nums[l] || target > nums[mid]) {
                    l = mid + 1; // search right half
                } else {
                    r = mid - 1; // search left half
                }
            } 
            // otherwise right half is sorted
            else {
                if (target > nums[r] || target < nums[mid]) {
                    r = mid - 1; // search left half
                } else {
                    l = mid + 1; // search right half
                }
            }
        }

        return -1; // target not found
    }

    // Main method to test the solution
    public static void main(String[] args) {
        SearchInRotatedSortedArray sol = new SearchInRotatedSortedArray();

        int[] nums1 = {4,5,6,7,0,1,2};
        int target1 = 0;
        System.out.println("Index of " + target1 + ": " + sol.search(nums1, target1)); // Expected: 4

        int[] nums2 = {4,5,6,7,0,1,2};
        int target2 = 3;
        System.out.println("Index of " + target2 + ": " + sol.search(nums2, target2)); // Expected: -1

        int[] nums3 = {1};
        int target3 = 0;
        System.out.println("Index of " + target3 + ": " + sol.search(nums3, target3)); // Expected: -1

        int[] nums4 = {1,3};
        int target4 = 3;
        System.out.println("Index of " + target4 + ": " + sol.search(nums4, target4)); // Expected: 1
    }
}
