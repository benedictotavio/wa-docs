package br.com.wa_docs.project.dtos;

public record ProjectResponseDefaultDto(
                Long id,
                String message) {

                        public ProjectResponseDefaultDto(String message) {
                                this(null, message);
                        }
}
