import java.io.*;
public class MyBitMap {
    public static void main(String[] args) {
        int[] arr = {1, 7, 3, 0, 10, 6, 6, 9, 1};
        System.out.println(bitmapHasCopy(arr) ? "存在重复元素" : "无重复元素");
        bitmapSort(arr);
        System.out.println("打印排序好的数组：");
        for(int x: arr){
            System.out.print(x+", ");
        }
        System.out.println();
    }

    private static boolean bitmapHasCopy(int[] arr){
        assert(arr.length > 0);
        int max = arr[0];
        int min = arr[0];
        for(int x: arr){
            if(max < x){
                max = x;
            }
            if(min > x){
                min = x;
            }
        }
        //为什么要加1，因为相当于个数
        int[] bitmap = new int[max - min + 1];//使用方法和map一毛一样
        for (int x: arr) {
            if(bitmap[x] > 0){
                return true;
            }else{
                bitmap[x] = 1;
            }
        }
        return false;
    }

    //位图排序就是计数排序：应用场景：元素数量arr.length远大于max-min
    private static void bitmapSort(int[] arr){
        assert(arr.length > 0);
        //先找出数组的最值
        int max = arr[0];
        int min = arr[0];
        for(int x: arr){
            if(max < x){
                max = x;
            }
            if(min > x){
                min = x;
            }
        }
        int[] bitmap = new int[max - min + 1];//下标就是数组的值，bitmap[arr[i]]=arr[i]的个数
        for(int x: arr){
            bitmap[x-min]++;
        }
        System.out.println("打印bitmap");
        for(int x: bitmap){
            System.out.print(x+", ");
        }
        System.out.println();
        //利用位图进行排序
        int index = 0;//bitmap的index
        //遍历bitmap进行排序，有点像计数排序
        for (int i = 0; i < bitmap.length; i++) {
            while(bitmap[i]>0){//使用while处理重复元素
                assert(index < arr.length);
                arr[index] = i + min;
                index++;
                bitmap[i]--;
            }
        }
    }

}
