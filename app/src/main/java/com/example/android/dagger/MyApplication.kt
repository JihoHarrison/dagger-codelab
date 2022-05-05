/*
 * Copyright (C) 2019 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.dagger

import android.app.Application
import com.example.android.dagger.login.di.AppComponent
import com.example.android.dagger.login.di.DaggerAppComponent
import com.example.android.dagger.storage.SharedPreferencesStorage
import com.example.android.dagger.user.UserManager

open class MyApplication : Application() {

    // AppComponent(Dagger Graph)를 applicationContext 이용하여
    // 모든 액티비티 또는 프래그먼트에서 사용 가능토록 지정!
    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext)
    }

    open val userManager by lazy {
        UserManager(SharedPreferencesStorage(this))
    }
}
