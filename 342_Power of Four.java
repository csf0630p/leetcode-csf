class Solution {
    public boolean isPowerOfFour(int num) {
        return Integer.toString(num, 4).matches("10*");
		
        return num > 0 && (num & (num - 1)) == 0 && (num - 1) % 3 == 0;
		// 4^n - 1 = (2^n + 1) * (2^n - 1), among (2^n-1), (2^n), (2^n+1), one of them must be a multiple of 3, and (2^n) cannot be the one, so (4^n - 1) % 3 == 0
		// only prove ((4-1)^n - 1)%3 ==0 is not enough, you also need to prove that (2^n-1)%3 != 0 when n is odd.
		// 2^n = (3-1)^n = C(n,0)3^n(-1)^0+....+(-1)^n.
		// 1.When 2^n is 4^n, which means n is even, in this case, (-1)^n==1 and (2^n-1）%3==0
		// 2.When 2&n is not 4^n, which means n is odd, in this case, (-1)^n=-1 and (2^n-1）%3==1；
		
		return (num > 0) && ((num & (num - 1)) == 0) && ((num & 0x55555555) == num);
    }
}