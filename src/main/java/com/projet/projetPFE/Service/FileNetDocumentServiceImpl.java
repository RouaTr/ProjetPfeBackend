package com.projet.projetPFE.Service;
import com.filenet.api.collection.ContentElementList;
import com.filenet.api.constants.AutoClassify;
import com.filenet.api.constants.CheckinType;
import com.filenet.api.constants.ClassNames;
import com.filenet.api.constants.RefreshMode;
import com.filenet.api.core.ContentTransfer;
import com.filenet.api.core.Domain;
import com.filenet.api.core.Factory;
import com.filenet.api.util.UserContext;
import com.filenet.api.core.ObjectStore;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.filenet.api.core.Connection;
import javax.security.auth.Subject;
import com.filenet.api.core.Document;

import java.io.InputStream;

@Service
public class FileNetDocumentServiceImpl implements FileNetDocumentService{
    @Override
    public void uploadToFileNet(MultipartFile file, String title) throws Exception {
        // Connexion à FileNet
        Connection conn = Factory.Connection.getConnection("http://192.168.56.101:9080/wsi/FNCEWS40MTOM/");
        Subject subject = UserContext.createSubject(conn, "GCD Administrator", "P@ssw0rd", null);
        UserContext.get().pushSubject(subject);

        Domain domain = Factory.Domain.fetchInstance(conn, null, null);
        ObjectStore os = Factory.ObjectStore.fetchInstance(domain, "DemoObjectStore", null);

        // Création du document
        Document doc = Factory.Document.createInstance(os, ClassNames.DOCUMENT);
        doc.getProperties().putValue("DocumentTitle", title);
        doc.set_MimeType(file.getContentType());  // "application/pdf"

        // Contenu du fichier
        ContentTransfer ct = Factory.ContentTransfer.createInstance();
        InputStream inputStream = file.getInputStream();
        ct.setCaptureSource(inputStream);
        ContentElementList contentList = Factory.ContentElement.createList();
        contentList.add(ct);
        doc.set_ContentElements(contentList);

        // Enregistrement dans FileNet
        doc.checkin(AutoClassify.DO_NOT_AUTO_CLASSIFY, CheckinType.MAJOR_VERSION);
        doc.save(RefreshMode.NO_REFRESH);

        UserContext.get().popSubject();
    }
    @Override
    public InputStream downloadDocumentFromFileNet(String documentId) throws Exception {
        // Connexion à FileNet
        Connection conn = Factory.Connection.getConnection("http://192.168.56.101:9080/wsi/FNCEWS40MTOM/");
        Subject subject = UserContext.createSubject(conn, "GCD Administrator", "P@ssw0rd", null);
        UserContext.get().pushSubject(subject);

        // Récupérer l'Object Store
        Domain domain = Factory.Domain.fetchInstance(conn, null, null);
        ObjectStore os = Factory.ObjectStore.fetchInstance(domain, "DemoObjectStore", null);

        // Récupérer le document par son ID
        Document doc = Factory.Document.fetchInstance(os, documentId, null);
        ContentElementList contentList = doc.get_ContentElements();
        ContentTransfer ct = (ContentTransfer) contentList.get(0);

        // Retourner le flux de contenu du document
        return ct.accessContentStream();  // Retourne InputStream
    }
}
