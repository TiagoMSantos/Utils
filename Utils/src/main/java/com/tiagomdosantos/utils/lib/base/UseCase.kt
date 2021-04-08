package com.tiagomdosantos.utils.lib.base

import com.tiagomdosantos.utils.lib.ApiError
import com.tiagomdosantos.utils.lib.CoroutineContextProvider
import com.tiagomdosantos.utils.lib.traceErrorException
import java.util.concurrent.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class UseCase<Type, in Params>(private val coroutineContextProvider: CoroutineContextProvider) where Type : Any {

    abstract suspend fun run(params: Params): Type

    fun execute(scope: CoroutineScope, params: Params, response: UseCaseResponse<Type>) {
        scope.launch(context = coroutineContextProvider.io()) {
            try {
                val result = run(params = params)
                withContext(context = coroutineContextProvider.main()) {
                    response.onSuccess(response = result)
                }
            } catch (exception: CancellationException) {
                withContext(context = coroutineContextProvider.main()) {
                    response.onError(error = traceErrorException(throwable = exception))
                }
            } catch (exception: Exception) {
                withContext(context = coroutineContextProvider.main()) {
                    response.onError(error = traceErrorException(throwable = exception))
                }
            }
        }
    }
}

interface UseCaseResponse<Type> {
    fun onSuccess(response: Type)
    fun onError(error: ApiError)
}
