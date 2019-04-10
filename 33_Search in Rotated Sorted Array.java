//Wrong Method 
import java.util.*;

class Solution {
  public int rotated (int target, int[] nums) {
    if (nums.length == 0) return -1;
    int st = 0, en = nums.length - 1;
    while (st < en) {
      if (nums[st] < nums[en]) {
        int mid = (en - st) / 2 + st;
        if (nums[mid] == target)
          return mid;
        else if (nums[mid] > target) {
          en = mid - 1;
        } else {
          st = mid + 1;
        }
      } else {
         int mid = (en - st) / 2 + st;
        if (nums[mid] < nums[en])
          st = mid + 1;
        if (nums[mid] > nums[st])
          en = mid - 1;                       
      }
    }
    return -1;
  }
}

class Main {  
  static public void main( String args[] ) {
    System.out.println( "Practice makes Perfect!" );
  }
}

rotated sorted array
//[4,5,6,7,0,1,2]
4,5,6,7,0,1,2
//target: 0, index: 4

length : 7
index : 0,  4
  3,  7
  
  
  
//Right
class Solution {
    public int search(int[] nums, int target) {
        if(nums == null || nums.length == 0)return -1;
        int i = 0, j = nums.length - 1;
        while(i <= j) {
            if(i == j) 
                return nums[i] == target? i : -1;
            int mid = i + (j - i) / 2;
            if(nums[mid] == target)return mid;
            if(nums[mid] < nums[j]) {
                if(target < nums[mid] || target > nums[j])j = mid - 1;
                else i = mid + 1;
            }
            else {//nums[mid] > nums[j]
                if(target > nums[mid] || target < nums[i])i = mid + 1;
                else j = mid - 1;
            }
        }
        return -1;
    }
}

//if duplicate, then this solution will lose the target
[1,1,1,3,1]
  
  
