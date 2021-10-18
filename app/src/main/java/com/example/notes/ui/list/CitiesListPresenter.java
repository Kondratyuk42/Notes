package com.example.notes.ui.list;

import com.example.notes.domain.CitiesRepository;

public class CitiesListPresenter {
    private final CitiesListView view;

    private final CitiesRepository repository;

    public CitiesListPresenter(CitiesListView view, CitiesRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    public void requestCities() {
        view.showCities(repository.getCities());
    }
}
