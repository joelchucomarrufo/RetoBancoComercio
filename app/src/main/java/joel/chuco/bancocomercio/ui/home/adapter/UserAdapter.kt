package joel.chuco.bancocomercio.ui.home.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import joel.chuco.bancocomercio.R
import joel.chuco.bancocomercio.data.remote.model.User
import joel.chuco.bancocomercio.util.inflate
import kotlinx.android.synthetic.main.item_user.view.*

class UserAdapter : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private var users: List<User> = emptyList()
    lateinit var listener: DetailAdapterListener

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): UserViewHolder {
        val view = parent.inflate(R.layout.item_user)

        return UserViewHolder(view)
    }



    fun setItems(list: List<User>) {
        users = list
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(viewHolder: UserViewHolder, position: Int) {
        viewHolder.bind(users[position])
    }

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: User) = with(itemView) {

            mtvName.text = item.name
            mtvUserName.text = item.username
            mtvEmail.text = item.email
            mtvPhone.text = item.phone
            mtvWebSite.text = item.website
            mcvUser.setOnClickListener { listener.onClickDetail(item) }

        }

    }

    interface DetailAdapterListener {
        fun onClickDetail(user: User)
    }

}
