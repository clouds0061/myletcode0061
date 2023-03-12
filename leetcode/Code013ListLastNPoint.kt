package leetcode

/**
 * 19. 删除链表的倒数第 N 个结点
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 * 示例 1：
 * 输入：head = [1,2,3,4,5], n = 2
 * 输出：[1,2,3,5]
 * 示例 2：
 * 输入：head = [1], n = 1
 * 输出：[]
 * 示例 3：
 * 输入：head = [1,2], n = 1
 * 输出：[1]
 * 提示：
 * 链表中结点的数目为 sz
 * 1 <= sz <= 30
 * 0 <= Node.val <= 100
 * 1 <= n <= sz
 * 进阶：你能尝试使用一趟扫描实现吗？
 */
class Code013ListLastNPoint {
    /**
     * 一次扫描，每个节点一次放入一个list中，然后判断移除那个节点后返回,效率一般
     */
    fun removeNthFromEnd(head: ListNode?, n: Int): ListNode? {
        var listNode = ArrayList<ListNode>()
        if (head == null) return head
        var func: ListNode = head
        if (head != null) {
            while (func.next != null) {
                listNode.add(func)
                func = func.next!!
            }
            listNode.add(func)
        }
        var index = listNode.size - n
        if (index == listNode.size - 1 && listNode.size > 1)
            listNode.get(index - 1).next = null
        else if (index == 0){
            return head.next
        }
        else{
            listNode.get(index - 1).next = listNode.get(index + 1)
        }
        return head
    }


    /**
     * 动态对话
     */
    fun dynamic(head: ListNode?, n: Int) {
        var dp = Array(n) {}
        //1.定义dp[i] 删除第i个节点后的链表
        //2.初始值
        //3.规律
        //删除第i个节点就是dp[i - 1].next = dp[i].next
    }
}

class ListNode(var `val`: Int) {
    var next: ListNode? = null
}

fun main() {

}