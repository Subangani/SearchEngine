package com.search.demo.reader;

import com.search.demo.util.CommonUtil;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@Component
public class SearchService {
	final static String ONE = "1";
	final static String TWO = "2";
	final static String THREE = "3";
	final static String QUIT = "quit";

	public static CommonUtil commonUtil;
	public String ORGANIZATION_ID = "organization_id";
	public String ORGANIZATION_NAME = "organization_name";
	public String USER_NAME = "user_name";

	protected static FileReader getFile(String fileName) {
		ClassLoader classLoader = SearchEngine.class.getClassLoader();

		try {
			return new FileReader(classLoader.getResource(fileName).getFile());
		} catch (FileNotFoundException e) {
			System.out.println(fileName + "File not found");
		}
		return null;
	}

	public List<JSONObject> searchForUserData(String category) {
		try {
			String input3 = getInput();
			String input4 = getInput();
			List<JSONObject> filteredList = getJsonObjectOfCategoryFromKeyValue(category, input3, input4);
			for (JSONObject jsonObject : filteredList) {
				processSearch(jsonObject);
				System.out.println("\n----------------------------\n");
			}
			return filteredList;
		} catch (Exception e) {

		}
		return null;
	}

	public String getInput() {
		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine();
		if (input.equalsIgnoreCase(QUIT)) {
			System.exit(1);
		}
		return input;
	}

	void processSearch(JSONObject object) {
	}

	protected List<JSONObject> getJsonObjectOfCategoryFromKeyValue(String category, String searchKey, String searchValue) {
		JSONParser jsonParser = new JSONParser();
		FileReader reader = null;

		List<JSONObject> jsonList = new ArrayList<>();
		try {
			switch (category) {

			case ONE:
				reader = getFile("data/users.json");
				break;
			case TWO:
				reader = getFile("data/tickets.json");
				break;
			case THREE:
				reader = getFile("data/organizations.json");
				break;
			default:
				return jsonList;
			}

			Object obj = jsonParser.parse(reader);
			List<JSONObject> objectList = (JSONArray) obj;

			jsonList = objectList.stream().filter(e -> checkForKeyValue((JSONObject) e, searchKey, searchValue))
					.collect(Collectors.toList());

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return jsonList;
	}

	protected boolean checkForKeyValue(JSONObject object, String searchKey, String searchValue) {
		if (object.keySet().contains(searchKey) && object.get(searchKey).toString().contains(searchValue)) {
			return true;
		}
		return false;
	}

	protected List<String> findUserNameFromOrganizationIdInUser(String organization_id) {
		List<String> users = new ArrayList<>();
		List<JSONObject> filteredList = getJsonObjectOfCategoryFromKeyValue(ONE, "organization_id", organization_id);
		for (JSONObject jsonObject : filteredList) {
			users.add(jsonObject.get("name").toString());
		}
		return users;
	}

	protected List<String> findFieldValueFromOrganizationIdInTicket(String organization_id, String field) {
		List<String> users = new ArrayList<>();
		List<JSONObject> filteredList = getJsonObjectOfCategoryFromKeyValue(TWO, "organization_id", organization_id);
		for (JSONObject jsonObject : filteredList) {
			users.add(jsonObject.get(field).toString());
		}
		return users;
	}

	protected List<String> findOrganizationNameFromOrganizationId(String organization_id) {
		List<String> organization_Names = new ArrayList<>();
		List<JSONObject> filteredList = getJsonObjectOfCategoryFromKeyValue(THREE, "_id", organization_id);
		for (JSONObject jsonObject : filteredList) {
			organization_Names.add(jsonObject.get("name").toString());
		}
		return organization_Names;
	}

}
