package com.example.car.services.impl;

import com.example.car.customExceptions.DataNotFoundException;
import com.example.car.dto.HopDongChoThueRequestDTO;
import com.example.car.dto.SearchingHopDongChoThueDto;
import com.example.car.entities.HopDongChoThue;
import com.example.car.entities.NhanVien;
import com.example.car.entities.Oto;
import com.example.car.entities.QuanLy;
import com.example.car.enums.HopDongChoThueStatus;
import com.example.car.enums.OtoStatus;
import com.example.car.repositories.HopDongChoThueRepository;
import com.example.car.repositories.NhanVienRepository;
import com.example.car.repositories.OtoRepository;
import com.example.car.repositories.QuanLyRepository;
import com.example.car.services.IHopDongChoThueService;
import com.example.car.specifications.HopDongChoThueSpecifications;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class HopDongChoThueService implements IHopDongChoThueService {

    private OtoRepository otoRepository;
    private QuanLyRepository quanLyRepository;
    private HopDongChoThueRepository hopDongChoThueRepository;

    @Override
    @Transactional
    public HopDongChoThue createHopDongChoThue(HopDongChoThueRequestDTO hopDongChoThueRequestDTO) throws Exception {
        Oto existingOto = otoRepository.findById(hopDongChoThueRequestDTO.getOtoId())
                .orElseThrow(() -> new DataNotFoundException(
                        "Khong tim thay o to co id: " + hopDongChoThueRequestDTO.getOtoId()
                ));
        QuanLy existingQuanLy = quanLyRepository.findById(hopDongChoThueRequestDTO.getQuanLyId())
                .orElseThrow(() -> new DataNotFoundException(
                        "Khong tim thay quan ly co id: " + hopDongChoThueRequestDTO.getQuanLyId()
                ));
        HopDongChoThue hopDongChoThue = HopDongChoThue.builder()
                .trangThai(HopDongChoThueStatus.OK)
                .oto(existingOto)
                .quanLy(existingQuanLy)
                .ngayTao(hopDongChoThueRequestDTO.getNgayThanhToan())
                .phanTramCuaDoiTac(hopDongChoThueRequestDTO.getPhanTramCuaDoiTac())
                .ghiChu(hopDongChoThueRequestDTO.getGhiChu())
                .ngayKetThuc(hopDongChoThueRequestDTO.getNgayKetThuc())
                .ngayBatDau(hopDongChoThueRequestDTO.getNgayBatDau())
                .ghiChu(hopDongChoThueRequestDTO.getGhiChu())
                .build();
        return hopDongChoThueRepository.save(hopDongChoThue);
    }

    @Override
    public HopDongChoThue getHopDongChoThueById(Integer id) throws Exception {
        return hopDongChoThueRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(
                        "Khong tim thay hop dong co id: " + id
                ));
    }

    @Transactional
    @Override
    public HopDongChoThue thanhLyHopDongChoThue(Integer hopDongChoThueId, HopDongChoThueStatus hopDongChoThueStatus) throws Exception {
        HopDongChoThue hopDongChoThue = hopDongChoThueRepository.findById(hopDongChoThueId)
                .orElseThrow(() -> new DataNotFoundException(
                        "Khong tim thay hop dong co id: " + hopDongChoThueId
                ));
        // cập nhật trạng thái của ô tô tương ứng với hợp đồng
        hopDongChoThue.getOto().setTrangThai(OtoStatus.HET_HAN_HOP_DONG);
        Oto oto = otoRepository.save(hopDongChoThue.getOto());
        hopDongChoThue.setOto(oto);
        hopDongChoThue.setTrangThai(hopDongChoThueStatus);
        return hopDongChoThueRepository.save(hopDongChoThue);
    }

    @Override
    public List<HopDongChoThue> findBySearchingHopDongChoThueDto(SearchingHopDongChoThueDto searchingHopDongChoThueDto) throws Exception {

        Specification<HopDongChoThue> result = null;
        if(null != searchingHopDongChoThueDto.getSdtDoiTac() || null != searchingHopDongChoThueDto.getDoiTacId()) {
            result = Specification.where(HopDongChoThueSpecifications.joinDoiTac());
        }
        else if(null != searchingHopDongChoThueDto.getBienSo()) {
            result = Specification.where(HopDongChoThueSpecifications.joinOto());
        }

        result = Specification.where(result)
                .and(HopDongChoThueSpecifications.belongDoiTac(searchingHopDongChoThueDto.getDoiTacId()))
                .and(HopDongChoThueSpecifications.hasBienSo(searchingHopDongChoThueDto.getBienSo()))
                .and(HopDongChoThueSpecifications.equalsHopDongChoThueStatus(searchingHopDongChoThueDto.getTrangThai()))
                .and(HopDongChoThueSpecifications.equalsSdtDoiTac(searchingHopDongChoThueDto.getSdtDoiTac()))
                .and(HopDongChoThueSpecifications.greaterThanOrEqualToDate(searchingHopDongChoThueDto.getNgayBatDau()))
                .and(HopDongChoThueSpecifications.lessThanOrEqualToDate(searchingHopDongChoThueDto.getNgayKetThuc()));

        return hopDongChoThueRepository.findAll(result);
    }
}
