package com.enpassio.twowaydatabinding.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.enpassio.twowaydatabinding.R;
import com.enpassio.twowaydatabinding.databinding.AddToyBinding;
import com.enpassio.twowaydatabinding.utils.InjectorUtils;
import com.enpassio.twowaydatabinding.viewmodel.AddToyViewModel;
import com.enpassio.twowaydatabinding.viewmodel.AddToyViewModelFactory;

import static com.enpassio.twowaydatabinding.ui.ToyListFragment.TOY_ID;

public class AddToyFragment extends Fragment {

    private AddToyBinding binding;
    public static final int NEW_TOY = -1;

    public AddToyFragment() {
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_add_toy, container, false);

        setHasOptionsMenu(true);

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ((AppCompatActivity) requireActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        int toyId = NEW_TOY;

        Bundle bundle = getArguments();
        if (bundle != null && bundle.containsKey(TOY_ID)) {
            toyId = bundle.getInt(TOY_ID);
        }

        //Get the view model instance and pass it to the binding implementation
        AddToyViewModelFactory factory = new AddToyViewModelFactory(InjectorUtils.provideRepository(getContext()), toyId);
        AddToyViewModel viewModel = ViewModelProviders.of(this, factory).get(AddToyViewModel.class);

        binding.setViewModel(viewModel);

        if(toyId >= 0) { //Edit case
            viewModel.getChosenToy().observe(this, toyEntry -> {
                viewModel.setToyBeingModified(toyEntry);
                binding.invalidateAll();
            });
        }

        binding.fab.setOnClickListener(v -> {
            viewModel.saveToy();
            returnToListFragment();
        });
    }

    private void returnToListFragment() {
        FragmentManager fm = getFragmentManager();
        if (fm != null) {
            fm.popBackStack();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //This is for making up button in the toolbar behave like back button
        if (item.getItemId() == android.R.id.home) {
            returnToListFragment();
        }
        return super.onOptionsItemSelected(item);
    }
}
