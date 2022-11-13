package app.example.demo.entity;

import lombok.Data;

@Data
public class User {
  private Integer id;
  private Integer role_id;
  private String username;
  private String password;
  private String nickName;
}
