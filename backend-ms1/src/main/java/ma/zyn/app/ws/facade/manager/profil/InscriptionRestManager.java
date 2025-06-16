package ma.zyn.app.ws.facade.manager.profil;

import io.swagger.v3.oas.annotations.Operation;
import ma.zyn.app.bean.core.profil.Inscription;
import ma.zyn.app.dao.criteria.core.profil.InscriptionCriteria;
import ma.zyn.app.service.facade.manager.profil.InscriptionManagerService;
import ma.zyn.app.ws.converter.profil.InscriptionConverter;
import ma.zyn.app.ws.dto.profil.InscriptionDto;
import ma.zyn.app.zynerator.util.PaginatedList;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/manager/inscription/")
public class InscriptionRestManager {




    @Operation(summary = "Finds a list of all inscriptions")
    @GetMapping("")
    public ResponseEntity<List<InscriptionDto>> findAll() throws Exception {
        ResponseEntity<List<InscriptionDto>> res = null;
        List<Inscription> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            converter.initObject(true);
        List<InscriptionDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

//    @Operation(summary = "Finds an optimized list of all inscriptions")
//    @GetMapping("optimized")
//    public ResponseEntity<List<InscriptionDto>> findAllOptimized() throws Exception {
//        ResponseEntity<List<InscriptionDto>> res = null;
//        List<Inscription> list = service.findAllOptimized();
//        HttpStatus status = HttpStatus.NO_CONTENT;
//        converter.initObject(true);
//        List<InscriptionDto> dtos  = converter.toDto(list);
//        if (dtos != null && !dtos.isEmpty())
//            status = HttpStatus.OK;
//        res = new ResponseEntity<>(dtos, status);
//        return res;
//    }

    @Operation(summary = "Finds a inscription by id")
    @GetMapping("id/{id}")
    public ResponseEntity<InscriptionDto> findById(@PathVariable Long id) {
        Inscription t = service.findById(id);
        if (t != null) {
            converter.init(true);
            InscriptionDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

//    @Operation(summary = "Finds a inscription by libelle")
//    @GetMapping("libelle/{libelle}")
//    public ResponseEntity<InscriptionDto> findByLibelle(@PathVariable String libelle) {
//	    Inscription t = new Inscription(libelle);
//        if (t != null) {
//            converter.init(true);
//            InscriptionDto dto = converter.toDto(t);
//            return getDtoResponseEntity(dto);
//        }
//        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//    }

    @Operation(summary = "Saves the specified  inscription")
    @PostMapping("")
    public ResponseEntity<InscriptionDto> save(@RequestBody InscriptionDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            Inscription myT = converter.toItem(dto);
            Inscription t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                InscriptionDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  inscription")
    @PutMapping("")
    public ResponseEntity<InscriptionDto> update(@RequestBody InscriptionDto dto) throws Exception {
        ResponseEntity<InscriptionDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Inscription t = service.findById(dto.getId());
            converter.copy(dto,t);
            Inscription updated = service.update(t);
            InscriptionDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of inscription")
    @PostMapping("multiple")
    public ResponseEntity<List<InscriptionDto>> delete(@RequestBody List<InscriptionDto> dtos) throws Exception {
        ResponseEntity<List<InscriptionDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<Inscription> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified inscription")
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

    @Operation(summary = "find by langue code")
    @GetMapping("langue/code/{code}")
    public List<InscriptionDto> findByLangueCode(@PathVariable String code){
        return findDtos(service.findByLangueCode(code));
    }
    @Operation(summary = "delete by langue code")
    @DeleteMapping("langue/code/{code}")
    public int deleteByLangueCode(@PathVariable String code){
        return service.deleteByLangueCode(code);
    }
    @Operation(summary = "find by niveauLangue code")
    @GetMapping("niveauLangue/code/{code}")
    public List<InscriptionDto> findByNiveauLangueCode(@PathVariable String code){
        return findDtos(service.findByNiveauLangueCode(code));
    }
    @Operation(summary = "delete by niveauLangue code")
    @DeleteMapping("niveauLangue/code/{code}")
    public int deleteByNiveauLangueCode(@PathVariable String code){
        return service.deleteByNiveauLangueCode(code);
    }
    @Operation(summary = "find by collaborateur id")
    @GetMapping("collaborateur/id/{id}")
    public List<InscriptionDto> findByCollaborateurId(@PathVariable Long id){
        return findDtos(service.findByCollaborateurId(id));
    }
    @Operation(summary = "delete by collaborateur id")
    @DeleteMapping("collaborateur/id/{id}")
    public int deleteByCollaborateurId(@PathVariable Long id){
        return service.deleteByCollaborateurId(id);
    }

    @Operation(summary = "Finds a inscription and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<InscriptionDto> findWithAssociatedLists(@PathVariable Long id) {
        Inscription loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        InscriptionDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds inscriptions by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<InscriptionDto>> findByCriteria(@RequestBody InscriptionCriteria criteria) throws Exception {
        ResponseEntity<List<InscriptionDto>> res = null;
        List<Inscription> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<InscriptionDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated inscriptions by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody InscriptionCriteria criteria) throws Exception {
        List<Inscription> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<InscriptionDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets inscription data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody InscriptionCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<InscriptionDto> findDtos(List<Inscription> list){
        converter.initObject(true);
        List<InscriptionDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<InscriptionDto> getDtoResponseEntity(InscriptionDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




   public InscriptionRestManager(InscriptionManagerService service, InscriptionConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final InscriptionManagerService service;
    private final InscriptionConverter converter;





}
