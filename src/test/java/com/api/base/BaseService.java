package com.api.base;

import com.api.filters.LoggingFilter;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseService {

    private static final String BASE_URL = "http://64.227.160.186:8080";

    private static final ThreadLocal<RequestSpecification> requestSpecification = new ThreadLocal<>();


    static {
        RestAssured.filters(new LoggingFilter());
    }

    public BaseService(){
        requestSpecification.set(RestAssured.given().baseUri(BASE_URL));
    }

    protected Response postRequest(Object payload, String endpoint){
        return requestSpecification.get().contentType(ContentType.JSON).body(payload).post(endpoint);
    }

    protected Response getRequest(String endpoint){
        return requestSpecification.get().contentType(ContentType.JSON).get(endpoint);
    }

    protected Response putRequest(Object payload, String endpoint){
        return requestSpecification.get().contentType(ContentType.JSON).body(payload).put(endpoint);
    }

    protected void setAuthToken(String token){
        requestSpecification.get().header("Authorization", "Bearer "+token);
    }

    public RequestSpecification getRequest() {
        return requestSpecification.get();
    }

}
