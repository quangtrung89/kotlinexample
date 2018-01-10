package com.trung.kotlinexample.viewmodel

import android.databinding.BaseObservable
import android.databinding.ObservableBoolean

class LoginViewModel : BaseObservable() {
    var isEnableButton: ObservableBoolean = ObservableBoolean(true)

}
