package com.example.android.dagger.login.di

import com.example.android.dagger.storage.SharedPreferencesStorage
import com.example.android.dagger.storage.Storage
import dagger.Binds
import dagger.Module

/**
 * 다음과 같이 생각할 수 있다.
 * With the code above, we told Dagger "when you need a Storage object use SharedPreferencesStorage"
 * "Dagger, 너 Storage 타입이 필요하면 SharedPreferencesStorage를 써!"
 **/

// Module 어노테이션은 이 모듈 클래스가 Dagger 모듈이라고 Dagger에게 알려준다.
@Module
abstract class StorageModule {

    // Binds 어노테이션은 추상 메서드 타입에만 달릴 수 있다.
    // 주입에 Storage 타입이 요청 되었을 때 구현체인 SharedPreferencesStorage를 반환해준다.
    @Binds
    abstract fun provideStorage(storage: SharedPreferencesStorage): Storage


}