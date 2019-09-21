package com.json.search.service;

import com.json.search.document.Organization;
import com.json.search.document.Ticket;
import com.json.search.document.User;
import org.beryx.textio.TextIO;

import java.util.List;

public class TicketSearchService extends SearchService {

    public TicketSearchService(TextIO textIO) {
        super(textIO);
    }

    @Override
    public void search(String key, String value) {
        List<Ticket> filteredTicketList = searchForTickerData(key,value);
        if (!filteredTicketList.isEmpty()){
            filteredTicketList.stream().forEach(e -> {
                processEachTicketRecord(e);
                textIO.getTextTerminal().println("*****************************");
            });

        } else {
            textIO.getTextTerminal().println("No search result found");
        }
    }

    private void processEachTicketRecord(Ticket ticket){
        printAllDocumentFields(ticket);
        String org_id = String.valueOf(ticket.getOrganization_id());
        List<Organization>  organizationList = searchForOrganizationData("_id",org_id);
        organizationList.stream().forEach(e ->{
            textIO.getTextTerminal().printf("%-30.30s  %-30.30s%n", "organization_name",e.getName());
        });
        textIO.getTextTerminal().println();
        List<User> assigneeUsersList = searchForUserData("_id", String.valueOf(ticket.getAssignee_id()));
        assigneeUsersList.stream().forEach(e -> {
            textIO.getTextTerminal().printf("%-30.30s  %-30.30s%n", "assignee_name",e.getName());
        });

        List<User> submitterUsersList = searchForUserData("_id", String.valueOf(ticket.getSubmitter_id()));
        submitterUsersList.stream().forEach(e -> {
            textIO.getTextTerminal().printf("%-30.30s  %-30.30s%n", "assignee_name",e.getName());
        });

    }

}
