123    963    741
456 -> 852 -> 852
789    741    963
class Solution {
    public void rotate(int[][] a) {
        int n = a.length;
		//Transpose 转置
        for (int st = n - 1; st >= 0; st--) {
            int i = st, j = 0, i2 = n - 1, j2 = n - i - 1;            
            while (i2 > i) {
                int temp = a[i][j];
                a[i][j] = a[i2][j2];
                a[i2][j2] = temp;
                i++; j++; i2--; j2--;
            }
        }
        for (int st = 1; st < n; st++) {
            int i = 0, j = st, i2 = n - j - 1, j2 = n - 1;            
            while (i2 > i) {
                int temp = a[i][j];
                a[i][j] = a[i2][j2];
                a[i2][j2] = temp;
                i++; j++; i2--; j2--;
            }
        }
		//Reverse
        for (int i = 0; i < n / 2; i++) {
            int[] temp = a[i];
            a[i] = a[n - i - 1];
            a[n - i - 1] = temp;                
        }		
        // for (int i = 0; i < n / 2; i++) {
        //     for (int j = 0; j < n; j++) {
        //         int temp = a[i][j];
        //         a[i][j] = a[n - i - 1][j];
        //         a[n - i - 1][j] = temp;                
        //     }
        // }		
    }
}

123    147    741
456 -> 258 -> 852
789    369    963
先转置再交换列，但交换列对java不友好

123    789    741
456 -> 456 -> 852
789    123    963
先交换行再转置
class Solution {
    public void rotate(int[][] matrix) {
        if(matrix.length<=1) return;
        int n = matrix.length;

        for(int i=0;i<n/2;i++){
            int[] temp = matrix[i];
            matrix[i] = matrix[n-1-i];
            matrix[n-1-i] = temp;
        }
       
        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                int temp =  matrix[j][i];
                matrix[j][i] = matrix[i][j];
                matrix[i][j] = temp;
            }
        }
    }
}

//https://leetcode.com/problems/rotate-image/discuss/18895/Clear-Java-solution
直接旋转
public void rotate(int[][] matrix) {
    int n=matrix.length;
    for (int i=0; i<n/2; i++) 
        for (int j=i; j<n-i-1; j++) {
            int tmp=matrix[i][j];
            matrix[i][j]=matrix[n-j-1][i];
            matrix[n-j-1][i]=matrix[n-i-1][n-j-1];
            matrix[n-i-1][n-j-1]=matrix[j][n-i-1];
            matrix[j][n-i-1]=tmp;
        }
    }