class Solution {
public:
    bool canWinNim(int n) {
        return ((n % 4 == 0) ? false : true);
    }
};

//真1假0。f[n]=min{f[n-1],f[n-2],f[n-3])},n>0,f[0]=0; 结论是被4整除时输