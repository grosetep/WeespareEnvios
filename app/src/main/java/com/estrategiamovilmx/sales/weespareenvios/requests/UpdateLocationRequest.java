package com.estrategiamovilmx.sales.weespareenvios.requests;

import com.estrategiamovilmx.sales.weespareenvios.items.UserItem;
import com.estrategiamovilmx.sales.weespareenvios.model.ShippingAddress;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UpdateLocationRequest implements Serializable {
    @SerializedName("user")
    private UserItem user;
    @SerializedName("address")
    private ShippingAddress address;
    @SerializedName("operation")
    private String operation;

    public UserItem getUser() {
        return user;
    }

    public void setUser(UserItem user) {
        this.user = user;
    }

    public ShippingAddress getAddress() {
        return address;
    }

    public void setAddress(ShippingAddress address) {
        this.address = address;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}