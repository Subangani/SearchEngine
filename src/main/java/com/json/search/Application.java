package com.json.search;

import com.json.search.service.OrganizationSearchService;
import com.json.search.service.TicketSearchService;
import com.json.search.service.UserSearchService;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Application {

    private static TextIO textIO = TextIoFactory.getTextIO();

    static TicketSearchService ticketSearchService = new TicketSearchService(textIO);
    static OrganizationSearchService organizationSearchService = new OrganizationSearchService(textIO);
    static UserSearchService userSearchService = new UserSearchService(textIO);

    public static void main(String[] args) {

        printFileContent("initial.txt");

        boolean searchOptionSelection = true;
        while (searchOptionSelection) {

            String input1 = textIO.newStringInputReader().read();
            if (input1.equalsIgnoreCase("quit")) {
                textIO.dispose();
                System.exit(1);
            }
            switch (input1) {
                case "1":
                    textIO.getTextTerminal().println("Please enter correct choice to proceed[ 1) Users or 2) Tickets or 3) Organizations]");
                    searchOptionSelection = false;
                    break;
                case "2":
                    printFileContent("data/searchableFields.txt");
                    searchOptionSelection = false;
                    break;
                case "quit":
                    textIO.dispose();
                default:
                    textIO.getTextTerminal().println("Please enter correct choice to proceed");
                    printFileContent("data/initial.txt");
            }
        }
        while (true) {
            String input2 = textIO.newStringInputReader().read();
            if (input2.equalsIgnoreCase("quit")) {
                textIO.dispose();
                System.exit(1);
            }
            if (input2.equalsIgnoreCase("1") || input2.equalsIgnoreCase("2") || input2.equalsIgnoreCase("3")) {
                searchForData(input2);
            } else if (input2.equalsIgnoreCase("quit")) {
                System.exit(1);
            } else {
                textIO.getTextTerminal().println("Please enter correct choices to proceed");
            }
        }

    }

    private static void searchForData(String category) {
        String searchKey = textIO.newStringInputReader().read();
        if (searchKey.equalsIgnoreCase("quit")) {
            textIO.dispose();
            System.exit(1);
        }
        String searchValue = textIO.newStringInputReader().read();
        if (searchValue.equalsIgnoreCase("quit")) {
            textIO.dispose();
            System.exit(1);
        }
        switch (category) {
            case "1":
                userSearchService.search(searchKey, searchValue);
                break;
            case "2":
                ticketSearchService.search(searchKey, searchValue);
                break;
            case "3":
                organizationSearchService.search(searchKey, searchValue);
                break;
        }
    }

    public static void printFileContent(String fileName) {
        ClassLoader classLoader = Application.class.getClassLoader();
        try (BufferedReader br = new BufferedReader(new FileReader(classLoader.getResource(fileName).getFile()))) {
            String line = null;
            while ((line = br.readLine()) != null) {
                textIO.getTextTerminal().println(line);
            }
        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        }
    }

    public static TextIO getTextIO() {
        return textIO;
    }
}
