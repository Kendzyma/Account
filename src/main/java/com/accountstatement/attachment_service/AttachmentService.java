package com.accountstatement.attachment_service;

import java.util.List;

public interface AttachmentService<T> {
        byte[] generate(List<T> data);
}
