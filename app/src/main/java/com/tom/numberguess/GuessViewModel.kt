package com.tom.numberguess

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

class GuessViewModel : ViewModel() {
    val min = MutableLiveData<Int>()
    val max = MutableLiveData<Int>()
    val bingo = MutableLiveData<Boolean>()
    var secret = Random().nextInt(100)+1
    init {
        min.value = 1
        max.value = 100
        println("secret: $secret")
        bingo.value = false
    }

    fun guess(num: Int) {
        if (secret == num) {
            //You got it
            bingo.value = true
        } else if (num > secret) {
            max.value = num
        } else {
            min.value = num
        }
    }

    fun add() {
        var n = min.value
        n?.plus(1)
        min.value = n
    }
}