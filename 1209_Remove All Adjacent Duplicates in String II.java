// 1.0 Brute Force, O(n^2/k)/O(1)
public String removeDuplicates(String s, int k) {
    StringBuilder sb = new StringBuilder(s);
    int length = -1;
    while (length != sb.length()) {
        length = sb.length();
        for (int i = 0, count = 1; i < sb.length(); ++i) {
            if (i == 0 || sb.charAt(i) != sb.charAt(i - 1)) {
                count = 1;
            } else if (++count == k) {
                sb.delete(i - k + 1, i + 1);
                break;
            }
        }
    }
    return sb.toString();
}

// 2.0 dp, O(n)/O(n)
public String removeDuplicates(String s, int k) {
    StringBuilder sb = new StringBuilder(s);
    int count[] = new int[sb.length()];
    for (int i = 0; i < sb.length(); ++i) {
        if (i == 0 || sb.charAt(i) != sb.charAt(i - 1)) {
            count[i] = 1;
        } else {
            count[i] = count[i - 1] + 1;
            if (count[i] == k) {
                sb.delete(i - k + 1, i + 1);
                i = i - k;
            }
        }
    }
    return sb.toString();
}

// 3.0 stack, O(n)/O(n)
public String removeDuplicates(String s, int k) {
    StringBuilder sb = new StringBuilder(s);
    Stack<Integer> counts = new Stack<>();
    for (int i = 0; i < sb.length(); ++i) {
        if (i == 0 || sb.charAt(i) != sb.charAt(i - 1)) {
            counts.push(1);
        } else {
            int incremented = counts.pop() + 1;
            if (incremented == k) {
                sb.delete(i - k + 1, i + 1);
                i = i - k;
            } else {
                counts.push(incremented);
            }
        }
    }
    return sb.toString();
}

// 4.0 Stack with Reconstruction, O(n)/O(n)
class Pair {
    int cnt;
    char ch;
    public Pair(int cnt, char ch) {
        this.ch = ch;
        this.cnt = cnt;
    }
}
public String removeDuplicates(String s, int k) {
    Stack<Pair> counts = new Stack<>();
    for (int i = 0; i < s.length(); ++i) {
        if (counts.empty() || s.charAt(i) != counts.peek().ch) {
            counts.push(new Pair(1, s.charAt(i)));
        } else {
            if (++counts.peek().cnt == k) {
                counts.pop();
            }
        }
    }
    StringBuilder b = new StringBuilder();
    while (!counts.empty()) {
        Pair p = counts.pop();
        for (int i = 0; i < p.cnt; i++) {
            b.append(p.ch);
        }
    }
    return b.reverse().toString();
}

// 5.0 Two Pointers, O(n)/O(n)
class Solution {
public String removeDuplicates(String s, int k) {
    Stack<Integer> counts = new Stack<>();
    char[] sa = s.toCharArray();
    int j = 0;
    for (int i = 0; i < s.length(); ++i, ++j) {
        sa[j] = sa[i];
        if (j == 0 || sa[j] != sa[j - 1]) {
            counts.push(1);
        } else {
            int incremented = counts.pop() + 1;
            if (incremented == k) {
                j = j - k;
            } else {
                counts.push(incremented);
            }
        }
    }
    return new String(sa, 0, j);
}
}