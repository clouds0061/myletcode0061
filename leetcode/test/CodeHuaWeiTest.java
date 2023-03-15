package leetcode.test;

import java.util.Scanner;

/**
 * 华为OD 机考 题目。卧槽还不如自己做，这些外包HR真的坑爹啊;
 */
public class CodeHuaWeiTest {

    public static void main(String[] args) {
        getMin();

//        userSetZero();
    }

    public static void getMin() {
        //定义输入得数据
        String[] strings = new String[]{};
        //使用java得输入函数
        Scanner in = new Scanner(System.in);
        //定义分隔
        in.useDelimiter("\n");//按回车分隔
        //分隔获取输入得数据
        strings = in.next().split(" ");

        if (strings == null || strings.length == 0) System.out.println("");//输入为空 那么返回也为空

        //定义dp[i] 表示 i个字符的组成的最小值的string
        String[] dp = new String[strings.length + 1];
        //初始化空字符组成的最小值也是“”
        dp[0] = "";
        //规律:
        //i个字符组成的最小值，一定是i-1个字符的最小值，然后和最后一个字符再去组合，然后比较
        for (int i = 1; i <= strings.length; i++) {
            String b;
            String a = dp[i - 1] + strings[i - 1];//a值表示i-1的最小值在前，最后一个字符在后的组合比如  1121 + 01
            if (Integer.valueOf(strings[i - 1]) == 0)//如果最后一个字符取值是0，那么它在前的清空就是dp[i-1]
                b = dp[i - 1];
            else
                b = strings[i - 1] + dp[i - 1];

            //如果b为""表示前面的都是0的组合
            if (b == "") {
                if (Integer.valueOf(a) == 0) dp[i] = "";//同时a也是0.没得说继续""
                else dp[i] = a;//否则为a
            } else {//不为"" 那么就需要比较大小来定了
                if (Integer.valueOf(a) <= Integer.valueOf(b)) dp[i] = a;
                else dp[i] = b;
            }

        }
        if (dp[strings.length] == "")
            System.out.println("0");
        else
            System.out.println(Integer.valueOf(dp[strings.length]));
    }


    static int result = 0;
    static int count = 0;
    static int indexI = 0;
    static int indexJ = 0;
    static int n, m;

    public static void userSetZero() {
        int[][] inputs;
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        m = in.nextInt();
        inputs = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                inputs[i][j] = in.nextInt();
            }
        }
        //new int[][]{{1, 1, 1}, {0, 1, 0}, {0, 1, 0}}
        // 1 1 1 1
        // 0 0 0 1
        // 1 1 0 0
        // 1 1 0 1
        setZero2(inputs);
        System.out.println("需要点击" + result + "次");
    }

    //1 1 1
    //0 1 0
    //0 1 0
    public static void setZero(int[][] inputs) {
        count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int ij = inputs[i][j];
                int countIJ = 0;
                if (inputs[i][j] == 1) {
                    countIJ++;
                    //正上
                    if (i - 1 >= 0 && inputs[i - 1][j] == 1) countIJ++;
                    //正右
                    if (j + 1 < m && inputs[i][j + 1] == 1) countIJ++;
                    //正下
                    if (i + 1 < n && inputs[i + 1][j] == 1) countIJ++;
                    //正左
                    if (j - 1 >= 0 && inputs[i][j - 1] == 1) countIJ++;
                    //坐上
                    if (i - 1 >= 0 && j - 1 >= 0 && inputs[i - 1][j - 1] == 1)
                        countIJ++;
                    //右上
                    if (i - 1 >= 0 && j + 1 < m && inputs[i - 1][j + 1] == 1)
                        countIJ++;
                    //右下
                    if (i + 1 < n && j + 1 < m && inputs[i + 1][j + 1] == 1)
                        countIJ++;
                    //左下
                    if (i + 1 < n && j - 1 >= 0 && inputs[i + 1][j - 1] == 1)
                        countIJ++;

                }
                if (count < countIJ) {
                    count = countIJ;
                    indexI = i;
                    indexJ = j;
                }
            }
        }

        //找到了周围1最多的点，把它和它周围的点都设置为0
        if (count != 0) {
            setPointZero(indexI, indexJ, inputs);
            result++;
        }
        if (count == 0) return;
        setZero(inputs);
    }

    //递归调用把[x,y]点和它周围和它周围的周围的1设置为0
    public static void setPointZero(int x, int y, int[][] inputs) {
        inputs[x][y] = 0;
        //正上
        if (x - 1 >= 0 && inputs[x - 1][y] == 1) setPointZero(x - 1, y, inputs);
        //正右
        if (y + 1 < m && inputs[x][y + 1] == 1) setPointZero(x, y + 1, inputs);
        //正下
        if (x + 1 < n && inputs[x + 1][y] == 1) setPointZero(x + 1, y, inputs);
        //正左
        if (y - 1 >= 0 && inputs[x][y - 1] == 1) setPointZero(x, y - 1, inputs);
        //坐上
        if (x - 1 >= 0 && y - 1 >= 0 && inputs[x - 1][y - 1] == 1)
            setPointZero(x - 1, y - 1, inputs);
        //右上
        if (x - 1 >= 0 && y + 1 < m && inputs[x - 1][y + 1] == 1)
            setPointZero(x - 1, y + 1, inputs);
        //右下
        if (x + 1 < n && y + 1 < m && inputs[x + 1][y + 1] == 1)
            setPointZero(x + 1, y + 1, inputs);
        //左下
        if (x + 1 < n && y - 1 >= 0 && inputs[x + 1][y - 1] == 1)
            setPointZero(x + 1, y - 1, inputs);
    }

    /**
     * 卧槽 根本不需要判断哪里的1最多，直接碰到1递归归零，只要是连着的肯定都会归零;然后递归调用;
     * @param inputs
     */
    public static void setZero2(int[][] inputs){
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (inputs[i][j] == 1) {
                    setPointZero(i, j, inputs);
                    result++;
//                    setZero(inputs);
                }
            }
        }
    }
}

