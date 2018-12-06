package com.estrategiamovilmx.sales.weespareenvios.responses;

import com.estrategiamovilmx.sales.weespareenvios.items.UserItem;

import java.io.Serializable;

/**
 * Created by administrator on 18/08/2017.
 */
public class UserResponse implements Serializable {
    private String status;
    private UserItem result = null;
    private String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UserItem getResult() {
        return result;
    }

    public void setResult(UserItem result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "UserResponse{" +
                "status='" + status + '\'' +
                ", result=" + result +
                ", message='" + message + '\'' +
                '}';
    }
}
