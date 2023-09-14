package com.frank.gsonserialize.adapter;

import com.frank.gsonserialize.model.Account;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * @author: maxinhang.
 * @version: 2023/9/14 09:54.
 */
public class AccountV2Adapter extends TypeAdapter<Account> {
    private Gson gson;

    public AccountV2Adapter(Gson gson) {
        this.gson = gson;
    }

    @Override
    public void write(JsonWriter out, Account value) throws IOException {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("NAME",value.getName());
        jsonObject.addProperty("AGE",value.getAge());
        jsonObject.addProperty("GENDER",value.getGender());
        gson.toJson(jsonObject,out);
    }

    @Override
    public Account read(JsonReader in) throws IOException {
        JsonElement jsonElement = JsonParser.parseReader(in);
        return gson.fromJson(jsonElement,Account.class);
    }
}
