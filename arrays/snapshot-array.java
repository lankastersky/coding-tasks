/*
Snapshot Array

Implement a SnapshotArray that supports the following interface:

SnapshotArray(int length) initializes an array-like data structure with the given length.  Initially, each element equals 0.
void set(index, val) sets the element at the given index to be equal to val.
int snap() takes a snapshot of the array and returns the snap_id: the total number of times we called snap() minus 1.
int get(index, snap_id) returns the value at the given index, at the time we took the snapshot with the given snap_id
 
Example 1:

Input: ["SnapshotArray","set","snap","set","get"]
[[3],[0,5],[],[0,6],[0,0]]
Output: [null,null,0,null,5]
Explanation: 
SnapshotArray snapshotArr = new SnapshotArray(3); // set the length to be 3
snapshotArr.set(0,5);  // Set array[0] = 5
snapshotArr.snap();  // Take a snapshot, return snap_id = 0
snapshotArr.set(0,6);
snapshotArr.get(0,0);  // Get the value of array[0] with snap_id = 0, return 5

https://leetcode.com/problems/snapshot-array/
leetcode - google phone interview
*/

class SnapshotArray {
    class Pair {
        int sn;
        int val;
        Pair(int s, int v) {
            sn = s;
            val = v;
        }
    }
    Map<Integer, List<Pair>> map;
    int sn;
    public SnapshotArray(int length) {
        map = new HashMap<>();
        for (int i = 0; i < length; i++) {
            map.put(i, new ArrayList<>());
        }
    }
    
    public void set(int index, int val) {
        List<Pair> arr = map.get(index);
        if (arr.isEmpty()) {
            arr.add(new Pair(sn, val));
        } else {
            Pair p = arr.get(arr.size() - 1);
            if (p.sn == sn) {
                p.val = val;            
            } else if (p.val != val) {
                arr.add(new Pair(sn, val));
            }
        }
    }
    
    public int snap() {
        sn++;
        return sn - 1;
    }
    
    public int get(int index, int snap_id) {
        List<Pair> arr = map.get(index);
        for (int i = arr.size() - 1; i >= 0; i--) {
            Pair p = arr.get(i);
            if (p.sn <= snap_id) {
                return p.val;
            }
        }
        return 0;
    }
}

/**
 * Your SnapshotArray object will be instantiated and called as such:
 * SnapshotArray obj = new SnapshotArray(length);
 * obj.set(index,val);
 * int param_2 = obj.snap();
 * int param_3 = obj.get(index,snap_id);
 */
