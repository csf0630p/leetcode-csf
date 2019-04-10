class Solution {
    public int[] searchRange(int[] nums, int target) {
        int[] result = new int[]{-1, -1};
        if (nums.length == 0)
            return result;
        int fit = bsearch5(nums, 0, nums.length - 1, target);
        if ((fit == -1) || (nums[fit] != target))
            return result;
        int lo = bsearch5(nums, 0, nums.length - 1, target - 1);
        while ((lo < fit) && (nums[lo] < target)) 
            lo++;        
        int hi = bsearch5(nums, 0, nums.length - 1, target + 1) - 1;
        if (hi < 0)
            hi = nums.length - 1;
        while ((hi > fit) && (nums[hi] > target)) 
            hi--;     
        result[0] = lo; result[1] = hi;
        return result;
    }
    public int bsearch5(int[] array, int low,int high,int target){
        if(low > high || target > array[high]){
            return -1;
        }
        while(low < high){
            int mid = low + (high -low)/2;
            if(array[mid] >= target){
                high = mid;
            }
            else{
                low = mid + 1;
            }
        }
        return high;
    }
}

method 2:
// bsearch5 method makes sure that return the first element >= target
class Solution {
    public int[] searchRange(int[] nums, int target) {
        int[] result = new int[]{-1, -1};
        if (nums.length == 0)
            return result;
        int fit = bsearch5(nums, 0, nums.length - 1, target);
        if ((fit == -1) || (nums[fit] != target))
            return result;
        // int lo = bsearch5(nums, 0, nums.length - 1, target - 1);
        // while ((lo < fit) && (nums[lo] < target)) 
        //     lo++; 
        int lo = fit;
        int hi = bsearch5(nums, 0, nums.length - 1, target + 1) - 1;
        if (hi < 0)
            hi = nums.length - 1;
        // while ((hi > fit) && (nums[hi] > target)) 
        //     hi--;     
        result[0] = lo; result[1] = hi;
        return result;
    }
    public int bsearch5(int[] array, int low,int high,int target){
        if(low > high || target > array[high]){
            return -1;
        }
        while(low < high){
            int mid = low + (high -low)/2;
            if(array[mid] >= target){
                high = mid;
            }
            else{
                low = mid + 1;
            }
        }
        return high;
    }
}


// with debug log
class Solution {
    public int[] searchRange(int[] nums, int target) {
        int[] result = new int[]{-1, -1};
        if (nums.length == 0)
            return result;
        int fit = bsearch5(nums, 0, nums.length - 1, target);
        if ((fit == -1) || (nums[fit] != target))
            return result;
        // int lo = bsearch5(nums, 0, nums.length - 1, target - 1);
        // while ((lo < fit) && (nums[lo] < target)) 
        //     lo++; 
        int lo = fit;
        int hi = bsearch5(nums, 0, nums.length - 1, target + 1) - 1;
        if (hi < 0)
            hi = nums.length - 1;
        // while ((hi > fit) && (nums[hi] > target)) 
        //     hi--;     
        result[0] = lo; result[1] = hi;
        return result;
    }
    public int bsearch5(int[] array, int low,int high,int target){
        if(low > high || target > array[high]){
            System.out.printf("err: %d %d \n", low, high);
            return -1;
        }
        while(low < high){
            int mid = low + (high -low)/2;
            System.out.printf("   : %d %d %d\n", low, high, mid);              
            if(array[mid] >= target){
                high = mid;
            }
            else{
                low = mid + 1;
            }
        }
        System.out.printf("end: %d %d \n", low, high);        
        return high;
    }
}
Your input
[6,7,7,7,7,8,8,8,8,9,9,9,9,10]
8
stdout
   : 0 13 6
   : 0 6 3
   : 4 6 5
   : 4 5 4
end: 5 5 
   : 0 13 6
   : 7 13 10
   : 7 10 8
   : 9 10 9
end: 9 9 
Output
[5,8]
Expected
[5,8]