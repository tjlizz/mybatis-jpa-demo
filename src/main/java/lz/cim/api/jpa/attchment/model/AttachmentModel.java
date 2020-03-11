package lz.cim.api.jpa.attchment.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "CIM_ATTACHMENT")
public class AttachmentModel {

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
    @Column(name = "USERID")
    private String userId;

    @Column(name = "KEY")
    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
