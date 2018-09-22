/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teste;

import connection.ConnectionFactory;
import javax.persistence.EntityManager;
import model.Pessoa;

public class Teste {

    public static void main(String[] args) {

        EntityManager em = new ConnectionFactory().getConnection();
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("Gisele");
        pessoa.setSobreNome("Nobre");
        
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
    }

}
