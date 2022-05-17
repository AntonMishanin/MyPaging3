package com.my.mypaging3.rx

import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

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

            println("BACKGROUND - ${Thread.currentThread()}")
           //for(i in -1_000_000_000 .. 1_000_000_000){
           //    if(i == 555_555){
           //        it.onNext(1)
           //    }
           //    if(i == -555_555){
           //        it.onNext(2)
           //    }
           //}
            it.onNext(1)
            someIoDeal4()
            it.onNext(2)
            someIoDeal4()
            it.onNext(3)
            someIoDeal4()
            it.onNext(4)

        }
         .subscribeOn(Schedulers.io())
         //.observeOn(Schedulers.io())
            .subscribe({ next ->
                println(next)
            }, { error ->
                println(error)
            }, {
                println("Completed")
            })
    }

    private fun someIoDeal(): Observable<Int> {
        return Observable.create<Int> {
            println("BACKGROUND - ${Thread.currentThread()}")
            for(i in -1_000_000_000 .. 1_000_000_000){
                if(i == 555_555){
                    it.onNext(1)
                }
                if(i == -555_555){
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