package ma.zyn.app.ws.facade.admin.accompagnement;

import io.swagger.v3.oas.annotations.Operation;
import ma.zyn.app.bean.core.accompagnement.TemplateEmailCollaborator;
import ma.zyn.app.dao.criteria.core.accompagnement.TemplateEmailCollaboratorCriteria;
import ma.zyn.app.service.facade.admin.accompagnement.TemplateEmailCollaboratorAdminService;
import ma.zyn.app.ws.converter.accompagnement.TemplateEmailCollaboratorConverter;
import ma.zyn.app.ws.dto.accompagnement.TemplateEmailCollaboratorDto;
import ma.zyn.app.zynerator.util.PaginatedList;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/templateEmailCollaborator/")
public class TemplateEmailCollaboratorRestAdmin {


    @Operation(summary = "Finds a list of all templateEmailCollaborators")
    @GetMapping("")
    public ResponseEntity<List<TemplateEmailCollaboratorDto>> findAll() throws Exception {
        ResponseEntity<List<TemplateEmailCollaboratorDto>> res = null;
        List<TemplateEmailCollaborator> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<TemplateEmailCollaboratorDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all templateEmailCollaborators")
    @GetMapping("optimized")
    public ResponseEntity<List<TemplateEmailCollaboratorDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<TemplateEmailCollaboratorDto>> res = null;
        List<TemplateEmailCollaborator> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<TemplateEmailCollaboratorDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a templateEmailCollaborator by id")
    @GetMapping("id/{id}")
    public ResponseEntity<TemplateEmailCollaboratorDto> findById(@PathVariable Long id) {
        TemplateEmailCollaborator t = service.findById(id);
        if (t != null) {
            TemplateEmailCollaboratorDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a templateEmailCollaborator by collaborator")
    @GetMapping("collaborator/{collaborator}")
    public ResponseEntity<TemplateEmailCollaboratorDto> findByCollaborator(@PathVariable String collaborator) {
	    TemplateEmailCollaborator t = service.findByReferenceEntity(new TemplateEmailCollaborator(collaborator));
        if (t != null) {
            TemplateEmailCollaboratorDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  templateEmailCollaborator")
    @PostMapping("")
    public ResponseEntity<TemplateEmailCollaboratorDto> save(@RequestBody TemplateEmailCollaboratorDto dto) throws Exception {
        if(dto!=null){
            TemplateEmailCollaborator myT = converter.toItem(dto);
            TemplateEmailCollaborator t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                TemplateEmailCollaboratorDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  templateEmailCollaborator")
    @PutMapping("")
    public ResponseEntity<TemplateEmailCollaboratorDto> update(@RequestBody TemplateEmailCollaboratorDto dto) throws Exception {
        ResponseEntity<TemplateEmailCollaboratorDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            TemplateEmailCollaborator t = service.findById(dto.getId());
            converter.copy(dto,t);
            TemplateEmailCollaborator updated = service.update(t);
            TemplateEmailCollaboratorDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of templateEmailCollaborator")
    @PostMapping("multiple")
    public ResponseEntity<List<TemplateEmailCollaboratorDto>> delete(@RequestBody List<TemplateEmailCollaboratorDto> dtos) throws Exception {
        ResponseEntity<List<TemplateEmailCollaboratorDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<TemplateEmailCollaborator> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified templateEmailCollaborator")
    @DeleteMapping("id/{id}")
    public ResponseEntity<Long> deleteById(@PathVariable Long id) throws Exception {
        ResponseEntity<Long> res;
        HttpStatus status = HttpStatus.PRECONDITION_FAILED;
        if (id != null) {
            boolean resultDelete = service.deleteById(id);
            if (resultDelete) {
                status = HttpStatus.OK;
            }
        }
        res = new ResponseEntity<>(id, status);
        return res;
    }


    @Operation(summary = "Finds a templateEmailCollaborator and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<TemplateEmailCollaboratorDto> findWithAssociatedLists(@PathVariable Long id) {
        TemplateEmailCollaborator loaded =  service.findWithAssociatedLists(id);
        TemplateEmailCollaboratorDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds templateEmailCollaborators by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<TemplateEmailCollaboratorDto>> findByCriteria(@RequestBody TemplateEmailCollaboratorCriteria criteria) throws Exception {
        ResponseEntity<List<TemplateEmailCollaboratorDto>> res = null;
        List<TemplateEmailCollaborator> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<TemplateEmailCollaboratorDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated templateEmailCollaborators by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody TemplateEmailCollaboratorCriteria criteria) throws Exception {
        List<TemplateEmailCollaborator> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<TemplateEmailCollaboratorDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets templateEmailCollaborator data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody TemplateEmailCollaboratorCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<TemplateEmailCollaboratorDto> findDtos(List<TemplateEmailCollaborator> list){
        List<TemplateEmailCollaboratorDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<TemplateEmailCollaboratorDto> getDtoResponseEntity(TemplateEmailCollaboratorDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




   public TemplateEmailCollaboratorRestAdmin(TemplateEmailCollaboratorAdminService service, TemplateEmailCollaboratorConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final TemplateEmailCollaboratorAdminService service;
    private final TemplateEmailCollaboratorConverter converter;





}
