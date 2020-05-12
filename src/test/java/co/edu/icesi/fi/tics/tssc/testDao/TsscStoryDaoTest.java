package co.edu.icesi.fi.tics.tssc.testDao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.fi.tics.tssc.TallerPersistenciaApplication;
import co.edu.icesi.fi.tics.tssc.dao.ITsscStoryDAO;
import co.edu.icesi.fi.tics.tssc.model.TsscStory;


@SpringBootTest(classes = TallerPersistenciaApplication.class)
@RunWith(SpringRunner.class)
@Rollback(false)
class TsscStoryDaoTest {
	
	@Autowired
	private ITsscStoryDAO storyDao;
	
	private TsscStory story1;
	
	
	public void setUp1() {

		story1 = new TsscStory();
		story1.setDescription("Historia Uno");

		storyDao.save(story1);
	}
	
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testSaveStory() {
		assertNotNull(storyDao);

		TsscStory storyP = new TsscStory();
		storyDao.save(storyP);

		assertTrue(storyDao.findById(storyP.getId()).size() == 1);

	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testUpdateStory() {
		setUp1();
		assertNotNull(storyDao);

		TsscStory story2 = new TsscStory();
		story2.setId(story1.getId());
     
		storyDao.update(story2);

		assertTrue(storyDao.findById(story2.getId()).isEmpty() == false);
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testDeleteStory() {
		setUp1();
		assertNotNull(storyDao);
		storyDao.delete(story1);
		assertTrue(storyDao.findAll().size() == 0);

	}

}
