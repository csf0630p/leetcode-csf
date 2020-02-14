// 1.0 two pointers, O(r+c)/O(1)+O(r*c)
//Amazon OA Edition
class Solution {
    private class PairInt{
        int first;
        int second;
        PairInt(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }
    
    public boolean searchMatrix(int[][] matrix, int target) {
        List<List<Integer>> matrixList = Arrays.stream(matrix)     // Stream<int[]>
            .map(Arrays::stream)                               // Stream<IntStream>
            .map(IntStream::boxed)                             // Stream<Stream<Integer>>
            .map(values -> values.collect(Collectors.toList())) // Stream<List<Integer>>
            .collect(Collectors.toList());                      // List<List<Integer>>
        PairInt ans = locationOfTargetValue(matrix.length, (matrix.length > 0) ? matrix[0].length : 0, matrixList, target);
        return !(ans.first == -1 && ans.second == -1);
    }
    
	PairInt locationOfTargetValue(int rowCount, int columnCount,
								  List<List<Integer>> matrix, int targetValue)
	{
		PairInt res = new PairInt(-1, -1);
        if(matrix == null || matrix.size() == 0 || matrix.get(0).size() == 0) return res;		
        // if(matrix == null || rowCount == 0 || columnCount == 0) return res;	
        int row = 0, col = columnCount - 1;
        while(col >= 0 && row < rowCount) {
            if (matrix.get(row).get(col) == targetValue) {
                res = new PairInt(row, col);
				break;
            } else if (matrix.get(row).get(col) < targetValue) {
				row++;
			} else { // if (matrix.get(row).get(col) > targetValue)
                col--;
            }
        }
        return res;
    }    
}

// 2.0 binary search 
// T: O(log(n!)) < O(nlogn)   S: O(1)
// https://leetcode.com/problems/search-a-2d-matrix-ii/solution/
class Solution {
    private boolean binarySearch(int[][] matrix, int target, int start, boolean vertical) {
        int lo = start;
        int hi = vertical ? matrix[0].length-1 : matrix.length-1;

        while (hi >= lo) {
            int mid = (lo + hi)/2;
            if (vertical) { // searching a column
                if (matrix[start][mid] < target) {
                    lo = mid + 1;
                } else if (matrix[start][mid] > target) {
                    hi = mid - 1;
                } else {
                    return true;
                }
            } else { // searching a row
                if (matrix[mid][start] < target) {
                    lo = mid + 1;
                } else if (matrix[mid][start] > target) {
                    hi = mid - 1;
                } else {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        // an empty matrix obviously does not contain `target`
        if (matrix == null || matrix.length == 0) {
            return false;
        }

        // iterate over matrix diagonals
        int shorterDim = Math.min(matrix.length, matrix[0].length);
        for (int i = 0; i < shorterDim; i++) {
            boolean verticalFound = binarySearch(matrix, target, i, true);
            boolean horizontalFound = binarySearch(matrix, target, i, false);
            if (verticalFound || horizontalFound) {
                return true;
            }
        }
        
        return false; 
    }
}

// 2.1 O(nlogn)/O(1)
// https://blog.csdn.net/Gongzq5/article/details/82694981
class Solution {
public:
    bool searchMatrix(vector<vector<int>>& matrix, int target) {
        int m = matrix.size();
        if (m <= 0) return false;
        int n = matrix[0].size();
        if (n <= 0) return false;
        for (int i = 0; i < n; i++) {
            if (matrix[0][i] <= target && matrix[m-1][i] >= target) {
                int front = 0, end = m-1;
                while (front < end) {
                    int mid = (front + end) / 2;
                    if (matrix[mid][i] > target) end = mid;
                    else if (matrix[mid][i] < target) front = mid + 1;
                    else front = end = mid;
                }
                if (front == end && matrix[front][i] == target) return true; 
            }
            else if (matrix[0][i] > target) return false;
        }
        return false;
    }
};

// 2.2 O(k(logn+logm))O(k(logn+logm)) / O(1)
// https://www.acwing.com/solution/LeetCode/content/290/

// 3.0 divide and conquer
// T: T(mn) = 3T(m/2 x n/2) + O(1) = 3T(mn/4)+O(1), by the Master theorem, T(n)=Θ(n^logb(a)), so T(mn) = O(mn^log(4, 3))
// https://leetcode.com/problems/search-a-2d-matrix-ii/discuss/66147/*Java*-an-easy-to-understand-divide-and-conquer-method
// https://blog.csdn.net/Gongzq5/article/details/82694981
class Solution {
     public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        if(m<1) return false;
        int n = matrix[0].length;

        return searchMatrix(matrix, new int[]{0,0}, new int[]{m-1, n-1}, target);
    }

    private boolean searchMatrix(int[][] matrix, int[] upperLeft, int[] lowerRight, int target) {
        if(upperLeft[0]>lowerRight[0] || upperLeft[1]>lowerRight[1]
                || lowerRight[0]>=matrix.length || lowerRight[1]>=matrix[0].length) 
            return false;
        if(lowerRight[0] == upperLeft[0] && lowerRight[1] == upperLeft[1])
            return matrix[upperLeft[0]][upperLeft[1]] == target;
        int rowMid = (upperLeft[0] + lowerRight[0]) / 2;
        int colMid = (upperLeft[1] + lowerRight[1]) / 2;
        int diff = matrix[rowMid][colMid] - target;
        if(diff > 0) {
            return searchMatrix(matrix, upperLeft, new int[]{rowMid, colMid}, target)
                    || searchMatrix(matrix, new int[]{upperLeft[0],colMid+1}, new int[]{rowMid, lowerRight[1]}, target)
                    || searchMatrix(matrix, new int[]{rowMid+1,upperLeft[1]}, new int[]{lowerRight[0], colMid}, target);
        }
        else if(diff < 0) {
            return searchMatrix(matrix, new int[]{upperLeft[0], colMid+1}, new int[]{rowMid, lowerRight[1]}, target)
                    || searchMatrix(matrix, new int[]{rowMid+1, upperLeft[1]}, new int[]{lowerRight[0], colMid}, target)
                    || searchMatrix(matrix, new int[]{rowMid+1, colMid+1}, lowerRight, target);
        }
        else return true;
    }  
}

// 3.1 divide and conquer
// https://leetcode.com/problems/search-a-2d-matrix-ii/solution/
// T(mn) = 2T(m/2 x n/2) + O(n) = O(nlogn)
// S : o(logn)
class Solution {
    private int[][] matrix;
    private int target;

    private boolean searchRec(int left, int up, int right, int down) {
        // this submatrix has no height or no width.
        if (left > right || up > down) {
            return false;
        // `target` is already larger than the largest element or smaller
        // than the smallest element in this submatrix.
        } else if (target < matrix[up][left] || target > matrix[down][right]) {
            return false;
        }

        int mid = left + (right-left)/2;

        // Locate `row` such that matrix[row-1][mid] < target < matrix[row][mid]
        int row = up;
        while (row <= down && matrix[row][mid] <= target) {
            if (matrix[row][mid] == target) {
                return true;
            }
            row++;
        }

        return searchRec(left, row, mid-1, down) || searchRec(mid+1, up, right, row-1);
    }

    public boolean searchMatrix(int[][] mat, int targ) {
        // cache input values in object to avoid passing them unnecessarily
        // to `searchRec`
        matrix = mat;
        target = targ;

        // an empty matrix obviously does not contain `target`
        if (matrix == null || matrix.length == 0) {
            return false;
        }

        return searchRec(0, 0, matrix[0].length-1, matrix.length-1);
    }
}