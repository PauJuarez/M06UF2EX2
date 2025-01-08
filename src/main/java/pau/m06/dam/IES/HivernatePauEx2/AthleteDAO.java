package pau.m06.dam.IES.HivernatePauEx2;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class AthleteDAO implements DAOInterface<Athletes> {

    private final EntityManager entityManager;

    public AthleteDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void add(Athletes athlete) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(athlete);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Error al agregar atleta: " + e.getMessage());
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
        }
    }

    @Override
    public List<Athletes> getAll() {
        TypedQuery<Athletes> query = entityManager.createQuery("SELECT a FROM Athletes a", Athletes.class);
        return query.getResultList();
    }

    public List<Athletes> findName(String partialName) {
        TypedQuery<Athletes> query = entityManager.createQuery(
                "SELECT a FROM Athletes a WHERE LOWER(a.name) LIKE LOWER(:partialName)", Athletes.class);
        query.setParameter("partialName", "%" + partialName + "%");
        return query.getResultList();
    }
}
