package amllado.arquillian.tomee.test.controllers;

import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.ws.rs.core.Response;

import org.apache.commons.io.IOUtils;
import org.apache.cxf.jaxrs.client.WebClient;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import amllado.arquillian.tomee.test.common.DeployUtils;

@RunWith( Arquillian.class )
public class TestHelloWorldRestController
{
    @ArquillianResource
    private URL webappUrl;

    @Deployment
    public static WebArchive deploy()
    {
        return DeployUtils.deployApplication();
    }

    @Test
    @RunAsClient
    public void testHelloWorldRestControllerUser()
    {
        try
        {
            final String authorizationHeader = "Basic "
                    + new String( Base64.getEncoder().encode( "user:userpass".getBytes() ), StandardCharsets.UTF_8 );
            final WebClient webClient = WebClient.create( webappUrl.toURI() ).header( "Authorization",
                    authorizationHeader );
            final Response response = webClient.path( "rest/helloWorld/helloWorldEjbUser" ).get();

            Assert.assertNotNull( response );
            System.out.println( response.getStatus() );
            Assert.assertTrue( response.getStatus() == Response.Status.OK.getStatusCode()
                    || response.getStatus() == Response.Status.FOUND.getStatusCode() );

            System.out.println( IOUtils.toString( ( InputStream ) response.getEntity() ) );
        }
        catch ( Exception e )
        {
            System.out.println( e.getMessage() );
            throw new RuntimeException( e );
        }
    }

    @Test
    @RunAsClient
    public void testHelloWorldRestControllerAdmin()
    {
        try
        {
            final String authorizationHeader = "Basic "
                    + new String( Base64.getEncoder().encode( "admin:adminpass".getBytes() ), StandardCharsets.UTF_8 );
            final WebClient webClient = WebClient.create( webappUrl.toURI() ).header( "Authorization",
                    authorizationHeader );
            final Response response = webClient.path( "rest/helloWorld/helloWorldEjbAdmin" ).get();

            Assert.assertNotNull( response );
            System.out.println( response.getStatus() );
            Assert.assertTrue( response.getStatus() == Response.Status.OK.getStatusCode()
                    || response.getStatus() == Response.Status.FOUND.getStatusCode() );

            System.out.println( IOUtils.toString( ( InputStream ) response.getEntity() ) );
        }
        catch ( Exception e )
        {
            System.out.println( e.getMessage() );
            throw new RuntimeException( e );
        }
    }
}
