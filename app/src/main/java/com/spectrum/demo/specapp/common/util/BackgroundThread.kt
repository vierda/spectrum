package com.spectrum.demo.specapp.common.util

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class BackgroundThread : PostExecutionThread {

    override val scheduler: Scheduler
        get() = Schedulers.computation()
}