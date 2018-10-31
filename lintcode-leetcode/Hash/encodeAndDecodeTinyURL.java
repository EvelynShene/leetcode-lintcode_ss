/** 535. Encode and Decode TinyURL(leetcode)
 *    Note: This is a companion problem to the System Design problem: Design TinyURL.
 *    TinyURL is a URL shortening service where you enter a URL such as https://leetcode.com/problems/design-tinyurl 
 *  and it returns a short URL such as http://tinyurl.com/4e9iAk.
 *    Design the encode and decode methods for the TinyURL service. There is no restriction on how your 
 *  encode/decode algorithm should work. You just need to ensure that a URL can be encoded to a tiny URL and the
 *  tiny URL can be decoded to the original URL.
 */
 
 /* Method 1: Using Counter 
  *      One problem with this method is that it is very easy to predict the next code generated, since the 
  *   pattern can be detected by generating a few encoded URLs.
  */
 public class Codec {
    Map<Integer, String> map = new HashMap<Integer, String>();
    int index = 0;
    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        map.put(index, longUrl);
        String res = "http://tinyurl.com/" + index;
        index++;
        return res;
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        String[] vals = shortUrl.trim().split("/");
        int key = Integer.parseInt(vals[vals.length - 1]);
        if(map.containsKey(key)){
            return map.get(key);
        }
        return "";
    }
}

/* Method 2: Use inbuild hashCode() function 
 *     As the number of encoded URLs increases, the probability of collisions increases, which leads to failure.
 */
public class Codec {
    Map<Integer, String> map = new HashMap<Integer, String>();
    
    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        map.put(longUrl.hashCode(), longUrl);
        String res = "http://tinyurl.com/" + longUrl.hashCode();
        return res;
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        int key = Integer.parseInt(shortUrl.replace("http://tinyurl.com/", "").trim());
        if(map.containsKey(key)){
            return map.get(key);
        }
        return "";
    }
}
