package leetcode.code

/**
 * 递归算法 例子
 */
class RecursiveAlgorithm {
    /**
     * 阶乘
     * 可以分解成----当前输入*(当前输入 - 1)!
     */
    fun factorial(input: Int): Int {
        if (input == 1) return 1
        return input * factorial(input - 1);
    }

    /**
     * 斐波纳契数列  又称为黄金分割数列: 1,1,2,3,5,8,13...
     * 满足fn = (n+1) + f(n-2) (n >= 2, n为正整数)
     *
     */
    fun fibonacciSequence(n: Int): Int {
        if (n == 2 || n == 1) return 1
        return (n + 1) + fibonacciSequence(n - 2)
    }

    /**
     * 模拟步骤
     *   -
     *  ---
     * -----
     *   A     B      C
     *
     * 编程实现把 A 的 n 个盘子移动到 C（盘子编号是 [1, n] ）
     * 每次只能移动1个盘子
     * 大盘子只能放在小盘子下面
     * @param n 盘子数量
     * 这里可以分解为
     * 1.把(n-1)的盘子移动到中间的B，然后将最下面的n盘子移动到C
     * 2.把(n-1)的盘子从b到c
     */
    fun hanoi(n: Int, a: String, b: String, c: String) {
        if (n == 1) {
            move(n,a,c)
            return
        }
        hanoi(n-1,a,c,b)//n-1的元素移动到B
        move(n,a,c)//将最下面的从a移动到c
        hanoi(n-1,b,a,c)//把n-1的元素 从b移动到c
    }

    fun move(n: Int,from:String,to:String) {
        println("将第${n}个盘子，从${from}移动到${to}")
    }


}

fun main() {
    var recursiveAlgorithm = RecursiveAlgorithm()
//    println("5的阶乘 = ${recursiveAlgorithm.factorial(5)}")
//
//    println("斐波纳契数列的第6个数 = ${recursiveAlgorithm.fibonacciSequence(6)}")

    println("汉诺塔调拨3个盘子到C的步骤:")
    recursiveAlgorithm.hanoi(3,"A","B","C")
}