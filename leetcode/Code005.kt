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
     * 不行 运行到第51/61个例子
     */
    fun maxArea3(height: IntArray): Int {
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

        var rightIndex = right
        var leftIndex = left
        while (true) {
            var oldRight = right
            var oldLeft = left
            rightIndex++
            leftIndex--
            if (rightIndex > height.size - 1 && leftIndex < 0) return large
            //右边
            if (rightIndex < height.size) {
                var leftHeight = height.get(oldLeft)
                println("rightIndex = $rightIndex")
                var rightHeightNew = height.get(rightIndex)
                var hRight = if (rightHeightNew > leftHeight) leftHeight else rightHeightNew
                var areaRight = (rightIndex - oldLeft) * hRight
                if (large < areaRight) {
                    right = rightIndex
                }
            }

            //左边
            if (leftIndex >= 0) {
                var rightHeight = height.get(oldRight)
                var leftHeightNew = height.get(leftIndex)
                var hLeft = if (leftHeightNew > rightHeight) rightHeight else leftHeightNew
                var areaLeft = (oldRight - leftIndex) * hLeft
                if (large < areaLeft) {
                    left = leftIndex
                }
            }

            println("right = $right , left = $left")
            var h = if (height.get(left) > height.get(right)) height.get(right) else height.get(left)
            var areas = (right - left) * h
            if (large < areas) {
                large = areas
            }
        }
    }

    /**
     * 上一种方法不行
     * 第一次 超时 55/61
     * 第二次 通过
     */
    fun maxArea4(height: IntArray): Int {
        var heightSort = IntArray(height.size)
        heightSort = height.copyOf()
        heightSort.sort()

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


        var width = right - left
        while (width < height.size){
            var start = 0
            while ((start + width) < height.size){
                var end = start + width
                var h =  if (height.get(start) > height.get(end)) height.get(end) else height.get(start)
                if (large < h * width) large = h * width
                start ++
            }
            width++
        }
        return large
    }

    /**
     * 别人的解法  实在是精妙，可是还是不知道为什么丢弃了辣么多组合，能确定那些一定更小呢
     */
    fun maxArea(height: IntArray): Int {
        var size = height.size
        var left = 0
        var right = size - 1
        var ans = 0
        while (left < right){
            ans = Math.max(ans,(right - left) * Math.min(height[left], height[right]))
            if (height[left] > height[right]) --right
            else ++left
        }
        return ans
    }
}


fun main(args: Array<String>) {
    //[159,157,139,51,98,71,4,125,48,
    // 125,64,4,105,79,136,169,113,13,
    // 95,88,190,5,148,17,152,20,196,
    // 141,35,42,188,147,199,127,198,
    // 49,150,154,175,199,80,191,3,137,
    // 22,92,58,87,57,153,175,199,110,
    // 75,16,62,96,12,3,83,55,144,30,6,
    // 23,28,56,174,183,183,173,15,126,
    // 128,104,148,172,163,35,181,68,
    // 162,181,179,37,197,193,85,10,
    // 197,169,17,141,199,175,164,180,
    // 183,90,115]
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