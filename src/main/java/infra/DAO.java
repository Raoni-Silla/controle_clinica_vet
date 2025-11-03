package infra;


import javax.persistence.*;
import java.util.List;

public class DAO <E>{


    final static EntityManagerFactory emf = Persistence.createEntityManagerFactory("clinica-vet-jpa");

    public DAO <E> incluirAtomico (E entidade){
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.persist(entidade);
        em.getTransaction().commit();

        em.close();

        return this;
    }

    public List<E> listarTodosAtomico(Class<E> entidade){

        EntityManager emTarefa = emf.createEntityManager();


        String jpql = "select e from " + entidade.getSimpleName() +" e";
        TypedQuery <E> query = emTarefa.createQuery(jpql,entidade);
        List<E> lista = query.getResultList();

        emTarefa.close();

        return lista;

    }

    public E findById(Class<E> entidade, Long id){
        EntityManager em = emf.createEntityManager();
        E entidadeEncontrada = em.find(entidade,id);
        em.close();
        return entidadeEncontrada;
    }


    public void removeById(Class<E> entidade, Long id){
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            E entidadeEncontrada = em.find(entidade, id);

            if (entidadeEncontrada != null) {
                em.remove(entidadeEncontrada);
            }

            em.getTransaction().commit();

            System.out.println("Removido com sucesso!");

        }catch (Exception e) {

            System.out.println("ERRO: Não foi possível remover a entidade.");
            System.out.println("MOTIVO: " + e.getMessage());

        }finally {
            em.close();
        }

    }

}
