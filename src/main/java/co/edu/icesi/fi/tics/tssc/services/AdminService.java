package co.edu.icesi.fi.tics.tssc.services;

import co.edu.icesi.fi.tics.tssc.model.TsscAdmin;

public interface AdminService {

	public TsscAdmin save(TsscAdmin nuevo);
	public TsscAdmin edit(TsscAdmin editado);
	public void delete(TsscAdmin delete);
}
