package com.netshoes.blog.users.http;

import com.netshoes.blog.users.domains.User;
import com.netshoes.blog.users.usecases.UserCrud;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UsersController {
    private final UserCrud userCrud;

    private UserDTO convertUser(final User user) {
        final UserDTO userDTO = new UserDTO();
        userDTO.setName(user.getName());
        return userDTO;
    }

    private User convertUserDTO(final @PathVariable("username") String username, final @RequestBody UserDTO userDTO) {
        final User user = new User();
        user.setUsername(username);
        user.setName(userDTO.getName());
        return user;
    }

    @GetMapping(value = "/{username}")
    private UserDTO find(@PathVariable("username") final String username) {
        final User user = userCrud.find(username);
        return convertUser(user);
    }

    @DeleteMapping(value = "/{username}")
    private void delete(@PathVariable("username") final String username) {
        userCrud.delete(username);
    }

    @PostMapping(value = "/{username}")
    private void create(@PathVariable("username") final String username, @RequestBody UserDTO userDTO) {
        final User user = convertUserDTO(username, userDTO);
        userCrud.create(user);
    }

    @PutMapping(value = "/{username}")
    private UserDTO update(@PathVariable("username") final String username, @RequestBody UserDTO userDTO) {
        final User user = convertUserDTO(username, userDTO);
        final User update = userCrud.update(user);
        return convertUser(update);
    }

}
