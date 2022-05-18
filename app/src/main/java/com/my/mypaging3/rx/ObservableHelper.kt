package com.my.mypaging3.rx

import android.os.SystemClock
import io.reactivex.Observable
import io.reactivex.functions.BiConsumer
import io.reactivex.schedulers.Schedulers
import okio.ByteString.Companion.toByteString
import java.util.concurrent.Callable

class ObservableHelper {

    fun checkObservableJust() {
        println("MAIN - ${Thread.currentThread()}")

        val disposable = Observable.just("first", someIoDeal(), someIoDeal2())
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe({ next ->
                println(next)
            }, { error ->
                println(error)
            }, {
                println("Completed")
            })
    }

    fun checkObservableFrom() {
        println("MAIN - ${Thread.currentThread()}")

        val disposable = Observable.fromCallable { (someIoDeal()) }
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe({ next ->
                println(next)
            }, { error ->
                println(error)
            }, {
                println("Completed")
            })
    }

    fun checkObservableCreate() {
        println("MAIN - ${Thread.currentThread()}")

        val disposable = Observable.create<Int> {

            //it.onNext(someIoDeal())
            //it.onNext(someIoDeal())
            //it.onComplete()
            it.onNext(0)
            println("dsds")
            println("BACKGROUND - ${Thread.currentThread()}")
            for (i in -1_000_000..1_000_000) {
                if (i == 555_555) {
                    it.onNext(1)
                }
                if (i == -555_555) {
                    it.onNext(2)
                }
            }
            it.onNext(1)
            //someIoDeal4()
            SystemClock.sleep(1000)
            it.onNext(2)
            //someIoDeal4()
            SystemClock.sleep(1000)
            it.onNext(3)
            //someIoDeal4()
            SystemClock.sleep(1000)
            it.onNext(4)
            SystemClock.sleep(20000);
        }//.publish()
            .subscribeOn(Schedulers.computation())
            .observeOn(Schedulers.io())
            .subscribe({ next ->
                println(next)
            }, { error ->
                println(error)
            }, {
                println("Completed")
            })
    }

    fun checkObservableDistinct() {
        val disposable = Observable.just(1, 2, 3, 4, 2, 4, 4, 5, 6)//Observable.range(8, 10)
            .filter {
                it % 2 == 0
            }
            .distinctUntilChanged()
            //.repeat()
            // .subscribeOn(Schedulers.io())
            // .observeOn(Schedulers.io())
            .subscribe({ next ->
                println(next)
            }, { error ->
                println(error)
            }, {
                println("Completed")
            })
    }

    fun checkObservableFromIterable() {
        val disposable = Observable.fromIterable(listOf(1, 2, 3, 4))
            .subscribe({ next ->
                println(next)
            }, { error ->
                println(error)
            }, {
                println("Completed")
            })
    }

    fun checkEmitItemsOneByOne() {
        val disposable = Observable.fromCallable { listOf(1, 2, 3, 4) }
            .flatMapIterable { it }
            .doOnNext {

            }
            .concatMap {
                // Do some deal for every item
                Observable.fromCallable { it }
            }
            .collect({
                arrayListOf(789)
            }, { array, value ->
                array.add(value)
            })
            .doOnSuccess {  }
            .subscribe({ next ->
                println(next)
            }, { error ->
                println(error)
            })
    }

    private fun someIoDeal(): Observable<Int> {
        return Observable.create<Int> {
            println("BACKGROUND - ${Thread.currentThread()}")
            for (i in -1_000_000_000..1_000_000_000) {
                if (i == 555_555) {
                    it.onNext(1)
                }
                if (i == -555_555) {
                    it.onNext(2)
                }
            }
            it.onNext(3)
            println("BACKGROUND - ${Thread.currentThread()}")
        }
    }

    private fun someIoDeal2(): Observable<String> {
        Thread.sleep(1_000)
        return Observable.just("Response")
    }

    private fun someIoDeal3(): Int {
        Thread.sleep(1_000)
        println("BACKGROUND - ${Thread.currentThread()}")
        return 8
    }

    private fun someIoDeal4(): Int {
        Thread.sleep(1_000)
        return 1
    }
}