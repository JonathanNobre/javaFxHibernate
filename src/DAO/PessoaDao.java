package DAO;

import connection.ConnectionFactory;
import java.util.List;
import javax.persistence.EntityManager;
import model.Pessoa;

public class PessoaDao {

    public static Pessoa save(Pessoa pessoa) {

        EntityManager em = new ConnectionFactory().getConnection();

        try {

            em.getTransaction().begin();

            if (pessoa.getId() == null) {

                em.persist(pessoa);

            } else {

                em.merge(pessoa);

            }

            em.getTransaction().commit();

        } catch (Exception e) {

            System.err.println(e);

            em.getTransaction().rollback();

        } finally {

            em.close();

        }

        return pessoa;

    }

    public Pessoa findById(Integer id) {

        EntityManager em = new ConnectionFactory().getConnection();

        Pessoa pessoa = null;

        try {

            pessoa = em.find(Pessoa.class, id);

        } catch (Exception e) {

            System.err.println(e);

        } finally {

            em.close();

        }

        return pessoa;

    }

    public static List<Pessoa> findAll() {

        EntityManager em = new ConnectionFactory().getConnection();

        List<Pessoa> pessoas = null;

        try {

            pessoas = em.createQuery("from Pessoa c").getResultList();

        } catch (Exception e) {

            System.err.println(e);

        } finally {

            em.close();

        }

        return pessoas;

    }

    public Pessoa remove(Integer id) {

        EntityManager em = new ConnectionFactory().getConnection();

        Pessoa pessoa = null;

        try {

            pessoa = em.find(Pessoa.class, id);

            em.getTransaction().begin();

            em.remove(pessoa);

            em.getTransaction().commit();

        } catch (Exception e) {

            System.err.println(e);

            em.getTransaction().rollback();

        } finally {

            em.close();

        }

        return pessoa;

    }

}
