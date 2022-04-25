package com.levelabdev.dictionary.data.db

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.reflect.TypeToken
import com.levelabdev.dictionary.data.util.JsonParser
import com.levelabdev.dictionary.domain.model.MeaningModel
import javax.inject.Inject

@ProvidedTypeConverter
class ListConverter(
    private val jsonParser: JsonParser
){
    @TypeConverter
    fun convertJSONToList(json: String): List<MeaningModel>{
        return jsonParser.fromJson<ArrayList<MeaningModel>>(
            json,
            object: TypeToken<ArrayList<MeaningModel>>(){}.type
        ) ?: emptyList()
    }

    @TypeConverter
    fun convertListToJSON(list: List<MeaningModel>):String {
        return jsonParser.toJson(
            list,
            object: TypeToken<ArrayList<MeaningModel>>(){}.type
        ) ?: "[]"
    }
}