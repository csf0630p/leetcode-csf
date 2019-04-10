// https://www.cnblogs.com/logosG/p/7979690.html

// Solution 1: O(n2), 额外空间O(1)
class Solution {
    public int trap(int[] height) {
        int l = height.length;    
        int res = 0;
        for (int i = 0; i < l; i++) {
            int lMax = height[i];
            for (int j = 0; j < i; j++)
                if (lMax < height[j])
                    lMax = height[j];
            int rMax = height[i];
            for (int j = i + 1; j < l; j++)
                if (rMax < height[j])
                    rMax = height[j];    
            res += Math.min(lMax, rMax) - height[i];
        }
        return res;
    }
}

// Solution 2: DP, O(n), 额外空间O(n)
class Solution {
    public int trap(int[] height) {
        int l = height.length;    
        if (l < 3)
            return 0;
        int res = 0;
        
        int[] leftMax = new int[l];
        leftMax[0] = height[0];
        for (int i = 1; i < l; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }
        
        int[] rightMax = new int[l];
        rightMax[l - 1] = height[l - 1];
        for (int i = l - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }                
        
        for (int i = 1; i < l - 1; i++) {
            int ls = Math.min(leftMax[i - 1], rightMax[i + 1]) - height[i];
            res += (ls > 0) ? ls : 0;
        }
        
//         for (int i = 0; i < l; i++) {
//             System.out.println(leftMax[i]+" "+rightMax[i]+" "+height[i]);
//         }        
        return res;
    }
}

//Solution 3：双指针法，在数组首尾分别创建一个指针，两指针相见时结束循环
class Solution {
    public int trap(int[] height) {
        int l = 0, r = height.length - 1;    
        if (height.length < 3)
            return 0;
        int res = 0;
        while (l < r) {
            int min = Math.min(height[l], height[r]);
            if (height[l] <= height[r]) {
                for (l++; l < r && height[l] <= min; l++) {
                    res += min - height[l];
                }
            } else {
                for (r--; l < r && min >= height[r]; r--) {
                    res += min - height[r];
                }                
            }
        }     
        return res;
    }
}

//Solution 4：一层一层的求存水数 O(n*maxHeight)
class Solution {
    class Rec {
        int min = Integer.MAX_VALUE;
        int max = 0;
        int count = 0;
    }
    public int trap(int[] height) {
        int l = height.length;
        int max = 0;
        for (Integer id : height) {
            if (id > max)
                max = id;
        }
        Rec[] rec = new Rec[max + 1];
        for (int i = 0; i <= max; i++) {
            rec[i] = new Rec();
        }
        for (int i = 0; i < l; i++) {
            for (int j = 0; j <= height[i]; j++) { 
                rec[j].count++;
                if (i < rec[j].min)
                    rec[j].min = i;
                if (i > rec[j].max)
                    rec[j].max = i;                
            }
        }
        int res = 0;
        for (int i = 1; i <= max; i++) {
            res += rec[i - 1].max - rec[i - 1].min + 1 - rec[i].count - 
                (rec[i].min - rec[i - 1].min +
                rec[i - 1].max - rec[i].max);
        }
        return res;
    }
}

//Soluton 5：维护一个非递增栈
class Solution {
    public int trap(int[] height) {
        Stack<Integer> s = new Stack<Integer>();
        int i = 0, n = height.length, res = 0;
        while (i < n) {
            if (s.isEmpty() || height[i] <= height[s.peek()]) {
                s.push(i++);
            } else {
                int t = s.pop();
                if (s.isEmpty()) continue;
                res += (Math.min(height[i], height[s.peek()]) - height[t]) * (i - s.peek() - 1);
            }
        }
        return res;
    }
}