package spring.boot.structure.config.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "ipcountry")
public class IpCountry {
    @Column(name = "countrySHORT", columnDefinition = "char(2)")
    String countryShort;
    @Column(name = "countryLONG", columnDefinition = "varchar(255)")
    String countryLong;
    @EmbeddedId
    private IpCountryId ipCountryId;

}
