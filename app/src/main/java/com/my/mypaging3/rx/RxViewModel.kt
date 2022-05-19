package com.my.mypaging3.rx

class RxViewModel {


}

fun main() {
    val helper = SubjectsExamples()
    // helper.checkPublishSubject()
    // helper.checkReplaySubject()
    // helper.checkBehaviorSubject()
    // helper.checkAsyncSubject()

    val observableHelper = ObservableHelper()
    //observableHelper.checkObservableJust()
    //observableHelper.checkObservableFrom()
    //observableHelper.checkObservableCreate()
    //observableHelper.checkObservableDistinct()
    //observableHelper.checkEmitItemsOneByOne()
    observableHelper.checkSingle()
}

