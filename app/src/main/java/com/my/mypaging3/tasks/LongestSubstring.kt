package com.my.mypaging3.tasks

/*
https://leetcode.com/problems/longest-substring-without-repeating-characters/

Given a string s, find the length of the longest substring without repeating characters.
 */

fun main() {
    println(lengthOfLongestSubstring("abba"))
}

fun lengthOfLongestSubstring(s: String): Int {
    val container = HashMap<Char, Int>()
    var maxLength = 0
    var min = 0

    for (i in s.indices) {
        val key = s[i]
        val minIndex = container[key]
        if (minIndex != null) {
            val currentMin = minIndex + 1
            min = if(currentMin >= min){
                currentMin
            }else{
                min
            }
            container.remove(key)
        }
        container[key] = i

        val currentMax = (i+1) - min
        maxLength = if (currentMax >= maxLength) {
            currentMax
        } else {
            maxLength
        }
    }
    return maxLength
}
