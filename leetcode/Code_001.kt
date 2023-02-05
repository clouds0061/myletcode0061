package leetcode

/**
 * 整数反转
 * 给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
 * 如果反转后整数超过 32 位的有符号整数的范围 [−231,  231 − 1] ，就返回 0。
 * 假设环境不允许存储 64 位整数（有符号或无符号）。
 */
class Code_001 {
    /**
     * Kotlin 的基本数值类型包括 Byte、Short、Int、Long、Float、Double 等。不同于 Java 的是，字符不属于数值类型，是一个独立的数据类型。
    类型 	位宽度
    Double 	64
    Float 	32
    Long 	64
    Int 	32
    Short 	16
    Byte 	8
     */

    //第一种思路，转变成String for循环一个一个的替换
    fun reversal(x: Int): Int {
        var i = x
        if (x > 7_463_847_412 || x < -8_463_847_412) return 0// 做个小小的优化
        var maxArrayPositive = listOf<Int>(7, 4, 6, 3, 8, 4, 7, 4, 1, 2)
        var maxArrayPNegative = listOf<Int>(8, 4, 6, 3, 8, 4, 7, 4, 1, 2)
        var max = 2_147_483_647
        var min = -2_147_483_648
        if (x == min) return 0
        //记录正负
        var positive = i >= 0
        if (!positive) i = Math.abs(i)

        var str: String = i.toString();
        var stf = StringBuffer(str)

        var len = stf.length //字符串长度

        var start = 0
        var end = len - 1
        var flag = true
        do {
            var old_end = stf.get(end)
            var old_start = stf.get(start)

            //-2147483412
            if (flag && len == 10) {
                when {
                    old_end.toString().toInt() > maxArrayPositive.get(end) -> return 0
                    old_end.toString().toInt() < maxArrayPositive.get(end) -> flag = false
                }
            }

            stf.replace(start, start + 1, old_end.toString())
            stf.replace(end, end + 1, old_start.toString())
            start++
            end--
        } while (start < end)

        var ret = stf.toString().toLong()
        when {
            ret > max || ret < min -> return 0
            else -> if (positive) return ret.toInt() else return -ret.toInt()
        }
    }


    //上一种思路其实麻烦了，其实从后往前输出就完事了,不用一个个替换
    //这个并没有更省时间 耗时更多了
    fun reversal2(x: Int): Int {
        var i = x
        var max = 2_147_483_647
        var min = -2_147_483_648
        if (x == min) return 0
        //记录正负
        var positive = i >= 0
        if (!positive) i = Math.abs(i)

        var str = i.toString()
        var end = str.length - 1
        var stf = StringBuffer()
        for (index in str.indices) {
            stf.append(str.get(end - index))
        }
        var ret = stf.toString().toLong()
        when {
            ret > max || ret < min -> return 0
            else -> if (positive) return ret.toInt() else return -ret.toInt()
        }
        return 0
    }


    //想不到更好的方法了
    fun reversal3(x: Int): Int {

        return 0
    }

}


fun main(args: Array<String>) {
    //112455 反转后应该是 554211
    var rev = Code_001().reversal(2_147_483_647)
    var rev2 = Code_001().reversal(2_111_111_002)
    var rev3 = Code_001().reversal(-1)
    var rev4 = Code_001().reversal(-2147483412)
    println(rev)
    println(rev2)
    println(rev3)
    println(rev4)
}