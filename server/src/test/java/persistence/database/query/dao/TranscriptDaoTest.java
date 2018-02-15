package persistence.database.query.dao;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import persistence.session.factory.configuration.SessionFactoryProvider;
import persistence.transcript.model.Transcript;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Test
public class TranscriptDaoTest {
    private static final String SOMETHING_USEFUL_TO_BE_SAVED = "something useful to be saved";
    private TranscriptDao transcriptDao;

    @BeforeMethod
    public void setUp() {
        transcriptDao = new TranscriptDao(SessionFactoryProvider.provide());
        transcriptDao.clearAllData();
        assertThat(transcriptDao.getAllData()).isEmpty();
    }

    public void saveEntityTranscript() {
        transcriptDao.save(new Transcript(SOMETHING_USEFUL_TO_BE_SAVED, new Date()));
        assertThat(transcriptDao.getAllData()).isNotEmpty();
    }

    @Test(expectedExceptions = javax.persistence.OptimisticLockException.class)
    public void tryUpdateNotExistingEntity() {
        transcriptDao.update(new Transcript(SOMETHING_USEFUL_TO_BE_SAVED, new Date()));
    }

    public void updateEntity() {
        //given
        Transcript entity = new Transcript(SOMETHING_USEFUL_TO_BE_SAVED, new Date());
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

    public void clearTranscriptTable() {
        //given
        Transcript entity = new Transcript(SOMETHING_USEFUL_TO_BE_SAVED, new Date());
        transcriptDao.save(entity);
        //when
        List<Transcript> entityFromDB = transcriptDao.getAllData();
        assertThat(entityFromDB).isNotEmpty();

        transcriptDao.clearAllData();
        entityFromDB = transcriptDao.getAllData();

        //then
        assertThat(entityFromDB).isEmpty();
    }

    public void deleteSingleEntity() {
        //given
        Transcript entity = new Transcript(SOMETHING_USEFUL_TO_BE_SAVED, new Date());
        transcriptDao.save(entity);
        //when
        Transcript entityFromDB = transcriptDao.getAllData().get(0);
        transcriptDao.delete(entityFromDB);
        List<Transcript> transcripts = transcriptDao.getAllData();

        //then
        assertThat(transcripts).isEmpty();
    }

    /**
     * priority has to be set at highest, because there is automate incrementation of ids.
     * We are looking for first saved entity so it will be id = 1;
     */
    @Test(priority = -1)
    public void findEntityById() {
        //given
        Transcript entity = new Transcript(SOMETHING_USEFUL_TO_BE_SAVED, new Date());
        transcriptDao.save(entity);
        //when
        Transcript entityFromDB = transcriptDao.findById(1);

        //then
        assertThat(entityFromDB.getMessage()).isEqualTo(SOMETHING_USEFUL_TO_BE_SAVED);
    }
}
