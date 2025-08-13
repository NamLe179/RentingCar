import random
from datetime import datetime, timedelta

# Helper to generate Vietnamese names
def generate_vietnamese_name():
    first_names = [
        "Nguyễn", "Trần", "Lê", "Phạm", "Hoàng", "Huỳnh", "Vũ", "Đặng", "Bùi", "Đỗ",
        "Hồ", "Ngô", "Dương", "Đào", "Đinh", "Lý", "Phan", "Trương", "Võ", "Lâm"
    ]
    middle_names = [
        "Thị", "Văn", "Minh", "Thu", "Anh", "Hoàng", "Quốc", "Xuân", "Mỹ", "Kiều",
        "Thanh", "Bích", "Ngọc", "Vân", "Duy", "Hồng", "Khánh", "Tuấn", "Linh", "Cẩm"
    ]
    last_names = [
        "An", "Bình", "Châu", "Dương", "Hải", "Lan", "Mai", "Ngân", "Phú", "Quý",
        "Sơn", "Thảo", "Uyên", "Vinh", "Yên", "Tùng", "Cường", "Hương", "Trâm", "Khoa"
    ]
    return f"{random.choice(first_names)} {random.choice(middle_names)} {random.choice(last_names)}"

# Helper for dates
def random_date(start_date, end_date):
    time_between_dates = end_date - start_date
    days_between_dates = time_between_dates.days
    random_number_of_days = 0 if days_between_dates == 0 else random.randrange(days_between_dates)
    random_hour = random.randrange(24)
    random_minute = random.randrange(60)
    random_second = random.randrange(60)
    return start_date + timedelta(days=random_number_of_days, hours=random_hour, minutes=random_minute, seconds=random_second)

# Helper to escape single quotes in SQL strings
def escape_sql_string(s):
    if isinstance(s, str):
        return s.replace("'", "''")
    return s

