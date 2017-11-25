-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Nov 20, 2017 at 08:24 AM
-- Server version: 10.1.13-MariaDB
-- PHP Version: 5.6.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `dv_aknkajen_pdosen`
--

-- --------------------------------------------------------

--
-- Table structure for table `akn_gedung`
--

CREATE TABLE `akn_gedung` (
  `kd_gedung` char(3) NOT NULL,
  `nama_gedung` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `akn_jurusan`
--

CREATE TABLE `akn_jurusan` (
  `kd_jur` char(3) NOT NULL,
  `jurusan` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `akn_jurusan`
--

INSERT INTO `akn_jurusan` (`kd_jur`, `jurusan`) VALUES
('J01', 'Teknik Komputer dan Informatika'),
('J02', 'Teknik Kimia Tekstil'),
('J03', 'Teknik Mesin');

-- --------------------------------------------------------

--
-- Table structure for table `akn_kelas`
--

CREATE TABLE `akn_kelas` (
  `id_kelas` tinyint(4) NOT NULL,
  `kelas` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `akn_prodi`
--

CREATE TABLE `akn_prodi` (
  `kd_prodi` char(3) NOT NULL,
  `kd_jur` char(3) NOT NULL,
  `prodi` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `akn_prodi`
--

INSERT INTO `akn_prodi` (`kd_prodi`, `kd_jur`, `prodi`) VALUES
('125', 'J03', 'D2 Teknik Mesin'),
('144', 'J02', 'D2 Teknik Kimia'),
('153', 'J01', 'D2 Teknik Informatika');

-- --------------------------------------------------------

--
-- Table structure for table `akn_ruang`
--

CREATE TABLE `akn_ruang` (
  `kd_ruang` char(6) NOT NULL,
  `kd_gedung` char(3) NOT NULL,
  `nama_ruang` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `akn_thajaran`
--

CREATE TABLE `akn_thajaran` (
  `id_th` int(4) NOT NULL,
  `tahun_ajaran` varchar(9) NOT NULL,
  `semester` enum('Gasal','Genap','','') NOT NULL,
  `sta_aktif` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `akn_thangkatan`
--

CREATE TABLE `akn_thangkatan` (
  `id_ta` int(4) NOT NULL,
  `th_angkatan` char(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `akn_thangkatan`
--

INSERT INTO `akn_thangkatan` (`id_ta`, `th_angkatan`) VALUES
(1, '2018'),
(2, '2016'),
(3, '2017'),
(4, '2015');

-- --------------------------------------------------------

--
-- Table structure for table `akn_user`
--

CREATE TABLE `akn_user` (
  `id_user` char(9) NOT NULL,
  `nama_user` varchar(50) NOT NULL,
  `pass` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `akn_user`
--

INSERT INTO `akn_user` (`id_user`, `nama_user`, `pass`) VALUES
('ID001', 'TU AKN Kajen 1', 'admin'),
('ID002', 'Atik', '123456');

-- --------------------------------------------------------

--
-- Table structure for table `mhs_mahasiswa`
--

CREATE TABLE `mhs_mahasiswa` (
  `nim` char(9) NOT NULL,
  `kd_prodi` char(3) NOT NULL,
  `id_ta` tinyint(4) NOT NULL,
  `nama_mhs` varchar(50) NOT NULL,
  `tmp_lahir` varchar(25) NOT NULL,
  `tgl_lahir` date NOT NULL,
  `jns_kel` enum('L','P') NOT NULL,
  `agama` varchar(10) NOT NULL,
  `alamat` varchar(50) DEFAULT NULL,
  `no_telp` varchar(25) DEFAULT NULL,
  `nama_ayah` varchar(35) DEFAULT NULL,
  `nama_ibu` varchar(35) DEFAULT NULL,
  `pkj_ayah` varchar(25) DEFAULT NULL,
  `pkj_ibu` varchar(25) DEFAULT NULL,
  `peng_ayah` double DEFAULT NULL,
  `peng_ibu` double DEFAULT NULL,
  `sta_mhs` tinyint(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `mhs_mahasiswa`
--

INSERT INTO `mhs_mahasiswa` (`nim`, `kd_prodi`, `id_ta`, `nama_mhs`, `tmp_lahir`, `tgl_lahir`, `jns_kel`, `agama`, `alamat`, `no_telp`, `nama_ayah`, `nama_ibu`, `pkj_ayah`, `pkj_ibu`, `peng_ayah`, `peng_ibu`, `sta_mhs`) VALUES
('151530006', '153', 4, 'Gilang Ramadhani Susilo', 'Pekalongan', '1998-01-10', 'L', 'Islam', 'Kedungwuni', '085878183999', 'Susila', 'Wartun', 'PNS', 'PNS', 3000000, 2000000, 1),
('151530017', '153', 4, 'Wiwik Rosalia', 'Pekalongan', '1997-01-04', 'P', 'Islam', 'Kesesi', '085642930739', 'Jeni B', 'Srigini', 'Wiraswasta', 'Wiraswasta', 3000000, 3000000, 1),
('151530028', '153', 4, 'Laelul Enjelita', 'Pekalongan', '1997-07-31', 'P', 'Islam', 'Tengeng Wetan', '085842111448', 'Sudi R', 'Wartumi', 'PNS', 'Ibu Rumah Tangga', 4000000, 0, 1);

-- --------------------------------------------------------

--
-- Table structure for table `mk_jadwalmk`
--

CREATE TABLE `mk_jadwalmk` (
  `id_tk` int(11) NOT NULL,
  `semester` int(2) NOT NULL,
  `kd_prodi` char(3) NOT NULL,
  `id_kelas` tinyint(4) NOT NULL,
  `kat_kelas` varchar(10) NOT NULL,
  `hari` varchar(10) NOT NULL,
  `id_sj` int(11) NOT NULL,
  `kd_mk` char(8) NOT NULL,
  `kd_dosen` char(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `mk_matakuliah`
--

CREATE TABLE `mk_matakuliah` (
  `kd_mk` char(8) NOT NULL,
  `kd_kmk` char(4) NOT NULL,
  `tahun_kur` int(4) NOT NULL,
  `kd_prodi` char(3) NOT NULL,
  `nama_mk` varchar(50) NOT NULL,
  `kategori_mk` varchar(15) NOT NULL,
  `sks_t` int(1) NOT NULL,
  `sks_p` int(1) NOT NULL,
  `jam_t` int(3) NOT NULL,
  `jam_p` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `mk_setjam`
--

CREATE TABLE `mk_setjam` (
  `id_sj` int(11) NOT NULL,
  `jam_ke` tinyint(1) NOT NULL,
  `jns_kelas` varchar(10) NOT NULL,
  `jam_mulai` time NOT NULL,
  `jam_selesai` time NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `pg_dosen`
--

CREATE TABLE `pg_dosen` (
  `nik` char(9) NOT NULL,
  `kd_dosen` char(6) NOT NULL,
  `nip` char(17) NOT NULL,
  `nama_pegawai` varchar(45) NOT NULL,
  `jk` enum('L','P','','') NOT NULL,
  `tmp_lahir` varchar(25) NOT NULL,
  `tgl_lahir` date NOT NULL,
  `agama` varchar(15) NOT NULL,
  `alamat` varchar(200) NOT NULL,
  `no_telepon1` int(11) NOT NULL,
  `no_telepon2` int(11) NOT NULL,
  `email` varchar(30) NOT NULL,
  `tmt` date NOT NULL,
  `nama_ibu` varchar(45) NOT NULL,
  `sta_menikah` enum('Belum Menikah','Menikah','Pernah Menikah','') NOT NULL,
  `jml_tanggungan` tinyint(2) NOT NULL,
  `sta_pegawai` enum('PNS','Kontrak','','') NOT NULL,
  `sta_aktif` enum('Aktif','Cuti','MD','Pensiun','Meninggal') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `pg_kategorikriterianilai`
--

CREATE TABLE `pg_kategorikriterianilai` (
  `kd_katn` char(7) NOT NULL,
  `id_th` tinyint(4) NOT NULL,
  `kd_prodi` char(3) NOT NULL,
  `kategori_penilaian` varchar(30) NOT NULL,
  `bobot` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `pg_kriterianilaidosen`
--

CREATE TABLE `pg_kriterianilaidosen` (
  `kd_knd` char(10) NOT NULL,
  `kd_katn` char(7) NOT NULL,
  `kriteria_penilaian` varchar(75) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `pg_masukandosen`
--

CREATE TABLE `pg_masukandosen` (
  `kd_nilai` char(11) NOT NULL,
  `masukan` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `pg_nilaidosen`
--

CREATE TABLE `pg_nilaidosen` (
  `kd_nilai` char(11) NOT NULL,
  `kd_knd` char(10) NOT NULL,
  `kd_dosen` char(6) NOT NULL,
  `nilai` int(1) NOT NULL,
  `nim` char(8) NOT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `akn_gedung`
--
ALTER TABLE `akn_gedung`
  ADD PRIMARY KEY (`kd_gedung`);

--
-- Indexes for table `akn_jurusan`
--
ALTER TABLE `akn_jurusan`
  ADD PRIMARY KEY (`kd_jur`);

--
-- Indexes for table `akn_kelas`
--
ALTER TABLE `akn_kelas`
  ADD PRIMARY KEY (`id_kelas`);

--
-- Indexes for table `akn_prodi`
--
ALTER TABLE `akn_prodi`
  ADD PRIMARY KEY (`kd_prodi`),
  ADD KEY `kd_jur` (`kd_jur`);

--
-- Indexes for table `akn_ruang`
--
ALTER TABLE `akn_ruang`
  ADD PRIMARY KEY (`kd_ruang`,`kd_gedung`);

--
-- Indexes for table `akn_thajaran`
--
ALTER TABLE `akn_thajaran`
  ADD PRIMARY KEY (`id_th`);

--
-- Indexes for table `akn_thangkatan`
--
ALTER TABLE `akn_thangkatan`
  ADD PRIMARY KEY (`id_ta`);

--
-- Indexes for table `akn_user`
--
ALTER TABLE `akn_user`
  ADD PRIMARY KEY (`id_user`);

--
-- Indexes for table `mhs_mahasiswa`
--
ALTER TABLE `mhs_mahasiswa`
  ADD PRIMARY KEY (`nim`),
  ADD KEY `kd_prodi` (`kd_prodi`),
  ADD KEY `id_ta` (`id_ta`);

--
-- Indexes for table `mk_jadwalmk`
--
ALTER TABLE `mk_jadwalmk`
  ADD PRIMARY KEY (`id_tk`,`kd_prodi`,`kd_mk`,`id_sj`,`id_kelas`);

--
-- Indexes for table `mk_matakuliah`
--
ALTER TABLE `mk_matakuliah`
  ADD PRIMARY KEY (`kd_mk`,`kd_kmk`,`kd_prodi`);

--
-- Indexes for table `mk_setjam`
--
ALTER TABLE `mk_setjam`
  ADD PRIMARY KEY (`id_sj`);

--
-- Indexes for table `pg_dosen`
--
ALTER TABLE `pg_dosen`
  ADD PRIMARY KEY (`nik`),
  ADD UNIQUE KEY `kd_dosen` (`kd_dosen`),
  ADD UNIQUE KEY `nip` (`nip`);

--
-- Indexes for table `pg_kategorikriterianilai`
--
ALTER TABLE `pg_kategorikriterianilai`
  ADD PRIMARY KEY (`kd_katn`,`id_th`,`kd_prodi`);

--
-- Indexes for table `pg_kriterianilaidosen`
--
ALTER TABLE `pg_kriterianilaidosen`
  ADD PRIMARY KEY (`kd_knd`,`kd_katn`);

--
-- Indexes for table `pg_masukandosen`
--
ALTER TABLE `pg_masukandosen`
  ADD PRIMARY KEY (`kd_nilai`);

--
-- Indexes for table `pg_nilaidosen`
--
ALTER TABLE `pg_nilaidosen`
  ADD PRIMARY KEY (`kd_nilai`,`kd_knd`,`kd_dosen`,`nim`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `akn_kelas`
--
ALTER TABLE `akn_kelas`
  MODIFY `id_kelas` tinyint(4) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `mk_setjam`
--
ALTER TABLE `mk_setjam`
  MODIFY `id_sj` int(11) NOT NULL AUTO_INCREMENT;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
