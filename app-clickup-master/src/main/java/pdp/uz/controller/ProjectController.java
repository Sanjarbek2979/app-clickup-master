package pdp.uz.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pdp.uz.model.ApiResponse;
import pdp.uz.model.AttachMemberPermissionDto;
import pdp.uz.model.ProjectDto;
import pdp.uz.service.ProjectService;


import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/project")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody ProjectDto dto) {
        ApiResponse apiResponse = projectService.create(dto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @PutMapping("/{id}/attach/member")
    public ResponseEntity<?> attachMember(@PathVariable UUID id, @Valid @RequestBody AttachMemberPermissionDto dto) {
        ApiResponse apiResponse = projectService.attachMember(id,dto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @PutMapping("/{id}/delete/member")
    public ResponseEntity<?> deleteMember(@PathVariable UUID id, @RequestParam UUID memberId) {
        ApiResponse apiResponse = projectService.deleteMember(id,memberId);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }
}
