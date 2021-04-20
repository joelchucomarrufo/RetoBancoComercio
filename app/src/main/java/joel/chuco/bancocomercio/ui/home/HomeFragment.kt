package joel.chuco.bancocomercio.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import joel.chuco.bancocomercio.R
import joel.chuco.bancocomercio.data.remote.model.Result
import joel.chuco.bancocomercio.data.remote.model.User
import joel.chuco.bancocomercio.ui.MainActivity
import joel.chuco.bancocomercio.ui.detail.DetailUserDialogFragment
import joel.chuco.bancocomercio.ui.home.adapter.UserAdapter
import kotlinx.android.synthetic.main.fragment_home.*

@AndroidEntryPoint
class HomeFragment : Fragment(), UserAdapter.DetailAdapterListener {

    private val viewModel by viewModels<HomeViewModel>()
    private var userAdapter: UserAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initCalls()
        subscribeUsers()
    }

    private fun initCalls() {
        viewModel.fetchUsers()
    }

    private fun subscribeUsers() {
        viewModel.userResponse.observe(viewLifecycleOwner, { result ->

            when (result.status) {
                Result.Status.SUCCESS -> {
                    if (result.data == null || result.data.isEmpty()!!) {
                        rvUsers.visibility = View.GONE
                        llWithoutResults.visibility = View.VISIBLE
                        (activity as MainActivity).showGlobalProgressBar(false)
                    } else {
                        loadUsers(result.data)
                    }
                }

                Result.Status.ERROR -> {
                    rvUsers.visibility = View.GONE
                    llWithoutResults.visibility = View.VISIBLE
                    Toast.makeText(
                        context,
                        "Ocurrio un error ${result.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                    (activity as MainActivity).showGlobalProgressBar(false)
                }

                Result.Status.LOADING -> {
                    (activity as MainActivity).showGlobalProgressBar(true)
                }
            }
        })
    }

    private fun loadUsers(data: List<User>) {
        userAdapter = UserAdapter()
        userAdapter?.setItems(data)
        userAdapter?.listener = this
        rvUsers?.isNestedScrollingEnabled = false
        rvUsers?.layoutManager = LinearLayoutManager(context)
        rvUsers?.adapter = userAdapter
        rvUsers.visibility = View.VISIBLE
        llWithoutResults.visibility = View.GONE
        (activity as MainActivity).showGlobalProgressBar(false)
    }

    override fun onClickDetail(user: User) {
        println("**** user -> " + Gson().toJson(user))
        val dialog =
            DetailUserDialogFragment.newInstance(user)
        dialog.show(childFragmentManager, DetailUserDialogFragment::class.java.name)
    }
}