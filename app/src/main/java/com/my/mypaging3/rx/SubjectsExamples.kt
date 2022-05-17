package com.my.mypaging3.rx

import io.reactivex.functions.Action
import io.reactivex.subjects.*
import org.reactivestreams.Subscription
import io.reactivex.subjects.PublishSubject




class SubjectsExamples {

    fun checkPublishSubject() {
        val publishSubject = PublishSubject.create<String>()
        //.subscribeOn(Schedulers.io())

        publishSubject.onNext("PS-1")
        val disposable = publishSubject
            //.map { it }
            //.subscribeOn(Schedulers.io())
            //.observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                println(it)
            }

        publishSubject.onNext("PS-2")
    }

    fun checkReplaySubject() {
        val replaySubject = ReplaySubject.create<String>()

        replaySubject.onNext("RS-0")
        replaySubject.onNext("RS-1")

        val disposable = replaySubject.subscribe {
            println(it)
        }
        disposable.dispose()
        replaySubject.onNext("RS-2")
    }

    fun checkBehaviorSubject() {
        val behaviorSubject = BehaviorSubject.createDefault("BS-0")
        val disposable = behaviorSubject.subscribe {
            println("1-$it")
        }

        behaviorSubject.onNext("BS-1")
        behaviorSubject.onNext("BS-2")

        val disposable2 = behaviorSubject.subscribe {
            println("2-$it")
        }

        behaviorSubject.onNext("BS-3")
    }

    fun checkAsyncSubject() {
        val asyncSubject = AsyncSubject.create<String>()

        asyncSubject.onNext("AS-0")
        //asyncSubject.onComplete()
        asyncSubject.subscribe()
        val disposable = asyncSubject.subscribe {
            println(it)
        }

        asyncSubject.onNext("AS-1")
        asyncSubject.onNext("AS-2")
        asyncSubject.onComplete()
    }
}