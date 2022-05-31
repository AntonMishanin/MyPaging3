package com.my.mypaging3.thread

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.my.mypaging3.R

class ThreadActivity : AppCompatActivity() {

    private val count = Count(value = 0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thread)

        //val runnable1 = Runnable1(count)
        //val runnable2 = Runnable1(count)
//
        //val thread1 = Thread(runnable1)
        //val thread2 = Thread(runnable2)
//
        //thread1.start()
        //thread2.start()

        repeat(10000) {
            Thread(Runnable1(count)).start()
        }

        // runnable1.print()
        // runnable2.print()

        //thread1.join()
        //thread2.join()

        //runnable1.print()
        //runnable2.print()
    }
}

class Runnable1(
    private val count: Count
) : Runnable {

    override fun run() {

        MySingleton.instance().doWork()
        //repeat(1_000_000) {
        //    count.increase()
        //}
    }

    fun print() = count.print()
}

data class Count(
    private var value: Int
) {

    @Synchronized
    fun increase() {
        value += 1
    }

    fun print() = println(value)
}

class MySingleton private constructor() {

    private var counter = 0

    init {
        println("INIT MySingleton")
    }

    companion object {

        private val lock = Object()

        //@Volatile
        private var instance: MySingleton? = null

        fun instance(): MySingleton {
            Thread.sleep(50)
            if (instance == null) {
                Thread.sleep(50)
                synchronized(lock) {
                    Thread.sleep(50)
                    if (instance == null) {
                        println("ONE TIME")
                        Thread.sleep(50)
                        instance = MySingleton()
                        Thread.sleep(50)
                    }
                }
            }

            return LazyHolder.mySingleton//instance!!
        }

        class LazyHolder {
            companion object {
                val mySingleton = MySingleton()
            }
        }
    }

    fun doWork() {
        counter++
        //println(counter)
    }
}