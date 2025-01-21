import React from 'react'
import { useEffect } from 'react'
import Credentials from './Credentials'
import { useNavigate } from 'react-router-dom';
import { use } from 'react';

const Logout = () => {
    const navigate = useNavigate();
    Credentials.setIsLogin(false);
    Credentials.setUser_name('');
    Credentials.setUser_role('');
    Credentials.setUser_naam('');
    //console.log(Credentials.getUser_name()+" "+Credentials.getUser_role()+" "+Credentials.getUser_naam()+" "+Credentials.getIsLogin());
    useEffect(() => {
        //console.log(Credentials.getUser_name()+" "+Credentials.getUser_role()+" "+Credentials.getUser_naam()+" "+Credentials.getIsLogin());
        navigate('/');
    }, []);
    return (
    <div>
      <h1>Logout</h1>
      <p>You have been logged out.</p>
    </div>
  )
}

export default Logout
