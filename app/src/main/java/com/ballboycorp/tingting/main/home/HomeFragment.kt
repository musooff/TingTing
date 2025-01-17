package com.ballboycorp.tingting.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import com.ballboycorp.tingting.R
import com.ballboycorp.tingting.base.BaseFragment
import com.ballboycorp.tingting.common.adapter.ImagePagerAdapter
import com.ballboycorp.tingting.databinding.FragmentHomeBinding
import com.ballboycorp.tingting.liked.LikedActivity
import com.ballboycorp.tingting.main.home.adapter.ViewPagerAdapter
import com.ballboycorp.tingting.pocha.details.PochaDetailsActivity
import com.ballboycorp.tingting.qr.QRScanActivity
import com.ballboycorp.tingting.recent.RecentActivity
import com.ballboycorp.tingting.utils.extensions.bind
import com.ballboycorp.tingting.utils.extensions.getViewModel
import com.ballboycorp.tingting.utils.extensions.observeIfTrue
import com.ballboycorp.tingting.utils.extensions.startActivity
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * Created by musooff on 08/04/2019.
 */

class HomeFragment: BaseFragment() {

    companion object {

        private const val FRAGMENT_TAG = "home_fragment"

        fun replace(fragmentActivity: FragmentActivity) {
            val manager = fragmentActivity.supportFragmentManager
            val transaction = manager.beginTransaction()
            val fragment = HomeFragment()
            transaction.replace(R.id.container_main, fragment, FRAGMENT_TAG)
                    .commit()
        }
    }

    private val viewModel by lazy { getViewModel<HomeViewModel>() }

    private val viewPagerAdapter = ImagePagerAdapter()



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = bind<FragmentHomeBinding>(inflater, R.layout.fragment_home, container)
        binding.viewModel = viewModel
        binding.clickHandler = ClickHandler()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vp_main.adapter = viewPagerAdapter
        tabs_vp_main.setupWithViewPager(vp_main)

        initialize()
    }

    private fun initialize(){
        initializeViewModel()

        viewPagerAdapter.submitList(arrayListOf("", "", ""))
    }

    private fun initializeViewModel() {
        viewModel.qrScanPageRequest.observeIfTrue(this) {
            startActivity<QRScanActivity>()
        }
    }

    inner class ClickHandler {

        fun onClickMoreRecent() {
            startActivity<RecentActivity>()
        }

        fun onClickMoreLiked() {
            startActivity<LikedActivity>()
        }

        fun onClickQr() {
            startActivity<QRScanActivity>()
        }

        fun onClickPocha() {
            startActivity<PochaDetailsActivity>()
        }
    }
}