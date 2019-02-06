package com.estrategiamovilmx.sales.weespareenvios.retrofit;

import com.estrategiamovilmx.sales.weespareenvios.requests.AddProductRequest;
import com.estrategiamovilmx.sales.weespareenvios.requests.CartRequest;
import com.estrategiamovilmx.sales.weespareenvios.requests.ChangeStatusOrderRequest;
import com.estrategiamovilmx.sales.weespareenvios.requests.CreateOrderRequest;
import com.estrategiamovilmx.sales.weespareenvios.requests.RegisterDeviceRequest;
import com.estrategiamovilmx.sales.weespareenvios.requests.ShippingBudgetRequest;
import com.estrategiamovilmx.sales.weespareenvios.requests.ShippingOrderRequest;
import com.estrategiamovilmx.sales.weespareenvios.requests.UpdateLocationRequest;
import com.estrategiamovilmx.sales.weespareenvios.requests.UserOperationRequest;
import com.estrategiamovilmx.sales.weespareenvios.requests.UpdateShoppingCartRequest;
import com.estrategiamovilmx.sales.weespareenvios.responses.BudgetResponse;
import com.estrategiamovilmx.sales.weespareenvios.responses.CategoryResponse;
import com.estrategiamovilmx.sales.weespareenvios.responses.ClassificationsResponse;
import com.estrategiamovilmx.sales.weespareenvios.responses.ConfigurationResponse;
import com.estrategiamovilmx.sales.weespareenvios.responses.CreateOrderResponse;
import com.estrategiamovilmx.sales.weespareenvios.responses.GenericResponse;
import com.estrategiamovilmx.sales.weespareenvios.responses.GetCartResponse;
import com.estrategiamovilmx.sales.weespareenvios.responses.GetContactsResponse;
import com.estrategiamovilmx.sales.weespareenvios.responses.GetOrdersResponse;
import com.estrategiamovilmx.sales.weespareenvios.responses.GetPaymentMethodResponse;
import com.estrategiamovilmx.sales.weespareenvios.responses.GetProductsResponse;
import com.estrategiamovilmx.sales.weespareenvios.responses.GetShippingAddressResponse;
import com.estrategiamovilmx.sales.weespareenvios.responses.GetVariantAdditionalResponse;
import com.estrategiamovilmx.sales.weespareenvios.responses.HelpTextsResponse;
import com.estrategiamovilmx.sales.weespareenvios.responses.MerchantsByServiceResponse;
import com.estrategiamovilmx.sales.weespareenvios.responses.OrderDetailResponse;
import com.estrategiamovilmx.sales.weespareenvios.responses.RatesResponse;
import com.estrategiamovilmx.sales.weespareenvios.responses.SubscriptionStatusResponse;
import com.estrategiamovilmx.sales.weespareenvios.responses.UserResponse;
import com.estrategiamovilmx.sales.weespareenvios.tools.Constants;
import com.estrategiamovilmx.sales.weespareenvios.ui.interfaces.WebServicesInterface;
import com.estrategiamovilmx.sales.weespareenvios.ui.interfaces.WebServicesMembersInterface;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by administrator on 21/07/2017.
 */
public class RestServiceWrapper {



    public static void getAllProducts(String start,String end, Callback function){
        //
        WebServicesInterface client = RetrofitClient.getClient(Constants.RETROFIT_SERVICE_REST).create(WebServicesInterface.class);
        Call<GetProductsResponse> call = client.getAllProducts(start, end);
        call.enqueue(function);
    }
    public static void getLocationsByUser(String id_user, String type_query, Callback function){
        //
        WebServicesInterface client = RetrofitClient.getClient(Constants.RETROFIT_SERVICE_REST).create(WebServicesInterface.class);
        Call<GetShippingAddressResponse> call = client.getLocationsByUser(id_user, type_query);
        call.enqueue(function);
    }
    public static void getContactsByUser(String id_user, Callback function){
        //
        WebServicesInterface client = RetrofitClient.getClient(Constants.RETROFIT_SERVICE_REST).create(WebServicesInterface.class);
        Call<GetContactsResponse> call = client.getContactsByUser(id_user);
        call.enqueue(function);
    }
    public static void getPaymentMethods( Callback function){
        //
        WebServicesInterface client = RetrofitClient.getClient(Constants.RETROFIT_SERVICE_REST).create(WebServicesInterface.class);
        Call<GetPaymentMethodResponse> call = client.getPaymentMethods();
        call.enqueue(function);
    }

