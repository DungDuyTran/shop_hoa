"use client";
import { useRegisterPage } from "./useRegisterPage";
import { RegisterForm } from "./RegisterForm";

export default function RegisterPage() {
  const { formData, error, loading, handleChange, handleSubmit } =
    useRegisterPage();

  return (
    <RegisterForm
      formData={formData as any}
      error={error}
      loading={loading}
      onChange={handleChange}
      onSubmit={handleSubmit}
    />
  );
}
