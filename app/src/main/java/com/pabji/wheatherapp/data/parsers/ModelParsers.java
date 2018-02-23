package com.pabji.wheatherapp.data.parsers;

import com.pabji.wheatherapp.data.models.WeatherObservation;
import com.pabji.wheatherapp.data.net.entities.AlternateNameEntity;
import com.pabji.wheatherapp.data.net.entities.GeonameEntity;
import com.pabji.wheatherapp.data.models.CityModel;
import com.pabji.wheatherapp.data.net.entities.ObservationEntity;

import java.util.ArrayList;
import java.util.List;


public class ModelParsers {

    public static CityModel toModel(GeonameEntity entity, String language){

        CityModel model = new CityModel();

        if(entity.lat != null && entity.lng != null){
            model.setLatitude(Double.valueOf(entity.lat));
            model.setLongitude(Double.valueOf(entity.lng));
        }

        if(entity.bbox != null){
            model.setEast(entity.bbox.east);
            model.setNorth(entity.bbox.north);
            model.setWest(entity.bbox.west);
            model.setSouth(entity.bbox.south);
        }

        if(entity.alternateNameEntities != null){
            for (AlternateNameEntity nameEntity: entity.alternateNameEntities) {
                if (nameEntity.lang.equals(language)) {
                    model.setName(nameEntity.name);
                    break;
                }
            }
        }

        return model;
    }

    public static WeatherObservation toModel (ObservationEntity observationEntity){
        WeatherObservation observation = new WeatherObservation();
        if(observationEntity.temperature != null){
            observation.setTemperature(Integer.valueOf(observationEntity.temperature));
        }

        if(observationEntity.clouds != null){
            observation.setClouds(observationEntity.clouds);
        }

        if(observationEntity.humidity != null){
            observation.setHumidity(observationEntity.humidity);
        }
        return observation;
    }


    public static List<CityModel> toListModel (List<GeonameEntity> entityList, String language){
        List<CityModel> list = new ArrayList<>();
        for(GeonameEntity entity : entityList){
            CityModel model = ModelParsers.toModel(entity,language);
            if (model.getName() != null && !list.contains(model)){
                list.add(model);
            }
        }
        return list;
    }
}
