package trello.pages;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BoardsPage {
	@FindBy(css = "a.oTmCsFlPhDLGz2.AQ0dAIzWIJDFUU")
	WebElement boardsPageTitle;
	@FindBy(xpath = "//p[@class='X7iA6JJNiXCA2r' and text()='Create']")
	WebElement createButton;
	@FindBy(xpath = "//span[@class='BmRHtH7FIX0jcL DRX_7shCoCpAU5' and text()='Create board']")
	WebElement createBoard;
	@FindBy(css = "input.nch-textfield__input.lsOhPsHuxEMYEb.lsOhPsHuxEMYEb.VkPAAkbpkKnPst")
	WebElement boardTitle;
	@FindBy(xpath = "//button[@class='hY6kPzdkHFJhfG bxgKMAm3lq5BpA SdamsUKjxSBwGb SEj5vUdI3VvxDc']")
	WebElement createBoardButton;
	@FindBy(xpath = "//a[@class='board-tile']")
	List<WebElement> boards;
	@FindBy(xpath = "//div[@class=' css-1og2rpm']")
	WebElement boardPermission;
	@FindBy(xpath = "//div[@class='tONJvKrAZwZgEq']//div[text()='Public']")
	WebElement selectBoardPermission;
	@FindBy(xpath = "//button[@class='bxgKMAm3lq5BpA SdamsUKjxSBwGb PnEv2xIWy3eSui SEj5vUdI3VvxDc']")
	WebElement boardPermissionConfirmation;
	WebDriverWait wait;

	public BoardsPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	// Method to get the title of boards page
	public String getPageTitle() {
		return boardsPageTitle.getText();
	}

	// user provides the necessary details to create the board
	public void createABoard(String boardName) throws Exception {
		createButton.click();
		createBoard.click();
		boardTitle.sendKeys(boardName);
		boardPermission.click();
		selectBoardPermission.click();
		boardPermissionConfirmation.click();
		// wait.until(ExpectedConditions.visibilityOf(createBoardButton));
		Thread.sleep(2000);
		createBoardButton.click();
	}

	// Method to click a particular board with given name
	public void clickOnCreatedBoard(String boardName) {
		// wait.until(ExpectedConditions.visibilityOfAllElements(boards));
		for (WebElement board : boards) {
			if (board.getText().equals(boardName)) {
				board.click();
				break;
			}

		}

	}
}