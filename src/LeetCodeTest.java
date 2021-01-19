import org.junit.Test;

import java.util.*;

public class LeetCodeTest {

    //712
    public int minimumDeleteSum(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            Arrays.fill(dp[i], 0);
        }
        for (int i = 1; i <= m; i++) {
            dp[i][0] = dp[i - 1][0] + s1.charAt(i - 1);
        }
        for (int j = 1; j <= n; j++) {
            dp[0][j] = dp[0][j - 1] + s2.charAt(j - 1);
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j] + s1.charAt(i - 1), dp[i][j - 1] + s2.charAt(j - 1));
                }
            }
        }
        return dp[m][n];
    }

    //每日一題：721
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        List<List<String>> res = accounts;
        int oldSize;
        int newSize;
        do {
            oldSize = res.size();
            res = func(res);
            newSize = res.size();
        } while (newSize != oldSize);
        return res;
    }

    public List<List<String>> func(List<List<String>> accounts){
        List<List<String>> res = new ArrayList<>();
        boolean[] mark = new boolean[accounts.size()];
        Arrays.fill(mark, false);
        for (int i = 0; i < accounts.size(); i++) {
            if(mark[i]) continue;
            String name1 = accounts.get(i).get(0);
            Set<String> emails = new TreeSet<>();
            for (int k = 1; k < accounts.get(i).size(); k++) {
                emails.add(accounts.get(i).get(k));
            }
            System.out.print("i="+i+": ");
            for (int j = 0; j < accounts.size(); j++) {
                //排除自己
                if (i == j) {
                    continue;
                }
                String name2 = accounts.get(j).get(0);
                if (name1.equals(name2)) {
                    boolean same = false;
                    //遍历查找是否是相同的用户
                    for (int k = 1; k < accounts.get(j).size(); k++) {
                        if (emails.contains(accounts.get(j).get(k))) {
                            same = true;
                            break;
                        }
                    }
                    if (same) {
                        for (int k = 1; k < accounts.get(j).size(); k++) {
                            emails.add(accounts.get(j).get(k));
                        }
                        mark[j] = true;
                        System.out.print(j+" ");
                    }
                }
            }
            System.out.println();
            List<String> list = new ArrayList<>();
            list.add(name1);
            list.addAll(emails);
            res.add(list);
        }
        return res;
    }

    @Test
    public void test0() {
        System.out.println("test0");
    }
}
