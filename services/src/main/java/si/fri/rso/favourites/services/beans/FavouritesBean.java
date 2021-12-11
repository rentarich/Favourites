package si.fri.rso.favourites.services.beans;

import si.fri.rso.favourites.models.Favourites;
import si.fri.rso.favourites.models.Item;
import si.fri.rso.favourites.models.Person;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.core.UriInfo;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@ApplicationScoped
public class FavouritesBean {
    private Logger log = Logger.getLogger(FavouritesBean.class.getName());
    private String idBean;

    @Inject
    private PersonBean personBean;

    @PostConstruct
    private void init(){
        idBean = UUID.randomUUID().toString();
        log.info("Init bean: " + FavouritesBean.class.getSimpleName() + " idBean: " + idBean);
    }

    @PreDestroy
    private void destroy(){
        log.info("Deinit bean: " + FavouritesBean.class.getSimpleName() + " idBean: " + idBean);
    }

    @PersistenceContext(unitName = "item-jpa")
    private EntityManager em;

    public List getFavouritesForPerson(Integer personId){
        Person person=personBean.getPerson(personId);

        TypedQuery<Favourites> query= em.createNamedQuery("Favourites.getFavouritesForPerson",Favourites.class);
        List<Favourites> personFave=query.setParameter("person",person).getResultList();

        List<Item> items=new ArrayList<>();
        personFave.forEach(f -> items.add(f.getItem()));

        return items;
    }

}
