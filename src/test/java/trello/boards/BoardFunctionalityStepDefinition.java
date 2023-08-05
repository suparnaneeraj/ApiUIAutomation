package trello.boards;

import org.junit.Assert;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import trello.pages.BoardDetailsPage;
import trello.pages.BoardsPage;
import trello.pages.LoginPage;
import utilities.CucumberHelper;

public class BoardFunctionalityStepDefinition {

	CucumberHelper helper = new CucumberHelper();
	LoginPage loginPage;
	BoardsPage boardsPage;
	BoardDetailsPage boardDetailsPage;
	TrelloAPI trelloAPI;
	String boardId, listId, cardId, cardId2, listId2, key, token;

	@Given("^user is successfully logged in the application$")
	public void user_is_successfully_logged_in_the_application() throws Exception {
		helper.createDriver();
		key = helper.dataProperties.get("apiKey");
		token = helper.dataProperties.get("token");
		loginPage = new LoginPage(helper.driver);
		boardsPage = new BoardsPage(helper.driver);
		loginPage.acceptCookies();
		loginPage.login(helper.dataProperties.get("email"), helper.dataProperties.get("password"));
		Assert.assertTrue("Your are on the incorrect page", boardsPage.getPageTitle().equals("Boards"));

	}

	@When("^user clicks on Create Board by providing the board title \"([^\"]*)\"$")
	public void user_clicks_on_Create_Board_by_providing_the_board_title(String boardTitle) throws Exception {
		boardsPage.createABoard(boardTitle);
	}

	@Then("^board with title \"([^\"]*)\" should be successfully created$")
	public void board_should_be_successfully_created(String boardTitle) throws Exception {
		boardDetailsPage = new BoardDetailsPage(helper.driver);
		Assert.assertTrue("Board creation failed", boardDetailsPage.verifycreatedBoardTitle().equals(boardTitle));
	}

	@Given("^a board is successfully created with title \"([^\"]*)\" with permission \"([^\"]*)\" through API$")
	public void a_board_is_successfully_created_with_title_with_permission_through_api(String boardTitle,String permission) throws Exception{
		trelloAPI = new TrelloAPI();

		boardId = trelloAPI.createBoardAPI(boardTitle, permission, key, token);
		Assert.assertNotNull("Error creating a board", boardId);
	}

	@Given("^user is in the created board page \"([^\"]*)\"$")
	public void user_is_in_the_created_board_page(String boardName) throws Exception {
		helper.createDriver();
		loginPage = new LoginPage(helper.driver);
		boardsPage = new BoardsPage(helper.driver);
		loginPage.acceptCookies();
		loginPage.login(helper.dataProperties.get("email"), helper.dataProperties.get("password"));
		Thread.sleep(2000);
		boardsPage.clickOnCreatedBoard(boardName);
		helper.refreshBrowser();
	}

	@When("^add list button is clicked by providing the list title \"([^\"]*)\"$")
	public void add_list_button_is_clicked_by_providing_the_list_title(String listTitle) throws Exception {
		boardDetailsPage = new BoardDetailsPage(helper.driver);
		boardDetailsPage.createList(listTitle);
	}

	@Then("^the list with title \"([^\"]*)\" should be created successfully$")
	public void the_list_with_title_should_be_created_successfully(String listTitle) throws Exception {
		Assert.assertTrue(boardDetailsPage.verifyListAdded(listTitle));
	}

	@And("^a list with title \"([^\"]*)\" is added to the board through API$")
	public void a_list_with_title_is_added_to_the_board_through_API(String listName) throws Exception {
		listId = trelloAPI.createList(listName, boardId, key, token);
		Assert.assertNotNull("Error creating a List", listId);
	}

	@When("^add card button of the created list \"([^\"]*)\" is clicked by providing the card title \"([^\"]*)\"$")
	public void add_card_button_of_the_created_list_is_clicked_by_providing_the_card_title(String listTitle,
			String cardTitle) throws Exception {
		boardDetailsPage = new BoardDetailsPage(helper.driver);
		boardDetailsPage.addACard(listTitle, cardTitle);
	}

