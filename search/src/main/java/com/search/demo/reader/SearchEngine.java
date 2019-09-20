package com.search.demo.reader;

import com.search.demo.DemoApplication;
import com.search.demo.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class SearchEngine {

	final static String ONE = "1";
	final static String TWO = "2";
	final static String THREE = "3";
	final static String QUIT = "quit";

	@Autowired
	private static CommonUtil commonUtil;

	private SearchService userSearchService = new UserSearchService();

	private SearchService ticketSearchService = new TicketSearchService();

	private SearchService organizationSearchService = new OrganizationSearchService();

	public void search() {

		commonUtil.printFileContent("data/initial.txt");
		Scanner scanner = new Scanner(System. in);

		boolean searchOptionSelection = true;
		while (searchOptionSelection){
			String input1 = scanner. nextLine();
			switch (input1){
			case ONE:
				System.out.println("Please enter correct choice to proceed[ 1) Users or 2) Tickets or 3) Organizations]");
				searchOptionSelection = false;
				break;
			case TWO:
				commonUtil.printFileContent("data/searchableFields.txt");
				searchOptionSelection = false;
				break;
			case QUIT:
				System.exit(1);
			default:
				System.out.println("Please enter correct choice to proceed");
				commonUtil.printFileContent("data/initial.txt");
			}
		}
		System.out.println("Select 1) Users or 2) Tickets or 3) Organizations");
		boolean categorySelection = true;
		while (categorySelection){
			String input2 = scanner. nextLine();
			if ( input2.equalsIgnoreCase(ONE) || input2.equalsIgnoreCase(TWO)  || input2.equalsIgnoreCase(THREE) ){
				categorySelection = false;
				searchForData(input2);
			} else if (input2.equalsIgnoreCase(QUIT)){
				System.exit(1);
			} else {
				System.out.println("Please enter correct choices to proceed");
			}
		}
	}

	private void searchForData(String category){
		switch (category){
		case ONE:
			userSearchService.searchForUserData(category);
			break;
		case TWO:
			ticketSearchService.searchForUserData(category);
			break;
		case THREE:
			organizationSearchService.searchForUserData(category);
			break;
		default:
			System.out.println("In correct choice");
			break;
		}
	}
}
