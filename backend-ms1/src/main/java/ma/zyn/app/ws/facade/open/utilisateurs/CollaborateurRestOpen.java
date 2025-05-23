package ma.zyn.app.ws.facade.open.utilisateurs;

import io.swagger.v3.oas.annotations.Operation;
import ma.zyn.app.bean.core.utilisateurs.Collaborateur;
import ma.zyn.app.dao.criteria.core.utilisateurs.CollaborateurCriteria;
import ma.zyn.app.service.facade.admin.utilisateurs.CollaborateurAdminService;
import ma.zyn.app.ws.converter.utilisateurs.CollaborateurConverter;
import ma.zyn.app.ws.dto.utilisateurs.CollaborateurDto;
import ma.zyn.app.zynerator.security.bean.User;
import ma.zyn.app.zynerator.util.PaginatedList;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/open/collaborateur/")
public class CollaborateurRestOpen {




    @Operation(summary = "Finds a list of all collaborateurs")
    @GetMapping("")
    public ResponseEntity<List<CollaborateurDto>> findAll() throws Exception {
        ResponseEntity<List<CollaborateurDto>> res = null;
        List<Collaborateur> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<CollaborateurDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all collaborateurs")
    @GetMapping("optimized")
    public ResponseEntity<List<CollaborateurDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<CollaborateurDto>> res = null;
        List<Collaborateur> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<CollaborateurDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a collaborateur by id")
    @GetMapping("id/{id}")
    public ResponseEntity<CollaborateurDto> findById(@PathVariable Long id) {
        Collaborateur t = service.findById(id);
        if (t != null) {
            CollaborateurDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a collaborateur by email")
    @GetMapping("email/{email}")
    public ResponseEntity<CollaborateurDto> findByEmail(@PathVariable String email) {
	    Collaborateur t = service.findByReferenceEntity(new Collaborateur(email));
        if (t != null) {
            CollaborateurDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  collaborateur")
    @PostMapping("")
    public ResponseEntity<CollaborateurDto> save(@RequestBody CollaborateurDto dto) throws Exception {
        if(dto!=null){
            Collaborateur myT = converter.toItem(dto);
            Collaborateur t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                CollaborateurDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  collaborateur")
    @PutMapping("")
    public ResponseEntity<CollaborateurDto> update(@RequestBody CollaborateurDto dto) throws Exception {
        ResponseEntity<CollaborateurDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Collaborateur t = service.findById(dto.getId());
            converter.copy(dto,t);
            Collaborateur updated = service.update(t);
            CollaborateurDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of collaborateur")
    @PostMapping("multiple")
    public ResponseEntity<List<CollaborateurDto>> delete(@RequestBody List<CollaborateurDto> dtos) throws Exception {
        ResponseEntity<List<CollaborateurDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<Collaborateur> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified collaborateur")
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


    @Operation(summary = "Finds a collaborateur and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<CollaborateurDto> findWithAssociatedLists(@PathVariable Long id) {
        Collaborateur loaded =  service.findWithAssociatedLists(id);
        CollaborateurDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds collaborateurs by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<CollaborateurDto>> findByCriteria(@RequestBody CollaborateurCriteria criteria) throws Exception {
        ResponseEntity<List<CollaborateurDto>> res = null;
        List<Collaborateur> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<CollaborateurDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated collaborateurs by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody CollaborateurCriteria criteria) throws Exception {
        List<Collaborateur> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<CollaborateurDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets collaborateur data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody CollaborateurCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<CollaborateurDto> findDtos(List<Collaborateur> list){
        List<CollaborateurDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<CollaborateurDto> getDtoResponseEntity(CollaborateurDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }



    @Operation(summary = "Change password to the specified  utilisateur")
    @PutMapping("changePassword")
    public boolean changePassword(@RequestBody User dto) throws Exception {
        return service.changePassword(dto.getUsername(),dto.getPassword());
    }

   public CollaborateurRestOpen(CollaborateurAdminService service, CollaborateurConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final CollaborateurAdminService service;
    private final CollaborateurConverter converter;





}
