package co.com.techandsolve.testatdd;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class GuardarFacturaFeature {

WebDriver webDriver;
	
	@Before("@guardarFactura")
	public void setup(){
		Utilidadesbd.ejecutarSentencia("DELETE FROM bill WHERE label = 'Factura de credito (test)';");
	}
	
	@After("@guardarFactura")
	public void after(){
		Utilidadesbd.ejecutarSentencia("DELETE FROM bill WHERE label = 'Factura de credito (test)';");
		webDriver.close();
	}

	@Given("^Dado que el usuario ingresa a la aplicacion para guardar una factura$")
	public void dado_que_el_usuario_ingresa_a_la_aplicacion_para_guardar_una_factura() throws Throwable {
		webDriver = new FirefoxDriver();
		webDriver.get("http://localhost:8080/CreditCardWeb/#/bill");
	}

	@When("^El usuario ingresa la factura con el nombre \"([^\"]*)\", con la descripcion \"([^\"]*)\" y con el monto de (\\d+)$")
	public void el_usuario_ingresa_la_factura_con_el_nombre_con_la_descripcion_y_con_el_monto_de(String label, String description, int monto) throws Throwable {
		WebElement lableElement = webDriver.findElement(By.name("label"));
		WebElement montoElement = webDriver.findElement(By.name("mount"));
		WebElement descriptionElement = webDriver.findElement(By.name("description"));
		WebElement guardarElement = webDriver.findElement(By.name("save"));

		montoElement.sendKeys(monto+"");
		lableElement.sendKeys(label);
		descriptionElement.sendKeys(description);
		guardarElement.click();
	}

	@Then("^el sistema visualiza un mensaje  con el nombre de \"([^\"]*)\"$")
	public void el_sistema_visualiza_un_mensaje_con_el_nombre_de(String mensaje) throws Throwable {
		try { 
			WebDriverWait wait = new WebDriverWait(webDriver, 2000);
			wait.until(ExpectedConditions.alertIsPresent()); 
			Alert alert = webDriver.switchTo().alert(); 
			Assert.assertEquals(mensaje, alert.getText());
			alert.accept();
			} catch (Exception e) { 
				//exception handling } 
		}
	}

}