def generate_data():
    sql_statements = []
    
    # Disable foreign key checks
    sql_statements.append("SET FOREIGN_KEY_CHECKS = 0;")
    
    # Optional: TRUNCATE existing data for a clean start (uncomment if needed)
    # sql_statements.append("TRUNCATE TABLE tai_san_cam_co;")
    # sql_statements.append("TRUNCATE TABLE danh_sach_den;")
    # sql_statements.append("TRUNCATE TABLE anh_cua_khach;")
    # sql_statements.append("TRUNCATE TABLE phu_phi_duoc_chon;")
    # sql_statements.append("TRUNCATE TABLE hoa_don;")
    # sql_statements.append("TRUNCATE TABLE hop_dong_thue;")
    # sql_statements.append("TRUNCATE TABLE anh_cua_xe;")
    # sql_statements.append("TRUNCATE TABLE tien_nghi_duoc_chon;")
    # sql_statements.append("TRUNCATE TABLE hoa_don_doi_tac;")
    # sql_statements.append("TRUNCATE TABLE hop_dong_cho_thue;")
    # sql_statements.append("TRUNCATE TABLE oto;")
    # sql_statements.append("TRUNCATE TABLE anh;")
    # sql_statements.append("TRUNCATE TABLE phu_phi;")
    # sql_statements.append("TRUNCATE TABLE tien_nghi;")
    # sql_statements.append("TRUNCATE TABLE mau_xe;")
    # sql_statements.append("TRUNCATE TABLE hang_xe;")
    # sql_statements.append("TRUNCATE TABLE quan_ly;")
    # sql_statements.append("TRUNCATE TABLE nhan_vien;")
    # sql_statements.append("TRUNCATE TABLE khach_hang;")
    # sql_statements.append("TRUNCATE TABLE doi_tac;")
    # sql_statements.append("TRUNCATE TABLE thanh_vien;")
    # sql_statements.append("TRUNCATE TABLE dia_chi;")

    # --- 1. dia_chi (addresses) ---
    num_dia_chi = 50
    dia_chi_ids = []
    tinh_list = [
        'Thành phố Hồ Chí Minh', 'Thành phố Hà Nội', 'Thành phố Đà Nẵng', 'Thành phố Cần Thơ',
        'Thành phố Hải Phòng', 'Tỉnh Bình Dương', 'Tỉnh Đồng Nai', 'Tỉnh Bà Rịa - Vũng Tàu',
        'Tỉnh Khánh Hòa', 'Tỉnh Quảng Nam', 'Tỉnh Nghệ An', 'Tỉnh Thanh Hóa',
        'Tỉnh Gia Lai', 'Tỉnh Đắk Lắk', 'Tỉnh Lâm Đồng', 'Thành phố Huế',
        'Tỉnh Vĩnh Phúc', 'Tỉnh Bắc Ninh', 'Tỉnh Hưng Yên', 'Tỉnh Long An'
    ]
    quan_list = [
        'Quận 1', 'Quận Ba Đình', 'Quận Hải Châu', 'Quận Ninh Kiều', 'Quận Lê Chân',
        'Quận Tân Bình', 'Quận Bình Thạnh', 'Huyện Củ Chi', 'Huyện Gia Lâm',
        'Thành phố Thủ Đức', 'Thành phố Dĩ An', 'Thành phố Biên Hòa', 'Thành phố Vũng Tàu',
        'Quận Đống Đa', 'Quận Gò Vấp', 'Quận Sơn Trà', 'Huyện Nhà Bè'
    ]
    phuong_list = [
        'Phường Bến Nghé', 'Phường Điện Biên', 'Phường Hòa Cường Bắc', 'Phường Xuân Khánh',
        'Phường Nghĩa Xá', 'Phường 4', 'Phường 6', 'Phường 12', 'Phường Phước Ninh',
        'Phường An Bình', 'Phường Phú Thọ', 'Phường Thống Nhất', 'Phường Bách Khoa',
        'Phường Chợ Đuổi', 'Phường Mỹ An', 'Phường Phú Mỹ'
    ]
    
    for i in range(1, num_dia_chi + 1):
        tinh = escape_sql_string(random.choice(tinh_list))
        quan = escape_sql_string(random.choice(quan_list))
        phuong = escape_sql_string(random.choice(phuong_list))
        so_nha = escape_sql_string(f"{random.randint(1, 999)} Đường {chr(random.randint(65, 90))}{random.randint(1, 10)}")
        sql_statements.append(f"INSERT INTO dia_chi (tinh, quan, phuong, so_nha) VALUES (N'{tinh}', N'{quan}', N'{phuong}', N'{so_nha}');")
        dia_chi_ids.append(i)

    # --- 2. thanh_vien (members) ---
    num_thanh_vien = 50
    thanh_vien_ids = []
    for i in range(1, num_thanh_vien + 1):
        tv_id = f"TV{i:03d}"
        ho_ten = generate_vietnamese_name()
        username = f"{ho_ten.replace(' ', '_').lower()}_{i}"
        password = escape_sql_string("pass" + str(random.randint(100, 999)) + ho_ten[:3])
        sdt = f"0{random.randint(900000000, 999999999)}"
        email = f"{username}{random.randint(1,100)}@example.com"
        dia_chi_id = random.choice(dia_chi_ids)
        sql_statements.append(f"INSERT INTO thanh_vien (id, username, password, ho_ten, sdt, email, dia_chi_id) VALUES ('{tv_id}', '{username}', '{password}', N'{escape_sql_string(ho_ten)}', '{sdt}', '{email}', {dia_chi_id});")
        thanh_vien_ids.append(tv_id)
    
    # --- 3. quan_ly (managers) ---
    num_quan_ly = 5
    quan_ly_ids = random.sample(thanh_vien_ids, num_quan_ly)
    for ql_id in quan_ly_ids:
        sql_statements.append(f"INSERT INTO quan_ly (id) VALUES ('{ql_id}');")

    # --- 4. nhan_vien (employees) ---
    num_nhan_vien = 10
    nhan_vien_ids = []
    temp_thanh_vien_ids = [tv for tv in thanh_vien_ids if tv not in quan_ly_ids]
    nhan_vien_ids = random.sample(temp_thanh_vien_ids, num_nhan_vien)
    for nv_id in nhan_vien_ids:
        sql_statements.append(f"INSERT INTO nhan_vien (id) VALUES ('{nv_id}');")

    # --- 5. khach_hang (customers) ---
    num_khach_hang = 25
    khach_hang_ids = []
    temp_thanh_vien_ids = [tv for tv in temp_thanh_vien_ids if tv not in nhan_vien_ids]
    khach_hang_ids = random.sample(temp_thanh_vien_ids, num_khach_hang)
    for kh_id in khach_hang_ids:
        sql_statements.append(f"INSERT INTO khach_hang (id) VALUES ('{kh_id}');")

    # --- 6. doi_tac (partners) ---
    num_doi_tac = 10
    doi_tac_ids = []
    temp_thanh_vien_ids = [tv for tv in temp_thanh_vien_ids if tv not in khach_hang_ids]
    doi_tac_ids = random.sample(temp_thanh_vien_ids, num_doi_tac)
    for dt_id in doi_tac_ids:
        sql_statements.append(f"INSERT INTO doi_tac (id) VALUES ('{dt_id}');")

    # --- 7. hang_xe (car brands) ---
    num_hang_xe_data = 10 # Number of actual brand data to insert
    hang_xe_ids = []
    hang_xe_list = [
        ('Toyota', 'Hãng xe hơi Nhật Bản nổi tiếng với độ bền và tiết kiệm nhiên liệu.'),
        ('Honda', 'Hãng xe hơi Nhật Bản, đa dạng mẫu mã từ sedan đến SUV.'),
        ('Mercedes-Benz', 'Thương hiệu xe sang của Đức, biểu tượng của sự sang trọng và công nghệ.'),
        ('BMW', 'Hãng xe hơi Đức, nổi bật với khả năng vận hành thể thao và thiết kế đẳng cấp.'),
        ('Hyundai', 'Hãng xe Hàn Quốc, đa dạng mẫu mã, giá cả phải chăng.'),
        ('Kia', 'Hãng xe Hàn Quốc, thiết kế trẻ trung, nhiều tính năng hiện đại.'),
        ('Ford', 'Hãng xe Mỹ, nổi tiếng với xe bán tải và SUV.'),
        ('Mazda', 'Hãng xe Nhật Bản, thiết kế KODO và công nghệ SkyActiv.'),
        ('VinFast', 'Hãng xe Việt Nam, sản xuất xe điện và xe xăng.'),
        ('Audi', 'Thương hiệu xe sang của Đức, thiết kế tinh tế và công nghệ tiên tiến.')
    ]
    for i, (ten, mo_ta) in enumerate(hang_xe_list):
        sql_statements.append(f"INSERT INTO hang_xe (ten, mo_ta) VALUES (N'{escape_sql_string(ten)}', N'{escape_sql_string(mo_ta)}');")
        hang_xe_ids.append(i + 1) # IDs start from 1

    # --- 8. mau_xe (car models) ---
    num_mau_xe = 50
    mau_xe_ids = []
    mau_xe_names_by_brand = {
        1: ['Camry', 'Altis', 'Vios', 'Innova', 'Fortuner', 'Corolla Cross', 'Yaris'], # Toyota
        2: ['Civic', 'CR-V', 'City', 'HR-V', 'Accord', 'BR-V'],      # Honda
        3: ['C-Class', 'E-Class', 'GLC', 'S-Class', 'A-Class', 'EQS', 'GLE'], # Mercedes
        4: ['X5', '3 Series', '5 Series', 'X3', '7 Series', 'X7', 'Z4'], # BMW
        5: ['Accent', 'Elantra', 'Tucson', 'Santa Fe', 'Kona', 'Creta', 'i10'], # Hyundai
        6: ['K3', 'Seltos', 'Carnival', 'Sonet', 'Cerato', 'Sportage', 'Morning'],   # Kia
        7: ['Ranger', 'Everest', 'Transit', 'Focus', 'Territory'],        # Ford
        8: ['Mazda 3', 'CX-5', 'Mazda 6', 'CX-8', 'CX-30', 'Mazda 2'],   # Mazda
        9: ['Fadil', 'Lux A2.0', 'Lux SA2.0', 'VF e34', 'VF 8', 'VF 9'], # VinFast
        10: ['A4', 'Q5', 'A6', 'Q7', 'A8', 'A3', 'Q3']                    # Audi
    }
    
    current_mau_xe_id = 1
    for i in range(num_mau_xe):
        hang_xe_id = random.choice(hang_xe_ids)
        ten_mau_xe = random.choice(mau_xe_names_by_brand[hang_xe_id]) + f" {random.randint(2015, 2024)}"
        so_ghe = random.choice([4, 5, 7])
        # Ensure 7-seater models are associated with appropriate names (e.g., SUV-like)
        if so_ghe == 7 and not any(kw in ten_mau_xe for kw in ['Fortuner', 'Santa Fe', 'Everest', 'Carnival', 'X5', 'Q7', 'SUV']):
            so_ghe = random.choice([4, 5]) # Reroll if 7-seater picked for an unlikely model
        mo_ta = escape_sql_string('Mẫu xe phổ biến, được ưa chuộng.') if so_ghe != 7 else escape_sql_string('Mẫu SUV/MPV 7 chỗ rộng rãi, tiện nghi.')
        sql_statements.append(f"INSERT INTO mau_xe (ten, so_ghe, mo_ta, hang_xe_id) VALUES (N'{escape_sql_string(ten_mau_xe)}', {so_ghe}, N'{mo_ta}', {hang_xe_id});")
        mau_xe_ids.append(current_mau_xe_id)
        current_mau_xe_id += 1

    # --- 9. tien_nghi (amenities) ---
    num_tien_nghi_data = 15 # Number of actual amenity data to insert
    tien_nghi_ids = []
    tien_nghi_list = [
        ('Camera lùi', 'Giúp quan sát phía sau xe khi lùi.'),
        ('Cảm biến va chạm', 'Cảnh báo chướng ngại vật xung quanh xe.'),
        ('Định vị GPS', 'Hệ thống dẫn đường toàn cầu.'),
        ('Kết nối Bluetooth', 'Kết nối điện thoại để nghe nhạc và gọi điện.'),
        ('Cửa sổ trời', 'Cửa sổ trên nóc xe, tạo không gian thoáng đãng.'),
        ('Hệ thống phanh ABS', 'Hệ thống chống bó cứng phanh, tăng cường an toàn.'),
        ('Ghế da cao cấp', 'Ghế bọc da sang trọng.'),
        ('Điều hòa tự động 2 vùng', 'Kiểm soát nhiệt độ độc lập.'),
        ('Màn hình cảm ứng giải trí', 'Hỗ trợ Apple CarPlay/Android Auto.'),
        ('Đèn pha LED tự động', 'Chiếu sáng tốt hơn và tự động bật/tắt.'),
        ('Cruise Control', 'Hệ thống giữ tốc độ ổn định.'),
        ('Cảnh báo lệch làn', 'Hệ thống hỗ trợ lái xe an toàn.'),
        ('Sạc không dây', 'Tiện lợi cho điện thoại.'),
        ('Cốp điện', 'Mở cốp tự động bằng nút bấm.'),
        ('Gương chiếu hậu chỉnh điện', 'Dễ dàng điều chỉnh gương.')
    ]
    for i, (ten, mo_ta) in enumerate(tien_nghi_list):
        sql_statements.append(f"INSERT INTO tien_nghi (ten, mo_ta) VALUES (N'{escape_sql_string(ten)}', N'{escape_sql_string(mo_ta)}');")
        tien_nghi_ids.append(i + 1)

    # --- 10. anh (images) ---
    num_anh = 100 # Generate a pool of 100 images
    anh_ids = []
    image_types = ['car_front', 'car_side', 'car_interior', 'car_rear', 'car_dashboard', 'car_engine', 'license_plate', 'id_card', 'driving_license', 'customer_photo']
    for i in range(1, num_anh + 1):
        image_type = random.choice(image_types)
        url = f"https://example.com/images/{image_type}_{i}.jpg"
        if 'car_' in image_type:
            ghi_chu = escape_sql_string('Ảnh minh họa xe')
        elif 'license_plate' in image_type:
            ghi_chu = escape_sql_string('Ảnh giấy tờ xe')
        else:
            ghi_chu = escape_sql_string('Ảnh giấy tờ/chân dung khách hàng')
        ngay_chup = random_date(datetime(2023, 1, 1), datetime(2024, 12, 31)).strftime('%Y-%m-%d %H:%M:%S')
        sql_statements.append(f"INSERT INTO anh (id, url, ghi_chu, ngay_chup) VALUES ({i}, '{url}', N'{ghi_chu}', '{ngay_chup}');")
        anh_ids.append(i)

    # Separate anh_ids for cars and customers to ensure unique references for anh_cua_xe and anh_cua_khach
    car_image_anh_ids_pool = [aid for aid in anh_ids if aid % 2 == 0] # Even IDs for cars
    customer_image_anh_ids_pool = [aid for aid in anh_ids if aid % 2 != 0] # Odd IDs for customers
    
    # --- 11. phu_phi (surcharges) ---
    num_phu_phi_data = 10 # Number of actual surcharge data to insert
    phu_phi_ids = []
    phu_phi_list = [
        ('Phí vệ sinh', 100000, 'Phí vệ sinh xe sau khi thuê.'),
        ('Phí giao xe tận nơi', 50000, 'Phí giao xe đến địa điểm yêu cầu.'),
        ('Phí phát sinh km', 2000, 'Phí cho mỗi km vượt quá giới hạn.'),
        ('Phí bảo hiểm cao cấp', 150000, 'Bảo hiểm toàn diện cho xe.'),
        ('Phí phụ trội giờ', 30000, 'Phí cho mỗi giờ thuê vượt quá thời gian hợp đồng.'),
        ('Phí cầu đường', 20000, 'Phí cầu đường phát sinh trong chuyến đi.'),
        ('Phí giữ xe đêm', 50000, 'Phí giữ xe qua đêm nếu không trả đúng giờ.'),
        ('Phí phạt trả trễ', 100000, 'Phí phạt nếu trả xe quá giờ quy định.'),
        ('Phí hỗ trợ khẩn cấp', 200000, 'Hỗ trợ khẩn cấp như thay lốp, kéo xe.'),
        ('Phí đổ đầy bình xăng', 10000, 'Phí dịch vụ đổ đầy bình xăng nếu khách không tự đổ.')
    ]
    for i, (ten, gia, mo_ta) in enumerate(phu_phi_list):
        sql_statements.append(f"INSERT INTO phu_phi (ten, gia, mo_ta) VALUES (N'{escape_sql_string(ten)}', {gia}, N'{escape_sql_string(mo_ta)}');")
        phu_phi_ids.append(i + 1)

    # --- 12. oto (cars) ---
    num_oto = 50
    oto_ids = []
    truyen_dong_list = ['Tự động', 'Số sàn']
    loai_nhien_lieu_list = ['Xăng', 'Dầu', 'Điện']
    
    current_oto_id = 1
    for i in range(num_oto):
        nam_san_xuat = random.randint(2018, 2024)
        truyen_dong = escape_sql_string(random.choice(truyen_dong_list))
        loai_nhien_lieu = escape_sql_string(random.choice(loai_nhien_lieu_list))
        muc_tieu_thu = random.randint(5, 12)
        bien_so = f"{random.randint(29, 99)}{chr(random.randint(65, 90))}-{random.randint(100, 999)}.{random.randint(10, 99)}"
        trang_thai = random.choice([0, 1, 2, 3]) # 0: Unavailable, 1: Available
        gia = random.randint(500000, 2500000) # Price per day
        ngay_tao = random_date(datetime(2023, 1, 1), datetime(2024, 1, 1)).strftime('%Y-%m-%d %H:%M:%S')
        mo_ta = escape_sql_string('Xe trong tình trạng tốt, thường xuyên bảo dưỡng và phù hợp cho gia đình.') if trang_thai == 1 else escape_sql_string('Xe đang được bảo trì hoặc đã được thuê dài hạn.')
        mau_xe_id = random.choice(mau_xe_ids)
        dia_chi_id = random.choice(dia_chi_ids)
        doi_tac_id = random.choice(doi_tac_ids)
        sql_statements.append(f"INSERT INTO oto (nam_san_xuat, truyen_dong, loai_nhien_lieu, muc_tieu_thu, bien_so, trang_thai, gia, ngay_tao, mo_ta, mau_xe_id, dia_chi_id, doi_tac_id) VALUES ({nam_san_xuat}, N'{truyen_dong}', N'{loai_nhien_lieu}', {muc_tieu_thu}, '{bien_so}', {trang_thai}, {gia}, '{ngay_tao}', N'{mo_ta}', {mau_xe_id}, {dia_chi_id}, '{doi_tac_id}');")
        oto_ids.append(current_oto_id)
        current_oto_id += 1

    # --- 13. hop_dong_cho_thue (lease contracts) ---
    num_hop_dong_cho_thue = 50
    hop_dong_cho_thue_ids = []
    
    current_hop_dong_cho_thue_id = 1
    for i in range(num_hop_dong_cho_thue):
        ngay_bat_dau = random_date(datetime(2023, 1, 1), datetime(2024, 6, 30))
        ngay_ket_thuc = ngay_bat_dau + timedelta(days=random.randint(30, 365))
        ghi_chu = escape_sql_string('Hợp đồng cho thuê định kỳ giữa công ty và đối tác.')
        phan_tram_cua_doi_tac = random.randint(60, 80)
        ngay_tao = random_date(ngay_bat_dau - timedelta(days=10), ngay_bat_dau - timedelta(days=1)).strftime('%Y-%m-%d %H:%M:%S')
        oto_id = random.choice(oto_ids)
        trang_thai = random.choice([0, 1, 2]) # 0: ok, 1: huy, 2: het_han
        quan_ly_id = random.choice(quan_ly_ids)
        sql_statements.append(f"INSERT INTO hop_dong_cho_thue (id, ngay_bat_dau, ngay_ket_thuc, ghi_chu, phan_tram_cua_doi_tac, ngay_tao, oto_id, trang_thai, quan_ly_id) VALUES ({current_hop_dong_cho_thue_id}, '{ngay_bat_dau.strftime('%Y-%m-%d %H:%M:%S')}', '{ngay_ket_thuc.strftime('%Y-%m-%d %H:%M:%S')}', N'{ghi_chu}', {phan_tram_cua_doi_tac}, '{ngay_tao}', {oto_id}, {trang_thai}, '{quan_ly_id}');")
        hop_dong_cho_thue_ids.append(current_hop_dong_cho_thue_id)
        current_hop_dong_cho_thue_id += 1

    # --- 14. tien_nghi_duoc_chon (selected amenities) ---
    num_tien_nghi_duoc_chon = 75 # Approx 1-2 amenities per car on average for selected cars
    current_tien_nghi_duoc_chon_id = 1
    for i in range(num_tien_nghi_duoc_chon):
        oto_id = random.choice(oto_ids)
        tien_nghi_id = random.choice(tien_nghi_ids)
        sql_statements.append(f"INSERT INTO tien_nghi_duoc_chon (id, oto_id, tien_nghi_id) VALUES ({current_tien_nghi_duoc_chon_id}, {oto_id}, {tien_nghi_id});")
        current_tien_nghi_duoc_chon_id += 1

    # --- 15. anh_cua_xe (car images) ---
    num_anh_cua_xe = 50 # At least 1 image per car for 50 cars
    current_anh_cua_xe_id = 1
    car_image_ids_used = set() # To ensure unique (id, oto_id) pairs if needed, though schema just needs unique ID
    
    for i in range(num_anh_cua_xe):
        anh_id = random.choice(car_image_anh_ids_pool) # Pick from car image pool
        
        # Ensure 'id' for anh_cua_xe is unique and references anh.id
        while anh_id in car_image_ids_used:
            anh_id = random.choice(car_image_anh_ids_pool)
        car_image_ids_used.add(anh_id)

        giay_to_xe = random.random() < 0.2 # 20% chance of being a document
        oto_id = random.choice(oto_ids)
        thumnail = random.random() < 0.5 # 50% chance of being a thumbnail
        sql_statements.append(f"INSERT INTO anh_cua_xe (id, giay_to_xe, oto_id, thumnail) VALUES ({anh_id}, {giay_to_xe}, {oto_id}, {thumnail});")
        current_anh_cua_xe_id += 1

    # --- 16. hop_dong_thue (rental contracts) ---
    num_hop_dong_thue = 50
    hop_dong_thue_ids = []
    trang_thai_options = [0, 1, 2, 3] # 0: Chờ xác nhận, 1: Đang thuê, 2: Đã hoàn thành, 3: Đã hủy
    
    current_hop_dong_thue_id = 1
    for i in range(num_hop_dong_thue):
        thoi_gian_nhan = random_date(datetime(2024, 1, 1), datetime(2025, 7, 30))
        rental_duration_days = random.randint(1, 15)
        thoi_gian_tra = thoi_gian_nhan + timedelta(days=rental_duration_days)
        
        trang_thai = random.choice(trang_thai_options)
        
        oto_id = random.choice(oto_ids)
        # Approximate the daily rental price from oto table, for simplicity using a random range
        oto_gia_daily = random.randint(500000, 2500000)
        gia = oto_gia_daily * rental_duration_days
        
        mo_ta = escape_sql_string('Hợp đồng thuê xe cá nhân.')
        
        check_in = 'NULL'
        check_out = 'NULL'
        khach_hang_danh_gia_chu = 'NULL'
        khach_hang_danh_gia_so = 'NULL'
        ngay_danh_gia = 'NULL'
        da_thanh_toan_cho_doi_tac = 'FALSE'
        
        if trang_thai == 1: # Đang thuê
            check_in_actual = random_date(thoi_gian_nhan - timedelta(minutes=10), thoi_gian_nhan + timedelta(minutes=10))
            check_in = f"'{check_in_actual.strftime('%Y-%m-%d %H:%M:%S')}'"
        elif trang_thai == 2: # Đã hoàn thành
            check_in_actual = random_date(thoi_gian_nhan - timedelta(minutes=10), thoi_gian_nhan + timedelta(minutes=10))
            check_in = f"'{check_in_actual.strftime('%Y-%m-%d %H:%M:%S')}'"
            check_out_actual = random_date(thoi_gian_tra - timedelta(minutes=30), thoi_gian_tra + timedelta(minutes=30))
            check_out = f"'{check_out_actual.strftime('%Y-%m-%d %H:%M:%S')}'"
            khach_hang_danh_gia_chu_text = escape_sql_string('Rất hài lòng về xe và dịch vụ, sẽ thuê lại!') if random.random() < 0.7 else escape_sql_string('Chất lượng xe tạm được, có thể cải thiện thêm.')
            khach_hang_danh_gia_chu = f"N'{khach_hang_danh_gia_chu_text}'"
            khach_hang_danh_gia_so = random.randint(3, 5)
            ngay_danh_gia = f"'{random_date(check_out_actual, check_out_actual + timedelta(days=2)).strftime('%Y-%m-%d %H:%M:%S')}'"
            da_thanh_toan_cho_doi_tac = random.choice(['TRUE', 'FALSE'])
            
        elif trang_thai == 3: # Đã hủy
            mo_ta = escape_sql_string('Hợp đồng đã bị hủy do thay đổi kế hoạch.')
            
            
        if trang_thai >= 2: trang_thai = trang_thai - 1

        khach_hang_id = random.choice(khach_hang_ids)
        sql_statements.append(f"INSERT INTO hop_dong_thue (id, thoi_gian_nhan, thoi_gian_tra, trang_thai, gia, mo_ta, check_in, check_out, khach_hang_danh_gia_chu, khach_hang_danh_gia_so, ngay_danh_gia, da_thanh_toan_cho_doi_tac, oto_id, khach_hang_id) VALUES ({current_hop_dong_thue_id}, '{thoi_gian_nhan.strftime('%Y-%m-%d %H:%M:%S')}', '{thoi_gian_tra.strftime('%Y-%m-%d %H:%M:%S')}', {trang_thai}, {gia}, N'{mo_ta}', {check_in}, {check_out}, {khach_hang_danh_gia_chu}, {khach_hang_danh_gia_so}, {ngay_danh_gia}, {da_thanh_toan_cho_doi_tac}, {oto_id}, '{khach_hang_id}');")
        hop_dong_thue_ids.append(current_hop_dong_thue_id)
        current_hop_dong_thue_id += 1

    # --- 17. anh_cua_khach (customer images) ---
    num_anh_cua_khach = 50 # At least 1 image per contract for 50 contracts
    current_anh_cua_khach_id = 1
    customer_image_ids_used = set() # To ensure unique (id, hop_dong_thue_id) pairs if needed
    
    for i in range(num_anh_cua_khach):
        anh_id = random.choice(customer_image_anh_ids_pool) # Pick from customer image pool
        while anh_id in customer_image_ids_used:
            anh_id = random.choice(customer_image_anh_ids_pool)
        customer_image_ids_used.add(anh_id)

        hop_dong_thue_id = random.choice(hop_dong_thue_ids)
        sql_statements.append(f"INSERT INTO anh_cua_khach (id, hop_dong_thue_id) VALUES ({anh_id}, {hop_dong_thue_id});")
        current_anh_cua_khach_id += 1

    # --- 18. hoa_don (invoices) ---
    num_hoa_don = 50
    hoa_don_ids = []
    phuong_thuc_thanh_toan_list = ['Chuyển khoản', 'Tiền mặt', 'Thẻ tín dụng', 'Ví điện tử']
    
    current_hoa_don_id = 1
    for i in range(num_hoa_don):
        # We need a hop_dong_thue that is likely "completed" (trang_thai = 3) for an invoice
        # For synthetic data, we can just randomly pick, or ensure we pick one that was set to 3
        # For simplicity in this generator, we'll pick from all, assuming logical consistency check is done on DB level
        hop_dong_thue_id = random.choice(hop_dong_thue_ids) 
        
        # Approximate the total price based on a random factor of car rental price
        tong_tien = random.randint(500000, 10000000)
        
        ngay_thanh_toan = random_date(datetime(2024, 7, 1), datetime(2025, 8, 1)).strftime('%Y-%m-%d %H:%M:%S')
        phuong_thuc_thanh_toan = escape_sql_string(random.choice(phuong_thuc_thanh_toan_list))
        ghi_chu = escape_sql_string('Hóa đơn thuê xe và các dịch vụ đi kèm.')
        nhan_vien_id = random.choice(nhan_vien_ids)
        sql_statements.append(f"INSERT INTO hoa_don (id, tong_tien, ngay_thanh_toan, phuong_thuc_thanh_toan, ghi_chu, hop_dong_thue_id, nhan_vien_id) VALUES ({current_hoa_don_id}, {tong_tien}, '{ngay_thanh_toan}', N'{phuong_thuc_thanh_toan}', N'{ghi_chu}', {hop_dong_thue_id}, '{nhan_vien_id}');")
        hoa_don_ids.append(current_hoa_don_id)
        current_hoa_don_id += 1

    # --- 19. phu_phi_duoc_chon (selected surcharges) ---
    num_phu_phi_duoc_chon = 75 # Approx 1-2 surcharges per invoice
    current_phu_phi_duoc_chon_id = 1
    for i in range(num_phu_phi_duoc_chon):
        phu_phi_id = random.choice(phu_phi_ids)
        hoa_don_id = random.choice(hoa_don_ids)
        gia_phu_phi = random.randint(10000, 200000) # Re-estimate for simplicity
        so_luong = random.randint(1, 3)
        sql_statements.append(f"INSERT INTO phu_phi_duoc_chon (id, gia, so_luong, phu_phi_id, hoa_don_id) VALUES ({current_phu_phi_duoc_chon_id}, {gia_phu_phi}, {so_luong}, {phu_phi_id}, {hoa_don_id});")
        current_phu_phi_duoc_chon_id += 1

    # --- 20. hoa_don_doi_tac (partner invoices) ---
    num_hoa_don_doi_tac = 50
    current_hoa_don_doi_tac_id = 1
    for i in range(num_hoa_don_doi_tac):
        hop_dong_cho_thue_id = random.choice(hop_dong_cho_thue_ids)
        
        # Re-estimate for simplicity
        tong_tien_doi_tac = random.randint(1000000, 5000000)
        
        ngay_bat_dau_hd = random_date(datetime(2024, 1, 1), datetime(2024, 6, 30))
        ngay_ket_thuc_hd = ngay_bat_dau_hd + timedelta(days=random.randint(7, 30))
        ngay_thanh_toan = ngay_ket_thuc_hd + timedelta(days=random.randint(1, 7))
        
        phuong_thuc_thanh_toan = escape_sql_string(random.choice(phuong_thuc_thanh_toan_list))
