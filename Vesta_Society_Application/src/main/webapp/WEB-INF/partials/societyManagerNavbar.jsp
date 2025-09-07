<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<style>
  @import url('https://fonts.googleapis.com/css2?family=Rubik:wght@500;700&display=swap');

  .sidebar {
    width: 260px;
    height: 100vh;
    background-color: #E5E0D8;
    color: #748873;
    font-family: 'Rubik', sans-serif;
    position: fixed;
    top: 0;
    left: 0;
    padding: 2rem 1.5rem;
    box-shadow: 4px 0 12px rgba(0, 0, 0, 0.08);
    z-index: 1000;
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    overflow-y: auto; /* ✅ Enables scrolling */
  }

  /* ✅ Custom Scrollbar */
  .sidebar::-webkit-scrollbar {
    width: 6px;
  }

  .sidebar::-webkit-scrollbar-thumb {
    background-color: #D1A980;
    border-radius: 10px;
  }

  .sidebar .brand {
    font-size: 1.9rem;
    font-weight: 700;
    color: #748873;
    text-decoration: none;
    letter-spacing: 1px;
    margin-bottom: 2rem;
    display: block;
    text-align: center;
  }

  .profile-section {
    text-align: center;
    margin-bottom: 2rem;
  }

  .profile-img {
    width: 80px;
    height: 80px;
    border-radius: 50%;
    object-fit: cover;
    border: 3px solid #D1A980;
    margin-bottom: 0.5rem;
    transition: transform 0.3s ease;
  }

  .profile-img:hover {
    transform: scale(1.05);
    cursor: pointer;
  }

  .profile-name {
    font-size: 1rem;
    font-weight: 500;
    color: #748873;
  }

  .nav-links {
    flex-grow: 1;
  }

  .nav-link {
    display: block;
    color: #748873;
    font-size: 1.05rem;
    font-weight: 500;
    margin: 1rem 0;
    text-decoration: none;
    transition: all 0.3s ease;
    padding: 0.5rem 1rem;
    border-radius: 8px;
    background-color: #F8F8F8;
  }

  .nav-link:hover {
    background-color: #D1A980;
    color: #ffffff;
  }

  .notification {
    position: relative;
    display: inline-block;
    font-size: 1.2rem;
  }

  .notification-badge {
    position: absolute;
    top: -6px;
    right: -10px;
    font-size: 0.75rem;
    padding: 4px 8px;
    border-radius: 12px;
    background-color: #dc3545;
    color: #fff;
    font-weight: 600;
  }

  .sidebar-footer {
    text-align: center;
    font-size: 0.85rem;
    color: #A0A0A0;
    padding-top: 1rem;
    border-top: 1px solid rgba(116,136,115,0.2);
  }

  @media (max-width: 768px) {
    .sidebar {
      width: 100%;
      height: auto;
      position: relative;
      padding: 1rem;
    }
  }
</style>

<div class="sidebar">
  <div>
    <a href="SocietyManagerServlet" class="brand">🏢 Vesta Manager</a>

    <div class="profile-section">
      <img src="${pageContext.request.contextPath}/images/${authUser.profile_photo}" alt="Profile" class="profile-img" />
      <div class="profile-name">${authUser.fullName}</div>
    </div>

    <div class="nav-links">
      <a href="ViewNotificationServlet" class="nav-link notification" title="Notifications">
        <i class="bi bi-bell-fill"></i>
        <c:if test="${sessionScope.unreadCount > 0}">
          <span class="notification-badge">${sessionScope.unreadCount}</span>
        </c:if>
        &nbsp; Notifications
      </a>
      <a href="SocietyManagerServlet" class="nav-link"><i class="bi bi-speedometer2 me-2"></i> Dashboard</a>
      <a href="UserServlet?action=view" class="nav-link"><i class="bi bi-people-fill me-2"></i> View Users</a>
      <a href="AssignFlatServlet" class="nav-link"><i class="bi bi-house-door-fill me-2"></i> Assign Flats</a>
      <a href="ParkingAssignmentServlet?action=view" class="nav-link"><i class="bi bi-truck me-2"></i> View Vehicles Assignment's</a>
      <a href="ViewParkingMap" class="nav-link"><i class="bi bi-grid-3x3-gap-fill me-2"></i> Parking Mapping</a>
      <a href="ViewAmenityServlet?action=view" class="nav-link"><i class="bi bi-calendar-check-fill me-2"></i> Amenity Approvals</a>
      <a href="ViewComplaints?action=view" class="nav-link"><i class="bi bi-chat-left-text-fill me-2"></i> Complaints</a>
      <a href="ProfileServlet" class="nav-link"><i class="bi bi-person-circle me-2"></i> My Profile</a>
      <a href="LogoutServlet" class="nav-link"><i class="bi bi-box-arrow-right me-2"></i> Logout</a>
    </div>
  </div>

  <div class="sidebar-footer">
    &copy; 2025 Vesta Society | Designed by MD
  </div>
</div>
