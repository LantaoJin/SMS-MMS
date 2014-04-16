package org.alanjin.smsmms.backend.bean;

public class MessageModel {
	private int id;
	private String modelName;
	private String content;
	private boolean usePoliteness;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getModelName() {
		return modelName;
	}
	
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public boolean isUsePoliteness() {
		return usePoliteness;
	}
	
	/**
	 * if usePolitness is true, head will be replace by Mr. XX
	 * @param usePoliteness
	 */
	public void setUsePoliteness(boolean usePoliteness) {
		this.usePoliteness = usePoliteness;
	}
}
