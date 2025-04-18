package  ma.zyn.app.ws.converter.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;




import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.transaction.StatusPaiement;
import ma.zyn.app.ws.dto.transaction.StatusPaiementDto;

@Component
public class StatusPaiementConverter {



    public StatusPaiement toItem(StatusPaiementDto dto) {
        if (dto == null) {
            return null;
        } else {
        StatusPaiement item = new StatusPaiement();
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


    public StatusPaiementDto toDto(StatusPaiement item) {
        if (item == null) {
            return null;
        } else {
            StatusPaiementDto dto = new StatusPaiementDto();
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


	
    public List<StatusPaiement> toItem(List<StatusPaiementDto> dtos) {
        List<StatusPaiement> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (StatusPaiementDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<StatusPaiementDto> toDto(List<StatusPaiement> items) {
        List<StatusPaiementDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (StatusPaiement item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(StatusPaiementDto dto, StatusPaiement t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<StatusPaiement> copy(List<StatusPaiementDto> dtos) {
        List<StatusPaiement> result = new ArrayList<>();
        if (dtos != null) {
            for (StatusPaiementDto dto : dtos) {
                StatusPaiement instance = new StatusPaiement();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
