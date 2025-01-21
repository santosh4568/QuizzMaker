import React from 'react';
import PropTypes from 'prop-types';
import { Link, useNavigate, useParams } from 'react-router-dom';

const Header = ({ user, role }) => {
    let ctaLink = '/login';
    if (role === 'USER') ctaLink = '/selectTitle';
    if (role === 'CONDUCTOR') ctaLink = '/createQuizz';

    return (
        <header className="header">
            <div className="container">
                <h1>Welcome to Quiz Maker</h1>
                <p>Create, Share, and Play Quizzes with Ease</p>
                <Link to={ctaLink} className="cta-button">Get Started</Link>
            </div>
        </header>
    );
};

Header.propTypes = {
    user: PropTypes.string,
    role: PropTypes.string,
};

export default Header;
