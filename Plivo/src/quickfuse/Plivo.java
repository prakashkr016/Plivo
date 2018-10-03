package quickfuse;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Plivo {

	public static WebDriver driver;

	public static void main(String[] args) throws InterruptedException {

		// Setting up system property and launching the url
		System.setProperty("webdriver.chrome.driver", ".\\BrowserDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://quickfuseapps.com/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
	    

		
		// Clicking on Create an App then click on Get Started...!!
		driver.findElement(By.xpath("//a[@id='link-create']")).click();
		driver.findElement(By.xpath("//*[@id=\"intro-dialog-cont\"]/div[2]/button")).click();

		
		// Adding a new page
		driver.findElement(By.xpath("//a[@id='add-page']")).click();
		driver.findElement(By.xpath("//input[@name='name'][@class='indented submitonenter']")).sendKeys("Test");
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div[20]/div[3]/button[1]")).click();

		
		// Clicking on Message Menu on left
		driver.findElement(By.xpath("//a[contains(text(),'Messaging')]")).click();

		// Taking off sms tag from menu
		Thread.sleep(1500);
		WebElement From = driver.findElement(By
				.xpath("//li[@class='module-item ui-widget-content ui-corner-all module-item-green ui-draggable'][3]"));
	
		
		// Creating action class object
		Actions act = new Actions(driver);

		// Dragged and dropped at desired x,y location
		act.dragAndDropBy(From, 700, 25).build().perform();

		waitFor(5, "//div[@class='syn-node ui-draggable syn-node-active']");
		From = driver.findElement(By.xpath("//div[@id='module-1']//*[@class='syn-node ui-draggable syn-node-active']"));
	
		waitFor(5,
				"//div[@id='module-2']//*[@class='syn-receptor ui-droppable syn-receptor-north ui-draggable syn-receptor-draggable']");

		WebElement To;
		To = driver.findElement(By.xpath(
				"//div[@class='syn-receptor ui-droppable syn-receptor-north ui-draggable syn-receptor-draggable']"));
		act.clickAndHold(From).release(To).build().perform();

		// Filling in SMS details
		driver.findElement(By.name("phone_constant")).sendKeys("8009224431");
		driver.findElement(By.name("message_phrase[]")).sendKeys("Hello World");

		
		
		// Taking the email feature from Menu
		From = driver.findElement(By
				.xpath("//li[@class='module-item ui-widget-content ui-corner-all module-item-green ui-draggable'][2]"));
		act.dragAndDropBy(From, 1000, 125).build().perform();
	

		// Connecting the sms end node with email top node
		From = driver
				.findElement(By.xpath("//div[@class='syn-node syn-node-attached-e ui-draggable syn-node-active']"));
		To = driver.findElement(By.xpath(
				"//div[@id='module-3']//*[@class='syn-receptor ui-droppable syn-receptor-north ui-draggable syn-receptor-draggable']"));
		act.clickAndHold(From).release(To).build().perform();

		// Filling in required details in email feature
		driver.findElement(By.xpath("//input[@name='smtp_url']")).sendKeys("smtp.gmail.com");
		driver.findElement(By.xpath("//input[@name='port']")).sendKeys("465");
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys("abc@gmail.com");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("password@abc");
		driver.findElement(By.xpath("//textarea[@name='from_constant']")).sendKeys("abc@gmail.com");
		driver.findElement(By.xpath("//textarea[@name='to_constant']")).sendKeys("xyz@gmail.com");
		driver.findElement(By.xpath("//textarea[@name='subject_constant']")).sendKeys("sms not sent");
        driver.findElement(By.xpath("//div[@id='module-3']//*[@name='message_phrase[]']"))
				.sendKeys("sms to phone no 8009224431 not sent");

        
        // Clicking on Basic menu on left
		driver.findElement(By.xpath("//a[contains(text(),'Basic')]")).click();
		
		
		//Call Hold - 1st Call holding
		// Picking up End Call function from menu
		From = driver.findElement(
				By.xpath("//li[@class='module-item ui-widget-content ui-corner-all module-item-red ui-draggable']"));
		act.dragAndDropBy(From, 400, 200).build().perform();
	

		From = driver.findElement(By
				.xpath("//div[@id='module-2']//*[@class='syn-node syn-node-attached-w ui-draggable syn-node-active']"));
		To = driver.findElement(By.xpath(
				"//div[@id='module-4']//*[@class='syn-receptor ui-droppable syn-receptor-north ui-draggable syn-receptor-draggable']"));
		act.clickAndHold(From).release(To).build().perform();

		//Call Hold - 2nd Call Holding
		From = driver.findElement(
				By.xpath("//li[@class='module-item ui-widget-content ui-corner-all module-item-red ui-draggable']"));
		act.dragAndDropBy(From, 600, 400).build().perform();

		From = driver.findElement(By
				.xpath("//div[@id='module-3']//*[@class='syn-node syn-node-attached-w ui-draggable syn-node-active']"));
		To = driver.findElement(By.xpath(
				"//div[@id='module-5']//*[@class='syn-receptor ui-droppable syn-receptor-north ui-draggable syn-receptor-draggable']"));
		act.clickAndHold(From).release(To).build().perform();

		//Call Hold - 3rd Call Holding
		From = driver.findElement(
				By.xpath("//li[@class='module-item ui-widget-content ui-corner-all module-item-red ui-draggable']"));
		act.dragAndDropBy(From, 1400, 400).build().perform();

		From = driver.findElement(By
				.xpath("//div[@id='module-3']//*[@class='syn-node syn-node-attached-e ui-draggable syn-node-active']"));
		To = driver.findElement(By.xpath(
				"//div[@id='module-6']//*[@class='syn-receptor ui-droppable syn-receptor-north ui-draggable syn-receptor-draggable']"));
		act.clickAndHold(From).release(To).build().perform();

		driver.close();

	}

	public static void waitFor(int loadtime, String xpath1) {
		new WebDriverWait(driver, loadtime).until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath1)));
	}
}
