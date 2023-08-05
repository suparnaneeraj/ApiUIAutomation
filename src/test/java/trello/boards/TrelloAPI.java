package trello.boards;

import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONObject;

import io.restassured.response.Response;
import utilities.APIHelperClass;

public class TrelloAPI extends APIHelperClass {
	// API to create a board in the given name
	public String createBoardAPI(String boardTitle, String boardPermission, String apiKey, String token) {
		Response response;
		String requestPath = "/boards", id;
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("name", boardTitle);
		requestParams.put("prefs_permissionLevel", boardPermission);
		requestParams.put("key", apiKey);
		requestParams.put("token", token);
		response = postRequest(requestPath, requestParams);
		System.out.println(response.getBody().asString());
		id = response.jsonPath().get("id");
		System.out.println(id);
		return id;
	}

	// API to create a list with given name in given board
	public String createList(String listName, String id, String apiKey, String token) {
		Response response;
		String requestPath = "/boards/" + id + "/lists", listId;
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("name", listName);
		requestParams.put("key", apiKey);
		requestParams.put("token", token);
		response = postRequest(requestPath, requestParams);
		listId = response.jsonPath().get("id");
		return listId;
	}

	// API to lists all the list in the given board
	public Response getListsOnBoard(String boardId, String apiKey, String token) {
		String requestPath = "/boards/" + boardId + "/lists";
		Response response;
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("key", apiKey);
		requestParams.put("token", token);
		response = getRequest(requestPath, requestParams);
		return response;
	}

	// Method to get the listId in a given board
	public String getListId(String ListName, String boardId, String apiKey, String token) {
		int indexOfList = 0;
		boolean flag = false;
		Response response = getListsOnBoard(boardId, apiKey, token);
		List<String> listNames = response.jsonPath().getList("name");
		List<String> listIds = response.jsonPath().getList("id");
		for (int i = 0; i < listNames.size(); i++) {
			if (listNames.get(i).equals(ListName)) {
				indexOfList = i;
				flag = true;
				break;
			}
		}
		if (flag) {
			return listIds.get(indexOfList);
		} else {
			return "0";
		}

	}

	// Method to create a card in a given list
	public String createCard(String cardName, String listId, String apiKey, String token) {
		Response response;
		String requestPath = "/cards", id;
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("name", cardName);
		requestParams.put("key", apiKey);
		requestParams.put("token", token);
		requestParams.put("idList", listId);
		response = postRequest(requestPath, requestParams);
		id = response.jsonPath().get("id");
		return id;

	}

	// Method to update the description field of a card
	public String updateCard(String cardId, String description, String apiKey, String token) {
		String requestPath = "/cards/" + cardId, id;
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("desc", description);
		requestParams.put("key", apiKey);
		requestParams.put("token", token);
		Response response = putRequest(requestPath, requestParams);
		id = response.jsonPath().get("id");
		return id;
	}

	public String[] getACard(String cardId, String apiKey, String token) {
		String requestPath = "/cards/" + cardId;
		String[] resultString = new String[2];
		Response response;
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("key", apiKey);
		requestParams.put("token", token);
		response = getRequest(requestPath, requestParams);
		resultString[0] = response.jsonPath().get("desc");
		resultString[1] = response.jsonPath().get("name");
		return resultString;

	}

	public String moveAllCardsToList(String boardId, String listId1, String listId2, String apiKey, String token) {
		Response response;
		String requestPath = "/lists/" + listId1 + "/moveAllCards";
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("idBoard", boardId);
		requestParams.put("key", apiKey);
		requestParams.put("token", token);
		requestParams.put("idList", listId2);
		response = postRequest(requestPath, requestParams);
		return response.asString();

	}

}