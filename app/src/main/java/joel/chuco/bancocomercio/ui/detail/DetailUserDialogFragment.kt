package joel.chuco.bancocomercio.ui.detail

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import joel.chuco.bancocomercio.R
import joel.chuco.bancocomercio.data.remote.model.User
import kotlinx.android.synthetic.main.fragment_detail_user.*

@AndroidEntryPoint
class DetailUserDialogFragment : DialogFragment() {

    private lateinit var user: User

    companion object {

        private const val USER = "user"

        fun newInstance(user: User): DetailUserDialogFragment {
            val args = Bundle()
            val fragment = DetailUserDialogFragment()
            args.putSerializable(USER, user)
            fragment.arguments = args

            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        user = requireArguments().getSerializable(USER) as User
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_detail_user, container, false)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        loadData()
    }

    private fun loadData() {
        tvName.text = user.name
        mtvUserName.text = user.username
        mtvPhone.text = user.phone
        mtvEmail.text = user.email
        mtvWebSite.text = user.website
        mtvStreetSuite.text = "${user.address?.street} ${user.address?.suite}"
        mtvCity.text = user.address?.city
        mtvZipCode.text = user.address?.zipcode
        mtvLatitude.text = user.address?.geo?.lat
        mtvLongitude.text = user.address?.geo?.lng
        mtvCompanyName.text = user.company?.name
        mtvCompanyPhrase.text = user.company?.catchPhrase
        mtvCompanyBs.text = user.company?.bs
    }

    override fun onStart() {
        super.onStart()
        fitFullScreen()
        initEvents()
    }

    private fun initEvents() {
        ibClose.setOnClickListener {
            dismiss()
        }
    }

    protected fun fitFullScreen() {
        val width = ViewGroup.LayoutParams.MATCH_PARENT
        val height = ViewGroup.LayoutParams.MATCH_PARENT
        dialog?.window?.setLayout(width, height)
    }

}