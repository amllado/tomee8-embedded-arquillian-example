package amllado.arquillian.tomee.ejb;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;

/**
 * @author arturo
 *
 */
@Stateless
public class HelloWorldBean implements HelloWorldService
{
    @RolesAllowed( { "adminrole" } )
    public String helloWorldAdmin()
    {
        return "EJB Admin: Hello, World!";
    }

    @RolesAllowed( { "userrole" } )
    public String helloWorldUser()
    {
        return "EJB User: Hello, World!";
    }
}
