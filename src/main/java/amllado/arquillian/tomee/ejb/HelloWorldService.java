package amllado.arquillian.tomee.ejb;

import javax.ejb.Local;

/**
 * @author arturo
 *
 */
@Local
public interface HelloWorldService
{
    public String helloWorldAdmin();

    public String helloWorldUser();
}
