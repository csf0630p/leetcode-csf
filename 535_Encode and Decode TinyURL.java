//https://leetcode.com/problems/encode-and-decode-tinyurl/discuss/100270/Three-different-approaches-in-java
// simple counter; hashcode; random function
public class Codec {

    Map<String, String> map = new HashMap<>();
    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {       
        int keyInt = Math.abs(longUrl.hashCode()); 
        // System.out.println(keyInt);
        String key = convert10to62(keyInt);
        while (map.containsKey(key)) {
            if (longUrl.equals(map.get(key))) {
                break;
            } else {
                keyInt++;
                key = convert10to62(keyInt);
            }
        }
        map.put(key, longUrl);        
        // System.out.println("http://tinyurl.com/" + key);
        return "http://tinyurl.com/" + key;
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        String key = shortUrl.replace("http://tinyurl.com/", "");
        if (map.containsKey(key)) {
            return map.get(key);
        } else {
            return "not found";
        }
    }
    
    private String convert10to62(int dec) {
        String res = "";
        while (dec > 0) {
            int mod = dec % 62;
            res = (char)bitConvert10to62(mod) + res;
            dec /= 62;
        }
        return res;
    }
    
    private int bitConvert10to62(int bit) {
        if (bit < 10) {
            return bit + '0';
        } else if (bit < 36) {
            return bit - 10 + 'A';
        } else {
            return bit - 36 + 'a';
        }
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(url));