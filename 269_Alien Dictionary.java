class Solution {
    public String alienOrder(String[] words) {
        if ((words == null) || (words.length == 0)) return "";
        
        HashMap<Character, Set<Character>> fanout = new HashMap<>();
        HashMap<Character, Integer> fanin = new HashMap<>();
        
        for (int i = 0; i < words.length; i++) {
            for (Character c : words[i].toCharArray()) {
                if (!fanin.containsKey(c))
                    fanin.put(c, 0);
                if (!fanout.containsKey(c))
                    fanout.put(c, new HashSet<Character>());                
            }
        }
        
        // scan and get the logical relationship
        for (int i = 0; i < words.length - 1; i++) {
            String str1 = words[i], str2 = words[i + 1];
            int j = 0;
            for (; j < Math.min(str1.length(), str2.length()); j++) {
                if (str1.charAt(j) != str2.charAt(j)) 
                    break;
            }
            // if "z""z" or "ab""abc", we can't get any information
            // otherwise it means str2[j] > str1[j], thus str1[j]->str2[j]            
            if (j != Math.min(str1.length(), str2.length())) {
                Set<Character> next;
                // if (fanout.containsKey(str1.charAt(j)))
                    next = fanout.get(str1.charAt(j));
                // else
                //     next = new HashSet<>();
                if (!next.contains(str2.charAt(j))) {
                    next.add(str2.charAt(j));
                    fanout.put(str1.charAt(j), next);
                    fanin.put(str2.charAt(j), fanin.getOrDefault(str2.charAt(j), 0) + 1); 
                }                    
            }
            // System.out.println(str1 + " " + str2);
            // if (!fanin.containsKey(str1.charAt(j)))
            //     fanin.put(str1.charAt(j), 0);
            // if (!fanout.containsKey(str2.charAt(j)))
            //     fanout.put(str2.charAt(j), new HashSet<>());            
        }
        // for (Character c : fanout.keySet()) {
        //     System.out.println(c + fanout.get(c).toString());            
        // }
        // System.out.println(fanin);
        // return "";
        
        //topo sort, bfs
        Queue<Character> q = new LinkedList<>();
        for (Map.Entry<Character, Integer> e : fanin.entrySet()) {
            if (e.getValue() == 0)
                q.add(e.getKey());
        }
        StringBuilder res = new StringBuilder();
        while (!q.isEmpty()) {
            Set<Character> next = fanout.get(q.peek());
            if (next != null)
                for (Character c : next) {
                    fanin.put(c, fanin.get(c) - 1);
                    if (fanin.get(c) == 0)
                        q.add(c);
                }
            res.append(q.poll());
        }
        for (Map.Entry<Character, Integer> e : fanin.entrySet()) {
            if (e.getValue() != 0)
                return "";
        }      
        return res.toString();
    }
}