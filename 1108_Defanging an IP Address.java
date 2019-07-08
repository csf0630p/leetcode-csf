class Solution {
    public String defangIPaddr(String address) {
        String[] ip = address.split("\\.");       
        String res = "";
        for (String s : ip) {
            res += s + "[.]";
        }
        res = res.substring(0, res.length() - 3);
        return res;
    }
}