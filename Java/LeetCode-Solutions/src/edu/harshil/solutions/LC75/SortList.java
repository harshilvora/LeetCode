package edu.harshil.solutions.LC75;

/**
 *
 * Given the head of a linked list, return the list after sorting it in ascending order.
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: head = [4,2,1,3]
 * Output: [1,2,3,4]
 * Example 2:
 * <p>
 * <p>
 * Input: head = [-1,5,3,4,0]
 * Output: [-1,0,3,4,5]
 * Example 3:
 * <p>
 * Input: head = []
 * Output: []
 */
public class SortList {

    /**
     * Runtime 12 ms Beats 58.68% O(NlogN)
     * Memory 50.4 MB Beats 72.4% O(N)
     */
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        //Merge Sort
        ListNode mid = getMid(head);
        ListNode left = sortList(head);
        ListNode right = sortList(mid);
        return mergeSort(left, right);

    }

    public ListNode mergeSort(ListNode left, ListNode right) {
        ListNode temp = new ListNode();
        ListNode tempHead = temp;
        while (left != null && right != null) {
            if (left.val < right.val) {
                tempHead.next = left;
                left = left.next;
            } else {
                tempHead.next = right;
                right = right.next;
            }
            tempHead = tempHead.next;
        }
        if (right != null)
            tempHead.next = right;
        else if (left != null)
            tempHead.next = left;
        return temp.next;
    }

    public ListNode getMid(ListNode list) {
        ListNode slow = list;
        ListNode fast = list;
        ListNode mid = null;
        while (fast != null && fast.next != null) {
            mid = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        mid.next = null;
        return slow;
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
