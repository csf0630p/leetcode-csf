//Method 1: heap
class Solution {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(k, (a, b) -> (a - b));
        for (Integer it : nums) {
            if (pq.size() == k) {
                if (it > pq.peek()) {
                    pq.poll();
                    pq.add(it);
                }
            }
            else
                pq.offer(it);
        }
        int ans = Integer.MAX_VALUE;
        for (Integer it : pq) 
            if (it < ans)
                ans = it;
        return ans;
    }
}
// T: O(n*log(k)) S: O(n+k)

//Method 1.1
public int findKthLargest(int[] nums, int k) {
    PriorityQueue<Integer> q = new PriorityQueue<>(k);
    for(int i: nums){
        q.offer(i);
        if(q.size() > k)
            q.poll();
    }
    return q.peek();
}

//Method 2: quick select  O(n)~O(n^2)/O(n)
//2.1
class Solution {
    public int findKthLargest(int[] nums, int k) {
        if (k < 1 || nums == null) 
            return 0; 
        return getKth(nums.length - k, nums, 0, nums.length - 1);
    }   
    int partition(int[] nums, int start, int end) {
        int p = nums[start];
        while(start < end){
            while(nums[end] >= p && start < end) 
                end--;
            nums[start] = nums[end];
            while(nums[start] < p && start < end) 
                start++;
            nums[end] = nums[start];
        }
        nums[start] = p;
        return start;
    }
    public int getKth(int k, int[] nums, int start, int end) {                
        int mid = partition(nums, start, end);
        if (k == mid) {
            return nums[k];
        } else if (k < mid) {
            return getKth(k, nums, start, mid - 1);
        } else {
            return getKth(k, nums, mid + 1, end);
        }
    }
}
//2.2
public int findKthLargest(int[] nums, int k) {
	if (k < 1 || nums == null) 
		return 0; 
	return getKth(nums.length - k, nums, 0, nums.length - 1);
}
 
public int getKth(int k, int[] nums, int start, int end) {
	int pivot = nums[end], left = start, right = end;
	while (true) {
		while (nums[left] < pivot && left < right) 
			left++;
		while (nums[right] >= pivot && right > left) 
			right--;
		if (left == right) 
			break;
		swap(nums, left, right);
	}
	swap(nums, left, end);
	if (k == left) {
		return pivot;
	} else if (k < left) {
		return getKth(k, nums, start, left - 1);
	} else {
		return getKth(k, nums, left + 1, end);
	}
}
 
public void swap(int[] nums, int n1, int n2) {
	int tmp = nums[n1];
	nums[n1] = nums[n2];
	nums[n2] = tmp;
}
//2.2 改成start作pivot
public int findKthLargest(int[] nums, int k) {
	if (k < 1 || nums == null) 
		return 0; 
	return getKth(nums.length - k, nums, 0, nums.length - 1);
}

public int getKth(int k, int[] nums, int start, int end) {                
	int pivot = nums[start], left = start + 1, right = end;
	while (left <= right) {
		while (left <= right && nums[left] <= pivot) 
			left++;
		while (right >= left && nums[right] > pivot) 
			right--;
		if (left < right) 
			swap(nums, left, right);
	}
	swap(nums, right, start);
	if (k == right) {
		return pivot;
	} else if (k < right) {
		return getKth(k, nums, start, right - 1);
	} else {
		return getKth(k, nums, right + 1, end);
	}
}

public void swap(int[] nums, int n1, int n2) {
	int tmp = nums[n1];
	nums[n1] = nums[n2];
	nums[n2] = tmp;
}

//2.3 顺序遍历法
class Solution {
    public int findKthLargest(int[] nums, int k) {
        if (k < 1 || nums == null) 
            return 0; 
        return getKth(nums.length - k, nums, 0, nums.length - 1);
    }
    
    int partition(int[] nums, int start, int end) {
        int pivot = nums[end];
        int storeIndex = start;
        for(int i = start; i < end; i++){
            if(nums[i] < pivot){
                swap(nums, storeIndex, i);
                storeIndex++;
            }
        }
        swap(nums, storeIndex, end);
        return storeIndex;        
    }

    public int getKth(int k, int[] nums, int start, int end) {                
        int mid = partition(nums, start, end);
        if (k == mid) {
            return nums[k];
        } else if (k < mid) {
            return getKth(k, nums, start, mid - 1);
        } else {
            return getKth(k, nums, mid + 1, end);
        }
    }

    public void swap(int[] nums, int n1, int n2) {
        int tmp = nums[n1];
        nums[n1] = nums[n2];
        nums[n2] = tmp;
    }
}