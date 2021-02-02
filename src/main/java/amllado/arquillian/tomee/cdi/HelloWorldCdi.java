package amllado.arquillian.tomee.cdi;

import javax.enterprise.context.ApplicationScoped;

/**
 * @author arturo
 *
 */
@ApplicationScoped
public class HelloWorldCdi
{
    public String helloWorld()
    {
        return "CDI: Hello, World!";
    }
}
