// Navbar.jsx
import React from 'react';
import { Link , useNavigate } from 'react-router-dom';
import PropTypes from 'prop-types';
import Credentials from './Credentials';

const Navbar = ({ user, role }) => {
    const isLoggedIn = Credentials.getIsLogin();
    const navigate = useNavigate();

    // Handle click for protected routes
    const handleProtectedClick = (e) => {
        if (!isLoggedIn) {
            e.preventDefault();
           navigate('/login');
        }
    };

    return (
        <nav className="navbar">
            <div className="container">
                <Link to="/" className="logo">Quiz Maker</Link>
                {isLoggedIn ? (
                    <ul className="nav-links">
                        <li><Link to="/">Home</Link></li>
                        {role === 'CONDUCTOR' && (
                            <>
                                <li><Link to="/createQuizz">Create Quiz</Link></li>
                                <li><Link to="/viewQuizz">View Quiz</Link></li>
                            </>
                        )}
                        {role === 'USER' && (
                            <li><Link to="/selectTitle">Take Quiz</Link></li>
                        )}
                        <li><Link to="/profile">{user}</Link></li>
                        <li><Link to="/logout">Logout</Link></li>
                    </ul>
                ) : (
                    <ul className="nav-links">
                        <li><Link to="/">Home</Link></li>
                        <li><Link to="/login" onClick={handleProtectedClick}>Create Quiz</Link></li>
                        <li><Link to="/login" onClick={handleProtectedClick}>Take Quiz</Link></li>
                        <li><Link to="/login">Login</Link></li>
                        <li><Link to="/signup">Signup</Link></li>
                    </ul>
                )}
            </div>
        </nav>
    );
};

Navbar.propTypes = {
    user: PropTypes.string,
    role: PropTypes.string
};

export default Navbar;