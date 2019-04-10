class Solution {
    public int min3(int a, int b, int c)
    {
        int temp = (a < b) ? a : b;
        return (temp < c) ? temp : c;
    }
    public int minimumDeleteSum(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int[][] f = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++)
        {
            for (int j = 0; j <= n; j++)
            {
                if ((i == 0) && (j == 0))
                {
                    f[i][j] = 0;
                }
                else if (i == 0)
                {
                    f[i][j] = f[i][j - 1] + s2.charAt(j - 1);
                }
                else if (j == 0)
                {
                    f[i][j] = f[i - 1][j] + s1.charAt(i - 1);
                }
                else
                {
                    int temp = 0;
                    if (s1.charAt(i - 1) != s2.charAt(j - 1))
                    {
                        // temp = s1.charAt(i - 1) + s2.charAt(j - 1);
                        temp = 1000000;
                    }
                    f[i][j] = min3(f[i][j - 1] + s2.charAt(j - 1),
                                   f[i - 1][j] + s1.charAt(i - 1),
                                   f[i - 1][j - 1] + temp);
                }
            }
        }
        
        return f[m][n];
    }
}


class Solution_2 {
    public int min2(int a, int b)
    {
        return (a < b) ? a : b;
    }
    public int minimumDeleteSum(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int[][] f = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++)
        {
            for (int j = 0; j <= n; j++)
            {
                if ((i == 0) && (j == 0))
                {
                    f[i][j] = 0;
                }
                else if (i == 0)
                {
                    f[i][j] = f[i][j - 1] + s2.charAt(j - 1);
                }
                else if (j == 0)
                {
                    f[i][j] = f[i - 1][j] + s1.charAt(i - 1);
                }
                else
                {
                    int temp = 0;                
                    if (s1.charAt(i - 1) == s2.charAt(j - 1))
                    {
                        f[i][j] = f[i - 1][j - 1];
                    }
                    else 
					{
						f[i][j] = min2(f[i][j - 1] + s2.charAt(j - 1),
                                       f[i - 1][j] + s1.charAt(i - 1));
					}
                }
            }
        }
        
        return f[m][n];
    }
}