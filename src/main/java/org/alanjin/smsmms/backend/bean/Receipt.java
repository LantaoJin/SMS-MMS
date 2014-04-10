package org.alanjin.smsmms.backend.bean;

import java.math.BigDecimal;
import java.sql.Date;

public class Receipt {
	private String receiptId;
	private String memId;
	private BigDecimal money;
	private String attnName;
	private Date createDate;
	private String description;
	
	public Receipt(String receiptId, BigDecimal money, Date createDate) {
		super();
		this.receiptId = receiptId;
		this.money = money;
		this.createDate = createDate;
	}

	public String getReceiptId() {
		return receiptId;
	}

	public void setReceiptId(String receiptId) {
		this.receiptId = receiptId;
	}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public String getAttnName() {
		return attnName;
	}

	public void setAttnName(String attnName) {
		this.attnName = attnName;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
