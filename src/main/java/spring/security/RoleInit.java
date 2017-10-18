package spring.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class RoleInit {
private Role role1 = new Role(1,"COMPANY","company");
private Role role2 = new Role(2,"ADMIN","admin");
private Role role3 = new Role(3,"CUSTOMER","customr");

private Collection<GrantedAuthority> roles = new ArrayList<>();

public RoleInit() {}

public Collection<GrantedAuthority> getRoles(){
	roles.add(new SimpleGrantedAuthority(role1.getCode()));
	roles.add(new SimpleGrantedAuthority(role2.getCode()));
	//roles.add(new SimpleGrantedAuthority(role3.getCode()));
	
	return roles;
}
public Collection<GrantedAuthority> getRolesCompany(){
	roles.add(new SimpleGrantedAuthority(role1.getCode()));
	
	return roles;
}
public Collection<GrantedAuthority> getRolesCustomer(){
	roles.add(new SimpleGrantedAuthority(role3.getCode()));
	
	return roles;
}
public Collection<GrantedAuthority> getRolesAdmin(){
	roles.add(new SimpleGrantedAuthority(role2.getCode()));
	
	return roles;
}

}
