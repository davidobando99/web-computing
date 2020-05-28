package co.edu.icesi.demo.test;



import static org.junit.Assert.*;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.demo.dao.ITAlumnoDao;
import co.edu.icesi.demo.model.TAlumno;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@Rollback(false)
public class TestTAlumnoDAO {

	@Autowired
	private ITAlumnoDao talumnoDao;
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void aTest() {

		assertNotNull(talumnoDao);
		
		TAlumno talumno = new TAlumno();
		talumno.setNombre("Jack");
		talumno.setApellidos("Bauer");
		
		talumnoDao.save(talumno);
		
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void bTest() {

		assertNotNull(talumnoDao);
		
		TAlumno alumno = talumnoDao.findById(2);
		assertNotNull("Code not found", alumno);
		alumno.setApellidos("JK");
		talumnoDao.update(alumno);
		
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void cTest() {

		assertNotNull(talumnoDao);
		
		TAlumno talumno = talumnoDao.findById(2);
		assertNotNull("El cliente NO existe", talumno);
		talumnoDao.delete(talumno);
		
	}

}
