package co.edu.icesi.fi.tics.tssc.testDao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.apache.commons.logging.Log;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.fi.tics.tssc.TallerPersistenciaApplication;
import co.edu.icesi.fi.tics.tssc.dao.ITsscTimecontrolDAO;
import co.edu.icesi.fi.tics.tssc.model.TsscTimecontrol;

@SpringBootTest(classes = TallerPersistenciaApplication.class)
@RunWith(SpringRunner.class)
@Rollback(false)
class TsscTimecontrolDaoTest {

	@Autowired
	private ITsscTimecontrolDAO timeDao;
	
	private TsscTimecontrol time1;
		
	public void setUp1() {

		time1 = new TsscTimecontrol();
		timeDao.save(time1);
	}
	
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testSaveTimecontrol() {
		assertNotNull(timeDao);

		TsscTimecontrol timeP = new TsscTimecontrol();
		timeDao.save(timeP);

		assertNotNull(timeDao.findById(timeP.getId()).get(0));
		
		timeDao.delete(timeP);

	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testUpdateTimecontrol() {
		setUp1();
		assertNotNull(timeDao);

		TsscTimecontrol time2 = new TsscTimecontrol();
		time2.setId(time1.getId());

		timeDao.update(time2);

		assertNotNull(timeDao.findById(time2.getId()).get(0));
		
		timeDao.delete(time1);
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testDeleteTimecontrol() {
		setUp1();
		assertNotNull(timeDao);
		timeDao.delete(time1);
		assertTrue(timeDao.findAll().size() == 0);
	}

}
