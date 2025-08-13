package com.example.car.services.impl;

import com.example.car.customExceptions.DataNotFoundException;
import com.example.car.customExceptions.InvalidParamException;
import com.example.car.dto.HoaDonDoiTacRequestDto;
import com.example.car.dto.HoaDonRequestDTO;
import com.example.car.dto.SearchingHoaDonDoiTacDto;
import com.example.car.entities.HoaDonDoiTac;
import com.example.car.entities.HopDongChoThue;
import com.example.car.entities.HopDongThue;
import com.example.car.entities.NhanVien;
import com.example.car.repositories.*;
import com.example.car.services.IHoaDonDoiTacService;
import com.example.car.specifications.HoaDonDoiTacSpecifications;
import com.example.car.specifications.HopDongChoThueSpecifications;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@AllArgsConstructor
public class HoaDonDoiTacService implements IHoaDonDoiTacService {

    private HopDongChoThueRepository hopDongChoThueRepository;
    private NhanVienRepository nhanVienRepository;
    private HoaDonDoiTacRepository hoaDonDoiTacRepository;
    private HoaDonRepository hoaDonRepository;
    private HopDongThueRepository hopDongThueRepository;

    @Override
    public List<HoaDonDoiTac> findBySearchingHoaDonDoiTacDto(SearchingHoaDonDoiTacDto searchingHoaDonDoiTacDto) {
        Specification<HoaDonDoiTac> result = null;
        if(null != searchingHoaDonDoiTacDto.getDoiTacId()) {
            result = Specification.where(HoaDonDoiTacSpecifications.joinDoiTac());
        }

        result = Specification.where(result)
                .and(HoaDonDoiTacSpecifications.belongDoiTac(searchingHoaDonDoiTacDto.getDoiTacId()))
                .and(HoaDonDoiTacSpecifications.greaterThanOrEqualToDate(searchingHoaDonDoiTacDto.getNgayBatDau()))
                .and(HoaDonDoiTacSpecifications.lessThanOrEqualToDate(searchingHoaDonDoiTacDto.getNgayKetThuc()))
                .and(HoaDonDoiTacSpecifications.hasPhuongThucThanhToan(searchingHoaDonDoiTacDto.getPhuongThucThanhToan()));

        return hoaDonDoiTacRepository.findAll(result);
    }

    @Transactional
    @Override
    public HoaDonDoiTac createHoaDonDoiTac(HoaDonDoiTacRequestDto hoaDonDoiTacRequestDto) throws DataNotFoundException, InvalidParamException {
        HopDongChoThue hopDongChoThue = hopDongChoThueRepository.findById(hoaDonDoiTacRequestDto.getHopDongChoThueId())
                .orElseThrow(() -> new DataNotFoundException(
                        "Khong tim thay hop dong cho thue co id: " + hoaDonDoiTacRequestDto.getHopDongChoThueId()
                ));
        NhanVien nhanVien = nhanVienRepository.findById(hoaDonDoiTacRequestDto.getNhanVienId())
                .orElseThrow(() -> new DataNotFoundException(
                        "Khong tim thay nhan vien co id: " + hoaDonDoiTacRequestDto.getNhanVienId()
                ));

        // kiểm tra tiền trong hóa đơn có đúng với tổng tiền của danh sách hợp đồng thuê
        Float tongTien = hoaDonRepository.getTongTienByHDChoThueIdAndCheckoutBetween(
                hoaDonDoiTacRequestDto.getHopDongChoThueId(),
                hoaDonDoiTacRequestDto.getNgayBatDau(),
                hoaDonDoiTacRequestDto.getNgayKetThuc());
        if(tongTien * hopDongChoThue.getPhanTramCuaDoiTac() / 100 != hoaDonDoiTacRequestDto.getTongTien()) {
            throw new InvalidParamException("tong tien trong hoa don khong hop le");
        }

        // luu hoa don doi tac
        HoaDonDoiTac hoaDonDoiTac = HoaDonDoiTac.builder()
                .tongTien(hoaDonDoiTacRequestDto.getTongTien())
                .ghiChu(hoaDonDoiTacRequestDto.getGhiChu())
                .ngayThanhToan(hoaDonDoiTacRequestDto.getNgayThanhToan())
                .ngayBatDau(hoaDonDoiTacRequestDto.getNgayBatDau())
                .ngayKetThuc(hoaDonDoiTacRequestDto.getNgayKetThuc())
                .phuongThucThanhToan(hoaDonDoiTacRequestDto.getPhuongThucThanhToan())
                .hopDongChoThue(hopDongChoThue)
                .nhanVien(nhanVien)
                .build();
        HoaDonDoiTac finalHoaDonDoiTac = hoaDonDoiTacRepository.save(hoaDonDoiTac);

        //cap nhat daThanhToanChoDoiTac cua cac hop dong thue
        List<HopDongThue> hopDongThueList = hopDongThueRepository.findHDThueChuaThanhToanChoDTByHDChoThueIdAndCheckoutBetween(
                        hoaDonDoiTacRequestDto.getHopDongChoThueId(),
                        hoaDonDoiTacRequestDto.getNgayBatDau(),
                        hoaDonDoiTacRequestDto.getNgayKetThuc())
                .stream()
                .peek(hopDongThue -> {
                    hopDongThue.setDaThanhToanChoDoiTac(true);
                    hopDongThue.setHoaDonDoiTac(finalHoaDonDoiTac);
                }).toList();
        hopDongThueRepository.saveAll(hopDongThueList);

        return hoaDonDoiTac;
    }

