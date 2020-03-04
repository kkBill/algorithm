### 链表问题汇总

题目列表：

1. [2. Add Two Numbers](https://leetcode-cn.com/problems/add-two-numbers/) [一星]
2. [445. Add Two Numbers II](https://leetcode-cn.com/problems/add-two-numbers-ii/) [五星]
3. [19. Remove Nth Node From End of List](https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/) [三星]
4. [21. Merge Two Sorted Lists](https://leetcode-cn.com/problems/merge-two-sorted-lists/) [二星]
5. [23. Merge k Sorted Lists](https://leetcode-cn.com/problems/merge-k-sorted-lists/) [五星]
6. [24. Swap Nodes in Pairs](https://leetcode-cn.com/problems/swap-nodes-in-pairs/) [五星+]
7. [25. Reverse Nodes in k-Group](https://leetcode-cn.com/problems/reverse-nodes-in-k-group/)  [五星+++]
8. [61. Rotate List](https://leetcode-cn.com/problems/rotate-list/) [四星]
9. [83. Remove Duplicates from Sorted List](https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list/) [五星]
10. [82. Remove Duplicates from Sorted List II](https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list-ii/) [四星]
11. [86. Partition List](https://leetcode-cn.com/problems/partition-list/) [三星]




---


##### [2. Add Two Numbers](https://leetcode-cn.com/problems/add-two-numbers/) 

用链表表示两个非负整数（**逆序存放**：2 -> 4 -> 3表示数字342），并且保证给定的数字不会出现前置零（leading zero）。求两数之和对应的链表。

思路：

第一个想法是读取两个链表的数字，将数字相加，再将和转换成链表。事实上，一旦这么想就跳入了本题的陷阱之中，因为用链表存储数值意味着可以不限位数，而int或long类型数值可表示的数值终究是有限的，不能表示“大数”，因此这种方法会造成溢出。

由于本题已经说明数字在链表中是**逆序**存放的，事实上降低了本题的难度。我们可以直接从前往后对链表的各个位数相加。当相同位置上的数相加之和大于等于10时，要向高位进1，因此需要定义一个进位值carry，初始化为0，这是本题处理的关键。而针对789+211=1000这种特殊情况，和的位数增大了一位，需要在遍历完链表之和再判断一下carry是否为1。

```java
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0; // 进位
        ListNode head = null, curr = null;
        
        while(l1 != null || l2 != null) {
            int x1 = l1 != null ? l1.val : 0;
            int x2 = l2 != null ? l2.val : 0;
            int sum = x1 + x2 + carry;
            carry = sum / 10;
            sum %= 10;
            ListNode node = new ListNode(sum);
            if(head == null) {
                head = node;
                curr = node;
            }else {
                curr.next = node;
                curr = node;
            }
            l1 = l1 != null ? l1.next : null;
            l2 = l2 != null ? l2.next : null;
        }
        // 考虑carry不为0的情况，比如 999 + 1 = 1000
        if(carry != 0) {
            curr.next = new ListNode(carry);
        }
        return head;
    }
}
```

扩展：如果数字不是逆序存放，该如何处理呢？这就是第445题。比如(3→4→2)+(4→6→5)=8→0→7

思路：借助栈来处理，先把两个链表的节点逐一压入两个栈中，再和前一题类似处理。此外，再构建链表的时候应该使用**头插法**，而不是前一题的尾插法。还有一种方法是递归实现。

```java
public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
    Stack<ListNode> s1 = new Stack<>();
    Stack<ListNode> s2 = new Stack<>();
    // 预处理
    while (l1 != null) {
        s1.push(l1);
        l1 = l1.next;
    }
    while (l2 != null) {
        s2.push(l2);
        l2 = l2.next;
    }

    int carry = 0, sum = 0, digit = 0;
    ListNode head = null;
    while (!s1.empty() || !s2.empty()) {
        int x = s1.empty() ? 0 : s1.pop().val;
        int y = s2.empty() ? 0 : s2.pop().val;
        sum = carry + x + y;
        carry = sum / 10;
        digit = sum % 10;
        ListNode node = new ListNode(digit);
        if(head == null) {
            head = node;
        }else{
            // 头插法，新插入的节点作为头节点
            node.next = head;
            head = node;
        }
    }
    if(carry == 1){
        ListNode node = new ListNode(1);
        node.next = head;
        head = node;
    }
    return head;
}
```



##### [445. Add Two Numbers II](https://leetcode-cn.com/problems/add-two-numbers-ii/)

本题是上一题的follow-up，重点关注**递归法**实现。

```java
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int n1 = len(l1);
        int n2 = len(l2);
        ListNode head = (n1 > n2) ? helper(l1, l2, n1 - n2) : helper(l2, l1, n2 - n1);
        if (head != null && head.val > 9) {
            head.val %= 10;
            ListNode pre = new ListNode(1);
            pre.next = head;
            head = pre;
        }
        return head;
    }

    // l1 的长度总是比 l2 的长度长，diff 为两者的长度差值
    private ListNode helper(ListNode l1, ListNode l2, int diff) {
        if (l1 == null) return null;
        ListNode curr = (diff == 0) ? new ListNode(l1.val + l2.val) : new ListNode(l1.val);
        ListNode post = (diff == 0) ? helper(l1.next, l2.next, 0) : helper(l1.next, l2, diff-1);
        // 产生进位
        if (post != null && post.val > 9) {
            post.val %= 10;
            curr.val++;
        }
        curr.next = post;
        return curr;
    }

    private int len(ListNode head) {
        if (head == null) return 0;
        return 1 + len(head.next);
    }
}
```



##### [19. Remove Nth Node From End of List](https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/)

删除链表的倒数第 n 个节点。

分析：双指针法，初始化指针 curr 和 post 指向第一个节点，然后，post 率先移动 n 个节点，然后 curr、post 同时移动，当 post 到达 null 时，curr 指向的恰为倒数第 n 个节点。由于链表的首节点可能被删除，增加一个 dummy 节点，方便统一处理。

```java
// 时间复杂度：O(n) 一趟遍历
// 空间复杂度：O(1)
class Solution {
    // 假定 n 总是有效的（1 ≤ n ≤ length）
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode prev = dummy, curr = head, post = head;
        while (n > 0){
            post = post.next;
            n--;
        }
        // 当跳出循环时，curr 指向倒数第 n 个节点
        while (post != null) {
            prev = prev.next;
            curr = curr.next;
            post = post.next;
        }
        // remove operation
        prev.next = curr.next;
        return dummy.next;
    }
}
```



##### [21. Merge Two Sorted Lists](https://leetcode-cn.com/problems/merge-two-sorted-lists/)

合并两个有序链表。要求不能使用额外的空间。

分析：巧用dummy节点，让代码更简洁。

```java
// 时间复杂度：O(m+n)
// 空间复杂度：O(1)
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode curr = dummy;
        while (l1 != null && l2 != null) {
            if(l1.val < l2.val) {
                curr.next = l1;
                l1 = l1.next;
            }else {
                curr.next = l2;
                l2 = l2.next;
            }
            curr = curr.next;
        }
        if(l1 == null) curr.next = l2;
        else curr.next = l1;
        
        return dummy.next;
    }
}
```



##### [23. Merge k Sorted Lists](https://leetcode-cn.com/problems/merge-k-sorted-lists/)

本题在前一题的基础上加大了难度，需要合并任意 k 个有序链表。

思路：每次遍历比较 k 个链表的首节点，把最小的那个节点连接到 curr 节点之后。

在本题中，假设有 k 个链表，那么每次获取最小值节点的时间复杂度是 O(logk)；假设所有链表共有 n 个节点，那么总的时间复杂度是 O(n*logk)，因为外层的循环要把每个节点都放进队列一遍。

```java
// 时间复杂度：O(nlog(k)) // n是所有链表的节点个数，k是链表个数
// 空间复杂度：O(1)   
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists.length == 0) return null;
        if(lists.length == 1) return lists[0];
        // 较小值排前面
        PriorityQueue<ListNode> queue = new PriorityQueue<>((o1, o2) -> o1.val - o2.val);
        ListNode dummy = new ListNode(-1);
        ListNode curr = dummy;
        // 把每个链表的头节点放入优先队列中
        for(ListNode node : lists) {
            if(node != null) queue.add(node);
        }
        while (!queue.isEmpty()) {
            ListNode node = queue.poll();
            if(node.next != null) {
                queue.add(node.next);
            }
            curr.next = node;
            curr = node;
        }
        return dummy.next;
    }
}
```

感悟：每次遍历获取最小值节点这一操作可以使用**优先队列**，而不是傻乎乎的for循环遍历一遍，因为使用优先队列（其本质是最大/小堆）可以把时间复杂度从O(n)降到O(logn)。对于优先队列这一数据结构的使用还不够敏感，导致自己写的代码不够优雅，需引起注意！通过本题，应该对优先队列有一定的敏感性，并且掌握Java中优先队列自定义对象比较的写法。

##### [24. Swap Nodes in Pairs](https://leetcode-cn.com/problems/swap-nodes-in-pairs/) [五星+]

思路：这一题又是巧用dummy节点的典型，利用了dummy节点之后，就可以使用pre节点进行删除了。

```java
class Solution {
    public ListNode swapPairs(ListNode head) {
        // 边界情况：链表为空，或只有一个节点
        // if(head == null || head.next == null) return head;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode prev = dummy, curr = head;
        // 确保至少有两个节点不为空，否则不需要发生交换
        while (curr != null && curr.next != null) {
            // swap node
            ListNode post = curr.next;
            prev.next = post;
            curr.next = post.next;
            post.next = curr;
            // update
            prev = curr;
            curr = curr.next;
        }
        return dummy.next;
    }
}
```



##### [25. Reverse Nodes in k-Group](https://leetcode-cn.com/problems/reverse-nodes-in-k-group/)  [五星+++]

思路：本题是上一题的升级版，总的思路是先划分出k个节点，然后对这部分进行翻转，所以要用两个函数来处理。对于每k个节点进行一次处理，这里用到一个`cnt % k == 0` 的技巧，`cnt`用于表示当前是第几个节点（从1开始计数），当`cnt % k == 0`成立时，表示恰划分好一个group，需要对这部分进行处理。

```java
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        if(head == null || k == 1) return head;

        ListNode dummy = new ListNode(-1);
        ListNode prev = dummy, curr = head, tail = head;
        int cnt = 1;
        while (curr != null) {
            if (cnt % k == 0) { // 技巧
                tail = curr.next;
                prev.next = reverse(head, tail);
                curr = tail;
                prev = head; // ！
                head = tail;
            } else {
                curr = curr.next;
            }
            cnt++;
        }
        prev.next = tail; //不能漏了
        return dummy.next;
    }

    // 翻转 [left, right) 区间的链表
    private ListNode reverse(ListNode left, ListNode right) {
        ListNode prev = null, curr = left;
        while (curr != right) {
            ListNode next = curr.next;
            curr.next = prev;
            // 更新
            prev = curr;
            curr = next;
        }
        return prev;
    }
}
```

翻转 [left, right) 之间的链表

```java
ListNode reverseList(ListNode left, ListNode right) {
    ListNode prev = null, curr = left;
    while (curr != right){
        ListNode next = curr.next;
        curr.next = prev;
        // 更新
        prev = curr;
        curr = next;
    }
    return prev;
}
```



##### [61. Rotate List](https://leetcode-cn.com/problems/rotate-list/) [四星]

比如1->2->3->4->5->NULL向右旋转2个节点，就成了4->5->1->2->3->NULL

```
Explanation:
rotate 1 steps to the right: 5->1->2->3->4->NULL
rotate 2 steps to the right: 4->5->1->2->3->NULL
```

这里首先应该考虑**旋转的个数超过链表节点个数**的情况，比如下面这种情况：

```
Input: 0->1->2->NULL, k = 4
Output: 2->0->1->NULL
Explanation:
rotate 1 steps to the right: 2->0->1->NULL
rotate 2 steps to the right: 1->2->0->NULL
rotate 3 steps to the right: 0->1->2->NULL
rotate 4 steps to the right: 2->0->1->NULL
```

所以首先应该遍历一遍链表统计节点个数，记为n，那么真正旋转的节点个数为k=k%n（题目中已经说明k为非负数）。然后再采用“快慢指针”的思想，或者就是常规的思路，来改动节点指针。

需要说明的是，**当需要改变节点位置时，我们往往记录被切断位置的前一个节点。**以例子进行说明：

```
1 -> 2 -> 3 -> 4 -> 5
假设要在节点3和4之间切断进行一些操作，我们往往记录前一个节点的指针（即指向节点3）来进行操作，而不是记录指向后一个节点的指针，这是进行链表操作最核心的思想，仔细想一想，这也是为什么有时候会在原链表头节点之前加一个dummy节点的原因。
```

代码：

```java
class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if(head == null) return head;
        int n = 0; // 链表长度
        ListNode curr = head, tail = null;
        while (curr != null) {
            if(curr.next == null) tail = curr; // 记录尾节点
            curr = curr.next;
            n++;
        }
        k %= n;
        if(k == 0) return head;
        
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode newTail = dummy;
        int i = 0;
        while (i < n-k) {
            newTail = newTail.next;
            i++;
        }
        // 这里的顺序不要搞错
        tail.next = dummy.next;
        dummy.next = newTail.next;
        newTail.next = null;
        
        return dummy.next;
    }
}
```



##### [83. Remove Duplicates from Sorted List](https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list/) [五星]

Input: 1->1->2->3->3；Output: 1->2->3。对于重复节点，删除并保留一个。

```java
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode curr = head;
        while (curr != null && curr.next != null) {
            if(curr.val == curr.next.val) {
                curr.next = curr.next.next; // delete operation
            }else {
                curr = curr.next; 
            }
        }
        return head;
    }
}
```



##### [82. Remove Duplicates from Sorted List II](https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list-ii/) [四星]

Input: 1->2->3->3->4->4->5；Output: 1->2->5。

方法1：整体的思路同上，当出现重复节点时，记录节点的值为`duplicate`，然后向后移动直到`curr`节点不再等于`duplicate`，此时进行删除操作。

```java
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode prev = dummy, curr = head;
        while (curr != null && curr.next != null) {
            if(curr.val == curr.next.val) {
                int duplicate = curr.val;
                while (curr != null && curr.val == duplicate) {
                    curr = curr.next;
                }
                prev.next = curr;
            } else {
                prev = curr;
                curr = curr.next;
            }
        }
        return dummy.next;
    }
}
```

方法2： 可以通过如下事实来判断是否出现重复值。

假设当前节点为`curr`，前一个节点为`prev`， 通过`curr`与`curr.next` 判断两个节点的值是否相等。

* 有重复值，则`curr`向后移动直至`curr`和`curr.next`两个节点的值不再相等，由于`prev`节点停留在原位置没有更新过，因此可以通过 `prev.next == curr` 这一条件来判断是否出现重复值。


* 无重复值，也就是`curr`节点不用移动，此时`prev`的下一个节点必然还是`curr`（即 `prev.next == curr` 成立）

这一思路不需要记录重复节点的值`duplicate`，而是根据`prev`和`curr`节点的相对位置来进行判断。思路非常巧妙。

```java
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode prev = dummy, curr = head;
        while (curr != null) {
            while (curr.next != null && curr.next.val == curr.val) {
                curr = curr.next;
            }
            if(prev.next == curr) { // 无重复值
                prev = curr;
                curr = curr.next;
            }else {                 // 出现重复值
                prev.next = curr.next;
                curr = prev.next;
            }
        }
        return dummy.next;
    }
}
```



##### [86. Partition List](https://leetcode-cn.com/problems/partition-list/) [三星]

给定一个链表和一个值x，调整链表，要求比x小的值排在前面，大于等于x的值排在后面，且不能改变原链表各个节点的相对位置。比如，Input: head = 1->4->3->2->5->2, x = 3；Output: 1->2->2->4->3->5

思路：将所有小于给定值的节点取出组成一个新的链表1，将所有大于等于给定值的节点取出组成一个新的链表2，然后将两个链表连接起来即可。由于只额外申请了两个辅助节点，空间复杂度为O(1)；时间复杂度为O(n)，一趟遍历链表。

本题又是巧用dummy节点的典型例题，在原链表和新链表中都运用了这一技巧，可以有效简化代码。

```java
class Solution {
    public ListNode partition(ListNode head, int x) {
        ListNode dummy1 = new ListNode(-1), dummy2 = new ListNode(-1);
        ListNode curr1 = dummy1, curr2 = dummy2;
        while(head != null) {
            ListNode node = head;
            head = head.next;
            node.next = null;
            if(node.val < x) {
                curr1.next = node;
                curr1 = curr1.next;
            }else {
                curr2.next = node;
                curr2 = curr2.next;
            }
        }
        curr1.next = dummy2.next;
        return dummy1.next;
    }
}
```



11. 翻转链表（[206. Reverse Linked List](https://leetcode-cn.com/problems/reverse-linked-list/)）

要求递归实现和非递归实现都熟练掌握，闭着眼睛都会写的那种。

递归版（感觉很绕啊，我还是先不看了...）

非递归版（必须熟练掌握）

```java
public ListNode reverseList(ListNode head) {
    ListNode prev = null, curr = head, next = head;
    while (curr != null){
        next = curr.next;
        curr.next = prev;
        // 更新
        prev = curr;
        curr = next;
    }
    return prev;
}
```



12. 翻转链表2（[92. Reverse Linked List II](https://leetcode-cn.com/problems/reverse-linked-list-ii/)）（新的思路~）

翻转区间[m,n]之间的链表，要求一趟遍历。比如，Input: 1->2->3->4->5->NULL, m = 2, n = 4；Output: 1->4->3->2->5->NULL ，保证1 ≤ m ≤ n ≤ length

```java
public ListNode reverseBetween(ListNode head, int m, int n) {
    if (head == null || m == n) return head;
    ListNode dummy = new ListNode(-1);
    dummy.next = head;
    ListNode prev = dummy;
    for (int i = 0; i < m - 1; i++) {
        prev = prev.next;
    }
    ListNode curr = prev.next;
    for (int i = m; i < n; i++) {
        ListNode t = curr.next;
        curr.next = t.next;
        t.next = prev.next;
        prev.next = t;
    }
    return dummy.next;
}
```

参考[这里](https://www.cnblogs.com/grandyang/p/4306611.html) 。



13. 判断链表是否存在环（[141. Linked List Cycle](https://leetcode-cn.com/problems/linked-list-cycle/)）


```java
public boolean hasCycle(ListNode head) {
    ListNode slow = head, fast = head;
    while (fast != null && fast.next != null){
        slow = slow.next;
        fast = fast.next.next;
        if(slow == fast) return true;
    }
    return false;
}
```




14. 找出环形链表的入口节点（[142. Linked List Cycle II](https://leetcode-cn.com/problems/linked-list-cycle-ii/)）

```java
public ListNode detectCycle(ListNode head) {
    ListNode slow = head, fast = head;
    boolean hasCycle = false;
    // 判断是否有环（快慢指针）
    while (fast != null && fast.next != null){
        slow = slow.next;
        fast = fast.next.next;
        if(slow == fast) {
            hasCycle = true;
            break;
        }
    }
    if(!hasCycle) return null;
    // 寻找环的入口节点
    slow = head;
    while (slow != fast) {
        slow = slow.next;
        fast = fast.next;
    }
    return slow;
}
```

13、14两题是快慢指针的经典例题，没什么好说的，熟练掌握~



15. 删除链表中的元素（[203. Remove Linked List Elements](https://leetcode-cn.com/problems/remove-linked-list-elements/)）（常规题）

```java
public ListNode removeElements(ListNode head, int val) {
    if(head == null) return null;
    ListNode dummy = new ListNode(-1);
    dummy.next = head;
    ListNode prev = dummy;
    while (prev.next != null) {
        if(prev.next.val == val) prev.next = prev.next.next;
        else prev = prev.next;
    }
    return dummy.next;
}
```

在LeetCode中，还有另外一题也是删除节点的，一开始看了下题目觉得“这是什么弱智题目”，再仔细一想感觉“题目是不是出错了”，最后看了题解才发现是自己的问题。这一题提供了一个很好的处理链表的思路。

在[237. Delete Node in a Linked List](https://leetcode-cn.com/problems/delete-node-in-a-linked-list/) 题中，要求编写一个函数，使其可以删除某个链表中给定的（**非末尾**）节点，你将只被给定要求被删除的节点。并且给出了以下几个约束条件：

- 链表至少包含两个节点。
- 链表中所有节点的值都是**唯一**的。
- 给定的节点为**非末尾节点并且一定是链表中的一个有效节点**。
- 不要从你的函数中返回任何结果。

```java
public void deleteNode(ListNode node) {
        
}
```

题目只给出了这样一个函数，我们无法获取链表的头节点。一般来说，我们要删除某个节点，就要获取该节点的前一个节点（prev），但是在本题中，只给出了待删除的节点，因此常规的思路行不通。由于该链表保证所有节点的值都是唯一的，且待删除的节点不是尾节点。因此可以这样考虑：（参考[这里](https://leetcode-cn.com/problems/delete-node-in-a-linked-list/solution/shan-chu-lian-biao-zhong-de-jie-dian-by-leetcode/)）

从链表里删除一个节点 node 的最常见方法是修改**之前**节点的 next 指针，使其指向之后的节点。

![img](https://pic.leetcode-cn.com/3579a496897df5321c110bf1301872b6e10c342f5e400ce45d2db0348d00d715-file_1555866623326)



由于我们无法访问我们想要删除的节点**之前**的节点，我们始终不能修改该节点的 next 指针。相反，我们可以将想要删除的节点的值替换为它后面节点中的值，然后删除它之后的节点（这个时候，已知的这个节点就成了待删除节点的prev节点）。如下图所示，已知给定node 3，且要求删除node 3，可以将node 3的值替换为在它后面的值，也就是4，然后再删除node 4即可。

![img](https://pic.leetcode-cn.com/858fae01d89c2080eb7e45a1f9d9a2b2f76e1a5c87815b324fd946e0bd8da495-file_1555866651920)

![img](https://pic.leetcode-cn.com/902dc5d3f8c44d3cbc0b6e837711cad2eefc021fd2b9de8dfabc6d478bc779b1-file_1555866680932)

![img](https://pic.leetcode-cn.com/2a6409b98dd73d6649fdc6fb984c88690547127467104c3923367be6f8fbc916-file_1555866773685)

因为我们知道要删除的节点不是列表的末尾，所以我们可以保证这种方法是可行的。

```java
public void deleteNode(ListNode node) {
    node.val = node.next.val;
    node.next = node.next.next;
}
```



16. **求两个链表相交节点（[160. Intersection of Two Linked Lists](https://leetcode-cn.com/problems/intersection-of-two-linked-lists/)）（很有意思~）**

本题要求时间复杂度O(n)，空间复杂度O(1)，还是有相当难度的。自己没想出来，参考评论区的。



```java
public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    if(headA == null || headB == null) return null;
    ListNode pA = headA, pB = headB;
    while(pA != pB) {
        pA = pA != null ? pA.next : headB;
        pB = pB != null ? pB.next : headA;
    }
    return pA;
}
```



17. 复制带随机节点的链表（[138. Copy List with Random Pointer](https://leetcode-cn.com/problems/copy-list-with-random-pointer/)）

节点的定义如下：

```
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
```

带随机节点的链表如下所示：

![img](https://assets.leetcode.com/uploads/2019/12/18/e1.png)

思路：

1）首先，复制每个节点，将链表修改为：

```
Old List: A --> B --> C --> D
New List: A --> A' --> B --> B' --> C --> C' --> D --> D'
```

2）比如，原链表中节点B的随机指针指向D，那么令节点B' 的随机指针指向节点D'。假设当前知道节点B的指针，也就很容易找到节点D'。

```
curr.random = prev.random.next;
```

这一步是本题的关键。这也是为什么在步骤1中要复制节点构成`A --> A' --> B --> B'` 的形式！

3）抽取A' --> B' --> ... 构成新的链表

总的来收不算难，主要是之前在《剑指offer》上已经做过了，但链表的处理仍然不熟悉。

```java
public Node copyRandomList(Node head) {
    if(head == null) return null;

    // 1.复制节点
    Node curr = head;
    while (curr != null) {
        Node copy = new Node(curr.val);
        copy.next = curr.next;
        curr.next = copy;
        curr = curr.next.next;
    }

    // 2.连接random指针
    curr = head;
    while (curr != null) {
        curr.next.random = (curr.random == null ? null : curr.random.next);
        curr = curr.next.next;
    }

    // 3.抽取链表
    Node newHead = head.next, newTail =head.next;
    curr = head;
    while (curr != null) {
        curr.next = newTail.next;
        curr = curr.next;
        newTail.next = curr != null ? curr.next : null;
        newTail = newTail.next;
    }
    return newHead;
}
```



18. 回文链表（[234. Palindrome Linked List](https://leetcode-cn.com/problems/palindrome-linked-list/)）

判断一个链表是否为回文链表。如果是数组，那么判断回文数非常简单，但是由于单向链表只能顺序遍历，不能从后向前访问，因此会比较麻烦。首先，利用快慢指针法找到链表的中间节点，然后翻转后半部分链表，然后再顺序比较这两部分的节点是否对应相等即可。

本题的关键在于翻转后半部分链表，同样的思路在[143. Reorder List](https://leetcode-cn.com/problems/reorder-list/) 也有运用。

```java
// 时间复杂度：O(n)
// 空间复杂度：O(1)
public boolean isPalindrome(ListNode head) {
    if(head == null || head.next == null) return true;
    ListNode slow = head, fast = head;
    while (fast != null && fast.next != null) {
        slow = slow.next;
        fast = fast.next.next;
    }
    // 节点个数为奇数个
    if(fast != null) slow = slow.next;
    
    // 翻转区间[slow, 尾节点]之间的链表
    ListNode prev = null, curr = slow;
    while (curr != null) {
        ListNode next = curr.next;
        curr.next = prev;
        prev = curr;
        curr = next;
    }
    slow = prev; // 将反转后的链表头节点重新设为slow

    // 重置fast指针
    fast = head;
    while (slow != null) {
        if(slow.val != fast.val) return false;
        slow = slow.next;
        fast = fast.next;
    }
    return true;
}
```



19. 重排链表（[143. Reorder List](https://leetcode-cn.com/problems/reorder-list/) ）

给定一个链表 L 为: L0→L1→…→Ln-1→Ln, 重排链表，将其调整为: L0→Ln→L1→Ln-1→L2→Ln-2→…

思路：纯粹就是链表的处理，但是第一次做也有一些值得学习的点。可以利用上一题的思路。

观察到链表的两部分分别是L0 --> L1 --> L2 ... (顺序)；Ln --> Ln-1 --> Ln-2 ...(倒序)，最后在中间节点相聚。因此，可以这样考虑：

（1）先利用快慢指针找到中间节点

（2）翻转链表的后半部分

（3）拼接前后两部分链表即可

这种方法的空间复杂度O(1)，时间复杂度O(n)

```java
public void reorderList(ListNode head) {
    if(head == null) return;
    ListNode p1 = head, p2 = head;
    while (p2 != null && p2.next != null) {
        p1 = p1.next;
        p2 = p2.next.next;
    }

    // 翻转后半部分
    ListNode prev = null, curr = p1;
    while (curr != null){
        ListNode next = curr.next;
        curr.next = prev;
        prev = curr;
        curr = next;
    }
    p1 = prev; // prev此时为翻转后的链表的节点
    p2 = head;

    while (p1.next != null){
        ListNode t = p1;
        p1 = p1.next;
        t.next = p2.next;
        p2.next = t;
        p2 = p2.next.next;
    }
}
```



20. 奇偶链表（[328. Odd Even Linked List](https://leetcode-cn.com/problems/odd-even-linked-list/)）

给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性。要求时间复杂度为O(n)，空间复杂度为O(1)。

这一题差不多就是[138. Copy List with Random Pointer](https://leetcode-cn.com/problems/copy-list-with-random-pointer/) 这一题最后一部分”抽取链表“的部分~

我觉得做这个题的关键是如何设置while()循环条件，举几个例子（比如3个节点、4个节点这样的链表）试一下即可。自己写的代码就是标准代码了，非常精简~

```java
public ListNode oddEvenList(ListNode head) {
    if (head == null) return head;
    ListNode prev = head, curr = head.next, evenHead = head.next;
    while (curr != null && curr.next != null) {
        prev.next = curr.next;
        prev = prev.next;
        curr.next = (prev != null ? prev.next : null);
        curr = curr.next;
    }
    prev.next = evenHead;
    return head;
}
```



21. 对链表进行插入排序（[147. Insertion Sort List](https://leetcode-cn.com/problems/insertion-sort-list/)）

思路：

```java
// 版本1
public ListNode insertionSortList(ListNode head) {
    if(head == null) return null;
    ListNode dummy = new ListNode(-1);
    dummy.next = head;
    ListNode preP1 = head, p1 = head.next; // 外侧遍历
    while (p1 != null) {
        ListNode preP2 = dummy, p2 = dummy.next; // 内层遍历
        while (p2 != p1) {
            if(p1.val < p2.val) break;
            preP2 = p2;
            p2 = p2.next;
        }
        if(p2 == p1){
            preP1 = p1;
            p1 = p1.next;
        }else {
            preP1.next = p1.next;
            p1.next = p2;
            preP2.next = p1;
            p1 = preP1.next;
        }
    }
    return dummy.next;
}

// 版本2（优化），速度快了不少~
public ListNode insertionSortList(ListNode head) {
    if (head == null) return null;
    ListNode dummy = new ListNode(-1);
    dummy.next = head;
    ListNode preP1 = head, p1 = head.next; // 外侧遍历
    while (p1 != null) {
        if (p1.val >= preP1.val) {
            preP1 = p1;
            p1 = p1.next;
            continue;
        }
        ListNode preP2 = dummy, p2 = dummy.next; // 内层遍历
        while (p2 != p1) {
            if (p1.val < p2.val) break;
            preP2 = p2;
            p2 = p2.next;
        }
        preP1.next = p1.next;
        p1.next = p2;
        preP2.next = p1;
        p1 = preP1.next;
    }
    return dummy.next;
}
```



22. 排序链表（[148. Sort List](https://leetcode-cn.com/problems/sort-list/)）

本题要求在*O*(*n* log *n*) 时间复杂度和常数级空间复杂度下，对链表进行排序。

对于链表这种数据结构，只能用归并排序了，但是归并排序的需要O(log n)的空间。下面的写法就是根据常规思路写出来的归并排序，严格来说，空间复杂度是不符合要求的。

```java
// 时间复杂度：O(n*logn)
// 空间复杂度：O(logn)
public ListNode sortList(ListNode head) {
    if (head == null || head.next == null) return head;
    // 寻找中间节点(快慢指针)
    ListNode slow = head, fast = head, prev = null;
    while (fast != null && fast.next != null) {
        prev = slow;
        slow = slow.next;
        fast = fast.next.next;
    }
    prev.next = null; // 分割前一部分链表

    // 递归处理左右两部分
    ListNode p1 = sortList(head);
    ListNode p2 = sortList(slow);
    // 归并
    return mergeTwoSortedList(p1, p2);
}

private ListNode mergeTwoSortedList(ListNode head1, ListNode head2) {
    ListNode dummy = new ListNode(-1);
    ListNode tail = dummy;
    while (head1 != null && head2 != null) {
        ListNode t;
        if (head1.val <= head2.val) {
            t = head1;
            head1 = head1.next;
        } else {
            t = head2;
            head2 = head2.next;
        }
        t.next = null;
        tail.next = t;
        tail = tail.next;
    }
    if (head1 == null) tail.next = head2;
    if (head2 == null) tail.next = head1;
    return dummy.next;
}
```

事实上，确实有空间复杂度为O(1)的归并排序写法，参考[这里](https://leetcode-cn.com/problems/sort-list/solution/sort-list-gui-bing-pai-xu-lian-biao-by-jyd/)。





23. **将有序链表转化为二叉搜索树**（[109. Convert Sorted List to Binary Search Tree](https://leetcode-cn.com/problems/convert-sorted-list-to-binary-search-tree/)）**(有点意思!)**

这一题与[108. Convert Sorted Array to Binary Search Tree](https://leetcode-cn.com/problems/convert-sorted-array-to-binary-search-tree/) 思路完全一致。区别在于有序数组可以直接计算出中间节点的位置，而链表只能采用快慢指针的思路获取中间节点。

解法一：

思路：每一趟找到链表的中间节点mid，根据mid节点的值创建二叉树的根节点root，然后再递归的处理前半部分链表[head, mid) 和后半部分链表[mid.next, null]，分别作为root的左右孩子节点。

```java
// 时间复杂度：O(N*logN)
// 空间复杂度：O(logN)
public TreeNode sortedListToBST(ListNode head) {
    if(head == null) return null;
    if(head.next == null) return new TreeNode(head.val);
  
  	// 该部分是这一解法比较耗时的地方，可以优化~
    ListNode slow = head, fast = head, prev = null;
    while (fast != null && fast.next != null) {
        prev = slow;
        slow = slow.next;
        fast = fast.next.next;
    }
    prev.next = null; // 阶段前半部分链表
    fast = slow.next; // 重置后半部分俩表的头节点（这里用fast节点代替）
  
    TreeNode root = new TreeNode(slow.val);
    // 递归处理左右子树~
    root.left = sortedListToBST(head);
    root.right = sortedListToBST(fast);
    return root;
}
```



解法二：新思路，有点意思~

本题的常规做法就是如上所示，此外还有另外一种自底向上的做法，参考[这里](https://leetcode-cn.com/problems/convert-sorted-list-to-binary-search-tree/solution/convert-sorted-list-to-binary-search-tree-di-ding-/) 。






