package br.com.wa_docs.folder.domains;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Entity
@Table(name = "folders")
public class Folder {

    @Id
    private Long id;

    private String name;

    // private Folder parentFolder;
}
