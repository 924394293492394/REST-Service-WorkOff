package lab6.controller;

import lab6.entity.Makeup;
import lab6.service.MakeupService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/makeups")
public class MakeupController {
    private final MakeupService makeupService;

    public MakeupController(MakeupService makeupService) {
        this.makeupService = makeupService;
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping
    public List<Makeup> getAllMakeups() {
        return makeupService.getAllMakeups();
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<Makeup> getMakeup(@PathVariable Long id) {
        return makeupService.getMakeup(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Makeup> createMakeup(@RequestBody Makeup makeup) {
        return makeupService.createMakeup(makeup);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Makeup> updateMakeup(@PathVariable Long id, @RequestBody Makeup makeup) {
        return makeupService.updateMakeup(id, makeup);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMakeup(@PathVariable Long id) {
        return makeupService.deleteMakeup(id);
    }
}