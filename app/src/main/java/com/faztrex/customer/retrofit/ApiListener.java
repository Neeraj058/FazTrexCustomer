package com.faztrex.customer.retrofit;

public interface ApiListener {

    void onApiSuccess(String endPointName, Object responseBody);

    void onApiError(String endPointName, String errorMessage);

    void onApiFailure(String endPointName, String failureMessage);
}
