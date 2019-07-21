// dfs
// https://leetcode.com/problems/expression-add-operators/discuss/71895/Java-Standard-Backtrace-AC-Solutoin-short-and-clear
/*
	overflow: we use a long type once it is larger than Integer.MAX_VALUE or minimum, we get over it.
	0 sequence: because we can't have numbers with multiple digits started with zero, we have to deal with it too.
	a little trick is that we should save the value that is to be multiplied in the next recursion. eval - multed + multed * cur
*/ 
//  using StringBuilder to add String, avoid substring(), use char[] num instead of String num

public class Solution {
    public List<String> addOperators(String num, int target) {
        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        dfs(res, sb, num.toCharArray(), 0, target, 0, 0);
        return res;
    }
    /**
 * @param res: result list to store valid expressions
 * @param sb: current expression string 
 * @param num: input num candidates
 * @param pos: current index in the input num array 
 * @param target: input target number
 * @param prev: current calculation value
 * @param multi: previous number, in order to multiply current number if we want to put * between preNum and curNum
 * */
    public void dfs(List<String> res, StringBuilder sb, char[] num, int pos, int target, long prev, long multi) { 
        if(pos == num.length) {
            if(target == prev) res.add(sb.toString());
            return;
        }
        long curr = 0;
        for(int i = pos; i < num.length; i++) {
        // corner case: if current position is 0, we can only use it as a single digit number, should be 0
        // if it is not a single digit number with leading 0, it should be considered as an invalid number             
            if(num[pos] == '0' && i != pos) break;
            curr = 10 * curr + num[i] - '0';
            int len = sb.length();
            if(pos == 0) {
                dfs(res, sb.append(curr), num, i + 1, target, curr, curr); 
                sb.setLength(len);
            } else {
                dfs(res, sb.append("+").append(curr), num, i + 1, target, prev + curr, curr); 
                sb.setLength(len);
                dfs(res, sb.append("-").append(curr), num, i + 1, target, prev - curr, -curr); 
                sb.setLength(len);
                dfs(res, sb.append("*").append(curr), num, i + 1, target, prev - multi + multi * curr, multi * curr); 
                sb.setLength(len);
            // trick part: to calculate multiplication, we should subtract previous number, and then add current
            // multiplication result to the subtraction result                 
            }
        }
    }
}