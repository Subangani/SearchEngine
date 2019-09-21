package com.json.search.document;

import java.util.List;

public class Organization {

    private int _id;
    private String url;
    private String external_id;
    private String name;
    private List<String> domain_names;
    private String created_at;
    private String details;
    private boolean shared_tickets;
    private List<String> tags;

    public int get_id() {
        return _id;
    }

    public String getUrl() {
        return url;
    }

    public String getExternal_id() {
        return external_id;
    }

    public String getName() {
        return name;
    }

    public List<String> getDomain_names() {
        return domain_names;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getDetails() {
        return details;
    }

    public boolean getShared_tickets() {
        return shared_tickets;
    }

    public List<String> getTags() {
        return tags;
    }
}
