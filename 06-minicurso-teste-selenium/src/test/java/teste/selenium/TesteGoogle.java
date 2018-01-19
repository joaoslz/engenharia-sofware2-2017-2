package teste.selenium;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteGoogle {

	@Test
	public void testeTituloPaginaDoGoogle() {

		// System.setProperty("webdriver.chrome.driver", "c:\\drivers\\chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", "/home/joao/drivers/chromedriver");
		
		
		// abre o chrome
		WebDriver driver = new ChromeDriver();
				
		driver.manage().window().setSize(new Dimension(1200, 765));

		driver.get("http://www.google.com");

		Assert.assertEquals("Google", driver.getTitle());
		//driver.quit();
	}

}