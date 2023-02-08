package org.example;

import org.apache.http.HttpHost;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;

import java.io.IOException;

public class EsConnect {
    RestClient restClient = RestClient.builder(
            new HttpHost("localhost",9200,"http")
    ).build();
    public static void main(String[] args) throws IOException {
        EsConnect es = new EsConnect();
//        es.addData();
        es.getData();
    }
    public void addData() throws IOException {
        System.out.println("connected to Elastic Search");
        String json = "{\"id\":\"3\",\"name\":\"Justin\"}";
        Request request = new Request("POST", "/employee/_doc/1");
        request.setJsonEntity(json);
        Response response = restClient.performRequest(request);

        System.out.println(EntityUtils.toString(response.getEntity()));
    }
    public void getData() throws IOException {
        Request request = new Request("GET", "/employee/_doc/1");
        Response response = restClient.performRequest(request);

        System.out.println(EntityUtils.toString(response.getEntity()));
    }
}
