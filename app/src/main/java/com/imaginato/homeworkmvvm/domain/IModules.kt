package com.imaginato.homeworkmvvm.domain

import android.app.Application
import androidx.room.Room
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.imaginato.homeworkmvvm.data.local.demo.DemoDatabase
import com.imaginato.homeworkmvvm.data.local.demo.DemoDao
import com.imaginato.homeworkmvvm.data.local.login.LogInDao
import com.imaginato.homeworkmvvm.data.local.login.LogInDatabase
import com.imaginato.homeworkmvvm.data.remote.demo.DemoApi
import com.imaginato.homeworkmvvm.data.remote.demo.DemoDataRepository
import com.imaginato.homeworkmvvm.data.remote.demo.DemoRepository
import com.imaginato.homeworkmvvm.data.remote.login.LogInApi
import com.imaginato.homeworkmvvm.data.remote.login.LogInDataRepository
import com.imaginato.homeworkmvvm.data.remote.login.LoginRepository
import com.imaginato.homeworkmvvm.ui.demo.MainActivityViewModel
import com.imaginato.homeworkmvvm.ui.login.LoginActivityViewModel
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.component.KoinApiExtension
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val BASE_URL = "https://ifconfig.me/"

val databaseModule = module {
    single { provideDatabase(androidApplication()) }
    single { provideDao(get()) }
}

val netModules = module {
    single { provideInterceptors() }
    single { provideOkHttpClient(get()) }
    single { provideRetrofit(get()) }
    single { provideGson() }
}

val apiModules = module {
    single { provideDemoApi(get()) }
    single { provideLogInApi(get()) }
}

val repositoryModules = module {
    single { provideDemoRepo(get()) }
    single { provideLogInRepo(get()) }
}

@OptIn(KoinApiExtension::class)
val viewModelModules = module {
    viewModel {
        MainActivityViewModel()
        LoginActivityViewModel()
    }
}

private fun provideDemoRepo(api: DemoApi): DemoRepository {
    return DemoDataRepository(api)
}

private fun provideLogInRepo(api: LogInApi): LoginRepository {
    return LogInDataRepository(api)
}

private fun provideDemoApi(retrofit: Retrofit): DemoApi = retrofit.create(DemoApi::class.java)

private fun provideLogInApi(retrofit: Retrofit): LogInApi = retrofit.create(LogInApi::class.java)

private fun provideDatabase(application: Application): LogInDatabase {
    return Room.databaseBuilder(application, LogInDatabase::class.java, "I-Database")
        .fallbackToDestructiveMigration()
        .build()
}

private fun provideDao(database: LogInDatabase): LogInDao {
    return database.logIn
}

private fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

private fun provideOkHttpClient(interceptors: ArrayList<Interceptor>): OkHttpClient {
    val clientBuilder = OkHttpClient.Builder()
    clientBuilder.readTimeout(2, TimeUnit.MINUTES)
    clientBuilder.connectTimeout(2, TimeUnit.MINUTES)
    interceptors.forEach { clientBuilder.addInterceptor(it) }
    return clientBuilder.build()
}

private fun provideInterceptors(): ArrayList<Interceptor> {
    val interceptors = arrayListOf<Interceptor>()
    val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    interceptors.add(loggingInterceptor)
    return interceptors
}

fun provideGson(): Gson {
    return GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create()
}
