package pl.coderslab.charity.donation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/donations")
@RequiredArgsConstructor
public class DonationController {

    private final DonationRepository donationRepository;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Donation> getAll() {
        return donationRepository.findAll();
    }

    @PostMapping
    public ResponseEntity createOne(@Valid @RequestBody Donation donation) {
        donationRepository.save(donation);
//        return ResponseEntity.status(HttpStatus.CREATED).location(URI.create("/api/donations/"
//                + donation.getId())).build();
        return ResponseEntity.created(URI.create("/api/donations/" + donation.getId())).build();
    }
    @GetMapping("/{id}")
    public ResponseEntity getOne(@PathVariable Long id) {
        Optional<Donation> optionalDonation = donationRepository.findById(id);
        if (optionalDonation.isPresent()) {
            return ResponseEntity.ok(optionalDonation.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    // Status 200 OK + stan usuniętego zasobu w treści odpowiedzi
    // Status 204 No Content i bez stanu zasobu w treści odpowiedzi
    public ResponseEntity deleteOne(@PathVariable Long id) {
        return donationRepository.findById(id)
                .map(this::delete)
                .map(i -> ResponseEntity.noContent().build())
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    private Donation delete(Donation donation) {
        donationRepository.delete(donation);
        return donation;
    }

    @PutMapping("/{id}")
    // 200 OK lub 404 Not Found
    public void updateOne(@RequestBody Donation donation, @PathVariable Long id) {

    }

    @PatchMapping("/{id}")
    // 200 OK lub 404 Not Found
    public void updatePartially(@PathVariable Long id, @RequestBody Map<String, Object> changes) {
        ;
    }


}
