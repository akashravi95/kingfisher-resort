package com.resort.kingfisher.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.resort.kingfisher.model.TourPackage;
import com.resort.kingfisher.repo.TourPackageRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TourPackageService {

    @Autowired
    private TourPackageRepository tourPackageRepository;

    // Create a new Tour Package
    public TourPackage saveTourPackage(TourPackage tourPackage) {
        return tourPackageRepository.save(tourPackage);
    }

    // Retrieve all Tour Packages
    public List<TourPackage> getAllTourPackages() {
        return tourPackageRepository.findAll();
    }

    // Retrieve a Tour Package by ID
    public Optional<TourPackage> getTourPackageById(Long id) {
        return tourPackageRepository.findById(id);
    }

    // Update an existing Tour Package
    public TourPackage updateTourPackage(Long id, TourPackage updatedTourPackage) {
        // Check if the Tour Package with the given ID exists
        if (tourPackageRepository.existsById(id)) {
            updatedTourPackage.setId(id); // Ensure the ID is set to the correct value
            return tourPackageRepository.save(updatedTourPackage);
        } else {
            throw new IllegalArgumentException("Tour Package with ID " + id + " does not exist.");
        }
    }

    // Delete a Tour Package by ID
    public void deleteTourPackage(Long id) {
        // Check if the Tour Package with the given ID exists
        if (tourPackageRepository.existsById(id)) {
            tourPackageRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Tour Package with ID " + id + " does not exist.");
        }
    }
}
