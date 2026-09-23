"use client";
import { useState } from "react";
import { useRouter } from "next/navigation";
import { api } from "@/lib/axios";

export function useLoginPage() {
  const router = useRouter();
  const [formData, setFormData] = useState({ email: "", password: "" });
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(false);

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
    if (error) setError("");
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setLoading(true);

    try {
      const res = await api.post("/api/auth/login", formData);
      const { accessToken, user } = res.data;

      // Lưu trữ Token và thông tin User
      localStorage.setItem("token", accessToken);
      localStorage.setItem("user", JSON.stringify(user));

      router.push("/"); // Chuyển về trang chủ
    } catch (err: any) {
      setError(
        err.response?.data?.message ||
          err.response?.data ||
          "Sai email hoặc mật khẩu",
      );
    } finally {
      setLoading(false);
    }
  };

  return { formData, error, loading, handleChange, handleSubmit };
}
