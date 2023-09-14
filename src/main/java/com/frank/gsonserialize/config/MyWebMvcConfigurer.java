package com.frank.gsonserialize.config;

import com.frank.gsonserialize.adapter.AccountAdapter;
import com.frank.gsonserialize.adapter.AccountV2Adapter;
import com.frank.gsonserialize.adapter.StringAdapter;
import com.frank.gsonserialize.model.Account;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.util.CollectionUtils;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: maxinhang.
 * @version: 2023/9/14 10:44.
 */
@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer {
    private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    /**
     * 配置消息转换器, gson的转换器作为第一个消息转换器.
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(0, getGsonHttpMessageConverter());
    }

    private GsonHttpMessageConverter getGsonHttpMessageConverter() {
        GsonHttpMessageConverter gsonHttpMessageConverter = new GsonHttpMessageConverter();
        fillGsonConfig(gsonHttpMessageConverter);
        fillMediaType(gsonHttpMessageConverter);
        return gsonHttpMessageConverter;
    }

    private void fillMediaType(GsonHttpMessageConverter gsonHttpMessageConverter) {
        List<MediaType> supportedMediaTypes = new ArrayList<>();
        supportedMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        supportedMediaTypes.add(MediaType.APPLICATION_FORM_URLENCODED);
        supportedMediaTypes.add(MediaType.TEXT_HTML);
        gsonHttpMessageConverter.setSupportedMediaTypes(supportedMediaTypes);
    }

    private void fillGsonConfig(GsonHttpMessageConverter gsonHttpMessageConverter) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.serializeNulls();
        gsonBuilder.setDateFormat(DEFAULT_DATE_FORMAT);
//        gsonBuilder.registerTypeAdapter(Account.class,new AccountAdapter());
        gsonBuilder.registerTypeAdapterFactory(new AccountV2AdapterFactory());
        gsonBuilder.registerTypeAdapter(String.class,new StringAdapter());
        Gson gson = gsonBuilder.create();
        gsonHttpMessageConverter.setGson(gson);
    }


    public static class AccountV2AdapterFactory implements TypeAdapterFactory {
        @Override
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
            if (Account.class.equals(type.getRawType())) {
                return (TypeAdapter<T>) new AccountV2Adapter(gson);
            }
            return null;
        }
    }
}
