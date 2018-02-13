package persistence.database.query.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import persistence.scores.model.GameResult;

/**
 * class which will handle persistence queries like save,update,delete,read for GameResults class
 */
public class GameResultDao implements GenericDao<GameResult>{
    private SessionFactory sf;

    public GameResultDao(SessionFactory sf) {
        this.sf = sf;
    }

    public void save(GameResult gameResult) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            session.persist(gameResult);
            session.getTransaction().commit();
        }
    }

    @Override
    public void update(GameResult obj) {

    }

    @Override
    public GameResult findById(long id) {
        return null;
    }

    @Override
    public void delete(GameResult obj) {

    }

    @Override
    public void getAllData() {

    }

    @Override
    public void clearTable() {

    }
}
