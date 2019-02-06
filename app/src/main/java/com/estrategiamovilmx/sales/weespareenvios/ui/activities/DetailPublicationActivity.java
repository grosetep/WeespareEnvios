package com.estrategiamovilmx.sales.weespareenvios.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.estrategiamovilmx.sales.weespareenvios.R;
import com.estrategiamovilmx.sales.weespareenvios.items.UserItem;
import com.estrategiamovilmx.sales.weespareenvios.model.ProductModel;
import com.estrategiamovilmx.sales.weespareenvios.tools.Constants;
import com.estrategiamovilmx.sales.weespareenvios.tools.GeneralFunctions;
import com.estrategiamovilmx.sales.weespareenvios.ui.fragments.DetailProductsFragment;


public class DetailPublicationActivity extends AppCompatActivity {
    public static String EXTRA_PRODUCT = "id_product";
    public static String EXTRA_IMAGEPATH="imagePath";
    public static String EXTRA_IMAGENAME="imageName";
    public static String EXTRA_FLOW="flow";
    private FloatingActionButton button_add_to_cart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_publication);

        Intent intent = getIntent();
        final String idProduct = intent.getStringExtra(EXTRA_PRODUCT);
        final String imagePath = intent.getStringExtra(EXTRA_IMAGEPATH);
        final String imageName = intent.getStringExtra(EXTRA_IMAGENAME);
        final String flow = intent.getStringExtra(EXTRA_FLOW);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarDetail);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        button_add_to_cart = (FloatingActionButton) findViewById(R.id.button_add_to_cart);
        button_add_to_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserItem user = GeneralFunctions.getCurrentUser(getApplicationContext());
                if (user!=null) {
                    /*if (flow.equals(DrinksFragment.FLOW_DRINKS)) {
                        addToCart();
                    }else{*/
                        addToCartActivity();
                    //}
                }else{
                    finish();
                    Intent i = new Intent(getBaseContext(),LoginActivity.class);
                    i.putExtra(Constants.flow,MainActivity.flow_no_registered);
                    startActivity(i);
                }
            }
        });

        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle("");

        ProductModel product = new ProductModel();
        product.setIdProduct(idProduct);
        product.setPath(imagePath);
        product.setImage(imageName);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.containerDetailPublication, DetailProductsFragment.createInstance(product, flow), "DetailProductsFragment")
                    .commit();

        }

    }
    private void addToCart(){
        DetailProductsFragment fragment = (DetailProductsFragment)getSupportFragmentManager().findFragmentByTag("DetailProductsFragment");
        if (fragment!=null) fragment.addToCart();
    }
    private void addToCartActivity(){
        DetailProductsFragment fragment = (DetailProductsFragment)getSupportFragmentManager().findFragmentByTag("DetailProductsFragment");
        if (fragment!=null) fragment.startAddToCartActivity();
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
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        DetailProductsFragment fragment = (DetailProductsFragment) getSupportFragmentManager().
                findFragmentByTag("DetailProductsFragment");
        fragment.loadInformation();

    }
}
