/*
Race Car

Your car starts at position 0 and speed +1 on an infinite number line.  (Your car can go into negative positions.)
Your car drives automatically according to a sequence of instructions A (accelerate) and R (reverse).
When you get an instruction "A", your car does the following: position += speed, speed *= 2.
When you get an instruction "R", your car does the following: if your speed is positive then speed = -1 , otherwise 
speed = 1.  (Your position stays the same.)
For example, after commands "AAR", your car goes to positions 0->1->3->3, and your speed goes to 1->2->4->-1.
Now for some target position, say the length of the shortest sequence of instructions to get there.

Example 1:
Input: 
target = 3
Output: 2
Explanation: 
The shortest instruction sequence is "AA".
Your position goes from 0->1->3.

Example 2:
Input: 
target = 6
Output: 5
Explanation: 
The shortest instruction sequence is "AAARA".
Your position goes from 0->1->3->7->7->6.
 
Note:
1 <= target <= 10000.

https://leetcode.com/problems/race-car/
*/

class Solution {
  class Seq {
    int pos;
    int speed = 1;
    Seq() {
    }
    Seq(int pos, int speed) {
      this.pos = pos;
      this.speed = speed;
    }
    void move(char c) {
      if (c == 'A') {
        pos += speed;
        speed *= 2;
      } else {
        speed = speed > 0 ? -1 : 1;
      }
    }
    String key() {
      return speed + "_" + pos;
    }
  }
  
  // Based on https://leetcode.com/problems/race-car/discuss/142360/Logical-Thinking-with-Clean-Java-Code
  public int racecar(int target) {
    Queue<Seq> queue = new LinkedList<>();
    Seq seq = new Seq();
    queue.add(seq);
    Set<String> seen = new HashSet<>();
    seen.add(seq.key());
    int res = 0;
    while (!queue.isEmpty()) {
      int size = queue.size();
      for (int i = 0; i < size; i++) {
        seq = queue.remove();
        if (seq.pos == target) {
          return res;
        }
        Seq aseq = new Seq(seq.pos, seq.speed);
        aseq.move('A');
        if (!seen.contains(aseq.key()) && (Math.abs(aseq.pos - target) < target)) {
          queue.add(aseq);
          seen.add(aseq.key());
        } 

        Seq rseq = new Seq(seq.pos, seq.speed);
        rseq.move('R');
        if (!seen.contains(rseq.key())) {
          queue.add(rseq);          
          seen.add(rseq.key());
        }
      }
      res++;
    }
    return res;
  }
}
