package si.fri.rso.favourites.services.beans;

import com.kumuluz.ee.logs.LogManager;
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
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class FavouritesBean {
    private com.kumuluz.ee.logs.Logger logger = LogManager.getLogger(PersonBean.class.getName());

    private String idBean;

    @Inject
    private PersonBean personBean;

    @Inject
    private ItemBean itemBean;

    @PostConstruct
    private void init(){
        idBean = UUID.randomUUID().toString();
        logger.info("Init bean: " + FavouritesBean.class.getSimpleName() + " idBean: " + idBean);
    }

    @PreDestroy
    private void destroy(){
        logger.info("Deinit bean: " + FavouritesBean.class.getSimpleName() + " idBean: " + idBean);
    }

    @PersistenceContext(unitName = "item-jpa")
    private EntityManager em;

    public List<Item> getFavouritesForPerson(Person person){
        TypedQuery<Favourites> query= em.createNamedQuery("Favourites.getFavouritesForPerson",Favourites.class);
        List<Favourites> personFave=query.setParameter("person",person).getResultList();

        List<Item> items=new ArrayList<>();
        personFave.forEach(f -> items.add(f.getItem()));

        return items;
    }

    public Favourites getFavourite(Integer id){
        Favourites favourite = em.find(Favourites.class, id);
        return favourite;
    }

    public Favourites getFavourite(Integer personId,Integer itemId){
        TypedQuery<Favourites> query= em.createNamedQuery("Favourites.getFavourite",Favourites.class);
        Person person=personBean.getPerson(personId);
        Item item=itemBean.getItem(itemId);
        query.setParameter("person",person);
        query.setParameter("item",item);
        Favourites favourites=query.getSingleResult();

        return favourites;
    }

    public List getFavourites(){

        return em.createNamedQuery("Favourites.getAll").getResultList();
    }

    @Transactional
    public Favourites addToFavourites(Integer itemId, Integer personId){
        Person person = personBean.getPerson(personId);
        Item item = itemBean.getItem(itemId);

        Favourites favourites=new Favourites();
        favourites.setItem(item);
        favourites.setPerson(person);

        em.persist(favourites);

        return favourites;
    }

    @Transactional
    public Favourites deleteFavourite(Integer personId,Integer itemId){
        Favourites favourites=getFavourite(personId,itemId);

        if(favourites!=null){
            em.remove(favourites);
        }

        return favourites;
    }

    @Transactional
    public Favourites deleteOneFavourite(Integer favouritesId){
        Favourites favourites=em.find(Favourites.class,favouritesId);

        if(favourites!=null){
            em.remove(favourites);
        }

        return favourites;
    }

}
