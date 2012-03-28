package ru.leti.alexeeva.shared;

import com.smartgwt.client.widgets.grid.ListGridRecord;
import java.util.Date;

public class AuthorLGRecord extends ListGridRecord {

    public AuthorLGRecord() {
    }

    public AuthorLGRecord(Long id, String fio, Date birthYear) {
        setId(id);
        setFio(fio);
        setBirthYear(birthYear);
    }

    public void setId(Long id) {
        setAttribute("id", id);
    }

    public Long getId() {
        return getAttributeAsLong("id");
    }

    public void setFio(String fio) {
        setAttribute("fio", fio);
    }

    public String getFio() {
        return getAttributeAsString("fio");
    }

    public void setBirthYear(Date birthYear) {
        setAttribute("birthYear", birthYear);
    }
}
