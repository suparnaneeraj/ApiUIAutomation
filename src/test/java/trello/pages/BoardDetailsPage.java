package trello.pages;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BoardDetailsPage {

	@FindBy(css = "h1.HKTtBLwDyErB_o")
	WebElement createdBoardTitle;
	@FindBy(xpath = "//span[text()='Add another list']")
	WebElement addListOption;
	@FindBy(css = "input.list-name-input")
	WebElement listName;
	@FindBy(xpath = "//input[@value='Add list']")
	WebElement addListButton;
	@FindBy(xpath = "//textarea[@class='list-header-name mod-list-name js-list-name-input']")
	List<WebElement> addedLists;
	By addcardButton = By.xpath("//span[text()='Add a card']");
	@FindBy(xpath = "//textarea[@placeholder='Enter a title for this card…']")
	WebElement cardTitle;
	@FindBy(xpath = "//input[@value='Add card']")
	WebElement addCard;
	@FindBy(xpath = "//span[@class='list-card-title js-card-name']")
	List<WebElement> cards;
	@FindBy(xpath = "//div[@class='current markeddown hide-on-edit js-desc js-show-with-desc']")
	WebElement cardDescription;
	By cardsInList = By.xpath("//parent::div//parent::div//a[@class='list-card js-member-droppable ui-droppable']");
	WebDriverWait wait;

	public BoardDetailsPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	}

	// Returns the name of the board created thus verifying the board creation
	public String verifycreatedBoardTitle() {
		return createdBoardTitle.getText();
	}

	public void createList(String listTitle) {
		addListOption.click();
		wait.until(ExpectedConditions.visibilityOf(listName));
		listName.sendKeys(listTitle);
		wait.until(ExpectedConditions.elementToBeClickable(addListButton));
		addListButton.click();
	}

	// Verify if the list is added , provides true if list exists otherwise returns
	// false
	public boolean verifyListAdded(String listTitle) throws Exception {
		boolean listStatus = false;
		Thread.sleep(1000);
		for (WebElement element : addedLists) {
			if (element.getText().equals(listTitle))
				listStatus = true;

		}
		return listStatus;
	}

	// Add a card in the given list in a board
	public void addACard(String listName, String cardName) {
		WebElement list;
		list = getAnElement(addedLists, listName);
		list.findElement(addcardButton).click();
		cardTitle.sendKeys(cardName);
		addCard.click();

	}

	// Method that accepts a list of WebElements and checks if a particular element
	// with text exists in the list
	public WebElement getAnElement(List<WebElement> elements, String text) {
		WebElement resultElement = null;
		for (WebElement element : elements) {
			if (element.getText().equals(text)) {
				resultElement = element;
				break;
			}

		}
		return resultElement;
	}

	// Method to check if the card with given title is created
	public String verifyCreatedCardIsListed(String cardTitle) {
		WebElement createdCard = getAnElement(cards, cardTitle);
		return createdCard.getText();
	}

	// Method to return the description of a card created
	public String getDescriptionOfCard(String cardTitle) {
		WebElement createdCard = getAnElement(cards, cardTitle);
		createdCard.click();
		return cardDescription.getText();
	}

	// Verifies if cards exists in a list
	public boolean verifyCardsInList(String listName, String cardNames) {

		String[] cards = cardNames.split(",");
		String cardsFromList = "";
		boolean cardMovedStatus = false;
		wait.until(ExpectedConditions.visibilityOfAllElements(addedLists));
		WebElement list = getAnElement(addedLists, listName);

		List<WebElement> cardInGivenList = list.findElements(cardsInList);
		for (WebElement element : cardInGivenList) {
			cardsFromList = cardsFromList + "," + element.getText();
		}
		if (cardsFromList.contains(cards[0]) & cardsFromList.contains(cards[1])) {
			cardMovedStatus = true;
		}
		return cardMovedStatus;
	}

}
