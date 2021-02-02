package amllado.arquillian.tomee.controller;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import amllado.arquillian.tomee.cdi.HelloWorldCdi;
import amllado.arquillian.tomee.ejb.HelloWorldService;

@Path( "helloWorld" )
@Consumes( "text/plain" )
@Produces( "text/plain" )
public class HelloWorldController
{
    @EJB
    private HelloWorldService service;

    @Inject
    private HelloWorldCdi cdi;

    @GET
    @Path( "helloWorldEjbAdmin" )
    public String helloWordEjbAdmin()
    {
        return service.helloWorldAdmin();
    }

    @GET
    @Path( "helloWorldEjbUser" )
    public String helloWordEjbUser()
    {
        return service.helloWorldUser();
    }

    @GET
    @Path( "helloWorldCdi" )
    public String helloWordCdi()
    {
        return cdi.helloWorld();
    }
}
