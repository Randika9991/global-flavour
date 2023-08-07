package lk.ijse.orm.thogakade.repository;

import lk.ijse.orm.thogakade.config.SessionFactoryConfig;
import lk.ijse.orm.thogakade.entity.Customer;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CustomerRepository {
    private final Session session;

    public CustomerRepository(){
        session= SessionFactoryConfig.getInstance().getSession();
    }

    public Customer getStudent(int id) {
        try {
            return session.get(Customer.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public int saveCustomer(Customer customer) {
        Transaction transaction = session.beginTransaction();
        try {
            int customerId = (int)session.save(customer);
            transaction.commit();
            session.close();
            return customerId;
        } catch (Exception e) {
            System.out.println(e);
            transaction .rollback();
            session.close();
            return -1;
        }
    }

    public boolean updateCustomer(Customer customer) {
        Transaction transaction1 = session.beginTransaction();
        try {
            session.update(customer);   //session updated(customer1)  --->me dekenma puluwan
            transaction1.commit();
            session.close();
            return true;
        } catch (Exception e) {
            transaction1.rollback();
            session.close();
            return false;
        }
    }

    public boolean deleteCustomer(Customer customer) {
        Transaction deleateTransaction = session.beginTransaction();
        try {
            session.delete(customer);
            deleateTransaction.commit();
            session.close();
            return true;
        } catch (Exception e) {
            deleateTransaction.rollback();
            session.close();
            return false;
        }
    }
}
