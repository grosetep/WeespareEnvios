package com.estrategiamovilmx.sales.weespareenvios.requests;

import com.estrategiamovilmx.sales.weespareenvios.items.BudgetResult;
import com.estrategiamovilmx.sales.weespareenvios.items.UserItem;
import com.estrategiamovilmx.sales.weespareenvios.model.Contact;
import com.estrategiamovilmx.sales.weespareenvios.model.OrderShipping;
import com.estrategiamovilmx.sales.weespareenvios.model.ShippingAddress;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by administrator on 10/08/2017.
 */
public class ShippingBudgetRequest implements Serializable{
    @SerializedName("id_order")
    private String id_order;
    @SerializedName("order_shipping")
    private OrderShipping order_shipping;

    public String getId_order() {
        return id_order;
    }

    public void setId_order(String id_order) {
        this.id_order = id_order;
    }

    public OrderShipping getOrder_shipping() {
        return order_shipping;
    }

    public void setOrder_shipping(OrderShipping order_shipping) {
        this.order_shipping = order_shipping;
    }
}
