package com.search.demo.reader;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TicketSearchService extends SearchService {

	@Override
	public void processSearch(JSONObject ticketObject){
		try {
			ticketObject.keySet().stream().forEach(e -> commonUtil.printSearchResult(e.toString(),ticketObject.get(e.toString()).toString()));

			String organization_id = ticketObject.get(ORGANIZATION_ID).toString();
			List<String> organization_Name = findOrganizationNameFromOrganizationId(organization_id);
			organization_Name.stream().forEach(e -> commonUtil.printSearchResult(ORGANIZATION_NAME,e));

			List<JSONObject> assigneeUserObject = getJsonObjectOfCategoryFromKeyValue(ONE,"_id",ticketObject.get("assignee_id").toString());
			if (!assigneeUserObject.isEmpty()){
				for (JSONObject assignee:assigneeUserObject) {
					commonUtil.printSearchResult("assignee_name",assignee.get("name").toString());
				}
			}

			List<JSONObject> submitterUserObject = getJsonObjectOfCategoryFromKeyValue(ONE,"_id",ticketObject.get("submitter_id").toString());
			if (submitterUserObject.isEmpty()){
				for (JSONObject submitter: submitterUserObject ) {
					commonUtil.printSearchResult("submitter_name",submitter.get("name").toString());
				}
			}

		} catch (Exception e){

		}

	}
}
