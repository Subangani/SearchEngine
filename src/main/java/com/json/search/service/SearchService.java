package com.json.search.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.json.search.document.Organization;
import com.json.search.document.Ticket;
import com.json.search.document.User;
import org.beryx.textio.TextIO;

import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class SearchService {

    protected static String ORGANIZATION_ID = "organization_id";

    Gson gson = new GsonBuilder().create();
    TextIO textIO;

    SearchService(TextIO textIO) {
        this.textIO = textIO;
    }

    public abstract void search(String key, String value);

    public List<Organization> searchForOrganizationData(String key, String value) {
        List<Organization> organizations = new ArrayList<>();
        try {
            JsonReader jsonIterator = getJsonStreamFromFile("organizations.json");
            jsonIterator.beginArray();
            while (jsonIterator.hasNext()) {
                Organization organization = gson.fromJson(jsonIterator, Organization.class);
                Class<?> c = organization.getClass();
                Field field = c.getDeclaredField(key);
                field.setAccessible(true);
                if (field.get(organization).toString().equalsIgnoreCase(value)) {
                    organizations.add(organization);
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return organizations;
    }

    public List<Ticket> searchForTickerData(String key, String value) {
        List<Ticket> tickets = new ArrayList<>();
        try {
            JsonReader jsonIterator = getJsonStreamFromFile("tickets.json");
            jsonIterator.beginArray();
            while (jsonIterator.hasNext()) {
                Ticket ticket = gson.fromJson(jsonIterator, Ticket.class);
                Class<?> c = ticket.getClass();
                Field field = c.getDeclaredField(key);
                field.setAccessible(true);
                if (field.get(ticket).toString().equalsIgnoreCase(value)) {
                    tickets.add(ticket);
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tickets;
    }

    public List<User> searchForUserData(String key, String value) {
        List<User> users = new ArrayList<>();
        try {
            JsonReader jsonIterator = getJsonStreamFromFile("users.json");
            jsonIterator.beginArray();
            while (jsonIterator.hasNext()) {
                User user = gson.fromJson(jsonIterator, User.class);
                Class<?> c = user.getClass();
                Field field = c.getDeclaredField(key);
                field.setAccessible(true);
                if (field.get(user).toString().equalsIgnoreCase(value)) {
                    users.add(user);
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }


    public JsonReader getJsonStreamFromFile(String filName) {
        InputStream jsonStream = getClass()
                .getClassLoader().getResourceAsStream(filName);

        JsonReader reader = null;
        try {
            reader = new JsonReader(new InputStreamReader(jsonStream, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("UnsupportedEncodingException");
        }
        return reader;
    }

    protected void printAllDocumentFields(Object t) {
        Class<?> c = t.getClass();
        Field[] fields = c.getDeclaredFields();
        Arrays.stream(fields).forEach(e -> {
            try {
                e.setAccessible(true);
                this.textIO.getTextTerminal().printf("%-30.30s  %-30.30s%n", e.getName(), e.get(t).toString());
            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
            }
        });
        textIO.getTextTerminal().println();
    }

}
