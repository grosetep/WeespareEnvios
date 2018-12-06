package com.estrategiamovilmx.sales.weespareenvios.ui.activities;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.estrategiamovilmx.sales.weespareenvios.R;
import com.estrategiamovilmx.sales.weespareenvios.items.CartProductItem;
import com.estrategiamovilmx.sales.weespareenvios.items.ConfigItem;
import com.estrategiamovilmx.sales.weespareenvios.items.UserItem;
import com.estrategiamovilmx.sales.weespareenvios.model.ApiException;
import com.estrategiamovilmx.sales.weespareenvios.model.Contact;
import com.estrategiamovilmx.sales.weespareenvios.model.PaymentMethod;
import com.estrategiamovilmx.sales.weespareenvios.model.ShippingAddress;
import com.estrategiamovilmx.sales.weespareenvios.model.ShoppingCart;
import com.estrategiamovilmx.sales.weespareenvios.requests.CreateOrderRequest;
import com.estrategiamovilmx.sales.weespareenvios.responses.CreateOrderResponse;
import com.estrategiamovilmx.sales.weespareenvios.retrofit.RestServiceWrapper;
import com.estrategiamovilmx.sales.weespareenvios.tools.Constants;
import com.estrategiamovilmx.sales.weespareenvios.tools.GeneralFunctions;
import com.estrategiamovilmx.sales.weespareenvios.tools.ShowConfirmations;
import com.estrategiamovilmx.sales.weespareenvios.tools.StringOperations;

import retrofit2.Call;
import retrofit2.Callback;

public class ReviewActivity extends AppCompatActivity {
    private static final String TAG = ReviewActivity.class.getSimpleName();
    private static final int NEW_ORDER =1;
    private ShoppingCart shopping_cart;
    private ShippingAddress shipping;
    private Contact contact;
    private PaymentMethod payment_method;
    //fields
    private TextView text_shipping_description;
    private TextView text_contact_description;
    private TextView text_payment_description;
    private TextView text_total_description;
    private TextView text_products;
    private RelativeLayout container_loading;
    private LinearLayout layout_actions;
    private LinearLayout text_head;
    private LinearLayout container_review;
    private RelativeLayout no_connection_layout;
    private CreateOrderRequest request = new CreateOrderRequest();
    //actions
    private AppCompatButton button_previous;
    private AppCompatButton button_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        Intent i = getIntent();
        shopping_cart = (ShoppingCart)i.getSerializableExtra(Constants.SELECTED_SHOPPING_CART);
        shipping = (ShippingAddress) i.getSerializableExtra(Constants.SELECTED_SHIPPING);
        contact = (Contact) i.getSerializableExtra(Constants.SELECTED_CONTACT);
        payment_method = (PaymentMethod) i.getSerializableExtra(Constants.SELECTED_PAYMENT_METHOD);

