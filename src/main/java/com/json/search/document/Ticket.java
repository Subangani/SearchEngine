package com.json.search.document;

import java.util.List;

public class Ticket {

    private String _id;
    private String url;
    private String external_id;
    private String created_at;
    private String type;
    private String subject;
    private String description;
    private String priority;
    private String status;
    private int submitter_id;
    private int assignee_id;
    private int organization_id;
    private List<String> tags;
    private boolean has_incidents;
    private String due_at;
    private String via;

    public String get_id() {
        return _id;
    }

    public String getUrl() {
        return url;
    }

    public String getExternal_id() {
        return external_id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getType() {
        return type;
    }

    public String getSubject() {
        return subject;
    }

    public String getDescription() {
        return description;
    }

    public String getPriority() {
        return priority;
    }

    public String getStatus() {
        return status;
    }

    public int getSubmitter_id() {
        return submitter_id;
    }

    public int getAssignee_id() {
        return assignee_id;
    }

    public int getOrganization_id() {
        return organization_id;
    }

    public List<String> getTags() {
        return tags;
    }

    public boolean getHas_incidents() {
        return has_incidents;
    }

    public String getDue_at() {
        return due_at;
    }

    public String getVia() {
        return via;
    }
}
