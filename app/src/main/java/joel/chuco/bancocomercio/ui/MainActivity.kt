package joel.chuco.bancocomercio.ui

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import dagger.hilt.android.AndroidEntryPoint
import joel.chuco.bancocomercio.R

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var loadingDialog: Dialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_BancoComercio)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun showGlobalProgressBar(show: Boolean) {
        if (loadingDialog == null && show) {
            showLoadingDialog()
        } else if (show && (loadingDialog != null && !loadingDialog?.isShowing!!)) {
            showLoadingDialog()
        } else if (!show && loadingDialog?.isShowing!!) {
            loadingDialog?.dismiss()
        }
    }

    private fun showLoadingDialog() {
        loadingDialog = Dialog(this)
        loadingDialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        loadingDialog?.setContentView(R.layout.layout_loading)
        loadingDialog?.setCancelable(false)
        val lp = WindowManager.LayoutParams()
        lp.copyFrom(loadingDialog?.window!!.attributes)
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT
        loadingDialog?.show()
        loadingDialog?.window!!.attributes = lp
    }
}