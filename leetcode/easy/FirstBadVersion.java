// https://leetcode.com/problems/first-bad-version/
// Problem: First Bad Version
// Difficulty: Easy
// Tags: Binary Search

public class FirstBadVersion extends VersionControl {
    public int firstBadVersion(int n) {
        int l = 1, r = n;

        while (l < r) {
            int mid = l + (r - l) / 2; // find mid

            if (isBadVersion(mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }

        return l;
    }

    // For testing purposes
    public static void main(String[] args) {
        FirstBadVersion fbv = new FirstBadVersion();
        fbv.setBadVersion(4); // Set the first bad version for testing
        int n = 5;
        System.out.println("First bad version is: " + fbv.firstBadVersion(n));
    }
}

// Mocked VersionControl base class
class VersionControl {
    private int badVersion;

    // Simulates the API
    public boolean isBadVersion(int version) {
        return version >= badVersion;
    }

    // Used to set the first bad version for testing
    public void setBadVersion(int badVersion) {
        this.badVersion = badVersion;
    }
}
