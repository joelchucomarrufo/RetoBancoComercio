package joel.chuco.bancocomercio.network

import joel.chuco.bancocomercio.data.remote.model.User
import retrofit2.Response
import retrofit2.http.*

/**
 * Retrofit API Service
 */
interface BancoComercioApiService {


    @GET("users")
    suspend fun getUsers() : Response<List<User>?>

}