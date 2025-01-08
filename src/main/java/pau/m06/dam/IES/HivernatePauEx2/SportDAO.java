package pau.m06.dam.IES.HivernatePauEx2;

import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class SportDAO implements DAOInterface<Sports> {

    private final Session session;

    public SportDAO(Session session) {
        this.session = session;
    }

    @Override
    public void add(Sports sport) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.persist(sport);  // Guardar el deporte usando persist
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();  // Deshacer la transacción si algo falla
            }
            System.err.println("Error al agregar deporte: " + e.getMessage());
        }
    }

    @Override
    public List<Sports> getAll() {
        return session.createQuery("from Sports", Sports.class).getResultList();
    }
}