#         phan_tram_cua_doi_tac = random.randint(60, 80)
        ghi_chu = escape_sql_string('Thanh toán tiền thuê xe cho đối tác theo hợp đồng.')
        nhan_vien_id = random.choice(nhan_vien_ids)
        
        sql_statements.append(f"INSERT INTO hoa_don_doi_tac (id, tong_tien, ngay_bat_dau, ngay_ket_thuc, ngay_thanh_toan, phuong_thuc_thanh_toan, ghi_chu, nhan_vien_id, hop_dong_cho_thue_id) VALUES ({current_hoa_don_doi_tac_id}, {tong_tien_doi_tac}, '{ngay_bat_dau_hd.strftime('%Y-%m-%d %H:%M:%S')}', '{ngay_ket_thuc_hd.strftime('%Y-%m-%d %H:%M:%S')}', '{ngay_thanh_toan.strftime('%Y-%m-%d %H:%M:%S')}', N'{phuong_thuc_thanh_toan}', N'{ghi_chu}', '{nhan_vien_id}', {hop_dong_cho_thue_id});")
        current_hoa_don_doi_tac_id += 1

    # --- 21. danh_sach_den (blacklist) ---
    num_danh_sach_den = 10 # Only a few blacklisted customers, as it's a "bad" event
    current_danh_sach_den_id = 1
    for i in range(num_danh_sach_den):
        ngay_them = random_date(datetime(2024, 1, 1), datetime(2025, 7, 30)).strftime('%Y-%m-%d %H:%M:%S')
        ly_do_list = [
            'Trả xe trễ 1 ngày', 'Không đổ đầy bình xăng khi trả xe', 'Gây hư hỏng nhỏ cho xe',
            'Không liên lạc được khi quá hạn hợp đồng', 'Vi phạm hợp đồng sử dụng xe',
            'Không cung cấp đủ giấy tờ cần thiết', 'Thái độ không hợp tác'
        ]
        ly_do = escape_sql_string(random.choice(ly_do_list))
        trang_thai = random.choice([0, 1, 2]) # 0: Đang bị theo dõi, 1: Đã xử lý/Hết hạn theo dõi
        khach_hang_id = random.choice(khach_hang_ids)
        quan_ly_id = random.choice(quan_ly_ids)
        hop_dong_thue_id = random.choice(hop_dong_thue_ids)
        sql_statements.append(f"INSERT INTO danh_sach_den (id, ngay_them, ly_do, trang_thai, khach_hang_id, quan_ly_id, hop_dong_thue_id) VALUES ({current_danh_sach_den_id}, '{ngay_them}', N'{ly_do}', {trang_thai}, '{khach_hang_id}', '{quan_ly_id}', {hop_dong_thue_id});")
        current_danh_sach_den_id += 1

    # --- 22. tai_san_cam_co (collateral) ---
    num_tai_san_cam_co = 30 # Some contracts might have collateral
    current_tai_san_cam_co_id = 1
    loai_tai_san_list = ['Giấy tờ tùy thân', 'Xe máy', 'Điện thoại', 'Tiền mặt', 'Giấy tờ có giá trị']
    
    for i in range(num_tai_san_cam_co):
        ten_tai_san = random.choice(['Giấy phép lái xe', 'Chứng minh nhân dân', 'Sổ hộ khẩu', 'Xe máy Honda', 'Điện thoại iPhone', '5,000,000 VNĐ'])
        if 'Giấy phép lái xe' in ten_tai_san or 'Chứng minh nhân dân' in ten_tai_san or 'Sổ hộ khẩu' in ten_tai_san:
            loai_tai_san = 'Giấy tờ tùy thân'
            gia = 0
        elif 'Xe máy' in ten_tai_san:
            loai_tai_san = 'Xe máy'
            gia = random.randint(10000000, 50000000)
        elif 'Điện thoại' in ten_tai_san:
            loai_tai_san = 'Điện thoại'
            gia = random.randint(5000000, 20000000)
        elif 'Tiền mặt' in ten_tai_san:
            loai_tai_san = 'Tiền mặt'
            gia = random.randint(2000000, 10000000)
        else:
            loai_tai_san = random.choice(loai_tai_san_list)
            gia = random.randint(1000000, 50000000)

        mo_ta = escape_sql_string('Tài sản thế chấp cho hợp đồng thuê xe.')
        trang_thai = random.choice([0, 1]) # 0: Đang giữ, 1: Đã trả lại
        
        thoi_gian_nhan_tsc = random_date(datetime(2024, 1, 1), datetime(2025, 7, 30))
        thoi_gian_tra_tsc = 'NULL'
        if trang_thai == 1:
            thoi_gian_tra_tsc_actual = random_date(thoi_gian_nhan_tsc, thoi_gian_nhan_tsc + timedelta(days=random.randint(1,15)))
            thoi_gian_tra_tsc = f"'{thoi_gian_tra_tsc_actual.strftime('%Y-%m-%d %H:%M:%S')}'"
        
        hop_dong_thue_id = random.choice(hop_dong_thue_ids)
        khach_hang_id = random.choice(khach_hang_ids)
        doi_tac_id = random.choice(doi_tac_ids)
        
        sql_statements.append(f"INSERT INTO tai_san_cam_co (id, ten, loai_tai_san, gia, mo_ta, trang_thai, thoi_gian_tra, thoi_gian_nhan, hop_dong_thue_id, khach_hang_id, doi_tac_id) VALUES ({current_tai_san_cam_co_id}, N'{escape_sql_string(ten_tai_san)}', N'{escape_sql_string(loai_tai_san)}', {gia}, N'{mo_ta}', {trang_thai}, {thoi_gian_tra_tsc}, '{thoi_gian_nhan_tsc.strftime('%Y-%m-%d %H:%M:%S')}', {hop_dong_thue_id}, '{khach_hang_id}', '{doi_tac_id}');")
        current_tai_san_cam_co_id += 1

    # Re-enable foreign key checks
    sql_statements.append("SET FOREIGN_KEY_CHECKS = 1;")
    
    # Write to file instead of printing
    with open("insert_data.sql", "w", encoding="utf-8") as f:
        f.write("use db_car;\n")
        f.write("\n".join(sql_statements))
    return "SQL statements have been generated and saved to 'insert_data.sql'."

print(generate_data())