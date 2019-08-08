/*
Moving Average from Data Stream

Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.

Example:

MovingAverage m = new MovingAverage(3);
m.next(1) = 1
m.next(10) = (1 + 10) / 2
m.next(3) = (1 + 10 + 3) / 3
m.next(5) = (10 + 3 + 5) / 3

https://leetcode.com/problems/moving-average-from-data-stream/
*/

class MovingAverage {
    int[] arr;
    int n;
    int head;
    int sum;
    int cur;

    public MovingAverage(int size) {
        sum = 0;
        n = size;
        head = 0;
        cur = 0;
        arr = new int[n];
    }
    
    public double next(int val) {
        int last = arr[head];
        arr[head] = val;
        head = (head + 1) % n;
        cur++;
        int size = cur;
        sum += val;
        if (size > n) {
            sum -= last;
            size = n;
        }
        return (double) sum / size;            
    }
}
