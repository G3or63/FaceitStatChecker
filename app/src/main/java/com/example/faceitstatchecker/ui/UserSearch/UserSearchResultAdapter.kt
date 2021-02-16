package com.example.faceitstatchecker.ui.UserSearch

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.faceitstatchecker.R
import com.example.faceitstatchecker.responses.internal.Player
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_player_search_result.view.*

/**
 * Created by George Hart on 16/02/2021;
 */
class UserSearchResultAdapter(val layoutInflater: LayoutInflater, val picasso: Picasso) :
    RecyclerView.Adapter<UserSearchResultAdapter.UserSearchResultViewHolder>() {
    val data = mutableListOf<Player>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserSearchResultViewHolder {
        val view = layoutInflater.inflate(R.layout.row_player_search_result, parent, false)
        return UserSearchResultViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserSearchResultViewHolder, position: Int) {
        val item = data[position]
        if(item.avatar.isEmpty()){
            picasso.load(R.drawable.rgb_pheasant).into(holder.view.playerAvatar)
        } else {
            picasso.load(item.avatar).into(holder.view.playerAvatar)
        }
         holder.view.playerName.text = item.nickname
    }

    override fun getItemCount(): Int {
        return data.size
    }
    class UserSearchResultViewHolder(val view: View) : RecyclerView.ViewHolder(view)
}