    public  static void shoppingCart(AddProductRequest cart_request, Callback function){
        WebServicesInterface client = RetrofitClient.getClient(Constants.RETROFIT_SERVICE_REST).create(WebServicesInterface.class);
        Call<GenericResponse> call = client.shoppingCart(cart_request);
        call.enqueue(function);
    }
    public  static void getShoppingCart(String id_user, Callback function){
        WebServicesInterface client = RetrofitClient.getClient(Constants.RETROFIT_SERVICE_REST).create(WebServicesInterface.class);
        Call<GetCartResponse> call = client.getShoppingCart(id_user);
        call.enqueue(function);
    }
    public  static void createOrder(CreateOrderRequest order, Callback function){
        WebServicesInterface client = RetrofitClient.getClient(Constants.RETROFIT_SERVICE_REST).create(WebServicesInterface.class);
        Call<CreateOrderResponse> call = client.createOrder(order);
        call.enqueue(function);
    }
    public  static void updateShoppingCart(UpdateShoppingCartRequest update_request, Callback function){
        WebServicesInterface client = RetrofitClient.getClient(Constants.RETROFIT_SERVICE_REST).create(WebServicesInterface.class);
        Call<GenericResponse> call = client.updateShoppingCart(update_request);
        call.enqueue(function);
    }
    public  static void getOrders(String id_user,String id_profile,int start, int end,String days, Callback function){
        WebServicesInterface client = RetrofitClient.getClient(Constants.RETROFIT_SERVICE_REST).create(WebServicesInterface.class);
        Call<GetOrdersResponse> call = client.getOrders(id_user, id_profile, start, end, days);
        call.enqueue(function);
    }
    public  static void getDeliverManOrders(String id_user,String id_profile,String type_query ,int start, int end,String days,Callback function){
        WebServicesInterface client = RetrofitClient.getClient(Constants.RETROFIT_SERVICE_REST).create(WebServicesInterface.class);
        Call<GetOrdersResponse> call = client.getDeliverManOrders(id_user, id_profile, type_query, start, end, days);
        call.enqueue(function);
    }
    public  static void userOperation(UserOperationRequest request, Callback function){
        WebServicesInterface client = RetrofitClient.getClient(Constants.RETROFIT_SERVICE_REST).create(WebServicesInterface.class);
        Call<UserResponse> call = client.userOperation(request);
        call.enqueue(function);
    }
    public  static void getConfiguration(Callback function){
        WebServicesInterface client = RetrofitClient.getClient(Constants.RETROFIT_SERVICE_REST).create(WebServicesInterface.class);
        Call<ConfigurationResponse> call = client.getConfiguration();
        call.enqueue(function);
    }
    public  static void recoveryPassword(String email,String newPassword,String encryptedPassword,Callback function){
        WebServicesInterface client = RetrofitClient.getClient(Constants.RETROFIT_SERVICE_REST).create(WebServicesInterface.class);
        Call<GenericResponse> call = client.recoveryPassword(email, newPassword, encryptedPassword);
        call.enqueue(function);
    }
    public  static void ChangeStatusOrder(ChangeStatusOrderRequest request, Callback function){
        WebServicesInterface client = RetrofitClient.getClient(Constants.RETROFIT_SERVICE_REST).create(WebServicesInterface.class);
        Call<GenericResponse> call = client.ChangeStatusOrder(request);
        call.enqueue(function);
    }
    public  static void registerDevice(RegisterDeviceRequest request, Callback function){
        WebServicesInterface client = RetrofitClient.getClient(Constants.RETROFIT_SERVICE_REST).create(WebServicesInterface.class);
        Call<GenericResponse> call = client.registerDevice(request);
        call.enqueue(function);
    }
    public  static void getDetailOrder(String id_user, Callback function){
        WebServicesInterface client = RetrofitClient.getClient(Constants.RETROFIT_SERVICE_REST).create(WebServicesInterface.class);
        Call<OrderDetailResponse> call = client.getDetailOrder(id_user);
        call.enqueue(function);
    }
    public  static void budget(ShippingBudgetRequest request, Callback function){
        WebServicesInterface client = RetrofitClient.getClient(Constants.RETROFIT_SERVICE_REST).create(WebServicesInterface.class);
        Call<BudgetResponse> call = client.budget(request);
        call.enqueue(function);
    }
    public  static void createShippingOrder(ShippingOrderRequest order, Callback function){
        WebServicesInterface client = RetrofitClient.getClient(Constants.RETROFIT_SERVICE_REST).create(WebServicesInterface.class);
        Call<GenericResponse> call = client.createShippingOrder(order);
        call.enqueue(function);
    }

