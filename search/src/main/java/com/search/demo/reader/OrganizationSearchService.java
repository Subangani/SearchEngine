package com.search.demo.reader;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrganizationSearchService extends SearchService {

	@Override
	public  void processSearch(JSONObject organizationObject) {
		organizationObject.keySet().parallelStream().forEach(e -> commonUtil.printSearchResult(e.toString(),organizationObject.get(e.toString()).toString()));

		String organization_id = organizationObject.get("_id").toString();
		List<String> userNames = findUserNameFromOrganizationIdInUser(organization_id);
		userNames.parallelStream().forEach(e -> commonUtil.printSearchResult(USER_NAME,e));

		List<String> ticketSubject = findFieldValueFromOrganizationIdInTicket(organization_id, "subject");
		for (int i = 0; i<ticketSubject.size(); i++){
			commonUtil.printSearchResult("ticket_"+i,ticketSubject.get(i));
		}
	}
}
