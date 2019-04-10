import java.util.*;
class Main {
  static Set<String> res1;
  static Set<String> res2;

  public static void main(String[] args) {
    String s1 = "aabbccdd";
    // String s2 = "";
    String s2 = "abbccdd";
    res1 = new TreeSet<>();
    brute(s1, s2);
    System.out.println(res1);
    System.out.println("==============");
    res2 = new TreeSet<>();
    // int[][][] memo = new int[s1.length()][s2.length()][s1.length() + 1];
    int[][] memo = new int[s1.length()][s2.length()];
    for (int k = 1; k <= s1.length(); k++) {
      for (int i = 0; i < s1.length() - k + 1; i++) {
        for (int j = 0; j < s2.length() - k + 1; j++) {
          if (j + k > s2.length())
            continue;
          if (s1.charAt(i + k - 1) == s2.charAt(j + k - 1)) {
            // memo[i][j][k] = memo[i][j][k - 1];
          } else {
            // memo[i][j][k] = memo[i][j][k - 1] + 1;
            memo[i][j] += 1;            
          }
          // if (memo[i][j][k] == 1)
          if (memo[i][j] == 1)
            res2.add(s1.substring(i, i + k) + " " + s2.substring(j, j + k));					
        }
      }
    }
    System.out.println(res2);    
  }

  static void brute(String s1, String s2) {
    for (int i = 0; i < s1.length(); i++) {
      for (int j = i + 1; j <= s1.length(); j++) {
        String t1 = s1.substring(i, j);
        for (int ii = 0; ii < s2.length() - t1.length() + 1; ii++) {
          int jj = ii + t1.length();
          String t2 = s2.substring(ii, jj);
          // System.out.println(t1 + " " + t2);
          int flag = 0;
          for (int k = 0; k < t1.length(); k++)
            if (t1.charAt(k) != t2.charAt(k))
              flag++;
          if (flag == 1)
            res1.add(t1 + " " + t2);
        }
      }
    }
  }
}