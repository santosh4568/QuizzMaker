import Footer from './Footer'
import Header from './Header'
import MainContent from './MainContent'
import Navbar from './Navbar';
import { useState, useEffect } from 'react';
import Credentials from './Credentials'

const Home = () => {
    const [login, setLogin] = useState(false);
    const [username, setUsername] = useState('');
    const [usernaam, setUsernaam] = useState('');
    const [role, setRole] = useState('');

    useEffect(() => {
        // Get credentials only once when component mounts
        const isLogin = Credentials.getIsLogin();
        const user_role = Credentials.getUser_role();
        const user_name = Credentials.getUser_name();
        const user_naam = Credentials.getUser_naam();

        setLogin(isLogin);
        setUsername(user_name || '');
        setUsernaam(user_naam || '');
        setRole(user_role || '');
    }, []); // Empty dependency array means this runs once on mount

    return (
        <div>
          <Navbar user={username} role={role}/>
            <Header />
            <MainContent 
                isLoggedIn={login}
                username={username}
                usernaam={usernaam}
                role={role}
            />
            <Footer />
        </div>
    );
};

export default Home;