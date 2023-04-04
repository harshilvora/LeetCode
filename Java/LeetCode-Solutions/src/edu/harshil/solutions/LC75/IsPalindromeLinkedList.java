package edu.harshil.solutions.LC75;


/**
 * https://leetcode.com/problems/palindrome-linked-list
 * Given the head of a singly linked list, return true if it is a
 * palindrome  or false otherwise.
 *
 * Constraints:
 *
 * The number of nodes in the list is in the range [1, 105].
 * 0 <= Node.val <= 9
 *
 *
 * Follow up: Could you do it in O(n) time and O(1) space?
 *
 *
 */
class IsPalindromeLinkedList {
    //  Definition for singly-linked list.
    class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    /**
     * Runtime 6 ms Beats 46.28% O(n)
     * Memory 63.5 MB Beats 17.61% O(1)
     *
     */
    public boolean isPalindrome(ListNode head) {

        ListNode first = head;
        ListNode firstEnd = getFirstHalf(head);
        ListNode secondStart = reverseList(firstEnd.next);
        while(first !=firstEnd.next && secondStart != null){
            if(first.val != secondStart.val)
            {
                return false;
            }
            first = first.next;
            secondStart = secondStart.next;
        }
        return true;
    }

    public ListNode reverseList(ListNode list){
        ListNode prev = null;

        ListNode head = list;
        while(head != null){
            ListNode nextNode = head.next;
            head.next = prev;
            prev = head;
            head = nextNode;
        }
        return prev;
    }

    public ListNode getFirstHalf(ListNode list){
        ListNode slow = list;
        ListNode fast = list;
        while(fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}