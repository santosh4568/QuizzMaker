// Login.jsx
import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { login } from '../api/UserService';
import Credentials from './Credentials';
import Navbar from './Navbar';

const Login = () => {
    const [role, setRole] = useState('USER');
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [errorMessage, setErrorMessage] = useState('');
    const navigate = useNavigate();

    const handleRoleChange = (e) => {
        setRole(e.target.value);
    };

    const handleSubmit = (e) => {
        e.preventDefault();
    };

    const handleLogin = async (e) => {
        e.preventDefault();
        
        try {
            const response = await login({ username, password, role });
            const data = response.data;
            
            if (data.status === 'success') {
                // Store user data in Credentials
                Credentials.setIsLogin(true);
                Credentials.setUser_role(data.role);
                Credentials.setUser_name(data.username);
                Credentials.setUser_naam(data.name);
                
                // Redirect to home page
                navigate('/');
            } else {
                setErrorMessage(data.message);
            }
        } catch (error) {
            if (error.response?.status === 404) {
                setErrorMessage('User not found');
            } else if (error.response?.data?.message) {
                setErrorMessage(error.response.data.message);
            } else {
                setErrorMessage('Login failed. Please try again.');
            }
        }
        setPassword('');
        setUsername('');
    };

    return (
        <div>
        <Navbar user={username === undefined ? '' : username} role={role === undefined ? '' : role}/>

        <section className="login-section">
            <div className="container">
                

                {/* Role Toggle */}
                <div className="role-toggle">
                    <label>
                        <input type="radio" name="role" value="USER" checked={role === 'USER'} onChange={handleRoleChange} />
                        I'm a Quiz Taker
                    </label>
                    <label>
                        <input type="radio" name="role" value="CONDUCTOR" checked={role === 'CONDUCTOR'} onChange={handleRoleChange} />
                        I'm a Quiz Conductor
                    </label>
                </div>

                {/* Login Form */}
                <form onSubmit={handleSubmit} className="login-form">
                    <div className="form-group">
                        <label htmlFor="username">Username</label>
                        <input type="text" id="username" name="username" value={username} onChange={(e) => setUsername(e.target.value)} required />
                    </div>

                    <div className="form-group">
                        <label htmlFor="password">Password</label>
                        <input type="password" id="password" name="password" value={password} onChange={(e) => setPassword(e.target.value)} required />
                    </div>

                    <input type="hidden" id="frole" name="frole" value={role} />
                    <button type="submit" className="submit-button" onClick={handleLogin}>Login</button>
                </form>
                <div className="error-message">
                    {errorMessage && <p>{errorMessage}</p>}
                </div>
            </div>
        </section>

        {/* <Footer /> */}
    </div>
    );
};
export default Login;