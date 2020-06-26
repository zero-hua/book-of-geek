package ink.zerohua.bookofgeek.utils;

import org.apache.shiro.crypto.hash.SimpleHash;

public class EnCode {

    public static String getEnCodeStr(String source,String salt){
        String s = new SimpleHash("SHA-256",source,salt,2).toString();
        return s;
    }
}
