package lz.cim.api.jpa.attchment.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lz.cim.api.core.view.ReslutView;
import lz.cim.api.jpa.attchment.model.AttachmentModel;
import lz.cim.api.jpa.attchment.server.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "附件服务")
@RestController
@RequestMapping("/api/attachment")
public class AttachmentController {


    @Autowired
    AttachmentService attachmentService;


    @ApiOperation("根据MD5查询文件")
    @RequestMapping(path = {"/{key}"}, method = RequestMethod.GET)
    public ResponseEntity<?> getByKey(@PathVariable("key") String key) {
        ReslutView reslutView = new ReslutView();
        List<AttachmentModel> attachmentModels = attachmentService.getByKey(key);
        reslutView.setData(attachmentModels.size());
        return ResponseEntity.status(HttpStatus.OK).body(reslutView);
    }


}
