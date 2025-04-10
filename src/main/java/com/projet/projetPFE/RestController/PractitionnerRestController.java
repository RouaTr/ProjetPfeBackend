package com.projet.projetPFE.RestController;

import com.projet.projetPFE.Entities.Practitionner;
import com.projet.projetPFE.Repository.PractitionnerRepository;
import com.projet.projetPFE.Service.PractitionnerService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping(value="/practitionner")
public class PractitionnerRestController {
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Autowired

    PractitionnerRepository practitionnerRepository;

    @Autowired
    PractitionnerService practitionnerService;
    @RequestMapping(method = RequestMethod.POST )
    ResponseEntity<?> addPractitionner(@RequestBody Practitionner practitionner){
        HashMap<String, Object> response = new HashMap<>();
        if(practitionnerRepository.existsByPractitionnerEmail(practitionner.getPractitionnerEmail())){
            response.put("message", "email exist deja !");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }else{
            practitionner.setPassword(this.bCryptPasswordEncoder.encode(practitionner.getPassword()));
            Practitionner savedUser = practitionnerRepository.save(practitionner);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);}

    }
    @RequestMapping(value = "/{id}" ,method = RequestMethod.PUT)
    public Practitionner updatePractitionner(@PathVariable("id")Long id, @RequestBody Practitionner practitionner){
        practitionner.setPassword(this.bCryptPasswordEncoder.encode(practitionner.getPassword()));
        Practitionner savedUser = practitionnerRepository.save(practitionner);

        Practitionner newPractitionner = practitionnerService.updatePractitionner(practitionner);
        return newPractitionner;
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE )

    public void deletePractitionner(@PathVariable("id") Long id){
        practitionnerService.deletePractitionner(id);

    }
    @RequestMapping(method = RequestMethod.GET )
    public List<Practitionner> diplayPractitionner(){
        return practitionnerService.displayPractitionner();

    }


    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> loginPractitionner(@RequestBody Practitionner practitionner) {
        System.out.println("in login-practitionner"+practitionner);
        HashMap<String, Object> response = new HashMap<>();

        Practitionner userFromDB = practitionnerRepository.findPractitionnerByPractitionnerEmail(practitionner.getPractitionnerEmail());
        System.out.println("userFromDB+practitionner"+userFromDB);
        if (userFromDB == null) {
            response.put("message", "Practitionner not found !");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } else {
            boolean compare = this.bCryptPasswordEncoder.matches(practitionner.getPassword(), userFromDB.getPassword());
            System.out.println("compare"+compare);
            if (!compare) {
                response.put("message", "practitionner not found !");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }else
            {
                String token = Jwts.builder()
                        .claim("data", userFromDB)
                        .signWith(SignatureAlgorithm.HS256, "SECRET")
                        .compact();
                response.put("token", token);

                return ResponseEntity.status(HttpStatus.OK).body(response);
            }

        }
    }
  @GetMapping("/exists")
  public ResponseEntity<Boolean> doesPatientExist(@RequestParam String practitionnerLastName, @RequestParam String practitionnerFirstName) {
      boolean exists = practitionnerService.doesPractitionnerExist(practitionnerLastName, practitionnerFirstName);
      return ResponseEntity.ok(exists);
  }
    @RequestMapping(value = "/{id}" , method = RequestMethod.GET)
    public Optional<Practitionner> getPractitionnerById(@PathVariable("id") Long id){

        Optional<Practitionner> practitionner = practitionnerService.displayPractitionnerbyid(id);
        return practitionner;
    }
}
