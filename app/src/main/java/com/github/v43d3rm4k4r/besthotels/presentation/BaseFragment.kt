package com.github.v43d3rm4k4r.besthotels.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import javax.inject.Inject

typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

/**
 * Variant of base fragment implementation.
 */
abstract class BaseFragment<VB : ViewBinding, VM : ViewModel, VMF : ViewModelProvider.Factory>(
    private val inflate: Inflate<VB>
) : Fragment() {

    private   var _binding: VB? = null
    protected val binding get() = _binding!!

    @Inject
    protected lateinit var viewModelFactory: VMF
    protected lateinit var viewModel: VM

    //@Inject
    //protected lateinit var navController: Provider<NavController>

    protected abstract fun viewModelClass(): Class<VM>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = viewModelFactory.create(viewModelClass())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = inflate(inflater, container, false)
        return binding.root
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        initialize()
//        setupListeners()
//        observe()
//    }
//
//    abstract fun initialize()
//
//    abstract fun setupListeners()
//
//    abstract fun observe()

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}