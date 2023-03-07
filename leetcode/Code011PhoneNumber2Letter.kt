package leetcode

/**
 * 17. 电话号码的字母组合

给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。

给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。



示例 1：

输入：digits = "23"
输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]

示例 2：

输入：digits = ""
输出：[]

示例 3：

输入：digits = "2"
输出：["a","b","c"]



提示：

0 <= digits.length <= 4
digits[i] 是范围 ['2', '9'] 的一个数字。


 */
class Code011PhoneNumber2Letter {

    /**
     * 算法一 没有一点点思路，只能想到枚举算法，几个for循环一整就ojbk了
     *
     */
    var list = ArrayList<String>()
    var letterMap = mapOf<String, String>(
        "2" to "abc",
        "3" to "def",
        "4" to "ghi",
        "5" to "jkl",
        "6" to "mno",
        "7" to "pqrs",
        "8" to "tuv",
        "9" to "wxyz",
    )

    var inputLen = 0
    fun letterCombinations(digits: String): List<String> {
        if (digits.isEmpty()) return list as List<String>
        var stf = StringBuffer("")

        inputLen = digits.length
        backTrack(0, digits, stf)

        for (i in list.indices) {
            println("${list[i].toString()}")
        }

        return list as List<String>
    }

    /**
     * 尝试使用回溯算法  写了半天一看，感觉严格来说不算回溯，应该是递归，因为这里判断只有所有字母都组合起来了才能做判断
     */
    fun backTrack(i: Int, digits: String, stf: StringBuffer) {
        if (i >= inputLen) {
            if (!list.contains(stf.toString())) list.add(stf.toString())
            return
        }

        var letterString = letterMap[digits[i].toString()]
        for (j in 0 until (letterString?.length ?: 0)) {
            stf.append(letterString?.get(j)?.toString() ?: "")
            backTrack(i + 1, digits, stf)
            stf.replace(stf.length - 1, stf.length, "")//最后一个替换为""
        }

    }

}

fun main() {
    var code = Code011PhoneNumber2Letter()
//    println(code.letterCombinations("223"))

    println(code.letterCombinations("233"))
}