package com.spring.study.upload;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Slf4j
@Controller
@RequestMapping("/spring")
public class SpringUploadController {
    @Value("${file.dir}")
    private String fileDir;

    @GetMapping("/upload")
    public String newFile() {
        return "upload/upload-form";
    }

    @PostMapping("/upload")
    public String saveFile(@RequestParam String itemName,
                           @RequestParam MultipartFile file,
                           HttpServletRequest request) throws IOException {
        log.info("request = {}", request);
        log.info("itemName = {}", itemName);
        log.info("multipartFile = {}", file);

        if(!file.isEmpty()) {
            //업로드 파일명
            String fullPath = fileDir + file.getOriginalFilename();
            log.info("파일 저장 fullPath = {}", fullPath);
            //파일 저장
            file.transferTo(new File(fullPath));
        }
        return "upload/upload-form";
    }
}
