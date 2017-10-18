package spring.security;

import org.springframework.stereotype.Component;

@Component
public class Role {

	private long id;
	private String code;
	private String label;
	
	public Role(){}
	

	public Role(long id, String code, String label) {
		this.id = id;
		this.code = "ROLE_"+code;
		this.label = label;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = "ROLE_"+code;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	
	
	
}
	
	
	

