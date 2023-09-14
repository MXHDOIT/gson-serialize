package com.frank.gsonserialize.adapter;

import com.frank.gsonserialize.model.Account;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * @author: maxinhang.
 * @version: 2023/9/14 09:54.
 */
public class AccountAdapter extends TypeAdapter<Account> {
    @Override
    public void write(JsonWriter out, Account value) throws IOException {
        out.beginObject();
        out.name("NAME");
        out.value(value.getName());
        out.name("AGE");
        out.value(value.getAge());
        out.name("GENDER");
        out.value(value.getGender());
        out.endObject();
    }

    @Override
    public Account read(JsonReader in) throws IOException {
        Account account = new Account();
        in.beginObject();
        while (in.hasNext()) {
            String filedName = null;
            JsonToken token = in.peek();
            if (token.equals(JsonToken.NAME)) {
                filedName = in.nextName();
            }

            if ("name".equals(filedName)) {
                token = in.peek();
                account.setName(in.nextString());
            }
            if ("age".equals(filedName)) {
                token = in.peek();
                account.setAge(in.nextInt());
            }
            if ("gender".equals(filedName)) {
                token = in.peek();
                account.setGender(in.nextString());
            }
        }
        in.endObject();
        return account;
    }
}
