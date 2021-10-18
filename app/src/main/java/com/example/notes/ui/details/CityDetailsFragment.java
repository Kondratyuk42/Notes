package com.example.notes.ui.details;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import com.example.notes.R;
import com.example.notes.domain.City;

public class CityDetailsFragment extends Fragment {
    public static final String ARG_CITY = "ARG_CITY";

    public static final String KEY_CITIES_LIST_DETAILS = "KEY_CITIES_LIST_DETAILS";

    public CityDetailsFragment() {
        super(R.layout.fragment_city_details);
    }

    public static CityDetailsFragment newInstance(City city) {

        Bundle args = new Bundle();
        args.putParcelable(ARG_CITY, city);

        CityDetailsFragment fragment = new CityDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView coatOfArms = view.findViewById(R.id.coat_of_arms);
        TextView cityName = view.findViewById(R.id.city_name);

        if (getArguments() != null && getArguments().containsKey(ARG_CITY)) {
            City city = getArguments().getParcelable(ARG_CITY);

            coatOfArms.setImageResource(city.getCoatOfArms());

            cityName.setText(city.getCityName());
        }

        getParentFragmentManager().setFragmentResultListener(CityDetailsFragment.KEY_CITIES_LIST_DETAILS, getViewLifecycleOwner(), new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {

                City city1 = result.getParcelable(ARG_CITY);

                coatOfArms.setImageResource(city1.getCoatOfArms());

                cityName.setText(city1.getCityName());

            }
        });

    }
}
