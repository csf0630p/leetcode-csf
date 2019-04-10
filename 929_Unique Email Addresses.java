class Solution {
    public int limit(int x, int len) {
        if (x > len - 1) 
            return len - 1;
        else 
            return x;
    }
    public int numUniqueEmails(String[] emails) {
        HashSet<String> set = new HashSet<>();        
        for (String s : emails) {
            int sep = s.indexOf('@');
            String domain = s.substring(limit(sep + 1, s.length()), s.length());
            String local = s.substring(0, sep);
            int pos2 = local.indexOf('+');
            if (pos2 != -1)
                local = local.substring(0, pos2);     
            int pos = local.indexOf('.');
            while (pos != -1) {
                local = local.substring(0, pos) + local.substring(limit(pos + 1, local.length()), local.length());                
                pos = local.indexOf('.');
            }
            set.add(local + "@" + domain);
        }
        return set.size();
    }
}