package com.itis.group11801.fedotova.mvpexample.di.modules

import com.itis.group11801.fedotova.mvpexample.data.repository.InfoRepository
import com.itis.group11801.fedotova.mvpexample.data.repository.InfoRepositoryImpl
import com.itis.group11801.fedotova.mvpexample.data.repository.UserRepository
import com.itis.group11801.fedotova.mvpexample.data.repository.UserRepositoryImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface DataModule {

    @Binds
    @Singleton
    fun bindUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository

    @Binds
    @Singleton
    fun bindInfoRepository(infoRepositoryImpl: InfoRepositoryImpl): InfoRepository
}