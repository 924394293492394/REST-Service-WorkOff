package api.management.WorkOff.controllers;

import api.management.WorkOff.models.Makeup;
import api.management.WorkOff.services.MakeupService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/makeups")
public class MakeupController {
    private final MakeupService makeupService;

    public MakeupController(MakeupService makeupService) {
        this.makeupService = makeupService;
    }

    @GetMapping
    public List<Makeup> getAllMakeups() {
        return makeupService.getAllMakeups();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Makeup> getMakeup(@PathVariable Long id) {
        return makeupService.getMakeup(id);
    }

    @PostMapping
    public ResponseEntity<Makeup> createMakeup(@RequestBody Makeup makeup) {
        return makeupService.createMakeup(makeup);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Makeup> updateMakeup(@PathVariable Long id, @RequestBody Makeup makeup) {
        return makeupService.updateMakeup(id, makeup);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMakeup(@PathVariable Long id) {
        return makeupService.deleteMakeup(id);
    }
}
