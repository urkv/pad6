package com.pad;

//import com.thoughtworks.xstream.XStream;
//import main.java.Employee;
//import org.glassfish.jersey.client.ClientConfig;
//import org.xmlpull.v1.XmlPullParserException;
//
//import java.net.URI;
//
//
//import javax.ws.rs.client.Client;
//import javax.ws.rs.client.ClientBuilder;
//import javax.ws.rs.client.WebTarget;
//import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.Response;
//import javax.ws.rs.core.UriBuilder;
//
//
//import java.io.IOException;
//import java.io.StringReader;
//import java.net.InetSocketAddress;
//import java.util.ArrayList;
//import java.util.Scanner;
/**
 * Created by LegalSoul on 11.12.2016.
 */
public class MainClient {

    public static void main(String[] args) {
/*
        XStream kuku = new XStream();
        WebTarget target;
        ClientConfig config = new ClientConfig();
        Client client = ClientBuilder.newClient(config);
        String requestStr = "GET /employee/?id=3";
//        String requestStr = "POST /employee/?number=15&firstname=SausageZ&lastname=Blueeeer&department=EarthQ&salary=900";
//        String requestStr = "PUT /employee/?id=3&firstname=SausageZ&lastname=Blueeeer&department=EarthQ&salary=900";
//        String requestStr = "DELETE /employee/?id=7";
//        String requestStr = "GET /employees/?offset=3&limit=6";
        if (requestStr.contains("employees"))
        {
            String queryParamStr = requestStr.substring(requestStr.indexOf("=")+1);
            target = client.target(UriBuilder
                    .fromUri("http://localhost:8080")
                    .queryParam("offset", Integer.parseInt(queryParamStr.substring(0,queryParamStr.indexOf("&"))))
                    .queryParam("limit", Integer.parseInt(queryParamStr.substring(queryParamStr.lastIndexOf("=")+1)))
                    .build());
            requestStr = requestStr.substring(requestStr.indexOf(" ")+1, requestStr.indexOf("?"));
        }
        else if (requestStr.contains("DELETE")){
            target = client.target(UriBuilder
                    .fromUri("http://localhost:8080")
                    .queryParam("id", Integer.parseInt(requestStr.substring(requestStr.indexOf("=")+1)))
                    .build());
            requestStr = "/delete/";
        }
        else if (requestStr.contains("PUT")){
            String queryParamStr = requestStr.substring(requestStr.indexOf("=")+1);
            String queryParamStr1 = queryParamStr.substring(queryParamStr.indexOf("=")+1);
            String queryParamStr2 = queryParamStr1.substring(queryParamStr1.indexOf("=")+1);
            String queryParamStr3 = queryParamStr2.substring(queryParamStr2.indexOf("=")+1);
            target = client.target(UriBuilder
                    .fromUri("http://localhost:8080")
                    .queryParam("id", Integer.parseInt(queryParamStr.substring(0,queryParamStr.indexOf("&"))))
                    .queryParam("firstname", queryParamStr1.substring(0,queryParamStr1.indexOf("&")))
                    .queryParam("lastname", queryParamStr2.substring(0,queryParamStr2.indexOf("&")))
                    .queryParam("department", queryParamStr3.substring(0,queryParamStr3.indexOf("&")))
                    .queryParam("salary", Double.parseDouble(queryParamStr.substring(queryParamStr.lastIndexOf("=")+1)))
                    .build());
            requestStr = "/put/";
        }
        else if (requestStr.contains("POST")){
            String queryParamStr = requestStr.substring(requestStr.indexOf("=")+1);
            String queryParamStr1 = queryParamStr.substring(queryParamStr.indexOf("=")+1);
            String queryParamStr2 = queryParamStr1.substring(queryParamStr1.indexOf("=")+1);
            String queryParamStr3 = queryParamStr2.substring(queryParamStr2.indexOf("=")+1);
            target = client.target(UriBuilder
                    .fromUri("http://localhost:8080")
                    .queryParam("number", Integer.parseInt(queryParamStr.substring(0,queryParamStr.indexOf("&"))))
                    .queryParam("firstname", queryParamStr1.substring(0,queryParamStr1.indexOf("&")))
                    .queryParam("lastname", queryParamStr2.substring(0,queryParamStr2.indexOf("&")))
                    .queryParam("department", queryParamStr3.substring(0,queryParamStr3.indexOf("&")))
                    .queryParam("salary", Double.parseDouble(queryParamStr.substring(queryParamStr.lastIndexOf("=")+1)))
                    .build());
            requestStr = "/post/";
        }
        else
        {
            target = client.target(UriBuilder
                    .fromUri("http://localhost:8080")
                    .queryParam("id", Integer.parseInt(requestStr.substring(requestStr.indexOf("=")+1)))
                    .build());
            requestStr = requestStr.substring(requestStr.indexOf(" ")+1, requestStr.indexOf("?"));
        }


        System.out.println(requestStr);
        System.out.println("workin!");

        Response response = target
                .path("/proxy"+requestStr)
                .request()
                .accept(MediaType.APPLICATION_XML)
                .get(Response.class);
        String strResponce = response.readEntity(String.class);
        System.out.println("done!");
        System.out.println("|||||||"+response.toString()+response.getHeaders());
        System.out.println(strResponce);
        try {
            System.out.println(((ArrayList<Employee>) kuku.fromXML(strResponce.substring(strResponce.indexOf(":") + 2)))
                    .toString().replace("=[", "\n").replace("], ", "\n").replace("}, ", "\n"));
        } catch (com.thoughtworks.xstream.io.StreamException c){
            System.out.printf("EMPTY RESPONSE");
        }
        catch (ArrayIndexOutOfBoundsException a){
            System.out.printf("EMPTY RESPONSE");
        }
*/

    }

}
