package sk.kasv.robert.eventmanager.service;

import sk.kasv.robert.eventmanager.entity.Location;
import sk.kasv.robert.eventmanager.repository.LocationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationService {

    private final LocationRepository locationRepository;

    // Constructor Injection
    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    // Create new Location
    public Location createLocation(Location location) {
        return locationRepository.save(location);
    }

    // Retrieve all locations
    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    // Retrieve location by ID
    public Optional<Location> getLocationById(Long id) {
        return locationRepository.findById(id);
    }

    // Retrieve locations by city (case-insensitive)
    public List<Location> getLocationsByCity(String city) {
        return locationRepository.findByCityIgnoreCase(city);
    }

    // Search locations by address keyword (partial match, case-insensitive)
    public List<Location> searchLocationsByAddress(String keyword) {
        return locationRepository.findByAddressContainingIgnoreCase(keyword);
    }

    // Update an existing Location
    public Optional<Location> updateLocation(Long id, Location updatedLocation) {
        return locationRepository.findById(id).map(location -> {
            location.setAddress(updatedLocation.getAddress());
            location.setCity(updatedLocation.getCity());
            return locationRepository.save(location);
        });
    }

    // Delete a location by ID
    public void deleteLocation(Long id) {
        locationRepository.deleteById(id);
    }
}
