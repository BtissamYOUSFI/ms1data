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
import ma.zyn.app.bean.core.utilisateurs.Manager;
import ma.zyn.app.ws.dto.utilisateurs.ManagerDto;

@Component
public class ManagerConverter {



    public Manager toItem(ManagerDto dto) {
        if (dto == null) {
            return null;
        } else {
            Manager item = new Manager();
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


    public ManagerDto toDto(Manager item) {
        if (item == null) {
            return null;
        } else {
            ManagerDto dto = new ManagerDto();
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



    public List<Manager> toItem(List<ManagerDto> dtos) {
        List<Manager> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (ManagerDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<ManagerDto> toDto(List<Manager> items) {
        List<ManagerDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Manager item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(ManagerDto dto, Manager t) {
        BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<Manager> copy(List<ManagerDto> dtos) {
        List<Manager> result = new ArrayList<>();
        if (dtos != null) {
            for (ManagerDto dto : dtos) {
                Manager instance = new Manager();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}

//package ma.zyn.app.ws.converter.utilisateurs;
//
//import ma.zyn.app.bean.core.utilisateurs.Manager;
//import ma.zyn.app.ws.dto.utilisateurs.ManagerDto;
//import ma.zyn.app.zynerator.converter.AbstractConverterHelper;
//import ma.zyn.app.zynerator.util.StringUtil;
//import org.springframework.beans.BeanUtils;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Component
//public class ManagerConverter {
//
//
//
//    public Manager toItem(ManagerDto dto) {
//        if (dto == null) {
//            return null;
//        } else {
//        Manager item = new Manager();
//            if(StringUtil.isNotEmpty(dto.getId()))
//                item.setId(dto.getId());
//            if(StringUtil.isNotEmpty(dto.getDescription()))
//                item.setDescription(dto.getDescription());
//            item.setAccountNonLocked(dto.getAccountNonLocked());
//            item.setPasswordChanged(dto.getPasswordChanged());
//            if(StringUtil.isNotEmpty(dto.getUsername()))
//                item.setUsername(dto.getUsername());
//            item.setAccountNonExpired(dto.getAccountNonExpired());
//            item.setCredentialsNonExpired(dto.getCredentialsNonExpired());
//            item.setEnabled(dto.getEnabled());
//            if(StringUtil.isNotEmpty(dto.getEmail()))
//                item.setEmail(dto.getEmail());
//            if(StringUtil.isNotEmpty(dto.getPassword()))
//                item.setPassword(dto.getPassword());
//
//
//            //item.setRoles(dto.getRoles());
//
//        return item;
//        }
//    }
//
//
//    public ManagerDto toDto(Manager item) {
//        if (item == null) {
//            return null;
//        } else {
//            ManagerDto dto = new ManagerDto();
//            if(StringUtil.isNotEmpty(item.getId()))
//                dto.setId(item.getId());
//            if(StringUtil.isNotEmpty(item.getDescription()))
//                dto.setDescription(item.getDescription());
//            if(StringUtil.isNotEmpty(item.getAccountNonLocked()))
//                dto.setAccountNonLocked(item.getAccountNonLocked());
//            if(StringUtil.isNotEmpty(item.getPasswordChanged()))
//                dto.setPasswordChanged(item.getPasswordChanged());
//            if(StringUtil.isNotEmpty(item.getUsername()))
//                dto.setUsername(item.getUsername());
//            if(StringUtil.isNotEmpty(item.getAccountNonExpired()))
//                dto.setAccountNonExpired(item.getAccountNonExpired());
//            if(StringUtil.isNotEmpty(item.getCredentialsNonExpired()))
//                dto.setCredentialsNonExpired(item.getCredentialsNonExpired());
//            if(StringUtil.isNotEmpty(item.getEnabled()))
//                dto.setEnabled(item.getEnabled());
//            if(StringUtil.isNotEmpty(item.getEmail()))
//                dto.setEmail(item.getEmail());
//
//
//        return dto;
//        }
//    }
//
//
//	
//    public List<Manager> toItem(List<ManagerDto> dtos) {
//        List<Manager> items = new ArrayList<>();
//        if (dtos != null && !dtos.isEmpty()) {
//            for (ManagerDto dto : dtos) {
//                items.add(toItem(dto));
//            }
//        }
//        return items;
//    }
//
//
//    public List<ManagerDto> toDto(List<Manager> items) {
//        List<ManagerDto> dtos = new ArrayList<>();
//        if (items != null && !items.isEmpty()) {
//            for (Manager item : items) {
//                dtos.add(toDto(item));
//            }
//        }
//        return dtos;
//    }
//
//
//    public void copy(ManagerDto dto, Manager t) {
//		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
//    }
//
//    public List<Manager> copy(List<ManagerDto> dtos) {
//        List<Manager> result = new ArrayList<>();
//        if (dtos != null) {
//            for (ManagerDto dto : dtos) {
//                Manager instance = new Manager();
//                copy(dto, instance);
//                result.add(instance);
//            }
//        }
//        return result.isEmpty() ? null : result;
//    }
//
//
//}
