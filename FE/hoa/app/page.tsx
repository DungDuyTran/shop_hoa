import Link from "next/link";

export default function HomePage() {
  return (
    <div className="flex flex-col items-center justify-center min-h-screen bg-gray-50 p-4">
      <div className="bg-white p-10 rounded-2xl shadow-xl flex flex-col items-center max-w-lg w-full">
        <h1 className="text-4xl font-extrabold text-gray-900 mb-4 text-center">
          Shop Hoa
        </h1>
        <p className="text-gray-600 mb-8 text-center">
          Chào mừng bạn đến với hệ thống. Vui lòng đăng nhập hoặc tạo tài khoản
          mới để trải nghiệm dịch vụ.
        </p>

        <div className="flex flex-col sm:flex-row gap-4 w-full justify-center">
          <Link
            href="/auth/login"
            className="flex-1 text-center px-6 py-3 bg-blue-600 text-white rounded-lg font-semibold hover:bg-blue-700 transition-colors shadow-sm"
          >
            Đăng nhập
          </Link>
          <Link
            href="/auth/register"
            className="flex-1 text-center px-6 py-3 bg-green-600 text-white rounded-lg font-semibold hover:bg-green-700 transition-colors shadow-sm"
          >
            Đăng ký
          </Link>
        </div>
      </div>
    </div>
  );
}
