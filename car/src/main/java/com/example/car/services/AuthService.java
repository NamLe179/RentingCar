package com.example.car.services;

import com.example.car.dto.UserRequestDTO;
import com.example.car.entities.*;
import com.example.car.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private ThanhVienRepository thanhVienRepository;
    @Autowired
    private KhachHangRepository khachHangRepository;
    @Autowired
    private DoiTacRepository doiTacRepository;
    @Autowired
    private NhanVienRepository nhanVienRepository;
    @Autowired
    private QuanLyRepository quanLyRepository;
    @Autowired
    private DiaChiRepository diaChiRepository;

    public ThanhVien login(UserRequestDTO userRequest) {
        String username = userRequest.getUsername();
        String password = userRequest.getPassword();
        ThanhVien thanhVien = thanhVienRepository.findByUsername(username);
        if (thanhVien != null && thanhVien.getPassword().equals(password)) {
            return thanhVien;
        }
        return null; // Trả về null nếu không tìm thấy hoặc mật khẩu không khớp
    }

    public ThanhVien register(UserRequestDTO userRequest) {
        String username = userRequest.getUsername();
        if (thanhVienRepository.findByUsername(username) != null) {
            return null; // Trả về null nếu đã tồn tại
        }

        DiaChi diaChi = new DiaChi();
        diaChi.setTinh(userRequest.getTinh());
        diaChi.setQuan(userRequest.getQuan());
        diaChi.setPhuong(userRequest.getPhuong());
        diaChi.setSoNha(userRequest.getSoNha());

        diaChiRepository.save(diaChi); // Lưu địa chỉ vào cơ sở dữ liệu

        String role = userRequest.getViTri();

        switch (role) {
            case "Khách hàng":
                String khachHangId = generateIdForKhachHang();
                KhachHang khachHang = new KhachHang();
                khachHang.setId(khachHangId);
                khachHang.setUsername(username);
                khachHang.setPassword(userRequest.getPassword());
                khachHang.setHoTen(userRequest.getHoTen());
                khachHang.setSdt(userRequest.getSdt());
                khachHang.setEmail(userRequest.getEmail());
                khachHang.setDiaChi(diaChi);
                return khachHangRepository.save(khachHang);

            case "Đối tác":
                String doiTacId = generateIdForDoiTac();
                DoiTac doiTac = new DoiTac();
                doiTac.setId(doiTacId);
                doiTac.setUsername(username);
                doiTac.setPassword(userRequest.getPassword());
                doiTac.setHoTen(userRequest.getHoTen());
                doiTac.setSdt(userRequest.getSdt());
                doiTac.setEmail(userRequest.getEmail());
                doiTac.setDiaChi(diaChi);
                return doiTacRepository.save(doiTac);

            case "Nhân viên":
                String nhanVienId = generateIdForNhanVien();
                NhanVien nhanVien = new NhanVien();
                nhanVien.setId(nhanVienId);
                nhanVien.setUsername(username);
                nhanVien.setPassword(userRequest.getPassword());
                nhanVien.setHoTen(userRequest.getHoTen());
                nhanVien.setSdt(userRequest.getSdt());
                nhanVien.setEmail(userRequest.getEmail());
                nhanVien.setDiaChi(diaChi);
                return nhanVienRepository.save(nhanVien);

            case "Quản lý":
                String quanLyId = generateIdForQuanLy();
                QuanLy quanLy = new QuanLy();
                quanLy.setId(quanLyId);
                quanLy.setUsername(username);
                quanLy.setPassword(userRequest.getPassword());
                quanLy.setHoTen(userRequest.getHoTen());
                quanLy.setSdt(userRequest.getSdt());
                quanLy.setEmail(userRequest.getEmail());
                quanLy.setDiaChi(diaChi);
                return quanLyRepository.save(quanLy);

            default:
                return null; // Trả về null nếu vai trò không hợp lệ
        }

        // Thiết lập các thông tin địa chỉ



    }

    private String generateIdForNhanVien() {
        String prefix = "NV";
        String lastId = nhanVienRepository.findTopByIdStartingWithOrderByIdDesc(prefix).getId();
        return buildNextId(prefix, lastId);
    }

    private String generateIdForQuanLy() {
        String prefix = "QL";
        String lastId = quanLyRepository.findTopByIdStartingWithOrderByIdDesc(prefix).getId();
        return buildNextId(prefix, lastId);
    }

    private String generateIdForKhachHang() {
        String prefix = "KH";
        String lastId = khachHangRepository.findTopByIdStartingWithOrderByIdDesc(prefix).getId();
        return buildNextId(prefix, lastId);
    }

    private String generateIdForDoiTac() {
        String prefix = "DT";
        String lastId = doiTacRepository.findTopByIdStartingWithOrderByIdDesc(prefix).getId();
        return buildNextId(prefix, lastId);
    }

    private String buildNextId(String prefix, String lastId) {
        int next = 1;
        if (lastId != null) {
            next = Integer.parseInt(lastId.substring(prefix.length())) + 1;
        }
        return String.format("%s%04d", prefix, next);
    }


}
