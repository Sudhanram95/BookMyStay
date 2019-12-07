package com.example.bookmystay.presenter.hoteldetail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bookmystay.R
import com.example.bookmystay.data.hoteldetail.CommentModel
import com.example.bookmystay.data.hoteldetail.HotelDetailModel
import com.example.bookmystay.domain.network.ViewState
import com.example.bookmystay.framework.di.viewmodel.ViewModelFactory
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_hotel_detail.view.*
import javax.inject.Inject

class HotelDetailFragment: DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var hotelDetailViewModel: HotelDetailViewModel
    var commentList = ArrayList<CommentModel>()
    private val commentAdapter: CommentAdapter by lazy {
        CommentAdapter(commentList)
    }

    lateinit var rootView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        hotelDetailViewModel = ViewModelProviders.of(this, viewModelFactory).get(HotelDetailViewModel::class.java)

        observeHotelDetail()
        observevCommentList()
        observeSavedComment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_hotel_detail, container, false)

        hotelDetailViewModel.callGetHotelApi()
        hotelDetailViewModel.callGetAllCommentsApi()

        initializeView()

        return rootView
    }

    private fun initializeView() {
        rootView.rvComments.layoutManager = LinearLayoutManager(context,
            RecyclerView.VERTICAL, false)
        rootView.rvComments.adapter = commentAdapter
    }

    private fun observeHotelDetail() {
        hotelDetailViewModel.getHotelDetailResponse().observe(this, object : Observer<ViewState<HotelDetailModel>> {
            override fun onChanged(viewState: ViewState<HotelDetailModel>?) {
                when(viewState) {
                    is ViewState.Loading -> {
                        rootView.progressBar.visibility = View.VISIBLE
                    }

                    is ViewState.Success -> {
                        rootView.progressBar.visibility = View.GONE

                        rootView.txtNameANdLocation.text = "${viewState.data.name} - ${viewState.data.location}"
                        rootView.txtDescription.text = viewState.data.location
                        rootView.txtRating.text = "${viewState.data.rating} Ratings"
                        rootView.txtNoReview.text = "${viewState.data.numberOfReviews} customer review(s)"
                        rootView.txtPrice.text = "Rs. ${viewState.data.cost}"
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
                        rootView.progressBar.visibility = View.VISIBLE
                    }

                    is ViewState.Success -> {
                        rootView.progressBar.visibility = View.GONE

                        commentAdapter.commentList.addAll(viewState.data)
                        commentAdapter.notifyDataSetChanged()
                    }

                    is ViewState.Error -> {
                        handleError(viewState.error)
                    }
                }
            }
        })
    }

    private fun observeSavedComment() {
        hotelDetailViewModel.getCommentModel().observe(this, object : Observer<ViewState<CommentModel>> {
            override fun onChanged(viewState: ViewState<CommentModel>?) {
                when(viewState) {
                    is ViewState.Success -> {
                        Log.e("HOtelDetailFrag", "Success")
                        commentAdapter.commentList.add(0, viewState.data)
                        commentAdapter.notifyDataSetChanged()
                    }

                    is ViewState.Error -> {
                        Toast.makeText(context, viewState.error.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

    private fun handleError(error: Throwable?) {
        rootView.progressBar.visibility = View.GONE
        rootView.llDisplay.visibility = View.GONE
        rootView.txtErrorMessage.visibility = View.VISIBLE
        rootView.txtErrorMessage.text = error?.message
    }
}