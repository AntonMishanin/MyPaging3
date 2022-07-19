package com.my.mypaging3.rx

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.my.mypaging3.R
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import java.lang.Exception
import java.util.*

class RxActivity : AppCompatActivity() {

    private val leftObservable = PublishSubject.create<String>()
    private val rightObservable = PublishSubject.create<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rx)

        val observableHelper = ObservableHelper()
        //observableHelper.checkObservableJust()
        //observableHelper.checkObservableFrom()
        //observableHelper.checkObservableCreate()
        //observableHelper.checkObservableDistinct()
        observableHelper.checkConcat()

    //   val disposable1 = Observable.zip(leftObservable, rightObservable) { q, w ->
    //       println("q")
    //       println("w")
    //       println("e")
    //   }
    //       .subscribeOn(Schedulers.io())
    //       .subscribe {

    //       }

       // val disposable = Observable.combineLatest(leftObservable, rightObservable) { f, s ->
       //     f
       // }
       // val disposable = Observable.zip(leftObservable, rightObservable) { q, w ->
       //     w
       // }
       // val disposable = Observable.merge(leftObservable, rightObservable)
        val disposable = Observable.concat(leftObservable, rightObservable)
            .doOnError {
                println("ERROR $it")
            }
            .retry { it: Throwable ->
                //Check Throwable
                true
            }
            .subscribe {
                println("TWO $it")
            }

        // val disposable2 = Observable.combineLatest(listOf(leftObservable), { f ->
        //     f[0]
        // }, 4).subscribe {
        //     println("ONE $it")
        // }

        var count2 = 0
        findViewById<Button>(R.id.leftObservable).setOnClickListener {
            leftObservable.onNext(Calendar.getInstance().time.toString())
            count2 ++
            if(count2==3){
                leftObservable.onComplete()
            }
        }
        var count = 0
        findViewById<Button>(R.id.rightObservable).setOnClickListener {
            rightObservable.onNext(count.toString())
            count++
            if (count == 3) {
                rightObservable.onError(Exception())
            }
        }
    }
}