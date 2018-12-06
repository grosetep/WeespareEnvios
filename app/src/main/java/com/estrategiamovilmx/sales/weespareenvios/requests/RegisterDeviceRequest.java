package com.estrategiamovilmx.sales.weespareenvios.requests;

import com.estrategiamovilmx.sales.weespareenvios.items.UserItem;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by administrator on 25/08/2017.
 */
public class RegisterDeviceRequest implements Serializable {
    @SerializedName("user")
    private UserItem user;
    @SerializedName("alreadyRegistered")
    private String alreadyRegistered;

    public UserItem getUser() {
        return user;
    }

    public void setUser(UserItem user) {
        this.user = user;
    }

    public String getAlreadyRegistered() {
        return alreadyRegistered;
    }

    public void setAlreadyRegistered(String alreadyRegistered) {
        this.alreadyRegistered = alreadyRegistered;
    }
}
