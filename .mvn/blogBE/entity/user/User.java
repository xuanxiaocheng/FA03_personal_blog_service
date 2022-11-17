package app.example.blogBE.entity.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class User {
  @ApiModelProperty(value = "id", required = true, hidden = true)
  private Integer id;
  @ApiModelProperty(value = "身份", required = true, hidden = true)
  private Integer role_id;
  private String username;
  private String password;
  @ApiModelProperty(value = "昵称", required = true, hidden = true)
  private String nickName;
}
