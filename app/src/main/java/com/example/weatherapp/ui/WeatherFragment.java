package com.example.weatherapp.ui;

import android.location.Location;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.weatherapp.R;
import com.example.weatherapp.adapters.ForecastWeatherAdapter;
import com.example.weatherapp.databinding.FragmentWeatherBinding;
import com.example.weatherapp.models.current.CurrentResponseModel;
import com.example.weatherapp.models.forecast.ForecastResponseModel;
import com.example.weatherapp.utils.Constants;
import com.example.weatherapp.utils.LocationPermissionService;
import com.example.weatherapp.utils.WeatherHelperFunctions;
import com.example.weatherapp.viewmodels.WeatherViewModel;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.squareup.picasso.Picasso;

public class WeatherFragment extends Fragment {

    private FragmentWeatherBinding binding;
    private final String TAG = WeatherFragment.class.getSimpleName();
    private WeatherViewModel weatherViewModel;
    private FusedLocationProviderClient providerClient;
    private ActivityResultLauncher<String> launcher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), result -> {
                if (result) {
                    //detect user location
                    detectUserLocation();
                } else {
                    //show dialog and explain why you need this permission
                }
            });


    public WeatherFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = FragmentWeatherBinding.inflate(inflater,container,false);
        //viewModel initialize before check location permission----------
        weatherViewModel = new ViewModelProvider(this)
                .get(WeatherViewModel.class);

        providerClient = LocationServices
                .getFusedLocationProviderClient(getActivity());
        checkLocationPermission();

        ForecastWeatherAdapter forecastWeatherAdapter = new ForecastWeatherAdapter();
        binding.recyclerViewForecast.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        binding.recyclerViewForecast.setAdapter(forecastWeatherAdapter);

        weatherViewModel.getCurrentLiveDate().observe(getActivity(), currentResponseModel -> {
            updateUI(currentResponseModel);
        });

        weatherViewModel.getForecastLiveDate().observe(getActivity(), new Observer<ForecastResponseModel>() {
            @Override
            public void onChanged(ForecastResponseModel forecastResponseModel) {
                forecastWeatherAdapter.submitNewForecastList(forecastResponseModel);
            }
        });
        return binding.getRoot();
    }

    private void updateUI(CurrentResponseModel currentResponseModel) {
        binding.tvCurrentDate.setText(WeatherHelperFunctions.getFormattedDateTime(currentResponseModel.getDt(), "MMM dd, yyyy hh.mm aa"));
        binding.tvCurrentAdress.setText(currentResponseModel.getName() + "," + currentResponseModel.getSys().getCountry());
        binding.tvCurrentTemp.setText(
                String.format("%.0f\u00B0 C", currentResponseModel.getMain().getTemp())
        );

        binding.tvCurrentFeelsLike.setText(
                String.format("Feels like %.0f\u00B0 C", currentResponseModel.getMain().getFeelsLike())
        );
        binding.tvCurrentMaxMin.setText(
                String.format("Max %.0f\u00B0 Min %.0f\u00b0", currentResponseModel.getMain().getTempMax(), currentResponseModel.getMain().getTempMin())
        );

        final String iconUrl = Constants.ICON_PREFIX + currentResponseModel.getWeather().get(0).getIcon() + Constants.ICON_SUFFIX;
        Picasso.get().load(iconUrl).into(binding.ivCurrentIcon);
        binding.tvCurrentCondition.setText(currentResponseModel.getWeather().get(0).getDescription());
        binding.tvCurrentHumidity.setText("Humidity " + currentResponseModel.getMain().getHumidity() + "%");
        binding.tvCurrentPressure.setText("Pressure " + currentResponseModel.getMain().getPressure() + "hPa");

    }

    private void checkLocationPermission() {
        if (LocationPermissionService.isLocationPermissionGranted(getContext())) {
            //detect user location
            detectUserLocation();
        } else {
            LocationPermissionService.requestLocationPermission(launcher);
        }
    }

    private void detectUserLocation() {
        providerClient.getLastLocation()
                .addOnSuccessListener(new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location == null) return;
                        // double lat = location.getLatitude();
                        // double lng = location.getLongitude();
                        weatherViewModel.setLocation(location);
                        weatherViewModel.loadData();

                        //Log.e("WeatherApp","Lat:"+lat+",lon:"+lng);
                    }
                });
    }
}