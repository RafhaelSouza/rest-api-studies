package com.studies.foodorders.domain.models.localization;

import com.studies.foodorders.core.validation.Groups;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;
import java.io.Serializable;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tab_cities")
public class City implements Serializable {

    private static final long serialVersionUID = -841771059752526724L;

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @Valid
    @ConvertGroup(from = Default.class, to = Groups.StateId.class)
    @NotNull
    @ManyToOne
    @JoinColumn(name = "state_id", nullable = false)
    private State state;

}
