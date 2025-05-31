package ma.zyn.app.ws.facade.admin.accompagnement;

import io.swagger.v3.oas.annotations.Operation;
import ma.zyn.app.bean.core.accompagnement.TemplateEmailManager;
import ma.zyn.app.dao.criteria.core.accompagnement.TemplateEmailManagerCriteria;
import ma.zyn.app.service.facade.admin.accompagnement.TemplateEmailManagerAdminService;
import ma.zyn.app.ws.converter.accompagnement.TemplateEmailManagerConverter;
import ma.zyn.app.ws.dto.accompagnement.TemplateEmailManagerDto;
import ma.zyn.app.zynerator.util.PaginatedList;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/templateEmailManager/")
public class TemplateEmailManagerRestAdmin {




    @Operation(summary = "Finds a list of all templateEmailManagers")
    @GetMapping("")
    public ResponseEntity<List<TemplateEmailManagerDto>> findAll() throws Exception {
        ResponseEntity<List<TemplateEmailManagerDto>> res = null;
        List<TemplateEmailManager> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<TemplateEmailManagerDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all templateEmailManagers")
    @GetMapping("optimized")
    public ResponseEntity<List<TemplateEmailManagerDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<TemplateEmailManagerDto>> res = null;
        List<TemplateEmailManager> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<TemplateEmailManagerDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a templateEmailManager by id")
    @GetMapping("id/{id}")
    public ResponseEntity<TemplateEmailManagerDto> findById(@PathVariable Long id) {
        TemplateEmailManager t = service.findById(id);
        if (t != null) {
            TemplateEmailManagerDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a templateEmailManager by manager")
    @GetMapping("manager/{manager}")
    public ResponseEntity<TemplateEmailManagerDto> findByManager(@PathVariable String manager) {
	    TemplateEmailManager t = service.findByReferenceEntity(new TemplateEmailManager(manager));
        if (t != null) {
            TemplateEmailManagerDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  templateEmailManager")
    @PostMapping("")
    public ResponseEntity<TemplateEmailManagerDto> save(@RequestBody TemplateEmailManagerDto dto) throws Exception {
        if(dto!=null){
            TemplateEmailManager myT = converter.toItem(dto);
            TemplateEmailManager t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                TemplateEmailManagerDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  templateEmailManager")
    @PutMapping("")
    public ResponseEntity<TemplateEmailManagerDto> update(@RequestBody TemplateEmailManagerDto dto) throws Exception {
        ResponseEntity<TemplateEmailManagerDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            TemplateEmailManager t = service.findById(dto.getId());
            converter.copy(dto,t);
            TemplateEmailManager updated = service.update(t);
            TemplateEmailManagerDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of templateEmailManager")
    @PostMapping("multiple")
    public ResponseEntity<List<TemplateEmailManagerDto>> delete(@RequestBody List<TemplateEmailManagerDto> dtos) throws Exception {
        ResponseEntity<List<TemplateEmailManagerDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<TemplateEmailManager> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified templateEmailManager")
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


    @Operation(summary = "Finds a templateEmailManager and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<TemplateEmailManagerDto> findWithAssociatedLists(@PathVariable Long id) {
        TemplateEmailManager loaded =  service.findWithAssociatedLists(id);
        TemplateEmailManagerDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds templateEmailManagers by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<TemplateEmailManagerDto>> findByCriteria(@RequestBody TemplateEmailManagerCriteria criteria) throws Exception {
        ResponseEntity<List<TemplateEmailManagerDto>> res = null;
        List<TemplateEmailManager> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<TemplateEmailManagerDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated templateEmailManagers by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody TemplateEmailManagerCriteria criteria) throws Exception {
        List<TemplateEmailManager> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<TemplateEmailManagerDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets templateEmailManager data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody TemplateEmailManagerCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<TemplateEmailManagerDto> findDtos(List<TemplateEmailManager> list){
        List<TemplateEmailManagerDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<TemplateEmailManagerDto> getDtoResponseEntity(TemplateEmailManagerDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




   public TemplateEmailManagerRestAdmin(TemplateEmailManagerAdminService service, TemplateEmailManagerConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final TemplateEmailManagerAdminService service;
    private final TemplateEmailManagerConverter converter;





}
