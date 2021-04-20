package joel.chuco.bancocomercio.data.remote.model

import com.google.gson.annotations.SerializedName
import joel.chuco.bancocomercio.util.Constants.EMPTY_STRING
import java.io.Serializable

class Geo : Serializable {

    @SerializedName("lat")
    val lat: String = EMPTY_STRING

    @SerializedName("lng")
    val lng: String = EMPTY_STRING

}