package joel.chuco.bancocomercio.data

import joel.chuco.bancocomercio.data.remote.UserRemoteDataSource
import joel.chuco.bancocomercio.data.remote.model.Result
import joel.chuco.bancocomercio.data.remote.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class UserRepository @Inject constructor(
        private val userRemoteDataSource: UserRemoteDataSource
) {

    suspend fun fetchUsers(): Flow<Result<List<User>?>> {
        return flow {
            emit(Result.loading())
            emit(userRemoteDataSource.fetchUsers())
        }.flowOn(Dispatchers.IO)
    }

}