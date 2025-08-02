create database db_car;
use db_car;
create table hang_xe (
	id varchar(10) primary key,
    ten varchar(20) not null,
    mo_ta varchar(200)
);

create table mau_xe (
	id varchar(10) primary key,
    ten varchar(20) not null,
    so_ghe integer(2) not null,
    mo_ta varchar(200),
    hang_xe_id varchar(10) not null,
    foreign key (hang_xe_id) references hang_xe(id)
);

create table tien_nghi (
	id varchar(10) primary key,
    ten varchar(20) not null,
    mo_ta varchar(200)
);

create table anh (
	id varchar(10) primary key,
    url varchar(200) not null,
    ghi_chu varchar(255),
    ngay_chup datetime
);

create table phu_phi (
	id varchar(10) primary key,
    ten varchar(20) not null,
    gia float(15) not null,
    mo_ta varchar(200)
);

create table dia_chi (
	id varchar(10) primary key,
    tinh varchar(100) not null,
    quan varchar(100) not null,
    phuong varchar(100) not null,
    so_nha varchar(100) not null
);

create table thanh_vien (
	id varchar(10) primary key,
    username varchar(40) not null,
    password varchar(50) not null,
    ho_ten varchar(50) not null,
    sdt varchar(10) not null,
    email varchar(50) not null,
    dia_chi_id varchar(10) not null,
    foreign key (dia_chi_id) references dia_chi(id)
);

create table quan_ly (
	id varchar(10) primary key,
    foreign key (id) references thanh_vien(id)
);

create table nhan_vien (
	id varchar(10) primary key,
    foreign key (id) references thanh_vien(id)
);

create table khach_hang (
	id varchar(10) primary key,
    foreign key (id) references thanh_vien(id)
);

create table doi_tac (
	id varchar(10) primary key,
    foreign key (id) references thanh_vien(id)
);

create table oto (
	id varchar(10) primary key,
    nam_san_xuat integer(4) not null,
    truyen_dong varchar(20) not null,
    loai_nhien_lieu varchar(20) not null,
    muc_tieu_thu integer(2) not null,
    bien_so varchar(10) not null,
    trang_thai integer(1) not null,
    gia float(15) not null,
    mo_ta varchar(500),
    mau_xe_id varchar(10) not null,
    dia_chi_id varchar(10) not null,
    doi_tac_id varchar(10) not null,
    foreign key (mau_xe_id) references mau_xe(id),
    foreign key (dia_chi_id) references dia_chi(id),
    foreign key (doi_tac_id) references doi_tac(id)
);

create table hop_dong_cho_thue (
	id varchar(10) primary key,
    ngay_bat_dau datetime not null,
    ngay_ket_thuc datetime not null,
    loai_hop_dong varchar(20) not null,
    ghi_chu varchar(200),
    oto_id varchar(10) not null,
    
    nhan_vien_id varchar(10) not null,
    foreign key (oto_id) references oto(id),
    
    foreign key (nhan_vien_id) references nhan_vien(id)
);

create table hoa_don_doi_tac (
	id varchar(10) primary key,
    tong_tien float(15) not null,
    ngay_thanh_toan datetime not null,
    phuong_thuc_thanh_toan varchar(20) not null,
    ghi_chu varchar(200),
    nhan_vien_id varchar(10) not null,
    hop_dong_cho_thue_id varchar(10) not null,
    foreign key (nhan_vien_id) references nhan_vien(id),
    foreign key (hop_dong_cho_thue_id) references hop_dong_cho_thue(id)
);

create table tien_nghi_duoc_chon (
	id varchar(10) primary key,
    oto_id varchar(10) not null,
    tien_nghi_id varchar(10) not null,
    foreign key (oto_id) references oto(id),
    foreign key (tien_nghi_id) references tien_nghi(id)
);

create table anh_cua_xe (
	id varchar(10) primary key,
    loai_anh varchar(50) not null,
    oto_id varchar(10) not null,
    thumnail boolean not null,
    foreign key (id) references anh(id),
    foreign key (oto_id) references oto(id)
);

create table hop_dong_thue (
	id varchar(10) primary key,
    thoi_gian_nhan datetime not null,
    thoi_gian_tra datetime not null,
    trang_thai integer(1) not null,
    mo_ta varchar(200),
    check_in datetime,
    check_out datetime,
    khach_hang_danh_gia_chu varchar(255),
    khach_hang_danh_gia_so integer(1),
    ngay_danh_gia datetime,
    doi_tac_bao_cao varchar(255),
    oto_id varchar(10) not null,
    khach_hang_id varchar(10) not null,
    foreign key (oto_id) references oto(id),
    foreign key (khach_hang_id) references khach_hang(id)
);

create table anh_cua_khach (
	id varchar(10) primary key,
    hop_dong_thue_id varchar(10) not null,
    foreign key (id) references anh(id),
    foreign key (hop_dong_thue_id) references hop_dong_thue(id)
);

create table danh_sach_den (
	id varchar(10) primary key,
    ngay_them datetime not null,
    ly_do varchar(255),
	trang_thai integer(1) not null,
    khach_hang_id varchar(10) not null,
    quan_ly_id varchar(10) not null,
    hop_dong_thue_id varchar(10) not null,
    foreign key (khach_hang_id) references khach_hang(id),
    foreign key (quan_ly_id) references quan_ly(id),
    foreign key (hop_dong_thue_id) references hop_dong_thue(id)
);

create table hoa_don (
	id varchar(10) primary key,
    tong_tien float(15) not null,
    ngay_thanh_toan datetime not null,
    phuong_thuc_thanh_toan varchar(20) not null,
    ghi_chu varchar(200),
    hop_dong_thue_id varchar(10) not null,
    nhan_vien_id varchar(10) not null,
    foreign key (hop_dong_thue_id) references hop_dong_thue(id),
    foreign key (nhan_vien_id) references nhan_vien(id)
);

create table phu_phi_duoc_chon (
	id varchar(10) primary key,
    gia float(15) not null,
    phu_phi_id varchar(10) not null,
    hoa_don_id varchar(10) not null,
	foreign key (phu_phi_id) references phu_phi(id),
    foreign key (hoa_don_id) references hoa_don(id)
);

create table tai_san_cam_co (
	id varchar(10) primary key,
    ten varchar(20) not null,
    loai_tai_san varchar(20) not null,
    gia float(15) not null,
    mo_ta varchar(200),
    trang_thai integer(1) not null,
    thoi_gian_tra datetime,
    thoi_gian_nhan datetime,
    hop_dong_thue_id varchar(10) not null,
    khach_hang_id varchar(10) not null,
    nhan_vien_nhan_id varchar(10) not null,
    nhan_vien_tra_id varchar(10) not null,
    
    foreign key (hop_dong_thue_id) references hop_dong_thue(id),
    foreign key (khach_hang_id) references khach_hang(id),
    foreign key (nhan_vien_nhan_id) references nhan_vien(id),
    foreign key (nhan_vien_tra_id) references nhan_vien(id)
);