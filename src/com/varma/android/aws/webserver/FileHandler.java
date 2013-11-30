package com.varma.android.aws.webserver;

import android.content.Context;
import android.os.Environment;

import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.entity.FileEntity;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpRequestHandler;

import java.io.File;
import java.io.IOException;
import java.net.URLConnection;

public class FileHandler implements HttpRequestHandler {
    private static final String EXTERNAL_STORAGE_PATH = Environment.getExternalStorageDirectory() + "";

    private Context context = null;
    int serverPort = 0;

    public FileHandler(Context context, int serverPort){
        this.context = context;
        this.serverPort = serverPort;
    }

    @Override
    public void handle(HttpRequest request, HttpResponse response, HttpContext httpContext) throws HttpException, IOException {
        HttpEntity entity = null;
        String uriString = request.getRequestLine().getUri();

        final String filePath = "/mnt/sdcard/sample.txt";
        File file = new File(filePath);
        String contentType = URLConnection.guessContentTypeFromName(file.getAbsolutePath());
        entity = new FileEntity(file, contentType);
        /*
        entity = new EntityTemplate(new ContentProducer() {
            public void writeTo(final OutputStream outstream) throws IOException {
                OutputStreamWriter writer = new OutputStreamWriter(outstream, "UTF-8");
                //String resp = Utility.openHTMLString(context, R.raw.nodirlisting);
                int a = 0;
                FileReader reader = new FileReader(filePath);
                while ((a = reader.read()) != -1) {
                    writer.write(a);
                }
                //writer.write(resp);
                writer.flush();
            }
        });
        */
        response.setHeader("Content-Type", contentType);
        response.setEntity(entity);
    }
}
