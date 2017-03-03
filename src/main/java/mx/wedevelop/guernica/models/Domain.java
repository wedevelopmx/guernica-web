package mx.wedevelop.guernica.models;

import java.util.Date;

/**
 * Created by colorado on 3/03/17.
 */
public interface Domain {
    Integer getId();
    void setId(Integer id);
    Integer getVersion();
    void setVersion(Integer version);
    Date getCreatedDate();
    void setCreatedDate(Date createdDate);
    Date getUpdatedDate();
    void setUpdatedDate(Date updatedDate);
}
