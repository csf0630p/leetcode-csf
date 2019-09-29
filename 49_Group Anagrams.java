class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for(String s: strs){
            int[] arr = new int[26];
            for(int i = 0;i<s.length();i++){
                arr[s.charAt(i) - 'a']++;
            }
			/* as same as
            for(Character c : s.toCharArray()){
                arr[c - 'a']++;
            }  			
			*/
            String key = Arrays.toString(arr);
            List<String> tempList = map.getOrDefault(key, new LinkedList<String>());
            tempList.add(s);
            map.put(key,tempList);
        }
        return new LinkedList<>(map.values());
    }
}