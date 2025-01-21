// components/ProtectedRoute.jsx
import { Navigate } from 'react-router-dom';
import Credentials from './Credentials';

const ProtectedRoute = ({ children, requiredRole }) => {
    const isLoggedIn = Credentials.getIsLogin();
    const userRole = Credentials.getUser_role();

    if (!isLoggedIn) {
        return <Navigate to="/login" />;
    }

    if (requiredRole && userRole !== requiredRole) {
        return <Navigate to="/" />;
    }

    return children;
};

export default ProtectedRoute;