package com.ballboycorp.tingting.main.more

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import com.ballboycorp.tingting.R
import com.ballboycorp.tingting.base.BaseFragment
import com.ballboycorp.tingting.databinding.FragmentMoreBinding
import com.ballboycorp.tingting.utils.extensions.bind

/**
 * Created by musooff on 08/04/2019.
 */

class MoreFragment: BaseFragment() {

    companion object {

        private const val FRAGMENT_TAG = "more_fragment"

        fun replace(fragmentActivity: FragmentActivity) {
            val manager = fragmentActivity.supportFragmentManager
            val transaction = manager.beginTransaction()
            val fragment = MoreFragment()
            transaction.replace(R.id.container_main, fragment, FRAGMENT_TAG)
                    .commit()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = bind<FragmentMoreBinding>(inflater, R.layout.fragment_more, container)
        return binding.root
    }
}