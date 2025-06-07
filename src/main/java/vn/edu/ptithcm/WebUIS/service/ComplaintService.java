package vn.edu.ptithcm.WebUIS.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import vn.edu.ptithcm.WebUIS.domain.entity.FormTemplate;
import vn.edu.ptithcm.WebUIS.repository.FormTemplateRepository;

@Service
@RequiredArgsConstructor
public class ComplaintService {

    private final FormTemplateRepository formTemplateRepository;

    /**
     * Lấy danh sách all form template
     * 
     * @return all form template
     */
    public List<FormTemplate> getAllFormTemplate() {
        return formTemplateRepository.findAll();
    }
}
