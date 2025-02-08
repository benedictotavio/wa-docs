package br.com.wa_docs.request.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.wa_docs.request.domains.Request;
import br.com.wa_docs.request.dtos.CreateRequestDto;
import br.com.wa_docs.request.dtos.ResponseRequestDto;
import br.com.wa_docs.request.mappers.RequestMapper;
import br.com.wa_docs.request.services.IRequestService;

@RestController
@RequestMapping("/api/v1/request")
public class RequestController {

    private final IRequestService requestService;
    private final RequestMapper requestMapper;

    public RequestController(IRequestService requestService, RequestMapper requestMappers) {
        this.requestService = requestService;
        this.requestMapper = requestMappers;
    }

    @PostMapping()
    public ResponseEntity<String> create(@RequestBody CreateRequestDto request) {
        try {
            this.requestService.save(
                    this.requestMapper.toRequest(request));
            return ResponseEntity.ok("Request created successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error creating request");
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        try {
            this.requestService.delete(id);
            return ResponseEntity.ok("Request deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error deleting request");
        }
    }

    @DeleteMapping("/bulk")
    public ResponseEntity<String> deleteMany(@RequestBody Long[] ids) {
        try {
            this.requestService.deleteMany(ids);
            return ResponseEntity.ok("Request deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error deleting request");
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseRequestDto> getByProject(@PathVariable Long id) {
        try {
            Request request = this.requestService.findById(id);
            return ResponseEntity.ok(
                    this.requestMapper.toResponseRequestDto(request));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
