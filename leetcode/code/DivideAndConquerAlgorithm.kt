package leetcode.code

/**
 * 分治算法
 *
 */
class DivideAndConquerAlgorithm {
    /**
     * 1.二分搜索
     */
    fun twoPartSearch(list: IntArray, target: Int) {
        list.sort()
        var left = 0
        var right = list.size - 1
        while (left <= right) {
            var middle = if ((left + right) % 2 == 0) (left + right) / 2 else (left + right + 1) / 2
            when {
                target == list[middle] -> {
                    println("list中存在元素$target")
                    return
                }
                target > list[middle] -> left = middle + 1
                target < list[middle] -> right = middle - 1
            }
        }
        println("list中不存在元素$target")
    }

    /**
     * 递归调用
     */
    fun twoPartSearch2(list: IntArray, target: Int, left: Int, right: Int) {
        list.sort()
        if (left > right) {
            println("list中不存在元素$target")
            return
        }
        var middle = if ((left + right) % 2 == 0) (left + right) / 2 else (left + right + 1) / 2
        when {
            target == list[middle] -> {
                println("list中存在元素$target")
                return
            }
            target > list[middle] -> twoPartSearch2(list, target, middle + 1, right)
            target < list[middle] -> twoPartSearch2(list, target, left, middle - 1)
        }
    }

    /**
     * 归并排序
     */
    fun mergeSort(listArr: IntArray, listTemp: IntArray, start: Int, end: Int) {
        if (end > start) {
            var middle: Int = (start + end) / 2
            mergeSort(listTemp, listArr, start, middle)
            mergeSort(listTemp, listArr, middle + 1, end)
            merge(listArr, listTemp, start, end, middle)
        }
    }

    private fun merge(listArr: IntArray, listTemp: IntArray, start: Int, end: Int, middle: Int) {
        var leftStart = start
        var rightStart = middle + 1
        for (k in start..end) {
            if (leftStart > middle) {
                listArr[k] = listTemp[rightStart++]
            } else if (rightStart > end) {
                listArr[k] = listTemp[leftStart++]
            } else if (listTemp[rightStart] < listTemp[leftStart]) listArr[k] =
                listTemp[rightStart++]
            else listArr[k] = listTemp[leftStart++]
        }
    }
}

fun main() {
    var code = DivideAndConquerAlgorithm()
    var list = IntArray(7)
    list[0] = 22
    list[1] = 15
    list[2] = 2
    list[3] = 7
    list[4] = 4
    list[5] = -21
    list[6] = 11
//    code.twoPartSearch(list,4)

//    code.twoPartSearch2(list,4,0,list.size)

    code.mergeSort(list, list.clone(), 0, list.size - 1)
    println("排序后的结果")
    for (i in list.indices) {
        println("${list[i]}")
    }
}