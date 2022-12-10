package br.edu.utfpr.paranazom.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_permission")
public class Permission {
	
	@Id
	private Long permission_id;
	private String description;
	
	public Long getPermissionId() {
		return permission_id;
	}
	public void setPermissionId(Long permission_id) {
		this.permission_id = permission_id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((permission_id == null) ? 0 : permission_id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Permission other = (Permission) obj;
		if (permission_id == null) {
			if (other.permission_id != null)
				return false;
		} else if (!permission_id.equals(other.permission_id))
			return false;
		return true;
	}
	

}