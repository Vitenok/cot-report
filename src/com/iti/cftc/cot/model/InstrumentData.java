package com.iti.cftc.cot.model;

import java.util.Date;

public class InstrumentData {

	private String name;
	private String exchangeName;
	private Date date;
	private long producersChange;
	private long swapDealersChange;
	private long managedMoneyChange;
	private long otherReportableChange;
	
	public InstrumentData(){
	}

	public InstrumentData(String name, String exchangeName, Date date, long producersChange, long swapDealersChange, long managedMoneyChange, long otherReportableChange) {
		super();
		this.name = name;
		this.exchangeName = exchangeName;
		this.date = date;
		this.producersChange = producersChange;
		this.swapDealersChange = swapDealersChange;
		this.managedMoneyChange = managedMoneyChange;
		this.otherReportableChange = otherReportableChange;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getExchangeName() {
		return exchangeName;
	}

	public void setExchangeName(String exchangeName) {
		this.exchangeName = exchangeName;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public long getProducersChange() {
		return producersChange;
	}

	public void setProducersChange(long producersChange) {
		this.producersChange = producersChange;
	}

	public long getSwapDealersChange() {
		return swapDealersChange;
	}

	public void setSwapDealersChange(long swapDealersChange) {
		this.swapDealersChange = swapDealersChange;
	}

	public long getManagedMoneyChange() {
		return managedMoneyChange;
	}

	public void setManagedMoneyChange(long managedMoneyChange) {
		this.managedMoneyChange = managedMoneyChange;
	}

	public long getOtherReportableChange() {
		return otherReportableChange;
	}

	public void setOtherReportableChange(long otherReportableChange) {
		this.otherReportableChange = otherReportableChange;
	}

	@Override
	public String toString() {
		return "InstrumentData [name=" + name + ", exchangeName=" + exchangeName + ", date=" + date + ", producersChange=" + producersChange + ", swapDealersChange=" + swapDealersChange + ", managedMoneyChange=" + managedMoneyChange + ", otherReportableChange=" + otherReportableChange + "]";
	}

}
