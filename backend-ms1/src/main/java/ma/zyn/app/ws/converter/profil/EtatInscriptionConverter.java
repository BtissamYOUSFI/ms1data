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
import ma.zyn.app.bean.core.profil.EtatInscription;
import ma.zyn.app.ws.dto.profil.EtatInscriptionDto;

@Component
public class EtatInscriptionConverter {



    public EtatInscription toItem(EtatInscriptionDto dto) {
        if (dto == null) {
            return null;
        } else {
        EtatInscription item = new EtatInscription();
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


    public EtatInscriptionDto toDto(EtatInscription item) {
        if (item == null) {
            return null;
        } else {
            EtatInscriptionDto dto = new EtatInscriptionDto();
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


	
    public List<EtatInscription> toItem(List<EtatInscriptionDto> dtos) {
        List<EtatInscription> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (EtatInscriptionDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<EtatInscriptionDto> toDto(List<EtatInscription> items) {
        List<EtatInscriptionDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (EtatInscription item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(EtatInscriptionDto dto, EtatInscription t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<EtatInscription> copy(List<EtatInscriptionDto> dtos) {
        List<EtatInscription> result = new ArrayList<>();
        if (dtos != null) {
            for (EtatInscriptionDto dto : dtos) {
                EtatInscription instance = new EtatInscription();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
