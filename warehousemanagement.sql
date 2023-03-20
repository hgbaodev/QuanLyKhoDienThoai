-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Mar 20, 2023 at 08:53 AM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.0.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `warehousemanagement`
--

-- --------------------------------------------------------

--
-- Table structure for table `ctkiemke`
--

CREATE TABLE `ctkiemke` (
  `makiemke` int(11) NOT NULL,
  `masanpham` int(11) NOT NULL,
  `soluong` int(11) NOT NULL,
  `chenlech` int(11) NOT NULL,
  `ghichu` varchar(255) NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `ctphieuchuyen`
--

CREATE TABLE `ctphieuchuyen` (
  `maphieuchuyen` int(11) NOT NULL,
  `masanpham` int(11) NOT NULL,
  `soluong` int(11) NOT NULL DEFAULT 0,
  `dongia` double NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `ctphieunhap`
--

CREATE TABLE `ctphieunhap` (
  `maphieunhap` int(11) NOT NULL,
  `masanpham` int(11) NOT NULL,
  `soluong` int(11) NOT NULL,
  `dongia` double NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `ctphieutrahang`
--

CREATE TABLE `ctphieutrahang` (
  `maphieutrahang` int(11) NOT NULL,
  `masanpham` int(11) NOT NULL,
  `soluong` int(11) NOT NULL,
  `dongia` double NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `ctphieuxuat`
--

CREATE TABLE `ctphieuxuat` (
  `maphieuxuat` int(11) NOT NULL,
  `masanpham` int(11) NOT NULL,
  `soluong` int(11) NOT NULL,
  `dongia` double NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `ctquyen`
--

CREATE TABLE `ctquyen` (
  `maquyen` int(11) NOT NULL,
  `manhomquyen` int(11) NOT NULL,
  `check` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `danhmucchucnang`
--

CREATE TABLE `danhmucchucnang` (
  `maquyen` int(11) NOT NULL,
  `tenquyen` varchar(255) NOT NULL DEFAULT '0',
  `hanhdong` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `donvitinh`
--

CREATE TABLE `donvitinh` (
  `madonvitinh` int(11) NOT NULL,
  `tendonvitinh` varchar(255) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `hanhdong`
--

CREATE TABLE `hanhdong` (
  `mahanhdong` int(11) NOT NULL,
  `tenhanhdong` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `hanhdong`
--

INSERT INTO `hanhdong` (`mahanhdong`, `tenhanhdong`) VALUES
(1, 'VIEW'),
(2, 'CREATE'),
(3, 'EDIT'),
(4, 'DELETE');

-- --------------------------------------------------------

--
-- Table structure for table `khachhang`
--

CREATE TABLE `khachhang` (
  `makh` int(11) NOT NULL,
  `tenkh` varchar(255) NOT NULL DEFAULT '0',
  `diachi` varchar(255) NOT NULL DEFAULT '0',
  `sdt` varchar(255) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `khohang`
--

CREATE TABLE `khohang` (
  `makhohang` int(11) NOT NULL,
  `tenkhohang` varchar(255) NOT NULL DEFAULT '0',
  `diachi` varchar(255) NOT NULL DEFAULT '0',
  `mota` varchar(255) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `khuvuvkho`
--

CREATE TABLE `khuvuvkho` (
  `makhuvuckho` int(11) NOT NULL,
  `tenkhuvuc` varchar(255) DEFAULT NULL,
  `makhohang` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `loaihang`
--

CREATE TABLE `loaihang` (
  `maloaihang` int(11) NOT NULL,
  `tenloaihang` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `nhacungcap`
--

CREATE TABLE `nhacungcap` (
  `mancc` int(11) NOT NULL,
  `tenncc` varchar(255) NOT NULL DEFAULT '0',
  `diachi` varchar(255) NOT NULL DEFAULT '0',
  `email` varchar(255) NOT NULL DEFAULT '0',
  `sdt` varchar(255) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `nhanvien`
--

CREATE TABLE `nhanvien` (
  `manv` int(11) NOT NULL,
  `hoten` varchar(255) NOT NULL DEFAULT '0',
  `gioitinh` varchar(255) NOT NULL DEFAULT '0',
  `ngaysinh` date DEFAULT NULL,
  `email` varchar(255) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `nhomquyen`
--

CREATE TABLE `nhomquyen` (
  `manhomquyen` int(11) NOT NULL,
  `tennhomquyen` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `phieuchuyen`
--

CREATE TABLE `phieuchuyen` (
  `maphieuchuyen` int(11) NOT NULL,
  `thogian` date DEFAULT curdate(),
  `trangthai` int(11) DEFAULT 0,
  `makhochuyendi` int(11) NOT NULL,
  `makhochuyenden` int(11) NOT NULL,
  `nguoitaophieu` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `phieukiemke`
--

CREATE TABLE `phieukiemke` (
  `maphieukiemke` int(11) NOT NULL,
  `thoigian` date DEFAULT curdate(),
  `nguoitao` int(11) NOT NULL,
  `makhothongke` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `phieunhap`
--

CREATE TABLE `phieunhap` (
  `maphieunhap` int(11) NOT NULL,
  `thoigian` date DEFAULT curdate(),
  `trangthai` int(11) DEFAULT 0,
  `makhonhap` int(11) DEFAULT NULL,
  `mancc` int(11) DEFAULT NULL,
  `manv` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `phieutrahang`
--

CREATE TABLE `phieutrahang` (
  `maphieutrahang` int(11) NOT NULL,
  `thoigian` date DEFAULT curdate(),
  `nguoitao` int(11) NOT NULL,
  `trangthai` int(11) DEFAULT 0,
  `makhotrahang` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `phieuxuat`
--

CREATE TABLE `phieuxuat` (
  `maphieuxuat` int(11) NOT NULL,
  `thoigian` date DEFAULT curdate(),
  `trangthai` int(11) DEFAULT 0,
  `makhoxuat` int(11) NOT NULL,
  `nguoitaophieuxuat` int(11) NOT NULL DEFAULT 0,
  `makh` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `sanpham`
--

CREATE TABLE `sanpham` (
  `masanpham` int(11) NOT NULL,
  `tensanpham` varchar(255) NOT NULL DEFAULT '0',
  `xuatxu` varchar(255) NOT NULL DEFAULT '0',
  `gianhap` double NOT NULL DEFAULT 0,
  `giaban` double NOT NULL DEFAULT 0,
  `hinhanh` varchar(255) NOT NULL DEFAULT '0',
  `madonvitinh` int(11) NOT NULL DEFAULT 0,
  `maloaihang` int(11) NOT NULL DEFAULT 0,
  `makhuvuc` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `taikhoan`
--

CREATE TABLE `taikhoan` (
  `manv` int(11) NOT NULL,
  `matkhau` varchar(255) NOT NULL DEFAULT '0',
  `trangthai` int(11) NOT NULL DEFAULT 0,
  `makhohang` int(11) NOT NULL DEFAULT 0,
  `manhomquyen` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `ctkiemke`
--
ALTER TABLE `ctkiemke`
  ADD PRIMARY KEY (`makiemke`,`masanpham`),
  ADD KEY `FK_ctkiemke_sanpham` (`masanpham`);

--
-- Indexes for table `ctphieuchuyen`
--
ALTER TABLE `ctphieuchuyen`
  ADD PRIMARY KEY (`maphieuchuyen`,`masanpham`),
  ADD KEY `FK_ctphieuchuyen_sanpham` (`masanpham`);

--
-- Indexes for table `ctphieunhap`
--
ALTER TABLE `ctphieunhap`
  ADD PRIMARY KEY (`maphieunhap`,`masanpham`),
  ADD KEY `FK__sanpham` (`masanpham`);

--
-- Indexes for table `ctphieutrahang`
--
ALTER TABLE `ctphieutrahang`
  ADD PRIMARY KEY (`maphieutrahang`,`masanpham`),
  ADD KEY `FK_ctphieutrahang_sanpham` (`masanpham`);

--
-- Indexes for table `ctphieuxuat`
--
ALTER TABLE `ctphieuxuat`
  ADD PRIMARY KEY (`maphieuxuat`,`masanpham`),
  ADD KEY `FK_ctphieuxuat_sanpham` (`masanpham`);

--
-- Indexes for table `ctquyen`
--
ALTER TABLE `ctquyen`
  ADD PRIMARY KEY (`maquyen`,`manhomquyen`),
  ADD KEY `FK_ctquyen_nhomquyen` (`manhomquyen`);

--
-- Indexes for table `danhmucchucnang`
--
ALTER TABLE `danhmucchucnang`
  ADD PRIMARY KEY (`maquyen`);

--
-- Indexes for table `donvitinh`
--
ALTER TABLE `donvitinh`
  ADD PRIMARY KEY (`madonvitinh`);

--
-- Indexes for table `hanhdong`
--
ALTER TABLE `hanhdong`
  ADD PRIMARY KEY (`mahanhdong`);

--
-- Indexes for table `khachhang`
--
ALTER TABLE `khachhang`
  ADD PRIMARY KEY (`makh`);

--
-- Indexes for table `khohang`
--
ALTER TABLE `khohang`
  ADD PRIMARY KEY (`makhohang`);

--
-- Indexes for table `khuvuvkho`
--
ALTER TABLE `khuvuvkho`
  ADD PRIMARY KEY (`makhuvuckho`),
  ADD KEY `FK_khuvuvkho_khohang` (`makhohang`);

--
-- Indexes for table `loaihang`
--
ALTER TABLE `loaihang`
  ADD PRIMARY KEY (`maloaihang`);

--
-- Indexes for table `nhacungcap`
--
ALTER TABLE `nhacungcap`
  ADD PRIMARY KEY (`mancc`);

--
-- Indexes for table `nhanvien`
--
ALTER TABLE `nhanvien`
  ADD PRIMARY KEY (`manv`);

--
-- Indexes for table `nhomquyen`
--
ALTER TABLE `nhomquyen`
  ADD PRIMARY KEY (`manhomquyen`);

--
-- Indexes for table `phieuchuyen`
--
ALTER TABLE `phieuchuyen`
  ADD PRIMARY KEY (`maphieuchuyen`),
  ADD KEY `FK_phieuchuyen_khohang` (`makhochuyendi`),
  ADD KEY `FK_phieuchuyen_khohang_2` (`makhochuyenden`),
  ADD KEY `FK_phieuchuyen_taikhoan` (`nguoitaophieu`);

--
-- Indexes for table `phieukiemke`
--
ALTER TABLE `phieukiemke`
  ADD PRIMARY KEY (`maphieukiemke`),
  ADD KEY `FK_phieukiemke_taikhoan` (`nguoitao`),
  ADD KEY `FK_phieukiemke_khohang` (`makhothongke`);

--
-- Indexes for table `phieunhap`
--
ALTER TABLE `phieunhap`
  ADD PRIMARY KEY (`maphieunhap`),
  ADD KEY `FK_phieunhap_nhacungcap` (`mancc`),
  ADD KEY `FK_phieunhap_taikhoan` (`manv`);

--
-- Indexes for table `phieutrahang`
--
ALTER TABLE `phieutrahang`
  ADD PRIMARY KEY (`maphieutrahang`),
  ADD KEY `FK_phieutrahang_taikhoan` (`nguoitao`),
  ADD KEY `FK_phieutrahang_khohang` (`makhotrahang`);

--
-- Indexes for table `phieuxuat`
--
ALTER TABLE `phieuxuat`
  ADD PRIMARY KEY (`maphieuxuat`),
  ADD KEY `FK_phieuxuat_khohang` (`makhoxuat`),
  ADD KEY `FK_phieuxuat_taikhoan` (`nguoitaophieuxuat`),
  ADD KEY `FK_phieuxuat_khachhang` (`makh`);

--
-- Indexes for table `sanpham`
--
ALTER TABLE `sanpham`
  ADD PRIMARY KEY (`masanpham`),
  ADD KEY `FK__donvitinh` (`madonvitinh`),
  ADD KEY `FK__loaihang` (`maloaihang`),
  ADD KEY `FK_sanpham_khuvuvkho` (`makhuvuc`);

--
-- Indexes for table `taikhoan`
--
ALTER TABLE `taikhoan`
  ADD PRIMARY KEY (`manv`),
  ADD KEY `FK__khohang` (`makhohang`),
  ADD KEY `FK_taikhoan_nhomquyen` (`manhomquyen`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `ctphieuchuyen`
--
ALTER TABLE `ctphieuchuyen`
  MODIFY `maphieuchuyen` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `danhmucchucnang`
--
ALTER TABLE `danhmucchucnang`
  MODIFY `maquyen` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `donvitinh`
--
ALTER TABLE `donvitinh`
  MODIFY `madonvitinh` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `hanhdong`
--
ALTER TABLE `hanhdong`
  MODIFY `mahanhdong` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `khachhang`
--
ALTER TABLE `khachhang`
  MODIFY `makh` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `khohang`
--
ALTER TABLE `khohang`
  MODIFY `makhohang` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `khuvuvkho`
--
ALTER TABLE `khuvuvkho`
  MODIFY `makhuvuckho` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `loaihang`
--
ALTER TABLE `loaihang`
  MODIFY `maloaihang` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `nhacungcap`
--
ALTER TABLE `nhacungcap`
  MODIFY `mancc` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `nhanvien`
--
ALTER TABLE `nhanvien`
  MODIFY `manv` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `nhomquyen`
--
ALTER TABLE `nhomquyen`
  MODIFY `manhomquyen` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `phieuchuyen`
--
ALTER TABLE `phieuchuyen`
  MODIFY `maphieuchuyen` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `phieukiemke`
--
ALTER TABLE `phieukiemke`
  MODIFY `maphieukiemke` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `phieunhap`
--
ALTER TABLE `phieunhap`
  MODIFY `maphieunhap` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `phieutrahang`
--
ALTER TABLE `phieutrahang`
  MODIFY `maphieutrahang` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `phieuxuat`
--
ALTER TABLE `phieuxuat`
  MODIFY `maphieuxuat` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `sanpham`
--
ALTER TABLE `sanpham`
  MODIFY `masanpham` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `ctkiemke`
--
ALTER TABLE `ctkiemke`
  ADD CONSTRAINT `FK_ctkiemke_phieukiemke` FOREIGN KEY (`makiemke`) REFERENCES `phieukiemke` (`maphieukiemke`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `FK_ctkiemke_sanpham` FOREIGN KEY (`masanpham`) REFERENCES `sanpham` (`masanpham`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `ctphieuchuyen`
--
ALTER TABLE `ctphieuchuyen`
  ADD CONSTRAINT `FK_ctphieuchuyen_phieuchuyen` FOREIGN KEY (`maphieuchuyen`) REFERENCES `phieuchuyen` (`maphieuchuyen`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `FK_ctphieuchuyen_sanpham` FOREIGN KEY (`masanpham`) REFERENCES `sanpham` (`masanpham`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `ctphieunhap`
--
ALTER TABLE `ctphieunhap`
  ADD CONSTRAINT `FK__phieunhap` FOREIGN KEY (`maphieunhap`) REFERENCES `phieunhap` (`maphieunhap`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `FK__sanpham` FOREIGN KEY (`masanpham`) REFERENCES `sanpham` (`masanpham`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `ctphieutrahang`
--
ALTER TABLE `ctphieutrahang`
  ADD CONSTRAINT `FK_ctphieutrahang_phieutrahang` FOREIGN KEY (`maphieutrahang`) REFERENCES `phieutrahang` (`maphieutrahang`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `FK_ctphieutrahang_sanpham` FOREIGN KEY (`masanpham`) REFERENCES `sanpham` (`masanpham`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `ctphieuxuat`
--
ALTER TABLE `ctphieuxuat`
  ADD CONSTRAINT `FK_ctphieuxuat_phieuxuat` FOREIGN KEY (`maphieuxuat`) REFERENCES `phieuxuat` (`maphieuxuat`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `FK_ctphieuxuat_sanpham` FOREIGN KEY (`masanpham`) REFERENCES `sanpham` (`masanpham`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `ctquyen`
--
ALTER TABLE `ctquyen`
  ADD CONSTRAINT `FK_ctquyen_danhmucchucnang` FOREIGN KEY (`maquyen`) REFERENCES `danhmucchucnang` (`maquyen`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `FK_ctquyen_nhomquyen` FOREIGN KEY (`manhomquyen`) REFERENCES `nhomquyen` (`manhomquyen`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `khuvuvkho`
--
ALTER TABLE `khuvuvkho`
  ADD CONSTRAINT `FK_khuvuvkho_khohang` FOREIGN KEY (`makhohang`) REFERENCES `khohang` (`makhohang`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `phieuchuyen`
--
ALTER TABLE `phieuchuyen`
  ADD CONSTRAINT `FK_phieuchuyen_khohang` FOREIGN KEY (`makhochuyendi`) REFERENCES `khohang` (`makhohang`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `FK_phieuchuyen_khohang_2` FOREIGN KEY (`makhochuyenden`) REFERENCES `khohang` (`makhohang`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `FK_phieuchuyen_taikhoan` FOREIGN KEY (`nguoitaophieu`) REFERENCES `taikhoan` (`manv`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `phieukiemke`
--
ALTER TABLE `phieukiemke`
  ADD CONSTRAINT `FK_phieukiemke_khohang` FOREIGN KEY (`makhothongke`) REFERENCES `khohang` (`makhohang`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `FK_phieukiemke_taikhoan` FOREIGN KEY (`nguoitao`) REFERENCES `taikhoan` (`manv`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `phieunhap`
--
ALTER TABLE `phieunhap`
  ADD CONSTRAINT `FK_phieunhap_nhacungcap` FOREIGN KEY (`mancc`) REFERENCES `nhacungcap` (`mancc`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `FK_phieunhap_taikhoan` FOREIGN KEY (`manv`) REFERENCES `taikhoan` (`manv`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `phieutrahang`
--
ALTER TABLE `phieutrahang`
  ADD CONSTRAINT `FK_phieutrahang_khohang` FOREIGN KEY (`makhotrahang`) REFERENCES `khohang` (`makhohang`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `FK_phieutrahang_taikhoan` FOREIGN KEY (`nguoitao`) REFERENCES `taikhoan` (`manv`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `phieuxuat`
--
ALTER TABLE `phieuxuat`
  ADD CONSTRAINT `FK_phieuxuat_khachhang` FOREIGN KEY (`makh`) REFERENCES `khachhang` (`makh`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `FK_phieuxuat_khohang` FOREIGN KEY (`makhoxuat`) REFERENCES `khohang` (`makhohang`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `FK_phieuxuat_taikhoan` FOREIGN KEY (`nguoitaophieuxuat`) REFERENCES `taikhoan` (`manv`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `sanpham`
--
ALTER TABLE `sanpham`
  ADD CONSTRAINT `FK__donvitinh` FOREIGN KEY (`madonvitinh`) REFERENCES `donvitinh` (`madonvitinh`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `FK__loaihang` FOREIGN KEY (`maloaihang`) REFERENCES `loaihang` (`maloaihang`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `FK_sanpham_khuvuvkho` FOREIGN KEY (`makhuvuc`) REFERENCES `khuvuvkho` (`makhuvuckho`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `taikhoan`
--
ALTER TABLE `taikhoan`
  ADD CONSTRAINT `FK__khohang` FOREIGN KEY (`makhohang`) REFERENCES `khohang` (`makhohang`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `FK__nhanvien` FOREIGN KEY (`manv`) REFERENCES `nhanvien` (`manv`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `FK_taikhoan_nhomquyen` FOREIGN KEY (`manhomquyen`) REFERENCES `nhomquyen` (`manhomquyen`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
