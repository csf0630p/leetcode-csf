// https://www.cnblogs.com/grandyang/p/4266812.html
// 1.0 greedy, iterative
class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
		int remain = 0, debt = 0, startPt = 0;
		for (int i = 0; i < gas.length; i++) {
			remain += (gas[i] - cost[i]);
			if (remain < 0) {
				startPt = i + 1;
				debt += remain;
				remain = 0;
			}
		}
		return remain + debt < 0 ? -1 : startPt;       
    }
}
// 1.1
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int total = 0, sum = 0, start = 0;
        for (int i = 0; i < gas.length; ++i) {
            total += gas[i] - cost[i];
            sum += gas[i] - cost[i];
            if (sum < 0) {
                start = i + 1;
                sum = 0;
            }
        }
        return (total < 0) ? -1 : start;    
    }	

// 2.0 reverse
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int total = 0, max = -1, start = 0;
        for (int i = gas.length - 1; i >= 0; --i) {
            total += gas[i] - cost[i];
            if (total > max) {
                start = i;
                max = total;
            }
        }
        return (total < 0) ? -1 : start; 
    }
