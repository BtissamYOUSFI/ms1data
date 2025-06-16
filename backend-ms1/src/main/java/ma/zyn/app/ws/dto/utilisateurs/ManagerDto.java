package ma.zyn.app.ws.dto.utilisateurs;

import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.security.bean.Role;
import ma.zyn.app.zynerator.security.ws.dto.UserDto;

import java.util.Collection;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class ManagerDto extends UserDto {

    private String description  ;

    private Collection<Role> roles;
    public ManagerDto(){
        super();
    }

    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }
}
