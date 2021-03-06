package com.udacity.asteroid.data.mapper

import com.udacity.asteroid.data.response.AsteroidResponse
import com.udacity.asteroid.data.storage.entity.AsteroidEntity
import com.udacity.asteroid.domain.model.AsteroidModel

object AsteroidMapper {

    fun transformAsteroidModelToEntity(asteroidModel: AsteroidModel): AsteroidEntity {
        return AsteroidEntity(
            asteroidModel.id,
            asteroidModel.codename,
            asteroidModel.closeApproachDate,
            asteroidModel.absoluteMagnitude,
            asteroidModel.estimatedDiameter,
            asteroidModel.relativeVelocity,
            asteroidModel.distanceFromEarth,
            asteroidModel.isPotentiallyHazardous
        )
    }

    fun transformEntityToModel(asteroidEntityList: List<AsteroidEntity>): List<AsteroidModel> {
        val asteroidModelList = mutableListOf<AsteroidModel>()

        asteroidEntityList.forEach {
            asteroidModelList.add(transformAsteroidEntityToModel(it))
        }
        return asteroidModelList
    }

    private fun transformAsteroidEntityToModel(asteroidEntity: AsteroidEntity): AsteroidModel {
        return AsteroidModel(
            asteroidEntity.id,
            asteroidEntity.codename,
            asteroidEntity.closeApproachDate,
            asteroidEntity.absoluteMagnitude,
            asteroidEntity.estimatedDiameter,
            asteroidEntity.relativeVelocity,
            asteroidEntity.distanceFromEarth,
            asteroidEntity.isPotentiallyHazardous
        )
    }

    fun transformResponseToModel(asteroidResponseList: List<AsteroidResponse>): List<AsteroidModel> {
        val asteroidModelList = mutableListOf<AsteroidModel>()

        asteroidResponseList.forEach {
            asteroidModelList.add(transformAsteroidResponseToModel(it))
        }
        return asteroidModelList
    }

    private fun transformAsteroidResponseToModel(asteroidResponse: AsteroidResponse): AsteroidModel {
        return AsteroidModel(
            asteroidResponse.id,
            asteroidResponse.codename,
            asteroidResponse.closeApproachDate,
            asteroidResponse.absoluteMagnitude,
            asteroidResponse.estimatedDiameter,
            asteroidResponse.relativeVelocity,
            asteroidResponse.distanceFromEarth,
            asteroidResponse.isPotentiallyHazardous
        )
    }

}