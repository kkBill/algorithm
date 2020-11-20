# 链表问题汇总

题目列表：

[toc]

---


## [2. Add Two Numbers](https://leetcode-cn.com/problems/add-two-numbers/) 

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



## [445. Add Two Numbers II](https://leetcode-cn.com/problems/add-two-numbers-ii/)

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



## [19. 删除链表的倒数第N个节点](https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/) [简单]

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



## [21. Merge Two Sorted Lists](https://leetcode-cn.com/problems/merge-two-sorted-lists/)

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



## [23. Merge k Sorted Lists](https://leetcode-cn.com/problems/merge-k-sorted-lists/)

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

## [24. Swap Nodes in Pairs](https://leetcode-cn.com/problems/swap-nodes-in-pairs/) [五星+]

思路：这一题又是巧用dummy节点的典型，利用了dummy节点之后，就可以使用pre节点进行删除了。

<img src="../img/lc24SwapNodesinPairs.png" alt="xx" style="zoom:50%;" />

```java
class Solution {
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode prev = dummy, curr = head;
        // 确保至少有两个节点不为空，否则不需要发生交换
        while(curr != null && curr.next != null) {
            // swap
            prev.next = curr.next;
            curr.next = curr.next.next;
            prev.next.next = curr;
            // update
            prev = curr;
            curr = prev.next;
        }
        return dummy.next;
    }
}
```



## [25. Reverse Nodes in k-Group](https://leetcode-cn.com/problems/reverse-nodes-in-k-group/)  [五星+++]

思路：本题是上一题的升级版，总的思路是先划分出k个节点，然后对这部分进行翻转，所以要用两个函数来处理。对于每k个节点进行一次处理，这里用到一个`cnt % k == 0` 的技巧，`cnt`用于表示当前是第几个节点（从1开始计数），当`cnt % k == 0`成立时，表示恰划分好一个group，需要对这部分进行处理。

写法1：

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

写法2：

```java
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode prev = dummy, curr = head;
        int i;
        while(curr != null) {
            ListNode left = curr, right = curr;
            i = 0;
            // 确定右边界
            for(; i < k && right != null; i++) {
                right = right.next;
            }
            if(i < k) { // 剩余不足k个元素
                prev.next = left;
                break;
            }else {
                prev.next = reverse(left, right);
                prev = left;
                curr = right;
            }
        }
        return dummy.next;
    }

    public ListNode reverse(ListNode left, ListNode right) {
        ListNode prev = null, curr = left;
        while(curr != right) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }
}
```



## [61. Rotate List](https://leetcode-cn.com/problems/rotate-list/) [四星]

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



## [83. Remove Duplicates from Sorted List](https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list/) [五星]

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



## [82. Remove Duplicates from Sorted List II](https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list-ii/) [四星]

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



## [86. Partition List](https://leetcode-cn.com/problems/partition-list/) [三星]

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



## [206. Reverse Linked List](https://leetcode-cn.com/problems/reverse-linked-list/) [3种解法, 递归版不熟悉]

要求递归实现和非递归实现都熟练掌握，闭着眼睛都会写的那种。

方法1：非递归版（必须熟练掌握）

```java
class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode prev = null, curr = head;
        while(curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }
}
```

方法2：递归版：先考虑只有一个节点、两个节点的情况。

```java
class Solution {
    public ListNode reverseList(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode curr = reverseList(head.next);
        // 比如一个 1 -> 2 -> 3 -> 4 -> 5-> null 的链表
        //                        |    | 
        // 此时：                 head curr  
        head.next.next = head;
        head.next = null;
        return curr;
    }
}
```

方法3：头插法。即新建一个dummy head节点，顺序遍历原链表，并把节点根据**头插法**连接在dummy节点之后，最后返回dummy.next即可。头插法可以实现链表的逆序，这也是一个很好的思路。

```java
class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode dummy = new ListNode(-1);
        while (head != null) {
            ListNode curr = head;
            head = head.next;
            curr.next = dummy.next;
            dummy.next = curr;
        }
        return dummy.next;
    }
}
```



## [92. Reverse Linked List II](https://leetcode-cn.com/problems/reverse-linked-list-ii/) [四星，头插法的应用]

