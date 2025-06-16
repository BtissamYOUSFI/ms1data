package ma.zyn.app.ws.facade.manager.transaction;

import io.swagger.v3.oas.annotations.Operation;
import ma.zyn.app.bean.core.transaction.StatusPaiement;
import ma.zyn.app.dao.criteria.core.transaction.StatusPaiementCriteria;
import ma.zyn.app.service.facade.manager.transaction.StatusPaiementManagerService;
import ma.zyn.app.ws.converter.transaction.StatusPaiementConverter;
import ma.zyn.app.ws.dto.transaction.StatusPaiementDto;
import ma.zyn.app.zynerator.util.PaginatedList;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/manager/statusPaiement/")
public class StatusPaiementRestManager {




    @Operation(summary = "Finds a list of all statusPaiements")
    @GetMapping("")
    public ResponseEntity<List<StatusPaiementDto>> findAll() throws Exception {
        ResponseEntity<List<StatusPaiementDto>> res = null;
        List<StatusPaiement> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<StatusPaiementDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all statusPaiements")
    @GetMapping("optimized")
    public ResponseEntity<List<StatusPaiementDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<StatusPaiementDto>> res = null;
        List<StatusPaiement> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<StatusPaiementDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a statusPaiement by id")
    @GetMapping("id/{id}")
    public ResponseEntity<StatusPaiementDto> findById(@PathVariable Long id) {
        StatusPaiement t = service.findById(id);
        if (t != null) {
            StatusPaiementDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a statusPaiement by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<StatusPaiementDto> findByLibelle(@PathVariable String libelle) {
	    StatusPaiement t = service.findByReferenceEntity(new StatusPaiement(libelle));
        if (t != null) {
            StatusPaiementDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  statusPaiement")
    @PostMapping("")
    public ResponseEntity<StatusPaiementDto> save(@RequestBody StatusPaiementDto dto) throws Exception {
        if(dto!=null){
            StatusPaiement myT = converter.toItem(dto);
            StatusPaiement t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                StatusPaiementDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  statusPaiement")
    @PutMapping("")
    public ResponseEntity<StatusPaiementDto> update(@RequestBody StatusPaiementDto dto) throws Exception {
        ResponseEntity<StatusPaiementDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            StatusPaiement t = service.findById(dto.getId());
            converter.copy(dto,t);
            StatusPaiement updated = service.update(t);
            StatusPaiementDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of statusPaiement")
    @PostMapping("multiple")
    public ResponseEntity<List<StatusPaiementDto>> delete(@RequestBody List<StatusPaiementDto> dtos) throws Exception {
        ResponseEntity<List<StatusPaiementDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<StatusPaiement> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified statusPaiement")
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


    @Operation(summary = "Finds a statusPaiement and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<StatusPaiementDto> findWithAssociatedLists(@PathVariable Long id) {
        StatusPaiement loaded =  service.findWithAssociatedLists(id);
        StatusPaiementDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds statusPaiements by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<StatusPaiementDto>> findByCriteria(@RequestBody StatusPaiementCriteria criteria) throws Exception {
        ResponseEntity<List<StatusPaiementDto>> res = null;
        List<StatusPaiement> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<StatusPaiementDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated statusPaiements by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody StatusPaiementCriteria criteria) throws Exception {
        List<StatusPaiement> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<StatusPaiementDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets statusPaiement data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody StatusPaiementCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<StatusPaiementDto> findDtos(List<StatusPaiement> list){
        List<StatusPaiementDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<StatusPaiementDto> getDtoResponseEntity(StatusPaiementDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




   public StatusPaiementRestManager(StatusPaiementManagerService service, StatusPaiementConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final StatusPaiementManagerService service;
    private final StatusPaiementConverter converter;





}
