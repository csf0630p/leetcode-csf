a = "11", b = "1" "100"
a = "1010", b = "1011" "10101"
  
  a = 1, b = 111
  1, 3
  001, 111,
  0001
  1000
class Solution {
  public addBinary (String a, String b) {
    char[] c = a.toCharArray();
    char[] d = b.toCharArray();
    StringBuilder ans = new StringBuilder();
    if (d.length < c.length) {
      char[] temp = c;
      c = d;
      d = temp;
    }
    else if (c.length < d.length) {
      char[] temp = new char[d.length];
      for (int i = 0; i < d.length - c.length; i++) {
        temp[i] = '0';
      }
      for (int i = 0; i < c.length; i++) {
        temp[d.length - c.length + i] = c[i];
      }
    }
    
    int temp = 0;
    for (int i = d.length - 1; i >= 0; i-- ) {
      int sum = c[i] - '0' + d[i] - '0' + temp;
      if (sum >= 2) {
        temp = 1;
        sum -= 2;
      }
      ans.append(sum);
    }
    if (temp != 0) {
      ans.append(temp);
    }
    ans.reverse();
    return ans;
  }
}
=============================================
x,y vertical scale (0,0), (0,1), (2,7), (5,1)......n points here, return K points nearest to the original point(0,0)
    (0,0), (0,1)
  
class Solution {
}
//we will use .....

class Solution {
    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int sum = 0;
        int i = a.length() - 1;
        int j = b.length() - 1;
        while(i >= 0 || j >= 0) {
            if(i >= 0)sum += a.charAt(i--) - '0';
            if(j >= 0)sum += b.charAt(j--) - '0';
            sb.append(sum % 2);
            sum = sum/2;
        }
        if(sum == 1)sb.append(sum);
        return sb.reverse().toString();
    }
}