package com.mygitgor.restaurant.service.impl;

import com.mygitgor.restaurant.domain.Order;
import com.mygitgor.restaurant.response.PaymentResponse;
import com.mygitgor.restaurant.service.PaymentService;
import com.stripe.Stripe;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Value("${stripe.api.key}")
    private String stripeSecretKy;

    @Value("${idram.api.key}")
    private String idramApiKey;
    @Value("${idram.api.key}")
    private String idramApiUrl;

    @Value("${easypay.api.url}")
    private String easypayApiUrl;
    @Value("${easypay.api.key}")
    private String easypayApiKey;

    @Override
    public PaymentResponse createStripePaymentLink(Order order) throws Exception {
        Stripe.apiKey = stripeSecretKy;
        SessionCreateParams params = SessionCreateParams.builder().addPaymentMethodType(
                SessionCreateParams
                        .PaymentMethodType.CARD)
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl("http://localhost:3000/payment/success/"+order.getId())
                .setCancelUrl("http://localhost:3000/payment/fail")
                .addLineItem(SessionCreateParams.LineItem.builder()
                        .setQuantity(1L).setPriceData(SessionCreateParams.LineItem.PriceData.builder()
                                .setCurrency("usd")
                                .setUnitAmount((long)order.getTotalPrice()*100)
                                .setProductData(SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                        .setName("taco food")
                                        .build())
                                .build()
                        ).build()
                ).build();

        Session session = Session.create(params);

        PaymentResponse response = new PaymentResponse();
        response.setPayment_url(session.getUrl());

        return response;
    }

    @Override
    public PaymentResponse createIdramPaymentLink(Order order) throws IOException, JSONException {
        JSONObject requestData = new JSONObject();
        requestData.put("merchant_id", "YOUR_MERCHANT_ID");
        requestData.put("order_id", order.getId());
        requestData.put("amount", order.getTotalAmount());
        requestData.put("currency", "AMD");
        requestData.put("return_url", "http://localhost:3000/payment/success/" + order.getId());
        requestData.put("cancel_url", "http://localhost:3000/payment/fail");

        HttpURLConnection connection = createHttpConnection(idramApiUrl, idramApiKey);

        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = requestData.toString().getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        return parsePaymentResponse(connection);
    }


    @Override
    public PaymentResponse createEasyPayPaymentLink(Order order) throws IOException, JSONException {
        JSONObject requestData = new JSONObject();
        requestData.put("amount", order.getTotalAmount());
        requestData.put("currency", "AMD");
        requestData.put("orderId", order.getId());
        requestData.put("successUrl", "http://localhost:3000/payment/success/" + order.getId());
        requestData.put("cancelUrl", "http://localhost:3000/payment/fail");

        HttpURLConnection connection = createHttpConnection(easypayApiUrl, easypayApiKey);

        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = requestData.toString().getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        return parsePaymentResponse(connection);
    }



    private HttpURLConnection createHttpConnection(String url, String apiKey) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Authorization", "Bearer " + apiKey);
        connection.setDoOutput(true);
        return connection;
    }

    private PaymentResponse parsePaymentResponse(HttpURLConnection connection) throws IOException, JSONException {
        int responseCode = connection.getResponseCode();
        if (responseCode != HttpURLConnection.HTTP_OK) {
            throw new IOException("Error creating payment link, code " + responseCode);
        }

        StringBuilder response = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
        }

        JSONObject jsonResponse = new JSONObject(response.toString());
        String paymentUrl = jsonResponse.getString("payment_url");

        PaymentResponse paymentResponse = new PaymentResponse();
        paymentResponse.setPayment_url(paymentUrl);

        return paymentResponse;
    }


}
