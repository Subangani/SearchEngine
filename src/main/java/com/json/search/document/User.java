package com.json.search.document;

import java.util.List;

public class User {
    private int _id;
    private String external_id;
    private String name;
    private String alias;
    private String created_at;
    private boolean active;
    private boolean verified;
    private boolean shared;
    private String  locale;
    private String  timezone;
    private String  last_login_at;
    private String  email;
    private String  phone;
    private String  signature;
    private int  organization_id;
    private List<String>  tags;
    private boolean suspended;
    private String role;

    public int get_id() {
        return _id;
    }

    public String getExternal_id() {
        return external_id;
    }

    public String getName() {
        return name;
    }

    public String getAlias() {
        return alias;
    }

    public String getCreated_at() {
        return created_at;
    }

    public boolean getActive() {
        return active;
    }

    public boolean getVerified() {
        return verified;
    }

    public boolean getShared() {
        return shared;
    }

    public String getLocale() {
        return locale;
    }

    public String getTimezone() {
        return timezone;
    }

    public String getLast_login_at() {
        return last_login_at;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getSignature() {
        return signature;
    }

    public int getOrganization_id() {
        return organization_id;
    }

    public List<String> getTags() {
        return tags;
    }

    public boolean getSuspended() {
        return suspended;
    }

    public String getRole() {
        return role;
    }
}
