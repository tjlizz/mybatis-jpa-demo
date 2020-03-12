package lz.cim.api.jpa.project.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lz.cim.api.jpa.attchment.model.AttachmentModel;

import javax.persistence.*;
import java.util.Date;

@ApiModel(value = "模型类")
@Entity
@Table(name = "CIM_PROJECT_MODEL")
public class ProjectModel {
    public ProjectModel() {
        this.version = 1;
    }

    @Id
    @Column(name = "ID")
    private String id;
    @ApiModelProperty(value = "工程编码")
    @Column(name = "PROJECTCODE")
    private String projectCode;
    @Column(name = "LAYERCODE")
    private String layerCode;
    @Column(name = "URL")
    private String url;
    @Column(name = "USERID")
    private String userId;
    @Column(name = "CREATETIME")
    private Date createTime;
    @Column(name = "SUBCODENAME")
    private String subCodeName;
    @Column(name = "IMAGEDATE")
    private Date imageDate;
    @Column(name = "BUILDINGCODE")
    private String buildingCode;
    @Column(name = "SPECIALITY")
    private String speciality;

    @Column(name = "VERSION")
    private Integer version;


    @JoinColumn(name = "ATTCHMENTID")
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    private AttachmentModel attachment;

    public AttachmentModel getAttachment() {
        return attachment;
    }

    public void setAttachment(AttachmentModel attachment) {
        this.attachment = attachment;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getLayerCode() {
        return layerCode;
    }

    public void setLayerCode(String layerCode) {
        this.layerCode = layerCode;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getSubCodeName() {
        return subCodeName;
    }

    public void setSubCodeName(String subCodeName) {
        this.subCodeName = subCodeName;
    }

    public Date getImageDate() {
        return imageDate;
    }

    public void setImageDate(Date imageDate) {
        this.imageDate = imageDate;
    }

    public String getBuildingCode() {
        return buildingCode;
    }

    public void setBuildingCode(String buildingCode) {
        this.buildingCode = buildingCode;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }


}
