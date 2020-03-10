package lz.cim.api.jpa.demo.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "CIM_ATTACHMENT")
public class Attachment {
    @Id
    @Column(name = "ID")
    private String id;
    @Column(name = "FILENAME")
    private String fileName;
    @Column(name = "OLDNAME")
    private String oldName;
    @Column(name = "FILEPATH")
    private String filePath;

    @Column(name = "CREATETIME")
    private Date createTime;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL} )
    @JoinColumn(name = "USERID")
    private UserModel create;

    public UserModel getCreate() {
        return create;
    }

    public void setCreate(UserModel create) {
        this.create = create;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getOldName() {
        return oldName;
    }

    public void setOldName(String oldName) {
        this.oldName = oldName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }


    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}