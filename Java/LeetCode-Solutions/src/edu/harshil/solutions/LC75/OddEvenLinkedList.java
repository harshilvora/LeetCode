package edu.harshil.solutions.LC75;

/**
 * https://leetcode.com/problems/odd-even-linked-list/
 * 328. Odd Even Linked List
 * Medium
 * 8.1K
 * 444
 * Companies
 * Given the head of a singly linked list, group all the nodes with odd indices together followed by the nodes with even indices, and return the reordered list.
 *
 * The first node is considered odd, and the second node is even, and so on.
 *
 * Note that the relative order inside both the even and odd groups should remain as it was in the input.
 *
 * You must solve the problem in O(1) extra space complexity and O(n) time complexity.
 *
 *
 */
public class OddEvenLinkedList {
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

    /**
     * Runtime 0 ms Beats 100% O(N)
     * Memory 42.2 MB Beats 48.36% O(1)
     */
    public ListNode oddEvenList(ListNode head) {
        if (head == null)
            return head;
        if (head.next == null)
            return head;
        ListNode odd = head;
        ListNode even = head.next;
        ListNode evenStart = even;
        while (even != null && even.next != null) {
            odd.next = odd.next.next;
            even.next = even.next.next;
            odd = odd.next;
            even = even.next;
        }
        odd.next = evenStart;
        return head;

    }

}
