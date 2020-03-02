### 搜索问题汇总

题目列表：

1. [200. 岛屿数量](https://leetcode-cn.com/problems/number-of-islands/) [三星，模板]
2. [695. 岛屿的最大面积](https://leetcode-cn.com/problems/max-area-of-island/) [三星，模板]
3. [130. 被围绕的区域](https://leetcode-cn.com/problems/surrounded-regions/) [四星]
4. [133. 克隆图](https://leetcode-cn.com/problems/clone-graph/) [五星]
5. [127. 单词接龙](https://leetcode-cn.com/problems/word-ladder/) [五星++]
6. [433. 最小基因变化](https://leetcode-cn.com/problems/minimum-genetic-mutation/) [五星]
7. [207. 课程表](https://leetcode-cn.com/problems/course-schedule/)
8. [210. 课程表 II](https://leetcode-cn.com/problems/course-schedule-ii/) [拓扑排序的两种方法，BFS & DFS]
9. ​



##### [200. 岛屿数量](https://leetcode-cn.com/problems/number-of-islands/) [三星，模板]

给定一个由'0'和'1'构成的二维矩阵，认为所有相邻的'1'构成为1块，计算总共由多少块。比如：

```
输入:
11000
11000
00100
00011

输出: 3
```

分析：利用dfs搜索。

第1版代码

```java
class Solution {
    private int[] x_axis = new int[]{1,-1,0,0};
    private int[] y_axis = new int[]{0,0,1,-1};
    private int ROWS;
    private int COLS;
    private char[][] grid;
    private boolean[][] visited;

    public int numIslands(char[][] grid) {
        ROWS = grid.length;
        if(ROWS == 0) return 0;
        COLS = grid[0].length;
        this.grid = grid;
        visited = new boolean[ROWS][COLS];

        int numberOfIsland = 0;
        for(int x = 0; x < ROWS; x++) {
            for(int y = 0; y < COLS; y++) {
                if(grid[x][y] == '1' && !visited[x][y]){
                    dfs(x, y);
                    numberOfIsland++;
                }
            }
        }
        return numberOfIsland;
    }

    private void dfs(int x, int y) {
        visited[x][y] = true;

        // 分别向上、下、左、右遍历
        for(int i = 0; i < 4; i++) {
            int nextX = x + x_axis[i];
            int nextY = y + y_axis[i];
            // 验证位置(nextX, nextY)是否在边界内
            boolean outOfBoundary = nextX < 0 || nextX >= ROWS || nextY < 0 || nextY >= COLS;
            if(!outOfBoundary && grid[nextX][nextY] == '1' && !visited[nextX][nextY]) {
                dfs(nextX, nextY);
            }
        }
    }
}
```

第2版代码

```java
class Solution {
    private int[] x_axis = new int[]{1, -1, 0, 0};
    private int[] y_axis = new int[]{0, 0, 1, -1};

    public int numIslands(char[][] grid) {
        if(grid == null || grid.length == 0) return 0;
        int rows = grid.length, cols = grid[0].length;

        int numberOfIsland = 0;
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                if (grid[x][y] == '1') {
                    dfs(grid, x, y);
                    numberOfIsland++;
                }
            }
        }
        return numberOfIsland;
    }

    private void dfs(char[][] grid, int x, int y) {
        grid[x][y] = 0; // 将1标记为0，表示已经访问过了
        for (int i = 0; i < 4; i++) { // 分别向上、下、左、右4个位置遍历
            int nextX = x + x_axis[i];
            int nextY = y + y_axis[i];
            boolean outOfBoundary = nextX < 0 || nextX >= grid.length || 
                                    nextY < 0 || nextY >= grid[0].length;
            if (!outOfBoundary && grid[nextX][nextY] == '1') {
                dfs(grid, nextX, nextY);
            }
        }
    }
}
```



##### [695. 岛屿的最大面积](https://leetcode-cn.com/problems/max-area-of-island/) [三星，模板]

和[200. 岛屿数量](https://leetcode-cn.com/problems/number-of-islands/)类似，本题求的是‘1’构成的连通块的最大面积。

```java
class Solution {
    private int[] x_axis = new int[]{1, -1, 0, 0};
    private int[] y_axis = new int[]{0, 0, 1, -1};
    private int ROWS;
    private int COLS;
    private int[][] grid;
    private boolean[][] visited;

    public int maxAreaOfIsland(int[][] grid) {
        ROWS = grid.length;
        if (ROWS == 0) return 0;
        COLS = grid[0].length;
        this.grid = grid;
        visited = new boolean[ROWS][COLS];

        int maxArea = 0;
        for (int x = 0; x < ROWS; x++) {
            for (int y = 0; y < COLS; y++) {
                if (grid[x][y] == 1 && !visited[x][y]) {
                    int area = dfs(x, y);
                    maxArea = Math.max(maxArea, area);
                }
            }
        }
        return maxArea;
    }

    private int dfs(int x, int y) {
        visited[x][y] = true;
        int area = 1;
        for (int i = 0; i < 4; i++) { // 分别向上、下、左、右遍历
            int nextX = x + x_axis[i];
            int nextY = y + y_axis[i];
            // 验证位置(nextX, nextY)是否在边界内
            boolean outOfBoundary = nextX < 0 || nextX >= ROWS || nextY < 0 || nextY >= COLS;
            if (!outOfBoundary && grid[nextX][nextY] == 1 && !visited[nextX][nextY]) {
                area += dfs(nextX, nextY);
            }
        }
        return area;
    }
}
```



##### [130. 被围绕的区域](https://leetcode-cn.com/problems/surrounded-regions/) [四星]

给定一个二维的矩阵，包含 `'X'` 和 `'O'`（**字母 O**）。找到所有被 `'X'` 围绕的区域，并将这些区域里所有的 `'O'` 用 `'X'` 填充。被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被填充为 'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。

分析：考虑到边界上的‘O'以及和边界上的'O'连通的'O'块都不能被填充为’X‘，因此先设法处理这部分的'O'。因此，第1步是先dfs把边界'O'的连通块替换为其他字符，比如'#'，此时剩下的'O'（如果还有的话）就是属于矩阵内部被'X'包围的了。第2步就是遍历矩阵，遇到'O'则替换为'X'；遇到’#‘就还原为'O'即可。

```java
class Solution {
    private char[][] board;
    private int rows;
    private int cols;

    public void solve(char[][] board) {
        rows = board.length;
        if (rows == 0) return;
        cols = board[0].length;
        this.board = board;
        // 检查board的四个边界，如有发现'O'，把与其连通的'O'均替换成'#'
        for (int c = 0; c < cols; c++) {
            if (board[0][c] == 'O') dfs(0, c, '#');
            if (board[rows - 1][c] == 'O') dfs(rows - 1, c, '#');
        }
        for (int r = 0; r < rows; r++) {
            if (board[r][0] == 'O') dfs(r, 0, '#');
            if (board[r][cols - 1] == 'O') dfs(r, cols - 1, '#');
        }

        // 检查board的内部，如遇到'O'，将其替换成'X'；如遇到'#'，则将其还原为'O'
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (board[r][c] == 'O') board[r][c] = 'X';
                if (board[r][c] == '#') board[r][c] = 'O';
            }
        }
    }

    private void dfs(int row, int col, char c) {
        // 判断是否越界
        if (row < 0 || row >= rows || col < 0 || col >= cols
                || board[row][col] == c || board[row][col] == 'X') return;
        board[row][col] = c;
        dfs(row + 1, col, c);
        dfs(row - 1, col, c);
        dfs(row, col + 1, c);
        dfs(row, col - 1, c);
    }
}
```



##### [133. 克隆图](https://leetcode-cn.com/problems/clone-graph/) [五星]

给出一个无向连通图的一个节点，要求对该图进行**深拷贝**。图节点的定义如下：

```java
class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
```

分析：本题考察的就是图的遍历，对DFS和BFS要非常熟悉。

DFS

```java
class Solution {
    private Node[] map = new Node[101];

    public Node cloneGraph(Node node) {
        if (node == null) return null;
        return dfs(node);
    }

    public Node dfs(Node node) {
        if (map[node.val] != null) {
            return map[node.val];
        }
        Node curr = new Node(node.val, new ArrayList<>());
        map[curr.val] = curr;
        for (Node n : node.neighbors) {
            curr.neighbors.add(dfs(n));
        }
        return curr;
    }
}
```

BFS

```java
class Solution {
    public Node cloneGraph(Node node) {
        if (node == null) return null;

        Queue<Node> queue = new LinkedList<>();
        Map<Node, Node> map = new HashMap<>(); // 原节点和复制节点的映射
        queue.add(node);
        map.put(node, new Node(node.val, new ArrayList<>()));
        while (!queue.isEmpty()) {
            Node parent = queue.poll();
            for (Node child : parent.neighbors) {
                if(!map.containsKey(child)) {
                    queue.add(child);
                    map.put(child, new Node(child.val, new ArrayList<>()));
                }
                map.get(parent).neighbors.add(map.get(child));
            }
        }
        return map.get(node);
    }
}
```



##### [127. 单词接龙](https://leetcode-cn.com/problems/word-ladder/) [五星++, 双向BFS]

给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord 的最短转换序列的长度。转换需遵循如下规则：

* 每次转换只能改变一个字母。
* 转换过程中的中间单词必须是字典中的单词。

说明:

* 如果不存在这样的转换序列，返回 0。
* 所有单词具有相同的长度。
* 所有单词只由小写字母组成。
* 字典中不存在重复的单词。
* 你可以假设 beginWord 和 endWord 是非空的，且二者不相同。

```java
输入:
beginWord = "hit",
endWord = "cog",
wordList = ["hot","dot","dog","lot","log","cog"]
输出: 5
解释: 一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog", 返回它的长度 5。

输入:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log"]
输出: 0
解释: endWord "cog" 不在字典中，所以无法进行转换。
```

分析：思路参考自[官方题解](https://leetcode-cn.com/problems/word-ladder/solution/dan-ci-jie-long-by-leetcode/)。

我们可以将这个问题抽象成一个**有向无环图**，即每个单词作为一个节点，两个只差一个字母的单词之间连一条边。问题就变成求从起点到终点(如果有的话)的最短路径，求最短路径问题可以通过BFS来解决。

而这一解法最重要的一点是：**如何找出两个只差一个字母的单词？**

为了快速的找到这些相邻节点，我们对给定的 `wordList` 做一个预处理，将单词中的某个字母用 `*` 代替。![Word_Ladder_2.png](https://pic.leetcode-cn.com/7212249f3e224d9d5ccbc292e902e48b572f965236378e034d8e03924404cba2-Word_Ladder_2.png)

通过预处理，构造出一个单词的**通用形式（generic form）**，如上图所示，`Hot`由3个字符组成，因此对应的有3种通用形式`*ot`，`H*t`，`Ho*`。 以`*ot`为例，在给定的`wordList`中，与之匹配的就有`hot, dot`和`lot`这3个单词。在预处理中，我们利用哈希表来存储**通用形式**与**其对应的单词列表**的映射关系，比如

```
key = *ot, value = <hot, dot, lot>
```

预处理结束后，我们可以认为图就构建完成了。只不过图的节点不像常规的那么明显。而是存储在map的value列表中。我们先来看一下常规的BFS模板，其算法流程如下：

```
1. put root-node into queue;
2. mark root-node as in-queue; 
3. while(queue is not empty) {
4.     node = pop first node of queue;
5.     visit node；
6.     for each node_i in neighbors of node {
7.          if node_i is not in queue, then push it into queue.  
8.	   }
9.}
```

而本题相比于这个常规的BFS遍历有什么区别呢？其区别在于，由一个节点延伸出去的邻边（假设单词的长度为3）包含3中可能，而每种可能性又对应一个单词列表。因此在遍历每个节点的邻接点时（也就是上述伪代码的第6行），需要额外做一些处理。这些**额外处理**的部分就是本题的难点所在，这也是为什么要进行预处理的原因，理解本题主要也就是要理解到这一点。下面结合代码理解：

**方法1**

```java
class Solution {
    // 时间复杂度：O(m*n) m是单词的长度，n是wordList中单词的个数
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        boolean existEndWord = false;
        // 预处理，d*g -> dog, dig
        Map<String, List<String>> dict = new HashMap<>();
        for (String word : wordList) {
            if (word.equals(endWord)) {
                existEndWord = true;
            }
            for (int i = 0; i < word.length(); i++) {
                String key = word.substring(0, i) + "*" + word.substring(i + 1);
                List<String> list = dict.getOrDefault(key, new ArrayList<>());
                list.add(word);
                dict.put(key, list);
            }
        }
        // 如果wordList中不存在endWord，直接返回0，结束程序
        if (!existEndWord) return 0;

        // BFS
        // 一个pair表示一个图节点，其中Integer表示该节点所在的层级
        Queue<Pair<String, Integer>> queue = new LinkedList<>();
        // 标记某个节点是否已经被访问过
        Map<String, Boolean> visited = new HashMap<>(); 
        queue.add(new Pair<>(beginWord, 1)); 
        visited.put(beginWord, true);

        while (!queue.isEmpty()) {
            Pair<String, Integer> node = queue.poll();
            String word = node.getKey();
            int depth = node.getValue();
			
            for (int i = 0; i < word.length(); i++) {
                // 在访问邻接点之前，首先生成该单词对应的通配形式
              	String key = word.substring(0, i) + "*" + word.substring(i + 1);
                // 如果预处理的字典中存在这一通配形式，说明找到了邻边
                if (dict.containsKey(key)) {
                    List<String> childList = dict.get(key);
                    for (String child : childList) {
                        if (child.equals(endWord)) {
                            return depth + 1;
                        }
                        if (!visited.containsKey(child)) {
                            queue.add(new Pair<>(child, depth + 1));
                            visited.put(child, true);
                        }
                    }
                }
            }
        }
        return 0;
    }
}
```



**方法2**：上面的方法本质上还是基于常规的BFS，但是在本题的场景中，`endword`是确定的，也就是说，搜索路径的尾部是唯一确定的。于是我们考虑，能否一边从`beginword`开始向下搜索，一边从`endword`开始向上搜索，双向搜索同时进行，直到两者同时访问某个节点，算法结束。这就是双向BFS。

通过双向广度优先搜索，可以有效减少搜索空间大小，从而降低时间和空间复杂度。如果只是从上到下搜索的话，要进入很多无用的分支。

![Word_Ladder_3.png](https://pic.leetcode-cn.com/be92086801e264f49bb1c01593dbfee5b08e52c600b62576c5fa0c1ef2d54eb8-Word_Ladder_3.png)

```java
class Solution {
    // 双向BFS
    private Map<String, List<String>> dict = new HashMap<>();
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        boolean existEndWord = false;
        // 预处理，d*g -> dog, dig
        for (String word : wordList) {
            if (word.equals(endWord)) {
                existEndWord = true;
            }
            for (int i = 0; i < word.length(); i++) {
                String key = word.substring(0, i) + "*" + word.substring(i + 1);
                List<String> list = dict.getOrDefault(key, new ArrayList<>());
                list.add(word);
                dict.put(key, list);
            }
        }
        // 如果wordList中不存在endWord，直接返回0，结束程序
        if (!existEndWord) return 0;

        // 双向广度优先搜索, 1 记录从上至下；2 记录从下至上
        Queue<Pair<String, Integer>> queue1 = new LinkedList<>();
        Map<String, Integer> visited1 = new HashMap<>();
        queue1.add(new Pair<>(beginWord, 1));
        visited1.put(beginWord, 1);

        Queue<Pair<String, Integer>> queue2 = new LinkedList<>();
        Map<String, Integer> visited2 = new HashMap<>();
        queue2.add(new Pair<>(endWord, 1));
        visited2.put(endWord, 1);

        while (!queue1.isEmpty() && !queue2.isEmpty()) {
            int ans1 = visitNode(queue1,visited1,visited2);
            if(ans1 != -1) return ans1;

            int ans2 = visitNode(queue2,visited2,visited1);
            if(ans2 != -1) return ans2;
        }
        return 0;
    }

    private int visitNode(Queue<Pair<String, Integer>> queue,
                          Map<String, Integer> visited1,
                          Map<String, Integer> visited2) {
        Pair<String, Integer> node = queue.poll();
        String word = node.getKey();
        int depth = node.getValue();

        for (int i = 0; i < word.length(); i++) {
            String key = word.substring(0, i) + "*" + word.substring(i + 1);
            if (dict.containsKey(key)) {
                List<String> childList = dict.get(key);
                for(String child : childList) {
                    // 如果该节点已经被另一个方向访问过了，可以直接返回结果
                    if(visited2.containsKey(child)) {
                        return depth + visited2.get(child);
                    }

                    if(!visited1.containsKey(child)) {
                        queue.add(new Pair<>(child, depth + 1));
                        visited1.put(child, depth + 1);
                    }
                }
            }
        }
        return -1;
    }
}
```

**方法3**

上面两种方法的核心在于预处理部分，也就是如何处理两个只相差一个字符的单词。我们也可以通过逐个替换字符，判断被替换后的单词是否出现在给定的`wordList`中的方法来做。因为题目已经说明了单词只包含小写字母，因此一个位置替换只需要尝试26次，还是在常数范围内。



##### [433. 最小基因变化](https://leetcode-cn.com/problems/minimum-genetic-mutation/) [五星]

本题和第[127. 单词接龙](https://leetcode-cn.com/problems/word-ladder/) 一模一样。这里采用上面说地方法3来做。

```java
class Solution {
    public int minMutation(String start, String end, String[] bank) {
        Set<String> set = new HashSet<>(Arrays.asList(bank));
        if(!set.contains(end)) return -1;

        char[] choices = new char[] {'A', 'C', 'G', 'T'};
        Queue<String> queue = new LinkedList<>();
        queue.add(start);
        int level = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            //attention! "k < queue.size();" is wrong, cause queue.size() grow dynamically
            for(int k = 0; k < size; k++) { 
                String temp = queue.poll();
                // 寻找temp的邻边
                for (int i = 0; i < temp.length(); i++) { //O(8)
                    for (int j = 0; j < choices.length; j++) { //O(4)
                        char[] word = temp.toCharArray();
                        word[i] = choices[j];
                        String newWord = String.valueOf(word);
                        if(newWord.equals(end)) {
                            return level;
                        }
                        if (set.contains(newWord)) {
                            queue.add(newWord);
                            set.remove(newWord); //相当于标记已经访问过
                        }
                    }
                }
            }
            level++;
        }
        return -1;
    }
}
```



##### [207. 课程表](https://leetcode-cn.com/problems/course-schedule/)

##### [210. 课程表 II](https://leetcode-cn.com/problems/course-schedule-ii/)

这两题合在一起讲解，属于拓扑序列的题目。其实非常简单，属于模板题，在PAT中已经见过。其中第207题就是判断是否存在拓扑序列，第210题就是求出拓扑序列。

解决拓扑排序的问题有两种思路，一种是使用BFS（称为Kahn算法）；一种是使用DFS。

方法1：使用BFS，这种方法主要是维护一个入度数组

1. 根据题目信息构建图结构的同时记录每个节点的入度；
2. 遍历入度数组，把所有入度为0的节点存入队列中。入度为0说明该节点没有任何前置节点；
3. 只要队列非空，就取出队首元素并访问该节点的邻接点，同时所有邻接点的入度减1，如果某个邻接点的入度变成了0，就把它入队。

207题

```java
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // 构建图
        Map<Integer, List<Integer>> graph = new HashMap<>();
        int[] indegree = new int[numCourses]; // 统计节点的入度
        for(int i = 0; i < prerequisites.length; i++) {
            List<Integer> adjacentList = 
                               graph.getOrDefault(prerequisites[i][1], new ArrayList<>());
            adjacentList.add(prerequisites[i][0]);
            graph.put(prerequisites[i][1], adjacentList);
            indegree[prerequisites[i][0]]++;
        }

        // 拓扑排序
        // 1.首先把入度为0的节点入队
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i < numCourses; i++){
            if(indegree[i] == 0) queue.add(i);
        }
        // 2.开始BFS
        int count = 0;
        while (!queue.isEmpty()) {
            int courseId = queue.poll();
            count++;
            if(graph.get(courseId) != null) {
                for (int adjacentId : graph.get(courseId)) {
                    indegree[adjacentId]--;
                    if (indegree[adjacentId] == 0) queue.add(adjacentId);
                }
            }
        }
        return count == numCourses;
    }
}
```

210题

```java
class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // 构建图
        Map<Integer, List<Integer>> graph = new HashMap<>();
        int[] indegree = new int[numCourses]; // 统计节点的入度
        for(int i = 0; i < prerequisites.length; i++) {
            List<Integer> adjacentList = 
                               graph.getOrDefault(prerequisites[i][1], new ArrayList<>());
            adjacentList.add(prerequisites[i][0]);
            graph.put(prerequisites[i][1], adjacentList);
            indegree[prerequisites[i][0]]++;
        }

        // 拓扑排序
        // 1.首先把入度为0的节点入队
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i < numCourses; i++){
            if(indegree[i] == 0) queue.add(i);
        }
        // 2.开始BFS
        int count = 0;
        int[] topoSeq = new int[numCourses];
        while (!queue.isEmpty()) {
            int courseId = queue.poll();
            topoSeq[count] = courseId;
            count++;
            if(graph.get(courseId) != null) {
                for (int adjacentId : graph.get(courseId)) {
                    indegree[adjacentId]--;
                    if (indegree[adjacentId] == 0) queue.add(adjacentId);
                }
            }
        }

        if(count != numCourses) return new int[0]; // 不存在合法的拓扑序列
        return topoSeq;
    }
}
```



方法2：使用DFS

207题：代码不贴了。

210题：参考[官方题解](https://leetcode-cn.com/problems/course-schedule-ii/solution/ke-cheng-biao-ii-by-leetcode/) 。

```java
class Solution {
    static int WHITE = 1; // 表示节点尚未被访问过
    static int GRAY = 2; // 表示节点正在被访问中
    static int DARK = 3; // 表示节点已经访问结束
    private boolean existCycle = false;
    private int[] topoSeq;
    private int len; // 拓扑序列topoSeq的长度
    private Map<Integer, List<Integer>> graph = new HashMap<>();
    private Map<Integer, Integer> color = new HashMap<>();

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // 初始化
        topoSeq = new int[numCourses];
        len = numCourses;
        for(int v = 0; v < numCourses; v++) {
            color.put(v, WHITE);
        }

        // 构建图
        for (int i = 0; i < prerequisites.length; i++) {
            List<Integer> adjacentList = 
                             graph.getOrDefault(prerequisites[i][1], new ArrayList<>());
            adjacentList.add(prerequisites[i][0]);
            graph.put(prerequisites[i][1], adjacentList);
        }

        // DFS
        for(int v = 0; v < numCourses; v++) {
            if(color.get(v) == WHITE) {
                dfs(v);
                if(existCycle) break;
            }
        }

        if(existCycle) return new int[0];
        return topoSeq;
    }

    private void dfs(int v) {
        color.put(v, GRAY);
        List<Integer> adjacentList = graph.get(v);
        if(adjacentList == null) {
            color.put(v, DARK);
            topoSeq[--len] = v;
            return;
        }
        for(int w : adjacentList) {
            if(color.get(w) == WHITE) {
                dfs(w);
            }
            if(color.get(w) == GRAY) {
                existCycle = true;
                return;
            }
        }
        color.put(v, DARK);
        topoSeq[--len] = v;
    }
}
```