    @Transactional
    @Override
    public HoaDonDoiTac updateHoaDonDoiTac(Integer hoaDonDoiTacId, HoaDonDoiTacRequestDto hoaDonDoiTacRequestDto) throws DataNotFoundException, InvalidParamException {
        HoaDonDoiTac hoaDonDoiTac = hoaDonDoiTacRepository.findById(hoaDonDoiTacId)
                .orElseThrow(() -> new DataNotFoundException(
                        "Khong tim thay hoa don doi tac co id: " + hoaDonDoiTacId
                ));

        HopDongChoThue hopDongChoThue = hopDongChoThueRepository.findById(hoaDonDoiTacRequestDto.getHopDongChoThueId())
                .orElseThrow(() -> new DataNotFoundException(
                        "Khong tim thay hop dong cho thue co id: " + hoaDonDoiTacRequestDto.getHopDongChoThueId()
                ));

        NhanVien nhanVien = nhanVienRepository.findById(hoaDonDoiTacRequestDto.getNhanVienId())
                .orElseThrow(() -> new DataNotFoundException(
                        "Khong tim thay nhan vien co id: " + hoaDonDoiTacRequestDto.getNhanVienId()
                ));

        //Đặt lại thuộc tính daThanhToanCuaDoi của các HopDongThue có hoaDonDoiTacId = hoaDonDoiTacId
        List<HopDongThue> oldHopDongThueList = hopDongThueRepository.findByHoaDonDoiTacId(hoaDonDoiTac)
                .stream()
                .peek(hopDongThue -> {
                    hopDongThue.setHoaDonDoiTac(null);
                    hopDongThue.setDaThanhToanChoDoiTac(false);
                }).toList();
        hopDongThueRepository.saveAll(oldHopDongThueList);


        // kiểm tra tiền trong hóa đơn có đúng với tổng tiền của danh sách hợp đồng thuê
        Float tongTien = hoaDonRepository.getTongTienByHDChoThueIdAndCheckoutBetween(
                hoaDonDoiTacRequestDto.getHopDongChoThueId(),
                hoaDonDoiTacRequestDto.getNgayBatDau(),
                hoaDonDoiTacRequestDto.getNgayKetThuc());
        if(tongTien * hopDongChoThue.getPhanTramCuaDoiTac() / 100 != hoaDonDoiTacRequestDto.getTongTien()) {
            throw new InvalidParamException("tong tien trong hoa don khong hop le");
        }

        // luu hoa don doi tac
        hoaDonDoiTac.setGhiChu(hoaDonDoiTacRequestDto.getGhiChu());
        hoaDonDoiTac.setNhanVien(nhanVien);
        hoaDonDoiTac.setHopDongChoThue(hopDongChoThue);
        hoaDonDoiTac.setNgayThanhToan(hoaDonDoiTacRequestDto.getNgayThanhToan());
        hoaDonDoiTac.setNgayBatDau(hoaDonDoiTacRequestDto.getNgayBatDau());
        hoaDonDoiTac.setNgayKetThuc(hoaDonDoiTacRequestDto.getNgayKetThuc());
        hoaDonDoiTac.setTongTien(hoaDonDoiTac.getTongTien());
        hoaDonDoiTac.setPhuongThucThanhToan(hoaDonDoiTacRequestDto.getPhuongThucThanhToan());
        HoaDonDoiTac finalHoaDonDoiTac = hoaDonDoiTacRepository.save(hoaDonDoiTac);

        //cap nhat daThanhToanChoDoiTac cua cac hop dong thue
        List<HopDongThue> hopDongThueList = hopDongThueRepository.findHDThueChuaThanhToanChoDTByHDChoThueIdAndCheckoutBetween(
                        hoaDonDoiTacRequestDto.getHopDongChoThueId(),
                        hoaDonDoiTacRequestDto.getNgayBatDau(),
                        hoaDonDoiTacRequestDto.getNgayKetThuc())
                .stream()
                .peek(hopDongThue -> {
                    hopDongThue.setDaThanhToanChoDoiTac(true);
                    hopDongThue.setHoaDonDoiTac(finalHoaDonDoiTac);
                }).toList();
        hopDongThueRepository.saveAll(hopDongThueList);

        return hoaDonDoiTac;
    }

    @Transactional
    @Override
    public void deleteHoaDonDoiTac(Integer id) throws DataNotFoundException, InvalidParamException {
        HoaDonDoiTac hoaDonDoiTac = hoaDonDoiTacRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(
                        "Khong tim thay hoa don doi tac co id: " + id
                ));
        //Đặt lại thuộc tính daThanhToanCuaDoi của các HopDongThue có hoaDonDoiTacId = hoaDonDoiTacId
        List<HopDongThue> oldHopDongThueList = hopDongThueRepository.findByHoaDonDoiTacId(hoaDonDoiTac)
                .stream()
                .peek(hopDongThue -> {
                    hopDongThue.setHoaDonDoiTac(null);
                    hopDongThue.setDaThanhToanChoDoiTac(false);
                }).toList();
        hopDongThueRepository.saveAll(oldHopDongThueList);
        hoaDonDoiTacRepository.delete(hoaDonDoiTac);
    }
}
