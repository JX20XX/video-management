package org.example.constant;

public enum VideoStatus {
    PENDING(1, "pending"),
    PROCESSING(2, "processing"),
    COMPLETED(3, "completed"),
    FAILED(4, "failed");
    private int statusCode;
    private String statusDesc;

    VideoStatus(int statusCOde,String statusDesc){
        this.statusCode = statusCOde;
        this.statusDesc = statusDesc;
    }

    public static VideoStatus getVideoStatus(int statusCode) {
        for (VideoStatus value : VideoStatus.values()) {
            if (value.statusCode == statusCode) {
                return value;
            }
        }
        return null;
    }
    
    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }
}
