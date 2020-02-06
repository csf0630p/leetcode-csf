// 1.0 Trie Time: O(m * m * n + L), space: O(m * n + L), where m = average length of products, n = products.length, L = searchWord.length().
    class Trie {
        Trie[] sub = new Trie[26];
        LinkedList<String> suggestion = new LinkedList<>();
    }
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        Trie root = new Trie();
        for (String p : products) { // build Trie.
            Trie t = root;
            for (char c : p.toCharArray()) { // insert current product into Trie.
                if (t.sub[c - 'a'] == null)
                    t.sub[c - 'a'] = new Trie();
                t = t.sub[c - 'a'];
                t.suggestion.offer(p); // put products with same prefix into suggestion list.
                Collections.sort(t.suggestion); // sort products.
                if (t.suggestion.size() > 3) // maintain 3 lexicographically minimum strings.
                    t.suggestion.pollLast();
            }
        }
        List<List<String>> ans = new ArrayList<>();
        for (char c : searchWord.toCharArray()) { // search product.
            if (root != null) // if current Trie is NOT null.
                root = root.sub[c - 'a'];
            ans.add(root == null ? Arrays.asList() : root.suggestion); // add it if there exist products with current prefix.
        }
        return ans;
    }    

// 1.1 
class Solution {
    class Trie {
        Trie[] sub = new Trie[26];
        TreeSet<String> suggestion = new TreeSet<>();
    }
    
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        Trie root = new Trie();
        for (String p : products) { // build Trie.
            Trie t = root;
            for (char c : p.toCharArray()) { // insert current product into Trie.
                if (t.sub[c - 'a'] == null)
                    t.sub[c - 'a'] = new Trie();
                t = t.sub[c - 'a'];
                t.suggestion.add(p); // put products with same prefix into suggestion list.
                // Collections.sort(t.suggestion); // sort products.
                if (t.suggestion.size() > 3) // maintain 3 lexicographically minimum strings.
                    t.suggestion.pollLast();
            }
        }
        List<List<String>> ans = new ArrayList<>();
        for (char c : searchWord.toCharArray()) { // search product.
            if (root != null) // if current Trie is NOT null.
                root = root.sub[c - 'a'];
            ans.add(root == null ? new ArrayList<>() : new ArrayList<>(root.suggestion)); // add it if there exist products with current prefix.
        }
        return ans;
    }       
}

//2.0 binary search
// Sorting and searching cost O(n * m * log n) and O(L * m * logn), respectively; Therefore,
// Time: O((n + L) * m * logn), space: O(1) - excluding space cost of sorting and return List, where m = average length of products, n = products.length, L = searchWord.length().

class Solution {
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        List<List<String>> ans = new ArrayList<>();
        Arrays.sort(products);
        for (int i = 1; i <= searchWord.length(); ++i) {
            String cur = searchWord.substring(0, i);
            int k = Arrays.binarySearch(products, cur);
            while (k > 0 && cur.equals(products[k - 1])) // in case there are more than 1 cur in products.
                --k; // find the first one. 
            if (k < 0) // no cur in products. 
                k = ~k; // find the first one larger than cur.
            List<String> suggestion = new ArrayList<>();
            for (int j = k + 3; k < products.length && k < j && products[k].startsWith(cur); ++k)
                suggestion.add(products[k]);
            ans.add(suggestion);
        }
        return ans;
    }
}

//2.1
// https://leetcode.com/problems/search-suggestions-system/discuss/436674/C%2B%2BJavaPython-Sort-and-Binary-Search-the-Prefix

public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        List<List<String>> res = new ArrayList<>();
        TreeMap<String, Integer> map = new TreeMap<>();
        Arrays.sort(products);
        List<String> productsList = Arrays.asList(products);

        for (int i = 0; i < products.length; i++) {
            map.put(products[i], i);
        }

        String key = "";
        for (char c : searchWord.toCharArray()) {
            key += c;
            String ceiling = map.ceilingKey(key);
            String floor = map.floorKey(key + "~");
            if (ceiling == null || floor == null) break;
            res.add(productsList.subList(map.get(ceiling), Math.min(map.get(ceiling) + 3, map.get(floor) + 1)));
        }

        while (res.size() < searchWord.length()) res.add(new ArrayList<>());
        return res;
    }