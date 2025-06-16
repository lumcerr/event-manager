package sk.kasv.robert.eventmanager.controller;

import sk.kasv.robert.eventmanager.entity.Location;
import sk.kasv.robert.eventmanager.service.LocationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/locations")
public class LocationController {

    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping
    public ResponseEntity<List<Location>> getAllLocations() {
        List<Location> locations = locationService.getAllLocations();
        return ResponseEntity.ok(locations);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Location> getLocationById(@PathVariable Long id) {
        Optional<Location> locationOptional = locationService.getLocationById(id);
        return locationOptional.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/city")
    public ResponseEntity<List<Location>> getLocationsByCity(@RequestParam String name) {
        List<Location> locations = locationService.getLocationsByCity(name);
        return ResponseEntity.ok(locations);
    }

    @GetMapping("/address")
    public ResponseEntity<List<Location>> searchLocationsByAddress(@RequestParam String keyword) {
        List<Location> locations = locationService.searchLocationsByAddress(keyword);
        return ResponseEntity.ok(locations);
    }

    @PostMapping
    public ResponseEntity<Location> createLocation(@RequestBody Location location) {
        Location createdLocation = locationService.createLocation(location);
        return new ResponseEntity<>(createdLocation, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Location> updateLocation(@PathVariable Long id, @RequestBody Location location) {
        Optional<Location> updatedLocation = locationService.updateLocation(id, location);
        return updatedLocation.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLocation(@PathVariable Long id) {
        locationService.deleteLocation(id);
        return ResponseEntity.noContent().build();
    }
}
