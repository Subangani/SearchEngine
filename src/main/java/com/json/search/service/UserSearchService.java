package com.json.search.service;

import com.json.search.document.Ticket;
import com.json.search.document.User;
import org.beryx.textio.TextIO;

import java.util.*;

public class UserSearchService extends SearchService {

    public UserSearchService(TextIO textIO) {
        super(textIO);
    }

    @Override
    public void search(String key, String value) {
        List<User> filteredUserList = searchForUserData(key,value);
        if (!filteredUserList.isEmpty()){
            filteredUserList.stream().forEach(e -> {
                processEachUserRecord(e);
                textIO.getTextTerminal().println("*****************************");
            });

        } else {
            textIO.getTextTerminal().println("No search result found");
        }
    }

    private void processEachUserRecord(User user){
        printAllDocumentFields(user);
        String org_id = String.valueOf(user.getOrganization_id());

        List<Ticket> filteredTickets = searchForTickerData(ORGANIZATION_ID,org_id);
        Map<Integer,User> filteredAssigneeUsers = new HashMap<>();
        Map<Integer,User> filteredSubmitterUsers = new HashMap<>();
        for (Ticket ticket:filteredTickets) {
            searchForUserData("_id", String.valueOf(ticket.getAssignee_id())).stream().forEach(e -> {
                filteredAssigneeUsers.put(e.get_id(),e);
            });
            searchForUserData("_id", String.valueOf(ticket.getSubmitter_id())).stream().forEach(e -> {
                filteredSubmitterUsers.put(e.get_id(),e);
            });
        }

        Set<String> ticketSubjects = new HashSet<>();
        filteredAssigneeUsers.keySet().forEach( e ->{
            List<Ticket> assigneeTickets = searchForTickerData(ORGANIZATION_ID, String.valueOf(filteredAssigneeUsers.get(e).getOrganization_id()));
            assigneeTickets.stream().forEach( t -> {
                ticketSubjects.add(t.getSubject());
            });
        });

        filteredSubmitterUsers.keySet().forEach( e ->{
            List<Ticket> submitterTickets = searchForTickerData(ORGANIZATION_ID, String.valueOf(filteredSubmitterUsers.get(e).getOrganization_id()));
            submitterTickets.stream().forEach( t -> {
                ticketSubjects.add(t.getSubject());
            });
        });
        ticketSubjects.stream().forEach(e ->{
            textIO.getTextTerminal().printf("%-30.30s  %-30.30s%n", "ticket_subject",e);
        });
    }

}
