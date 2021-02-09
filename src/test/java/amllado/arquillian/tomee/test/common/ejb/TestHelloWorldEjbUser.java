package amllado.arquillian.tomee.test.common.ejb;

import static org.junit.Assert.assertTrue;

import java.net.URL;

import javax.annotation.security.RunAs;
import javax.ejb.EJB;
import javax.ejb.EJBAccessException;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import amllado.arquillian.tomee.ejb.HelloWorldService;
import amllado.arquillian.tomee.test.common.DeployUtils;

@RunAs( "userrole" )
@RunWith( Arquillian.class )
public class TestHelloWorldEjbUser
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
    public void testHelloWorldEjbUser()
    {
        String message;

        try
        {
            message = service.helloWorldUser();
            System.out.println( message );
        }
        catch ( Exception e )
        {
            System.out.println( e.getMessage() );
            throw new RuntimeException( e );
        }

        assertTrue( message != null && message.length() > 0 );
    }

    /**
     * Acceso con rol incorrecto especificado en el @RunAs. Debe producirse un error de acceso y comprobar que así ocurre.
     */
    @Test( expected = EJBAccessException.class )
    public void helloWorldEjbAdminShouldEJBAccessException()
    {
        service.helloWorldAdmin();
    }
}
