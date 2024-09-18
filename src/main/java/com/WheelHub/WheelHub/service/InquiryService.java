package com.WheelHub.WheelHub.service;

import com.WheelHub.WheelHub.dto.InquiryDtos.InquiryDto;
import java.util.List;

public interface InquiryService {

  InquiryDto createInquiry(InquiryDto inquiryDTO);

  InquiryDto getInquiryById(Long id);

  List<InquiryDto> getAllInquiries();

  InquiryDto updateInquiry(Long id, InquiryDto inquiryDTO);

  void deleteInquiry(Long id);
}
