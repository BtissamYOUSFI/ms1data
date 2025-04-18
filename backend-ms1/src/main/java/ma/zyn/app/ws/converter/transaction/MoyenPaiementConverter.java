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
import ma.zyn.app.bean.core.transaction.MoyenPaiement;
import ma.zyn.app.ws.dto.transaction.MoyenPaiementDto;

@Component
public class MoyenPaiementConverter {



    public MoyenPaiement toItem(MoyenPaiementDto dto) {
        if (dto == null) {
            return null;
        } else {
        MoyenPaiement item = new MoyenPaiement();
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


    public MoyenPaiementDto toDto(MoyenPaiement item) {
        if (item == null) {
            return null;
        } else {
            MoyenPaiementDto dto = new MoyenPaiementDto();
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


	
    public List<MoyenPaiement> toItem(List<MoyenPaiementDto> dtos) {
        List<MoyenPaiement> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (MoyenPaiementDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<MoyenPaiementDto> toDto(List<MoyenPaiement> items) {
        List<MoyenPaiementDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (MoyenPaiement item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(MoyenPaiementDto dto, MoyenPaiement t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<MoyenPaiement> copy(List<MoyenPaiementDto> dtos) {
        List<MoyenPaiement> result = new ArrayList<>();
        if (dtos != null) {
            for (MoyenPaiementDto dto : dtos) {
                MoyenPaiement instance = new MoyenPaiement();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
