package persistence;

import org.hibernate.SessionFactory;
import org.testng.annotations.Test;
import persistence.database.query.dao.TranscriptDao;
import persistence.session.factory.configuration.SessionFactoryProvider;
import persistence.transcript.model.Transcript;

import java.util.Date;

@Test
public class ConfigurationTest {
    private SessionFactory sf = SessionFactoryProvider.provide();

    public void testConnection() {

    }

    public void saveEntityTranscript() {
        TranscriptDao transcriptDao = new TranscriptDao(sf);
        transcriptDao.save(new Transcript("something useful to be saved", new Date()));
    }
}
