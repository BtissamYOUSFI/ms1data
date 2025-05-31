package ma.zyn.app.ws.converter.accompagnement;

import ma.zyn.app.bean.core.accompagnement.TemplateEmailManager;
import ma.zyn.app.ws.dto.accompagnement.TemplateEmailManagerDto;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;
import ma.zyn.app.zynerator.util.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TemplateEmailManagerConverter {



    public TemplateEmailManager toItem(TemplateEmailManagerDto dto) {
        if (dto == null) {
            return null;
        } else {
        TemplateEmailManager item = new TemplateEmailManager();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getManager()))
                item.setManager(dto.getManager());
            if(StringUtil.isNotEmpty(dto.getSubject()))
                item.setSubject(dto.getSubject());
            if(StringUtil.isNotEmpty(dto.getBody()))
                item.setBody(dto.getBody());



        return item;
        }
    }


    public TemplateEmailManagerDto toDto(TemplateEmailManager item) {
        if (item == null) {
            return null;
        } else {
            TemplateEmailManagerDto dto = new TemplateEmailManagerDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getManager()))
                dto.setManager(item.getManager());
            if(StringUtil.isNotEmpty(item.getSubject()))
                dto.setSubject(item.getSubject());
            if(StringUtil.isNotEmpty(item.getBody()))
                dto.setBody(item.getBody());


        return dto;
        }
    }


	
    public List<TemplateEmailManager> toItem(List<TemplateEmailManagerDto> dtos) {
        List<TemplateEmailManager> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (TemplateEmailManagerDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<TemplateEmailManagerDto> toDto(List<TemplateEmailManager> items) {
        List<TemplateEmailManagerDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (TemplateEmailManager item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(TemplateEmailManagerDto dto, TemplateEmailManager t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<TemplateEmailManager> copy(List<TemplateEmailManagerDto> dtos) {
        List<TemplateEmailManager> result = new ArrayList<>();
        if (dtos != null) {
            for (TemplateEmailManagerDto dto : dtos) {
                TemplateEmailManager instance = new TemplateEmailManager();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
