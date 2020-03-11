package lz.cim.api.jpa.attchment.server.impl;

import lz.cim.api.jpa.attchment.model.AttachmentModel;
import lz.cim.api.jpa.attchment.repository.AttachmentRepository;
import lz.cim.api.jpa.attchment.server.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttachmentServiceImpl implements AttachmentService {

    @Autowired
    AttachmentRepository attachmentRepository;


    @Override
    public AttachmentModel save(AttachmentModel attachmentModel) {
        return attachmentRepository.save(attachmentModel);
    }

    @Override
    public AttachmentModel update(AttachmentModel attachmentModel) {
        return attachmentRepository.saveAndFlush(attachmentModel);
    }

    @Override
    public List<AttachmentModel> getByKey(String key) {
        return attachmentRepository.findAllByKey(key);
    }

    @Override
    public AttachmentModel getById(String id) {
        List<AttachmentModel> attachmentModels = attachmentRepository.findAllById(id);

        if (attachmentModels.size() > 0) return attachmentModels.get(0);
        return null;
    }
}
