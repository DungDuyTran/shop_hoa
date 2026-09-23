"use client";
import React from "react";
import Link from "next/link";

interface Props {
  formData: { email: ""; password: "" };
  error: string;
  loading: boolean;
  onChange: (e: React.ChangeEvent<HTMLInputElement>) => void;
  onSubmit: (e: React.FormEvent) => void;
}

export function LoginForm({
  formData,
  error,
  loading,
  onChange,
  onSubmit,
}: Props) {
  return (
    <div className="flex items-center justify-center min-h-screen bg-gray-100">
      <div className="bg-white p-8 rounded-lg w-[400px] shadow-lg">
        <h1 className="text-3xl font-bold text-gray-800 mb-6 text-center">
          Đăng Nhập
        </h1>

        {error && (
          <div className="p-3 bg-red-100 border border-red-400 text-red-700 rounded mb-4 text-sm text-center">
            {error}
          </div>
        )}

        <form onSubmit={onSubmit} className="flex flex-col gap-4">
          <input
            type="email"
            name="email"
            required
            value={formData.email}
            onChange={onChange}
            placeholder="Email"
            className="p-3 rounded border border-gray-300 focus:outline-none focus:ring-2 focus:ring-blue-500 text-black"
          />
          <input
            type="password"
            name="password"
            required
            value={formData.password}
            onChange={onChange}
            placeholder="Mật khẩu"
            className="p-3 rounded border border-gray-300 focus:outline-none focus:ring-2 focus:ring-blue-500 text-black"
          />

          <button
            type="submit"
            disabled={loading}
            className="mt-2 bg-blue-600 hover:bg-blue-700 text-white font-semibold py-3 rounded transition-colors disabled:opacity-50"
          >
            {loading ? "Đang xử lý..." : "Đăng Nhập"}
          </button>
        </form>

        <p className="mt-6 text-gray-600 text-center text-sm">
          Chưa có tài khoản?{" "}
          <Link
            href="/auth/register"
            className="text-blue-600 font-bold hover:underline"
          >
            Đăng ký
          </Link>
        </p>
      </div>
    </div>
  );
}
