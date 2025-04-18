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
import ma.zyn.app.bean.core.profil.Metier;
import ma.zyn.app.ws.dto.profil.MetierDto;

@Component
public class MetierConverter {



    public Metier toItem(MetierDto dto) {
        if (dto == null) {
            return null;
        } else {
        Metier item = new Metier();
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


    public MetierDto toDto(Metier item) {
        if (item == null) {
            return null;
        } else {
            MetierDto dto = new MetierDto();
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


	
    public List<Metier> toItem(List<MetierDto> dtos) {
        List<Metier> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (MetierDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<MetierDto> toDto(List<Metier> items) {
        List<MetierDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Metier item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(MetierDto dto, Metier t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<Metier> copy(List<MetierDto> dtos) {
        List<Metier> result = new ArrayList<>();
        if (dtos != null) {
            for (MetierDto dto : dtos) {
                Metier instance = new Metier();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
