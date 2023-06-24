package tn.esprit.spring.controller;

import lombok.AllArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.InputStream;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class ImageController {

    @GetMapping("/get-image-dynamic-type")
    @ResponseBody
    public ResponseEntity<InputStreamResource> getImageDynamicType(@RequestParam("jpg") boolean jpg) {
        MediaType contentType = jpg ? MediaType.IMAGE_JPEG : MediaType.IMAGE_PNG;
        InputStream in = jpg ?
                getClass().getResourceAsStream("/images/camping-sample.jpg") :
                getClass().getResourceAsStream("/images/camping-sample-1.jpg");
        return ResponseEntity.ok()
                .contentType(contentType)
                .body(new InputStreamResource(in));
    }

}
