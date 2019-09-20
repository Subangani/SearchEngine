package com.search.demo.reader;

import com.google.gson.JsonObject;
import com.search.demo.util.CommonUtil;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserSearchService extends SearchService {


	@Override
	public void processSearch(JSONObject jsonObject){

		jsonObject.keySet().parallelStream().forEach(e -> commonUtil.printSearchResult(e.toString(),jsonObject.get(e.toString()).toString()));

		String organization_id = jsonObject.get(ORGANIZATION_ID).toString();
		List<String> organization_Name = findOrganizationNameFromOrganizationId(organization_id);
		organization_Name.parallelStream().forEach(e -> commonUtil.printSearchResult(ORGANIZATION_NAME,e));
		List<JSONObject> filteredTicketList = getJsonObjectOfCategoryFromKeyValue(TWO,ORGANIZATION_ID,organization_id);

		List<String> organizationalIdOfAssignees = new ArrayList<>();
		for (int i = 0; i<filteredTicketList.size(); i++){
			List<JSONObject> assigneeUserObject = getJsonObjectOfCategoryFromKeyValue(ONE,"_id",filteredTicketList.get(i).get("assignee_id").toString());
			if (assigneeUserObject!=null || !assigneeUserObject.isEmpty()){
				for (JSONObject assignee:assigneeUserObject) {
					organizationalIdOfAssignees.add(assignee.get(ORGANIZATION_ID).toString());
				}
			}
		}

		for (String orgId : organizationalIdOfAssignees) {
			List<JSONObject> ticketSubjectsOfAssignee = new ArrayList<>();
			ticketSubjectsOfAssignee.addAll(getJsonObjectOfCategoryFromKeyValue(TWO,ORGANIZATION_ID,orgId));
			for (JSONObject ticket:ticketSubjectsOfAssignee) {
				commonUtil.printSearchResult("assignee_ticket_subject",ticket.get("subject").toString());
			}
		}

		List<String> organizationalIdOfSubmitters = new ArrayList<>();
		for (int i = 0; i<filteredTicketList.size(); i++){
			List<JSONObject> submitterUserObject = getJsonObjectOfCategoryFromKeyValue(ONE,"_id",filteredTicketList.get(i).get("submitter_id").toString());
			if (submitterUserObject!= null || !submitterUserObject.isEmpty()){
				for (JSONObject assignee:submitterUserObject) {
					organizationalIdOfSubmitters.add(assignee.get(ORGANIZATION_ID).toString());
				}
			}
		}

		for (String orgId : organizationalIdOfSubmitters) {
			List<JSONObject> ticketSubjectsOfSubmitter = new ArrayList<>();
			ticketSubjectsOfSubmitter.addAll(getJsonObjectOfCategoryFromKeyValue(TWO,ORGANIZATION_ID,orgId));
			for (JSONObject ticket:ticketSubjectsOfSubmitter) {
				commonUtil.printSearchResult("submitter_ticket_subject",ticket.get("subject").toString());
			}
		}




	}

}
