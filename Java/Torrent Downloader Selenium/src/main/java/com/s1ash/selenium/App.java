package com.s1ash.selenium;

import com.s1ash.selenium.iptorrents.IptorrentsDownloader;

public class App {

	public static void main(String[] args) {
		IptorrentsDownloader iptorrents = new IptorrentsDownloader();
		iptorrents.downloadTorrent("Escape from New York", "1080p", 8.2);
	}

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		/*
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\s1ash\\eclipse-workspace\\SeleniumPractice\\chromedriver.exe");

		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("http://www.google.com");

		WebElement element = driver.findElement(By.name("q"));
		element.sendKeys("cmtv");
		element.submit();
		
		System.out.println("click cmtv on google");
		driver.findElement(By.xpath("//*[@id=\"rso\"]/div[1]/div/div/div/div/div[1]/a")).click();
		
		//sleep(1000);
		System.out.println("avançar eu aceito button");
		driver.findElement(By.xpath("//*[@id=\"qc-cmp-purpose-button\"]")).click();
		//sleep(1000);
		
		System.out.println("guardar cenas");
		driver.findElement(By.xpath("//*[@id=\"qcCmpUi\"]/div/div[3]/button")).click();
		sleep(2000);

		try {
			System.out.println("a aceitar");
			driver.findElement(By.xpath("//*[@id=\"avancarintro\"]")).click();
		} catch (NoSuchElementException e) {
			System.out.println("excepçao");
			// TODO: handle exception
		}
		
		
		sleep(5000);
		
		System.out.println("clickar na noticia");
		driver.findElement(By.xpath("/html/body/section[2]/div/div/div[1]/div[3]/div[11]/article/div[1]/h3/a")).click();
		
		//((WebElement) findElements.get(0)).click();;
		
	}

	private static void sleep(int sleep) {
		try {
			Thread.sleep(sleep);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	*/
}
