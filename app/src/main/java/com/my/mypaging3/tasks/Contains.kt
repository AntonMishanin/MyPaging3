package com.my.mypaging3.tasks

/*
Input: s = "ilovecodingonleetcode", target = "code"
Output: 2
 */

fun main() {
    println(Solution().rearrangeCharacters(s = "lrnvlcqukanpdnluowenfxquitzryponxsikhciohyostvmkapkfpglzikitwiraqgchxnpryhwpuwpozacjhmwhjvslprqlnxrk",
        target = "woijih"))
}

class Solution {
    fun rearrangeCharacters(s: String, target: String): Int {

        var counter = 0

        val map = HashMap<Char, Int>()
        s.forEach {
            map[it] = (map[it] ?: 0) + 1
        }

        var index = 0
        while (true) {

            // target[index] ==
            val key = target[index]
            if (map.containsKey(target[index])) {
                if (map[target[index]] ?: 0 == 1) {
                    map.remove(key)
                } else {
                    map[key] = (map[key] ?: 0) - 1
                }
            } else {
                return counter
            }

            index++
            if (index == target.length) {
                index = 0
                counter++
            }
        }
    }
}