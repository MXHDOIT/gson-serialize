package com.frank.gsonserialize;

import com.frank.gsonserialize.adapter.AccountAdapter;
import com.frank.gsonserialize.model.Account;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

@SpringBootTest
class GsonSerializeApplicationTests {

    static Account account;

    static String jsonString;

    @BeforeAll
    static void before() {
        account = new Account("frank", 18, "man", "China");
        jsonString = "{\"name\":\"frank\",\"age\":18,\"gender\":\"man\"}";
    }

    @Test
    void contextLoads() {

    }

    @Test
    void testNewWay() {
        Gson gson = new Gson();
        //序列化
        String json = gson.toJson(account);
        System.out.println(json);
        //反序列化
        Account accountJson = gson.fromJson(jsonString, Account.class);
        System.out.println(accountJson.toString());
    }

    @Test
    void testBuildWay() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        //序列化
        String json = gson.toJson(account);
        System.out.println(json);
        //反序列化
        Account accountJson = gson.fromJson(jsonString, Account.class);
        System.out.println(accountJson.toString());
    }

    @Test
    void testWriteJson() throws IOException {
        Gson gson = new Gson();
        FileWriter fileWriter = new FileWriter("student.json");
        fileWriter.write(gson.toJson(account));
        fileWriter.close();
    }

    @Test
    void testReadJson() throws IOException {
        Gson gson = new Gson();
        BufferedReader bufferedReader = new BufferedReader(
                new FileReader("student.json"));

        String jsonStr = gson.fromJson(bufferedReader, Account.class).toString();
        System.out.println(jsonStr);
    }


    @Test
    void testMappingJson() throws IOException {
        Gson gson = new Gson();
        String name = "Test Mapping";
        long rollNo = 1;
        boolean verified = false;
        int[] marks = {100, 101};

        //序列化
        System.out.println("{");
        System.out.println("name: " + gson.toJson(name) + ",");
        System.out.println("rollNo: " + gson.toJson(rollNo) + ",");
        System.out.println("verified: " + gson.toJson(verified) + ",");
        System.out.println("marks:" + gson.toJson(marks));
        System.out.println("}");

        //反序列化
        name = gson.fromJson("\"Mahesh Kumar\"", String.class);
        rollNo = gson.fromJson("1", Long.class);
        verified = gson.fromJson("false", Boolean.class);
        marks = gson.fromJson("[100,90,85]", int[].class);

        System.out.println("name: " + name);
        System.out.println("rollNo: " + rollNo);
        System.out.println("verified: " + verified);
        System.out.println("marks:" + Arrays.toString(marks));
    }

    @Test
    void testJsonElement() {
        JsonParser jsonParser = new JsonParser();
        JsonElement jsonElement = jsonParser.parseString(jsonString);
        if (jsonElement.isJsonObject()) {
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            JsonElement name = jsonObject.get("name");
            System.out.println("Name: " + name.getAsString());
        }
    }

    @Test
    void testDefinedAdapter() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Account.class, new AccountAdapter());
        Gson gson = gsonBuilder.create();

        String s = gson.toJson(account);
        System.out.println(s);
        Account account1 = gson.fromJson(jsonString, Account.class);
        System.out.println(account1.toString());
    }

    @Test
    void testPrintNull() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        account.setGender(null);
        String s = gson.toJson(account);
        System.out.println(s);

        //显示NULL值
        Gson gson1 = gsonBuilder.serializeNulls().create();
        String s1 = gson1.toJson(account);
        System.out.println(s1);
    }


    @Test
    void testSinceAnn() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setVersion(1.0);
        Gson gson = gsonBuilder.create();
        String s = gson.toJson(account);
        System.out.println(s);

        //显示NULL值
        Gson gson1 = gsonBuilder.setVersion(1.1).create();
        String s1 = gson1.toJson(account);
        System.out.println(s1);
    }

    @Test
    void testExcludeField() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.excludeFieldsWithoutExposeAnnotation();
        Gson gson = gsonBuilder.create();
        String s = gson.toJson(account);
        System.out.println(s);
    }

    @Test
    void  testSerializedNameAnn() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        String toJson = gson.toJson(account);
        System.out.println(toJson);

        Account account1 = gson.fromJson(jsonString, Account.class);
        System.out.println(account1.toString());
        jsonString = "{\"name\":\"frank\",\"age\":18,\"GENDER\":\"woman\"}";
        account1 = gson.fromJson(jsonString, Account.class);
        System.out.println(account1.toString());
    }
}
