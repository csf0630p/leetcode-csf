/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
class Solution {
    public List<Interval> merge(List<Interval> intervals) {
        // List<Interval> res = new ArrayList<>(); 
        Stack<Interval> res = new Stack<>();
        if (intervals.size() == 0)
            return res;
        intervals.sort((a, b) -> (a.start - b.start));
        res.add(intervals.get(0));
        for (int i = 1; i < intervals.size(); i++) {
            int st = intervals.get(i).start, ed = intervals.get(i).end;
            // if (res.get(res.size() - 1).end >= st) {
            if (res.peek().end >= st) {                
                // Interval rp = new Interval(res.peek().start, Math.max(res.peek().end, ed));     
                // res.pop(); res.push(rp);
                res.peek().end = Math.max(res.peek().end, ed);
            } else {
                res.push(intervals.get(i));
            }
        }
            // System.out.print(id.start + " " + id.end + "; ");
        return new ArrayList<Interval>(res);
    }
}

class Solution {
    public List<Interval> merge(List<Interval> intervals) {
        Stack<Interval> res = new Stack<>();
        if (intervals.size() == 0)
            return res;
        intervals.sort((a, b) -> (a.start - b.start));
        res.add(intervals.get(0));
        for (int i = 1; i < intervals.size(); i++) {
            int st = intervals.get(i).start, ed = intervals.get(i).end;
            if (res.peek().end >= st) {                
				res.peek().end = Math.max(res.peek().end, ed);
            } else {
                res.push(intervals.get(i));
            }
        }
        return new ArrayList<Interval>(res);
    }
}