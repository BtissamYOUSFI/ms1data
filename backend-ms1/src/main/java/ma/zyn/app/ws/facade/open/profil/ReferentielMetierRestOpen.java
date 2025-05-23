package ma.zyn.app.ws.facade.open.profil;

import io.swagger.v3.oas.annotations.Operation;
import ma.zyn.app.bean.core.profil.ReferentielMetier;
import ma.zyn.app.dao.criteria.core.profil.ReferentielMetierCriteria;
import ma.zyn.app.service.facade.admin.profil.ReferentielMetierAdminService;
import ma.zyn.app.ws.converter.profil.ReferentielMetierConverter;
import ma.zyn.app.ws.dto.profil.ReferentielMetierDto;
import ma.zyn.app.zynerator.util.PaginatedList;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/open/referentielMetier/")
public class ReferentielMetierRestOpen {




    @Operation(summary = "Finds a list of all referentielMetiers")
    @GetMapping("")
    public ResponseEntity<List<ReferentielMetierDto>> findAll() throws Exception {
        ResponseEntity<List<ReferentielMetierDto>> res = null;
        List<ReferentielMetier> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            converter.initObject(true);
        List<ReferentielMetierDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all referentielMetiers")
    @GetMapping("optimized")
    public ResponseEntity<List<ReferentielMetierDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<ReferentielMetierDto>> res = null;
        List<ReferentielMetier> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<ReferentielMetierDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a referentielMetier by id")
    @GetMapping("id/{id}")
    public ResponseEntity<ReferentielMetierDto> findById(@PathVariable Long id) {
        ReferentielMetier t = service.findById(id);
        if (t != null) {
            converter.init(true);
            ReferentielMetierDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a referentielMetier by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<ReferentielMetierDto> findByLibelle(@PathVariable String libelle) {
	    ReferentielMetier t = service.findByReferenceEntity(new ReferentielMetier(libelle));
        if (t != null) {
            converter.init(true);
            ReferentielMetierDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  referentielMetier")
    @PostMapping("")
    public ResponseEntity<ReferentielMetierDto> save(@RequestBody ReferentielMetierDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            ReferentielMetier myT = converter.toItem(dto);
            ReferentielMetier t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                ReferentielMetierDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  referentielMetier")
    @PutMapping("")
    public ResponseEntity<ReferentielMetierDto> update(@RequestBody ReferentielMetierDto dto) throws Exception {
        ResponseEntity<ReferentielMetierDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            ReferentielMetier t = service.findById(dto.getId());
            converter.copy(dto,t);
            ReferentielMetier updated = service.update(t);
            ReferentielMetierDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of referentielMetier")
    @PostMapping("multiple")
    public ResponseEntity<List<ReferentielMetierDto>> delete(@RequestBody List<ReferentielMetierDto> dtos) throws Exception {
        ResponseEntity<List<ReferentielMetierDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<ReferentielMetier> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified referentielMetier")
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
    public List<ReferentielMetierDto> findByLangueCode(@PathVariable String code){
        return findDtos(service.findByLangueCode(code));
    }
    @Operation(summary = "delete by langue code")
    @DeleteMapping("langue/code/{code}")
    public int deleteByLangueCode(@PathVariable String code){
        return service.deleteByLangueCode(code);
    }

    @Operation(summary = "Finds a referentielMetier and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<ReferentielMetierDto> findWithAssociatedLists(@PathVariable Long id) {
        ReferentielMetier loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        ReferentielMetierDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds referentielMetiers by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<ReferentielMetierDto>> findByCriteria(@RequestBody ReferentielMetierCriteria criteria) throws Exception {
        ResponseEntity<List<ReferentielMetierDto>> res = null;
        List<ReferentielMetier> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<ReferentielMetierDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated referentielMetiers by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody ReferentielMetierCriteria criteria) throws Exception {
        List<ReferentielMetier> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<ReferentielMetierDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets referentielMetier data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody ReferentielMetierCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<ReferentielMetierDto> findDtos(List<ReferentielMetier> list){
        converter.initObject(true);
        List<ReferentielMetierDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<ReferentielMetierDto> getDtoResponseEntity(ReferentielMetierDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




   public ReferentielMetierRestOpen(ReferentielMetierAdminService service, ReferentielMetierConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final ReferentielMetierAdminService service;
    private final ReferentielMetierConverter converter;





}
