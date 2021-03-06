package co.edu.icesi.taller3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.taller3.model.TsscAdmin;
import co.edu.icesi.taller3.repository.AdminRepository;

@Service
public class AdminServiceImp implements AdminService{

	
	private AdminRepository adminRepo;
	
	@Autowired
	public AdminServiceImp(AdminRepository adminRepo) {
		this.adminRepo = adminRepo;
	}

	@Override
	public TsscAdmin saveAdmin(TsscAdmin admin) {
		return adminRepo.save(admin);
	}
	
	
}
