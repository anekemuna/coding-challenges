// https://leetcode.com/problems/best-time-to-buy-and-sell-stock/description/
// Problem: Best Time to Buy and Sell Stock
// Difficulty: Easy

class Solution {
    public int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;

        for (int price : prices) {
            minPrice = Math.min(minPrice, price);
            maxProfit = Math.max(maxProfit, price - minPrice);
        }

        return maxProfit;
    }
}