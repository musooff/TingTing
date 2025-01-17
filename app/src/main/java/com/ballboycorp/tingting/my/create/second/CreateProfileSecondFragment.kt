package com.ballboycorp.tingting.my.create.second

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ballboycorp.tingting.R
import com.ballboycorp.tingting.base.BaseFragment
import com.ballboycorp.tingting.databinding.FragmentCreateProfileSecondBinding
import com.ballboycorp.tingting.my.create.CreateProfileActivity
import com.ballboycorp.tingting.utils.extensions.bind
import com.ballboycorp.tingting.utils.extensions.getViewModel

/**
 * Created by musooff on 15/04/2019.
 */

class CreateProfileSecondFragment: BaseFragment() {

    companion object {
        private const val REQUEST_GALLERY = 1
    }

    private val viewModel by lazy { getViewModel<CreateProfileSecondViewModel>() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = bind<FragmentCreateProfileSecondBinding>(inflater, R.layout.fragment_create_profile_second, container)
        binding.viewModel = viewModel
        binding.clickHandler = ClickHandler()
        return binding.root
    }

    inner class ClickHandler {
        fun onClickDone() {
            (activity as CreateProfileActivity).onSignUp(viewModel.thumb.takeIf { viewModel.selectedImage == 1 })
        }

        fun onClickImage(selectedImage: Int) {
            when(selectedImage) {
                0 -> viewModel.selectedImage = selectedImage
                1 -> {
                    val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
                    startActivityForResult(intent, REQUEST_GALLERY)
                }
            }
        }
    }

    fun setGender(gender: Int) {
        viewModel.gender = gender
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_GALLERY) {
            if (resultCode == Activity.RESULT_OK) {
                viewModel.thumb = data?.data
                viewModel.selectedImage = 1
            }
        }
    }
}