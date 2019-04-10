Word 

Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:

Only one letter can be changed at a time.
Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
Note:

Return 0 if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.
You may assume no duplicates in the word list.
You may assume beginWord and endWord are non-empty and are not the same.
  
Input:
beginWord = "hit",
endWord = "cog",
wordList = ["hot","dot","dog","lot","log","cog"]

Output: 5

Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
return its length 5.
  
  
  
  class solution{
    public int wordladder (String begin, String end, List<String> list){
        int count = 0;
        HashSet<String> wordset = new HashSet<>(list);
        HashSet<String> visited = new HashSet<>();
        visited.add(begin);
        if (wordset.contains(begin)) {
          wordset.remove(begin);
        }
      
      while (!visited.contains(end)) {
        HashSet<String> t = new HashSet<>();
        for (String word: visited) {
          for (int i = 0; i < word.length(); i++) {
            char[] chars = word.toCharArray();
            for (char j = 'a'; j <= 'z'; j++) {
              chars[i] = j;
              String newWord = new String(chars);
              if (wordset.contains(newWord)) {
                t.add(newWord);
                wordSet.remove(newWord);
              }                
            }
          }
        }
        count += 1;
        if (t.size() == 0) {
          return 0;
        }
        visited = t;
      }
      return count;
    }
  }

O(l * 26 * w)