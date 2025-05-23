package ma.zyn.app.ws.facade.open.profil;

import io.swagger.v3.oas.annotations.Operation;
import ma.zyn.app.bean.core.profil.NiveauLangue;
import ma.zyn.app.dao.criteria.core.profil.NiveauLangueCriteria;
import ma.zyn.app.service.facade.admin.profil.NiveauLangueAdminService;
import ma.zyn.app.ws.converter.profil.NiveauLangueConverter;
import ma.zyn.app.ws.dto.profil.NiveauLangueDto;
import ma.zyn.app.zynerator.util.PaginatedList;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/open/niveauLangue/")
public class NiveauLangueRestOpen {




    @Operation(summary = "Finds a list of all niveauLangues")
    @GetMapping("")
    public ResponseEntity<List<NiveauLangueDto>> findAll() throws Exception {
        ResponseEntity<List<NiveauLangueDto>> res = null;
        List<NiveauLangue> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<NiveauLangueDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all niveauLangues")
    @GetMapping("optimized")
    public ResponseEntity<List<NiveauLangueDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<NiveauLangueDto>> res = null;
        List<NiveauLangue> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<NiveauLangueDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a niveauLangue by id")
    @GetMapping("id/{id}")
    public ResponseEntity<NiveauLangueDto> findById(@PathVariable Long id) {
        NiveauLangue t = service.findById(id);
        if (t != null) {
            NiveauLangueDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a niveauLangue by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<NiveauLangueDto> findByLibelle(@PathVariable String libelle) {
	    NiveauLangue t = service.findByReferenceEntity(new NiveauLangue(libelle));
        if (t != null) {
            NiveauLangueDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  niveauLangue")
    @PostMapping("")
    public ResponseEntity<NiveauLangueDto> save(@RequestBody NiveauLangueDto dto) throws Exception {
        if(dto!=null){
            NiveauLangue myT = converter.toItem(dto);
            NiveauLangue t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                NiveauLangueDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  niveauLangue")
    @PutMapping("")
    public ResponseEntity<NiveauLangueDto> update(@RequestBody NiveauLangueDto dto) throws Exception {
        ResponseEntity<NiveauLangueDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            NiveauLangue t = service.findById(dto.getId());
            converter.copy(dto,t);
            NiveauLangue updated = service.update(t);
            NiveauLangueDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of niveauLangue")
    @PostMapping("multiple")
    public ResponseEntity<List<NiveauLangueDto>> delete(@RequestBody List<NiveauLangueDto> dtos) throws Exception {
        ResponseEntity<List<NiveauLangueDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<NiveauLangue> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified niveauLangue")
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


    @Operation(summary = "Finds a niveauLangue and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<NiveauLangueDto> findWithAssociatedLists(@PathVariable Long id) {
        NiveauLangue loaded =  service.findWithAssociatedLists(id);
        NiveauLangueDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds niveauLangues by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<NiveauLangueDto>> findByCriteria(@RequestBody NiveauLangueCriteria criteria) throws Exception {
        ResponseEntity<List<NiveauLangueDto>> res = null;
        List<NiveauLangue> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<NiveauLangueDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated niveauLangues by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody NiveauLangueCriteria criteria) throws Exception {
        List<NiveauLangue> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<NiveauLangueDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets niveauLangue data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody NiveauLangueCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<NiveauLangueDto> findDtos(List<NiveauLangue> list){
        List<NiveauLangueDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<NiveauLangueDto> getDtoResponseEntity(NiveauLangueDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




   public NiveauLangueRestOpen(NiveauLangueAdminService service, NiveauLangueConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final NiveauLangueAdminService service;
    private final NiveauLangueConverter converter;





}
