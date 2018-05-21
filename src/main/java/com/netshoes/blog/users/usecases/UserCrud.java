package com.netshoes.blog.users.usecases;

import com.netshoes.blog.users.domains.User;
import com.netshoes.blog.users.exceptions.NotFoundException;
import com.netshoes.blog.users.gateways.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserCrud {

    private final UserRepository repository;

    public User create(final User user) {
        return repository.save(user);
    }

    public void delete(final String id) {
        repository.delete(find(id));
    }

    public User update(final User user) {
        final User existing = find(user.getUsername());
        existing.setName(user.getName());
        return repository.save(existing);
    }

    public User find(final String id) {
        final User existing = repository.findOne(id);
        if (existing == null) {
            throw new NotFoundException();
        }
        return existing;
    }
}
