package com.example.notes.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;

import android.os.Bundle;

import com.example.notes.R;
import com.example.notes.domain.City;
import com.example.notes.ui.details.CityDetailsFragment;
import com.example.notes.ui.list.CitiesListFragment;

public class MainActivity extends AppCompatActivity {

    private static final String ARG_CITY = "ARG_CITY";

    private City selectedCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();

        if (!(fragmentManager.findFragmentById(R.id.fragment_container) instanceof CitiesListFragment)) {
            fragmentManager.popBackStack();
        }

        boolean isLandscape = getResources().getBoolean(R.bool.is_landscape);

        if (savedInstanceState != null && savedInstanceState.containsKey(ARG_CITY)) {
            selectedCity = savedInstanceState.getParcelable(ARG_CITY);

            if (isLandscape) {
                Bundle bundle = new Bundle();
                bundle.putParcelable(ARG_CITY, selectedCity);

                fragmentManager.setFragmentResult(CityDetailsFragment.KEY_CITIES_LIST_DETAILS, bundle);
            } else {
                CityDetailsFragment detailsFragment = CityDetailsFragment.newInstance(selectedCity);

                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, detailsFragment)
                        .addToBackStack(null)
                        .commit();
            }
        }

        getSupportFragmentManager().setFragmentResultListener(CitiesListFragment.KEY_CITIES_LIST_ACTIVITY, this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {

                selectedCity = result.getParcelable(CitiesListFragment.ARG_CITY);

                if (isLandscape) {

                    fragmentManager.setFragmentResult(CityDetailsFragment.KEY_CITIES_LIST_DETAILS, result);
                } else {
                    CityDetailsFragment detailsFragment = CityDetailsFragment.newInstance(selectedCity);

                    fragmentManager.beginTransaction()
                            .replace(R.id.fragment_container, detailsFragment)
                            .addToBackStack(null)
                            .commit();
                }
            }
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        if (selectedCity != null) {
            outState.putParcelable(ARG_CITY, selectedCity);
        }
        super.onSaveInstanceState(outState);
    }
}