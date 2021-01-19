import java.util.LinkedList;
import java.util.Queue;
public class Trie {
    private static final int SIZE = 26;
    private TreeNode root;
    private int depth;
    private class TreeNode{
        private TreeNode[] son;
        private int num;
        private char val;
        private boolean isEnd;
        private TreeNode parent;
        TreeNode(){
            son = new TreeNode[SIZE];
            num = 1;
            isEnd = false;
            parent = null;
        }
    }

    public Trie(){
        root = new TreeNode();
        root.val = '0';
        depth = 0;
    }

    public void insert(String str){
        if(str == null || str.length() == 0){
            return;
        }
        TreeNode node = root;
        char[] letters = str.toCharArray();
        for (int i = 0;i < letters.length; i++) {
            int pos = letters[i] - 'a';
            if(node.son[pos] == null){
                node.son[pos] = new TreeNode();
                node.son[pos].parent = node;
                node.son[pos].val = letters[i];//节点不存储字符，即root.val为空
                depth = Math.max(depth,i + 1);
            }else{
                node.son[pos].num++;
            }
            node = node.son[pos];
        }
        node.isEnd = true;//存储最后一个字符的节点为单词的末端
    }

    public boolean has(String str){
        if(str == null || str.length() == 0){
            return false;
        }
        TreeNode node = root;
        char[] lettters = str.toCharArray();
        for(char c: lettters){
            int pos = c - 'a';
            if(node.son[pos] == null){
                return false;
            }else{
                node = node.son[pos];
            }
        }
        return node.isEnd;
    }

    public void preTraverse(){
        preTraverse(root);
        System.out.println();
    }

    private void preTraverse(TreeNode node){

        for(TreeNode child: node.son){
            if(child!= null){
                if(child.isEnd) {
                    System.out.println(child.val);
                }else{
                    System.out.print(child.val);
                }
                preTraverse(child);
            }
        }

    }

    public int countPrefix(String prefix){
        if(prefix == null || prefix.length() == 0){
            return -1;
        }
        TreeNode node = root;
        char[] letters = prefix.toCharArray();
        for(char c: letters){
            int pos = c - 'a';
            if(node.son[pos] == null){
                return 0;
            }else{
                node = node.son[pos];
            }
        }
        return node.num;
    }

    public void hasPrefix(String prefix){
        if(prefix == null || prefix.length() == 0){
            return;
        }
        TreeNode node = root;
        char[] letters = prefix.toCharArray();
        for(char c: letters){
            int pos = c - 'a';
            if(node.son[pos] == null){
                return;
            }else{
                node = node.son[pos];
            }
        }
        preTraverse(node,prefix);
    }

    private void preTraverse(TreeNode node,String prefix){
        if(!node.isEnd){
            for(TreeNode child: node.son){
                if(child != null) {
                    preTraverse(child, prefix + child.val);
                }
            }

        }else {//只有到达末尾的时候进行打印
            System.out.println(prefix);
        }
    }

    /*
    两个问题：
    1、使用字典树对单词进行排序：前序遍历
    2、输出最长公共前缀
    字典树的应用：单词查找，前缀查找，单词排序，计数统计等等功能
    * */

    //树的遍历方法：前中后+深度广度(需要visited数组的辅助，这个数组对于树形结构不好构建)：一共5中遍历方法
//    public void dfs(TreeNode node){
//        if(node == null){
//            return;
//        }
//        if(node.isEnd) {
//            System.out.println(node.val);
//        }else{
//            System.out.print(node.val);
//        }
//        for(TreeNode child: node.son){
//            dfs(child);
//        }
//    }

    public void printOrder(){
        dfs(root,"");
    }

    /*
    * 递归回溯的总结：总共有两种形式：字符串 和 vector，今天只讨论字符串
    * 方式一(没什么用，不直观)：边递归，边输出，例如preTraverse的写法sout完之后再进入循环递归
    * 方式二(常用)：在递归的过程中在递归调用的传递参数时，进行累加在，最后一层递归函数中输出整体路径，
    *        例如dfs的写法，dfs(child,str + node.val);//路径累加，末尾输出if(node.isEnd)sout(str);
    *  再说一下vector的处理：在递归的最后一层if(end)res.push_back(path);
    *  在递归的过程中：path.push_back(ele);dfs(...,path,...);path.pop_back();//回溯操作
    * */


    //深度优先遍历，按字典顺序打印出单词
    private void dfs(TreeNode node,String str){
        if(node == null)return;
        if(node.isEnd){
            System.out.println(str + node.val);
            return;
        }
        for (TreeNode child: node.son) {
            dfs(child,str + (node == root?"":""+node.val));
        }
    }

    public void bfs(){
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            TreeNode node = queue.poll();
            if(node.isEnd){
                System.out.println(getNodePath(node));
            }
            for(TreeNode child: node.son){
                if(child != null){
                    queue.offer(child);
                }
            }
        }
    }


    public int getDepth() {
        return depth;
    }

    /***
     * 从当前字母往上遍历路径
     * @param node
     * @return path
     */
    public String getNodePath(TreeNode node){
        StringBuilder path = new StringBuilder();
        TreeNode p = node;
        while(p != root){
            path.append(p.val);
            p = p.parent;
        }
        return path.reverse().toString();
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        String[] strs = {"banana","band","bee","absolute","acm","band"};
        String[]prefix= {"ba","b","band","abc"};
        for (String str: strs) {
            trie.insert(str);
        }

        System.out.println(trie.has("abc"));
        trie.hasPrefix("b");
        for(String pre: prefix){
            int num = trie.countPrefix(pre);
            System.out.println("前缀：" + pre + "数量为：" + num);
        }
        System.out.println("树的深度："+ trie.getDepth());
        System.out.println("树的深度优先遍历：");//按照字典序进行打印
        trie.printOrder();
        System.out.println("树的广度优先遍历：");//按照单词的长度从小到大进行打印，bfs是无权图寻找最短路径的算法
        trie.bfs();
        System.out.println("hasPrefix：");
        trie.hasPrefix("ba");
    }
}
