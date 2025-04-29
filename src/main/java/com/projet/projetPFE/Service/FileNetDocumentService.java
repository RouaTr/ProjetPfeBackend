package com.projet.projetPFE.Service;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public interface FileNetDocumentService {
    void uploadToFileNet(MultipartFile file, String title) throws Exception;
    InputStream downloadDocumentFromFileNet(String documentId) throws Exception;
}
