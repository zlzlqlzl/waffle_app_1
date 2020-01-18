package com.example.waffleappHW1

import io.reactivex.*
import okhttp3.ResponseBody
import retrofit2.*
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.lang.reflect.Type

class RxErrorHandlingCallAdapterFactory private constructor(
    scheduler: Scheduler
) : CallAdapter.Factory() {

    companion object {
        fun create(scheduler: Scheduler) = RxErrorHandlingCallAdapterFactory(scheduler)
    }

    private val original: RxJava2CallAdapterFactory =
        RxJava2CallAdapterFactory.createWithScheduler(scheduler)

    override fun get(
        returnType: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? = RxErrorHandlingCallAdapter(
        retrofit,
        original.get(returnType, annotations, retrofit) as CallAdapter<out Any, *>
    )

    private class RxErrorHandlingCallAdapter<R>(
        private val retrofit: Retrofit,
        private val wrappedCallAdapter: CallAdapter<R, *>
    ) : CallAdapter<R, Any> {

        override fun adapt(call: Call<R>): Any =
            when (val result = wrappedCallAdapter.adapt(call)) {
                is Flowable<*> ->
                    result.onErrorResumeNext { t: Throwable -> Flowable.error(toApiException(t)) }
                is Single<*> ->
                    result.onErrorResumeNext { Single.error(toApiException(it)) }
                is Maybe<*> ->
                    result.onErrorResumeNext { t: Throwable -> Maybe.error(toApiException(t)) }
                is Completable ->
                    result.onErrorResumeNext { Completable.error(toApiException(it)) }
                is Observable<*> ->
                    result.onErrorResumeNext { t: Throwable -> Observable.error(toApiException(t)) }
                else -> result
            }

        override fun responseType(): Type = wrappedCallAdapter.responseType()

        private fun toApiException(t: Throwable): Throwable {
            if (t is HttpException) {
                try {
                } catch (e: Exception) {
                    null
                }?.let { errorResponse ->
                }
            }
            return t
        }
    }
}