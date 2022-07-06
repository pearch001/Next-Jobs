package com.NextJobs.NextJobsapi.api;

import com.NextJobs.NextJobsapi.services.StorageService;
import com.NextJobs.NextJobsapi.utils.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RequestMapping("/nextjobs/v1/file")
@RestController
public class FileController {

    @Autowired
    StorageService storageService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    @PostMapping("/picture/upload")
    public ResponseEntity<String> uploadPicture(@RequestParam(value = "file") MultipartFile file,
                                                @RequestHeader(value = "Authorization") String authorizationHeader) {
        String email = jwtTokenUtil.getUsernameFromToken(authorizationHeader.substring(7));
        String filename =  email + "image";
        return new ResponseEntity<>(storageService.uploadFile(file,filename,email), HttpStatus.OK);
    }

    @PostMapping("/cv/upload")
    public ResponseEntity<String> uploadCv(@RequestParam(value = "file") MultipartFile file,
                                           @RequestHeader(value = "Authorization") String authorizationHeader) {
        String email = jwtTokenUtil.getUsernameFromToken(authorizationHeader.substring(7));
        String filename =  email + "CV";
        return new ResponseEntity<>(storageService.uploadFile(file,filename,email), HttpStatus.OK);
    }

    @GetMapping("/downloadCv/{fileName}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable String fileName) {
        byte[] data = storageService.downloadFile(fileName);
        ByteArrayResource resource = new ByteArrayResource(data);
        return ResponseEntity
                .ok()
                .contentLength(data.length)
                .header("Content-type", "application/octet-stream")
                .header("Content-disposition", "attachment; filename=\"" + fileName + "\"")
                .body(resource);
    }



}
