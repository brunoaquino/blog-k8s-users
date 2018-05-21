package com.netshoes.blog.users.domains;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;


@Getter
@Setter
@RedisHash("User")
public class User implements Serializable {
    @Id
    private String username;
    private String name;

}
