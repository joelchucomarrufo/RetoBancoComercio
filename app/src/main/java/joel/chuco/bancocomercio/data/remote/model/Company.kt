package joel.chuco.bancocomercio.data.remote.model

import com.google.gson.annotations.SerializedName
import joel.chuco.bancocomercio.util.Constants.EMPTY_STRING
import java.io.Serializable

class Company : Serializable {

    @SerializedName("name")
    val name: String = EMPTY_STRING

    @SerializedName("catchPhrase")
    val catchPhrase: String = EMPTY_STRING

    @SerializedName("bs")
    val bs: String = EMPTY_STRING

}