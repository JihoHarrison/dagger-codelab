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

package com.example.android.dagger.registration

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.android.dagger.MyApplication
import com.example.android.dagger.R
import com.example.android.dagger.login.di.RegistrationComponent
import com.example.android.dagger.main.MainActivity
import com.example.android.dagger.registration.enterdetails.EnterDetailsFragment
import com.example.android.dagger.registration.termsandconditions.TermsAndConditionsFragment
import javax.inject.Inject

class RegistrationActivity : AppCompatActivity() {

    @Inject
    lateinit var registrationViewModel: RegistrationViewModel
    lateinit var registrationComponent: RegistrationComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_registration)
        // super.onCreate 전에 inject 되어야 한다. 추후 Fragment 관련되어 문제가 발생할 수 있음.
        // Dagger에게 최종적으로 의존성을 주입하라고 요청을 보내는 부분
        // RegistrationComponent 인스턴스가 ActivityScope으로 만들어지면서 사라지게 된다.
        // (application as MyApplication).appComponent.inject(this)

        // 위 주석처리 부분이 사라지면서 registrationComponent는 아래와 같이 초기화 된다.
        registrationComponent = (application as MyApplication).appComponent.registrationComponents().create()
        registrationComponent.inject(this)
        super.onCreate(savedInstanceState)



        // 위에서 Field Injection을 해주기 때문에 아래 뷰모델 초기화는 필요 없어진다.
        // registrationViewModel = RegistrationViewModel((application as MyApplication).userManager)
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_holder, EnterDetailsFragment())
            .commit()
    }

    /**
     * Callback from EnterDetailsFragment when username and password has been entered
     */
    fun onDetailsEntered() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_holder, TermsAndConditionsFragment())
            .addToBackStack(TermsAndConditionsFragment::class.java.simpleName)
            .commit()
    }

    /**
     * Callback from T&CsFragment when TCs have been accepted
     */
    fun onTermsAndConditionsAccepted() {
        registrationViewModel.registerUser()
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }
}
