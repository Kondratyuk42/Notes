package com.example.notes.ui.list;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.notes.R;
import com.example.notes.domain.City;
import com.example.notes.domain.InMemoryCitiesRepository;

import java.util.List;

public class CitiesListFragment extends Fragment implements CitiesListView{
    public static final String KEY_CITIES_LIST_ACTIVITY = "KEY_CITIES_LIST_ACTIVITY";
    public static final String ARG_CITY = "ARG_CITY";


    private LinearLayout citiesListRoot;

    private CitiesListPresenter presenter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new CitiesListPresenter(this, new InMemoryCitiesRepository());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cities_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        citiesListRoot = view.findViewById(R.id.cities_root);

        presenter.requestCities();
    }

    @Override
    public void showCities(List<City> cities) {

        for (City city : cities) {
            View itemView = LayoutInflater.from(requireContext()).inflate(R.layout.item_city, citiesListRoot, false);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Bundle bundle = new Bundle();
                    bundle.putParcelable(ARG_CITY, city);

                    getParentFragmentManager()
                            .setFragmentResult(KEY_CITIES_LIST_ACTIVITY, bundle);
                }
            });

            ImageView coatOfArms = itemView.findViewById(R.id.coat_of_arms);
            coatOfArms.setImageResource(city.getCoatOfArms());

            TextView cityName = itemView.findViewById(R.id.city_name);
            cityName.setText(city.getCityName());

            citiesListRoot.addView(itemView);
        }
    }
}
