import java.util.*;

public class Solution {

    private String minWindow(String s, String t) {
        int[] count = new int[256];//默认初始值为0
        int number = t.length();
        int length = Integer.MAX_VALUE;
        int start = 0;

        //初始化原始窗口
        for(int i=0;i<t.length();i++){
            count[t.charAt(i)]++;
        }

        int l = 0;
        int r = 0;
        while(r < s.length()){
            if(number != 0){
                if(count[s.charAt(r)]>0)number--;
                count[s.charAt(r)]--;
                if(number!=0){
                    r++;
                }
            }else{
                if(count[s.charAt(l)]>=0){
                    if(length > r-l+1){
                        length = r-l+1;
                        start = l;
                    }
                    number++;
                    r++;
                }
                count[s.charAt(l)]++;
                l++;
            }
        }

        //Java中substring(startIndex,endIndex)不包括endIndex
        return length != Integer.MAX_VALUE?s.substring(start,length+start):"";
    }


    public int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer,Integer> record = new HashMap<>();
        List<Integer> res = new ArrayList<>();
        for(int i=0;i<nums1.length;i++){
            int count = record.getOrDefault(nums1[i],0);
            record.put(nums1[i],count+1);
        }

        for(int i=0;i<nums2.length;i++){
            int count = record.getOrDefault(nums2[i],0);
            if(count > 0){
                record.put(nums2[i],count-1);
                res.add(nums2[i]);
            }
        }

        int[] res2 = new int[res.size()];
        for(int i = 0;i < res2.length;i++){
            res2[i] = res.get(i);
        }
        return res2;
    }

    public static void getDigits(int n){
        while(n!=0){
            int tmp = n%10;
            n /= 10;
            System.out.println(tmp);
        }
    }

    public static void getNumber(int...arr){
        int number = 0;
        for(int i=0;i<arr.length;i++){
            number *= 10;
            number += arr[i];
        }
        System.out.println(number);
    }

    //290 Word Pattern，主要是双射，---> + <---两个方向都要考虑
    public static boolean wordPattern(String pattern, String str) {

        String[] words = str.split(" ");//这个方法特别好用
        if(pattern.length()!=words.length){
            return false;
        }

        Map<Character,String> map1 = new HashMap<>();
        Map<String,Character> map2 = new HashMap<>();

        for(int i=0;i<pattern.length();i++){
            String stmp = map1.getOrDefault(pattern.charAt(i),"none");
            if(stmp.equals("none")){
                map1.put(pattern.charAt(i),words[i]);
            }else{
                if(!stmp.equals(words[i])){
                    return false;
                }
            }

        }

        for(int i=0;i<words.length;i++){
            Character ctmp = map2.getOrDefault(words[i],'N');
            if(ctmp == 'N'){
                map2.put(words[i],pattern.charAt(i));
            }else{
                if(ctmp != pattern.charAt(i)){
                    return false;
                }
            }
        }

        return true;
    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x;}
    }

    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while(cur!=null){
            ListNode next = cur.next;
            cur.next = pre;

            pre = cur;
            cur = next;
        }
        return pre;
    }

    //71 Simplify Path
    static public String simplifyPath(String path) {
        Stack<String> record = new Stack<>();
        String[] arr = path.split("/");
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals("..")) {
                if (!record.empty()) {
                    record.pop();
                }
            }else if(!arr[i].equals(".")&&!arr[i].equals("")) {
                record.push(arr[i]);
            }
        }

        //速度：StringBuilder>StringBuffer(线程安全)>String
        StringBuffer res = new StringBuffer();

        List<String> list = new ArrayList<>();

        while(!record.empty()){
            list.add(record.pop());
        }

        for(int i=list.size()-1;i>=0;i--){
            res.append("/").append(list.get(i));
        }

        return new String(res);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "ADOBECODEBANC";
        String t = "ABC";
        System.out.println(solution.minWindow(s,t));

        //Map测试
        Map<Character,Integer> map = new HashMap<>();
        for(int i=0;i<s.length();i++){
            int count = map.getOrDefault(s.charAt(i),0);
            map.put(s.charAt(i),count+1);
        }

        for(Object c:map.keySet()){
            System.out.print(c+"-->"+map.get(c)+" ");
        }
        System.out.println();

        getDigits(19);

        //可变参数类型
        getNumber(1,2,3);

        String path = "/home/";
        String[] arr = path.split("/");
        System.out.println(arr.length);
        for(String str:arr){
            System.out.print(str+" ");
        }
        System.out.println();

        System.out.println(simplifyPath(path));

    }
}
