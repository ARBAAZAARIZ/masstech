<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Footer</title>
  <style>
    .footer {
      background: linear-gradient(to right, #2C3E50, #4CA1AF); /* dark pastel blend */
      color: #FDF6F0;
      padding: 20px 0;
      text-align: center;
      font-family: 'Segoe UI', sans-serif;
      margin-top: 60px;
      box-shadow: 0 -4px 12px rgba(0,0,0,0.1);
      width: 100%;
    }

    .footer-content p {
      margin: 4px 0;
      font-size: 0.95rem;
      font-weight: 500;
      letter-spacing: 0.5px;
    }

    .footer-content p:first-child {
      font-size: 1rem;
      font-weight: 600;
    }

    @media (max-width: 600px) {
      .footer-content p {
        font-size: 0.85rem;
      }
    }
  </style>
</head>
<body>
  <div class="footer">
    <div class="footer-content">
      <p>&copy; 2025 Vesta Society Management</p>
      <p>Crafted with care by MD | Empowering secure, smart living</p>
    </div>
  </div>
</body>
</html>
