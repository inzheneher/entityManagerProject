import conf.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = Configuration.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MovieTest {
    @Autowired
    private EntityManagerFactory emf;
    private EntityManager em;
    private Movie movieFromPersist;
    private final static String SELECT_QUERY = "FROM Movie WHERE id = %s";

    @BeforeAll
    void setUp() {
        em = emf.createEntityManager();

        em.getTransaction().begin();

        Movie movieToPersist = new Movie(
                1L,
                "The Godfather",
                1972,
                "English");

        em.persist(movieToPersist);
        em.getTransaction().commit();
        movieFromPersist = (Movie) em.createQuery(String.format(SELECT_QUERY, 1)).getSingleResult();
    }

    @Test
    void shouldThrowExceptionWhenNoResultsFromQuery() {
        assertThrows(
                NoResultException.class,
                () -> em.createQuery(String.format(SELECT_QUERY, 2)).getSingleResult());
    }

    @Test
    void shouldPassIfQueryReturnNotNull() {
        assertNotNull(movieFromPersist);
    }

    @Test
    void shouldReturnTrueIfAtLeastOneRecordWillBeFoundInDb() {
        assertEquals("The Godfather", movieFromPersist.getMovieName());
    }
}
