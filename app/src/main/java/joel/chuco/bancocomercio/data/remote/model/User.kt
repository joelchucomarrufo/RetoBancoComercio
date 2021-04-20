package joel.chuco.bancocomercio.data.remote.model

import com.google.gson.annotations.SerializedName
import joel.chuco.bancocomercio.data.remote.model.Address
import joel.chuco.bancocomercio.util.Constants.EMPTY_INTEGER
import joel.chuco.bancocomercio.util.Constants.EMPTY_STRING
import java.io.Serializable

class User : Serializable {

    @SerializedName("id")
    val id: Int = EMPTY_INTEGER

    @SerializedName("name")
    val name: String = EMPTY_STRING

    @SerializedName("username")
    val username: String = EMPTY_STRING

    @SerializedName("email")
    val email: String = EMPTY_STRING

    @SerializedName("address")
    val address: Address? = null

    @SerializedName("phone")
    val phone: String = EMPTY_STRING

    @SerializedName("website")
    val website: String = EMPTY_STRING

    @SerializedName("company")
    val company: Company? = null

}