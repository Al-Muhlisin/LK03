# Filkom Mart - Object Oriented Programming Project (LK03)

## 📌 Deskripsi

Project ini merupakan implementasi konsep **Pemrograman Berorientasi Objek (OOP)** dalam bahasa **Java** untuk mensimulasikan sistem **Point of Sale (POS)** pada toko ritel *Filkom Mart*.

Program ini dibuat sebagai bagian dari **Latihan Kerja 03 (LK03)** dengan tujuan menerapkan konsep:

* Class dan Object
* Constructor
* Inheritance
* Polymorphism
* Method Overloading
* Method Overriding

Sistem memungkinkan pengelolaan berbagai jenis produk serta simulasi transaksi penjualan.

---

## 👥 Anggota Kelompok

| Nama                    | NIM             |
| ----------------------- | --------------- |
| Aryan Zaky Prayogo      | 255150207111059 |
| Achmad Hujairi          | 255150200111042 |
| M. Hidayatulloh H. A. M | 255150201111025 |
| M. Ahshal Zilhamsyah    | 255150200111041 |
| Dikardo Siahaan         | 255150200111040 |

---

## 🧩 Pembagian Tugas

| Anggota                     | Tugas                                                                      |
| --------------------------- | -------------------------------------------------------------------------- |
| **Aryan Zaky Prayogo**      | Implementasi class `ShopManagementSystem` (Main Program)                                      |
| **Achmad Hujairi**          | Implementasi class `FoodProduct` dan `ElectronicProduct`                                           |
| **M. Hidayatulloh H. A. M** | Membuat class `Product` (Superclass)                                      |
| **M. Ahshal Zilhamsyah**    | Implementasi class `ClothingProduct`                                       |
| **Dikardo Siahaan**         | Implementasi class `Transaction` |

---

## 🏗 Struktur Program

```
LK03
│
├── Product.java
├── FoodProduct.java
├── ElectronicProduct.java
├── ClothingProduct.java
├── Transaction.java
└── ShopManagementSystem.java
```

---

## ⚙️ Konsep OOP yang Digunakan

### 1️⃣ Encapsulation

Field pada class dibuat **private** dan diakses menggunakan **getter dan setter**.

### 2️⃣ Inheritance

Class berikut mewarisi `Product`:

* `FoodProduct`
* `ElectronicProduct`
* `ClothingProduct`

### 3️⃣ Method Overriding

Method `calculateDiscount()` di-override pada setiap subclass untuk aturan diskon yang berbeda.

### 4️⃣ Method Overloading

Beberapa method menggunakan overloading, seperti:

* `updateStock(int quantity)`
* `updateStock(int quantity, String reason)`
* `addItem(Product item)`
* `addItem(Product item, int quantity)`

Method `addItem(...)` berada pada class `Transaction` untuk mendukung penambahan item transaksi 1 produk maupun banyak produk sekaligus.

### 5️⃣ Polymorphism

Array bertipe `Product[]` digunakan untuk menyimpan berbagai jenis produk dan memprosesnya secara polymorphic.

---

## 🛒 Fitur Program

* Manajemen berbagai jenis produk
* Perhitungan diskon berdasarkan kategori produk
* Simulasi transaksi penjualan berbasis class `Transaction`
* Perhitungan total harga setelah diskon
* Pengelolaan stok produk

---

## 🔄 Mekanisme Program Saat Ini

### Alur di `ShopManagementSystem` (Main Program)

1. Menampilkan menu utama berbasis `Scanner`:
	 * Tambah produk
	 * Tampilkan produk
	 * Update stok
	 * Simulasi transaksi
2. Menyimpan produk pada array `Product[]` secara polymorphic.
3. Untuk transaksi, `ShopManagementSystem` hanya:
	 * menerima input ID produk dan kuantitas,
	 * memanggil `transaction.addItem(...)`,
	 * mengeksekusi checkout melalui `transaction.processCheckout()`,
	 * menampilkan struk melalui `transaction.printReceipt()`.

### Tanggung Jawab Class `Transaction`

* Menyimpan daftar item transaksi dan jumlahnya.
* Mendukung overloading:
	* `addItem(Product item)`
	* `addItem(Product item, int quantity)`
* Menghitung:
	* subtotal,
	* total diskon,
	* total bayar.
* Mengecek kecukupan stok saat checkout.
* Mengurangi stok produk setelah checkout berhasil.
* Mencetak struk transaksi.

---
