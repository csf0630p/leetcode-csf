class Solution {
    public static int findNthDigit(int n) {
        if (n <= 0)
            return 0;  
        int sum = 0, lastSum = 0, i = 0;
        while (sum < n) {
            lastSum = sum;
            sum += 9 * Math.pow(10, i) * (i + 1);
            i++;
        }
        int x = (n - lastSum) / i;
        int y = (n - lastSum) % i;
        if (y == 0) {
            x--;
            y = i;
        }
        int ls = (int) Math.pow(10, i - 1);
        int xx = (int) (x + ls);
        return (int)String.valueOf(xx).charAt(y - 1) - '0'; 
    }
}

9*1 = 9
(99-10+1)*2 = 180, +9 = 189
(999-100+1)*3

12345678910111213

00000000010101010

0
10
120

17, 7, 3*2 +1

x * i + () == n - lastSum
x == xx - 10^(i-1)

0 == 10 - 10^1
0 * 2 + (2) = 11 - 9

(n - lastSum) / i = x...()

3 * 2 + 2 == 17 - 9
3 = 13 - 10