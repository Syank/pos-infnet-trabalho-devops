package rgba.SkillShare.control;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/livenessProbe")
public class KubernetesLivenessProbeController {

    @RequestMapping("/isAlive")
    public ResponseEntity<Void> isAlive() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
