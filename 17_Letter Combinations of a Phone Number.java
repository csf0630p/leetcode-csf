//method 1: recursion with String O(3~4^n)/O(n^2)+O(3~4^n res)
class Solution {
    public List<String> letterCombinations(String digits) {
        List<String> res = new LinkedList<>();
        if (digits.equals(""))
            return res;
        gene(res, digits, "");
        return res;
    }
    
    void gene(List<String> res, String digits, String word) {
        if (digits.equals("")) {
            res.add(word);
        } else {
            Set<Character> letters = convert(digits.charAt(0));
            for (Character c : letters) {
                gene(res, digits.substring(1), word + c);
            }
        }       
    }
    
    Set<Character> convert(char c) {
        Set<Character> set = new HashSet<>();
        if (c < '2' || c > '9')
            return set;
        char start = (char)((c - '2') * 3 + 'a'), end = (char)((c - '1') * 3 + 'a');
        if (c >= '7') end++;
        if (c >= '8') start++;        
        if (c == '9') end++;
        for (char id = start; id < end; id++) {
            set.add(id);
        }
        return set;
    }
}

//method 1.1
class Solution {
    public List<String> letterCombinations(String digits) {
        List<String> res = new LinkedList<>();
        if (digits.equals("")) return res;
		String[] mapping = new String[] {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};        
        gene(mapping, res, digits, "");
        return res;
    }
    
    void gene(String[] mapping, List<String> res, String digits, String word) {
        if (digits.equals("")) {
            res.add(word);
        } else {
            char[] letters = mapping[digits.charAt(0) - '0'].toCharArray();
            for (char c : letters) {
                gene(mapping, res, digits.substring(1), word + c);
            }
        }       
    }
}

//method 2: backtrack, pre-convert hashtable, StringBuilder  O(3~4^n)/O(n)+O(3~4^n res)
class Solution {
    public List<String> letterCombinations(String digits) {
        List<String> res = new LinkedList<>();        
        if (digits.equals(""))
            return res;
        HashMap<Character, List<Character>> convert = new HashMap<>();
        for (char c = '2'; c <= '9'; c++) {
            convert.put(c, makeList(c));
        }
        System.out.println(convert);
        StringBuilder word = new StringBuilder(), digitsRemain = new StringBuilder(digits);
        gene(convert, res, digitsRemain, word);
        return res;
    }
    
    void gene(HashMap<Character, List<Character>> convert, List<String> res, StringBuilder digits, StringBuilder word) {
        if (digits.toString().equals("")) {
            res.add(word.toString());
        } else {
            List<Character> letters = convert.get(digits.charAt(0));
            for (Character c : letters) {
                char temp = digits.charAt(0);
                gene(convert, res, digits.deleteCharAt(0), word.append(c));
                digits.insert(0, temp);
                word.deleteCharAt(word.length() - 1);
            }
        }       
    }
    
    List<Character> makeList(char c) {
        List<Character> list = new ArrayList<>();
        if (c < '2' || c > '9')
            return list;
        char start = (char)((c - '2') * 3 + 'a'), end = (char)((c - '1') * 3 + 'a');
        if (c >= '7') end++;
        if (c >= '8') start++;        
        if (c == '9') end++;
        for (char id = start; id < end; id++) {
            list.add(id);
        }
        return list;
    }
}

//method 3: BFS,	O(3~4^n)/O(3~4^n)
class Solution {
    public List<String> letterCombinations(String digits) {
		Queue<String> ans = new LinkedList<String>();
		if (digits.isEmpty()) 
            return (List)ans;
		String[] mapping = new String[] {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
		ans.add("");
		while(ans.peek().length() != digits.length()){
			String remove = ans.poll();
			String map = mapping[digits.charAt(remove.length())-'0'];
			for(char c: map.toCharArray()){
				ans.add(remove + c);
			}
		}
		return (List)ans;
	}
}

//method 3.1: BFS
class Solution {
    public List<String> letterCombinations(String digits) {
		Queue<String> ans = new LinkedList<String>();
		if (digits.isEmpty()) 
            return (List)ans;
		String[] mapping = new String[] {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
		ans.add("");
		for(int i = 0; i < digits.length(); i++){
			int x = digits.charAt(i) - '0';
			while(ans.peek().length() == i){
				String s = ans.poll();
				for(char c : mapping[x].toCharArray())
					ans.add(s + c);
			}
		}
		return (List)ans;
	}
}