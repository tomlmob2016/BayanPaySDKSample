package com.example.bayansdksample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Date;

import dp.toml.directpay.PaymentActivity;
import models.BillingDetails;
import models.DPCustomStyles;
import models.DccDetails;
import models.MerchantFieldDetails;
import models.OtherDetails;
import models.PaymentDetails;
import models.ShippingDetails;
import models.TransactionDetails;
import models.TransactionResponse;
import utils.DPSettings;
import utils.PaymentConstants;

public class MainActivity extends AppCompatActivity {

    Button btnAggregatorHosted, btnMerchantHosted;

    String strTransactionURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnAggregatorHosted = findViewById(R.id.btnPayAggregator);
        btnMerchantHosted = findViewById(R.id.btnPayMerchant);

        btnAggregatorHosted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                testAggregatorHosted();

            }
        });


        btnMerchantHosted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                testMerchantHosted();


            }
        });


    }

    private void testAggregatorHosted() {

        DPSettings dpSettings = new DPSettings();
       /* dpSettings.setMerchantID("201808291000003");
        dpSettings.setCollaboratorID(PaymentConstants.COLLABORATORS.BAYANPAY);
        dpSettings.setIntegrationType(PaymentConstants.IntegrationType.AggregatorHosted);
        dpSettings.setMerchantKey("pCiV40UPZTLZSEKf07qt5RtiP+BQmF1wmAumYY2eto8=");
        dpSettings.setPlatformType(PaymentConstants.PlatformType.Java);*/

        dpSettings.setMerchantID("201810031000002");
        dpSettings.setCollaboratorID(PaymentConstants.COLLABORATORS.BAYANPAY);
        dpSettings.setIntegrationType(PaymentConstants.IntegrationType.AggregatorHosted);
        dpSettings.setMerchantKey("9wOysSHmjOzn2B3dnsDcietAQurH7eRusY3wdbe6YWY=");
        dpSettings.setPlatformType(PaymentConstants.PlatformType.Java);

        /*********** Transaction Details *******************/
        TransactionDetails transactionDetails = new TransactionDetails();
        transactionDetails.setOrderNo("" + new Date().getTime());
        transactionDetails.setSuccessURL("https://test.direcpay.com/direcpay/secure/BayanPaybitMapEncDecRevTxnResponse.jsp");
        transactionDetails.setFailureURL("https://test.direcpay.com/direcpay/secure/BayanPaybitMapEncDecRevTxnFailResponse.jsp");
        transactionDetails.setChannelMode(PaymentConstants.ChannelMode.INTERNET);
        transactionDetails.setTransactionType(PaymentConstants.TransactionType.SALE);
        transactionDetails.setCurrencyCode(PaymentConstants.CurrencyCode.SAR);
        transactionDetails.setPayMode(PaymentConstants.PaymentMode.CREDIT_CARD);
        transactionDetails.setAmount(10.0);


        Intent intent = new Intent(this, PaymentActivity.class);

        intent.putExtra(PaymentConstants.MERCHANT_ID, dpSettings.getMerchantID());
        intent.putExtra(PaymentConstants.KEY, dpSettings.getMerchantKey());
        intent.putExtra(PaymentConstants.INTEGRATION_TYPE, dpSettings.getIntegrationType());
        intent.putExtra(PaymentConstants.COLLABORATOR_ID, dpSettings.getCollaboratorID());

        if (dpSettings.getCollaboratorID().equalsIgnoreCase(PaymentConstants.COLLABORATORS.BAYANPAY.value)) {
            strTransactionURL = "https://staging.bayanpay.sa/direcpay/secure/PaymentTxnServlet";
        } else {
            strTransactionURL = "https://test.direcpay.com/direcpay/secure/PaymentTxnServlet";
        }

        intent.putExtra(PaymentConstants.TRANSACTION_URL, strTransactionURL);
        intent.putExtra(PaymentConstants.PLATFORM_TYPE, dpSettings.getPlatformType());
        intent.putExtra(PaymentConstants.TRANSACTION_BLOCK, transactionDetails);
        intent.putExtra(PaymentConstants.LANGUAGE, PaymentConstants.AppLanguage.English);

        startActivityForResult(intent, 1);

    }

    private void testMerchantHosted() {
        DPSettings dpSettings = new DPSettings();
        dpSettings.setMerchantID("202001071000001");
        dpSettings.setCollaboratorID(PaymentConstants.COLLABORATORS.BAYANPAY);
        dpSettings.setIntegrationType(PaymentConstants.IntegrationType.MerchantHosted);
        dpSettings.setMerchantKey("xwuIYt0MqKkRylO0FN7SBSNqQBkQftMZH4WZvX8OTEg=");
        dpSettings.setPlatformType(PaymentConstants.PlatformType.Java);

        /*********** Transaction Details *******************/
        TransactionDetails transactionDetails = new TransactionDetails();
        transactionDetails.setOrderNo("" + new Date().getTime());
        transactionDetails.setSuccessURL("https://test.direcpay.com/direcpay/secure/BayanPaybitMapEncDecRevTxnResponse.jsp");
        transactionDetails.setFailureURL("https://test.direcpay.com/direcpay/secure/BayanPaybitMapEncDecRevTxnFailResponse.jsp");
        transactionDetails.setChannelMode(PaymentConstants.ChannelMode.INTERNET);
        transactionDetails.setTransactionType(PaymentConstants.TransactionType.SALE);
        transactionDetails.setCurrencyCode(PaymentConstants.CurrencyCode.SAR);
        transactionDetails.setPayMode(PaymentConstants.PaymentMode.CREDIT_CARD);
        transactionDetails.setAmount(10.0);

        /*********** Billing Details *******************/
        BillingDetails billingDetails = new BillingDetails();
        billingDetails.setFirstName("John");
        billingDetails.setLastName("smith");
        billingDetails.setEmail("ap030719@gmail.com");
        billingDetails.setStreet1("BTest");
        billingDetails.setStreet2("BTest");
        billingDetails.setPincode("123456");
        billingDetails.setCity("Mumbai");
        billingDetails.setState("Maharashtra");
        billingDetails.setCountry("IN");
        billingDetails.setMobile("1234567890");
        // Note : Landline number should be in below format only.
        billingDetails.setLandline("91|0251|1234567");

        /*********** Shipping Details *******************/
        ShippingDetails shippingDetails = new ShippingDetails();
        shippingDetails.setFirstName("John");
        shippingDetails.setLastName("smith");
        shippingDetails.setEmail("ap030719@gmail.com");
        shippingDetails.setStreet1("STest");
        shippingDetails.setStreet2("STest");
        shippingDetails.setPincode("123456");
        shippingDetails.setCity("Mumbai");
        shippingDetails.setState("Maharashtra");
        shippingDetails.setCountry("IN");
        shippingDetails.setMobile("1234567890");
        // Note : Landline number should be in below format only.
        shippingDetails.setLandline("91|0251|1234567");


        /*********** User Details *******************/
        MerchantFieldDetails merchantFieldDetails = new MerchantFieldDetails();
        merchantFieldDetails.setUDF1("");
        merchantFieldDetails.setUDF2("");
        merchantFieldDetails.setUDF3("");
        merchantFieldDetails.setUDF4("");
        merchantFieldDetails.setUDF5("");
        merchantFieldDetails.setUDF10("");

        /*********** Other Details Details *******************/
        OtherDetails otherDetails = new OtherDetails();
        otherDetails.setCustomerID("direcpay555");
        otherDetails.setTransactionSource("");
        otherDetails.setProductInfo("");
        otherDetails.setIsUserLoggedIn("");
        otherDetails.setItemTotal("");
        otherDetails.setItemCategory("");
        otherDetails.setIgnoreValidationResult("");

        /*********** DCC Details *******************/
        DccDetails dccDetails = new DccDetails();
        dccDetails.setForeignAmount("");
        dccDetails.setForeignCurrency("");
        dccDetails.setReferenceNumber("");

        /****** Custome Style*******/
        DPCustomStyles customStyles = new DPCustomStyles();
        if (dpSettings.getCollaboratorID().equalsIgnoreCase(PaymentConstants.COLLABORATORS.BAYANPAY.value)) {
            customStyles.setMerchantLogo(R.drawable.ic_bayan_logo);
        } else {
            customStyles.setMerchantLogo(R.drawable.dp_aap_logo);
        }
        customStyles.setButtonBgColor("#000000");
        customStyles.setButtonTextColor("#ffffff");
        customStyles.setTitleBgColor("#da3939");
        customStyles.setTitleTextColor("#ffffff");


        Intent intent = new Intent(this, PaymentActivity.class);

        intent.putExtra(PaymentConstants.MERCHANT_ID, dpSettings.getMerchantID());
        intent.putExtra(PaymentConstants.KEY, dpSettings.getMerchantKey());
        intent.putExtra(PaymentConstants.INTEGRATION_TYPE, dpSettings.getIntegrationType());
        intent.putExtra(PaymentConstants.COLLABORATOR_ID, dpSettings.getCollaboratorID());

        if (dpSettings.getCollaboratorID().equalsIgnoreCase(PaymentConstants.COLLABORATORS.BAYANPAY.value)) {
            strTransactionURL = "https://staging.bayanpay.sa/direcpay/secure/PaymentTxnServlet";
        } else {
            strTransactionURL = "https://test.direcpay.com/direcpay/secure/PaymentTxnServlet";
        }


        intent.putExtra(PaymentConstants.TRANSACTION_URL, strTransactionURL);
        intent.putExtra(PaymentConstants.PLATFORM_TYPE, dpSettings.getPlatformType());
        intent.putExtra(PaymentConstants.TRANSACTION_BLOCK, transactionDetails);
        intent.putExtra(PaymentConstants.BILLING_BLOCK, billingDetails);
        intent.putExtra(PaymentConstants.SHIPPING_BLOCK, shippingDetails);
        intent.putExtra(PaymentConstants.MERCHANT_FIELD_BLOCK, merchantFieldDetails);
        intent.putExtra(PaymentConstants.OTHER_DETAILS_BLOCK, otherDetails);
        intent.putExtra(PaymentConstants.DCC_DETAILS_BLOCK, dccDetails);
        intent.putExtra(PaymentConstants.CUSTOME_STYLE, customStyles);
        intent.putExtra(PaymentConstants.LANGUAGE, PaymentConstants.AppLanguage.English);
        // Note : Make sure your font file should be present in given path.
        intent.putExtra(PaymentConstants.CUSTOM_FONT, "fonts/BarlowSemiCondensed-Medium.ttf");


        startActivityForResult(intent, 1);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            Intent intent = new Intent(this, TransactionResponseActivity.class);
            // Note : You can get complete response in below transactionResponse
            TransactionResponse transactionResponse = data.getParcelableExtra(PaymentConstants.TRANSACTION_RESPONSE);
            intent.putExtra("transactionResponse", transactionResponse);
            startActivity(intent);
            finish();
        }
    }
}