    public  static void shippingAddressOperation(UpdateLocationRequest request, Callback function){
        WebServicesInterface client = RetrofitClient.getClient(Constants.RETROFIT_SERVICE_REST).create(WebServicesInterface.class);
        Call<GenericResponse> call = client.shippingAddressOperation(request);
        call.enqueue(function);
    }
    public  static void getInitialRate(String id_country,Callback function){
        WebServicesInterface client = RetrofitClient.getClient(Constants.RETROFIT_SERVICE_REST).create(WebServicesInterface.class);
        Call<RatesResponse> call = client.getInitialRate(id_country);
        call.enqueue(function);
    }
    public  static void getHelpTexts(String id_country,Callback function){
        WebServicesInterface client = RetrofitClient.getClient(Constants.RETROFIT_SERVICE_REST).create(WebServicesInterface.class);
        Call<HelpTextsResponse> call = client.getHelpTexts(id_country);
        call.enqueue(function);
    }

    /*inicio cambios para domicilios*/

    public  static void getClassificationsByService(String type_service,Callback function){
        WebServicesInterface client = RetrofitClient.getClient(Constants.RETROFIT_SERVICE_REST).create(WebServicesInterface.class);
        Call<ClassificationsResponse> call = client.getClassificationsByService(type_service);
        call.enqueue(function);
    }

    public  static void getMerchantsByService(String type_service,String classificationKey,String id_country,int start, int end,Callback function){
        WebServicesInterface client = RetrofitClient.getClient(Constants.RETROFIT_SERVICE_REST).create(WebServicesInterface.class);
        Call<MerchantsByServiceResponse> call = client.getMerchantsByService(type_service, classificationKey, id_country, start, end);
        call.enqueue(function);
    }
    public static void getCategories( Callback function){
        //
        WebServicesInterface client = RetrofitClient.getClient(Constants.RETROFIT_SERVICE_REST).create(WebServicesInterface.class);
        Call<CategoryResponse> call = client.getCategories();
        call.enqueue(function);
    }

    public  static void getVariantsXProduct(String id_product, Callback function){
        WebServicesInterface client = RetrofitClient.getClient(Constants.RETROFIT_SERVICE_REST).create(WebServicesInterface.class);
        Call<GetVariantAdditionalResponse> call = client.getVariantsXProduct(id_product);
        call.enqueue(function);
    }

    /*fin cambios para domicilios*/

    /*Members rest service*/
    public  static void getSuscriptionStatus(String merchant_key,Callback function){
        WebServicesMembersInterface client = RetrofitClientMembers.getClient(Constants.MENBERS_DOMAIN_REST).create(WebServicesMembersInterface.class);
        Call<SubscriptionStatusResponse> call = client.getSuscriptionStatus(merchant_key);
        call.enqueue(function);
    }
}
