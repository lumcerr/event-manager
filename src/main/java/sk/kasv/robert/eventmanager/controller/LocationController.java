package sk.kasv.robert.eventmanager.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.kasv.robert.eventmanager.entity.Location;
import sk.kasv.robert.eventmanager.repository.LocationRepository;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/locations")
public class LocationController {

    private final LocationRepository locationRepository;

    public LocationController(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @GetMapping
    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Location> getLocationById(@PathVariable Long id) {
        Optional<Location> location = locationRepository.findById(id);
        return location.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(
            summary = "Create new location",
            requestBody = @RequestBody(
                    required = true,
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(example = "{\n  \"address\": \"123 Main St\",\n  \"city\": \"Springfield\"\n}")
                    )
            )
    )
    public ResponseEntity<Location> createLocation(@RequestBody Map<String, String> body) {
        String address = body.get("address");
        String city = body.get("city");
        Location location = new Location();
        location.setAddress(address);
        location.setCity(city);
        return ResponseEntity.ok(locationRepository.save(location));
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Update location",
            requestBody = @RequestBody(
                    required = true,
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(example = "{\n  \"address\": \"456 Oak Ave\",\n  \"city\": \"Shelbyville\"\n}")
                    )
            )
    )
    public ResponseEntity<Location> updateLocation(@PathVariable Long id, @RequestBody Map<String, String> body) {
        Optional<Location> optionalLocation = locationRepository.findById(id);
        if (optionalLocation.isPresent()) {
            Location location = optionalLocation.get();
            location.setAddress(body.get("address"));
            location.setCity(body.get("city"));
            return ResponseEntity.ok(locationRepository.save(location));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLocation(@PathVariable Long id) {
        if (locationRepository.existsById(id)) {
            locationRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
