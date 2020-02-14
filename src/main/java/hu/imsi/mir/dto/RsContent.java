package hu.imsi.mir.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RsContent extends RsResponse{

    private Integer id;
    private String name;
    private String uuid;
    private String type;
    private String description;
    private String contentUrl;
    private String internalUrl;

}