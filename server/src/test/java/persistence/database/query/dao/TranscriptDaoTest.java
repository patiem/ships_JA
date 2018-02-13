package persistence.database.query.dao;

import org.hibernate.SessionFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import persistence.session.factory.configuration.SessionFactoryProvider;
import persistence.transcript.model.Transcript;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;


@Test
public class TranscriptDaoTest {
    private TranscriptDao transcriptDao;

    @BeforeMethod
    public void setUp() {
        transcriptDao = new TranscriptDao(SessionFactoryProvider.provide());
        transcriptDao.clearAllData();
        assertThat(transcriptDao.getAllData()).isEmpty();
    }

    public void saveEntityTranscript() {
        transcriptDao.save(new Transcript("something useful to be saved", new Date()));
        assertThat(transcriptDao.getAllData()).isNotEmpty();
    }

    @Test(expectedExceptions = javax.persistence.OptimisticLockException.class)
    public void tryUpdateNotExistingEntity() {
        transcriptDao.update(new Transcript("something useful to be saved", new Date()));
    }

    public void updateEntity() {
        //given
        Transcript entity = new Transcript("something useful to be saved", new Date());
        String someNewText = "some new text";
        transcriptDao.save(entity);
        //when
        Transcript entityFromDB = transcriptDao.getAllData().get(0);
        entityFromDB.setMessage(someNewText);
        transcriptDao.update(entityFromDB);
        Transcript updatedEntity = transcriptDao.getAllData().get(0);

        //then
        assertThat(updatedEntity.getMessage()).isEqualTo("some new text");
    }
}
