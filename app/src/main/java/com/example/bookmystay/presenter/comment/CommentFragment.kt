package com.example.bookmystay.presenter.comment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.bookmystay.R
import com.example.bookmystay.data.hoteldetail.CommentModel
import com.example.bookmystay.domain.network.ViewState
import com.example.bookmystay.framework.di.viewmodel.ViewModelFactory
import com.example.bookmystay.presenter.hoteldetail.CommentAdapter
import com.example.bookmystay.presenter.hoteldetail.HotelDetailViewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_comment.view.*
import javax.inject.Inject

class CommentFragment: DaggerFragment() {

    @Inject lateinit var viewModelFactory: ViewModelFactory
    lateinit var hotelDetailViewModel: HotelDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        hotelDetailViewModel = ViewModelProviders.of(activity!!, viewModelFactory).get(HotelDetailViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_comment, container, false)

        rootView.btnSubmit.setOnClickListener {
            hotelDetailViewModel.saveComment(
                rootView.edtName.text.toString(),
                rootView.edtComment.text.toString()
            )
        }

        return rootView
    }
}