package leetcode.code

/***
 * 回溯算法
 *
 */
class BackTrack {


    /**
     * 1.八皇后问题:在8×8格的国际象棋上摆放8个皇后，使其不能互相攻击，即任意两个皇后都不能处于同一行、同一列或同一斜线上，问有多少种摆法
     *  仔细一分析，这就是遍历了所有方案，再遍历的同时直接找出合适的方案++，如果碰到不合适的直接就跳过了
     *
     */
    var count = 0//记录可能的情况数量
    var chesses = Array(8) { IntArray(8) }//8x8的二维数组，表示棋子 默认为0 表示没放棋子
    fun eightQueen(row: Int) {
        if (row > 7) {//超过最后一行,说明说有可能都筛选完毕了
            printResult()//打印当前情况
            count++
            return
        }
        //遍历列数据
        for (col in 0 until 8) {
            if (isLegal(row, col)) {
                chesses[row][col] = 1
                eightQueen(row + 1)
                chesses[row][col] = 0
            }
        }
    }

    //判断当前行和列放置Queen是否合法
    fun isLegal(row: Int, col: Int): Boolean {
        //判断相同列是否已经存在Queen了
        for (i in 0 until 8) {
            if (chesses[i][col] == 1) return false
        }

        //判断相同行是否已经存在Queen了
        for (i in 0 until 8) {
            if (chesses[row][i] == 1) return false
        }

        var i = row
        var j = col
        //判断左上,此时不用检查坐下，因为坐下还没开始放置Queen
        while (i >= 0 && j >= 0) {
            if (chesses[i][j] == 1) return false
            i--
            j--
        }

        i = row
        j = col
        //判断右上,此时不用检查右下，因为右下还没开始放置Queen
        while (i >= 0 && j <= 7) {
            if (chesses[i][j] == 1) return false
            i--
            j++
        }


        return true
    }

    fun printResult() {
        println("八皇后的第${count + 1} 种情况:")
        var rowString = StringBuffer("")
        for (row in 0 until 8) {
            rowString.setLength(0)
            for (col in 0 until 8) {
                if (chesses[row][col] == 1)
                    rowString.append("Q")
                else
                    rowString.append("-")
            }
            println(rowString.toString())
        }
    }
}


fun main() {
    var code = BackTrack()
    code.eightQueen(0)
    println("八皇后问题有${code.count}种解法")
}