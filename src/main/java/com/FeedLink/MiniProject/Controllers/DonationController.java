package com.FeedLink.MiniProject.Controllers;




import com.FeedLink.MiniProject.Entity.Donation;
import com.FeedLink.MiniProject.Repository.DonationRepository;
import com.FeedLink.MiniProject.Service.CloudinaryService;
import com.FeedLink.MiniProject.Service.DonorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "*") // Allow all domains for now
@RequestMapping("/donor")
public class DonationController {

    @Autowired
    private DonorService donationService;

    @Autowired
    private CloudinaryService cloudinaryService;

    @Autowired
    private DonationRepository donationRepository;


    @PostMapping("/addDonation")
    public ResponseEntity<String> addDonation(
            @RequestParam String donorName,
            @RequestParam String mobileNumber,
            @RequestParam String foodDetails,
            @RequestParam String location,
            @RequestParam MultipartFile image) {
        try {
            String imageUrl = cloudinaryService.uploadImage(image);

            Donation donation = new Donation();
            donation.setDonorName(donorName);
            donation.setMobileNo(mobileNumber);
            donation.setFoodDetails(foodDetails);
            donation.setLocation(location);
            donation.setImageUrl(imageUrl);

            donationRepository.save(donation);
            return ResponseEntity.ok("Donation added successfully!");
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Error uploading image");
        }
    }

    @GetMapping("/all")
    public List<Donation> getDonations(@RequestParam(value = "location", required = false) String location) {
        if (location == null || location.isEmpty()) {
            return donationService.getAllDonations();
        } else {
            return donationService.getDonationsByLocation(location);
        }
    }
}