package co.com.techandsolve.testatdd;

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

public class GuardarFeature {
	WebDriver webDriver;
	
	@Before("@guardar")
	public void setup(){
		
	}
	
	@After("@guardar")
	public void after(){
		Utilidadesbd.ejecutarSentencia("DELETE FROM card WHERE cedula = '1115069076';");
	}
	
	@Given("^Dado que el usuario ingresa a la aplicacion para guardar una tarjeta$")
	public void dado_que_el_usuario_ingresa_a_la_aplicacion_para_guardar_una_tarjeta() throws Throwable {
		webDriver = new FirefoxDriver();
		webDriver.get("http://localhost:8080/CreditCardWeb/");
	}
	
	
	@When("^El usuario da click en el boton nuevo$")
	public void el_usuario_da_click_en_el_boton_nuevo() throws Throwable {
		WebElement nuevoElement = webDriver.findElement(By.name("new"));
		nuevoElement.click();
	}

	@Then("^debe registrar la tarjeta con el nombre \"([^\"]*)\" con la cedula \"([^\"]*)\", el monto de (\\d+) y en la fecha (\\d+)-(\\d+)-(\\d+)$")
	public void debe_registrar_la_tarjeta_con_el_nombre_con_la_cedula_el_monto_de_y_en_la_fecha(String label, String cedula, int monto, int anno, int mes, int dia) throws Throwable {
		WebElement cedulaElement = webDriver.findElement(By.name("cedula"));
		WebElement lableElement = webDriver.findElement(By.name("label"));
		WebElement montoElement = webDriver.findElement(By.name("mount"));
		WebElement fechaElement = webDriver.findElement(By.name("dateCut"));
		WebElement guardarElement = webDriver.findElement(By.name("save"));

		cedulaElement.sendKeys(cedula);
		montoElement.sendKeys(monto+"");
		lableElement.sendKeys(label);
		fechaElement.sendKeys(anno+"-"+mes+"-"+dia);
		guardarElement.click();
	}
	
	
	

	@Then("^debe visualizar una dialog con el mensaje \"([^\"]*)\"$")
	public void debe_visualizar_una_dialog_con_el_mensaje(String mensaje) throws Throwable {
		try { 
			WebDriverWait wait = new WebDriverWait(webDriver, 2000);
			wait.until(ExpectedConditions.alertIsPresent()); 
			Alert alert = webDriver.switchTo().alert(); 
			Assert.assertEquals(mensaje, alert.getText());
			alert.accept();
			} catch (Exception e) { 
				//exception handling } 
		}
		
		webDriver.close();
	}

}