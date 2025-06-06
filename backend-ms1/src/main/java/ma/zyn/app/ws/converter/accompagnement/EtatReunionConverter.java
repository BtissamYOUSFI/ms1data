package  ma.zyn.app.ws.converter.accompagnement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;




import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.accompagnement.EtatReunion;
import ma.zyn.app.ws.dto.accompagnement.EtatReunionDto;

@Component
public class EtatReunionConverter {



    public EtatReunion toItem(EtatReunionDto dto) {
        if (dto == null) {
            return null;
        } else {
        EtatReunion item = new EtatReunion();
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


    public EtatReunionDto toDto(EtatReunion item) {
        if (item == null) {
            return null;
        } else {
            EtatReunionDto dto = new EtatReunionDto();
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


	
    public List<EtatReunion> toItem(List<EtatReunionDto> dtos) {
        List<EtatReunion> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (EtatReunionDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<EtatReunionDto> toDto(List<EtatReunion> items) {
        List<EtatReunionDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (EtatReunion item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(EtatReunionDto dto, EtatReunion t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<EtatReunion> copy(List<EtatReunionDto> dtos) {
        List<EtatReunion> result = new ArrayList<>();
        if (dtos != null) {
            for (EtatReunionDto dto : dtos) {
                EtatReunion instance = new EtatReunion();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
