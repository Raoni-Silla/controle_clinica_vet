package infra;

import entidades.Consulta;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.List;


public class ConsultaDao extends DAO <Consulta> {

    public List<Consulta> RelatorioConsultasPorData(LocalDate dataInicio, LocalDate dataFim) {

        String jpql = "select c from Consulta c where c.dataConsulta between :dataInicio and :dataFim";

        EntityManager em = emf.createEntityManager();

        try {
            TypedQuery<Consulta> query = em.createQuery(jpql, Consulta.class);
            query.setParameter("dataInicio", dataInicio);
            query.setParameter("dataFim", dataFim);

            return query.getResultList();

        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}
