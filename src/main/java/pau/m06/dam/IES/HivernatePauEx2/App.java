package pau.m06.dam.IES.HivernatePauEx2;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class App {

    static SessionFactory sessionFactory = null;
    static Session session = null;

    public static void tearUp() {
        sessionFactory = new org.hibernate.cfg.Configuration().configure().buildSessionFactory();
        session = sessionFactory.openSession();
    }

    public static void tearDown() {
        if (session != null) {
            session.close();
        }
    }

    public static void comprobarSesion() {
        if (session != null) {
            System.out.println("Sesión abierta");
        } else {
            System.out.println("Fallo en la sesión");
        }
    }

    private static void addSport() {
        String sportName = View.sportForm();
        Sports sport = new Sports(sportName);

        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.persist(sport);
            transaction.commit();
            View.showMessage("Deporte agregado con éxito.");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback(); 
            }
            View.showMessage("Error al agregar deporte: " + e.getMessage());
        }
    }

    private static void addAthlete() {
        Athletes athlete = View.athleteForm();
        if (athlete == null) {
            View.showMessage("Atleta no creado. Verifica los datos ingresados.");
            return;
        }

        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.persist(athlete);
            transaction.commit();
            View.showMessage("Atleta agregado con éxito.");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            View.showMessage("Error al agregar atleta: " + e.getMessage());
        }
    }

    private static void searchAthlete() {
        String partialName = View.askAthlete();
        try {
            Query<Athletes> query = session.createQuery(
                    "from Athletes a where lower(a.name) like lower(:name)", Athletes.class
            );
            query.setParameter("name", "%" + partialName + "%");
            List<Athletes> athletes = query.list();
            View.printAthletes(athletes);
        } catch (Exception e) {
            View.showMessage("Error al buscar atleta: " + e.getMessage());
        }
    }

    private static void listAthletes() {
        List<Sports> sports = session.createQuery("from Sports", Sports.class).list();
        int selectedSportId = View.askSport(sports);

        try {
            Query<Athletes> query = session.createQuery(
                    "from Athletes a where a.sport.cod = :sportId", Athletes.class
            );
            query.setParameter("sportId", selectedSportId);
            List<Athletes> athletesBySport = query.list();
            athletesBySport.forEach(View::printAthlete);
        } catch (Exception e) {
            View.showMessage("Error al listar atletas por deporte: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        System.out.println("Connecting...");
        try {
            tearUp();
            System.out.println("Connected!");
            comprobarSesion();

            while (true) {
                int option = View.showMenu();
                switch (option) {
                    case 1 -> addSport();
                    case 2 -> addAthlete();
                    case 3 -> searchAthlete();
                    case 4 -> listAthletes();
                    case 5 -> {
                        View.showMessage("Saliendo...");
                        return;
                    }
                    default -> View.showMessage("Opción no válida. Intente nuevamente.");
                }
            }
        } catch (Exception e) {
            System.out.println("Error al conectar con la base de datos: " + e.getMessage());
            e.printStackTrace();
        } finally {
            tearDown();
        }
    }
}
