import React from 'react';

const MainContent = () => {
    return (
        <main className="main-content">
            <div className="container">
                <section className="features">
                    <h2>Why Choose Quiz Maker?</h2>
                    <div className="feature-grid">
                        <div className="feature-item">
                            <h3>Create Quizzes</h3>
                            <p>Easily create engaging quizzes and share them with your audience.</p>
                        </div>
                        <div className="feature-item">
                            <h3>Take Quizzes</h3>
                            <p>Enjoy an interactive experience while testing your knowledge.</p>
                        </div>
                        <div className="feature-item">
                            <h3>Track Results</h3>
                            <p>Get instant feedback and analytics after completing a quiz.</p>
                        </div>
                    </div>
                </section>
            </div>
        </main>
    );
};

export default MainContent;
