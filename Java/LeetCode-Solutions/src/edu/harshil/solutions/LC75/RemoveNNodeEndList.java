package edu.harshil.solutions.LC75;

/**
 * Given the head of a linked list, remove the nth node from the end of the list and return its head.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: head = [1,2,3,4,5], n = 2
 * Output: [1,2,3,5]
 * Example 2:
 * <p>
 * Input: head = [1], n = 1
 * Output: []
 * Example 3:
 * <p>
 * Input: head = [1,2], n = 1
 * Output: [1]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the list is sz.
 * 1 <= sz <= 30
 * 0 <= Node.val <= 100
 * 1 <= n <= sz
 * <p>
 * <p>
 * Follow up: Could you do this in one pass?
 */
public class RemoveNNodeEndList {

    /**
     * Basic Approach
     * Runtime 0 ms Beats 100%
     * Memory 40.9 MB Beats 28.15%
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        int size = 0;

        ListNode node = head;
        while(node != null){
            node = node.next;
            size++;
        }
        if(size == 1)
            return null;
        if(size == n)
            return head.next;

        node = head;
        int pos = 0;


        while(node != null){
            if(pos==size - n-1){
                if(node.next != null){
                    node.next = node.next.next;
                }
                else{
                    node.next = null;
                }
                break;
            }
            node = node.next;
            pos++;
        }

        return head;
    }

    /**
     * One Pass Two Pointer Approach
     * Runtime 0 ms Beats 100% O(N)
     * Memory 40.6 MB Beats 56.93% O(N)
     */
    public ListNode removeNthFromEndOnePass(ListNode head, int n) {
        ListNode fast = head;
        ListNode slow = head;
        for (int i = 0; i < n; i++)
            fast = fast.next;
        if (fast == null)
            return head.next;

        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return head;


    }

    class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
