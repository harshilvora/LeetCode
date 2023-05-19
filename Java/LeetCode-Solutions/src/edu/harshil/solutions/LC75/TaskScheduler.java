package edu.harshil.solutions.LC75;

import java.util.*;

/**
 *Given a characters array tasks, representing the tasks a CPU needs to do, where each letter represents a different task. Tasks could be done in any order. Each task is done in one unit of time. For each unit of time, the CPU could complete either one task or just be idle.
 *
 * However, there is a non-negative integer n that represents the cooldown period between two same tasks (the same letter in the array), that is that there must be at least n units of time between any two same tasks.
 *
 * Return the least number of units of times that the CPU will take to finish all the given tasks.
 *
 *
 *
 * Example 1:
 *
 * Input: tasks = ["A","A","A","B","B","B"], n = 2
 * Output: 8
 * Explanation:
 * A -> B -> idle -> A -> B -> idle -> A -> B
 * There is at least 2 units of time between any two same tasks.
 * Example 2:
 *
 * Input: tasks = ["A","A","A","B","B","B"], n = 0
 * Output: 6
 * Explanation: On this case any permutation of size 6 would work since n = 0.
 * ["A","A","A","B","B","B"]
 * ["A","B","A","B","A","B"]
 * ["B","B","B","A","A","A"]
 * ...
 * And so on.
 * Example 3:
 *
 * Input: tasks = ["A","A","A","A","A","A","B","C","D","E","F","G"], n = 2
 * Output: 16
 * Explanation:
 * One possible solution is
 * A -> B -> C -> A -> D -> E -> A -> F -> G -> A -> idle -> idle -> A -> idle -> idle -> A
 *
 *
 * Constraints:
 *
 * 1 <= task.length <= 104
 * tasks[i] is upper-case English letter.
 * The integer n is in the range [0, 100].
 *
 */
public class TaskScheduler {

    /**
     *
     * Runtime 3 ms Beats 68.91% O(n)
     * Memory 45.4 MB Beats 7.11% O(1)
     * Using Maths, Explanation: https://leetcode.com/problems/task-scheduler/solutions/621839/easist-java-solution-beats-99-with-explanation-12-lines/comments/1521726
     */
    public int leastIntervalMathematical(char[] tasks, int n) {

        if(n==0)
            return tasks.length;

        int[] chars = new int[26];
        int max = 0;
        for(int i=0; i<tasks.length;i++){
            int count = ++chars[tasks[i]-'A'];
            max = Math.max(max,count);
        }
        int countmax = 0;
        for(int i: chars){
            if(i==max)
                countmax++;
        }

        return Math.max(tasks.length, ((n +1)*(max-1))+countmax);
    }

    /**
     *
     * Runtime  47 ms Beats 17.89% O(n*log26) -> O(n)
     * Memory 44.9 MB Beats 7.11% O(26) -> O(1)
     *
     * Using Priority Queue
     */
    public int leastInterval(char[] tasks, int n) {

        if(n==0)
            return tasks.length;
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();

        for(int i=0; i<tasks.length;i++)
            map.put(tasks[i], map.getOrDefault(tasks[i],0)+1);

        //Start with tasks with maximum occurences
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        pq.addAll(map.values());

        int time =0;

        while(pq.size()>0){
            List<Integer> newCount = new ArrayList<Integer>();
            // Run n+1 times to form a cycle for next task
            for(int i=0; i<=n; i++){
                //To only add tasks which have cooled down
                if(pq.size() > 0){
                    int count = pq.poll();
                    count--;
                    //Add only when there is task present
                    if(count > 0)
                        newCount.add(count);
                }
                // Calculate both idle and task time
                time++;
                //End Condition when no more tasks are present
                if(pq.size()==0 && newCount.size()==0)
                    break;
            }
            if(newCount.size()>0)
                //Resort tasks with maximum first
                pq.addAll(newCount);
        }

        return time;
    }
}
