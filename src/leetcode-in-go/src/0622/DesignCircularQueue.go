package main

import "fmt"

type MyCircularQueue struct {
	queue []int
	size  int
	front int
	rear  int
}

/** Initialize your data structure here. Set the size of the queue to be k. */
func Constructor(k int) MyCircularQueue {
	return MyCircularQueue{
		queue: make([]int, k+1),
		size:  k + 1,
		front: 0,
		rear:  0,
	}
}

/** Insert an element into the circular queue. Return true if the operation is successful. */
func (this *MyCircularQueue) EnQueue(value int) bool {
	if this.IsFull() {
		return false
	}
	this.queue[this.rear] = value
	this.rear = (this.rear + 1) % this.size
	return true
}

/** Delete an element from the circular queue. Return true if the operation is successful. */
func (this *MyCircularQueue) DeQueue() bool {
	if this.IsEmpty() {
		return false
	}
	this.front = (this.front + 1) % this.size
	return true
}

/** Get the front item from the queue. */
func (this *MyCircularQueue) Front() int {
	if this.IsEmpty() {
		return -1
	}
	return this.queue[this.front]
}

/** Get the last item from the queue. */
func (this *MyCircularQueue) Rear() int {
	if this.IsEmpty() {
		return -1
	}
	t := this.rear
	t = (t + this.size - 1) % this.size
	return this.queue[t]
}

/** Checks whether the circular queue is empty or not. */
func (this *MyCircularQueue) IsEmpty() bool {
	return this.rear == this.front
}

/** Checks whether the circular queue is full or not. */
func (this *MyCircularQueue) IsFull() bool {
	return (this.rear+1)%this.size == this.front
}

func main() {
	obj := Constructor(8);
	fmt.Println(obj.EnQueue(3)) // true
	fmt.Println(obj.EnQueue(5)) // true
	fmt.Println(obj.EnQueue(9)) // true
	fmt.Println(obj.EnQueue(0)) // false
	fmt.Println(obj.DeQueue())  // true
	fmt.Println(obj.DeQueue())  // true
	fmt.Println("rear:", obj.rear)
	fmt.Println(obj.IsEmpty()) // false
	fmt.Println(obj.IsEmpty()) // false
	fmt.Println(obj.Rear())
	fmt.Println(obj.Rear())
	fmt.Println(obj.DeQueue())  // true
}
