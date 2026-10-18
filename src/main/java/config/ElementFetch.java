package main.java.config;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import test.java.BaseTest;

import java.util.List;

public class ElementFetch {
	
	public WebElement getWebElement(String identifierType, String identifierValue) {
		switch (identifierType) {
		case "ID":
			return BaseTest.getDriver().findElement(By.id(identifierValue));
		case "CSS":
			return BaseTest.getDriver().findElement(By.cssSelector(identifierValue));
		case "TAGNAME":
			return BaseTest.getDriver().findElement(By.tagName(identifierValue));
		case "XPATH":
			return BaseTest.getDriver().findElement(By.xpath(identifierValue));
		case "LINK TEXT":
			return BaseTest.getDriver().findElement(By.linkText(identifierValue));
		default:
			return null;
		}
	}

	public List<WebElement> getListWebElements(String identifierType, String identifierValue) {
		switch (identifierType) {
		case "ID":
			return BaseTest.getDriver().findElements(By.id(identifierValue));
		case "CSS":
			return BaseTest.getDriver().findElements(By.cssSelector(identifierValue));
		case "TAGNAME":
			return BaseTest.getDriver().findElements(By.tagName(identifierValue));
		case "XPATH":
			return BaseTest.getDriver().findElements(By.xpath(identifierValue));
		case "LINK TEXT":
			return BaseTest.getDriver().findElements(By.linkText(identifierValue));
		default:
			return null;
		}
	}
}
