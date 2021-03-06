package com.my.mypaging3.rx

import android.os.SystemClock
import com.my.mypaging3.auth.github.GitHubApi
import com.my.mypaging3.auth.github.RemoteFactory
import com.my.mypaging3.auth.github.Response
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import retrofit2.Call

class ObservableHelper {

    private val retrofit = RemoteFactory().provideRetrofit()
    private val gitHubApi = retrofit.create(GitHubApi::class.java)

    private val singleFromNetwork = Single.create<String> { emitter ->
        println("1-start")

        gitHubApi.fetchNowPlaying2().enqueue(object : retrofit2.Callback<List<Response>> {
            override fun onResponse(
                call: Call<List<Response>>,
                response: retrofit2.Response<List<Response>>
            ) {
                println("1-success")
                emitter.onSuccess("1-success")
            }

            override fun onFailure(call: Call<List<Response>>, t: Throwable) =
                println("1-error")
        })
    }

    private val singleFromDb = Single.create<String> { emitter ->
        println("1-start")

        gitHubApi.fetchNowPlaying2().enqueue(object : retrofit2.Callback<List<Response>> {
            override fun onResponse(
                call: Call<List<Response>>,
                response: retrofit2.Response<List<Response>>
            ) {
                println("1-success")
                emitter.onSuccess("1-success")
            }

            override fun onFailure(call: Call<List<Response>>, t: Throwable) =
                println("1-error")
        })
    }

    private val observable1 = Observable.create<String> { emitter ->
        println("1-start")

        gitHubApi.fetchNowPlaying2().enqueue(object : retrofit2.Callback<List<Response>> {
            override fun onResponse(
                call: Call<List<Response>>,
                response: retrofit2.Response<List<Response>>
            ) {
                println("1-success")
                emitter.onNext("1-success")
                emitter.onComplete()
            }

            override fun onFailure(call: Call<List<Response>>, t: Throwable) =
                println("1-error")
        })
    }//.startWith("1-default value")

    private val observable2 = Observable.create<String> { emitter ->
        println("2-start")

        gitHubApi.fetchNowPlaying2().enqueue(object : retrofit2.Callback<List<Response>> {
            override fun onResponse(
                call: Call<List<Response>>,
                response: retrofit2.Response<List<Response>>
            ) {
                println("2-success")
                emitter.onNext("2-success")
            }

            override fun onFailure(call: Call<List<Response>>, t: Throwable) =
                println("2-error")
        })
    }//.startWith("2-default value")

    private val observable3 = Observable.create<String> { emitter ->
        println("3-start")

        gitHubApi.fetchNowPlaying2().enqueue(object : retrofit2.Callback<List<Response>> {
            override fun onResponse(
                call: Call<List<Response>>,
                response: retrofit2.Response<List<Response>>
            ) {
                println("3-success")
                emitter.onNext("3-success")
            }

            override fun onFailure(call: Call<List<Response>>, t: Throwable) =
                println("3-error${t.message}")
        })
    }//.startWith("3-default value")

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
            //.concatWith()
            //.join()
            .concatMap {
                // Do some deal for every item
                Observable.fromCallable { it }
            }
            .collect({
                arrayListOf(789)
            }, { array, value ->
                array.add(value)
            })
            .doOnSuccess { }
            //.concatWith()
            .subscribe({ next ->
                println(next)
            }, { error ->
                println(error)
            })
    }

    fun checkSingle() {
        val disposable = Flowable.fromCallable { emptyList<Int>() }
            //val disposable = Single.fromCallable { emptyList<Int>() }
            //.subscribe { it ->
            //    println(it)
            //}
            .doOnTerminate {

            }
            .subscribe({ success ->

            }, { d ->

            }, {

            }, { d ->

            })
    }

    fun checkZip() {

        val disposable1 = Observable.zip(observable1, observable2, observable3, { q, w, e ->
            println("q")
            println("w")
            println("e")
        })
            .subscribeOn(Schedulers.io())
            .subscribe {

            }

        val disposable = Single.fromCallable { emptyList<Int>() }
            .subscribe { it ->
                println(it)
            }
    }

    private val content1 = BehaviorSubject.create<String>()

    fun checkSequenceConcatMap() {
        val disposable = Observable.just("")
            .concatMap {
                println("observable1")
                content1.onNext(it)
                observable1
            }
            .concatMap {
                println("observable2")
                observable2
            }
            .concatMap {
                println("observable3")
                observable3
            }.subscribeOn(Schedulers.io())
            .subscribe {
                println("success")
            }
    }

    // fun fetchContent(): Flowable<String> {
    //     return if (content1.value.isNullOrEmpty()) {
    //         content1.toFlowable(BackpressureStrategy.MISSING)
    //     } else {
    //         observable1 // do request
    //     }
    // }

    fun checkOnlineFirst(isNeedFresh: Boolean = false): Single<String> {
        return if (isNeedFresh) {
            doFreshRequest()
        } else {
            val sessionCache = ""
            when (sessionCache.isEmpty()) {
                true -> doFreshRequest()
                false -> Single.fromCallable { sessionCache }
            }
        }
    }

    private fun doFreshRequest(): Single<String> {
        return singleFromNetwork
            .doOnSuccess {
                // save to db and to session cache
            }
            .onErrorResumeNext {
                // check network state and show - need to turn on internet
                // check token exception and refresh token
                // request from db
                singleFromDb // if db is empty - show
            }
            .map {
                // convert to domain entity
                it
            }
    }

    fun checkSingleScheduler() {
        val disposable = Observable.create<Int> {
            println("CREATE")
            it.onNext(1)
            it.onComplete()
        }
            //  .startWith {
            //      println("33333333")
            //      println(Thread.currentThread())
            //      it.onNext(0)
            //  }
            .map { it.toString() }
            .doOnSubscribe {
                println("3333333")
                println(Thread.currentThread())
            }
            // .flatMap {
            //     println("observable1")
            //     observable1
            // }
            // .toMap { value: String ->
            //
            //     2
            // }
            // .flatMap {
            //     println("observable2")
            //     observable2
            // }
            //.blockingFirst()
            .observeOn(Schedulers.computation())
            //.flatMap {
            //    println("observable3")
            //    observable3
            //}
            .startWith("222222")
            .subscribeOn(Schedulers.io())
            .startWith("111111")
            .startWith {


            }
            .doOnSubscribe {
                println("000000")
                println(Thread.currentThread())
            }
            //.switchMap{

            //}
            //         .subscribeWith();
            .subscribe {
                println("result $it")
                println(Thread.currentThread())
            }

    }

    fun checkConcat() {
        val disposable = Observable.concat(observable1, observable2, observable3)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread(), true)
            .subscribe {
                println("subscribe $it")
            }
    }

    fun checkCombineLatest() {
        val disposable = Observable.combineLatest(observable1, observable2, { f, s ->


            "result"
        })
            // .concatMapCompletable {
            //
            //     Observable.just(it)
            // }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread(), true)
            .subscribe {
                println("subscribe $it")
            }
    }

    fun checkConcatWith() {
        val disposable = observable1.concatWith(observable2)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread(), true)
            .subscribe {
                println("subscribe $it")
            }
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