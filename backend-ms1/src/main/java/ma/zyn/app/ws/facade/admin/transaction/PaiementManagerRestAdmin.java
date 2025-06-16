package ma.zyn.app.ws.facade.admin.transaction;

import io.swagger.v3.oas.annotations.Operation;
import ma.zyn.app.bean.core.transaction.PaiementManager;
import ma.zyn.app.dao.criteria.core.transaction.PaiementManagerCriteria;
import ma.zyn.app.service.facade.admin.transaction.PaiementManagerAdminService;
import ma.zyn.app.ws.converter.transaction.PaiementManagerConverter;
import ma.zyn.app.ws.dto.transaction.PaiementManagerDto;
import ma.zyn.app.zynerator.util.PaginatedList;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/paiementManager/")
public class PaiementManagerRestAdmin {




    @Operation(summary = "Finds a list of all paiementManagers")
    @GetMapping("")
    public ResponseEntity<List<PaiementManagerDto>> findAll() throws Exception {
        ResponseEntity<List<PaiementManagerDto>> res = null;
        List<PaiementManager> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            converter.initObject(true);
        List<PaiementManagerDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all paiementManagers")
    @GetMapping("optimized")
    public ResponseEntity<List<PaiementManagerDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<PaiementManagerDto>> res = null;
        List<PaiementManager> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<PaiementManagerDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a paiementManager by id")
    @GetMapping("id/{id}")
    public ResponseEntity<PaiementManagerDto> findById(@PathVariable Long id) {
        PaiementManager t = service.findById(id);
        if (t != null) {
            converter.init(true);
            PaiementManagerDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a paiementManager by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<PaiementManagerDto> findByLibelle(@PathVariable String libelle) {
	    PaiementManager t = service.findByReferenceEntity(new PaiementManager(libelle));
        if (t != null) {
            converter.init(true);
            PaiementManagerDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  paiementManager")
    @PostMapping("")
    public ResponseEntity<PaiementManagerDto> save(@RequestBody PaiementManagerDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            PaiementManager myT = converter.toItem(dto);
            PaiementManager t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                PaiementManagerDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  paiementManager")
    @PutMapping("")
    public ResponseEntity<PaiementManagerDto> update(@RequestBody PaiementManagerDto dto) throws Exception {
        ResponseEntity<PaiementManagerDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            PaiementManager t = service.findById(dto.getId());
            converter.copy(dto,t);
            PaiementManager updated = service.update(t);
            PaiementManagerDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of paiementManager")
    @PostMapping("multiple")
    public ResponseEntity<List<PaiementManagerDto>> delete(@RequestBody List<PaiementManagerDto> dtos) throws Exception {
        ResponseEntity<List<PaiementManagerDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<PaiementManager> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified paiementManager")
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


    @Operation(summary = "Finds a paiementManager and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<PaiementManagerDto> findWithAssociatedLists(@PathVariable Long id) {
        PaiementManager loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        PaiementManagerDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds paiementManagers by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<PaiementManagerDto>> findByCriteria(@RequestBody PaiementManagerCriteria criteria) throws Exception {
        ResponseEntity<List<PaiementManagerDto>> res = null;
        List<PaiementManager> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<PaiementManagerDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated paiementManagers by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody PaiementManagerCriteria criteria) throws Exception {
        List<PaiementManager> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<PaiementManagerDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets paiementManager data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody PaiementManagerCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<PaiementManagerDto> findDtos(List<PaiementManager> list){
        converter.initObject(true);
        List<PaiementManagerDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<PaiementManagerDto> getDtoResponseEntity(PaiementManagerDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




   public PaiementManagerRestAdmin(PaiementManagerAdminService service, PaiementManagerConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final PaiementManagerAdminService service;
    private final PaiementManagerConverter converter;





}
