package si.fri.rso.favourites.api.v1;

import com.kumuluz.ee.cors.annotations.CrossOrigin;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;


@SecurityScheme(name = "openid-connect", type = SecuritySchemeType.OPENIDCONNECT,
        openIdConnectUrl = "http://auth-server-url/.well-known/openid-configuration")
@ApplicationPath("v1")
@OpenAPIDefinition(info = @Info(title = "FavouritesAPI", version = "v2.0.0", contact = @Contact(email = "rentarich@gmail.com"), license = @License(name="RentarichLicense")), servers = @Server(url = "http://20.62.179.11/favourites/v1"), security
        = @SecurityRequirement(name = "openid-connect"))
@CrossOrigin(name = "my-resource")
public class FavouritesApplication extends Application {
}
