package com.my.mypaging3.thread

import android.os.*
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.Button
import com.my.mypaging3.R
import kotlinx.coroutines.sync.Mutex
import okhttp3.internal.notify
import okhttp3.internal.wait
import java.util.concurrent.*
import java.util.concurrent.locks.Lock
import java.util.concurrent.locks.ReentrantLock

class ThreadActivity : AppCompatActivity() {

    private val count = Count(value = 0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thread)

        val looperThread = LooperThread()
        looperThread.start()


        val thread = object : HandlerThread("sdsd") {
            override fun run() {
                // super.run()
                println(Thread.currentThread())
                sleep(3000)
            }

            fun sleep() {
                println(Thread.currentThread())
                sleep(3000)
            }
        }
        thread.start()
        //thread.interrupt()



        val executor = Executors.newSingleThreadExecutor()
        val future = executor.submit<String> {
            Thread.sleep(1_000)
            return@submit "TEST"
        }
        val result = future.get()
        println(result)
        println("AFTER")

        val executorService = Executors.newFixedThreadPool(2)

        //Executor
        //ThreadPoolExecutor
        future.get()

        //CountDownLatch().await()

        findViewById<Button>(R.id.button2).setOnClickListener {
            //looperThread.doSomeWork()
            thread.run()
        }


        val handler = Handler(Looper.getMainLooper())
        // handler.sendMessage()
        // handler.postAtTime()
        // handler.removeCallbacks()

        //val runnable1 = Runnable1(count)
        //val runnable2 = Runnable1(count)
//
        //val thread1 = Thread(runnable1)
        //val thread2 = Thread(runnable2)
//
        //thread1.start()
        //thread2.start()

        // repeat(10000) {
        //     Thread(Runnable1(count)).wait()//.start()
        // }

        // val lock = ReentrantLock()
        // val l = Lock
        // val myObject = Object()
        // val m = Monitor()
        // synchronized()


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

        @Volatile
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

internal class LooperThread : Thread() {

    private var mHandler: Handler? = null

    override fun run() {
        println("RUN")
        Looper.prepare()
        mHandler = Handler(Looper.myLooper()!!)
        Looper.loop()
    }

    fun doSomeWork() {
        println("START WORK 0")
        mHandler?.post {
            println("START WORK")
            Thread.sleep(1_000)
            println("FINISH WORK")
        }
    }
}