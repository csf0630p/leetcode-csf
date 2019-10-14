// https://leetcode.com/problems/maximum-equal-frequency/discuss/404005/Java-O(N)
// O(n)/O(max)
class Solution {
    public int maxEqualFreq(int[] nums) {
        if (nums.length <= 1) return 1;
        int[] mem = new int[100001];
        int maxRet = 0;             // 记录结果
        int maxRepeatNum = 0;       // 最大重复元素的个数
        int numOfMaxRepeat = 0;     // 出现次数为maxRepeatNum的元素个数
        int k = 0;                  // prefix中不同元素的个数


        for (int i = 0; i < nums.length; ++i) {
            int num = nums[i];
            mem[num]++;
            if (mem[num] == 1) {
                k += 1;
            }
            if (mem[num] > maxRepeatNum) {
                maxRepeatNum = mem[num];
                numOfMaxRepeat = 1;
            } else if (mem[num] == maxRepeatNum) {
                numOfMaxRepeat++;
            }
            // 多出来的一个元素跟其他元素相同，如[1, 1, 2, 2, 3, 3, 3] 最后一个3是多余的
            // 只有最大个数出现的次数为1的时候，才需要满足这个公式
            if (numOfMaxRepeat==1 && k*maxRepeatNum - (k-1) == i+1) {
                maxRet = i+1;
            }
            // 多出来的一个元素跟其他元素不同，如[1, 1, 2, 2, 3, 3, 4] 最后一个4是多余的
            if (numOfMaxRepeat*maxRepeatNum+1 == i+1) {
                maxRet = i+1;
            }
            // 特殊处理当maxRepeatNum=1的时候，这种情况一直满足条件
            if (maxRepeatNum == 1) {
                maxRet = i+1;
            }
        }
        return maxRet;
    }
}

// https://leetcode.com/problems/maximum-equal-frequency/discuss/403931/JavaC%2B%2BPython-Easy-to-understand-(compare-counts)
// O(n)/O(n)
public int maxEqualFreq(int[] nums) {
	Map<Integer, Integer> countMap = new HashMap<>();
	Map<Integer, Integer> freqMap = new HashMap<>();
	int res = 0;
	for (int i = 0; i < nums.length; i++) {
		// update counts
		countMap.put(nums[i], countMap.getOrDefault(nums[i], 0) + 1);
		int freq = countMap.get(nums[i]);
		// update counts with that frequency
		freqMap.put(freq, freqMap.getOrDefault(freq, 0) + 1);

		int count = freqMap.get(freq) * freq;
		if (count == i + 1 && i != nums.length - 1) { // case 1
			res = Math.max(res, i + 2);
		} else if (count == i) { // case 2
			res = Math.max(res, i + 1);
		}
	}
	return res;
}