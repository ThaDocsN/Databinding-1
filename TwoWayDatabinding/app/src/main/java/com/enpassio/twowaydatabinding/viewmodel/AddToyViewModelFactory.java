package com.enpassio.twowaydatabinding.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.enpassio.twowaydatabinding.data.ToyRepository;
import com.enpassio.twowaydatabinding.data.model.ToyEntry;

public class AddToyViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private ToyRepository mRepo;
    private ToyEntry mChosenToy;

    public AddToyViewModelFactory(ToyRepository repo, ToyEntry chosenToy) {
        mRepo = repo;
        mChosenToy = chosenToy;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        //noinspection unchecked
        return (T) new AddToyViewModel(mRepo, mChosenToy);
    }
}

