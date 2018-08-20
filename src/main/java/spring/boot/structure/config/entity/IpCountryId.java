package spring.boot.structure.config.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
public class IpCountryId implements Serializable {
    //define two primary keys
    @Column(name = "ipFROM")
    private Long ipFrom;
    @Column(name = "ipTO")
    private long ipTo;

    public IpCountryId(Long ipFrom, Long ipTo) {
        this.ipFrom = ipFrom;
        this.ipTo = ipTo;
    }

}
