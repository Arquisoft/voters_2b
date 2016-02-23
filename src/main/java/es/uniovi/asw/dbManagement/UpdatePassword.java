package es.uniovi.asw.dbManagement;

import es.uniovi.asw.dbManagement.model.Voter;

public interface UpdatePassword {

	public boolean changePassword(String password,String newPassword,Voter voter);
}
