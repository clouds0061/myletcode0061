package leetcode

class Test {

    //表示以 a 开头的字符串
    fun match1(string : String) : Boolean{
        return string.matches("^a.*".toRegex())
    }

    //表示以 s 结尾的字符串
    fun match2(string : String) : Boolean{
        return string.matches(".*s$".toRegex())
    }

    //表示单个字符匹配[]中的
    fun match3(string : String) : Boolean{
        return string.matches("[132abc]".toRegex())
    }
    //表示单个字符匹配排除[]中的
    fun match4(string : String) : Boolean{
        return string.matches("[^132abc]".toRegex())
    }

    //.表示匹配任意单个字符
    fun match5(string : String) : Boolean{
        return string.matches(".".toRegex())
    }

 //表示匹配0个或者任意a
    fun match6(string : String) : Boolean{
        return string.matches("a*".toRegex())
    }

    //表示匹配1个或者任意a
    fun match7(string : String) : Boolean{
        return string.matches("a+".toRegex())
    }


    //表示a重复出现2-5次
    fun match8(string : String) : Boolean{
        return string.matches("a{2,5}".toRegex())
    }

    //表示axxxc的结构。其中xxx可以是任意字符，也可以是空字符""
    fun match9(string : String) : Boolean{
        return string.matches("(a.*c){3}".toRegex())
    }

    //手机号码 138-1277-0000
    fun match10(string : String) : Boolean{
        return string.matches("1[3-9][0-9]{9}".toRegex())
    }

    //省份证匹配
    fun match11(string : String) : Boolean{
        //^(\d{6}) 表示6个数字开头
        //(19|2[0-3]) 表示年份前面两个数 19或者 20,21,22,23
        // \\d{2} 表示两为数的年份后两位
        //(0[1-9]|1[0-2]) 表示月份 0开头的有01-09   或者 1开头的10-12
        //(0[1-9]|1[0-9]|2[0-9]|3[0-1]) 表示日期 0开头的01-09 1开头的10-19 2开头20-29 3开头30-31
        //(\d{3})  表示三个数字
        //(\d|X|x)?$  表示以数字或者X或者x结尾 ? 表示这个数也可以没有
        return string.matches("^(\\d{6})(19|2[0-3])\\d{2}(0[1-9]|1[0-2])(0[1-9]|1[0-9]|2[0-9]|3[0-1])(\\d{3})(\\d|X|x)?$".toRegex())
    }
}


fun main() {
    var test = Test()
    println("420528199206140000 is id card is ${test.match11("420528199206140000")}")
    println("4205281992061400001 is id card is ${test.match11("4205281992061400001")}")
    println("42052819920614000x is id card is ${test.match11("42052819920614000x")}")
    println("42052819920614000X is id card is ${test.match11("42052819920614000X")}")
    println("420528189206140000 is id card is ${test.match11("420528189206140000")}")



//    println(test.match1("a is b"))
//    println(test.match1("aPP"))
//    println(test.match1("a____"))
//    println(test.match1(""))
//    println(test.match1("ca"))


//    println(test.match2("a is bs"))
//    println(test.match2("aPP"))
//    println(test.match2("a____s"))

//    println(test.match3("1"))
//    println(test.match3("a"))
//    println(test.match3("ss"))
//    println(test.match3("c"))
//    println(test.match3("123a"))

//    println(test.match4("1"))
//    println(test.match4("a"))
//    println(test.match4("5"))
//    println(test.match4("c"))
//    println(test.match4("123a"))

//
//    println(test.match5("1"))
//    println(test.match5("a"))
//    println(test.match5("_"))
//    println(test.match5("*"))
//    println(test.match5("A"))


//    println(test.match6("a"))
//    println(test.match6("aaaa"))
//    println(test.match6(""))
//    println(test.match6("s"))
//    println(test.match6("1"))


//    println(test.match7("a"))
//    println(test.match7("aaaa"))
//    println(test.match7(""))
//    println(test.match7("s"))
//    println(test.match7("1"))

//    println(test.match8("aaa"))
//    println(test.match8("aaaa"))
//    println(test.match8("aaaaa"))
//    println(test.match8("aaaaaaa"))
//    println(test.match8("a"))
//    println(test.match8(""))

//    println("acacac = ${test.match9("acacac")}")
//    println("aaaacaca___c = ${test.match9("aaaacaca___c")}")
//    println("bc = ${test.match9("bc")}")
//    println("bc = ${test.match9("bc")}")
//    println("a_ca_ca_c = ${test.match9("a_ca_ca_c")}")
//    println("a_a = ${test.match9("a_a")}")
//    println("bbb = ${test.match9("bbb")}")

//    println("12811110000 = ${test.match10("12811110000")}")
//    println("13811110000 = ${test.match10("13811110000")}")
//    println("19811110000 = ${test.match10("19811110000")}")
//    println("138111100001 = ${test.match10("138111100001")}")
//    println("1381111000  = ${test.match10("1381111000")}")
}