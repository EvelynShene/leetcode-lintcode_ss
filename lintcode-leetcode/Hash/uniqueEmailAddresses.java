/** 929. Unique Email Addresses
 *      Every email consists of a local name and a domain name, separated by the @ sign.
 *      For example, in alice@leetcode.com, alice is the local name, and leetcode.com is the domain name.
 *      Besides lowercase letters, these emails may contain '.'s or '+'s.
 *      If you add periods ('.') between some characters in the local name part of an email address, mail sent 
 *  there will be forwarded to the same address without dots in the local name.  
 *  For example, "alice.z@leetcode.com" and "alicez@leetcode.com" forward to the same email address.  
 *  (Note that this rule does not apply for domain names.)
 *      If you add a plus ('+') in the local name, everything after the first plus sign will be ignored. This 
 *  allows certain emails to be filtered, for example m.y+name@email.com will be forwarded to my@email.com.  
 *  (Again, this rule does not apply for domain names.)
 *      It is possible to use both of these rules at the same time.
 *      Given a list of emails, we send one email to each address in the list.  
 *  How many different addresses actually receive mails? 
 *
 *      Example: Input: ["test.email+alex@leetcode.com","test.e.mail+bob.cathy@leetcode.com",
 *                      "testemail+david@lee.tcode.com"]
 *               Output: 2
 *               Explanation: "testemail@leetcode.com" and "testemail@lee.tcode.com" actually receive mails
 *
 *      Note: 1) 1 <= emails[i].length <= 100
 *            2) 1 <= emails.length <= 100
 *            3) Each emails[i] contains exactly one '@' character.
 */
 
 //Method 1: Use HashMap + Set
 class Solution {
    public int numUniqueEmails(String[] emails) {
        if(emails == null || emails.length == 0){
            return 0;
        }
        int count = 0;
        Map<String, Set<String>> map = new HashMap<String, Set<String>>();
        for(String email: emails){
            String[] names = email.split("@");
            if(names.length < 2){
                continue;
            }
            String domain = names[1];
            String local = names[0].replaceAll(".", "");
            int index = local.indexOf("+");
            if(index != -1){
                local = local.substring(0, index);
            }
            if(!map.containsKey(local)){
                Set<String> domains = new HashSet<String>();
                domains.add(domain);
                map.put(local, domains);
                count++;
            }
            else{
                Set<String> domains = map.get(local);
                if(!domains.contains(domain)){
                    domains.add(domain);
                    count++;
                }
            }
        }
        return count;
    }
}

//Method 2: Use Set, more concise
class Solution {
    public int numUniqueEmails(String[] emails) {
        if(emails == null || emails.length == 0){
            return 0;
        }
    
        Set<String> set = new HashSet<String>();
        for(String email: emails){
            String[] names = email.split("@");
            if(names.length < 2){
                continue;
            }
            String local = names[0].replaceAll(".", "");
            int index = local.indexOf("+");
            if(index != -1){
                local = local.substring(0, index);
            }
            String new_email = local + "@" + names[1];
            set.add(new_email);
        }
        return set.size();
    }
}
