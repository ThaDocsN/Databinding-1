package com.enpassio.databindingwithrecyclerviewkotlin.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.enpassio.databindingwithrecyclerviewkotlin.R
import com.enpassio.databindingwithrecyclerviewkotlin.databinding.FragmentDetailsBinding
import com.enpassio.databindingwithrecyclerviewkotlin.model.Product


/**
 * Created by Greta Grigutė on 2018-12-23.
 */


class DetailsFragment : androidx.fragment.app.Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentDetailsBinding>(
            inflater, R.layout.fragment_details, container, false)

        //These are for making up button work. Not related to databinding.
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        setHasOptionsMenu(true)

        /*Once we get the chosen product from the bundle,
        we pass it to the binding implementation*/
        val chosenProduct = arguments?.getParcelable<Product>(PRODUCT_KEY)
        binding.product = chosenProduct

        return binding.root
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        //This is for making up button in the toolbar behave like back button
        if (item?.itemId == android.R.id.home) {
            fragmentManager?.popBackStack()
        }
        return super.onOptionsItemSelected(item)
    }
}