Meeting Rooms II
 
 Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.
   
   Example 1:

Input: [[0, 30],[5, 10],[15, 20]]
Output: 2
  
  [0,5,15]
  [30,10,20]
  
  [0,5,15]
  [10,20,30]
  
  5 10 15 20 
  Solution{
    public int getMinNum(Interval[] intervals){
      int result=0;
      int[] starts=new int[intervals.length];
      int[] ends=new int[intervals.length];
      for(int i=0;i<intervals.length;i++){
        starts[i]=intervals[i].start;
        ends[i]=intervals[i].end;
      }
      Arrays.sort(starts);
      Arrays.sort(ends);
      for(int i=1;i<intervals.length;i++){
        if(starts[i]<ends[i-1])
          result++;
      }
      return result;
    }
    class Interval{
      int start;
      int end;
      public Interval(int start,int end){
        this.start=start;
        this.end=end;
      }
    }
}
  
  