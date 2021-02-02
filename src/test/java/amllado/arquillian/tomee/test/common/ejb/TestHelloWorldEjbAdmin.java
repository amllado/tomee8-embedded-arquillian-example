package amllado.arquillian.tomee.test.common.ejb;

import java.net.URL;

import javax.annotation.security.RunAs;
import javax.ejb.EJB;
import javax.ejb.EJBAccessException;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import amllado.arquillian.tomee.ejb.HelloWorldService;
import amllado.arquillian.tomee.test.common.DeployUtils;

@RunAs( "adminrole" )
@RunWith( Arquillian.class )
public class TestHelloWorldEjbAdmin
{
    @ArquillianResource
    private URL webappUrl;

    @EJB
    private HelloWorldService service;

    @Deployment
    public static WebArchive deploy()
    {
        return DeployUtils.deployApplication();
    }

    /**
     * Acceso con rol correcto especificado en el @RunAs.
     */
    @Test
    public void testHelloWorldEjbAdmin()
    {
        String message;

        try
        {
            message = service.helloWorldAdmin();
            System.out.println( message );
        }
        catch ( Exception e )
        {
            System.out.println( e.getMessage() );
            throw new RuntimeException( e );
        }

        Assert.assertTrue( message != null && message.length() > 0 );
    }

    /**
     * Acceso con rol incorrecto especificado en el @RunAs. Debe producirse un error de acceso y comprobar que así ocurre.
     */
    @Test
    public void testHelloWorldEjbUser()
    {
        String message;
        Exception ejbException = null;

        try
        {
            message = service.helloWorldUser();
            System.out.println( message ); // Nunca habría que llegar aquí.
        }
        catch ( Exception e )
        {
            ejbException = e;
        }

        Assert.assertTrue( ejbException != null && ejbException instanceof EJBAccessException );
    }
}
