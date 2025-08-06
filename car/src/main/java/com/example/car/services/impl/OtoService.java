package com.example.car.services.impl;

import com.example.car.customExceptions.DataNotFoundException;
import com.example.car.dto.OtoRequestDto;
import com.example.car.entities.*;
import com.example.car.enums.OtoStatus;
import com.example.car.repositories.*;
import com.example.car.services.IOtoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class OtoService implements IOtoService {

    OtoRepository otoRepository;
    DoiTacRepository doiTacRepository;
    DiaChiRepository diaChiRepository;
    MauXeRepository mauXeRepository;
    TienNghiDuocChonRepository tienNghiDuocChonRepository;
    TienNghiRepository tienNghiRepository;

    /*
    * thêm mới ô tô
    */
    @Transactional
    @Override
    public Oto createOto(OtoRequestDto otoRequestDto) throws Exception {
        DoiTac doiTac = doiTacRepository.findById(otoRequestDto.getDoiTacId())
                .orElseThrow(() -> new DataNotFoundException(
                    "Khong tim thay doi tac co id " + otoRequestDto.getDoiTacId()
                ));
        MauXe mauXe = mauXeRepository.findById(otoRequestDto.getMauXeId())
                .orElseThrow(() -> new DataNotFoundException(
                        "Khong tim thay mau xe co id " + otoRequestDto.getMauXeId()
                ));
        DiaChi diaChi = DiaChi.builder()
                .tinh(otoRequestDto.getDiaChi().getTinh())
                .quan(otoRequestDto.getDiaChi().getQuan())
                .phuong(otoRequestDto.getDiaChi().getPhuong())
                .soNha(otoRequestDto.getDiaChi().getSoNha())
                .build();
        DiaChi newDiaChi = diaChiRepository.save(diaChi);
        Oto oTo = Oto.builder()
                .doiTac(doiTac)
                .mauXe(mauXe)
                .diaChi(newDiaChi)
                .bienSo(otoRequestDto.getBienSo())
                .moTa(otoRequestDto.getMoTa())
                .loaiNhienLieu(otoRequestDto.getLoaiNhienLieu())
                .mucTieuThu(otoRequestDto.getMucTieuThu())
                .namSanXuat(otoRequestDto.getNamSanXuat())
                .truyenDong(otoRequestDto.getTruyenDong())
                .trangThai(OtoStatus.CHO_DUYET)
                .build();
        Oto finalOTo = otoRepository.save(oTo);
        List<TienNghiDuocChon> tienNghiDuocChonList = otoRequestDto.getTienNghiList().stream()
                .map((Integer tienNghiId) -> {
                            TienNghi tienNghi = tienNghiRepository.findById(tienNghiId)
                                    .orElseThrow(() -> new DataNotFoundException(
                                            "Khong tim thay tien nghi co id " + tienNghiId
                                    ));
                            return TienNghiDuocChon.builder().tienNghi(tienNghi).oto(finalOTo).build();
                        }
                ).toList();
        tienNghiDuocChonRepository.saveAll(tienNghiDuocChonList);
        return finalOTo;
    }

    @Override
    @Transactional
    public Oto updateOto(Integer id, OtoRequestDto otoRequestDto) throws Exception {
        Oto exsitingOto = otoRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(
                        "Khong tim thay o to co id " + id
                ));
        DoiTac doiTac = doiTacRepository.findById(otoRequestDto.getDoiTacId())
                .orElseThrow(() -> new DataNotFoundException(
                        "Khong tim thay doi tac co id " + otoRequestDto.getDoiTacId()
                ));
        MauXe mauXe = mauXeRepository.findById(otoRequestDto.getMauXeId())
                .orElseThrow(() -> new DataNotFoundException(
                        "Khong tim thay mau xe co id " + otoRequestDto.getMauXeId()
                ));

        DiaChi diaChi = diaChiRepository.findById(otoRequestDto.getDiaChi().getId())
                .orElseThrow(() -> new DataNotFoundException(
                        "Khong tim thay dia chi co id " + otoRequestDto.getDiaChi().getId()
                ));
        diaChi.setTinh(otoRequestDto.getDiaChi().getTinh());
        diaChi.setQuan(otoRequestDto.getDiaChi().getQuan());
        diaChi.setPhuong(otoRequestDto.getDiaChi().getPhuong());
        diaChi.setSoNha(otoRequestDto.getDiaChi().getSoNha());
        DiaChi updateDiaChi = diaChiRepository.save(diaChi);
        List<TienNghiDuocChon> oldTienNghiDuocChonList = tienNghiDuocChonRepository.findByOtoId(exsitingOto.getId());
        tienNghiDuocChonRepository.deleteAll(oldTienNghiDuocChonList);
        List<TienNghiDuocChon> tienNghiDuocChonList = otoRequestDto.getTienNghiList().stream()
                .map((Integer tienNghiId) -> {
                            TienNghi tienNghi = tienNghiRepository.findById(tienNghiId)
                                    .orElseThrow(() -> new DataNotFoundException(
                                            "Khong tim thay tien nghi co id " + tienNghiId
                                    ));
                            return TienNghiDuocChon.builder().tienNghi(tienNghi).oto(exsitingOto).build();
                        }
                ).toList();
        tienNghiDuocChonRepository.saveAll(tienNghiDuocChonList);

        exsitingOto.setTrangThai(otoRequestDto.getTrangThai());
        exsitingOto.setGia(otoRequestDto.getGia());
        exsitingOto.setBienSo(otoRequestDto.getBienSo());
        exsitingOto.setLoaiNhienLieu(otoRequestDto.getLoaiNhienLieu());
        exsitingOto.setMoTa(otoRequestDto.getMoTa());
        exsitingOto.setMucTieuThu(otoRequestDto.getMucTieuThu());
        exsitingOto.setNamSanXuat(otoRequestDto.getNamSanXuat());
        exsitingOto.setTruyenDong(otoRequestDto.getTruyenDong());
        exsitingOto.setDiaChi(updateDiaChi);
        exsitingOto.setDoiTac(doiTac);
        exsitingOto.setMauXe(mauXe);
        return otoRepository.save(exsitingOto);
    }

    @Override
    public Oto getOtoById(Integer id) throws Exception {
        return otoRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(
                        "Khong tim thay o to co id " + id
                ));
    }

    @Override
    public List<Oto> findByDoiTacId(String doiTacId) {
        return otoRepository.findByDoiTacId(doiTacId);
    }


}
