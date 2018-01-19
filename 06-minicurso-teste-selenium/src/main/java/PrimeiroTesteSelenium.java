import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class PrimeiroTesteSelenium {
	
	public static void main(String[] args) {

		System.setProperty("webdriver.chrome.driver", "/home/joao/drivers/chromedriver");
		//System.setProperty("webdriver.chrome.driver", "c:\\drivers\\chromedriver.exe");
		
		// abre o chrome
		WebDriver driver = new ChromeDriver();
		
		// acessar o site do google
		driver.get("http://www.google.com.br");
		
		// digitar no campo de busca selenium webdriver
		WebElement campoDeBusca = driver.findElement(By.name("q") );
		
		campoDeBusca.sendKeys("Selenium webdriver");
		
        // submete o formulário
		campoDeBusca.submit();
		
	}

}
