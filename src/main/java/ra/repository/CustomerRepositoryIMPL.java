package ra.repository;

import org.springframework.transaction.annotation.Transactional;
import ra.model.Customer;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
@Transactional
public class CustomerRepositoryIMPL implements ICustomerRepository{
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Customer> findAll() {
        String queryStr="select c from Customer as c";
        TypedQuery<Customer> query= em.createQuery(queryStr, Customer.class);
        return query.getResultList();
    }

    @Override
    public Customer findById(Long id) {
        String queryStr="select c from Customer as c where c.id= :id";
        TypedQuery<Customer> query=em.createQuery(queryStr, Customer.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public void save(Customer customer) {
        if (customer.getId()!= null){
            em.merge(customer);
        }else {
            em.persist(customer);
        }
    }

    @Override
    public void remove(Long id) {
        Customer customer=findById(id);
        if (customer!= null){
            em.remove(id);
        }
    }
}
