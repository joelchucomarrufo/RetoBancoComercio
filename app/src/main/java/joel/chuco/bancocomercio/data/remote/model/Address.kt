package joel.chuco.bancocomercio.data.remote.model

import com.google.gson.annotations.SerializedName
import joel.chuco.bancocomercio.util.Constants.EMPTY_STRING
import java.io.Serializable

class Address : Serializable {

    @SerializedName("street")
    val street: String = EMPTY_STRING

    @SerializedName("suite")
    val suite: String = EMPTY_STRING

    @SerializedName("city")
    val city: String = EMPTY_STRING

    @SerializedName("zipcode")
    val zipcode: String = EMPTY_STRING

    @SerializedName("geo")
    val geo: Geo? = null

}