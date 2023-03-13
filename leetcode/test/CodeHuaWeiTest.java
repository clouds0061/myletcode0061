package leetcode.test;

/**
 * 华为OD 机考 题目。卧槽还不如自己做，这些外包HR真的坑爹啊;
 */
public class CodeHuaWeiTest {

    public static void main(String[] args) {
//        String [] inputs = new String[] {"01","02"};
        String[] inputs = new String[]{"01", "02", "11"};
        getMin(inputs);

        userSetZero();
    }

    public static void getMin(String[] strings) {
        //定义dp[i] 表示 i个字符的组成的最小值的string
        String[] dp = new String[strings.length + 1];
        //
        dp[0] = "";
        //规律
        for (int i = 1; i <= strings.length; i++) {
            String a = dp[i - 1] + strings[i - 1];
            String b = strings[i - 1] + dp[i - 1];
            if (Integer.valueOf(a) <= Integer.valueOf(b)) dp[i] = a;
            else dp[i] = b;
        }
        System.out.println(Integer.valueOf(dp[strings.length]));
    }


    static int result = 0;
    static int count = 0;
    static int indexI = 0;
    static int indexJ = 9;
    static int n, m;

    public static void userSetZero() {
        n = 3;
        m = 3;
        setZero(new int[][]{{1, 1, 1}, {0, 1, 0}, {0, 1, 0}});
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
}

