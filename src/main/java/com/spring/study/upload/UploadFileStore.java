package com.spring.study.upload;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Component //스프링 빈 등록 -> 의존성 주입
public class UploadFileStore {
    //파일 저장 경로
    @Value("${file.dir}")
    private String fileDir;

    public String getFullPath(String filename) {
        //파일 경로와 이름을 합쳐 전체경로 작성
        return fileDir + filename;
    }

    //여러 개의 업로드 파일을 처리
    public List<UploadFile> storeFiles(List<MultipartFile> multipartFiles) throws IOException {
        List<UploadFile> storeFileResult = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            if (!multipartFile.isEmpty()) {
                //각각 저장
                storeFileResult.add(storeFile(multipartFile));
            }
        }
        return storeFileResult;
    }

    //파일 하나 저장
    public UploadFile storeFile(MultipartFile multipartFile) throws IOException {
        if(multipartFile.isEmpty()) {
            return null;
        }
        //사용자가 업로드한 파일 이름
        String originalFileName = multipartFile.getOriginalFilename();
        //고유 이름 생성
        String storeFileName = createStoreFileName(originalFileName);
        //파일 저장
        multipartFile.transferTo(new File(getFullPath(storeFileName)));
        //객체 리턴
        return new UploadFile(originalFileName, storeFileName);
    }

    //서버에 저장할 고유이름(UUID) 생성
    private String createStoreFileName(String originalFilename) {
        String ext = extractExt(originalFilename);
        String uuid = UUID.randomUUID().toString();
        return uuid + "." + ext;
    }

    //확장자만 뽑아냄
    private String extractExt(String originalFilename) {
        int pos = originalFilename.lastIndexOf(".");
        return originalFilename.substring(pos + 1);
    }
}
