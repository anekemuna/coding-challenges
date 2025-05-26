// https://leetcode.com/problems/best-time-to-buy-and-sell-stock/description/
// Problem: Best Time to Buy and Sell Stock
// Difficulty: Easy
// Tag: Array, Greedy
// Given an array prices where prices[i] is the price of a stock on the ith day,
// return the maximum profit you can achieve from a single buy and sell.
// You must buy before you sell.

public class BestTimeToBuyAndSellStock {

    // Solution: Track minimum price and compute profit at each step
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    public int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;

        for (int price : prices) {
            minPrice = Math.min(minPrice, price);
            maxProfit = Math.max(maxProfit, price - minPrice);
        }

        return maxProfit;
    }

    // Main method for quick testing
    public static void main(String[] args) {
        BestTimeToBuyAndSellStock solver = new BestTimeToBuyAndSellStock();
        int[] prices = {7, 1, 5, 3, 6, 4};

        System.out.println("Max Profit: " + solver.maxProfit(prices)); // Output: 5
    }
}
