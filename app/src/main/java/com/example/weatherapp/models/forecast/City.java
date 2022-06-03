package com.example.weatherapp.models.forecast;

import com.google.gson.annotations.SerializedName;

public class City{

	@SerializedName("country")
	private String country;

	@SerializedName("coord")
	private com.example.weatherapp.models.forecast.Coord coord;

	@SerializedName("sunrise")
	private int sunrise;

	@SerializedName("timezone")
	private int timezone;

	@SerializedName("sunset")
	private int sunset;

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private int id;

	@SerializedName("population")
	private int population;

	public void setCountry(String country){
		this.country = country;
	}

	public String getCountry(){
		return country;
	}

	public void setCoord(com.example.weatherapp.models.forecast.Coord coord){
		this.coord = coord;
	}

	public com.example.weatherapp.models.forecast.Coord getCoord(){
		return coord;
	}

	public void setSunrise(int sunrise){
		this.sunrise = sunrise;
	}

	public int getSunrise(){
		return sunrise;
	}

	public void setTimezone(int timezone){
		this.timezone = timezone;
	}

	public int getTimezone(){
		return timezone;
	}

	public void setSunset(int sunset){
		this.sunset = sunset;
	}

	public int getSunset(){
		return sunset;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setPopulation(int population){
		this.population = population;
	}

	public int getPopulation(){
		return population;
	}
}