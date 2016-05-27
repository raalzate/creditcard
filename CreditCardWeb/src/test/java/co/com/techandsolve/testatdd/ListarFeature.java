package co.com.techandsolve.testatdd;

import java.util.List;

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

public class ListarFeature {
	
	WebDriver webDriver;
	
	@Before("@listar")
	public void setup(){
		Utilidadesbd.ejecutarSentencia("INSERT INTO card (label, status, amount, cedula, bonus) values ('Tarjeta Test', 0, 1500000, '11111', 0);");
		Utilidadesbd.ejecutarSentencia("INSERT INTO card (label, status, amount, cedula, bonus) values ('Tarjeta Test Bloq', 1, 1500000, '22222', 0);");

	}
	
	@After("@listar")
	public void after(){
		Utilidadesbd.ejecutarSentencia("DELETE FROM card WHERE cedula = '22222';");
		Utilidadesbd.ejecutarSentencia("DELETE FROM card WHERE cedula = '11111';");
	}
	
	
	@Given("^Dado que el usuario ingresa a la aplicacion$")
	public void dado_que_el_usuario_ingresa_a_la_aplicacion() throws Throwable {
		webDriver = new FirefoxDriver();
		webDriver.get("http://localhost:8080/CreditCardWeb/");
	}

	@When("^El usuario busque con la cedula (\\d+)$")
	public void el_usuario_busque_con_la_cedula(int cedula) throws Throwable {
		WebElement cedulaElement = webDriver.findElement(By.name("cedula"));
		WebElement buscarElement = webDriver.findElement(By.name("buscar"));

		cedulaElement.sendKeys(cedula+"");
		buscarElement.click();
		
	}

	@Then("^debe aparcer el usuario (\\d+)$")
	public void debe_aparcer_el_usuario(int cedula) throws Throwable {
		List<WebElement> cedulaElement = webDriver.findElements(By.name("card"));
		boolean estaEnLaLista = false;
		
		for (WebElement webElement : cedulaElement) {
			estaEnLaLista = webElement.getText().contains("11111");
			
		}
		
		Assert.assertTrue(estaEnLaLista);
		webDriver.close();
		
	}
	
	@Then("^Debe aparecer una alerta especificando el error \"([^\"]*)\"$")
	public void debe_aparecer_una_alerta_especificando_el_error(String mensaje) throws Throwable {
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
	
	@Then("^Debe eliminar la tarjeta \"([^\"]*)\" y aceptar el mensaje \"([^\"]*)\"$")
	public void debe_eliminar_la_tarjeta_y_aceptar_el_mensaje(String tarjeta, String mensaje) throws Throwable {
		//11111
		
		List<WebElement> cedulaElement = webDriver.findElements(By.name("card"));
		List<WebElement> eliminarElement = webDriver.findElements(By.name("eliminar"));

		int index = 0;
		for (WebElement webElement : cedulaElement) {
			if(webElement.getText().contains(tarjeta)){
				eliminarElement.get(index).click();
				try { 
					WebDriverWait wait = new WebDriverWait(webDriver, 2000);
					wait.until(ExpectedConditions.alertIsPresent()); 
					Alert alert = webDriver.switchTo().alert(); 
					Assert.assertEquals(mensaje, alert.getText());
					alert.accept();
					} catch (Exception e) { 
						//exception handling } 
				}
				
				break;
			}
			index++;
		}
		
	}
	
	@Then("^Debe aparecer una alerta especificando mensaje exitoso \"([^\"]*)\"$")
	public void debe_aparecer_una_alerta_especificando_mensaje_exitoso(String mensaje) throws Throwable {
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
