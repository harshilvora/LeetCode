package edu.harshil.solutions.LC75;

import java.util.HashSet;

/**
 * 202. Happy Number
 * Easy
 * 8.4K
 * 1.1K
 * Companies
 * Write an algorithm to determine if a number n is happy.
 * <p>
 * A happy number is a number defined by the following process:
 * <p>
 * Starting with any positive integer, replace the number by the sum of the squares of its digits.
 * Repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1.
 * Those numbers for which this process ends in 1 are happy.
 * Return true if n is a happy number, and false if not.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: n = 19
 * Output: true
 * Explanation:
 * 12 + 92 = 82
 * 82 + 22 = 68
 * 62 + 82 = 100
 * 12 + 02 + 02 = 1
 * Example 2:
 * <p>
 * Input: n = 2
 * Output: false
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= n <= 231 - 1
 */
public class HappyNumber {

    /**
     * Runtime 2 ms Beats 19.33% O(N)
     * Memory 39.6 MB Beats 59.65% O(N)
     */
    public boolean isHappy(int n) {

        HashSet<Integer> set = new HashSet<Integer>();
        while (!set.contains(n) && n != 1) {
            set.add(n);
            n = getSum(n);
        }

        return n == 1;
    }

    public int getSum(int num) {
        int sum = 0;

        while (num > 0) {
            sum += (num % 10) * (num % 10);
            num /= 10;
        }

        return sum;
    }
}
