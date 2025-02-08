package br.com.wa_docs.folder.domains;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "folders")
public class Folder {
    private String name;
    private String path;
}
