package com.anthares.AnaAntunes;

import java.io.Serializable;

public class FileContent implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private long currentLiftPNumber;
	private long currentTaskPNumber;
	
	private String boat;
	private String currentLiftPLabel;
	private String currentTaskPLabel;
	
	private long currentYear;
	private String lastUpdateDate;
	
	public long getCurrentLiftPNumber() {
		return currentLiftPNumber;
	}

	public void setCurrentLiftPNumber(long currentLiftPNumber) {
		this.currentLiftPNumber = currentLiftPNumber;
	}

	public long getCurrentTaskPNumber() {
		return currentTaskPNumber;
	}

	public void setCurrentTaskPNumber(long currentTaskPNumber) {
		this.currentTaskPNumber = currentTaskPNumber;
	}

	public String getCurrentLiftPLabel() {
		return currentLiftPLabel;
	}

	public void setCurrentLiftPLabel(String currentLiftPLabel) {
		this.currentLiftPLabel = currentLiftPLabel;
	}

	public String getCurrentTaskPLabel() {
		return currentTaskPLabel;
	}

	public void setCurrentTaskPLabel(String currentTaskPLabel) {
		this.currentTaskPLabel = currentTaskPLabel;
	}

	public long getCurrentYear() {
		return currentYear;
	}

	public void setCurrentYear(long currentYear) {
		this.currentYear = currentYear;
	}

	public String getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(String lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public String getBoat() {
		return boat;
	}

	public void setBoat(String boat) {
		this.boat = boat;
	}

	@Override
	public String toString() {
		return "FileContent [currentLiftPNumber=" + currentLiftPNumber + ", currentTaskPNumber=" + currentTaskPNumber
				+ ", boat=" + boat + ", currentLiftPLabel=" + currentLiftPLabel + ", currentTaskPLabel="
				+ currentTaskPLabel + ", currentYear=" + currentYear + ", lastUpdateDate=" + lastUpdateDate + "]";
	}
	
	
	
}
