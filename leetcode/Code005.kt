package leetcode

/**
 * 给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。

找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。

返回容器可以储存的最大水量。

说明：你不能倾斜容器
 */
class Code005 {

    fun maxArea2(height: ArrayList<Int>): Int {
        var large = 0
        for (i in height.indices) {
            var heightI = height[i]
            for (j in i + 1 until height.size) {
                var heightJ = height[j]
                var width = j - i
                var h = if (heightI > heightJ) heightJ else heightI
                if (width * h > large) large = width * h
            }
        }
        return large
    }

    /**
     * 冒泡法  超出时间限制了
     */
    fun maxArea1(height: IntArray): Int {
        var large = 0
        for (i in height.indices) {
            var heightI = height[i]
            for (j in i + 1 until height.size) {
                var heightJ = height[j]
                var width = j - i
                var h = if (heightI > heightJ) heightJ else heightI
                if (width * h > large) large = width * h
            }
        }
        return large
    }

    /**
     * 第二种，找到两个最高的，然后顶下最大的水池
     * 依次从最高的往下排
     */
    fun maxArea(height: IntArray): Int {
        var heightSort = IntArray(height.size)
        heightSort = height.copyOf()
        heightSort.sort()

        for (i in heightSort.indices)
            println("heightSort = ${heightSort.get(i)}")
        var rightHeight = heightSort.get(heightSort.size - 1)
        var leftHeight = heightSort.get(heightSort.size - 2)
        var large =
            Math.abs(height.lastIndexOf(rightHeight) - height.indexOf(leftHeight)) * leftHeight

        var right = 0
        var left = 0
        if (height.lastIndexOf(rightHeight) > height.indexOf(leftHeight)) {
            right = height.lastIndexOf(rightHeight)
            left = height.indexOf(leftHeight)
        } else {
            right = height.indexOf(leftHeight)
            left = height.lastIndexOf(rightHeight)
        }
        println("right = $right ， left = $left")
        var rightIndex = right
        var leftIndex = left
        while (true) {
            rightIndex++
            leftIndex--
            if (rightIndex > height.size - 1 && leftIndex < 0) return large
            //右边
            if (rightIndex < height.size) {
                var leftHeight = height.get(left)
                println("rightIndex = $rightIndex")
                var rightHeightNew = height.get(rightIndex)
                var hRight = if (rightHeightNew > leftHeight) leftHeight else rightHeightNew
                var areaRight = (rightIndex - left) * hRight
                if (large < areaRight) {
                    large = areaRight
                    right = rightIndex
                }
            }

            //左边
            if (leftIndex >= 0) {
                var rightHeight = height.get(right)
                var leftHeightNew = height.get(leftIndex)
                var hLeft = if (leftHeightNew > rightHeight) rightHeight else leftHeightNew
                var areaLeft = (right - leftIndex) * hLeft
                if (large < areaLeft) {
                    large = areaLeft
                    left = leftIndex
                }
            }
        }
    }
}


fun main(args: Array<String>) {
    var code = Code005()
    var intArray = IntArray(10)
    intArray.set(0, 8)
    intArray.set(1, 10)
    intArray.set(2, 14)
    intArray.set(3, 0)
    intArray.set(4, 13)
    intArray.set(5, 10)
    intArray.set(6, 9)
    intArray.set(7, 9)
    intArray.set(8, 11)
    intArray.set(9, 11)
    println("水池最大 = ${code.maxArea(intArray)}")
}