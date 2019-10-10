/*
Find Median from Data Stream
Hard

Median is the middle value in an ordered integer list. If the size of the list is even, 
there is no middle value. So the median is the mean of the two middle value.

For example,
[2,3,4], the median is 3

[2,3], the median is (2 + 3) / 2 = 2.5

Design a data structure that supports the following two operations:

void addNum(int num) - Add a integer number from the data stream to the data structure.
double findMedian() - Return the median of all elements so far.

Example:
addNum(1)
addNum(2)
findMedian() -> 1.5
addNum(3) 
findMedian() -> 2
 
Follow up:
If all integer numbers from the stream are between 0 and 100, how would you optimize it?
If 99% of all integer numbers from the stream are between 0 and 100, how would you optimize it?

https://leetcode.com/problems/find-median-from-data-stream/
*/

class MedianFinder {

  PriorityQueue<Integer> minHeap;
  PriorityQueue<Integer> maxHeap;
  
  /** initialize your data structure here. */
  public MedianFinder() {
    minHeap = new PriorityQueue<>();
    maxHeap = new PriorityQueue<>(Collections.reverseOrder());
  }

  public void addNum(int num) {
    if (maxHeap.isEmpty() || num <= maxHeap.peek()) {
      maxHeap.add(num);
    } else {
      minHeap.add(num);
    }
    if (maxHeap.size() - minHeap.size() > 1) {
      minHeap.add(maxHeap.remove());
    } else if (minHeap.size() > maxHeap.size()) {
      maxHeap.add(minHeap.remove());
    }
  }

  public double findMedian() {
    if (maxHeap.isEmpty()) {
      return 0;
    }
    if (minHeap.size() == maxHeap.size()) {
      double min = minHeap.peek();
      double max = maxHeap.peek();
      return (min + max) / 2;
    }
    return maxHeap.peek();
  }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
