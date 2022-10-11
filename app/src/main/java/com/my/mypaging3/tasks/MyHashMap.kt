package com.my.mypaging3.tasks

/*
https://leetcode.com/problems/design-hashmap/

Design a HashMap without using any built-in hash table libraries.

Implement the MyHashMap class:

MyHashMap() initializes the object with an empty map.
void put(int key, int value) inserts a (key, value) pair into the HashMap. If the key already exists in the map, update the corresponding value.
int get(int key) returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key.
void remove(key) removes the key and its corresponding value if the map contains the mapping for the key.
 */

fun main() {

}

class MyHashMap() {

    private val container = mutableListOf<Chain>()

    fun put(key: Int, value: Int) {
        for (i in container.indices) {
            if (key == container[i].key) {
                container.remove(container[i])
                container.add(Chain(key, value))
                return
            }
        }
        container.add(Chain(key, value))
    }

    fun get(key: Int): Int {
        container.forEach {
            if (it.key == key) return it.value
        }
        return -1
    }

    fun remove(key: Int) {
        for (i in container.size - 1 downTo 0) {
            if (key == container[i].key) {
                container.removeAt(i)
            }
        }
    }

    data class Chain(
        val key: Int,
        val value: Int
    )
}

/**
 * Your MyHashMap object will be instantiated and called as such:
 * var obj = MyHashMap()
 * obj.put(key,value)
 * var param_2 = obj.get(key)
 * obj.remove(key)
 */
