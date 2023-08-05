Feature: Verify basic functionalities in a board


Scenario Outline: Verify if a board can be created successfully through UI
Given user is successfully logged in the application
When user clicks on Create Board by providing the board title "<boardTitle>"
Then board with title "<boardTitle>" should be successfully created

Examples:
	|	boardTitle	|
	|	Project A	|
 
@Test
 Scenario Outline: Verify if a list can be added to a board
 Given a board is successfully created with title "<boardTitle>" with permission "<boardPermission>" through API
 And user is in the created board page "<boardTitle>"
 When add list button is clicked by providing the list title "<listTitle>"
 Then the list with title "<listTitle>" should be created successfully
 
 Examples:
	|	boardTitle	|	listTitle	|	boardPermission	|
	|	Project B	|	Open	|	public	|
	

	Scenario Outline: Verify if a card can be added to a list
	Given a board is successfully created with title "<boardTitle>" with permission "<boardPermission>" through API
	And a list with title "<listTitle>" is added to the board through API
	And user is in the created board page "<boardTitle>"
	When add card button of the created list "<listTitle>" is clicked by providing the card title "<cardTitle>"
	Then card with title "<cardTitle>" should be successfully added to that list
	
	Examples:
	|	boardTitle	|	listTitle	|	cardTitle	|	boardPermission	|
	|	Project C	|	In Progress	|	Create Test plan	|	public	|
	

	Scenario Outline: Verify if the description of a task can be edited
	Given a board is successfully created with title "<boardTitle>" with permission "<boardPermission>" through API
	And a card "<cardName>" is created through API under the list "<listName>"
	And user is in the created board page "<boardTitle>"
	When the description of the card "<cardName>" is updated as "<description>"
	Then the description "<description>" should be successfully saved in card "<cardName>"
	
	Examples:
	|	boardTitle	|	cardName	|	listName	|	description	|	boardPermission	|
	|	Project D	|	Create testcase	|	To Do	|	Create testcase for card creation	|	public	|
	|	Project P	|	Create tasks	|	Ready for QA	|	Create tasks for this feature	|	public	|	
	
	
	Scenario Outline: Verify if tasks can be moved from one list to another
	Given a board is successfully created with title "<boardTitle>" with permission "<boardPermission>" through API
	And a two lists "<list1>" "<list2>" are created in the board through API
	And the cards "<cards>" are created in one list "<list1>"
	And user is in the created board page "<boardTitle>"
	When the cards are moved from one list "<list1>" to another "<list2>" using API
	Then the list "<list2>" in the board should contain the cards "<cards>"
	
	Examples:
	|	boardTitle	|	list1	|	list2	|	cards	|	boardPermission	|
	|	Project Q	|	To Do	|	Done	|	Create test plan,Create testcase	|	public	|	
	|	Project R	|	Open	|	Closed	|	Create test plan,Create testcase	|	public	|
	
	
	
	
	