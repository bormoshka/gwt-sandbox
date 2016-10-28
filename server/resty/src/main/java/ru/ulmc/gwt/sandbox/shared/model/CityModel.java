package ru.ulmc.gwt.sandbox.shared.model;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.user.client.rpc.IsSerializable;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;


public class CityModel implements IsSerializable {
    private static Long idCount = 0L; // это хак
    private Long id;
    private String name;
    private Long population;
    private Float size;
    private Long density;
    private String country;


    public CityModel() {
    }

    public CityModel(String name, String country, Long population, Float size) {
        this.id = idCount++;
        this.name = name;
        this.population = population;
        this.size = size;
        this.density = (long)(population/size);
        this.country = country;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPopulation() {
        return population;
    }

    public void setPopulation(Long population) {
        this.population = population;
    }

    public Float getSize() {
        return size;
    }

    public void setSize(Float size) {
        this.size = size;
    }

    public Long getDensity() {
        return density;
    }

    public void setDensity(Long density) {
        this.density = density;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public interface CityProperties extends PropertyAccess<CityModel> {
        @Editor.Path("id")
        ModelKeyProvider<CityModel> key();

        ValueProvider<CityModel, String> name();

        ValueProvider<CityModel, String> country();

        ValueProvider<CityModel, Long> density();

        ValueProvider<CityModel, Float> size();

        ValueProvider<CityModel, Long> population();
    }

}
