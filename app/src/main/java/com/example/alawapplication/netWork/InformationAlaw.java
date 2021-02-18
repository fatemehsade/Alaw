package com.example.alawapplication.netWork;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class InformationAlaw {

    public static final String TAG = "alaa";

    public byte[] getUrlBytes(String urlSpec) throws IOException{

        URL url=new URL(urlSpec);
        HttpURLConnection connection= (HttpURLConnection) url.openConnection();
        try {

            InputStream inputStream=connection.getInputStream();

            if (connection.getResponseCode()!=HttpURLConnection.HTTP_OK){
                throw new IOException(connection.getResponseMessage()+" :with"+urlSpec);
            }
            ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
            byte[] buffer=new byte[1024];
            int byteRead=0;
            while ((byteRead=inputStream.read(buffer))!=-1){
                outputStream.write(buffer,0,byteRead);

            }
            byte [] result=outputStream.toByteArray();
            inputStream.close();
            outputStream.close();
            return result;

        }
        finally {
            connection.disconnect();
        }
    }

    public String getUrlString(String urlSpec) throws IOException {
        return new String(getUrlBytes(urlSpec));
    }
}
