"use client";
import { useState } from "react";
import { useRouter } from "next/navigation";
import { api } from "@/lib/axios";

export function useRegisterPage() {
  const router = useRouter();
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");
  const [formData, setFormData] = useState({
    hoTen: "",
    email: "",
    sdt: "",
    password: "",
  });

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
    if (error) setError("");
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setLoading(true);

    try {
      await api.post("/api/auth/register", formData);
      alert("Đăng ký thành công! Vui lòng đăng nhập.");
      router.push("/auth/login");
    } catch (err: any) {
      setError(
        err.response?.data?.message ||
          err.response?.data ||
          "Đăng ký thất bại, email có thể đã tồn tại.",
      );
    } finally {
      setLoading(false);
    }
  };

  return { formData, error, loading, handleChange, handleSubmit };
}
