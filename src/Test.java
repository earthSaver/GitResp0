public class Test {
    private static  Boolean func(){
        System.out.print("a");
        return false;
    }

    private static Boolean isPrime(int n){
        if(n==0||n==1){
            return false;
        }

        for (int i = 2; i <= Math.sqrt(n); i++) {//平方根也要算在内，以平方根为分界点
            if(n % i == 0){
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        if(func()){
            System.out.println("a");
        }else{
            System.out.println("b");
        }

        int count = 0;
        System.out.println("100以内的素数：");
        for (int i = 0; i <= 100 ; i++) {
            if(isPrime(i)) {
                System.out.print(i + " ");
                count++;
            }
        }
        System.out.println("\n"+"素数个数为：" + count);
        //2、3、5、7、11、13、17、19、23、29、31、37、41、43、47、53、59、61、67、71、73、79、83、89、97。
    }
}
