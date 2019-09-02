class Solution {
    public int[] singleNumber(int[] nums) {
        int diff = 0;
        for (int num : nums) diff ^= num;
        diff &= -diff;  // diff &= -diff 得到出 diff 最右侧不为 0 的位
		// int lastBit = (aXorb & (aXorb - 1)) ^ aXorb;  // the last bit that a diffs b
        int[] res = new int[2];
        for (int num : nums) {
            if ((num & diff) == 0) res[0] ^= num;
            else res[1] ^= num;
        }
        return res;
    }
}
0101 d 
1010 1's comp 
1011 2's comp 
0001 last digit

0101 d
0100 d-1
0100 d & d-1
0001 last digit