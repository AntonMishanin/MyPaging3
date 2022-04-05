package com.my.mypaging3.custom.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.my.mypaging3.databinding.ItemUserBinding
import com.my.mypaging3.library.domain.User

class UserAdapter(
    private val comparator: UserComparator = UserComparator()
) : ListAdapter<User, UserViewHolder>(comparator) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = UserViewHolder.create(parent)

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, position)
    }
}

class UserViewHolder private constructor(
    binding: ItemUserBinding
) : RecyclerView.ViewHolder(binding.root) {

    private val binding: ItemUserBinding = ItemUserBinding.bind(binding.root)

    companion object {
        fun create(parent: ViewGroup): UserViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemUserBinding.inflate(inflater, parent, false)
            return UserViewHolder(binding)
        }
    }

    fun bind(user: User?, position: Int) = with(binding) {
        binding.title.text = position.toString()
    }
}

class UserComparator : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: User, newItem: User) = oldItem == newItem
}