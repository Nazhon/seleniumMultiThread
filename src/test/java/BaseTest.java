
package test.java;

import java.io.File;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.TimeZone;
import java.util.UUID;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.epam.healenium.SelfHealingDriver;

import main.java.config.Context;
import main.java.config.Utils;

public class BaseTest {

	// credenciales TIA
	public static String userSuscriptor = "tia";
	public static String passSuscriptor = "tia";
	public static String userSuscriptor1 = "ext-martha.hernandez@sbins.cl";
	public static String passSuscriptor1 = "pruebasqa1234";
	// credenciales Portal Privado
	public static String userregistrado = "16.035.093-8";
	public static String passregistrada = "CVR8-V49N-3EJ7-2K9S";
	public static String userregistrado2 = "39.708.230-K";
	public static String passregistrada2 = "Pruebas_qa2024";

	private static ThreadLocal<WebDriver> delegate = new ThreadLocal<>();
	private static ThreadLocal<SelfHealingDriver> driver = new ThreadLocal<>();
	public static ExtentReports extent;
	public static ExtentSparkReporter spark;
	// public static ExtentTest test;
	// public static ExtentTest step;
	private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
	private static ThreadLocal<ExtentTest> step = new ThreadLocal<>();

	protected static String environment;

	public static ExtentTest getTest() {
		return test.get();
	}

	public static ExtentTest getStep() {
		return step.get();
	}

	public static SelfHealingDriver getDriver() {
		return driver.get();
	}

	public static void setDriver(SelfHealingDriver healingDriver) {
		driver.set(healingDriver);
	}

	public static WebDriver getDelegate() {
		return delegate.get();
	}

	public static void setDelegate(WebDriver webDriver) {
		delegate.set(webDriver);
	}

	public static void setTest(ExtentTest extentTest) {
		test.set(extentTest);
	}

	public static void setStep(ExtentTest extentStep) {
		step.set(extentStep);
	}

	@BeforeTest(description = "Se configura el controlador y el navegador")
	public void beforeTestMethod() {
		TimeZone timeZone = TimeZone.getTimeZone("America/Santiago");
		TimeZone.setDefault(timeZone);
		environment = System.getProperty("environment");
		//environment = "UAT";
		extent = new ExtentReports();
		spark = new ExtentSparkReporter("report" + File.separator + "AutomationReport.html");
		spark.config().setDocumentTitle("Reporte de automatización de pruebas");
		spark.config().setEncoding("UTF-8");
		spark.config().setReportName("Resultado Pruebas Automatizadas | Ambiente " + environment);
		extent.attachReporter(spark);
		extent.setSystemInfo("Proyecto", "ALMA");
		extent.setSystemInfo("Automatizador", "QA TestGroup");
		extent.setSystemInfo("Sistema Operativo", System.getProperty("os.name"));
		extent.setSystemInfo("Zona horaria", TimeZone.getDefault().getDisplayName());
		extent.setSystemInfo("Ambiente", environment);
	}

	@BeforeMethod(description = "Se configura el reporte de pruebas")
	@Parameters(value = { "browserName" })
	public void beforeMethod(String browserName, Method testMethod, ITestResult result) {

		test.set(extent.createTest(Utils.convertToPascalCase(testMethod.getName())));
		step.set(getTest());
		System.out.println(String.format("Se inicia la ejecución del escenario: %s", testMethod.getName()));
		setUpDriver(browserName);
		//getDriver().manage().window().maximize();
//		driver.get(Constants.url);
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	}

	@AfterMethod
	public void afterMethod(ITestResult result) {
		try {
			String testName = result.getName();
			String evidencia;

			if (result.getStatus() == ITestResult.SUCCESS) {

				getStep().log(Status.PASS, MarkupHelper.createLabel("Prueba exitosa: " + testName, ExtentColor.GREEN));

				evidencia = Utils.takeScreenshot(testName, "passed");

				getStep().pass("Clic para visualizar evidencia",
						MediaEntityBuilder.createScreenCaptureFromBase64String(evidencia).build());

				System.out.println("Prueba exitosa: " + testName);
			} else if (result.getStatus() == ITestResult.SKIP) {

				getStep().log(Status.SKIP,
						MarkupHelper.createLabel("Prueba incompleta: " + testName, ExtentColor.AMBER));

				evidencia = Utils.takeScreenshot(testName, "skipped");

				getStep().pass("Clic para visualizar evidencia",
						MediaEntityBuilder.createScreenCaptureFromBase64String(evidencia).build());

				System.out.println("Prueba incompleta: " + testName);

			} else if (result.getStatus() == ITestResult.FAILURE) {

				Utils.errorLOG(result);
			}
		} finally {

			if (getDelegate() != null) {
				getDelegate().quit();
			}

			driver.remove();
			delegate.remove();
			test.remove();
			step.remove();
		}
	}

	@AfterTest
	public void afterTest() {
		extent.setSystemInfo("Navegador", "Chrome");
		// extent.setSystemInfo("Navegador", ((RemoteWebDriver)
		// getDriver().getDelegate()).getCapabilities().getBrowserName());
		// extent.setSystemInfo("Version", ((RemoteWebDriver)
		// getDriver().getDelegate()).getCapabilities().getBrowserVersion());
		extent.flush();
	}

	@Context(description = "Configura el tipo y las características del controlador")
	public void setUpDriver(String browserName) {

		//System.setProperty("webdriver.chrome.driver",
		//		"C:\\Users\\ext-jsanchez\\.cache\\selenium\\chromedriver\\win64\\148.0.7778.178\\chromedriver.exe");
		if (browserName.contains("chrome")) {

			ChromeOptions options = new ChromeOptions();
			options.addArguments("--headless");// Comentar cuando se quiera visualizar el
			// navegador durante la ejecución
			options.addArguments("--window-size=1920,1080");
			options.addArguments("--user-data-dir=/tmp/chrome-user-data-" + UUID.randomUUID(), "--no-sandbox",
					"--disable-dev-shm-usage");

			WebDriver localDriver = new ChromeDriver(options);
			delegate.set(localDriver);

			SelfHealingDriver healingDriver = SelfHealingDriver.create(localDriver);

			driver.set(healingDriver);

			System.out.println("THREAD: " + Thread.currentThread().getId());

			System.out.println("SESSION: " + ((RemoteWebDriver) getDriver().getDelegate()).getSessionId());

		} else if (browserName.contains("edge")) {
//			driver = new EdgeDriver();

		} else if (browserName.contains("firefox")) {
//			driver = new FirefoxDriver();
		}
	}
}
