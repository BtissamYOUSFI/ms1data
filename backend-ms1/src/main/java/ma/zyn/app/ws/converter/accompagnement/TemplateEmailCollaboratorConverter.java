package ma.zyn.app.ws.converter.accompagnement;

import ma.zyn.app.bean.core.accompagnement.TemplateEmailCollaborator;
import ma.zyn.app.ws.dto.accompagnement.TemplateEmailCollaboratorDto;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;
import ma.zyn.app.zynerator.util.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TemplateEmailCollaboratorConverter {



    public TemplateEmailCollaborator toItem(TemplateEmailCollaboratorDto dto) {
        if (dto == null) {
            return null;
        } else {
        TemplateEmailCollaborator item = new TemplateEmailCollaborator();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCollaborator()))
                item.setCollaborator(dto.getCollaborator());
            if(StringUtil.isNotEmpty(dto.getSubject()))
                item.setSubject(dto.getSubject());
            if(StringUtil.isNotEmpty(dto.getBody()))
                item.setBody(dto.getBody());



        return item;
        }
    }


    public TemplateEmailCollaboratorDto toDto(TemplateEmailCollaborator item) {
        if (item == null) {
            return null;
        } else {
            TemplateEmailCollaboratorDto dto = new TemplateEmailCollaboratorDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getCollaborator()))
                dto.setCollaborator(item.getCollaborator());
            if(StringUtil.isNotEmpty(item.getSubject()))
                dto.setSubject(item.getSubject());
            if(StringUtil.isNotEmpty(item.getBody()))
                dto.setBody(item.getBody());


        return dto;
        }
    }


	
    public List<TemplateEmailCollaborator> toItem(List<TemplateEmailCollaboratorDto> dtos) {
        List<TemplateEmailCollaborator> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (TemplateEmailCollaboratorDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<TemplateEmailCollaboratorDto> toDto(List<TemplateEmailCollaborator> items) {
        List<TemplateEmailCollaboratorDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (TemplateEmailCollaborator item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(TemplateEmailCollaboratorDto dto, TemplateEmailCollaborator t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<TemplateEmailCollaborator> copy(List<TemplateEmailCollaboratorDto> dtos) {
        List<TemplateEmailCollaborator> result = new ArrayList<>();
        if (dtos != null) {
            for (TemplateEmailCollaboratorDto dto : dtos) {
                TemplateEmailCollaborator instance = new TemplateEmailCollaborator();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
