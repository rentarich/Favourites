package si.fri.rso.favourites.api.v1.resources;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import si.fri.rso.favourites.models.Favourites;
import si.fri.rso.favourites.models.Item;
import si.fri.rso.favourites.models.Person;
import si.fri.rso.favourites.services.beans.FavouritesBean;
import si.fri.rso.favourites.services.beans.ItemBean;
import si.fri.rso.favourites.services.beans.PersonBean;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@ApplicationScoped
@Path("favourites")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FavouritesResource {

    private Client httpClient;
    private String baseUrl;

    @Inject
    private FavouritesBean favouritesBean;

    @Inject
    private PersonBean personBean;

    @Inject
    private ItemBean itemBean;

    private Logger logger=Logger.getLogger(FavouritesResource.class.getName());

    @PostConstruct
    private void init() {
        httpClient = ClientBuilder.newClient();
    }

    @GET
    @Operation(description = "Get list of all favourite items for person {personId}.", summary = "Get persons favourite items",
            tags = "favourites",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Got all favourite items for person {personId}.", content = @Content(
                            array = @ArraySchema(schema = @Schema(implementation = Item.class))),
                            headers = {@Header(name = "X-Total-Count", schema = @Schema(type = "integer"))}
                    )})
    @Path("{personId}")
    public Response getFavourites(@PathParam("personId") int personId){
        //Person person=favouritesBean.getFavourite(id).getPerson();
        Person person=personBean.getPerson(personId);
        List<Item> faveItems = favouritesBean.getFavouritesForPerson(person);

        return Response.ok(faveItems).header("X - total count", faveItems.size()).build();
    }

    @POST
    @Operation(description = "Add item {itemId} to favourites for person {personId}.", summary = "Add to favourites", tags = "favourites", responses = {
            @ApiResponse(responseCode = "201",
                    description = "Added to favourites.",
                    content = @Content(
                            schema = @Schema(implementation = Favourites.class))
            ),
            @ApiResponse(responseCode = "400",
                    description = "Bad request."
            )})
    @Path("/{itemId}/{personId}")
    public Response addToFavourites(@PathParam("itemId") Integer itemId, @PathParam("personId") Integer personId){
        if ((itemId == null || personId == null)) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        else {
            Person person=personBean.getPerson(personId);
            Item item = itemBean.getItem(itemId);
            List<Integer> favouritesList = favouritesBean.getFavouritesForPerson(person).stream().map(i -> i.getId()).collect(Collectors.toList());
            if(favouritesList.contains(itemId)) {
                return Response.status(Response.Status.BAD_REQUEST).build();
            } else {
                Favourites favourites = favouritesBean.addToFavourites(itemId, personId);
                return Response.status(Response.Status.CREATED).entity(favourites).build();
            }
        }
    }


    @DELETE
    @Operation(description = "Remove item {itemId} from favourites for person {personId}.", summary = "Remove from favourites",
            tags = "favourites",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Removed from favourites.", content = @Content(schema = @Schema(implementation =
                            Favourites.class))),
                    @ApiResponse(responseCode = "400", description = "Bad request.")
            })
    @Path("/{itemId}/{personId}")
    public Response removeFromFavourites(@PathParam("itemId") Integer itemId, @PathParam("personId") Integer personId){
        if ((itemId == null || personId == null)) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        else {

            try {
                Favourites favourites=favouritesBean.deleteFavourite(personId,itemId);

                return  Response.status(Response.Status.NO_CONTENT).entity(favourites).build();
            }
            catch (Exception e){
                return Response.status(Response.Status.BAD_REQUEST).build();
            }
        }
    }


}