        final Toolbar toolbar_shipping = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar_shipping);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        init();
        setValues();
        assignActions();

    }
    private void initProcess(boolean flag){
        container_loading.setVisibility(flag?View.VISIBLE:View.GONE);
        layout_actions.setVisibility(flag?View.GONE:View.VISIBLE);
        container_review.setVisibility(flag?View.GONE:View.VISIBLE);
        text_head.setVisibility(flag?View.GONE:View.VISIBLE);
        no_connection_layout.setVisibility(View.GONE);
    }
    private void setValues(){

        text_shipping_description.setText(shipping.getAddressForUser());
        text_contact_description.setText(contact.getName().concat(" - ").concat(contact.getPhone()));
        text_payment_description.setText(payment_method.getMethod());
        text_total_description.setText(StringOperations.getAmountFormat(String.valueOf(shopping_cart.getTotal())));
        if (shopping_cart!=null && shopping_cart.getProducts()!=null && shopping_cart.getProducts().size()>0) {
            StringBuffer products_list = new StringBuffer();
            for (CartProductItem p: shopping_cart.getProducts()){
                String price = "";
                if (p.getOfferPrice()!=null && !p.getOfferPrice().isEmpty()){
                    price = StringOperations.getAmountFormat(p.getOfferPrice());
                }else{
                    price = StringOperations.getAmountFormat(p.getRegularPrice());
                }
                products_list.append(p.getProduct()+ " - "+ p.getUnits()+" X "+price+"\n");

            }
            text_products.setText(products_list.toString());
        }
    }
    private void init(){
        container_loading = (RelativeLayout) findViewById(R.id.container_loading);
        layout_actions = (LinearLayout) findViewById(R.id.layout_actions);
        text_head = (LinearLayout) findViewById(R.id.text_head);
        container_review = (LinearLayout) findViewById(R.id.container_review);
        no_connection_layout = (RelativeLayout) findViewById(R.id.no_connection_layout);

        text_shipping_description = (TextView) findViewById(R.id.text_shipping_description);
        text_contact_description = (TextView) findViewById(R.id.text_contact_description);
        text_payment_description = (TextView) findViewById(R.id.text_payment_description);
        text_total_description = (TextView) findViewById(R.id.text_total_description);
        text_products = (TextView) findViewById(R.id.text_products);
        button_previous = (AppCompatButton) findViewById(R.id.button_previous);
        button_next = (AppCompatButton) findViewById(R.id.button_next);
    }
    private void assignActions(){

        button_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initProcess(true);
                createOrder(v);
            }
        });
    }
    private void createOrder(final View v){
        UserItem user = GeneralFunctions.getCurrentUser(getApplicationContext());
        ConfigItem config = GeneralFunctions.getConfiguration(getApplicationContext());
        if (shopping_cart!=null && shipping!=null && contact!=null && payment_method !=null){
            request.setId_order("0");
            request.setId_user(user!=null?user.getIdUser():"0");

            request.setId_cart(shopping_cart.getId_cart());
            request.setTotal(String.valueOf(shopping_cart.getTotal()));
            request.setId_payment_method(payment_method.getIdPaymentMethod());
            request.setContact(contact);
            request.setShipping(shipping);
            request.setBusinessName(config!=null?config.getBusinessName():"");
            request.setNameUser(user!=null?user.getName():"");
            request.setAmountFormatTotal(StringOperations.getAmountFormat(String.valueOf(shopping_cart.getTotal())));
            request.setToken(GeneralFunctions.getTokenUser(getApplicationContext()));
            Log.d(TAG,"Enviar pedido:"+request.toString());
            RestServiceWrapper.createOrder(request,new Callback<CreateOrderResponse>() {
                @Override
                public void onResponse(Call<CreateOrderResponse> call, retrofit2.Response<CreateOrderResponse> response) {
                    Log.d(TAG, "Respuesta: " + response.body().getResult().toString());
                    if (response != null && response.isSuccessful()) {
                        CreateOrderResponse cart_response = response.body();
                        if (cart_response != null && cart_response.getStatus().equals(Constants.success)) {
                            if (cart_response.getResult().getStatus().equals(String.valueOf(Constants.uno))) {
                                //check response
                                Log.d(TAG,"\ncart_response:"+cart_response.getResult().toString());

                                if (validId(cart_response.getResult().getNewAddress()) &&
                                        validId(cart_response.getResult().getNewContact()) &&
                                            validId(cart_response.getResult().getNewOrder()) &&
                                                validId(cart_response.getResult().getCartCleaned())) {
                                    initProcess(false);
                                    startCongratsActivity(cart_response.getResult().getNewOrder());

                                }else{
                                    Log.d(TAG,"\ncart_response:"+cart_response.getResult().toString());
                                    initProcess(false);
                                    Snackbar.make(v,cart_response.getResult().getMessage(),Snackbar.LENGTH_LONG);
                                    //ShowConfirmations.showConfirmationMessage(cart_response.getResult().getMessage(), ReviewActivity.this);
                                    Log.d(TAG,"Error response...................:"+cart_response.getResult().getMessage());
                                    request.getContact().setIdContact(validId(cart_response.getResult().getNewContact())?cart_response.getResult().getNewContact():"0");
                                    request.getShipping().setId_location(validId(cart_response.getResult().getNewAddress())?cart_response.getResult().getNewAddress():"0");
                                    request.setId_order(validId(cart_response.getResult().getNewOrder())?cart_response.getResult().getNewOrder():"0");
                                }


                            }else{
                                initProcess(false);
                                Snackbar.make(v,cart_response.getResult().getMessage(),Snackbar.LENGTH_LONG);
                                ShowConfirmations.showConfirmationMessage(cart_response.getResult().getMessage(), ReviewActivity.this);
                            }
                        } else if (cart_response != null && cart_response.getStatus().equals(Constants.no_data)){
                            String response_error = response.body().getMessage();
                            Log.d(TAG, "Mensage:" + response_error);
                            Snackbar.make(v,response_error,Snackbar.LENGTH_LONG);
                            ShowConfirmations.showConfirmationMessage(response_error, ReviewActivity.this);
                            initProcess(false);
                        }else{
                            String response_error = response.message();
                            Log.d(TAG, "Error:" + response_error);
                            Snackbar.make(v,response_error,Snackbar.LENGTH_LONG);
                            ShowConfirmations.showConfirmationMessage(response_error, ReviewActivity.this);
                            initProcess(false);
                        }
                    }else{
                        initProcess(false);
                        ShowConfirmations.showConfirmationMessage(getString(R.string.error_invalid_login,getString(R.string.error_generic)),ReviewActivity.this);
                    }
                }
                @Override
                public void onFailure(Call<CreateOrderResponse> call, Throwable t) {
                    initProcess(false);
                    Log.d(TAG,"ERROR: " +t.getStackTrace().toString() + " --->" + t.getCause() + "  -->" + t.getMessage() + " --->");
                    ApiException apiException = new ApiException();
                    try {
                        apiException.setMessage(t.getMessage());
                        ShowConfirmations.showConfirmationMessage(apiException.getMessage(), ReviewActivity.this);
                        initProcess(false);
                    } catch (Exception ex) {
                        // do nothing
                    }
                }
            });
        }else{
            ShowConfirmations.showConfirmationMessage(getString(R.string.promt_review_error),ReviewActivity.this);
            initProcess(false);
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void startCongratsActivity(String order){
        Intent i = new Intent(this,CongratsActivity.class);
        Bundle args = new Bundle();
        args.putSerializable(Constants.SELECTED_SHIPPING,shipping);
        args.putString(Constants.ORDER_NO,order);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NO_HISTORY);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.putExtras(args);
        startActivity(i);
    }
    private boolean validId(String id){
        return id!=null && !id.isEmpty() && !id.equals(String.valueOf(Constants.cero));
    }
}
