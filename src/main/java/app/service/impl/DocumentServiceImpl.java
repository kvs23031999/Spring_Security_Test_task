package app.service.impl;

import app.dto.DocumentDto;
import app.entity.Author;
import app.entity.Document;
import app.repository.AuthorRepository;
import app.repository.DocumentRepository;
import app.service.DocumentService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DocumentServiceImpl implements DocumentService {

    private DocumentRepository documentRepository;
    private AuthorRepository authorRepository;

    private PasswordEncoder passwordEncoder;

    public DocumentServiceImpl(DocumentRepository documentRepository,
                               AuthorRepository authorRepository, PasswordEncoder passwordEncoder) {
        this.documentRepository = documentRepository;
        this.authorRepository = authorRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveDocument(DocumentDto documentDto) {
        Document document = new Document();
        document.setTitle(documentDto.getTitle() + " " + documentDto.getContent());
        document.setAuthor(documentDto.getAuthor());

        Author author = authorRepository.findByName("Author_ADMIN");
        if ( author == null ) {
            author = checkRoleExist();
        }
        document.setAuthors(List.of(author));
        documentRepository.save(document);
    }

    @Override
    public Document findByAuthor(String author) {
        return documentRepository.findByAuthor(author);
    }

    @Override
    public List<DocumentDto> findAllDocuments() {
        List<Document> documents = documentRepository.findAll();
        return documents.stream().map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    private DocumentDto convertEntityToDto(Document document) {
        DocumentDto documentDto = new DocumentDto();
        String[] title = document.getTitle().split("");
        documentDto.setTitle(document.getTitle());
        documentDto.setContent(document.getContent());
        documentDto.setAuthor(document.getAuthor());
        documentDto.setInstant(documentDto.getInstant());
        return documentDto;
    }

    private Author checkRoleExist() {
        Author author = new Author();
        author.setName("Author_ADMIN");
        return authorRepository.save(author);
    }
}
