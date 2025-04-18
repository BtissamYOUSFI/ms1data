package  ma.zyn.app.ws.converter.utilisateurs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;




import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.utilisateurs.Collaborateur;
import ma.zyn.app.ws.dto.utilisateurs.CollaborateurDto;

@Component
public class CollaborateurConverter {



    public Collaborateur toItem(CollaborateurDto dto) {
        if (dto == null) {
            return null;
        } else {
        Collaborateur item = new Collaborateur();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getDescription()))
                item.setDescription(dto.getDescription());
            if(StringUtil.isNotEmpty(dto.getEmail()))
                item.setEmail(dto.getEmail());
            item.setEnabled(dto.getEnabled());
            item.setCredentialsNonExpired(dto.getCredentialsNonExpired());
            item.setAccountNonExpired(dto.getAccountNonExpired());
            if(StringUtil.isNotEmpty(dto.getUsername()))
                item.setUsername(dto.getUsername());
            item.setPasswordChanged(dto.getPasswordChanged());
            item.setAccountNonLocked(dto.getAccountNonLocked());
            if(StringUtil.isNotEmpty(dto.getPassword()))
                item.setPassword(dto.getPassword());


            //item.setRoles(dto.getRoles());

        return item;
        }
    }


    public CollaborateurDto toDto(Collaborateur item) {
        if (item == null) {
            return null;
        } else {
            CollaborateurDto dto = new CollaborateurDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getDescription()))
                dto.setDescription(item.getDescription());
            if(StringUtil.isNotEmpty(item.getEmail()))
                dto.setEmail(item.getEmail());
            if(StringUtil.isNotEmpty(item.getEnabled()))
                dto.setEnabled(item.getEnabled());
            if(StringUtil.isNotEmpty(item.getCredentialsNonExpired()))
                dto.setCredentialsNonExpired(item.getCredentialsNonExpired());
            if(StringUtil.isNotEmpty(item.getAccountNonExpired()))
                dto.setAccountNonExpired(item.getAccountNonExpired());
            if(StringUtil.isNotEmpty(item.getUsername()))
                dto.setUsername(item.getUsername());
            if(StringUtil.isNotEmpty(item.getPasswordChanged()))
                dto.setPasswordChanged(item.getPasswordChanged());
            if(StringUtil.isNotEmpty(item.getAccountNonLocked()))
                dto.setAccountNonLocked(item.getAccountNonLocked());


        return dto;
        }
    }


	
    public List<Collaborateur> toItem(List<CollaborateurDto> dtos) {
        List<Collaborateur> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (CollaborateurDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<CollaborateurDto> toDto(List<Collaborateur> items) {
        List<CollaborateurDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Collaborateur item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(CollaborateurDto dto, Collaborateur t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<Collaborateur> copy(List<CollaborateurDto> dtos) {
        List<Collaborateur> result = new ArrayList<>();
        if (dtos != null) {
            for (CollaborateurDto dto : dtos) {
                Collaborateur instance = new Collaborateur();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
