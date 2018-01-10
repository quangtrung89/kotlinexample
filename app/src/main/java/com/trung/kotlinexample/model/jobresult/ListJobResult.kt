package com.trung.kotlinexample.model.jobresult

import com.trung.kotlinexample.model.CommonResult

data class ListJobResult(val data: List<JobItem>?) : CommonResult()