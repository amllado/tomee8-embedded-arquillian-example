package amllado.arquillian.tomee.test.common;

import java.io.File;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.jboss.shrinkwrap.resolver.api.maven.MavenResolverSystem;

/**
 * @author amllado
 *
 */
public class DeployUtils
{
    public static WebArchive deployApplication()
    {
        try
        {
            WebArchive archive = null;
            MavenResolverSystem resolver = Maven.resolver();

            File[] files = resolver.loadPomFromFile( "pom.xml" ).importCompileAndRuntimeDependencies().resolve()
                    .withTransitivity().asFile();

            archive = ShrinkWrap.create( WebArchive.class, "tomee8-embedded-arquillian-example.war" )
                    .addAsLibraries( files ).addAsWebInfResource( new File( "src/main/webapp/WEB-INF/web.xml" ) )
                    .addAsWebInfResource( new File( "src/main/webapp/WEB-INF/beans.xml" ) )
                    .addPackages( true, "amllado.arquillian.tomee" );

            return archive;
        }
        catch ( Exception e )
        {
            e.printStackTrace();
            throw new RuntimeException( e );
        }
    }
}
