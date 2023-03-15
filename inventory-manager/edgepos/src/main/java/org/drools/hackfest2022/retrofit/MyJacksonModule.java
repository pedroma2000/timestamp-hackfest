package org.drools.hackfest2022.retrofit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.drools.ruleunits.api.DataObserver;
import org.kie.kogito.drools.core.data.FieldDataStore;
import org.kie.kogito.drools.core.data.ListDataStore;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.fasterxml.jackson.databind.type.TypeFactory;

public class MyJacksonModule extends SimpleModule {
    
    public static class RetrofitListDataStoreDeserializer extends StdSerializer<ListDataStore<?>> {
        RetrofitListDataStoreDeserializer(TypeFactory typeFactory) {
            super(typeFactory.constructRawCollectionLikeType(ListDataStore.class));
        }

        @Override
        public void serialize(ListDataStore<?> value, JsonGenerator gen, SerializerProvider provider) throws IOException {
            List<Object> results = new ArrayList<>();
            value.subscribe(DataObserver.of(results::add));
            gen.writeObject(results);
        }
    }

    public static class RetrofitFieldDataStoreDeserializer extends StdSerializer<FieldDataStore<?>> {
        RetrofitFieldDataStoreDeserializer(TypeFactory typeFactory) {
            super(typeFactory.constructParametricType(FieldDataStore.class, TypeFactory.unknownType()));
        }

        @Override
        public void serialize(FieldDataStore<?> value, JsonGenerator gen, SerializerProvider provider) throws IOException {
            List<Object> results = new ArrayList<>();
            value.subscribe(DataObserver.of(results::add));
            gen.writeObject(results.get(0));
        }
    }

    public MyJacksonModule(TypeFactory typeFactory) {
        addDefaultSerializers(typeFactory);
    }

    private void addDefaultSerializers(TypeFactory typeFactory) {
        addSerializer(new RetrofitListDataStoreDeserializer(typeFactory));
        addSerializer(new RetrofitFieldDataStoreDeserializer(typeFactory));
    }

    /*
     *             List<Object> results = new ArrayList<>();
            value.subscribe(DataObserver.of(results::add));
            gen.writeObject(results);
     */
}