翻转区间[m,n]之间的链表，**要求一趟遍历**。比如，Input: 1->2->3->4->5->NULL, m = 2, n = 4；Output: 1->4->3->2->5->NULL ，保证1 ≤ m ≤ n ≤ length。

方法1：找到区间[m,n]的节点，对这部分进行翻转。虽然也挺快的，但是这种做法并不满足“一趟遍历”的要求。这种做法，第一遍为了确定区间[m,n]，会遍历到第n个节点（O(n)）；第2遍真正处理翻转，需要消耗O(n-m)个时间。**最差情况需要两趟遍历。**

```java
class Solution {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode left = head, beforeLeft = dummy;
        for(int i = 1; i < m; i++) {
            beforeLeft = left;
            left = left.next;
        }
        ListNode right = left;
        for(int i = m; i <= n; i++) {
            right = right.next;
        }
        beforeLeft.next = reverseList(left, right);
        left.next = right; // 翻转后，left变为区间[m,n]的尾节点
        return dummy.next;
    }
    // 翻转区间[left, right)的链表，并返回该部分链表翻转后新的头节点
    public ListNode reverseList(ListNode left, ListNode right) {
        ListNode prev = null, curr = left;
        while(curr != right) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }
}
```



方法2：第1次做是参考[这里](https://www.cnblogs.com/grandyang/p/4306611.html)的。第2次回顾时，发现这种做法的本质就是头插法。确定第m个节点的前一个节点，记为prev，并记第m个节点为curr，每次都把curr的下一个节点移到prev之后即可。

```java
// 时间复杂度：O(n) 一趟遍历
class Solution {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode prev = dummy;
        for(int i = 1; i < m; i++) {
            prev = prev.next;
        }
        ListNode curr = prev.next;
        for(int i = m; i < n; i++) {
            ListNode post = curr.next;
            curr.next = post.next;
            post.next = prev.next;
            prev.next = post;
        }
        return dummy.next;
    }
}
```



## [109. Convert Sorted List to Binary Search Tree](https://leetcode-cn.com/problems/convert-sorted-list-to-binary-search-tree/) [五星+]

这一题与[108. Convert Sorted Array to Binary Search Tree](https://leetcode-cn.com/problems/convert-sorted-array-to-binary-search-tree/) 思路完全一致。区别在于有序数组可以直接计算出中间节点的位置，而链表只能采用快慢指针的思路获取中间节点。

方法1：每一趟找到链表的中间节点mid，根据mid节点的值创建二叉树的根节点root，然后再递归的处理前半部分链表[head, mid) 和后半部分链表[mid.next, null]，分别作为root的左右孩子节点。

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
    prev.next = null; // 截断前半部分链表
    fast = slow.next; // 重置后半部分链表的头节点（这里用fast节点代替）
  
    TreeNode root = new TreeNode(slow.val);
    // 递归处理左右子树~
    root.left = sortedListToBST(head);
    root.right = sortedListToBST(fast);
    return root;
}
```

后记：当然，本题也可以先把链表中的值转存至数组中，然后利用第108题的做法做，这样可以把时间缩减至O(n)，只不过空间复杂度变成了O(n)，这是典型的**用空间换时间**的做法。面试的时候也可以把这个思路说给面试官听。

方法2：参考[这里](https://leetcode-cn.com/problems/convert-sorted-list-to-binary-search-tree/solution/convert-sorted-list-to-binary-search-tree-di-ding-/) 。这种思路的思想是**模拟二叉树的中序遍历**，因为BST的中序遍历是升序序列，而给定的链表也恰是升序序列，因此，可以在模拟中序遍历的过程中把链表的值填入树的结构中，最后形成BST。由于只需要遍历一趟链表，该做法的时间复杂度为O(n)，空间复杂度为O(log n)（即递归深度）。

但是感觉这种做法还是比较难以理解的，目前理解的还不深刻，没想到递归还可以这么玩儿。先把解法记录于此。

```java
class Solution {
    private ListNode gHead; // 全局变量
    public TreeNode sortedListToBST(ListNode head) {
        gHead = head;
        int len = getLength(head);
        return helper(0, len-1);
    }
    
    private TreeNode helper(int l, int r) {
        if(l > r) return null;
        int mid = (r - l) / 2 + l;
        TreeNode left = helper(l, mid-1);
        TreeNode root = new TreeNode(gHead.val);
        gHead = gHead.next;
        root.left = left;
        root.right = helper(mid+1, r);
        return root;
    }
    
    private int getLength(ListNode head) {
        ListNode curr = head;
        int len = 0;
        while (curr != null) {
            len++;
            curr = curr.next;
        }
        return len;
    }
}
```



## [138. Copy List with Random Pointer](https://leetcode-cn.com/problems/copy-list-with-random-pointer/) [四星]

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

2）假设原链表中节点B的随机指针指向D，那么我们要想办法把节点B' 的随机指针指向节点D'。假设当前节点curr为节点B，那么复制节点B'的random指针操作如下：

```
curr.next.random = curr.random.next;
// curr为节点B，curr.next则为节点B'，curr.random为D，而curr.random.next则为D'
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



## [141. Linked List Cycle](https://leetcode-cn.com/problems/linked-list-cycle/) [二星, 基础]

判断链表是否有环。

方法1：快慢指针法。

```java
// 时间复杂度：
// 空间复杂度：O(1)
public class Solution {
    public boolean hasCycle(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast) return true;
        }
        return false;
    }
}
```

方法2：哈希法。（虽然快慢指针法已经是这道题的标准解法，但是最基础的哈希法也别忘记了）

将节点存储在一个Set中，在遍历的过程中，如果一个节点已经出现在Set中，那么这个链表就是有环的。

```java
public class Solution {
    public boolean hasCycle(ListNode head) {
        Set<ListNode> set = new HashSet<>();
        ListNode curr = head;
        while (curr != null) {
            if(set.contains(curr)) {
                return true;
            }else {
                set.add(curr);
            }
            curr = curr.next;
        }
        return false;
    }
}
```



## [142. Linked List Cycle II](https://leetcode-cn.com/problems/linked-list-cycle-ii/) [需要给出证明过程]

方法1：快慢指针法。

证明：（图片来自官方题解）

![image.png](https://pic.leetcode-cn.com/99987d4e679fdfbcfd206a4429d9b076b46ad09bd2670f886703fb35ef130635-image.png)

利用快慢指针法，假设慢指针slow和快指针fast在node h处相遇，此时有：

```
2×distance(slow) = distance(fast)，即
2×(F+a) = F+a+n×(b+a) (其中n为正数，表示快指针对慢指针套的圈数)
化简可得：
F = (n-1)×(b+a) + b
由这个式子可以看出：
1.若快慢指针在相遇时，快指针只多走了1圈，即n=1，那么有F=b，此时重置slow为head，然后让slow和fast每次走一步，当两者再次相遇时，相遇点就是环的入口。
2.若快慢指针在相遇时，快指针只多走了不止1圈，即n>1，此时不妨令 F = C×(b+a)+b (其中C为常数)
  和1一样，当slow走了F距离的时候，fast也必然会与其相遇，只不过这种情况下，fast会先绕环C圈，然后再走b距离   与slow相遇
```

复杂度分析：假设链表没有环，程序会在阶段1就结束，只会遍历n/2个节点，时间复杂度为O(n)

假设链表有环，在第2阶段中，快慢指针需再遍历 F 次，由于 F 必然小于 n(链表长度)，因此这一阶段仍然是O(n)。因此，无论那种情况，总的时间复杂度是O(n)级别的。空间复杂度就是O(1)了。

```java
// 时间复杂度：O(n)
// 空间复杂度：O(1)
public class Solution {
    public ListNode detectCycle(ListNode head) {
        boolean hasCycle = false;
        ListNode slow = head, fast = head;
        // 阶段1. 快慢指针法判断链表是否有环
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast) {
                hasCycle = true;
                break;
            }
        }
        if(!hasCycle) return null;
        // 阶段2. 寻找入口
        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }
}
```



方法2：哈希法。我们分配一个 Set 去保存所有的列表节点。我们逐一遍历列表，检查当前节点是否出现过，如果节点已经出现过，那么一定形成了环且它是环的入口。从实现的角度讲，这种方法更加简单，和前一题判断链表是否有环一摸一样。

时间复杂度：不管有没有环，都要(也只需要)完整的遍历一遍环，即O(n)

空间复杂度：借助哈希表把所有节点存入其中，即O(n)。

```java
public class Solution {
    public ListNode detectCycle(ListNode head) {
        Set<ListNode> set = new HashSet<>();
        ListNode curr = head;
        while (curr != null) {
            if(set.contains(curr)) {
                return curr;
            }else {
                set.add(curr);
            }
            curr = curr.next;
        }
        return null;
    }
}
```



## [143. Reorder List](https://leetcode-cn.com/problems/reorder-list/) [四星, 细节要注意]

给定一个链表 L 为: L0→L1→…→Ln-1→Ln, 重排链表，将其调整为: L0→Ln→L1→Ln-1→L2→Ln-2→…

思路：纯粹就是链表的处理，但是第一次做也有一些值得学习的点。可以利用上一题的思路。

观察到链表的两部分分别是L0 --> L1 --> L2 ... (顺序)；Ln --> Ln-1 --> Ln-2 ...(倒序)，最后在中间节点相聚。因此，可以这样考虑：

（1）先利用快慢指针找到中间节点

（2）翻转链表的后半部分

（3）拼接前后两部分链表即可

这种方法的空间复杂度O(1)，时间复杂度O(n)

```java
class Solution {
    public void reorderList(ListNode head) {
        if(head == null) return; // 注意特殊情况的判断，否则后面第3部分判断 p1.next!=null 就会出错
        // 1.寻找中间节点
        ListNode p1 = head, p2 = head;
        while (p2 != null && p2.next != null) {
            p1 = p1.next;
            p2 = p2.next.next;
        }

        // 2.翻转后半部分
        ListNode prev = null, curr = p1;
        while (curr != null){
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        p1 = prev; // prev此时为翻转后的链表的头节点
        p2 = head;
        // 3.合并
        while (p1.next != null){
            ListNode t = p1;
            p1 = p1.next;
            t.next = p2.next;
            p2.next = t;
            p2 = p2.next.next;
        }
    }
}
```



## [147. Insertion Sort List](https://leetcode-cn.com/problems/insertion-sort-list/) [链表插入排序, 必须熟练]

思路：排序问题，想要速度快，就要尽量减少比较的次数，这是最核心的观点。

- 需要一个指针始终指向**已排序部分**的最后一个节点，这里记为tail，初始化为头节点
- 记当前节点为curr，初始化为第2个节点
- 外层循环即从第2个节点开始到最后一个节点
- 每一轮比较时，首先比较curr与tail节点的大小，如果curr.val >= tail.val，则直接把curr连接在tail之后即可
- 否则，需要从首节点开始比较，直到找到合适的插入位置。这里借助dummy的技巧使得代码更简洁。

复杂度分析：理想情况下，即原链表有序，则时间复杂度为O(n)；最差情况，即原链表逆序，则时间复杂度为O(n^2)

```java
class Solution {
    public ListNode insertionSortList(ListNode head) {
        if(head == null) return null;
        
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode tail = head, curr = head.next;
        while (curr != null) {
            if(curr.val >= tail.val) {
                tail = curr;
            }else {
                ListNode prev = dummy;
                while (prev.next.val < curr.val) {
                    prev = prev.next;
                }
                // 位置调整
                tail.next = curr.next;
                curr.next = prev.next;
                prev.next = curr;
            }
            // next node
            curr = tail.next;
        }
        return dummy.next;
    }
}
```



## [148. Sort List](https://leetcode-cn.com/problems/sort-list/) [五星, 链表归并排序, bottom-to-up的解法不够熟练]

本题要求在*O*(*n* log *n*) 时间复杂度和常数级空间复杂度下，对链表进行排序。

方法1：对于链表这种数据结构，只能用归并排序了，但是归并排序的需要O(log n)的空间。下面的写法就是根据常规思路写出来的归并排序，严格来说，空间复杂度是不符合要求的。

```java
// 时间复杂度：O(n*logn)
// 空间复杂度：O(logn)
class Solution {
    public ListNode sortList(ListNode head) {
        if(head == null || head.next == null) return head;
        
        // 1.快慢指针确定中间节点, 当退出循环时, slow指向后半部分的第一个节点
        ListNode slow = head, fast = head, prev = null;
        while(fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        prev.next = null; // 截断前半部分
        
        // 2.递归
        ListNode h1 = sortList(head);
        ListNode h2 = sortList(slow);
        
        // 3.合并两个有序链表
        return merge(h1, h2);
    }
    
    // 合并两个有序链表，返回合并后链表的首节点
    public ListNode merge(ListNode h1, ListNode h2) {
        ListNode dummy = new ListNode(-1);
        ListNode prev = dummy;
        while (h1 != null && h2 != null) {
            if(h1.val < h2.val) {
                prev.next = h1;
                h1 = h1.next;
            }else {
                prev.next = h2;
                h2 = h2.next;
            }
            prev = prev.next;
        }
        if(h1 != null) prev.next = h1;
        else prev.next = h2;
        return dummy.next;
    }
}
```



方法2：事实上，确实有空间复杂度为O(1)的归并排序写法，参考[这里](https://leetcode-cn.com/problems/sort-list/solution/sort-list-gui-bing-pai-xu-lian-biao-by-jyd/)。也就是bottom-to-up的写法，或者说是**归并排序的非递归写法**。这种方法一定要熟悉。

```java
class Solution {
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        int length = getLength(head);
        // O(log n)
        for (int step = 1; step < length; step <<= 1) {
            ListNode prev = dummy;
            ListNode curr = prev.next;
            // O(n)
            while (curr != null) {
                ListNode left = curr;
                ListNode right = cut(left, step);
                curr = cut(right, step);
                prev.next = merge(left, right);
                // update prev for next iteration
                while (prev.next != null) {
                    prev = prev.next;
                }
            }
        }
        return dummy.next;
    }

    // 关键函数~
    // 分割链表: 截断从head节点开始(including head)的第n个节点，并返回截断之后半部分的新链表头
    // 原链表：2(head)->3->1->4->5->9->null
    // 调用 cut(head, 2) 后，链表变为：
    // part 1: 2->3->null
    // part 2: 1->4->5->9->null （cut(head, 2)方法 返回节点1）
    public ListNode cut(ListNode head, int n) {
        if(head == null) return null; // 关键

        ListNode curr = head;
        for (int i = 1; i < n && curr.next != null; i++) {
            curr = curr.next;
        }
        ListNode right = curr.next;
        curr.next = null; // 截断前n个节点
        return right;
    }

    // 获取链表长度
    public int getLength(ListNode head) {
        ListNode curr = head;
        int length = 0;
        while (curr != null) {
            length++;
            curr = curr.next;
        }
        return length;
    }
    
    // 合并两个有序链表，返回合并后链表的首节点
    public ListNode merge(ListNode h1, ListNode h2) {
        ListNode dummy = new ListNode(-1);
        ListNode prev = dummy;
        while (h1 != null && h2 != null) {
            if (h1.val < h2.val) {
                prev.next = h1;
                h1 = h1.next;
            } else {
                prev.next = h2;
                h2 = h2.next;
            }
            prev = prev.next;
        }
        if (h1 != null) prev.next = h1;
        else prev.next = h2;
        return dummy.next;
    }
}
```



方法3：本题还可以用**快速排序**的思想去写，由于链表无法随机访问，在“主元选取”部分做简单处理了（即挑选链表的首个节点作为pivot element），这样的解法可以通过测试，但效率非常低。

```java
class Solution {
    public ListNode sortList(ListNode head) {
        return qSort(head, null);
    }
    
    private ListNode qSort(ListNode head, ListNode end) {
        if (head == end || head.next == end) return head;

        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pivot = head, prev = dummy;
        while (prev.next != end) {
            if (prev.next.val < pivot.val) {
                ListNode temp = prev.next;
                prev.next = prev.next.next;
                temp.next = dummy.next;
                dummy.next = temp;
            } else {
                prev = prev.next;
            }
        }
        
        // 递归处理
        dummy.next = qSort(dummy.next, pivot);
        pivot.next = qSort(pivot.next, end);

        return dummy.next;
    }
}
```



## [160. Intersection of Two Linked Lists](https://leetcode-cn.com/problems/intersection-of-two-linked-lists/) [四星, 很好的思路]

求两个链表的相交节点，如果两个链表不相交，则返回null。

本题要求时间复杂度O(n)，空间复杂度O(1)，还是有相当难度的。自己没想出来，参考评论区的。

方法1：哈希法，但是不满足本题的要求。

```java
public class Solution {
    // 时间复杂度：O(n) // n为两个链表的所有节点
    // 空间复杂度：O(n) // 同上
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        Set<ListNode> set = new HashSet<>();
        ListNode curr = headA;
        while(curr != null) {
            set.add(curr);
            curr = curr.next;
        }
        curr = headB;
        while(curr != null) {
            if(set.contains(curr)){
                return curr;
            }
            curr = curr.next;
        }
        return null;
    }
}
```

方法2：最优解

思路如下图所示：

![image.png](https://pic.leetcode-cn.com/e0efdd908a8941e3c53a68a6049d88c437b8299e48540a772ab091b86695d981-image.png)

初始化 pA = headA, pB = headB，开始遍历。
以上图为例，pA会先到达链表尾，**当pA到达末尾时，重置pA为headB；同样的，当pB到达末尾时，重置pB为headA**。当pA与pB相遇时，必然就是两个链表的交点。

为什么要这样处理？因为这样的一个遍历过程，对pA而言，走过的路程即为`a+c+b`，对pB而言，即为`b+c+a`，显然`a+c+b = b+c+a`，这就是该算法的核心原理。

即使两个链表没有相交点，事实上，仍然可以统一处理，因为这种情况意味着相交点就是null，也就是上图中的公共部分c没有了，从而递推式变成了pA: `a+b`，pB:` b+a`，这同样是成立的。

```java
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode pA = headA, pB = headB;
        while (pA != pB) {
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
        }
        return pA;
    }
}
```



## [203. Remove Linked List Elements](https://leetcode-cn.com/problems/remove-linked-list-elements/) [一星]

删除链表中等于给定值 **val **的所有节点。

```java
class Solution {
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode prev = dummy;
        while(prev.next != null) {
            if(prev.next.val == val) {
                prev.next = prev.next.next;
                continue;
            }
            prev = prev.next;
        }
        return dummy.next;
    }
}
```



## [237. Delete Node in a Linked List](https://leetcode-cn.com/problems/delete-node-in-a-linked-list/) [五星]

在LeetCode中，还有另外一题（第237题）也是考察删除节点的，一开始觉得“这是什么弱智题目”，再仔细一想感觉“题目是不是出错了”，最后看了题解才发现是自己的问题。这一题提供了一个很好的处理链表的思路。

本题中，要求编写一个函数，使其可以删除某个链表中给定的（**非末尾**）节点，你将只被给定要求被删除的节点。并且给出了以下几个约束条件：

- 链表至少包含两个节点。
- 链表中所有节点的值都是**唯一**的。
- 给定的节点为**非末尾节点并且一定是链表中的一个有效节点**。
- 不要从你的函数中返回任何结果。

```java
public void deleteNode(ListNode node) {
        
}
```

题目只给出了这样一个函数，我们无法获取链表的头节点。**一般来说，我们要删除某个节点，就要获取该节点的前一个节点（prev）**，但是在本题中，只给出了待删除的节点，因此常规的思路行不通。由于该链表保证所有节点的值都是唯一的，且待删除的节点不是尾节点。因此可以这样考虑，如下所示。（思路参考[这里](https://leetcode-cn.com/problems/delete-node-in-a-linked-list/solution/shan-chu-lian-biao-zhong-de-jie-dian-by-leetcode/)）

从链表里删除一个节点 node 的最常见方法是修改**之前**节点的 next 指针，使其指向之后的节点。

由于我们无法访问我们想要删除的节点**之前**的节点，因此，我们可以**将想要删除的节点的值替换为它后面节点中的值，然后删除它之后的节点（这个时候，已知的这个节点就成了待删除节点的prev节点）**。如下图所示，已知给定node 3，且要求删除node 3，可以将node 3的值替换为在它后面的值，也就是4，然后再删除node 4即可。

![img](https://pic.leetcode-cn.com/3579a496897df5321c110bf1301872b6e10c342f5e400ce45d2db0348d00d715-file_1555866623326)

![img](https://pic.leetcode-cn.com/858fae01d89c2080eb7e45a1f9d9a2b2f76e1a5c87815b324fd946e0bd8da495-file_1555866651920)

![img](https://pic.leetcode-cn.com/902dc5d3f8c44d3cbc0b6e837711cad2eefc021fd2b9de8dfabc6d478bc779b1-file_1555866680932)

![img](https://pic.leetcode-cn.com/2a6409b98dd73d6649fdc6fb984c88690547127467104c3923367be6f8fbc916-file_1555866773685)

因为我们知道要删除的节点不是列表的末尾节点，所以我们可以保证这种方法是可行的。

```java
class Solution {
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
```



## [234. Palindrome Linked List](https://leetcode-cn.com/problems/palindrome-linked-list/) [一星, 常规题]

判断一个链表是否为回文链表。如果是数组，那么判断回文数非常简单，但是由于单向链表只能顺序遍历，不能从后向前访问，因此会比较麻烦。首先，利用快慢指针法找到链表的中间节点，然后翻转后半部分链表，然后再顺序比较这两部分的节点是否对应相等即可。

本题的关键在于翻转后半部分链表，同样的思路在[143. Reorder List](https://leetcode-cn.com/problems/reorder-list/) 也有运用。

```java
class Solution {
    // 时间复杂度：O(n)
    // 空间复杂度：O(1)
    public boolean isPalindrome(ListNode head) {
        if(head == null || head.next == null) return true;
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        if(fast != null) { // 节点个数为奇数个
            slow = slow.next;
        }
        
        slow = reverse(slow); // 将反转后的链表头节点重新设为slow
        fast = head; // 重置fast指针
        
        while (slow != null) {
            if(slow.val != fast.val) return false;
            slow = slow.next;
            fast = fast.next;
        }
        return true;
    }
    
    private ListNode reverse(ListNode head) {
        ListNode prev = null, curr = head;
        while(curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }
}
```



## [328. Odd Even Linked List](https://leetcode-cn.com/problems/odd-even-linked-list/) [五星, 第一遍的代码优雅]

给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性。要求时间复杂度为O(n)，空间复杂度为O(1)。

这一题差不多就是[138. Copy List with Random Pointer](https://leetcode-cn.com/problems/copy-list-with-random-pointer/) 这一题最后一部分”抽取链表“的部分~

我觉得做这个题的关键是如何设置while()循环条件，举几个例子（比如3个节点、4个节点这样的链表）试一下即可。自己写的代码就是标准代码了，非常精简~

prev节点处理奇数个节点，curr处理偶数个节点。两者均在原链表上进行操作，比较trick.

```java
// 写的真是经典啊~
class Solution {
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
}
```

时隔一个月后重写，速度更快了，但还不如第一次写的优雅~
思路：把奇数节点在原链表中操作，偶数节点连接到一个新链表中，最后把新链表连接在原链表的末尾。
考虑到原链表的个数有奇数个或偶数个，因此组要在语句1中判断一下，以保证最后curr不为空，可确保语句3不会出错。另外，使用这种做法千万不能忘记语句2，这行代码的意思是从原链表中”取出“第偶个数节点，如果不及时切断，那么它的next指针仍然会指向原链表中的节点，当原链表为奇数个节点时，忘记语句2会导致出现”环“，提交时显示超时。

```java
class Solution {
    public ListNode oddEvenList(ListNode head) {
        if(head == null) return null;
        ListNode dummy = new ListNode(-1);
        ListNode prev = dummy, curr = head;
        while(curr != null && curr.next != null) {
            ListNode t = curr.next;
            curr.next = curr.next.next;
            if(curr.next != null){ // 语句1
                curr = curr.next;
            }
            // 偶数链表
            t.next = null; // 语句2（关键）
            prev.next = t;
            prev = t;
        }
        curr.next = dummy.next; // 语句3
        return head;
    }
}
```



## [876. Middle of the Linked List](https://leetcode-cn.com/problems/middle-of-the-linked-list/) [一星]

求非空链表的中间节点。

```java
class Solution {
    public ListNode middleNode(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
```

