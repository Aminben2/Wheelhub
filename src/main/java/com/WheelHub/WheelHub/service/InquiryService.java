package com.WheelHub.WheelHub.service;

import com.WheelHub.WheelHub.dto.InquiryDTO;

import java.util.List;

public interface InquiryService {

    InquiryDTO createInquiry(InquiryDTO inquiryDTO);

    InquiryDTO getInquiryById(Long id);

    List<InquiryDTO> getAllInquiries();

    InquiryDTO updateInquiry(Long id, InquiryDTO inquiryDTO);

    void deleteInquiry(Long id);
}
