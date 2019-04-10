public class Solution {
    List<List<String>> res = new ArrayList<>();
    HashMap<String, List<String>> wordMap = new HashMap<>();
        
    public void backTrace(String word,String start,List<String> list){
        if (word.equals(start)){
            list.add(0,start);
            res.add(new ArrayList<String>(list));
            list.remove(0);
            return;
        }
        list.add(0,word);
        if (wordMap.get(word) != null)
            for (String s:wordMap.get(word))
                backTrace(s,start,list);
        list.remove(0);
    }
    
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {    	
        if (!wordList.contains(endWord)) 
        	return res;
        for (String id : wordList) 
        	wordMap.put(id, null);

        Queue<String> q = new LinkedList<>();
        q.add(beginWord);
        HashMap<String, Integer> level = new HashMap<>();
        level.put(beginWord, 0);
        boolean found = false;
        int minLevel = 0;
        HashSet<String> visited = new HashSet<>();
        
        while (!q.isEmpty()) {
        	String head = q.poll();
            if (found && (level.get(head) == minLevel))
                break;
        	String temp;
        	for (int i = 0; i < head.length(); i++) {
        		for (char j = 'a'; j <= 'z'; j++) {
        			if (head.charAt(i) != j) {
        				temp = head.substring(0, i) + j + head.substring(i + 1, head.length());
        				if (wordMap.containsKey(temp)) {
                            if (!visited.contains(temp) ||
                               ((level.get(head) + 1) <= level.get(temp))) {
                                if (wordMap.get(temp) != null) {
                                    // System.out.println(wordMap);
                                    wordMap.get(temp).add(head);
                                } else {
                                    List<String> l = new ArrayList<>();
                                    l.add(head);
                                    wordMap.put(temp, l);
                                }
                                if (!visited.contains(temp))    
                                    q.add(temp);
                                visited.add(temp);
                                level.put(temp, level.get(head) + 1);
                            }
        				}                       
        				if (temp.equals(endWord)) {
                            minLevel = level.get(head) + 1;
                            found = true;                       
        				} 
        			}
        		}
        	}        	
        }
        List<String> list = new LinkedList<>();
        backTrace(endWord,beginWord,list);
        return res;
    }
}
----
public class Solution {
    List<List<String>> res = new ArrayList<>();
    HashMap<String, List<String>> wordMap = new HashMap<>();
        
    public void backTrace(String word,String start,List<String> list){
        if (word.equals(start)){
            list.add(0,start);
            res.add(new ArrayList<String>(list));
            list.remove(0);
            return;
        }
        list.add(0,word);
        if (wordMap.get(word) != null)
            for (String s:wordMap.get(word))
                backTrace(s,start,list);
        list.remove(0);
    }
    
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
    	
        if (!wordList.contains(endWord)) {
        	return res;
        }
        
        for (String id : wordList) {
        	wordMap.put(id, null);
        }
        
//        int[] pre = new int[wordList.size()];
        Queue<String> q = new LinkedList<>();
        q.add(beginWord);
        HashMap<String, Integer> level = new HashMap<>();
        level.put(beginWord, 0);
        boolean found = false;
        int minLevel = 0;
        HashSet<String> visited = new HashSet<>();
        
        while (!q.isEmpty()) {
            // System.out.println(q);
        	String head = q.poll();
            if (found)
                if (level.get(head) == minLevel)
                    break;
        	String temp;
            // System.out.println("debug: " + q);
            // System.out.println("debug: " + level);
        	for (int i = 0; i < head.length(); i++) {
        		for (char j = 'a'; j <= 'z'; j++) {
        			if (head.charAt(i) != j) {
        				temp = head.substring(0, i) + j + head.substring(i + 1, head.length());
        				if (wordMap.containsKey(temp)) {
                            if (!visited.contains(temp) ||
                               ((level.get(head) + 1) <= level.get(temp))) {
                                if (wordMap.get(temp) != null) {
                                    // System.out.println(wordMap);
                                    wordMap.get(temp).add(head);
                                } else {
                                    List<String> l = new ArrayList<>();
                                    l.add(head);
                                    wordMap.put(temp, l);
                                }
                                if (!visited.contains(temp))    
                                    q.add(temp);
                                visited.add(temp);
                                level.put(temp, level.get(head) + 1);
                            }
        				}
        				if (temp.equals(endWord)) {
                            // System.out.println("w: " + wordMap);
                            // System.out.println("l: " + level);
                            // System.out.println();
                            minLevel = level.get(head) + 1;
                            found = true;
        					// String id = temp;
        					// Deque<String> line = new LinkedList<>();
        					// while (id != null) {
        					// 	line.addFirst(id);    
        					// id = wordMap.get(id).get(0);
        					// }
        					// res.add((List<String>) line);                            
        				}
 
        			}
        		}
        	}
        	
        }
        // System.out.println("w: " + wordMap);
        List<String> list = new LinkedList<>();
        backTrace(endWord,beginWord,list);
        return res;
    }

}