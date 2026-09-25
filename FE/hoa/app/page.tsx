"use client";

import { useRouter } from "next/navigation";

export default function HomePage() {
  const router = useRouter();

  return (
    <div
      className="relative min-h-screen bg-cover bg-center bg-no-repeat"
      style={{
        backgroundImage:
          "url('https://pendecor.vn/uploads/files/2022/01/25/thiet-ke-shop-hoa-tuoi-nho-1.jpg')",
      }}
    >
      {/* Lớp phủ mờ */}
      <div className="absolute inset-0 z-0 bg-black/40"></div>

      {/* Cụm nút */}
      <div className="absolute top-6 right-6 z-20 flex gap-4">
        <button
          onClick={() => router.push("/auth/login")}
          className="px-6 py-2.5 bg-green-600/90 backdrop-blur-sm text-white rounded-lg font-semibold hover:bg-green-700 transition-colors shadow-lg"
        >
          Đăng nhập
        </button>

        <button
          onClick={() => router.push("/auth/register")}
          className="px-6 py-2.5 bg-green-600/90 backdrop-blur-sm text-white rounded-lg font-semibold hover:bg-green-700 transition-colors shadow-lg"
        >
          Đăng ký
        </button>
      </div>

      {/* Nội dung */}
      <div className="relative z-10 flex flex-col items-center justify-center min-h-screen text-white px-4">
        <h1 className="text-5xl md:text-7xl font-extrabold mb-4 drop-shadow-xl text-center">
          Shop Hoa
        </h1>

        <p className="text-lg md:text-xl font-medium drop-shadow-md text-center max-w-2xl">
          Chào mừng bạn đến với hệ thống. Vui lòng đăng nhập hoặc tạo tài khoản
          mới để trải nghiệm dịch vụ.
        </p>
      </div>
    </div>
  );
}
