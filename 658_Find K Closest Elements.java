//method 1: O(nlogn)/O(k) without res
class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> res = new ArrayList<>();
        for (int id : arr)
            res.add(id);
        res.sort((a, b) -> (Math.abs(a - x) - Math.abs(b - x)));
         res = res.subList(0, k);
         res.sort((a, b) -> (a - b));
         return res;        
    }
}

//method 2: O(logn + k)/O(k) without res
class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> res = new ArrayList<>();
        for (int id : arr)
            res.add(id);
		int n = res.size();
		if (x <= res.get(0)) {
			return res.subList(0, k);
		} else if (res.get(n - 1) <= x) {
			return res.subList(n - k, n);
		} else {
            int low = 0, high = arr.length - 1, index = -1;
            while(low <= high){
                int mid = low + (high - low) / 2;
                if(arr[mid] > x){
                    high = mid - 1;
                }
                else if(arr[mid] < x){
                    low = mid + 1;
                }
                else{
                    index = mid;
                    break;
                }
            }
            int diffLow = Math.abs(arr[low] - x);
            int diffHigh = Math.abs(arr[high] - x);
            if (index == -1)
                index = (diffLow <= diffHigh) ? low : high;
			low = Math.max(0, index - k - 1);
            high = Math.min(res.size() - 1, index + k - 1);
			while (high - low > k - 1) {
				if (low < 0 || (x - arr[low]) <= (arr[high] - x))
					high--;
				else if (high > res.size() - 1 || (x - arr[low]) > (arr[high] - x))
					low++;
				// else
				// 	System.out.println("unhandled case: " + low + " " + high);
			}
			return res.subList(low, high + 1);
		}              
    }
}

//method 3: