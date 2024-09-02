package app.service;

import app.dto.DocumentDto;
import app.entity.Document;

import java.util.List;

public interface DocumentService {


    void saveDocument(DocumentDto documentDto);

    Document findByAuthor(String author);

    List<DocumentDto> findAllDocuments();
}
