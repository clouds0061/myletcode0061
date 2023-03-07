package leetcode.code

import kotlin.math.min


/**
 * 动态规划
 */
class DynamicProgram {

    /**
     * 案例 1：编辑距离
     * 这次给的这道题比上面的难一些，在 leetcdoe 的定位是 hard 级别。好像是 leetcode 的第 72 号题。
     * 问题描述
     * 给定两个单词 word1 和 word2，计算出将 word1 转换成 word2 所使用的最少操作数 。你可以对一个单词进行如下三种操作:
     * 插入一个字符
     * 删除一个字符
     * 替换一个字符
     *
     *示例 1：
     * 输入：word1 = "horse", word2 = "ros"
     * 输出：3
     * 解释：
     * horse -> rorse (将 'h' 替换为 'r')
     * rorse -> rose (删除 'r')
     * rose -> ros (删除 'e')
     * 示例 2：
     * 输入：word1 = "intention", word2 = "execution"
     * 输出：5
     * 解释：
     * intention -> inention (删除 't')
     * inention -> enention (将 'i' 替换为 'e')
     * enention -> exention (将 'n' 替换为 'x')
     * exention -> exection (将 'n' 替换为 'c')
     * exection -> execution (插入 'u')
     */
    fun world1ToWorld2(world1: String, world2: String) : Int{
        //1.定义二位数组: 数组的定义为world1长度为i，world2长度为j时，需要转化的操作数为dp[i][j]
        var dp = Array(world1.length + 1) { IntArray(world2.length + 1) }
        //2.定义初始值
        //如果world1长度为0，明显不能带入dp[i - 1][j]
        //world2长度为0也一样
        //所以
        //world1长度为0的时候，dp[i][0]的操作就是前面一步的操作+1;//也就剩下删除操作了
        dp[0][0] = 0
        for (i in 1..world1.length) {
            dp[i][0] = dp[i - 1][0] + 1
        }
        //world2长度为0
        for (i in 1..world2.length) {
            if (i == 0) continue
            dp[0][i] = dp[0][i - 1] + 1
        }
        //3.定义关系  这里是最难的部分
        //如果world1[i - 1] == world2[j - 1],即当前有相等的字符，那么dp[i][j]即讲长度为i的world1转化为长度为j的world2的时候不许要考虑最后这个字符，那么dp[i][j] == dp[i - 1][j - 1],只需要考虑前面的次数，因为最后一个相等，不用操作
        //如果world1[i - 1] ！= world2[j - 1]，那么在达成最终结果dp[i][j]时，前面一步可能有三种操作:
        //1.插入字符;即把i长度的world1变成和j-1长度的world2相同,然后在world1末尾插入一个字符得到最终结果;即dp[i][j] = dp[i][j - 1] + 1
        //2.删除字符;即把i - 1长度的world1变成和j长度的world2相同，然后删除world1末尾的字符得到最终结果;即dp[i][j] = dp[i - 1][j] + 1
        //3.替换字符，即dp[i][j] = dp[i - 1][j - 1] + 1;也就是在吧i-1长度的world1变成j - 1长度的world2的基础上，做一次字符替换;
        for (i in 1..world1.length) {
            for (j in 1..world2.length) {
                if (world1[i - 1] == world2[j - 1]) dp[i][j] = dp[i - 1][j - 1]
                else {
                    dp[i][j] = min(min((dp[i][j - 1] + 1), (dp[i - 1][j] + 1)),(dp[i - 1][j - 1] + 1))
                }
            }
        }

        return dp[world1.length][world2.length]
    }
}


fun main() {
    var code = DynamicProgram()
    println("转换所需次数:${code.world1ToWorld2("horse","ros")}")
}