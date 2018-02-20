package beans;

import com.google.gson.JsonDeserializer;
import com.google.gson.JsonObject;

import java.sql.Timestamp;

public interface PlanBeanDeserializer {
    // change serialization for specific types
    JsonDeserializer<PlanBean> deserializer = (json, typeOfT, context) -> {
        JsonObject jsonObject = json.getAsJsonObject();

        // {"clientId":"1-1","concesionaria":"C1","concesionariaId":1,"cuotasPagadas":60,"documento":100,"fechaAlta":{"nanos":0},"fechaUltimoUpdate":{"nanos":797000000},"id":1,"vehiculo":"Corsa"}
        PlanBean plan = new PlanBean(
                jsonObject.get("id").getAsInt()
                , jsonObject.get("cuotasPagadas").getAsInt()
                , jsonObject.get("vehiculo").getAsString()
                , jsonObject.get("concesionaria").getAsString()
                , jsonObject.get("concesionariaId").getAsInt()
                , jsonObject.get("documento").getAsLong()
                , jsonObject.get("clientId").getAsString()
                , new Timestamp(jsonObject.get("fechaAlta").getAsJsonObject().get("nanos").getAsLong())
                , new Timestamp(jsonObject.get("fechaUltimoUpdate").getAsJsonObject().get("nanos").getAsLong())
        );
        return plan;
    };
}
