package si.fri.rso.favourites.api.v1.resources;


/*import com.kumuluz.ee.cors.annotations.CrossOrigin;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;*/


import si.fri.rso.favourites.models.Favourites;
import si.fri.rso.favourites.models.Item;
import si.fri.rso.favourites.services.beans.FavouritesBean;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import java.util.logging.Logger;

@ApplicationScoped
@Path("favourites")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FavouritesResource {

    private Client httpClient;
    private String baseUrl;

    @Inject
    private FavouritesBean favouritesBean;

    private Logger logger=Logger.getLogger(FavouritesResource.class.getName());

    @PostConstruct
    private void init() {
        httpClient = ClientBuilder.newClient();
    }


    /*@Operation(description = "Vrni vse sezname", summary = "Vrni seznam vseh nakupovalnih seznamov.", tags = "seznami", responses = {
            @ApiResponse(responseCode = "200",
                    description = "Vrnjen seznam nakupovalnih seznamov.",
                    content = @Content(
                            array = @ArraySchema(schema = @Schema(implementation = NakupovalniSeznam.class))),
                    headers = {@Header(name = "X-Total-Count", schema = @Schema(type = "integer"))}
            )})*/

    // add to fave

    // remove to fave

    // get all
    @GET
    @Path("{id}")
    public Response getFavourites(@PathParam("id") int personId){
        List<Favourites> faveItems = favouritesBean.getFavouritesForPerson(personId);

        return Response.ok(faveItems).header("X - total count", faveItems.size()).build();
    }



}
