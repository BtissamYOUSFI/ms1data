package  ma.zyn.app.ws.converter.profil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;




import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.profil.Langue;
import ma.zyn.app.ws.dto.profil.LangueDto;

@Component
public class LangueConverter {



    public Langue toItem(LangueDto dto) {
        if (dto == null) {
            return null;
        } else {
        Langue item = new Langue();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getStyle()))
                item.setStyle(dto.getStyle());
            if(StringUtil.isNotEmpty(dto.getDescription()))
                item.setDescription(dto.getDescription());



        return item;
        }
    }


    public LangueDto toDto(Langue item) {
        if (item == null) {
            return null;
        } else {
            LangueDto dto = new LangueDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getStyle()))
                dto.setStyle(item.getStyle());
            if(StringUtil.isNotEmpty(item.getDescription()))
                dto.setDescription(item.getDescription());


        return dto;
        }
    }


	
    public List<Langue> toItem(List<LangueDto> dtos) {
        List<Langue> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (LangueDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<LangueDto> toDto(List<Langue> items) {
        List<LangueDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Langue item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(LangueDto dto, Langue t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<Langue> copy(List<LangueDto> dtos) {
        List<Langue> result = new ArrayList<>();
        if (dtos != null) {
            for (LangueDto dto : dtos) {
                Langue instance = new Langue();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
