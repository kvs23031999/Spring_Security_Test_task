package app.controller;


import app.dto.DocumentDto;
import app.entity.Document;
import app.service.DocumentService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
@Controller
public class AuthController {

    private DocumentService documentService;

    public AuthController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @GetMapping("index")
    public String home() {
        return "index";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @GetMapping("register")
    public String showRegistrationForm(Model model) {
        DocumentDto document = new DocumentDto();
        model.addAttribute("document", document);
        return "register";
    }

    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("document") DocumentDto document,
                               BindingResult result,
                               Model model) {
        Document existing = documentService.findByAuthor(document.getAuthor());
        if (existing != null) {
            result.rejectValue("author", "author error",
                    "The author already exists");
        }
        if (result.hasErrors()) {
            model.addAttribute("document", document);
            return "register";
        }
        documentService.saveDocument(document);
        return "redirect:/register?success";
    }

    @GetMapping("/documents")
    public String listRegisteredDocuments(Model model) {
        List<DocumentDto> documents = documentService.findAllDocuments();
        model.addAttribute("documents", documents);
        return "documents";
    }
}
