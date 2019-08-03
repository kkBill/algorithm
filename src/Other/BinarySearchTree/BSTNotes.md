本节内容参考《[算法](https://book.douban.com/subject/19952400/)》（第4版）实现了二叉搜索树（BinarySearchTree，BST）。

节点的结构如下：

```java
// 由于BST中的节点是按序存放的，故Key要继承自Compare
public class BST<Key extends Comparable<Key>, Value> {
    private Node root;
    // 内部类：定义树节点
    private class Node {
        private Key key;
        private Value value;
        private Node left, right;
        private int N; // 以该节点为根的子树中的节点总数

        Node(Key key, Value value, int N) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
            this.N = N;
        }
    }
    
	//...
}
```

其中包含如下几个常用的API：

* size(Node x)：获取以x为根节点的子树的节点总数
* get(Key key)：查找key并获取对应的值，如果树中不存在key，则返回bull
* put(Key key, Value value)：插入一个节点
* min(Node x)：获取以x为根节点的子树的最小节点
* max(Node x)：获取以x为根节点的子树的最大节点
* select(int k)：选择树中第k大的节点（BST的中序遍历是增序序列）
* rank(Key key)：查询key是BST中排名第几，是select()的逆方法
* deleteMin(Node x)：删除以x为根节点的子树的最小节点，并返回删除后该子树的根节点
* deleteMax(Node x)：删除以x为根节点的子树的最大节点，并返回删除后该子树的根节点
* **delete(Key key)**：删除key对应的节点，并返回删除后树的根节点
* printBSTByInOrder()：中序遍历，用于测试/验证
* printBSTByLevelOrder：层序遍历，用于测试/验证

其中最需要注意的就是delete()方法了，由于一些小的疏忽，在这里碰见了一个bug，记录如下。

在正确的代码中，该方法实现如下：

```java
private Node delete(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = delete(x.left, key);
        else if (cmp > 0) x.right = delete(x.right, key);
        else {
            // 如果待删除的节点只有一个孩子，则直接把孩子提升上来即可
            if (x.left == null) return x.right;
            if (x.right == null) return x.left;

            Node t = x; 
            x = min(t.right); 
            // 注意下面这两句话的顺序
            x.right = deleteMin(t.right); // 语句1
            x.left = t.left;              // 语句2
        }

        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }
```

在自己实现的时候，我把语句1和语句2的顺序对调了，导致在删除节点时出现错误（当被删除节点有左右孩子的情况），这是为什么呢？以下图为例（为了方便说明，这里把空节点也画出来），现在要删除节点10。

![bst1](https://github.com/kkBill/algorithm/tree/master/img/bst1.png)

如果按照正确的顺序（即语句1->语句2），则执行完语句1后，因为`t.right`是节点17，而`deleteMin(17)`的语义就是删除以节点17为根节点的子树的最小节点，由于该子树实际上只有节点17这么一个节点，因此它会被删除，并返回`null`作为x的右节点；执行完语句2后，会把x的左指针指向t的左节点，即节点6。结果如下图所示：

![bst1](D:\soft\others\YouDaoNoteImg\bst2.png)

当`return x`之后，其实就是这样的：

![bst1](D:\soft\others\YouDaoNoteImg\bst3.png)

这样操作后，节点17被返回，作为调整后子树的根节点，从而节点10就被删除了。

那么，如果语句1、2对调，会发生什么呢？我们再来画一遍图，看看会发生什么。

先执行`x.left = t.left;   `，则变成如下这样：

![bst1](D:\soft\others\YouDaoNoteImg\bst4.png)

再执行`x.right = deleteMin(t.right);`，**这个时候，`deleteMin(t.right)`就会把节点6给删除掉，并返回删除后子树的根节点，也就是节点17**，（请回顾deleteMin(Node x)的语义）。从而就变成了下面这样子：

![bst1](D:\soft\others\YouDaoNoteImg\bst5.png)

可见，顺序不正确就会发生致命的错误！





