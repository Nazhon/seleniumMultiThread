package main.java.utils;

import java.time.Duration;
import java.util.List;
import java.util.Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;

import main.java.config.Context;
import main.java.config.Utils;
import main.java.config.Validation;
import main.java.pageEvents.tia.transversal.TransversalEvents;
import test.java.BaseTest;

public class Events extends BaseTest {

	@Context(description = "Obtiene el texto de un elemento")
	public static String getText(String xpathElement) {
		try {
			WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(50));

			WebElement element = getDriver().findElement(By.xpath(xpathElement));
			wait.until(ExpectedConditions.visibilityOf(element));
			return element.getText();
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
			return null;
		}
	}

	@Context(description = "Verifica si un elemento está visible")
	public static boolean isVisible(String xpath) {
		try {
			List<WebElement> elements = BaseTest.getDriver().findElements(By.xpath(xpath));
			return !elements.isEmpty() && elements.get(0).isDisplayed();
		} catch (NoSuchElementException | StaleElementReferenceException e) {
			return false;
		}
	}
	
	@Context(description = "Verifica si un elemento está visible")
	public static boolean isSelected(String xpath) {
		try {
			WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(50));

			WebElement element = getDriver().findElement(By.xpath(xpath));
			wait.until(ExpectedConditions.elementToBeSelected(element));
			return true;
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
			return false;
		}
		
	}

	@Context(description = "Selecciona un botón")
	public static void clickButtonWithArray(String xpathElement, String numero) {
		try {
			int num = Integer.parseInt(numero.trim());

			// Encuentra todos los elementos que coinciden con el XPath proporcionado
			List<WebElement> elements = BaseTest.getDriver().findElements(By.xpath(xpathElement));
			Utils.outputInfo("Número de elementos encontrados: " + elements.size() + " para el XPath: " + xpathElement);

			// Verifica si el índice está dentro de los límites
			if (num >= 0 && num < elements.size()) {
				WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(50));
				wait.until(ExpectedConditions.elementToBeClickable(elements.get(num)));

				String buttonName = getName(elements.get(num), xpathElement);

				if (elements.get(num).isEnabled()) {
					elements.get(num).click();
					Utils.outputInfo("Se ha hecho clic en el botón: " + buttonName);
				} else {
					Utils.eventFailed("El botón '" + buttonName + "' no está desplegado o habilitado");
				}
			} else {
				Utils.eventFailed(
						"Índice " + num + " fuera de límites para la lista de elementos con tamaño " + elements.size());
			}
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Obtiene el valor de un elemento")
	public static String getValue(String xpathElement) {
		try {
			WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(50));

			WebElement element = getDriver().findElement(By.xpath(xpathElement));
			wait.until(ExpectedConditions.visibilityOf(element));
			return element.getAttribute("value");
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
			return null;
		}
	}

	@Context(description = "Obtiene el valor dado un atributo de un elemento")
	public static String getAtributte(String xpathElement, String atributte) {
		try {
			WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(50));

			WebElement element = getDriver().findElement(By.xpath(xpathElement));
			wait.until(ExpectedConditions.visibilityOf(element));
			return element.getAttribute(atributte);
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
			return null;
		}
	}

	@Context(description = "Escribe dentro de un campo de texto ubicado según xpath")
	public static void sendKeys(String xpathElement, String text) {
		try {
			WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(50));

			WebElement element = BaseTest.getDriver().findElement(By.xpath(xpathElement));
			wait.until(ExpectedConditions.visibilityOf(element));

			String inputName = getName(element, xpathElement);
			if (element.isDisplayed() && element.isEnabled()) {
				int caracteres = element.getAttribute("value").toCharArray().length;
				for (int i = 0; i < caracteres; i++) {
					element.sendKeys(Keys.BACK_SPACE);
				}
				element.sendKeys(text);
				Utils.outputInfo("Se ha ingresado el texto '" + text + "' en el campo: " + inputName);
				Validation.trueBooleanCondition(
						element.getAttribute("value").contains(text)
								|| element.getAttribute("value").contains(text.replaceAll(" ", "")),
						"El texto se ha ingresado correctamente",
						String.format(
								"El texto no se ha ingresado correctamente. Valor ingresado %s, Valor que se requiere ingresar %s",
								element.getAttribute("value"), text));
			} else {
				Utils.eventFailed("El campo '" + inputName + "' no se encuentra habilitado o desplegado");
			}
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Escribe dentro de un campo de texto ubicado según xpath y ademas envia una tecla")
	public static void sendKeys(String xpathElement, CharSequence... keysToSend) {
		try {
			WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(50));

			WebElement element = BaseTest.getDriver().findElement(By.xpath(xpathElement));
			wait.until(ExpectedConditions.visibilityOf(element));

			String inputName = getName(element, xpathElement);
			if (element.isDisplayed() && element.isEnabled()) {
				// Limpiar campo manualmente con BACK_SPACE
				int caracteres = element.getAttribute("value").toCharArray().length;
				for (int i = 0; i < caracteres; i++) {
					element.sendKeys(Keys.BACK_SPACE);
				}

				element.sendKeys(keysToSend); // Aquí ahora puede recibir texto y teclas
				String textoIngresado = element.getAttribute("value");

				// Validar solo con la parte de texto (sin ENTER ni teclas especiales)
				String textoEsperado = "";
				for (CharSequence key : keysToSend) {
					if (!(key instanceof Keys)) {
						textoEsperado += key.toString();
					}
				}

				Utils.outputInfo("Se ha ingresado el texto '" + textoIngresado + "' en el campo: " + inputName);
				Validation.trueBooleanCondition(
						textoIngresado.contains(textoEsperado)
								|| textoIngresado.contains(textoEsperado.replaceAll(" ", "")),
						"El texto se ha ingresado correctamente",
						String.format(
								"El texto no se ha ingresado correctamente. Valor ingresado %s, Valor que se requiere ingresar %s",
								textoIngresado, textoEsperado));
			} else {
				Utils.eventFailed("El campo '" + inputName + "' no se encuentra habilitado o desplegado");
			}
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Escribe dentro de un campo de texto ubicado según xpath pero indicando en el mensaje el nombre de la varibale en vez de indicar el ID del elemento web")
	public static void sendKeysWithReturnVariableName(String xpathElement, String text) {
		try {
			WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(50));

			WebElement element = BaseTest.getDriver().findElement(By.xpath(xpathElement));
			wait.until(ExpectedConditions.visibilityOf(element));

			String inputName = Events.getVariableName(xpathElement);
			if (element.isDisplayed() && element.isEnabled()) {
				int caracteres = element.getAttribute("value").toCharArray().length;
				for (int i = 0; i < caracteres; i++) {
					element.sendKeys(Keys.BACK_SPACE);
				}

				element.sendKeys(text);
				Utils.outputInfo("Se ha ingresado el texto '" + text + "' en el campo: " + inputName);
				Validation.trueBooleanCondition(
						element.getAttribute("value").contains(text)
								|| element.getAttribute("value").contains(text.replaceAll(" ", "")),
						"El texto se ha ingresado correctamente",
						String.format(
								"El texto no se ha ingresado correctamente. Valor ingresado %s, Valor que se requiere ingresar %s",
								element.getAttribute("value"), text));
			} else {
				Utils.eventFailed("El campo '" + inputName + "' no se encuentra habilitado o desplegado");
			}
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Selecciona un botón")
	public static void clickButton(String xpathElement) {
		try {
			WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(50));

//			WebElement element = BaseTest.getDriver().findElement(By.xpath(xpathElement));
			WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathElement)));
			String buttonName = getName(element, xpathElement);

			if (element.isEnabled()) {
				element.click();
				Utils.outputInfo("Se ha hecho clic en el botón: " + buttonName);
			} else {
				Utils.eventFailed("El botón '" + buttonName + "' no está desplegado o habilitado");
			}
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Obtiene el nombre del elemento, de lo contrario obtiene el nombre de la variable")
	public static String getName(WebElement element, String xpathElement) {
		String name = "";
		try {
			name = Utils.getName(element, xpathElement);
			if (Objects.equals(name, "") || name.isEmpty() || name.equals("null")) {
				name = Utils.elementName(xpathElement);
			}
			return name;
		} catch (Exception e) {
			name = Utils.elementName(xpathElement);
			return name;
		}
	}

	@Context(description = "Obtiene el nombre de la variable")
	public static String getVariableName(String xpathElement) {
		return Utils.elementNameRelativeClass(xpathElement);
	}

	@Context(description = "Presiona la tecla ENTER en el contexto de ingresar texto en un campo")
	public static void enter(String inputXpath) {
		try {
			WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(50));
			Actions actions = new Actions(getDriver());

			WebElement element = BaseTest.getDriver().findElement(By.xpath(inputXpath));
			wait.until(ExpectedConditions.visibilityOf(element));

			actions.moveToElement(element).build().perform();
			actions.sendKeys(Keys.ENTER);
			Utils.outputInfo("Se ha presionado la tecla ENTER");

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Devuelve una lista de elementos")
	public static List<WebElement> getElementList(String listXpath) {
		try {
			WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(50));

			List<WebElement> elements = BaseTest.getDriver().findElements(By.xpath(listXpath));
			wait.until(ExpectedConditions.visibilityOfAllElements(elements));
			return elements;

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
			return null;
		}
	}

	@BeforeMethod(description = "Selecciona un elemento de la lista donde el texto contenga la opción a seleccionar")
	public static String selectByContainsText(String xpathElement, String option) {
		try {
			WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(50));

			WebElement element = BaseTest.getDriver().findElement(By.xpath(xpathElement));
			wait.until(ExpectedConditions.visibilityOf(element));
			String name = getName(element, xpathElement);

			Select select = new Select(getDriver().findElement(By.xpath(xpathElement)));

			for (int i = 0; i < select.getOptions().size(); i++) {
				String opcion = select.getOptions().get(i).getText();
				if (opcion.contains(option)) {
					select.getOptions().get(i).click();
					break;
				}
			}

			String selectedOption = select.getFirstSelectedOption().getText();
			Validation.trueBooleanCondition(!selectedOption.equals(""),
					"Se ha seleccionado la opción '" + selectedOption + "' en la lista desplegable '" + name + "'",
					"No se selecciono correctamente la opcion.");
			// Utils.outputInfo("Se ha seleccionado la opción '" + selectedOption + "' en la
			// lista desplegable '" + name + "'");

			return selectedOption;

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
			return null;
		}
	}

	@Context(description = "Presiona la tecla ENTER en el contexto de ingresar texto en un campo")
	public static void enterAlt(String inputXpath) {
		try {
			WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(50));
			Actions actions = new Actions(getDriver());

			WebElement element = BaseTest.getDriver().findElement(By.xpath(inputXpath));
			wait.until(ExpectedConditions.visibilityOf(element));

			actions.moveToElement(element).build().perform();
			actions.sendKeys(Keys.RETURN);
			Utils.outputInfo("Se ha presionado la tecla RETURN");

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Se hace scroll para encontrar la póliza generada")
	public static void scroll(String inputXpath) {
		try {
			Actions actions = new Actions(getDriver());

			WebElement element = BaseTest.getDriver().findElement(By.xpath(inputXpath));

			actions.scrollToElement(element).perform();
			Utils.outputInfo("Se ha hecho scroll correctamente hasta:" + element.getText());

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Limpiara la caja de texto", page = "Login", functionality = "Transversal")
	public static void limpiarCajaDeTexto(String limiparFecha) {

		try {
			WebElement element = BaseTest.getDriver().findElement(By.xpath(limiparFecha));
			if (!element.getText().equals("")) {
				Utils.outputInfo("El elemento tenía como texto" + element.getText() + " el cuál se ha limpiado ");
				element.clear();
			} else {
				Utils.outputInfo("El elemento esta vacio por lo que no se limpiará");
			}

//			Actions actions = new Actions(driver);
//			actions.sendKeys(Keys.ENTER);

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Presiona la tecla ENTER en el contexto de ingresar texto en un campo")
	public static void teclaAbajo(String inputXpath, int num) {
		try {
			WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(50));
			Actions actions = new Actions(getDriver());

			WebElement element = BaseTest.getDriver().findElement(By.xpath(inputXpath));
			wait.until(ExpectedConditions.visibilityOf(element));

			actions.moveToElement(element).build().perform();
			Thread.sleep(1500);
			actions.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
			TransversalEvents.loadingTIAGeneral();
			Utils.outputInfo("Se ha presionado tecla ABAJO y ENTER");

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@BeforeMethod(description = "Selecciona un elemento de la lista donde el texto coincida exactamente con la opción a seleccionar")
	public static String selectByExactText(String xpathElement, String option) {
		try {
			WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(60));

			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathElement)));

			String name = getName(element, xpathElement);
			Select select = new Select(element);

			select.selectByVisibleText(option);
			Utils.outputInfo("Se ha seleccionado la opción '" + option + "' en la lista '" + name + "'");
			return option;

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
			return null;
		}
	}

}
