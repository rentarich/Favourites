package si.fri.rso.favourites.services.beans;

import com.kumuluz.ee.logs.LogManager;
import si.fri.rso.favourites.models.Item;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.UUID;


@ApplicationScoped
public class ItemBean {
    private com.kumuluz.ee.logs.Logger logger = LogManager.getLogger(ItemBean.class.getName());
    private String idBean;

    @PostConstruct
    private void init(){
        idBean = UUID.randomUUID().toString();
        logger.info("Init bean: " + ItemBean.class.getSimpleName() + " idBean: " + idBean);
    }

    @PreDestroy
    private void destroy(){
        logger.info("Deinit bean: " + ItemBean.class.getSimpleName() + " idBean: " + idBean);
    }

    @PersistenceContext(unitName = "item-jpa")
    private EntityManager em;

    public List getItems(){

        return em.createNamedQuery("Item.getAll").getResultList();
    }

    public Item getItem(Integer id){
        Item item = em.find(Item.class, id);
        return item;
    }
}

