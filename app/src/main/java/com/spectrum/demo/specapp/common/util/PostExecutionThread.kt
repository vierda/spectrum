package com.spectrum.demo.specapp.common.util

import io.reactivex.Scheduler


interface PostExecutionThread {
    val scheduler: Scheduler
}