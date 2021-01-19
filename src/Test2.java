import java.util.*;
public class Test2 {

    private static void isPrime(int n,List<Integer> list){
        if(n == 0){
            return;
        }
        list.add(1);
        if(n == 1){
            return;
        }
        list.add(n);
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if(n % i == 0){
                if(i == Math.sqrt(n)){
                    list.add(i);
                }else {
                    list.add(i);
                    list.add(n / i);
                }
            }
        }
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        isPrime(96,list);
        list.sort(null);//进行排序
        System.out.println(list);
    }
}
