package lz.cim.api.jpa.attchment.server;

import lz.cim.api.jpa.attchment.model.AttachmentModel;

import java.util.List;

public interface AttachmentService {

    AttachmentModel save(AttachmentModel attachmentModel);

    AttachmentModel update(AttachmentModel attachmentModel);

    List<AttachmentModel> getByKey(String key);

    AttachmentModel getById(String key);


}
