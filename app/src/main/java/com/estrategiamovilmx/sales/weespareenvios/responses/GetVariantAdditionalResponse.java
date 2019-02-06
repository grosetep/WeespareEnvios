package com.estrategiamovilmx.sales.weespareenvios.responses;


import com.estrategiamovilmx.sales.weespareenvios.items.GetVariantAdditionalResult;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by administrator on 09/08/2017.
 */
public class GetVariantAdditionalResponse implements Serializable {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("result")
    @Expose
    private GetVariantAdditionalResult result;
    @SerializedName("message")
    @Expose
    private String message;
    private final static long serialVersionUID = 4965525119717718735L;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public GetVariantAdditionalResult getResult() {
        return result;
    }

    public void setResult(GetVariantAdditionalResult result) {
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
        return new String().concat("status:").concat(status).concat("result:").concat(result.toString()).concat("message:").concat(message).toString();
    }
}



