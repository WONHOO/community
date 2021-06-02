package life.majiang.community.dto;

import life.majiang.community.model.User;
import lombok.Data;

@Data
public class NotificationDTO {
    private Long id;
    private Long gmtCreate;
    private Integer status;
    private User notifier;
//    private String notifierName;
    private String outerTitle;
//    private Long outerid;
    private String type;
//    private Integer type;
}
