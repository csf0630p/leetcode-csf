// 1.0 
public List<List<Integer>> generate(int numRows) {        
	List<List<Integer>> res = new LinkedList<>();
	if (numRows == 0) return res;
	Integer[] last = null, line = null;
	for (int id = 0; id < numRows; id++) {
		if (id == 0) {
			line = new Integer[1];
			line[0] = 1;               
		} else {
			last = line;
			line = new Integer[id + 1];
			line[0] = 1;
			for (int i = 0; i < last.length - 1; i++) {
				line[i + 1] = last[i] + last[i + 1];
			}
			line[id] = 1;
		}
		res.add(Arrays.asList(line));
	}
	return res;
}    

// https://leetcode.com/problems/pascals-triangle/discuss/38141/My-concise-solution-in-Java
// 2.0 inplace
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList();
        List<Integer> row = new ArrayList();
        for(int i = 0; i < numRows; i++) {
            for(int j = row.size() - 1; j >= 1 ; j--) {
                row.set(j, row.get(j) + row.get(j - 1));
            }
            row.add(1);
            res.add(new ArrayList(row));
        }
        return res;
    }