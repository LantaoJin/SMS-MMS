package org.alanjin.smsmms.backend.bean;

public class MessageModel {
    private int id;
    private String modelName;
    private String content;
    private boolean usePoliteness;
    private String description;

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
     * 
     * @param usePoliteness
     */
    public void setUsePoliteness(boolean usePoliteness) {
        this.usePoliteness = usePoliteness;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + this.id;
        hash = 23 * hash + (this.modelName != null ? this.modelName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MessageModel other = (MessageModel) obj;
        if (this.id != other.id) {
            return false;
        }
        if ((this.modelName == null) ? (other.modelName != null) : !this.modelName.equals(other.modelName)) {
            return false;
        }
        return true;
    }
}
