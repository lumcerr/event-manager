package sk.kasv.robert.eventmanager.service;

import sk.kasv.robert.eventmanager.entity.Location;
import sk.kasv.robert.eventmanager.repository.LocationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationService {

    private final LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public Location createLocation(Location location) {
        return locationRepository.save(location);
    }

    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    public Optional<Location> getLocationById(Long id) {
        return locationRepository.findById(id);
    }

    public List<Location> getLocationsByCity(String city) {
        return locationRepository.findByCityIgnoreCase(city);
    }

    public List<Location> searchLocationsByAddress(String keyword) {
        return locationRepository.findByAddressContainingIgnoreCase(keyword);
    }

    public Optional<Location> updateLocation(Long id, Location updatedLocation) {
        return locationRepository.findById(id).map(location -> {
            location.setAddress(updatedLocation.getAddress());
            location.setCity(updatedLocation.getCity());
            return locationRepository.save(location);
        });
    }

    public void deleteLocation(Long id) {
        locationRepository.deleteById(id);
    }
}
