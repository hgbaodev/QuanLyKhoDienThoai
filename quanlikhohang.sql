-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 25, 2023 at 04:24 AM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 7.4.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `quanlikhohang`
--

-- --------------------------------------------------------

--
-- Table structure for table `chitietkiemke_sanpham`
--

CREATE TABLE `chitietkiemke_sanpham` (
  `makiemke` int(11) NOT NULL COMMENT 'Mã phiếu kiểm kê',
  `masanpham` int(11) NOT NULL COMMENT 'Mã sản phẩm thuộc phiếu',
  `maimei` varchar(100) NOT NULL COMMENT 'Mã imei thuộc phiếu',
  `trangthai` int(11) DEFAULT NULL COMMENT 'Trạng thái hiện tại của phiếu'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `ctkiemke`
--

CREATE TABLE `ctkiemke` (
  `maphieukiemmke` int(11) NOT NULL COMMENT 'Mã Phiếu kiểm kê',
  `masanpham` int(11) NOT NULL COMMENT 'Mã sản phẩm được kiểm kê',
  `soluong` int(11) NOT NULL COMMENT 'Số lượng sản phẩm khi kiểm kê',
  `chenhlech` int(11) NOT NULL COMMENT 'Lượng chênh lệch so với phiếu kiểm kê cùng mã sản phẩm trước',
  `ghichu` varchar(255) NOT NULL COMMENT 'Ghi chú cho phiếu'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `ctphieudoi`
--

CREATE TABLE `ctphieudoi` (
  `maphieudoi` tinyint(4) NOT NULL COMMENT 'Mã phiếu đổi',
  `imei_doi` varchar(100) NOT NULL COMMENT 'Mã imei máy đổi',
  `imei_moi` varchar(100) NOT NULL COMMENT 'Mã imei máy mới cho khách hàng',
  `lydo` varchar(100) DEFAULT NULL COMMENT 'Lý do đổi máy'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `ctphieunhap`
--

CREATE TABLE `ctphieunhap` (
  `maphieunhap` int(11) NOT NULL COMMENT 'Mã phiếu nhập',
  `maphienbansp` int(11) NOT NULL DEFAULT 0 COMMENT 'Mã phiên bản sản phẩm nhập',
  `soluong` int(11) NOT NULL DEFAULT 0 COMMENT 'Số lượng phiên bản sản phẩm',
  `dongia` int(11) NOT NULL DEFAULT 0 COMMENT 'Đơn giá phiên bản sản phẩm',
  `hinhthucnhap` tinyint(1) NOT NULL DEFAULT 0 COMMENT 'Hình thức nhập: lô hay từng máy'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `ctphieunhap`
--

INSERT INTO `ctphieunhap` (`maphieunhap`, `maphienbansp`, `soluong`, `dongia`, `hinhthucnhap`) VALUES
(1, 17, 3, 7000000, 1),
(1, 19, 5, 15000000, 0),
(2, 19, 50, 15000000, 0),
(3, 2, 2, 5000000, 1);

-- --------------------------------------------------------

--
-- Table structure for table `ctphieutra`
--

CREATE TABLE `ctphieutra` (
  `maphieutra` int(11) NOT NULL COMMENT 'Mã phiếu trả',
  `imei_tra` varchar(100) DEFAULT NULL COMMENT 'Mã imei của máy trả',
  `lydo` varchar(100) DEFAULT NULL COMMENT 'Lý do trả máy',
  `giathuvao` int(11) DEFAULT NULL COMMENT 'Giá thu lại sản phẩm'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `ctphieuxuat`
--

CREATE TABLE `ctphieuxuat` (
  `maphieuxuat` int(11) NOT NULL COMMENT 'Mã phiếu xuất',
  `maphienbansp` int(11) NOT NULL DEFAULT 0 COMMENT 'Mã phiên bản sản phẩm xuất kho',
  `soluong` int(11) NOT NULL DEFAULT 0 COMMENT 'Số lượng',
  `dongia` int(11) NOT NULL DEFAULT 0 COMMENT 'Đơn giá'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `ctquyen`
--

CREATE TABLE `ctquyen` (
  `manhomquyen` int(11) NOT NULL COMMENT 'Mã nhóm quyền',
  `machucnang` varchar(50) NOT NULL COMMENT 'Mã Chức năng',
  `hanhdong` varchar(50) NOT NULL COMMENT 'Tên hành động'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `ctquyen`
--

INSERT INTO `ctquyen` (`manhomquyen`, `machucnang`, `hanhdong`) VALUES
(1, 'donvitinh', 'create'),
(1, 'donvitinh', 'delete'),
(1, 'donvitinh', 'update'),
(1, 'donvitinh', 'view'),
(1, 'khuvuckho', 'create'),
(1, 'khuvuckho', 'delete'),
(1, 'khuvuckho', 'update'),
(1, 'khuvuckho', 'view'),
(1, 'kiemke', 'create'),
(1, 'kiemke', 'delete'),
(1, 'kiemke', 'update'),
(1, 'kiemke', 'view'),
(1, 'loaisanpham', 'create'),
(1, 'loaisanpham', 'delete'),
(1, 'loaisanpham', 'update'),
(1, 'loaisanpham', 'view'),
(1, 'nhacungcap', 'create'),
(1, 'nhacungcap', 'delete'),
(1, 'nhacungcap', 'update'),
(1, 'nhacungcap', 'view'),
(1, 'nhanvien', 'create'),
(1, 'nhanvien', 'delete'),
(1, 'nhanvien', 'update'),
(1, 'nhanvien', 'view'),
(1, 'nhaphang', 'create'),
(1, 'nhaphang', 'delete'),
(1, 'nhaphang', 'update'),
(1, 'nhaphang', 'view'),
(1, 'nhomquyen', 'create'),
(1, 'nhomquyen', 'delete'),
(1, 'nhomquyen', 'update'),
(1, 'nhomquyen', 'view'),
(1, 'sanpham', 'create'),
(1, 'sanpham', 'delete'),
(1, 'sanpham', 'update'),
(1, 'sanpham', 'view'),
(1, 'taikhoan', 'create'),
(1, 'taikhoan', 'delete'),
(1, 'taikhoan', 'update'),
(1, 'taikhoan', 'view'),
(1, 'xuathang', 'create'),
(1, 'xuathang', 'delete'),
(1, 'xuathang', 'update'),
(1, 'xuathang', 'view'),
(2, 'khuvuckho', 'view'),
(2, 'nhacungcap', 'view'),
(2, 'nhaphang', 'view'),
(2, 'sanpham', 'view'),
(4, 'donvitinh', 'view'),
(4, 'khuvuckho', 'view'),
(4, 'kiemke', 'view'),
(4, 'loaisanpham', 'view'),
(4, 'nhacungcap', 'view'),
(5, 'donvitinh', 'view'),
(5, 'khuvuckho', 'view'),
(5, 'kiemke', 'view'),
(6, 'khuvuckho', 'view'),
(6, 'kiemke', 'view'),
(6, 'loaisanpham', 'view'),
(6, 'nhacungcap', 'view'),
(6, 'nhanvien', 'view'),
(7, 'loaisanpham', 'create'),
(7, 'nhanvien', 'create'),
(7, 'sanpham', 'create'),
(7, 'xuathang', 'create'),
(8, 'donvitinh', 'view');

-- --------------------------------------------------------

--
-- Table structure for table `ctsanpham`
--

CREATE TABLE `ctsanpham` (
  `maimei` varchar(255) NOT NULL DEFAULT 'AUTO_INCREMENT' COMMENT 'Mã imei của máy',
  `maphienbansp` int(11) NOT NULL COMMENT 'Mã phiên bản sản phẩm của máy',
  `maphieunhap` int(11) NOT NULL COMMENT 'Mã phiếu nhập khi nhập máy về',
  `maphieuxuat` int(11) DEFAULT NULL COMMENT 'Mã phiếu xuất khi xuất kho (Nullable)',
  `tinhtrang` int(11) NOT NULL COMMENT 'Tình trạng hiện tại của máy'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `ctsanpham`
--

INSERT INTO `ctsanpham` (`maimei`, `maphienbansp`, `maphieunhap`, `maphieuxuat`, `tinhtrang`) VALUES
('356285077460984', 19, 2, NULL, 1),
('356285077460985', 19, 2, NULL, 1),
('356285077460986', 19, 2, NULL, 1),
('356285077460987', 19, 2, NULL, 1),
('356285077460988', 19, 2, NULL, 1),
('356285077460989', 19, 2, NULL, 1),
('356285077460990', 19, 2, NULL, 1),
('356285077460991', 19, 2, NULL, 1),
('356285077460992', 19, 2, NULL, 1),
('356285077460993', 19, 2, NULL, 1),
('356285077460994', 19, 2, NULL, 1),
('356285077460995', 19, 2, NULL, 1),
('356285077460996', 19, 2, NULL, 1),
('356285077460997', 19, 2, NULL, 1),
('356285077460998', 19, 2, NULL, 1),
('356285077460999', 19, 2, NULL, 1),
('356285077461000', 19, 2, NULL, 1),
('356285077461001', 19, 2, NULL, 1),
('356285077461002', 19, 2, NULL, 1),
('356285077461003', 19, 2, NULL, 1),
('356285077461004', 19, 2, NULL, 1),
('356285077461005', 19, 2, NULL, 1),
('356285077461006', 19, 2, NULL, 1),
('356285077461007', 19, 2, NULL, 1),
('356285077461008', 19, 2, NULL, 1),
('356285077461009', 19, 2, NULL, 1),
('356285077461010', 19, 2, NULL, 1),
('356285077461011', 19, 2, NULL, 1),
('356285077461012', 19, 2, NULL, 1),
('356285077461013', 19, 2, NULL, 1),
('356285077461014', 19, 2, NULL, 1),
('356285077461015', 19, 2, NULL, 1),
('356285077461016', 19, 2, NULL, 1),
('356285077461017', 19, 2, NULL, 1),
('356285077461018', 19, 2, NULL, 1),
('356285077461019', 19, 2, NULL, 1),
('356285077461020', 19, 2, NULL, 1),
('356285077461021', 19, 2, NULL, 1),
('356285077461022', 19, 2, NULL, 1),
('356285077461023', 19, 2, NULL, 1),
('356285077461024', 19, 2, NULL, 1),
('356285077461025', 19, 2, NULL, 1),
('356285077461026', 19, 2, NULL, 1),
('356285077461027', 19, 2, NULL, 1),
('356285077461028', 19, 2, NULL, 1),
('356285077461029', 19, 2, NULL, 1),
('356285077461030', 19, 2, NULL, 1),
('356285077461031', 19, 2, NULL, 1),
('356285077461032', 19, 2, NULL, 1),
('356285077461033', 19, 2, NULL, 1),
('356543072290260', 17, 1, NULL, 1),
('356543076290260', 19, 1, NULL, 1),
('356543076290261', 19, 1, NULL, 1),
('356543076290262', 19, 1, NULL, 1),
('356543076290263', 19, 1, NULL, 1),
('356543076290264', 19, 1, NULL, 1),
('356543077290260', 17, 1, NULL, 1),
('356543078290260', 17, 1, NULL, 1),
('786285077460984', 2, 3, NULL, 1),
('786285077465684', 2, 3, NULL, 1);

-- --------------------------------------------------------

--
-- Table structure for table `danhmucchucnang`
--

CREATE TABLE `danhmucchucnang` (
  `machucnang` varchar(50) NOT NULL COMMENT 'Mã chức năng',
  `tenchucnang` varchar(255) NOT NULL COMMENT 'Tên chức năng',
  `trangthai` int(11) NOT NULL COMMENT 'Trạng thái hiện tại của chức năng'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `danhmucchucnang`
--

INSERT INTO `danhmucchucnang` (`machucnang`, `tenchucnang`, `trangthai`) VALUES
('donvitinh', 'Quản lý đơn vị tính', 0),
('khuvuckho', 'Quản lý khu vực kho', 0),
('kiemke', 'Quản lý kiểm kê', 0),
('loaisanpham', 'Quản lý loại sản phẩm', 0),
('nhacungcap', 'Quản lý nhà cung cấp', 0),
('nhanvien', 'Quản lý nhân viên', 0),
('nhaphang', 'Quản lý nhập hàng', 0),
('nhomquyen', 'Quản lý nhóm quyền', 0),
('sanpham', 'Quản lý sản phẩm', 0),
('taikhoan', 'Quản lý tài khoản', 0),
('xuathang', 'Quản lý xuất hàng', 0);

-- --------------------------------------------------------

--
-- Table structure for table `dungluongram`
--

CREATE TABLE `dungluongram` (
  `madlram` int(11) NOT NULL COMMENT 'Mã dung lượng Ram',
  `kichthuocram` int(11) DEFAULT NULL COMMENT 'Kích thước Ram',
  `trangthai` tinyint(4) DEFAULT 1 COMMENT 'Trạng thái hiện tại'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `dungluongram`
--

INSERT INTO `dungluongram` (`madlram`, `kichthuocram`, `trangthai`) VALUES
(1, 3, 1),
(2, 2, 1),
(3, 4, 1),
(4, 6, 1),
(5, 8, 1);

-- --------------------------------------------------------

--
-- Table structure for table `dungluongrom`
--

CREATE TABLE `dungluongrom` (
  `madlrom` int(11) NOT NULL COMMENT 'Mã dung lượng Rom',
  `kichthuocrom` int(11) DEFAULT NULL COMMENT 'Kích thước Rom',
  `trangthai` tinyint(4) DEFAULT 1 COMMENT 'Trạng thái hiện tại'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `dungluongrom`
--

INSERT INTO `dungluongrom` (`madlrom`, `kichthuocrom`, `trangthai`) VALUES
(1, 32, 1),
(2, 64, 1),
(3, 128, 1),
(4, 256, 1),
(5, 512, 1);

-- --------------------------------------------------------

--
-- Table structure for table `hedieuhanh`
--

CREATE TABLE `hedieuhanh` (
  `mahedieuhanh` int(11) NOT NULL COMMENT 'Mã hệ điều hành',
  `tenhedieuhanh` varchar(255) NOT NULL COMMENT 'Tên hệ điều hành',
  `trangthai` tinyint(1) DEFAULT NULL COMMENT 'Trạng thái hiện tại'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `hedieuhanh`
--

INSERT INTO `hedieuhanh` (`mahedieuhanh`, `tenhedieuhanh`, `trangthai`) VALUES
(1, 'Android', 1),
(2, 'IOS', 1);

-- --------------------------------------------------------

--
-- Table structure for table `khachhang`
--

CREATE TABLE `khachhang` (
  `makh` int(11) NOT NULL COMMENT 'Mã khách hàng',
  `tenkhachhang` varchar(255) NOT NULL COMMENT 'Họ tên khách hàng',
  `diachi` varchar(255) NOT NULL COMMENT 'Địa chỉ của khách hàng',
  `sdt` varchar(255) NOT NULL COMMENT 'Số điện thoại của khách hàng',
  `trangthai` int(11) NOT NULL COMMENT 'Trạng thái hiện tại của khách hàng',
  `ngaythamgia` date NOT NULL DEFAULT current_timestamp() COMMENT 'Ngày  tạo tài khoản'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `khachhang`
--

INSERT INTO `khachhang` (`makh`, `tenkhachhang`, `diachi`, `sdt`, `trangthai`, `ngaythamgia`) VALUES
(1, 'Nguyễn Văn A', 'Gia Đức, Ân Đức, Hoài Ân, Bình Định', '0387913347', 1, '2023-04-24'),
(2, 'Trần Nhất Nhất', '205 Trần Hưng Đạo, phường 10, quận 5, TPHCM', '0123456789', 1, '2023-04-24'),
(3, 'Hoàng Gia Bảo', 'Khoa Trường, Hoài Ân, Bình Định', '0987654322', 1, '2023-04-24'),
(4, 'Đinh Ngọc Ân', '21C, khu phố 1A, phường Tân Thới Hiệp, quận 12, TPHCM', '0123456541', 1, '2023-04-24'),
(5, 'Đỗ Nam Công Chính', 'Thủ Đức', '0123214569', 1, '2023-04-24');

-- --------------------------------------------------------

--
-- Table structure for table `khuvuckho`
--

CREATE TABLE `khuvuckho` (
  `makhuvuc` int(11) NOT NULL COMMENT 'Mã khu vực kho',
  `tenkhuvuc` varchar(255) NOT NULL COMMENT 'Tên khu vực kho',
  `ghichu` varchar(255) NOT NULL COMMENT 'Ghi chú cho khu vực',
  `trangthai` int(11) NOT NULL COMMENT 'Trạng thái hiện tại của khu vực'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `khuvuckho`
--

INSERT INTO `khuvuckho` (`makhuvuc`, `tenkhuvuc`, `ghichu`, `trangthai`) VALUES
(1, 'Khu vực A', 'Apple', 1),
(2, 'Khu vực B', 'Xiaomi', 1),
(3, 'Khu vực C', 'Samsung', 1),
(4, 'Khu vực C', 'Realme', 1),
(5, 'Khu vực E', 'Oppo', 1);

-- --------------------------------------------------------

--
-- Table structure for table `mausac`
--

CREATE TABLE `mausac` (
  `mamau` int(11) NOT NULL COMMENT 'Mã màu sắc',
  `tenmau` varchar(50) NOT NULL DEFAULT '0' COMMENT 'Tên màu sắc',
  `trangthai` tinyint(4) DEFAULT 1 COMMENT 'Trạng thái hiện tại'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `mausac`
--

INSERT INTO `mausac` (`mamau`, `tenmau`, `trangthai`) VALUES
(1, 'Xanh', 1),
(2, 'Đỏ', 1),
(3, 'Vàng', 1),
(4, 'Bạc', 1),
(5, 'Hồng', 1),
(6, 'Đen', 1),
(7, 'Xanh ngọc', 1);

-- --------------------------------------------------------

--
-- Table structure for table `nhacungcap`
--

CREATE TABLE `nhacungcap` (
  `manhacungcap` int(11) NOT NULL COMMENT 'Mã nhà cung cấp',
  `tennhacungcap` varchar(255) NOT NULL COMMENT 'Tên nhà cung cấp',
  `diachi` varchar(255) NOT NULL COMMENT 'Địa chỉ nhà cung cấp',
  `email` varchar(255) NOT NULL COMMENT 'Email nhà cung cấp',
  `sdt` varchar(255) NOT NULL COMMENT 'Số điện thoại nhà cung cấp',
  `trangthai` int(11) NOT NULL DEFAULT 1 COMMENT 'Trạng thái hiện tại'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `nhacungcap`
--

INSERT INTO `nhacungcap` (`manhacungcap`, `tennhacungcap`, `diachi`, `email`, `sdt`, `trangthai`) VALUES
(1, 'Đại học Sài Gòn', '273 An Dương Vương, Phường 3, Quận 5, Thành phố Hồ Chí Minh', 'dhsaigon@gmail.com', '0123456789', 1),
(2, 'Công ty PA Việt Nam', 'Số 79 đường số 6, Hưng Phước 4, Phú Mỹ Hưng, quận 7, TPHCM', 'contact@paviet.vn', '19009477', 1),
(3, 'Công ty TNHH Thực phẩm OK (OKFood)', '186 Phú Định, Phường 16, Quận 8, TP.HCM', 'okfoodvn@gmail.com', '0938451796', 1);

-- --------------------------------------------------------

--
-- Table structure for table `nhanvien`
--

CREATE TABLE `nhanvien` (
  `manv` int(11) NOT NULL COMMENT 'Mã nhân viên',
  `hoten` varchar(255) NOT NULL COMMENT 'Họ tên nhân viên',
  `gioitinh` int(11) NOT NULL COMMENT 'Giới tính nhân viên',
  `ngaysinh` date NOT NULL COMMENT 'Ngày sinh nhân viên',
  `sdt` varchar(50) NOT NULL COMMENT 'Số điện thoại nhân viên',
  `email` varchar(255) NOT NULL COMMENT 'Email nhân viên',
  `trangthai` int(11) NOT NULL COMMENT 'Trạng thái hiện tại của nhân viên'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `nhanvien`
--

INSERT INTO `nhanvien` (`manv`, `hoten`, `gioitinh`, `ngaysinh`, `sdt`, `email`, `trangthai`) VALUES
(1, 'Trần Nhật Sinhh', 1, '2003-12-20', '0387913347', 'transinh085@gmail.com', 1),
(2, 'hoang gia bao', 1, '2023-04-11', '0355374322', 'musicanime2501@gmail.com', 1),
(3, 'Đinh Ngọc Ân', 1, '2023-04-15', '0765010628', 'andinh1443@gmail.com', 1);

-- --------------------------------------------------------

--
-- Table structure for table `nhomquyen`
--

CREATE TABLE `nhomquyen` (
  `manhomquyen` int(11) NOT NULL COMMENT 'Mã nhóm quyền',
  `tennhomquyen` varchar(255) NOT NULL COMMENT 'Tên nhóm quyền',
  `trangthai` int(11) NOT NULL COMMENT 'Trạng thái hiện tại của nhóm quyền'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `nhomquyen`
--

INSERT INTO `nhomquyen` (`manhomquyen`, `tennhomquyen`, `trangthai`) VALUES
(1, 'Admin', 1),
(2, 'Nhân viên nhập hàng', 1),
(3, 'Nhân viên xuất hàng', 1),
(4, 'Thủ kho', 1),
(5, 'Nhân viên kiểm kho', 1),
(6, 'tesst', 0),
(7, '', 0),
(8, 'ok', 1);

-- --------------------------------------------------------

--
-- Table structure for table `phienbansanpham`
--

CREATE TABLE `phienbansanpham` (
  `maphienbansp` int(11) NOT NULL COMMENT 'Mã phiên bản sản phẩm',
  `masp` int(11) DEFAULT NULL COMMENT 'Mã sản phẩm thuộc phiên bản',
  `rom` int(11) DEFAULT NULL COMMENT 'Mã dung lượng Rom của máy',
  `ram` int(11) DEFAULT 0 COMMENT 'Mã dung lượng Ram của máy	',
  `mausac` int(11) DEFAULT NULL COMMENT 'Màu sắc sản phẩm',
  `gianhap` int(11) DEFAULT NULL COMMENT 'Giá nhập của sản phẩm',
  `giaxuat` int(11) DEFAULT NULL COMMENT 'Giá xuất của sản phẩm',
  `soluongton` int(11) DEFAULT 0 COMMENT 'Số lượng tồn hiện tại của phiên bản sản phẩm',
  `trangthai` tinyint(1) NOT NULL DEFAULT 1 COMMENT 'Trạng thái hiện tại của phiên bản sản phẩm'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `phienbansanpham`
--

INSERT INTO `phienbansanpham` (`maphienbansp`, `masp`, `rom`, `ram`, `mausac`, `gianhap`, `giaxuat`, `soluongton`, `trangthai`) VALUES
(2, 1, 1, 1, 1, 5000000, 5500000, 0, 1),
(15, 1, 3, 4, 1, 6000000, 6500000, 0, 1),
(17, 2, 3, 5, 1, 7000000, 7800000, 0, 1),
(19, 3, 3, 3, 5, 15000000, 16500000, 0, 1),
(21, 3, 3, 3, 6, 15000000, 16500000, 0, 1),
(23, 3, 3, 3, 1, 15500000, 17000000, 0, 1);

-- --------------------------------------------------------

--
-- Table structure for table `phieubaohanh`
--

CREATE TABLE `phieubaohanh` (
  `maphieubaohanh` int(11) NOT NULL COMMENT 'Mã phiếu bảo hành',
  `maimei` varchar(255) NOT NULL COMMENT 'Mã imei máy bảo hành',
  `lydo` varchar(50) NOT NULL COMMENT 'Lý do bảo hành',
  `thoigian` datetime NOT NULL DEFAULT curdate(),
  `thoigiantra` datetime DEFAULT NULL COMMENT 'Thời gian trả sản phẩm lại cho khách hàng',
  `makh` int(11) DEFAULT NULL COMMENT 'Mã khách hàng có sản phẩm bảo hành'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `phieudoi`
--

CREATE TABLE `phieudoi` (
  `maphieudoi` tinyint(4) NOT NULL COMMENT 'Mã phiếu đổi',
  `thoigian` date DEFAULT curdate(),
  `nguoitao` int(11) NOT NULL COMMENT '	Mã người tạo phiếu'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `phieukiemke`
--

CREATE TABLE `phieukiemke` (
  `maphieu` int(11) NOT NULL COMMENT 'Mã phiếu kiểm kê',
  `thoigian` date NOT NULL DEFAULT curdate(),
  `nguoitaophieukiemke` int(11) NOT NULL COMMENT 'Mã người tạo phiếu'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `phieunhap`
--

CREATE TABLE `phieunhap` (
  `maphieunhap` int(11) NOT NULL COMMENT 'Mã phiếu nhập',
  `thoigian` datetime DEFAULT current_timestamp() COMMENT 'Thời gian tạo phiếu',
  `manhacungcap` int(11) NOT NULL COMMENT 'Mã nhà cung cấp sản phẩm',
  `nguoitao` int(11) NOT NULL COMMENT '	Mã người tạo phiếu',
  `tongtien` bigint(20) NOT NULL DEFAULT 0 COMMENT 'Tổng số tiền của phiếu nhập',
  `trangthai` int(11) NOT NULL DEFAULT 1 COMMENT 'Trạng thái hiện tại của phiếu'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `phieunhap`
--

INSERT INTO `phieunhap` (`maphieunhap`, `thoigian`, `manhacungcap`, `nguoitao`, `tongtien`, `trangthai`) VALUES
(1, '2023-04-14 21:00:43', 1, 1, 96000000, 1),
(2, '2023-04-15 00:21:28', 1, 1, 750000000, 1),
(3, '2023-04-15 00:37:24', 1, 1, 10000000, 1);

-- --------------------------------------------------------

--
-- Table structure for table `phieutra`
--

CREATE TABLE `phieutra` (
  `maphieutra` int(11) NOT NULL COMMENT 'Mã phiếu trả',
  `thoigian` date DEFAULT curdate(),
  `nguoitao` int(11) NOT NULL COMMENT '	Mã người tạo phiếu'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `phieuxuat`
--

CREATE TABLE `phieuxuat` (
  `maphieuxuat` int(11) NOT NULL COMMENT 'Mã phiếu xuất',
  `thoigian` datetime NOT NULL DEFAULT current_timestamp() COMMENT 'Thời gian tạo phiếu',
  `tongtien` bigint(20) DEFAULT NULL COMMENT 'Tổng số tiền của phiếu',
  `nguoitaophieuxuat` int(11) DEFAULT NULL COMMENT '	Mã người tạo phiếu',
  `makh` int(11) DEFAULT NULL COMMENT 'Mã khách hàng nhận',
  `trangthai` int(11) DEFAULT NULL COMMENT 'Trạng thái hiện tại của phiếu'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `sanpham`
--

CREATE TABLE `sanpham` (
  `masp` int(11) NOT NULL COMMENT 'Mã sản phẩm',
  `tensp` varchar(255) DEFAULT NULL COMMENT 'Tên sản phẩm',
  `hinhanh` varchar(255) DEFAULT NULL COMMENT 'Hình ảnh sản phẩm',
  `xuatxu` int(11) DEFAULT NULL COMMENT 'Xuất xứ của sản phẩm',
  `chipxuly` varchar(255) DEFAULT NULL COMMENT 'Chip xử lý của sản phẩm',
  `dungluongpin` int(11) DEFAULT NULL COMMENT 'Dung lượng pin sản phẩm',
  `kichthuocman` double DEFAULT NULL COMMENT 'Kích thước màn hình sản phẩm',
  `hedieuhanh` int(11) DEFAULT NULL COMMENT 'Hệ điều hành của sản phẩm',
  `phienbanhdh` int(11) DEFAULT NULL COMMENT 'Phiên bản hệ điều hành',
  `camerasau` varchar(255) DEFAULT NULL COMMENT 'Độ phân giải Camera sau',
  `cameratruoc` varchar(255) DEFAULT NULL COMMENT 'Độ phân giải Camera trước',
  `thoigianbaohanh` int(11) DEFAULT NULL COMMENT 'Thời gian bảo hành của sản phẩm',
  `thuonghieu` int(11) DEFAULT NULL COMMENT 'Thương hiệu của sản phẩm',
  `khuvuckho` int(11) DEFAULT NULL COMMENT 'Khu vực kho chứa sản phẩm',
  `soluongton` int(11) DEFAULT 0 COMMENT 'Số lượng tồn của sản phẩm',
  `trangthai` tinyint(1) DEFAULT 1 COMMENT 'Trạng thái hiện tại của sản phẩm'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `sanpham`
--

INSERT INTO `sanpham` (`masp`, `tensp`, `hinhanh`, `xuatxu`, `chipxuly`, `dungluongpin`, `kichthuocman`, `hedieuhanh`, `phienbanhdh`, `camerasau`, `cameratruoc`, `thoigianbaohanh`, `thuonghieu`, `khuvuckho`, `soluongton`, `trangthai`) VALUES
(1, 'Vivo Y22s', '803avatar.jpg', 1, 'SnapDragon 680', 5000, 6.55, 1, 12, 'Chính 50 MP & Phụ 2 MP', '8 MP', 24, 1, 1, 0, 1),
(2, 'Samsung Galaxy A53 5G', '57samsung-galaxy-a53-cam-thumb-1-600x600.jpg', 1, 'Exynos 1280', 5000, 6.5, 1, 12, 'Chính 64 MP & Phụ 12 MP, 5 MP, 5 MP', '32 MP', 24, 3, 2, 0, 1),
(3, 'iPhone 13 mini', '997iphone-13-mini-pink-1-600x600.jpg', 1, ' Apple A15 Bionic', 2438, 5.4, 2, 15, '2 camera 12 MP', ' 12 MP', 36, 1, 1, 0, 1);

-- --------------------------------------------------------

--
-- Table structure for table `taikhoan`
--

CREATE TABLE `taikhoan` (
  `manv` int(11) NOT NULL COMMENT 'Mã nhân viên chủ tài khoản',
  `matkhau` varchar(255) NOT NULL COMMENT 'Mật khẩu',
  `manhomquyen` int(11) NOT NULL COMMENT 'Mã nhóm quyền của tài khoản',
  `tendangnhap` varchar(50) NOT NULL DEFAULT '' COMMENT 'Tên khi đăng nhập của tài khoản',
  `trangthai` int(11) NOT NULL COMMENT 'Trạng thái của tài khoản',
  `otp` varchar(50) DEFAULT NULL COMMENT 'Mã otp khi xác minh tài khoản (Nullable)'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `taikhoan`
--

INSERT INTO `taikhoan` (`manv`, `matkhau`, `manhomquyen`, `tendangnhap`, `trangthai`, `otp`) VALUES
(1, '$2a$12$DpzujPeBqAKbSJ1wUXdeteFDxNQxe0TFj3jbo3Spmdz2u3VDMjawG', 1, 'sinh', 1, '219598'),
(2, '$2a$12$NzRkFx2115iyA38.XIjK.enK.54njfiahuJBDZP8bhXUtzIsQwpAS', 1, 'baopro', 1, '451418'),
(3, '$2a$12$y/FEMm/LtYyXQ2IfXCBqQensGFVY.dkU4Ds.auhUiGqG613DzyDS.', 1, 'an', 1, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `thuonghieu`
--

CREATE TABLE `thuonghieu` (
  `mathuonghieu` int(11) NOT NULL COMMENT 'Mã thương hiệu',
  `tenthuonghieu` varchar(255) NOT NULL COMMENT 'Tên thương hiệu',
  `trangthai` tinyint(1) NOT NULL DEFAULT 1 COMMENT 'Trạng thái hiện tại của thương hiệu'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `thuonghieu`
--

INSERT INTO `thuonghieu` (`mathuonghieu`, `tenthuonghieu`, `trangthai`) VALUES
(1, 'Apple', 1),
(2, 'Xiaomi', 1),
(3, 'Samsung', 1),
(4, 'Sữa tươi', 0),
(7, 'g', 0),
(8, '', 0);

-- --------------------------------------------------------

--
-- Table structure for table `xuatxu`
--

CREATE TABLE `xuatxu` (
  `maxuatxu` int(11) NOT NULL COMMENT 'Mã xuất xứ',
  `tenxuatxu` varchar(50) NOT NULL COMMENT 'Tên nơi xuất xứ',
  `trangthai` tinyint(1) DEFAULT 1 COMMENT 'Trạng thái hiện tại'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `xuatxu`
--

INSERT INTO `xuatxu` (`maxuatxu`, `tenxuatxu`, `trangthai`) VALUES
(1, 'Trung Quốc', 1),
(2, 'Hàn Quốc', 1),
(3, 'Việt Nam', 1),
(4, '', 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `chitietkiemke_sanpham`
--
ALTER TABLE `chitietkiemke_sanpham`
  ADD PRIMARY KEY (`makiemke`,`maimei`,`masanpham`),
  ADD KEY `ChiTietKiemKe_SanPham_FK` (`makiemke`,`masanpham`),
  ADD KEY `ChiTietKiemKe_SanPham_FK_1` (`maimei`);

--
-- Indexes for table `ctkiemke`
--
ALTER TABLE `ctkiemke`
  ADD PRIMARY KEY (`maphieukiemmke`,`masanpham`),
  ADD KEY `ctkiemke_FK` (`masanpham`);

--
-- Indexes for table `ctphieudoi`
--
ALTER TABLE `ctphieudoi`
  ADD PRIMARY KEY (`maphieudoi`,`imei_doi`,`imei_moi`) USING BTREE,
  ADD KEY `ctphieudoi_FK_1` (`imei_doi`),
  ADD KEY `ctphieudoi_FK_2` (`imei_moi`);

--
-- Indexes for table `ctphieunhap`
--
ALTER TABLE `ctphieunhap`
  ADD PRIMARY KEY (`maphieunhap`,`maphienbansp`),
  ADD KEY `ctphieunhap_FK` (`maphienbansp`);

--
-- Indexes for table `ctphieutra`
--
ALTER TABLE `ctphieutra`
  ADD PRIMARY KEY (`maphieutra`),
  ADD KEY `ctphieutra_FK_1` (`imei_tra`);

--
-- Indexes for table `ctphieuxuat`
--
ALTER TABLE `ctphieuxuat`
  ADD PRIMARY KEY (`maphieuxuat`,`maphienbansp`);

--
-- Indexes for table `ctquyen`
--
ALTER TABLE `ctquyen`
  ADD PRIMARY KEY (`manhomquyen`,`machucnang`,`hanhdong`) USING BTREE,
  ADD KEY `ctquyen_FK` (`machucnang`);

--
-- Indexes for table `ctsanpham`
--
ALTER TABLE `ctsanpham`
  ADD PRIMARY KEY (`maimei`) USING BTREE;

--
-- Indexes for table `danhmucchucnang`
--
ALTER TABLE `danhmucchucnang`
  ADD PRIMARY KEY (`machucnang`);

--
-- Indexes for table `dungluongram`
--
ALTER TABLE `dungluongram`
  ADD PRIMARY KEY (`madlram`);

--
-- Indexes for table `dungluongrom`
--
ALTER TABLE `dungluongrom`
  ADD PRIMARY KEY (`madlrom`);

--
-- Indexes for table `hedieuhanh`
--
ALTER TABLE `hedieuhanh`
  ADD PRIMARY KEY (`mahedieuhanh`) USING BTREE;

--
-- Indexes for table `khachhang`
--
ALTER TABLE `khachhang`
  ADD PRIMARY KEY (`makh`);

--
-- Indexes for table `khuvuckho`
--
ALTER TABLE `khuvuckho`
  ADD PRIMARY KEY (`makhuvuc`);

--
-- Indexes for table `mausac`
--
ALTER TABLE `mausac`
  ADD PRIMARY KEY (`mamau`),
  ADD UNIQUE KEY `tenmau` (`tenmau`);

--
-- Indexes for table `nhacungcap`
--
ALTER TABLE `nhacungcap`
  ADD PRIMARY KEY (`manhacungcap`);

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
-- Indexes for table `phienbansanpham`
--
ALTER TABLE `phienbansanpham`
  ADD PRIMARY KEY (`maphienbansp`) USING BTREE,
  ADD KEY `FK_phienbansanpham_sanpham` (`masp`),
  ADD KEY `phienbansanpham_FK` (`mausac`),
  ADD KEY `phienbansanpham_FK_1` (`ram`),
  ADD KEY `phienbansanpham_FK_2` (`rom`);

--
-- Indexes for table `phieubaohanh`
--
ALTER TABLE `phieubaohanh`
  ADD PRIMARY KEY (`maphieubaohanh`),
  ADD KEY `phieubaohanh_FK` (`makh`),
  ADD KEY `phieubaohanh_FK_1` (`maimei`);

--
-- Indexes for table `phieudoi`
--
ALTER TABLE `phieudoi`
  ADD PRIMARY KEY (`maphieudoi`),
  ADD KEY `FK_phieudoi_taikhoan` (`nguoitao`);

--
-- Indexes for table `phieukiemke`
--
ALTER TABLE `phieukiemke`
  ADD PRIMARY KEY (`maphieu`),
  ADD KEY `FK_phieukiemke_taikhoan` (`nguoitaophieukiemke`);

--
-- Indexes for table `phieunhap`
--
ALTER TABLE `phieunhap`
  ADD PRIMARY KEY (`maphieunhap`),
  ADD KEY `FK_phieunhap_nhacungcap` (`manhacungcap`),
  ADD KEY `FK_phieunhap_taikhoan` (`nguoitao`);

--
-- Indexes for table `phieutra`
--
ALTER TABLE `phieutra`
  ADD PRIMARY KEY (`maphieutra`),
  ADD KEY `FK_phieutra_taikhoan` (`nguoitao`);

--
-- Indexes for table `phieuxuat`
--
ALTER TABLE `phieuxuat`
  ADD PRIMARY KEY (`maphieuxuat`),
  ADD KEY `FK_phieuxuat_khachhang` (`makh`),
  ADD KEY `FK_phieuxuat_taikhoan` (`nguoitaophieuxuat`);

--
-- Indexes for table `sanpham`
--
ALTER TABLE `sanpham`
  ADD PRIMARY KEY (`masp`) USING BTREE,
  ADD KEY `sanpham_FK` (`hedieuhanh`),
  ADD KEY `sanpham_FK_1` (`khuvuckho`),
  ADD KEY `sanpham_FK_2` (`thuonghieu`),
  ADD KEY `sanpham_FK_3` (`xuatxu`);

--
-- Indexes for table `taikhoan`
--
ALTER TABLE `taikhoan`
  ADD PRIMARY KEY (`manv`),
  ADD UNIQUE KEY `tendangnhap` (`tendangnhap`),
  ADD KEY `FK_taikhoan_nhomquyen` (`manhomquyen`);

--
-- Indexes for table `thuonghieu`
--
ALTER TABLE `thuonghieu`
  ADD PRIMARY KEY (`mathuonghieu`) USING BTREE;

--
-- Indexes for table `xuatxu`
--
ALTER TABLE `xuatxu`
  ADD PRIMARY KEY (`maxuatxu`) USING BTREE;

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `ctphieudoi`
--
ALTER TABLE `ctphieudoi`
  MODIFY `maphieudoi` tinyint(4) NOT NULL AUTO_INCREMENT COMMENT 'Mã phiếu đổi';

--
-- AUTO_INCREMENT for table `dungluongram`
--
ALTER TABLE `dungluongram`
  MODIFY `madlram` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Mã dung lượng Ram', AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `dungluongrom`
--
ALTER TABLE `dungluongrom`
  MODIFY `madlrom` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Mã dung lượng Rom', AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `hedieuhanh`
--
ALTER TABLE `hedieuhanh`
  MODIFY `mahedieuhanh` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Mã hệ điều hành', AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `khachhang`
--
ALTER TABLE `khachhang`
  MODIFY `makh` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Mã khách hàng', AUTO_INCREMENT=35;

--
-- AUTO_INCREMENT for table `khuvuckho`
--
ALTER TABLE `khuvuckho`
  MODIFY `makhuvuc` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Mã khu vực kho', AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `mausac`
--
ALTER TABLE `mausac`
  MODIFY `mamau` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Mã màu sắc', AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `nhacungcap`
--
ALTER TABLE `nhacungcap`
  MODIFY `manhacungcap` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Mã nhà cung cấp', AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `nhanvien`
--
ALTER TABLE `nhanvien`
  MODIFY `manv` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Mã nhân viên', AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `nhomquyen`
--
ALTER TABLE `nhomquyen`
  MODIFY `manhomquyen` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Mã nhóm quyền', AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `phienbansanpham`
--
ALTER TABLE `phienbansanpham`
  MODIFY `maphienbansp` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Mã phiên bản sản phẩm', AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT for table `phieubaohanh`
--
ALTER TABLE `phieubaohanh`
  MODIFY `maphieubaohanh` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Mã phiếu bảo hành';

--
-- AUTO_INCREMENT for table `phieukiemke`
--
ALTER TABLE `phieukiemke`
  MODIFY `maphieu` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Mã phiếu kiểm kê';

--
-- AUTO_INCREMENT for table `phieunhap`
--
ALTER TABLE `phieunhap`
  MODIFY `maphieunhap` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Mã phiếu nhập', AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `phieutra`
--
ALTER TABLE `phieutra`
  MODIFY `maphieutra` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Mã phiếu trả';

--
-- AUTO_INCREMENT for table `phieuxuat`
--
ALTER TABLE `phieuxuat`
  MODIFY `maphieuxuat` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Mã phiếu xuất', AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `sanpham`
--
ALTER TABLE `sanpham`
  MODIFY `masp` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Mã sản phẩm', AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `thuonghieu`
--
ALTER TABLE `thuonghieu`
  MODIFY `mathuonghieu` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Mã thương hiệu', AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `xuatxu`
--
ALTER TABLE `xuatxu`
  MODIFY `maxuatxu` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Mã xuất xứ', AUTO_INCREMENT=5;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `chitietkiemke_sanpham`
--
ALTER TABLE `chitietkiemke_sanpham`
  ADD CONSTRAINT `ChiTietKiemKe_SanPham_FK` FOREIGN KEY (`makiemke`,`masanpham`) REFERENCES `ctkiemke` (`maphieukiemmke`, `masanpham`),
  ADD CONSTRAINT `ChiTietKiemKe_SanPham_FK_1` FOREIGN KEY (`maimei`) REFERENCES `ctsanpham` (`maimei`);

--
-- Constraints for table `ctkiemke`
--
ALTER TABLE `ctkiemke`
  ADD CONSTRAINT `FK_ctkiemke_phieukiemke` FOREIGN KEY (`maphieukiemmke`) REFERENCES `phieukiemke` (`maphieu`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `ctphieudoi`
--
ALTER TABLE `ctphieudoi`
  ADD CONSTRAINT `ctphieudoi_FK` FOREIGN KEY (`maphieudoi`) REFERENCES `phieudoi` (`maphieudoi`),
  ADD CONSTRAINT `ctphieudoi_FK_1` FOREIGN KEY (`imei_doi`) REFERENCES `ctsanpham` (`maimei`),
  ADD CONSTRAINT `ctphieudoi_FK_2` FOREIGN KEY (`imei_moi`) REFERENCES `ctsanpham` (`maimei`);

--
-- Constraints for table `ctphieunhap`
--
ALTER TABLE `ctphieunhap`
  ADD CONSTRAINT `ctphieunhap_FK` FOREIGN KEY (`maphienbansp`) REFERENCES `phienbansanpham` (`maphienbansp`),
  ADD CONSTRAINT `ctphieunhap_FK_1` FOREIGN KEY (`maphieunhap`) REFERENCES `phieunhap` (`maphieunhap`);

--
-- Constraints for table `ctphieutra`
--
ALTER TABLE `ctphieutra`
  ADD CONSTRAINT `ctphieutra_FK` FOREIGN KEY (`maphieutra`) REFERENCES `phieutra` (`maphieutra`),
  ADD CONSTRAINT `ctphieutra_FK_1` FOREIGN KEY (`imei_tra`) REFERENCES `ctsanpham` (`maimei`);

--
-- Constraints for table `ctphieuxuat`
--
ALTER TABLE `ctphieuxuat`
  ADD CONSTRAINT `FK__phieuxuat` FOREIGN KEY (`maphieuxuat`) REFERENCES `phieuxuat` (`maphieuxuat`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `ctquyen`
--
ALTER TABLE `ctquyen`
  ADD CONSTRAINT `FK__nhomquyen` FOREIGN KEY (`manhomquyen`) REFERENCES `nhomquyen` (`manhomquyen`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `ctquyen_FK` FOREIGN KEY (`machucnang`) REFERENCES `danhmucchucnang` (`machucnang`);

--
-- Constraints for table `phienbansanpham`
--
ALTER TABLE `phienbansanpham`
  ADD CONSTRAINT `FK_phienbansanpham_sanpham` FOREIGN KEY (`masp`) REFERENCES `sanpham` (`masp`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `phienbansanpham_FK` FOREIGN KEY (`mausac`) REFERENCES `mausac` (`mamau`),
  ADD CONSTRAINT `phienbansanpham_FK_1` FOREIGN KEY (`ram`) REFERENCES `dungluongram` (`madlram`),
  ADD CONSTRAINT `phienbansanpham_FK_2` FOREIGN KEY (`rom`) REFERENCES `dungluongrom` (`madlrom`);

--
-- Constraints for table `phieubaohanh`
--
ALTER TABLE `phieubaohanh`
  ADD CONSTRAINT `phieubaohanh_FK` FOREIGN KEY (`makh`) REFERENCES `khachhang` (`makh`),
  ADD CONSTRAINT `phieubaohanh_FK_1` FOREIGN KEY (`maimei`) REFERENCES `ctsanpham` (`maimei`);

--
-- Constraints for table `phieudoi`
--
ALTER TABLE `phieudoi`
  ADD CONSTRAINT `FK_phieudoi_taikhoan` FOREIGN KEY (`nguoitao`) REFERENCES `taikhoan` (`manv`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `phieukiemke`
--
ALTER TABLE `phieukiemke`
  ADD CONSTRAINT `FK_phieukiemke_taikhoan` FOREIGN KEY (`nguoitaophieukiemke`) REFERENCES `taikhoan` (`manv`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `phieunhap`
--
ALTER TABLE `phieunhap`
  ADD CONSTRAINT `FK_phieunhap_nhacungcap` FOREIGN KEY (`manhacungcap`) REFERENCES `nhacungcap` (`manhacungcap`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `FK_phieunhap_taikhoan` FOREIGN KEY (`nguoitao`) REFERENCES `taikhoan` (`manv`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `phieutra`
--
ALTER TABLE `phieutra`
  ADD CONSTRAINT `FK_phieutra_taikhoan` FOREIGN KEY (`nguoitao`) REFERENCES `taikhoan` (`manv`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `phieuxuat`
--
ALTER TABLE `phieuxuat`
  ADD CONSTRAINT `FK_phieuxuat_khachhang` FOREIGN KEY (`makh`) REFERENCES `khachhang` (`makh`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `FK_phieuxuat_taikhoan` FOREIGN KEY (`nguoitaophieuxuat`) REFERENCES `taikhoan` (`manv`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `sanpham`
--
ALTER TABLE `sanpham`
  ADD CONSTRAINT `sanpham_FK` FOREIGN KEY (`hedieuhanh`) REFERENCES `hedieuhanh` (`mahedieuhanh`),
  ADD CONSTRAINT `sanpham_FK_1` FOREIGN KEY (`khuvuckho`) REFERENCES `khuvuckho` (`makhuvuc`),
  ADD CONSTRAINT `sanpham_FK_2` FOREIGN KEY (`thuonghieu`) REFERENCES `thuonghieu` (`mathuonghieu`),
  ADD CONSTRAINT `sanpham_FK_3` FOREIGN KEY (`xuatxu`) REFERENCES `xuatxu` (`maxuatxu`);

--
-- Constraints for table `taikhoan`
--
ALTER TABLE `taikhoan`
  ADD CONSTRAINT `FK_taikhoan_nhanvien` FOREIGN KEY (`manv`) REFERENCES `nhanvien` (`manv`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `FK_taikhoan_nhomquyen` FOREIGN KEY (`manhomquyen`) REFERENCES `nhomquyen` (`manhomquyen`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
