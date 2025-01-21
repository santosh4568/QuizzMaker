import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { signup } from '../api/UserService';
import Credentials from './Credentials';
import Navbar from './Navbar';

const Signup = () => {
    const navigate = useNavigate();
    const [formData, setFormData] = useState({
        name: '',
        username: '',
        email: '',
        phone:'',
        password: '',
        confirmPassword: '',
        role: 'USER'
    });
    const [errors, setErrors] = useState({});
    const [apiError, setApiError] = useState('');

    const validateForm = () => {
        const newErrors = {};
        if (!formData.name.trim()) newErrors.name = 'Name is required';
        if (!formData.username.trim()) newErrors.username = 'Username is required';
        if (!formData.email.trim()) {
            newErrors.email = 'Email is required';
        } else if (!/\S+@\S+\.\S+/.test(formData.email)) {
            newErrors.email = 'Email is invalid';
        }
        if (!formData.password) {
            newErrors.password = 'Password is required';
        } else if (formData.password.length < 6) {
            newErrors.password = 'Password must be at least 6 characters';
        }
        if (formData.password !== formData.confirmPassword) {
            newErrors.confirmPassword = 'Passwords do not match';
        }
        return newErrors;
    };

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData(prev => ({
            ...prev,
            [name]: value
        }));
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        const validationErrors = validateForm();
        
        if (Object.keys(validationErrors).length === 0) {
            try {
                const response = await signup(formData);
                if (response.data.status === 'success') {
                    navigate('/login');
                }
            } catch (error) {
                setApiError(error.response?.data?.message || 'Registration failed');
            }
        } else {
            setErrors(validationErrors);
        }
    };

    return (
        <>
        <Navbar />
        <div className="signup-container">
            <form onSubmit={handleSubmit} className="signup-form">
                {/* <h2>Sign Up</h2> */}
                {apiError && <div className="error-message">{apiError}</div>}
                
                <div className="form-group">
                    <input
                        type="text"
                        name="name"
                        placeholder="Full Name"
                        value={formData.name}
                        onChange={handleChange}
                    />
                    {errors.name && <p className="error">{errors.name}</p>}
                </div>

                <div className="form-group">
                    <input
                        type="text"
                        name="username"
                        placeholder="Username"
                        value={formData.username}
                        onChange={handleChange}
                    />
                    {errors.username && <p className="error">{errors.username}</p>}
                </div>

                <div className="form-group">
                    <input
                        type="email"
                        name="email"
                        placeholder="Email"
                        value={formData.email}
                        onChange={handleChange}
                    />
                    {errors.email && <p className="error">{errors.email}</p>}
                </div>

                <div className="form-group">
                    <input
                        type="text"
                        name="phone"
                        placeholder="Phone Number"
                        value={formData.phone}
                        onChange={handleChange}
                    />
                    {errors.phone && <p className="error">{errors.phone}</p>}
                </div>

                <div className="form-group">
                    <input
                        type="password"
                        name="password"
                        placeholder="Password"
                        value={formData.password}
                        onChange={handleChange}
                    />
                    {errors.password && <p className="error">{errors.password}</p>}
                </div>

                <div className="form-group">
                    <input
                        type="password"
                        name="confirmPassword"
                        placeholder="Confirm Password"
                        value={formData.confirmPassword}
                        onChange={handleChange}
                    />
                    {errors.confirmPassword && <p className="error">{errors.confirmPassword}</p>}
                </div>

                <div className="form-group">
                    <select name="role" value={formData.role} onChange={handleChange}>
                        <option value="USER">User</option>
                        <option value="CONDUCTOR">Conductor</option>
                    </select>
                </div>

                <button type="submit" className="signup-button">Sign Up</button>
            </form>
        </div>
        </>
    );

};

export default Signup;