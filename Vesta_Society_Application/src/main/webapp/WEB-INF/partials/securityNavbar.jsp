<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">

<style>
  .sidebar-security {
    position: fixed;
    top: 0;
    left: 0;
    height: 100vh;
    width: 270px;
    background: linear-gradient(180deg, #2C3E50, #4CA1AF);
    padding: 2rem 1.5rem;
    box-shadow: 4px 0 12px rgba(0, 0, 0, 0.2);
    font-family: 'Segoe UI', sans-serif;
    z-index: 1000;
  }

  .sidebar-brand {
    font-size: 1.6rem;
    font-weight: 600;
    color: #ffffff;
    margin-bottom: 2rem;
    display: flex;
    align-items: center;
    gap: 0.5rem;
    text-decoration: none;
  }

  .sidebar-profile {
    text-align: center;
    margin-bottom: 2rem;
  }

  .sidebar-profile img {
    width: 64px;
    height: 64px;
    object-fit: cover;
    border-radius: 50%;
    border: 2px solid #fff;
    margin-bottom: 0.5rem;
  }

  .sidebar-profile .profile-name {
    color: #ffffff;
    font-weight: 500;
    font-size: 1rem;
  }

  .sidebar-nav .nav-link {
    color: #e2e6ea;
    font-size: 1rem;
    font-weight: 500;
    padding: 0.6rem 1rem;
    border-radius: 8px;
    margin-bottom: 0.5rem;
    display: block;
  }

  .sidebar-nav .nav-link:hover {
    background-color: #ffffff22;
    color: #ffd700;
    text-decoration: none;
  }

  .main-content {
    margin-left: 270px;
    padding: 2rem;
  }
</style>

<!-- Sidebar -->
<div class="sidebar-security">
  <a href="SecurityServlet" class="sidebar-brand text-center">🛡️ Vesta Security Panel</a>

  <div class="sidebar-profile">
    <img src="${pageContext.request.contextPath}/images/${authUser.profile_photo}" alt="Profile" />
    <div class="profile-name">${authUser.fullName}</div>
  </div>

  <nav class="sidebar-nav">
    <a class="nav-link" href="SecurityServlet"><i class="bi bi-speedometer2 me-2"></i> Dashboard</a>
    <a class="nav-link" href="VisitorLogServlet?action=view"><i class="bi bi-person-lines-fill me-2"></i> Visitor Entries</a>
    <a class="nav-link" href="DeliveryLogServlet"><i class="bi bi-box-seam me-2"></i> Delivery Entries</a>
    <a class="nav-link" href="VisitorLogViewServlet"><i class="bi bi-person-badge me-2"></i> Visitor Approval Logs</a>
    <a class="nav-link" href="AmenityCheckinLogViewer"><i class="bi bi-calendar-check me-2"></i> Amenity Check-ins</a>
    <a class="nav-link" href="ViewMyNotificationsServlet"><i class="bi bi-exclamation-circle me-2"></i> Incident Reports</a>
    <a class="nav-link" href="ProfileServlet"><i class="bi bi-person-circle me-2"></i> My Profile</a>
    <a class="nav-link" href="LogoutServlet"><i class="bi bi-box-arrow-right me-2"></i> Logout</a>
  </nav>
</div>
