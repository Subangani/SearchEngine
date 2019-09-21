package com.json.search.service;

import com.json.search.document.Organization;
import com.json.search.document.Ticket;
import com.json.search.document.User;
import org.beryx.textio.TextIO;

import java.util.List;

public class OrganizationSearchService extends SearchService {

    public OrganizationSearchService(TextIO textIO) {
        super(textIO);
    }

    @Override
    public void search(String key, String value) {

        List<Organization> filteredOrganizationList = searchForOrganizationData(key, value);
        if (!filteredOrganizationList.isEmpty()) {
            filteredOrganizationList.stream().forEach(e -> {
                processEachOrganizationRecord(e);
                textIO.getTextTerminal().println("*****************************");
            });

        } else {
            textIO.getTextTerminal().println("No search result found");
        }
    }

    private void processEachOrganizationRecord(Organization org) {
        String org_id = String.valueOf(org.get_id());
        printAllDocumentFields(org);
        List<Ticket> filtedTickets = searchForTickerData(ORGANIZATION_ID, org_id);
        filtedTickets.stream().forEach(e -> {
            textIO.getTextTerminal().printf("%-30.30s  %-30.30s%n", "subject", e.getSubject());
        });
        textIO.getTextTerminal().println();
        List<User> filteredUsers = searchForUserData(ORGANIZATION_ID, org_id);
        filteredUsers.stream().forEach(e -> {
            textIO.getTextTerminal().printf("%-30.30s  %-30.30s%n", "user_name", e.getName());
        });
    }

}
