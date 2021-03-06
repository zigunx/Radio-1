package com.hoffenkloffen.radio.utils;

import com.google.inject.Inject;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class Downloader implements IDownloader {

    @Inject
    private ISimpleLogFacade log;

    public InputStream getInputStream(final String uri) {
        try {
            URL url = new URL(uri);

            return url.openStream();
        } catch (IOException e) {
            log.e("Get InputStream failed.", e);
        }

        return null;
    }

    public String getResponse(String uri) {
        HttpClient httpClient = new DefaultHttpClient();

        try {
            HttpGet httpGet = new HttpGet(uri);

            ResponseHandler<String> responseHandler = new BasicResponseHandler();

            return httpClient.execute(httpGet, responseHandler);
        } catch (IOException e) {
            log.e("Get response failed.", e);
        } finally {
            httpClient.getConnectionManager().shutdown();
        }

        return null;
    }
}
