package joel.chuco.bancocomercio.data.remote

import joel.chuco.bancocomercio.data.remote.model.Result
import joel.chuco.bancocomercio.data.remote.model.User
import joel.chuco.bancocomercio.network.BancoComercioApiService
import joel.chuco.bancocomercio.util.ErrorUtils
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class UserRemoteDataSource @Inject constructor(private val retrofit: Retrofit) {

    suspend fun fetchUsers(): Result<List<User>?> {
        val apiService = retrofit.create(BancoComercioApiService::class.java);
        return getResponse(
                request = { apiService.getUsers() },
                defaultErrorMessage = "Ocurrio un error")

    }

    private suspend fun <T> getResponse(request: suspend () -> Response<T>, defaultErrorMessage: String): Result<T> {
        return try {
            val result = request.invoke()
            if (result.isSuccessful) {
                return Result.success(result.body())
            } else {
                val errorResponse = ErrorUtils.parseError(result, retrofit)
                Result.error(errorResponse?.message ?: defaultErrorMessage, errorResponse)
            }
        } catch (e: Throwable) {
            Result.error("Error desconocido", null)
        }
    }
}