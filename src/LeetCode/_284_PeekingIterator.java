package LeetCode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/*
// 这么做虽然实现了指定功能，但是使用了O(n)的空间，是不允许的
class PeekingIterator implements Iterator<Integer> {

    private List<Integer> list;
    private int curr;
    public PeekingIterator(Iterator<Integer> iterator) {
        // initialize any member here.
        list = new ArrayList<>();
        curr = 0;
        while (iterator.hasNext()) {
            list.add(iterator.next());
        }
    }

    // Returns the next element in the iteration without advancing the iterator.
    public Integer peek() {
        return list.get(curr);
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public Integer next() {
        Integer ans = list.get(curr);
        curr++;
        return ans;
    }

    @Override
    public boolean hasNext() {
        return curr < list.size();
    }
}
*/

// 添加缓存的思想~
class PeekingIterator implements Iterator<Integer> {

    private Iterator<Integer> iterator;
    private Integer cache;
    public PeekingIterator(Iterator<Integer> iterator) {
        // initialize any member here.
        this.cache = null;
        this.iterator = iterator;
    }

    // Returns the next element in the iteration without advancing the iterator.
    public Integer peek() {
        if(cache == null) {
            cache = iterator.next();
        }
        return cache;
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public Integer next() {
        if(cache == null) {
            return iterator.next();
        }
        // 如果cache非空，说明之前调用过peek()
        Integer temp = cache;
        cache = null;
        return temp;
    }

    @Override
    public boolean hasNext() {
        return cache != null || iterator.hasNext();
    }
}

public class _284_PeekingIterator {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
//        Iterator<Integer> iterator = list.iterator();
//        iterator.forEachRemaining(item ->System.out.println(item));
//        System.out.println(iterator.next()); // 1
//        System.out.println(iterator.next()); // 2
//        System.out.println(iterator.hasNext());
//        System.out.println(iterator.next());
//        System.out.println(iterator.hasNext());

        PeekingIterator iterator = new PeekingIterator(list.iterator());
        System.out.println(iterator.next()); // 1
        System.out.println(iterator.peek()); // 2
        System.out.println(iterator.next()); // 2
        System.out.println(iterator.hasNext()); // true
        System.out.println(iterator.next()); // 3
        System.out.println(iterator.hasNext()); // false
    }
}
