package com.example.domain.usecase


import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

typealias CompletionBlock<T> = UseCase.Request<T>.() -> Unit

abstract class UseCase<T> {

    private var parentJob: Job = Job()
    var backgroundContext: CoroutineContext = Dispatchers.IO
    var foregroundContext: CoroutineContext = Dispatchers.Main

    protected abstract suspend fun executeOnBackground(): T

    fun execute(block: CompletionBlock<T>) {
        val response = Request<T>().apply { block() }
        unsubscribe()
        parentJob = Job()
        CoroutineScope(foregroundContext + parentJob).launch {
            try {
                val result = withContext(backgroundContext) {
                    executeOnBackground()
                }
                response(result)
            } catch (cancellationException: CancellationException) {
                response(cancellationException)
            } catch (e: Exception) {
                response(e)
            }
        }
    }


    fun unsubscribe() {
        parentJob.apply {
            cancelChildren()
            cancel()
        }
    }


    class Request<T> {
        private var onComplete: ((T) -> Unit)? = null
        private var onError: ((Throwable) -> Unit)? = null
        private var onCancel: ((CancellationException) -> Unit)? = null

        fun onComplete(block: (T) -> Unit) {
            onComplete = block
        }

        fun onError(block: (Throwable) -> Unit) {
            onError = block
        }

        fun onCancel(block: (CancellationException) -> Unit) {
            onCancel = block
        }

        operator fun invoke(result: T) {
            onComplete?.invoke(result)
        }

        operator fun invoke(error: Throwable) {
            onError?.invoke(error)
        }

        operator fun invoke(error: CancellationException) {
            onCancel?.invoke(error)
        }
    }
}