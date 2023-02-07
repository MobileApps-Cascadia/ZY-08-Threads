package com.zybooks.threadingdemo

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FlowCounterSource {
    private var counter:Int = 0
    val latestData: Flow<String> = flow{
        while(true){
            val message = "Counting on UI Thread\n" + counter
            emit(message)
            counter++
            delay(1000)
        }
    }
}
