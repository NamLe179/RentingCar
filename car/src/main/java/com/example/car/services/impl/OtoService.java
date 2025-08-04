package com.example.car.services.impl;

import com.example.car.customExceptions.DataNotFoundException;
import com.example.car.dto.DoiTacDto;
import com.example.car.dto.MauXeDto;
import com.example.car.dto.OtoDto;
import com.example.car.dto.TienNghiDto;
import com.example.car.entities.*;
import com.example.car.enums.OtoStatus;
import com.example.car.repositories.*;
import com.example.car.services.IOtoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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
    public Oto createOto(OtoDto oToDto) throws Exception {
        DoiTac doiTac = doiTacRepository.findById(oToDto.getDoiTacDto().getId())
                .orElseThrow(() -> new DataNotFoundException(
                    "Khong tim thay doi tac co id " + oToDto.getDoiTacDto().getId()
                ));
        MauXe mauXe = mauXeRepository.findById(oToDto.getMauXeDto().getId())
                .orElseThrow(() -> new DataNotFoundException(
                        "Khong tim thay mau xe co id " + oToDto.getMauXeDto().getId()
                ));
        DiaChi diaChi = DiaChi.builder()
                .tinh(oToDto.getDiaChiDto().getTinh())
                .quan(oToDto.getDiaChiDto().getQuan())
                .phuong(oToDto.getDiaChiDto().getPhuong())
                .soNha(oToDto.getDiaChiDto().getSoNha())
                .build();
        diaChi = diaChiRepository.save(diaChi);
        Oto oTo = Oto.builder()
                .doiTac(doiTac)
                .mauXe(mauXe)
                .diaChi(diaChi)
                .bienSo(oToDto.getBienSo())
                .moTa(oToDto.getMoTa())
                .loaiNhienLieu(oToDto.getLoaiNhienLieu())
                .mucTieuThu(oToDto.getMucTieuThu())
                .namSanXuat(oToDto.getNamSanXuat())
                .truyenDong(oToDto.getTruyenDong())
                .trangThai(OtoStatus.CHO_DUYET)
                .build();
        Oto finalOTo = otoRepository.save(oTo);
        List<TienNghiDuocChon> tienNghiDuocChonList = oToDto.getTienNghiDtoList().stream()
                .map((TienNghiDto tienNghiDto) -> {
                            TienNghi tienNghi = tienNghiRepository.findById(tienNghiDto.getId())
                                    .orElseThrow(() -> new DataNotFoundException(
                                            "Khong tim thay tien nghi co id " + tienNghiDto.getId()
                                    ));
                            return TienNghiDuocChon.builder().tienNghi(tienNghi).oto(finalOTo).build();
                        }
                ).toList();
        tienNghiDuocChonRepository.saveAll(tienNghiDuocChonList);
        return finalOTo;
    }

    @Override
    public Oto getOtoById(Integer id) throws Exception {
        return otoRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(
                        "Khong tim thay o to co id " + id
                ));
    }
}
