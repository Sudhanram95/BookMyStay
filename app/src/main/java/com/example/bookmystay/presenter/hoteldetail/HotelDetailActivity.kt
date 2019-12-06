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
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class HotelDetailActivity : DaggerAppCompatActivity() {

    @Inject lateinit var viewModelFactory: ViewModelFactory
    lateinit var hotelDetailViewModel: HotelDetailViewModel
//    lateinit var commentList: List<CommentModel>
//    private val commentAdapter: CommentAdapter by lazy {
//        CommentAdapter(commentList)
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        hotelDetailViewModel = ViewModelProviders.of(this, viewModelFactory).get(HotelDetailViewModel::class.java)
        hotelDetailViewModel.callGetHotelApi()
        hotelDetailViewModel.callGetAllCommentsApi()

        observeHotelDetail()
        observevCommentList()
    }

//    private fun initializeView() {
//        rvComments.layoutManager = LinearLayoutManager(this@HotelDetailActivity,
//            RecyclerView.VERTICAL, false)
//        rvComments.adapter = commentAdapter
//    }

    private fun observeHotelDetail() {
        hotelDetailViewModel.getHotelDetailResponse().observe(this, object : Observer<ViewState<HotelDetailModel>> {
            override fun onChanged(viewState: ViewState<HotelDetailModel>?) {
                when(viewState) {
                    is ViewState.Loading -> {
                        progressBar.visibility = View.VISIBLE
                    }

                    is ViewState.Success -> {
                        progressBar.visibility = View.GONE

                        txtNameANdLocation.text = "${viewState.data.name} - ${viewState.data.location}"
                        txtDescription.text = viewState.data.location
                        txtRating.text = "${viewState.data.rating} Ratings"
                        txtNoReview.text = "${viewState.data.numberOfReviews} customer review(s)"
                        txtPrice.text = "Rs. ${viewState.data.cost}"
                    }

                    is ViewState.Error -> {
                        handleError(viewState.error)
                    }
                }
            }
        })
    }

    private fun observevCommentList() {
        hotelDetailViewModel.getAllCommentsResponse().observe(this, object : Observer<ViewState<List<CommentModel>>> {
            override fun onChanged(viewState: ViewState<List<CommentModel>>?) {
                when(viewState) {
                    is ViewState.Loading -> {
                        progressBar.visibility = View.VISIBLE
                    }

                    is ViewState.Success -> {
                        progressBar.visibility = View.GONE

                        val commentAdapter = CommentAdapter(viewState.data)
                        rvComments.layoutManager = LinearLayoutManager(this@HotelDetailActivity,
                            RecyclerView.VERTICAL, false)
                        rvComments.adapter = commentAdapter
                    }

                    is ViewState.Error -> {
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
