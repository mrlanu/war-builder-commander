package io.lanu.travian.warbuildercommander.models;

import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AttackRequest implements Serializable {
    private String clientId;
    private String attackId;
    private boolean immediately;
    private String villageName;
    private Integer x;
    private Integer y;
    private Integer kindAttack;
    private List<WaveModel> waves;
    private Date time; // format HH:MM:SS
    private long timeCorrection;
}
