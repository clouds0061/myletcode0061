package leetcode

/**
 * 整数转罗马数字
 * 罗马数字包含以下七种字符： I， V， X， L，C，D 和 M。

字符          数值
I             1
V             5
X             10
L             50
C             100
D             500
M             1000

例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。

通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：

I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。
C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。

给你一个整数，将其转为罗马数字

提示：
1 <= num <= 3999
 */
class Code006Int2RomanNum {
    fun intToRoman(num: Int): String {
        var number = num
        if (number == 1) return "I"
        if (number == 3999) return "MMMCMXCIX"
        var stfNum = StringBuffer("")

        var a: Int =
            when (number) {
                in 1..9 -> 1
                in 10..99 -> 10
                in 100..999 -> 100
                else -> 1000
            }

        while (a > 0) {
            var i = number / a
            when (i) {
                in 1..3 -> for (x in 1..i) {
                    when {
                        number in 1..9 -> stfNum.append("I")
                        number in 10..99 -> stfNum.append("X")
                        number in 100..999 -> stfNum.append("C")
                        number >= 1000 -> stfNum.append("M")
                    }

                }
                in 6..8 -> {
                    when {
                        number in 1..9 -> stfNum.append("V")
                        number in 10..99 -> stfNum.append("L")
                        number in 100..999 -> stfNum.append("D")
                    }
                    for (num in 1..i-5){
                        when {
                            number in 1..9 -> stfNum.append("I")
                            number in 10..99 -> stfNum.append("X")
                            number in 100..999 -> stfNum.append("C")
                        }
                    }

                }
                4 -> {
                    when {
                        number in 1..9 -> stfNum.append("IV")
                        number in 10..99 -> stfNum.append("XL")
                        number in 100..999 -> stfNum.append("CD")
                    }
                }
                5 -> {
                    when {
                        number in 1..9 -> stfNum.append("V")
                        number in 10..99 -> stfNum.append("L")
                        number in 100..999 -> stfNum.append("D")
                    }
                }
                9 -> {
                    when {
                        number in 1..9 -> stfNum.append("IX")
                        number in 10..99 -> stfNum.append("XC")
                        number in 100..999 -> stfNum.append("CM")
                    }
                }
            }
            number %= a
            a /= 10
        }

        return stfNum.toString()
    }
}

fun main() {
    var code = Code006Int2RomanNum()
//    println("1994 = ${code.intToRoman(1994)}")
//    println("3 = ${code.intToRoman(3)}")
//    println("58 = ${code.intToRoman(58)}")
//    println("9 = ${code.intToRoman(9)}")
    println("1000 = ${code.intToRoman(1000)}")
}