package com.example.android.dagger.login.di

import com.example.android.dagger.registration.RegistrationActivity
import dagger.Component

@Component
interface AppComponent {

    fun inject(activity: RegistrationActivity)

}