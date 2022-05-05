package com.my.mypaging3.dynamic_proxy

import android.util.Log

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class Event(val value: String)

@Target(AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
annotation class Param(val value: String)

inline fun testInline0(text: String, closure: ()-> String){
    closure.invoke()
    "".forEach {  }
}

inline fun testInline1(text: String,  crossinline closure: ()-> String){
    testInline0(text, closure)
}

fun testInline2(string: String){
    testInline1(""){
        //TODO
        Log.d("ee", "ee")
        ""
    }
}