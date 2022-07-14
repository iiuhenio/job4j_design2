package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;

public class JsonTest {
    public boolean using;
    public int amount;
    public String res;
    String[] array;
    Contact contact;

    public JsonTest(boolean using, int amount, Contact contact, String[] array, String res) {
        this.using = using;
        this.amount = amount;
        this.res = res;
        this.array = array;
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "JsonTest{"
                + "using=" + using
                + ", amount=" + amount
                + ", res='" + res + '\''
                + ", array=" + Arrays.toString(array)
                + ", contact=" + contact
                + '}';
    }

    public static void main(String[] args) {
        JsonTest jt = new JsonTest(true, 9,
                new Contact("25"), new String[]{"Student", "Free"}, "yes");

        /* Преобразуем объект person в json-строку. */
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(jt));

        /* Модифицируем json-строку */
        final String jtjson =
                "{"
                        + "\"using\":true,"
                        + "\"amount\":9,"
                        + "\"contact\":"
                        + "{"
                        + "\"phone\":\"25\""
                        + "},"
                        + "\"array\":"
                        + "[\"Student\",\"Free\"],"
                        + "\res\":yes"
                        + "}";

        final JsonTest jsMod = gson.fromJson(jtjson, JsonTest.class);
        System.out.println(jsMod);
    }
}
