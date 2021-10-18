package com.example.notes.domain;

import com.example.notes.R;

import java.util.ArrayList;
import java.util.List;

public class InMemoryCitiesRepository implements CitiesRepository{
    @Override
    public List<City> getCities() {
        ArrayList<City> result = new ArrayList<>();
        result.add(new City(R.string.ebrg, R.string.ebrg2));
        result.add(new City(R.string.msc, R.string.msc2));
        result.add(new City(R.string.nsk, R.string.nsk2));
        return result;
    }
}
