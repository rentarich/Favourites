package si.fri.rso.favourites.api.v1;

/*import com.kumuluz.ee.cors.annotations.CrossOrigin;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;*/

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;


//@OpenAPIDefinition(info = @Info(title = "Rest API", version = "v1", contact = @Contact(), license = @License(), description = "Java API za upravljanje nakupovalnih seznamov."), servers = @Server(url ="http://localhost:4444/v1"))
@ApplicationPath("v1")
//@CrossOrigin(name = "my-resource")
public class FavouritesApplication extends Application {
}
