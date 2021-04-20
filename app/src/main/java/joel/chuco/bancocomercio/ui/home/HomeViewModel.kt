package joel.chuco.bancocomercio.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import joel.chuco.bancocomercio.data.UserRepository
import joel.chuco.bancocomercio.data.remote.model.Result
import joel.chuco.bancocomercio.data.remote.model.User
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeViewModel @ViewModelInject constructor(private val userRepository: UserRepository
) : ViewModel() {

    private val _userResponse = MutableLiveData<Result<List<User>?>>()
    val userResponse = _userResponse

    fun fetchUsers() {
        viewModelScope.launch {
            userRepository.fetchUsers().collect {
                _userResponse.value = it
            }
        }
    }

}