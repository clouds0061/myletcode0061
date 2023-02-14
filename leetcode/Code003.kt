package leetcode

/**
 * 给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。

回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。

例如，121 是回文，而 123 不是

来源：力扣（LeetCode）
链接：https://leetcode.cn/problems/palindrome-number
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
class Code003 {

    /**
     *  分正负
     */
    fun isPalindrome1(x: Int): Boolean {
        if (x < 0) return false //负数不可能是回文数
        var intString = x.toString()
        var start = 0
        var end = intString.length
        while (start < end) {
            if (intString.substring(start, start + 1) != intString.substring(
                    end - 1,
                    end
                )
            ) return false

            start++
            end--
        }
        return true
    }


    fun isPalindrome2(x: Int): Boolean {
        if (x < 0) return false //负数不可能是回文数
        var input = x
        var y = 0
        while (input > 0) {
            var i = input % 10
            input /= 10
            if (input != 0)
                y = y * 10 + i * 10
            else
                y += i
        }
        if (x == y) return true
        return false
    }

    /**
     * 看的别人的接单，思路和我一样，但是代码更简洁更高效，是最优秀的答案
     */
    fun isPalindrome(x: Int): Boolean {
        if (x < 0) return false
        var temp = 0
        var input = x
        while (input != 0) {
            temp = temp * 10 + input % 10
            input /= 10
        }
        return temp == x
    }
}


fun main(args: Array<String>) {
    println(Code003().isPalindrome(123))
    println("==================")
    println(Code003().isPalindrome(-123))
    println("==================")
    println(Code003().isPalindrome(1))
    println("==================")
    println(Code003().isPalindrome(11))
    println("==================")
    println(Code003().isPalindrome(31213))
    println("==================")
    println(Code003().isPalindrome(-121))


}