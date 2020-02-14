// 1.0 binary search, O(log(mn)) = O(log m + log n), O(1)
class Solution {
  public boolean searchMatrix(int[][] matrix, int target) {
    int m = matrix.length;
    if (m == 0) return false;
    int n = matrix[0].length;

    // binary search
    int left = 0, right = m * n - 1;
    int pivotIdx, pivotElement;
    while (left <= right) {
      pivotIdx = (left + right) / 2;
      pivotElement = matrix[pivotIdx / n][pivotIdx % n];
      if (target == pivotElement) return true;
      else {
        if (target < pivotElement) right = pivotIdx - 1;
        else left = pivotIdx + 1;
      }
    }
    return false;
  }
}

// 1.1 O(log m + log n)/O(1)
class Solution {
	public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix.length == 0 || matrix[0].length == 0)  
            return false;
        
    	int heigth = matrix.length;
    	int width = matrix[0].length;
    	
    	if(matrix[0][0] > target || matrix[heigth-1][width-1] < target) 
    	    return false;
    
    	int head = 0, tail = heigth-1, mid;
    	
    	while(head != tail && matrix[tail][0] > target){
    		mid = (head+tail+1)/2;
    		
    		if(matrix[mid][0] < target)		
    		    head = mid;
    		else if(matrix[mid][0] > target)	
    		    tail = mid-1;	
    		else 	
    		    return true;
    	}
    	
    	int row = tail;
    	head = 0; 
    	tail = width-1;
    	
    	while(head <= tail) {
    		mid = (head+tail)/2;
    		if(matrix[row][mid] < target)
    			head = mid + 1;
    		else if(matrix[row][mid] > target)
    			tail = mid -1;
    		else 
    		    return true;
    	}
    	return false;
    }
}
