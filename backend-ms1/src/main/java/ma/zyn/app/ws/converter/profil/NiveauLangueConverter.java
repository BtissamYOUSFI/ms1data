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
import ma.zyn.app.bean.core.profil.NiveauLangue;
import ma.zyn.app.ws.dto.profil.NiveauLangueDto;

@Component
public class NiveauLangueConverter {



    public NiveauLangue toItem(NiveauLangueDto dto) {
        if (dto == null) {
            return null;
        } else {
        NiveauLangue item = new NiveauLangue();
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


    public NiveauLangueDto toDto(NiveauLangue item) {
        if (item == null) {
            return null;
        } else {
            NiveauLangueDto dto = new NiveauLangueDto();
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


	
    public List<NiveauLangue> toItem(List<NiveauLangueDto> dtos) {
        List<NiveauLangue> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (NiveauLangueDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<NiveauLangueDto> toDto(List<NiveauLangue> items) {
        List<NiveauLangueDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (NiveauLangue item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(NiveauLangueDto dto, NiveauLangue t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<NiveauLangue> copy(List<NiveauLangueDto> dtos) {
        List<NiveauLangue> result = new ArrayList<>();
        if (dtos != null) {
            for (NiveauLangueDto dto : dtos) {
                NiveauLangue instance = new NiveauLangue();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
