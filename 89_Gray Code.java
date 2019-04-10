//method 1: dfs
class Solution {
    int power = 1;
    List<Integer> list = null;
    HashSet<Integer> set = null;
    
    public List<Integer> grayCode(int n) {
        for (int i = 0; i < n; i++)
            power <<= 1;
        list = new ArrayList<>();
        set = new HashSet<>();
        set.add(0);
        list.add(0);
        dfs(0, n);
        return list;
    }
    
    void dfs(int id, int n) {
        if (list.size() == power)
            return;        
        int mask = 1;
        for (int i = 0; i < n; i++) {
            int temp = id ^ mask;
            if (!set.contains(temp)) {
                set.add(temp);
                list.add(temp);
                dfs(temp, n);
                if (list.size() == power) 
                    return;
                set.remove(temp);
                list.remove(list.size() - 1);
            }
            mask <<= 1;
        }
    }
}

//method 2: math formula
class Solution {    
    public List<Integer> grayCode(int n) {        
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < (1 << n); i++)
            res.add(i ^ i >> 1);
        return res;
    }
}

//http://www.voidcn.com/article/p-yeexwwuy-gw.html
//method 3: generate
class Solution {
    public List<Integer> grayCode(int n) {
        List<Integer> res = new ArrayList<>();
        res.add(0);
        for (int i = 0; i < n; i++) {
            int highestBit = 1 << i;
            for (int j = res.size() - 1; j >= 0; j--) {
                res.add(highestBit + res.get(j));
				//res.add(highestBit | res.get(j)); 等效上句
            }
        }
        return res;
    }
}