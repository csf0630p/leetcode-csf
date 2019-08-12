// 1.0 greedy. O(n+m)/O(1)
class Solution {
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g); Arrays.sort(s);
        int i = 0, j = 0, res = 0;
        while (i < g.length && j < s.length) {
            if (s[j] >= g[i]) {
                res++; i++; j++;
            } else {
                j++;
            }
        }
        return res;
    }
}

// 1.1 more efficiency
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g); Arrays.sort(s);
        int i = 0, j = 0;
        while (i < g.length && j < s.length) {
            if (s[j] >= g[i]) i++;        
            j++;            
        }
        return i;
    }