	@Then("^card with title \"([^\"]*)\" should be successfully added to that list$")
	public void card_with_title_should_be_successfully_added_to_that_list(String cardTitle) throws Exception {
		Assert.assertTrue("Error creating card",
				boardDetailsPage.verifyCreatedCardIsListed(cardTitle).equals(cardTitle));
	}

	@And("^a card \"([^\"]*)\" is created through API under the list \"([^\"]*)\"$")
	public void a_card_is_created_through_api_under_the_list(String cardName, String ListName) throws Exception {
		listId = trelloAPI.getListId(ListName, boardId, key, token);
		if (listId.equals("0")) {
			listId = trelloAPI.createList(ListName, boardId, key, token);
		}
		cardId = trelloAPI.createCard(cardName, listId, key, token);
		Assert.assertNotNull(listId);
		Assert.assertNotNull(cardId);
	}

	@When("^the description of the card \"([^\"]*)\" is updated as \"([^\"]*)\"$")
	public void the_description_of_the_card_is_updated_as(String cardName, String description) throws Exception {
		String updatedDescStatus = trelloAPI.updateCard(cardId, description, key, token);
		Assert.assertNotNull("Error updating a card", updatedDescStatus);
	}

	@Then("^the description \"([^\"]*)\" should be successfully saved in card \"([^\"]*)\"$")
	public void the_description_should_be_successfully_saved_in_card(String description, String cardName)
			throws Exception {
		boardDetailsPage = new BoardDetailsPage(helper.driver);
		String[] cardUpdatedDetails = trelloAPI.getACard(cardId, key, token);
		Assert.assertTrue(cardUpdatedDetails[0].equals(description));
		Assert.assertTrue(cardUpdatedDetails[1].equals(cardName));
		Assert.assertEquals("Description not updated", description, boardDetailsPage.getDescriptionOfCard(cardName));
	}

	@And("^a two lists \"([^\"]*)\" \"([^\"]*)\" are created in the board through API$")
	public void a_two_lists_are_created_in_the_board_through_api(String list1, String list2) throws Exception {
		listId = trelloAPI.getListId(list1, boardId, key, token);
		if (listId.equals("0")) {
			listId = trelloAPI.createList(list1, boardId, key, token);
		}
		listId2 = trelloAPI.getListId(list2, boardId, key, token);
		if (listId2.equals("0")) {
			listId2 = trelloAPI.createList(list2, boardId, key, token);
		}
		Assert.assertNotNull(listId);
		Assert.assertNotNull(listId2);
	}

	@And("^the cards \"([^\"]*)\" are created in one list \"([^\"]*)\"$")
	public void the_cards_are_created_in_one_list(String cardNames, String list1) throws Exception {
		String[] cards = cardNames.split(",");
		cardId = trelloAPI.createCard(cards[0], listId, key, token);
		cardId2 = trelloAPI.createCard(cards[1], listId, key, token);
		Assert.assertNotNull(cardId);
		Assert.assertNotNull(cardId2);
	}

	@When("^the cards are moved from one list \"([^\"]*)\" to another \"([^\"]*)\" using API$")
	public void the_cards_are_moved_from_one_list_to_another_using_API(String list1, String list2) throws Exception {
		String moveCardsStatus = trelloAPI.moveAllCardsToList(boardId, listId, listId2, key, token);
		Assert.assertNotNull(moveCardsStatus);
	}

	@Then("^the list \"([^\"]*)\" in the board should contain the cards \"([^\"]*)\"$")
	public void the_list_in_the_board_should_contain_the_cards(String listName, String cards) throws Exception {
		boardDetailsPage = new BoardDetailsPage(helper.driver);
		Assert.assertTrue("Error in moving the cards", boardDetailsPage.verifyCardsInList(listName, cards));
	}

	@After
	public void tearDown() {
		helper.closeBrowser();
		loginPage = null;
		boardDetailsPage = null;
		boardsPage = null;
		trelloAPI = null;
	}

}
