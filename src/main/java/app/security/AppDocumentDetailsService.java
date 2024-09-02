package app.security;


import app.entity.Author;
import app.entity.Document;
import app.repository.DocumentRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class AppDocumentDetailsService implements UserDetailsService{

    private final DocumentRepository documentRepository;

    public AppDocumentDetailsService(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String author) throws UsernameNotFoundException {
        Document document = documentRepository.findByAuthor(author);


        if (document != null) {
            return new org.springframework.security.core.userdetails.User(
                    document.getTitle(),
                    document.getContent(),
                    mapRolesToAuthorities(document.getAuthors()));

        } else {
            throw new UsernameNotFoundException("Invalid login or password.");
        }
    }
    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Author> authors) {
        return authors.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }

}
