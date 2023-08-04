package com.studies.foodorders.domain.models.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tab_groups")
public class Group implements Serializable {

    private static final long serialVersionUID = 1385722726501787970L;

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @JsonIgnore
    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "timestamp")
    private OffsetDateTime createdAt;

    @JsonIgnore
    @UpdateTimestamp
    @Column(columnDefinition = "timestamp")
    private OffsetDateTime updatedAt;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "group_permission",
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id"))
    private List<Permission> permissions = new ArrayList<>();

    public boolean addPermission(Permission permission) {
        return getPermissions().add(permission);
    }

    public boolean deletePermission(Permission permission) {
        return getPermissions().remove(permission);
    }

}
