class Solution {
    int m = 0, n = 0;
    public List<String> findWords(char[][] board, String[] words) {
        Set<String> res = new TreeSet<>();    
        List<String> res2 = new ArrayList<>(res);
        m = board.length;
        if (m == 0) return res2;
        n = board[0].length;
        if (n == 0) return res2;  
        for (String word : words)
            for (int i = 0; i < m; i++) 
                for (int j = 0; j < n; j++) 
                    if (exist(board, i, j, word, 0)) 
                        res.add(word);
        res2 = new ArrayList<>(res);
        return res2;        
    }
    
    private boolean exist(char[][] board, int i, int j, String word, int start) {
        // if (start >= word.length()) return true;
        if ((i < 0) || (j < 0) || (i >= m) || (j >= n)) return false;
        boolean res = false;
        if (board[i][j] == word.charAt(start)) {
            if (start == word.length() - 1) return true;
            char temp = board[i][j];    //for restore
            board[i][j] = '#';  // avoid infinite loop
            res = exist(board, i + 1, j, word, start + 1) ||
                    exist(board, i - 1, j, word, start + 1) ||
                    exist(board, i, j + 1, word, start + 1) ||
                    exist(board, i, j - 1, word, start + 1);
            // if does not use compound expression, then we have to do complete 4 search, TLE
            board[i][j] = temp;
        }
        return res;
    }        
}

// method 2: trie + dfs
class Solution {
    class Trie {
        boolean isEnd = false;
        Trie[] next = new Trie[26];
    }     
    int m = 0, n = 0;
    Set<String> res = new TreeSet<>();      
    public List<String> findWords(char[][] board, String[] words) {
        Trie root = new Trie();  
        List<String> res2 = new ArrayList<>(res);
        m = board.length; if (m == 0) return res2;
        n = board[0].length; if (n == 0) return res2;  
        for (String word : words) {
            Trie node = root;
            for (Character c : word.toCharArray()) {
                if (node.next[c - 'a'] == null)
                    node.next[c - 'a'] = new Trie();
                node = node.next[c - 'a'];
            }
            node.isEnd = true;
        }            
        for (int i = 0; i < m; i++) 
            for (int j = 0; j < n; j++) 
                exist(board, i, j, "", 0, root);
        res2 = new ArrayList<>(res);
        return res2;        
    }
    
    private void exist(char[][] board, int i, int j, String word, int start, Trie node) {
        if ((i < 0) || (j < 0) || (i >= m) || (j >= n) || (board[i][j] == '#')) return;
        if (node.next[board[i][j] - 'a'] != null) {
            // System.out.printf("%d %d %c\n", i, j, board[i][j]);
            if (node.next[board[i][j] - 'a'].isEnd) {
                res.add(word + board[i][j]);
                // return;
            }
            char temp = board[i][j]; 
            String path = word + board[i][j];
            board[i][j] = '#';              
            exist(board, i + 1, j, path, start + 1, node.next[temp - 'a']);
            exist(board, i - 1, j, path, start + 1, node.next[temp - 'a']);
            exist(board, i, j + 1, path, start + 1, node.next[temp - 'a']);
            exist(board, i, j - 1, path, start + 1, node.next[temp - 'a']);
            board[i][j] = temp;
        }
        return;
    }        
}