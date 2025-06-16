package ma.zyn.app.ws.facade.manager.utilisateurs;

import io.swagger.v3.oas.annotations.Operation;
import ma.zyn.app.bean.core.utilisateurs.Manager;
import ma.zyn.app.dao.criteria.core.utilisateurs.ManagerCriteria;
import ma.zyn.app.service.facade.manager.utilisateurs.ManagerManagerService;
import ma.zyn.app.ws.converter.utilisateurs.ManagerConverter;
import ma.zyn.app.ws.dto.utilisateurs.ManagerDto;
import ma.zyn.app.zynerator.security.bean.User;
import ma.zyn.app.zynerator.util.PaginatedList;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/manager/manager/")
public class ManagerRestManager {




    @Operation(summary = "Finds a list of all managers")
    @GetMapping("")
    public ResponseEntity<List<ManagerDto>> findAll() throws Exception {
        ResponseEntity<List<ManagerDto>> res = null;
        List<Manager> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<ManagerDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all managers")
    @GetMapping("optimized")
    public ResponseEntity<List<ManagerDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<ManagerDto>> res = null;
        List<Manager> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<ManagerDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a manager by id")
    @GetMapping("id/{id}")
    public ResponseEntity<ManagerDto> findById(@PathVariable Long id) {
        Manager t = service.findById(id);
        if (t != null) {
            ManagerDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a manager by email")
    @GetMapping("email/{email}")
    public ResponseEntity<ManagerDto> findByEmail(@PathVariable String email) {
	    Manager t = service.findByReferenceEntity(new Manager(email));
        if (t != null) {
            ManagerDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  manager")
    @PostMapping("")
    public ResponseEntity<ManagerDto> save(@RequestBody ManagerDto dto) throws Exception {
        if(dto!=null){
            Manager myT = converter.toItem(dto);
            Manager t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                ManagerDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  manager")
    @PutMapping("")
    public ResponseEntity<ManagerDto> update(@RequestBody ManagerDto dto) throws Exception {
        ResponseEntity<ManagerDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Manager t = service.findById(dto.getId());
            converter.copy(dto,t);
            Manager updated = service.update(t);
            ManagerDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of manager")
    @PostMapping("multiple")
    public ResponseEntity<List<ManagerDto>> delete(@RequestBody List<ManagerDto> dtos) throws Exception {
        ResponseEntity<List<ManagerDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<Manager> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified manager")
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


    @Operation(summary = "Finds a manager and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<ManagerDto> findWithAssociatedLists(@PathVariable Long id) {
        Manager loaded =  service.findWithAssociatedLists(id);
        ManagerDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds managers by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<ManagerDto>> findByCriteria(@RequestBody ManagerCriteria criteria) throws Exception {
        ResponseEntity<List<ManagerDto>> res = null;
        List<Manager> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<ManagerDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated managers by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody ManagerCriteria criteria) throws Exception {
        List<Manager> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<ManagerDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets manager data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody ManagerCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<ManagerDto> findDtos(List<Manager> list){
        List<ManagerDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<ManagerDto> getDtoResponseEntity(ManagerDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }



    @Operation(summary = "Change password to the specified  utilisateur")
    @PutMapping("changePassword")
    public boolean changePassword(@RequestBody User dto) throws Exception {
        return service.changePassword(dto.getUsername(),dto.getPassword());
    }

   public ManagerRestManager(ManagerManagerService service, ManagerConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final ManagerManagerService service;
    private final ManagerConverter converter;





}
