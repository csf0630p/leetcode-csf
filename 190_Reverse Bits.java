// 1.0
public class Solution {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            res <<= 1;
            res |= (n & 1);
            n >>>= 1;
        }
        return res;        
    }
}

// 1.1 将 int 拆成 4 个 byte，然后缓存 byte 对应的比特位翻转，最后再拼接
private static Map<Byte, Integer> cache = new HashMap<>();

public int reverseBits(int n) {
    int ret = 0;
    for (int i = 0; i < 4; i++) {
        ret <<= 8;
        ret |= reverseByte((byte) (n & 0b11111111));
        n >>= 8;
    }
    return ret;
}

private int reverseByte(byte b) {
    if (cache.containsKey(b)) return cache.get(b);
    int ret = 0;
    byte t = b;
    for (int i = 0; i < 8; i++) {
        ret <<= 1;
        ret |= t & 1;
        t >>= 1;
    }
    cache.put(b, ret);
    return ret;
}

// 1.2
public class Solution {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
            int ret=n;
            ret = ret >>> 16 | ret<<16;
            ret = (ret & 0xff00ff00) >>> 8 | (ret & 0x00ff00ff) << 8;
            ret = (ret & 0xf0f0f0f0) >>> 4 | (ret & 0x0f0f0f0f) << 4;
            ret = (ret & 0xcccccccc) >>> 2 | (ret & 0x33333333) << 2;
            ret = (ret & 0xaaaaaaaa) >>> 1 | (ret & 0x55555555) << 1;
            return ret;
    }
}