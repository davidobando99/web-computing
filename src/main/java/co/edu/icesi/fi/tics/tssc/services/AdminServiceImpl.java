package co.edu.icesi.fi.tics.tssc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.fi.tics.tssc.dao.ITsscAdminDAO;
import co.edu.icesi.fi.tics.tssc.model.TsscAdmin;
import co.edu.icesi.fi.tics.tssc.repositories.IAdminRepository;

@Service
public class AdminServiceImpl implements AdminService{
	
	
	//private IAdminRepository adminRepository;
	
	private ITsscAdminDAO adminDao;
	
	@Autowired
	public AdminServiceImpl(ITsscAdminDAO adminDao) {
		this.adminDao= adminDao;
	}

	@Override
	@Transactional
	public TsscAdmin save(TsscAdmin nuevo) {
		adminDao.save(nuevo);
		return nuevo;
	}

	@Override
	@Transactional
	public TsscAdmin edit(TsscAdmin editado) {
		 adminDao.update(editado);
		 return editado;
	}

	@Override
	@Transactional
	public void delete(TsscAdmin delete) {
		adminDao.delete(delete);
	}
	
	

}
