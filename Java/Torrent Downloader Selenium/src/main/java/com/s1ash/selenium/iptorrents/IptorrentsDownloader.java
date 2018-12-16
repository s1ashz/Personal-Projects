package com.s1ash.selenium.iptorrents;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static com.s1ash.selenium.util.conts.*;

public class IptorrentsDownloader {

	private final String CLASS_WEBPAGE = "https://www.iptorrents.com";
	private final String USERNAME_INPUT_XPATH = "//*[@id=\"login\"]/form/ul/li[1]/input";
	private final String PASSWORD_INPUT_XPATH = "//*[@id=\"login\"]/form/ul/li[2]/input";
	private final String AUTH_BTN_XPATH = "//*[@id=\"login\"]/form/ul/li[3]/input";
	
	private final String SEARCH_BAR_XPATH = "//*[@id=\"Search\"]/tbody/tr/td/input[1]";
	private final String SUBMINT_SEARCH_BAR_XPATH = "//*[@id=\"Search\"]/tbody/tr/td/input[2]";
	
	private final String SORT_BY_SEEDERS_XPATH = "//*[@id=\"torrents\"]/tbody/tr[1]/th[8]/a";
	
	private String NAME_TORRENT_XPATH;
	private String SIZE_TORRENT_XPATH;
	private String DOWNLOAD_LINK_XPATH;
	
	private int torrentTableLength = 41;
	
	private WebDriver chromeDriver;
	
	private String requestedTorrentName;
	private String requestedTorrentQuality;
	private double requestedTorrentSize;
	
	public IptorrentsDownloader() {
		System.getProperty("webdriver.chrome.driver", "C:\\Users\\s1ash\\eclipse-workspace\\SeleniumPractice\\chromedriver.exe");
		chromeDriver = new ChromeDriver();
		chromeDriver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	}
	
	public void downloadTorrent(String torrentName, String torrentQuality, double torrentSize) {
		this.requestedTorrentName = torrentName;
		this.requestedTorrentQuality = torrentQuality;
		this.requestedTorrentSize = torrentSize;
		
		openWebPage();
		submitAuth();
		searchAndDownloadTorrent();
	}
	
	private void openWebPage() {
		chromeDriver.get(CLASS_WEBPAGE);
	}

	private void submitAuth() {
		chromeDriver.findElement(By.xpath(USERNAME_INPUT_XPATH)).sendKeys("s1ash");
		chromeDriver.findElement(By.xpath(PASSWORD_INPUT_XPATH)).sendKeys(LEL);
		chromeDriver.findElement(By.xpath(AUTH_BTN_XPATH)).click();
	}
	
	
	private void searchAndDownloadTorrent() {
		searchAndOrderTorrentsBySeeds();
		downloadTorrentFromList();
	}
	
	private void searchAndOrderTorrentsBySeeds() {
		chromeDriver.findElement(By.xpath(SEARCH_BAR_XPATH)).sendKeys(requestedTorrentName);
		chromeDriver.findElement(By.xpath(SUBMINT_SEARCH_BAR_XPATH)).click();
		chromeDriver.findElement(By.xpath(SORT_BY_SEEDERS_XPATH)).click();
	}

	private void downloadTorrentFromList() {
		boolean torrentNotFound = true;
		int i = 1;
		
		while (torrentNotFound) {
			i++;
			
			if (i > torrentTableLength ) {
				System.out.println("torrent was not found...");
				return;
			}
			
			getTorrentDataFromTableRow(i);
			String tableTorrentName = getTorrentName();
			double tableTorrentSize = getTorrentSize();
			
			if ( validateTorrentNameAndSize(tableTorrentName, tableTorrentSize) ) {
				WebElement downloadLink = chromeDriver.findElement(By.xpath(DOWNLOAD_LINK_XPATH));
				downloadLink.click();
				torrentNotFound = false;
				System.out.println("Torrent Found: " + tableTorrentName + " size: " + tableTorrentSize);
			}
		}
	}

	private void getTorrentDataFromTableRow(int i) {
		NAME_TORRENT_XPATH = "//*[@id=\"torrents\"]/tbody/tr[" + i + "]/td[2]/a";
		SIZE_TORRENT_XPATH = "//*[@id=\"torrents\"]/tbody/tr[" + i + "]/td[6]";
		DOWNLOAD_LINK_XPATH = "//*[@id=\"torrents\"]/tbody/tr[" + i + "]/td[4]";
	}
	
	private String getTorrentName() {
		return chromeDriver.findElement(By.xpath(NAME_TORRENT_XPATH)).getText().toLowerCase();
	}
	
	private Double getTorrentSize() {
		String torrentSize = chromeDriver.findElement(By.xpath(SIZE_TORRENT_XPATH)).getText();
		torrentSize = torrentSize.replaceAll(" GB", "");
		torrentSize = torrentSize.replaceAll(" MB", "");
		torrentSize = torrentSize.replaceAll(" KB", "");
		return Double.valueOf(torrentSize);
	}

	private boolean validateTorrentNameAndSize(String tableTorrentName, double tableTorrentSize) {
		return tableTorrentName.contains(requestedTorrentName.toLowerCase()) && tableTorrentName.contains(requestedTorrentQuality) && tableTorrentSize < requestedTorrentSize;
	}
	
}
