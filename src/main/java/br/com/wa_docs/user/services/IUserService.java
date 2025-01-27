package br.com.wa_docs.user.services;

import br.com.wa_docs.user.domains.User;

public interface IUserService {
    User getUserById(Long id);
}
