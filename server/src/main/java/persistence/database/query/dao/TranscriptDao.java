package persistence.database.query.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import persistence.transcript.model.Transcript;

import java.util.List;

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
        openCurrentSessionWithTransaction().update(transcript);
        currentTransaction.commit();
    }

    @Override
    public Transcript findById(long id) {
        return openCurrentSession().get(Transcript.class, id);
    }

    @Override
    public void delete(Transcript transcript) {
        openCurrentSessionWithTransaction().delete(transcript);
    }

    @Override
    public List<Transcript> getAllData() {
        return openCurrentSession().createQuery("FROM transcript", Transcript.class).list();
    }

    @Override
    public void clearAllData() {
        //todo created named query for this (portable issue)
        openCurrentSessionWithTransaction().createNativeQuery("truncate table transcript");
    }
}
