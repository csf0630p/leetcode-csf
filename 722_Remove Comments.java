// 1.0 scan by bytes
// https://leetcode.com/problems/remove-comments/discuss/109197/One-pass-solution-in-Java
class Solution {
    public List<String> removeComments(String[] source) {
        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();     
        boolean mode = false;
        for (String s : source) {
            for (int i = 0; i < s.length(); i++) {
                if (mode) {
                    if (s.charAt(i) == '*' && i < s.length() - 1 && s.charAt(i + 1) == '/') {
                        mode = false;
                        i++;        //skip '/' on next iteration of i
                    }
                }
                else {
                    if (s.charAt(i) == '/' && i < s.length() - 1 && s.charAt(i + 1) == '/') {
                        break;      //ignore remaining characters on line s
                    }
                    else if (s.charAt(i) == '/' && i < s.length() - 1 && s.charAt(i + 1) == '*') {
                        mode = true;
                        i++;           //skip '*' on next iteration of i
                    }
                    else    sb.append(s.charAt(i));     //not a comment
                }
            }
            if (!mode && sb.length() > 0) {
                res.add(sb.toString());
                sb = new StringBuilder();   //reset for next line of source code
            }
        }
        return res;
    }
}

// 2.0
// https://blog.csdn.net/magicbean2/article/details/79330484
// https://leetcode.com/problems/remove-comments/discuss/109202/java-solution-easy-to-understand
    private boolean incomment;
    private String unfinish;
    private List<String> res;

    private void addLine(String line) {
        unfinish = "";
        if (!line.isEmpty())
            res.add(line);
    }
    
    private void processIncomment(String line) {
        int ind = line.indexOf("*/");
        if (ind == -1)
            return;
        incomment = false;
        processNotIncomment(line.substring(ind + 2));
    }

    private void processNotIncomment(String line) {
        int ind = line.indexOf("/*");
        int ind1 = line.indexOf("//");
        if (ind == -1 && ind1 == -1) {
            addLine(unfinish + line);
            return;
        }
        if (ind1 != -1 && (ind == -1 || ind1 < ind)) {
            addLine(unfinish + line.substring(0, ind1));
            return;
        } 
        incomment = true;
        unfinish += line.substring(0, ind);
        processIncomment(line.substring(ind + 2));
    }
    
    public List<String> removeComments(String[] source) {
        incomment = false;
        unfinish = "";
        res = new ArrayList<>();
        for (String line: source)
            if (incomment)
                processIncomment(line);
            else
                processNotIncomment(line);
        return res;
    }

// incomplete
class Solution {
    public List<String> removeComments(String[] source) {
        boolean inCom = false;
        List<String> res = new ArrayList<>();
        for (String str : source) {
            if (inCom) {
                if (str.indexOf("*/") != -1) {
                    str
                }
            } else {
                
            }
        }
    }
}