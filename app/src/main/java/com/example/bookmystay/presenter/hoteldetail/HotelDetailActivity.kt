package com.example.bookmystay.presenter.hoteldetail

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bookmystay.R
import com.example.bookmystay.data.hoteldetail.CommentModel
import com.example.bookmystay.data.hoteldetail.HotelDetailModel
import com.example.bookmystay.domain.network.ViewState
import com.example.bookmystay.framework.di.viewmodel.ViewModelFactory
import com.google.gson.Gson
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class HotelDetailActivity : DaggerAppCompatActivity() {

    @Inject lateinit var viewModelFactory: ViewModelFactory
    lateinit var hotelDetailViewModel: HotelDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        hotelDetailViewModel = ViewModelProviders.of(this, viewModelFactory).get(HotelDetailViewModel::class.java)
        hotelDetailViewModel.callGetHotelApi()
        hotelDetailViewModel.callGetAllCommentsApi()

        observeHotelDetail()
        observevCommentList()
    }

    private fun observeHotelDetail() {
        hotelDetailViewModel.getHotelDetailResponse().observe(this, object : Observer<ViewState<HotelDetailModel>> {
            override fun onChanged(viewState: ViewState<HotelDetailModel>?) {
                when(viewState!!.status) {
                    ViewState.Status.LOADING -> {
                        progressBar.visibility = View.VISIBLE
                    }

                    ViewState.Status.SUCCESS -> {
                        progressBar.visibility = View.GONE

                        viewState.data?.let {
                            txtNameANdLocation.text = "${it.name} - ${it.location}"
                            txtDescription.text = it.location
                            txtRating.text = "${it.rating} Ratings"
                            txtNoReview.text = "${it.numberOfReviews} customer review(s)"
                            txtPrice.text = "Rs. ${it.cost}"
                        }
                    }

                    ViewState.Status.ERROR -> {
                        handleError(viewState.error)
                    }
                }
            }
        })
    }

    private fun observevCommentList() {
        hotelDetailViewModel.getAllCommentsResponse().observe(this, object : Observer<ViewState<List<CommentModel>>> {
            override fun onChanged(viewState: ViewState<List<CommentModel>>?) {
                when(viewState!!.status) {
                    ViewState.Status.LOADING -> {
                        progressBar.visibility = View.VISIBLE
                    }

                    ViewState.Status.SUCCESS -> {
                        progressBar.visibility = View.GONE

                        val commentAdapter = CommentAdapter(viewState.data!!)
                        rvComments.layoutManager = LinearLayoutManager(this@HotelDetailActivity,
                            RecyclerView.VERTICAL, false)
                        rvComments.adapter = commentAdapter
                    }

                    ViewState.Status.ERROR -> {
                        progressBar.visibility = View.GONE
                        handleError(viewState.error)
                    }
                }
            }
        })
    }

    private fun handleError(error: Throwable?) {
        progressBar.visibility = View.GONE
        llDisplay.visibility = View.GONE
        txtErrorMessage.visibility = View.VISIBLE
        txtErrorMessage.text = error?.message
    }
}
