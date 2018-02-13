package persistence.database.query.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import persistence.transcript.model.Transcript;


/**
 * class which will handle persistence queries like save,update,delete,read for Transcript class
 */
public class TranscriptDao implements GenericDao<Transcript> {
    private final SessionFactory sf;
    private Session currentSession;
    private Transaction currentTransaction;

    public TranscriptDao(SessionFactory sf) {
        this.sf = sf;
    }

    public Session openCurrentSession() {
        currentSession = sf.openSession();
        return currentSession;
    }

    public Session openCurrentSessionWithTransaction() {
        currentSession = openCurrentSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }

    @Override
    public void save(Transcript transcript) {
        openCurrentSessionWithTransaction().persist(transcript);
        currentTransaction.commit();
    }

    @Override
    public void update(Transcript transcript) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            session.update(transcript);
            session.getTransaction().commit();
        }
    }

    @Override
    public Transcript findById(long id) {
        return null;
    }

    @Override
    public void delete(Transcript obj) {

    }

    @Override
    public void getAllData() {

    }

    @Override
    public void clearTable() {

    }
}
