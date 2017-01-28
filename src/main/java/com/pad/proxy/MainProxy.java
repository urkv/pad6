//package com.pad.proxy;
//
//import com.thoughtworks.xstream.XStream;
//import main.java.Employee;
//import org.glassfish.jersey.client.ClientConfig;
//
//import javax.ws.rs.*;
//import javax.ws.rs.core.*;
//
//import redis.clients.jedis.Jedis;
//import redis.clients.jedis.JedisPool;
//import redis.clients.jedis.JedisPoolConfig;
//
//import javax.ws.rs.client.Client;
//import javax.ws.rs.client.ClientBuilder;
//import javax.ws.rs.client.WebTarget;
//import javax.ws.rs.core.MediaType;
//import java.util.ArrayList;
//import java.util.Random;
//
//import static java.util.Collections.addAll;
//
///**
// * Created by LegalSoul on 30.11.2016.
// */
//@Path("/")
//public class MainProxy {
//    public static XStream kuku = new XStream();
//    JedisCache myJed;
//    // This method is called if TEXT_PLAIN is request
//    @GET
//    @Path("/employee")
//    @Produces(MediaType.APPLICATION_XML)
//    public String sayPlainTextHello(@QueryParam("id") int id) {
//        myJed = JedisCache.getInstance();
//
//        System.out.println("ID==="+id);
//
//        if (!myJed.jedis.smembers(id + "id").isEmpty()) {
//            System.out.println("YAAAAY JEDIS");
//            return myJed.jedis.smembers(id + "id").toString();
//        }
//        ClientConfig config = new ClientConfig();
//        Client client = ClientBuilder.newClient(config);
//        String response="";
//        // changing warehouse!
//        Random rnd = new Random();
//        if (rnd.nextInt(2) == 0) {
//            WebTarget target = client.target(UriBuilder.fromUri("http://localhost:8081").build());
//            response = target.path("ware/"+id).request().accept(MediaType.APPLICATION_XML).get(Response.class).readEntity(String.class);
//        }
//        else{
//            WebTarget target = client.target(UriBuilder.fromUri("http://localhost:8082").build());
//            response = target.path("ware2/"+id).request().accept(MediaType.APPLICATION_XML).get(Response.class).readEntity(String.class);
//        }
//        myJed.jedisAdd( id+"id",response);
//        return response;
//    }
//
//
//    @GET
//    @Path("/employees/")
//    @Produces(MediaType.APPLICATION_XML)
//    public String sayPlainTextHello(@QueryParam("offset") int offset, @QueryParam("limit") int limit, @Context Request request) {
//        myJed = JedisCache.getInstance();
//
//        System.out.println("OFFSET/LIMT==="+offset+":"+limit);
//
//        if (!myJed.jedis.smembers(offset + "" + limit).isEmpty()) {
//            System.out.println("YAAAAY JEDIS");
//            return myJed.jedis.smembers(offset + "" + limit).toString();
//        }
//        ClientConfig config = new ClientConfig();
//        Client client = ClientBuilder.newClient(config);
//        String response="";
//        // changing warehouse!
//        Random rnd = new Random();
//        if (rnd.nextInt(2) == 0) {
//            WebTarget target = client.target(UriBuilder.fromUri("http://localhost:8081").build());
//            response = target.path("ware/"+offset+"/"+limit).request().accept(MediaType.APPLICATION_XML).get(Response.class).readEntity(String.class);
//        }
//        else{
//            WebTarget target = client.target(UriBuilder.fromUri("http://localhost:8082").build());
//            response = target.path("ware2/"+offset+"/"+limit).request().accept(MediaType.APPLICATION_XML).get(Response.class).readEntity(String.class);
//        }
//        myJed.jedisAdd( offset +""+limit,response);
//
//        return response;
//    }
//
//
//    @GET
//    @Path("/delete")
//    @Produces(MediaType.APPLICATION_XML)
//    public String deleteEmployee(@QueryParam("id") int id) {
//
//
//        System.out.println("ID==="+id);
//
//        ClientConfig config = new ClientConfig();
//        Client client = ClientBuilder.newClient(config);
//        String response="";
//        // changing warehouse!
//        Random rnd = new Random();
//        if (rnd.nextInt(2) == 0) {
//            WebTarget target = client.target(UriBuilder.fromUri("http://localhost:8081").build());
//            response = target.path("ware/delete/"+id).request().accept(MediaType.APPLICATION_XML).get(Response.class).readEntity(String.class);
//        }
//        else{
//            WebTarget target = client.target(UriBuilder.fromUri("http://localhost:8082").build());
//            response = target.path("ware2/delete/"+id).request().accept(MediaType.APPLICATION_XML).get(Response.class).readEntity(String.class);
//        }
//
//        return response;
//    }
//
//    @GET
//    @Path("/post")
//    @Produces(MediaType.APPLICATION_XML)
//    public String addEmployee(@QueryParam("number") int number, @QueryParam("firstname") String firstname,
//                              @QueryParam("lastname") String lastname, @QueryParam("department") String department, @QueryParam("salary") double salary) {
//
//        ClientConfig config = new ClientConfig();
//        Client client = ClientBuilder.newClient(config);
//        String response="";
//        // changing warehouse!
//        Random rnd = new Random();
//        if (rnd.nextInt(2) == 0) {
//            WebTarget target = client.target(UriBuilder.fromUri("http://localhost:8081").build());
//            response = target.path("ware/post/"+number+"/"+firstname+"/"+lastname+"/"+department+"/"+salary)
//                    .request().accept(MediaType.APPLICATION_XML).get(Response.class).readEntity(String.class);
//        }
//        else{
//            WebTarget target = client.target(UriBuilder.fromUri("http://localhost:8082").build());
//            response = target.path("ware2/post/"+number+"/"+firstname+"/"+lastname+"/"+department+"/"+salary)
//                    .request().accept(MediaType.APPLICATION_XML).get(Response.class).readEntity(String.class);
//        }
//
//        return response;
//    }
//
//    @GET
//    @Path("/put")
//    @Produces(MediaType.APPLICATION_XML)
//    public String modifdEmployee(@QueryParam("id") int id, @QueryParam("firstname") String firstname,
//                              @QueryParam("lastname") String lastname, @QueryParam("department") String department, @QueryParam("salary") double salary) {
//
//        ClientConfig config = new ClientConfig();
//        Client client = ClientBuilder.newClient(config);
//        String response="";
//        // changing warehouse!
//        Random rnd = new Random();
//        if (rnd.nextInt(2) == 0) {
//            WebTarget target = client.target(UriBuilder.fromUri("http://localhost:8081").build());
//            response = target.path("ware/put/"+id+"/"+firstname+"/"+lastname+"/"+department+"/"+salary)
//                    .request().accept(MediaType.APPLICATION_XML).get(Response.class).readEntity(String.class);
//        }
//        else{
//            WebTarget target = client.target(UriBuilder.fromUri("http://localhost:8082").build());
//            response = target.path("ware2/put/"+id+"/"+firstname+"/"+lastname+"/"+department+"/"+salary)
//                    .request().accept(MediaType.APPLICATION_XML).get(Response.class).readEntity(String.class);
//        }
//
//        return response;
//    }
//
//}
