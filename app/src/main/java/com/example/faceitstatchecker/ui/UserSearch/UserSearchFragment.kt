package com.example.faceitstatchecker.ui.UserSearch

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.faceitstatchecker.R
import com.example.faceitstatchecker.ui.main.MainFragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.user_search_fragment.*

/**
 * Created by George Hart on 16/02/2021;
 */
class UserSearchFragment : Fragment(){
    val viewModel: UserSearchViewModel by viewModels()
    val userResultAdapter: UserSearchResultAdapter by lazy { UserSearchResultAdapter(layoutInflater, Picasso.with(context)) }

    companion object {
        fun newInstance() = UserSearchFragment()
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.user_search_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userResultList.layoutManager = LinearLayoutManager(context)
        userResultList.adapter = userResultAdapter
        viewModel.searchResultsList.observe(viewLifecycleOwner, Observer {
            userResultAdapter.data.clear()
            userResultAdapter.data.addAll(it)
            userResultAdapter.notifyDataSetChanged()
        })
        userSearchBox.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                //ignore
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.userInputChanged(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {
                //ignore
            }

        })
    }
}