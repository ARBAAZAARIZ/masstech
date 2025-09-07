<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Create Society</title>

    <!-- Bootstrap & Icons -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">

    <!-- Page Styling -->
    <style>
        body {
            background: linear-gradient(to right, #FFF2EF, #FFDBB6);
            font-family: 'Segoe UI', sans-serif;
        }

        .main-content {
            margin-left: 270px;
            padding: 2rem;
        }

        .form-container {
            background-color: #fbeae7;
            border-radius: 16px;
            padding: 32px;
            max-width: 650px;
            margin: auto;
            box-shadow: 0 6px 16px rgba(0, 0, 0, 0.08);
        }

        h3 {
            color: #5D688A;
            font-weight: 600;
        }

        label.form-label {
            font-weight: 500;
            color: #4a4a4a;
        }

        .form-control {
            border-radius: 12px;
            border: 1px solid #d8cfcf;
            padding: 10px 14px;
            font-size: 1rem;
        }

        .form-control:focus {
            border-color: #F7A5A5;
            box-shadow: 0 0 0 0.2rem rgba(247, 165, 165, 0.25);
        }

        .btn-success {
            background-color: #F7A5A5;
            border: none;
            color: #2c2c2c;
            font-weight: 500;
            padding: 10px 24px;
            border-radius: 30px;
            transition: background 0.3s ease;
        }

        .btn-success:hover {
            background-color: #e68c8c;
        }

        .btn-secondary {
            background-color: #d3c0c0;
            border: none;
            color: #2c2c2c;
            font-weight: 500;
            padding: 10px 24px;
            border-radius: 30px;
        }

        .btn-secondary:hover {
            background-color: #bbaaaa;
        }

        .alert-danger {
            border-radius: 12px;
            font-weight: 500;
        }
    </style>
</head>
<body>

    <%@ include file="../partials/navbar.jsp" %>

    <div class="main-content">
        <h3 class="mb-4 text-center">Add New Society</h3>

        <!-- Error Message Section -->
        <c:if test="${not empty errorMessage}">
            <div class="alert alert-danger alert-dismissible fade show text-center" role="alert">
                <strong>Error:</strong> ${errorMessage}
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
        </c:if>

        <div class="form-container">
            <form action="SocietyCrudServlet" method="post">
                <input type="hidden" name="action" value="saveCreate" />

                <!-- Society Name -->
                <div class="mb-3">
                    <label class="form-label">Society Name</label>
                    <input type="text" name="name" class="form-control" placeholder="Enter society name" required />
                </div>

                <!-- Address Line 1 -->
                <div class="mb-3">
                    <label class="form-label">Address Line 1</label>
                    <input type="text" name="addressLine1" class="form-control" placeholder="Street, Building, etc." required />
                </div>

                <!-- Address Line 2 -->
                <div class="mb-3">
                    <label class="form-label">Address Line 2</label>
                    <input type="text" name="addressLine2" class="form-control" placeholder="Landmark, optional" />
                </div>

                <!-- City -->
                <div class="mb-3">
                    <label class="form-label">City</label>
                    <input type="text" name="city" class="form-control" required />
                </div>

                <!-- State -->
                <div class="mb-3">
                    <label class="form-label">State</label>
                    <input type="text" name="state" class="form-control" required />
                </div>

                <!-- Pincode -->
                <div class="mb-3">
                    <label class="form-label">Pincode</label>
                    <input type="text" name="pincode" class="form-control" maxlength="10" required />
                </div>

                <!-- Submit Button -->
                <div class="d-flex justify-content-center mt-4">
                    <button type="submit" class="btn btn-success me-3">
                        <i class="bi bi-check-circle me-1"></i> Create Society
                    </button>
                    <a href="SocietyCrudServlet?action=view" class="btn btn-secondary">
                        <i class="bi bi-x-circle me-1"></i> Cancel
                    </a>
                </div>
            </form>
        </div>
    </div>

    <%@ include file="../partials/footer.jsp" %>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
