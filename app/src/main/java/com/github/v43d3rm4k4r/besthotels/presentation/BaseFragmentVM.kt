package com.github.v43d3rm4k4r.besthotels.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import javax.inject.Inject

/**
 * Base for fragment working with the [ViewBinding], [ViewModel] and Dagger2.
 */
abstract class BaseFragmentVM<VB : ViewBinding, VM : ViewModel, VMF : ViewModelProvider.Factory>(
    inflate: Inflate<VB>
) : BaseFragment<VB>(inflate) {

    @Inject
    protected lateinit var viewModelFactory: VMF
    protected lateinit var viewModel: VM

    protected abstract fun viewModelClass(): Class<VM>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[viewModelClass()]
    }
}