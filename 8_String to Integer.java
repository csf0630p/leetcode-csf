String to Integer 
The function first discards as many whitespace characters as necessary until the first non-whitespace character is found. Then, starting from this character, takes an optional initial plus or minus sign followed by as many numerical digits as possible, and interprets them as a numerical value.

The string can contain additional characters after those that form the integral number, which are ignored and have no effect on the behavior of this function.

If the first sequence of non-whitespace characters in str is not a valid integral number, or if no such sequence exists because either str is empty or it contains only whitespace characters, no conversion is performed.

If no valid conversion could be performed, a zero value is returned.
  
Input: "   -42"
Output: -42
Explanation: The first non-whitespace character is '-', which is the minus sign.
             Then take as many numerical digits as possible, which gets 4
  
" -4 2": -4
" +4" : 

“ aiw 430”
"0"

class solution {
  public int stoi (String in){
    long ans = 0;
    boolean isP = false;
    boolean firstSpace = true;
    boolean firstSign = true;
    boolean haveAns = false;
    in += ".";
    for (int i = 0; i < in.length(); i++) {
      if ((firstSpace) && (in.charAt(i) == ' '))
        continue;
      else
        firstSpace = false;
      
      if (firstSign && (in.charAt(i) == '-' || in.charAt(i) == '+')) {
        firstSign = false;
        if (in.charAt(i) == '-')
          isP = false;
        else 
          isP = true;     
      } else if (in.charAt(i) >= '0' && in.charAt(i) <= '9') {
        if (!haveAns)
          haveAns = true;
        if (ans > Integer.MAX_VALUE)
          ans = Integer.MAX_VALUE;
        else {
          ans *= 10;
          ans += in.charAt(i) - '0';
        }
      } else {
        if (!haveAns)
          return 0;
        else
          return (isP) ? (int)ans : (int)-ans;
      }
    }
    
  }
}
   -42.
42
/////////
class Solution {
    public int myAtoi(String in) {
        long ans = 0;
        boolean isP = true;
        boolean firstSpace = true;
        boolean firstSign = true;
        boolean haveAns = false;
        in = in.trim();
        in += ".";
        for (int i = 0; i < in.length(); i++) {
          if (firstSign && (in.charAt(i) == '-' || in.charAt(i) == '+')) {
            firstSign = false;
            isP = (in.charAt(i) == '-') ? false : true;
          } else if (in.charAt(i) >= '0' && in.charAt(i) <= '9') {
            firstSign = false;
            haveAns = true;
            ans *= 10;
            ans += in.charAt(i) - '0';
            if (ans > Integer.MAX_VALUE) 
                return (isP) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
          } else {
            if (!haveAns)
              return 0;
            else {
                if (ans > Integer.MAX_VALUE) 
                    return (isP) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
                else 
                	return (isP) ? (int)ans : (int)-ans;
            }             
          }
        }  
        return 0;
    }
}
////////
package ls;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Temp {
    public static int myAtoi(String in) {
        long ans = 0;
        boolean isP = true;
        boolean firstSpace = true;
        boolean firstSign = true;
        boolean haveAns = false;
        in = in.trim();
        in += ".";
        for (int i = 0; i < in.length(); i++) {
          // if ((firstSpace) && (in.charAt(i) == ' '))
          //   continue;
          // else
          //   firstSpace = false;

          if (firstSign && (in.charAt(i) == '-' || in.charAt(i) == '+')) {
            firstSign = false;
            if (in.charAt(i) == '-')
              isP = false;
            else 
              isP = true;     
          } else if (in.charAt(i) >= '0' && in.charAt(i) <= '9') {
            firstSign = false;
            haveAns = true;
            if (ans > Integer.MAX_VALUE) {
                return (isP) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
                //   return Integer.MAX_VALUE;
                // else
                //   return Integer.MIN_VALUE;
            }
            else {
              ans *= 10;
              ans += in.charAt(i) - '0';
            }
          } else {
            if (!haveAns)
              return 0;
            else {
                if (ans > Integer.MAX_VALUE) {
                    return (isP) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
                    // if (isP)
                    //   return Integer.MAX_VALUE;
                    // else
                    //   return Integer.MIN_VALUE;
                } else 
                	return (isP) ? (int)ans : (int)-ans;
            }             
          }
        }  
        return 0;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            String str = line;
            
            int ret = myAtoi(str);
            
            String out = String.valueOf(ret);
            
            System.out.print(out);
        }
    }
    
//    public static String stringToString(String input) {
//        return JsonArray.readFrom("[" + input + "]").get(0).asString();
//    }
}

//class Solution {
//
//}
//
//public class MainClass {
//
//    
//
//}
